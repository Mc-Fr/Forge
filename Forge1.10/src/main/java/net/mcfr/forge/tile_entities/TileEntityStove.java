package net.mcfr.forge.tile_entities;

import net.mcfr.Constants;
import net.mcfr.McfrBlocks;
import net.mcfr.forge.BlockBellows;
import net.mcfr.forge.BlockStove;
import net.mcfr.forge.guis.ContainerStove;
import net.mcfr.utils.NBTUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityStove extends TileEntityLockable implements ITickable, ISidedInventory {
  /** La température maximale. */
  public static final int MAX_TEMPERATURE = 150;
  /** Le nombre de tics avant de changer la température d'un degré. */
  private static final int TICKS_PER_STEPS = 2 * 20;

  /** Le nombre de tics de l'item courant. */
  private int totalFuelTicks;
  /** Le nombre de tics restant à consommer. */
  private int remainingFuelTicks;
  /** Le nombre de tics restant avant d'augmenter d'un degré. */
  private int remainingStepTicks;
  private int temperature;
  private boolean maxReached;
  private ItemStack fuel;

  public TileEntityStove() {
    super();
    this.totalFuelTicks = 0;
    this.remainingFuelTicks = 0;
    this.remainingStepTicks = TICKS_PER_STEPS;
    this.temperature = 0;
    this.maxReached = false;
    this.fuel = null;
  }

  @Override
  public void update() {
    if (hasActiveBellows() && this.temperature < MAX_TEMPERATURE) {
      // On consomme s'il y a du carburant disponible et que le précédent a fini de brûler.
      if (!isBurning() && TileEntityFurnace.isItemFuel(this.fuel)) {
        this.remainingFuelTicks = this.totalFuelTicks = TileEntityFurnace.getItemBurnTime(this.fuel);
        decrStackSize(0, 1);
        BlockStove.setState(true, getWorld(), getPos());
      }
    }
    // On éteint si le four n'est plus chaud et ne brûle plus.
    if (!isBurning() && !isHot() && getWorld().getBlockState(getPos()).getBlock() == McfrBlocks.LIT_STOVE) {
      BlockStove.setState(false, getWorld(), getPos());
    }

    if (isBurning() && this.temperature < MAX_TEMPERATURE && !this.maxReached) {
      this.remainingFuelTicks--;
      // Augmentation température.
      if (getRemainingStepTicks() == 0) {
        this.remainingStepTicks = TICKS_PER_STEPS;
        this.temperature++;
        if (this.temperature == MAX_TEMPERATURE)
          this.maxReached = true;
      }
      else
        this.remainingStepTicks--;
    } // Diminution température.
    else if (isHot() || getRemainingStepTicks() != TICKS_PER_STEPS) {
      if (getRemainingStepTicks() == TICKS_PER_STEPS) {
        if (this.temperature > 0)
          this.temperature--;
        if (this.temperature >= 0)
          this.remainingStepTicks = 0;
      }
      else
        this.remainingStepTicks++;
      if (this.maxReached && getTemperature() < MAX_TEMPERATURE - 5)
        this.maxReached = false;
    }

    markDirty();
  }

  public int getTotalFuelTicks() {
    return this.totalFuelTicks;
  }

  public int getRemainingFuelTicks() {
    return this.remainingFuelTicks;
  }

  public float getFuelProgress() {
    return !isBurning() ? 0 : (float) (getTotalFuelTicks() - getRemainingFuelTicks()) / getTotalFuelTicks();
  }

  public int getRemainingStepTicks() {
    return this.remainingStepTicks;
  }

  public float getNextTemperatureProgress() {
    return (float) (TICKS_PER_STEPS - getRemainingStepTicks()) / TICKS_PER_STEPS;
  }

  public int getTemperature() {
    return this.temperature;
  }

  public float getTemperatureProgress() {
    return (float) getTemperature() / MAX_TEMPERATURE;
  }

  public boolean isBurning() {
    return this.remainingFuelTicks > 0 && hasActiveBellows();
  }

  public boolean isHot() {
    return this.temperature > 0;
  }

  private boolean hasActiveBellows() {
    // @f0
    BlockPos[] pos = {getPos().east(), getPos().east().south(), getPos().south(), getPos().west().south(), getPos().west(), getPos().west().north(), getPos().north(), getPos().east().north()};
    // @f1

    for (BlockPos p : pos)
      if (isActiveBellow(p))
        return true;

    return false;
  }

  /**
   * Vérifie si il y a un soufflet activé à la position indiquée.
   * 
   * @param pos la position à vérifier
   * @return Présence d'un soufflet activé.
   */
  private boolean isActiveBellow(BlockPos pos) {
    if (this.worldObj.getBlockState(pos).getBlock() instanceof BlockBellows) {
      return ((TileEntityBellows) this.worldObj.getTileEntity(pos)).isPowered();
    }
    return false;
  }

  @Override
  public String getName() {
    return "container.stove";
  }

  @Override
  public boolean hasCustomName() {
    return false;
  }

  @Override
  public int getSizeInventory() {
    return 1;
  }

  @Override
  public ItemStack getStackInSlot(int index) {
    return index == 0 ? this.fuel : null;
  }

  @Override
  public ItemStack decrStackSize(int index, int count) {
    if (index == 0 && this.fuel != null) {
      ItemStack stack = this.fuel.splitStack(count);

      if (this.fuel.stackSize == 0)
        this.fuel = null;

      return stack;
    }
    return null;
  }

  @Override
  public ItemStack removeStackFromSlot(int index) {
    if (index == 0) {
      ItemStack old = this.fuel;
      clear();
      return old;
    }
    return null;
  }

  @Override
  public void setInventorySlotContents(int index, ItemStack stack) {
    if (isItemValidForSlot(index, stack))
      this.fuel = stack;
  }

  @Override
  public int getInventoryStackLimit() {
    return 64;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player) {
    return true;
  }

  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    return index == 0 && (stack == null || TileEntityFurnace.isItemFuel(stack));
  }

  @Override
  public int getFieldCount() {
    return 0;
  }

  @Override
  public void clear() {
    this.fuel = null;
  }

  @Override
  public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
    return new ContainerStove(playerInventory, this);
  }

  @Override
  public String getGuiID() {
    return Constants.MOD_ID + ":stove";
  }

  @Override
  public void openInventory(EntityPlayer player) {}

  @Override
  public void closeInventory(EntityPlayer player) {}

  @Override
  public int getField(int id) {
    return -1;
  }

  @Override
  public void setField(int id, int value) {}

  @Override
  public int[] getSlotsForFace(EnumFacing side) {
    return new int[]{0};
  }

  @Override
  public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
    return false;
  }

  @Override
  public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
    return false;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);

    this.totalFuelTicks = compound.getInteger("TotalFuelTicks");
    this.remainingFuelTicks = compound.getInteger("RemainingFuelTicks");
    this.remainingStepTicks = compound.getInteger("RemainingStepTicks");
    this.temperature = compound.getInteger("Temperature");
    this.fuel = compound.hasKey("Fuel", NBTUtils.TAG_COMPOUND) ? ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Fuel")) : null;
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);

    compound.setInteger("TotalFuelTicks", getTotalFuelTicks());
    compound.setInteger("RemainingFuelTicks", getRemainingFuelTicks());
    compound.setInteger("RemainingStepTicks", getRemainingStepTicks());
    compound.setInteger("Temperature", getTemperature());
    if (this.fuel != null)
      compound.setTag("Fuel", this.fuel.writeToNBT(new NBTTagCompound()));

    return compound;
  }

  @Override
  public SPacketUpdateTileEntity getUpdatePacket() {
    return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
  }

  @Override
  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
    readFromNBT(pkt.getNbtCompound());
  }

  @Override
  public NBTTagCompound getUpdateTag() {
    return writeToNBT(new NBTTagCompound());
  }
}
