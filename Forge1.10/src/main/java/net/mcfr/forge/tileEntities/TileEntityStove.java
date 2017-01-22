package net.mcfr.forge.tileEntities;

import net.mcfr.Constants;
import net.mcfr.McfrBlocks;
import net.mcfr.forge.BlockBellows;
import net.mcfr.forge.guis.ContainerStove;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityStove extends TileEntityLockable implements ITickable, ISidedInventory {
  /** La température maximale. */
  public static final int MAX_TEMPERATURE = 500;
  /** Le nombre de tics avant de changer la température d'un degré. */
  private static final int TICKS_PER_STEPS = 2 * 20;

  private int lastTick;
  /** Le nombre de tics de l'item courant. */
  private int totalFuelTicks;
  /** Le nombre de tics restant à consommer. */
  private int remainingFuelTicks;
  private int temperature;
  private ItemStack fuel;

  public TileEntityStove() {
    setFuel(null);
    this.temperature = 0;
  }

  // FIXME : le carburant est consommé trop vite.
  @Override
  public void update() {
    if (this.fuel != null && hasActiveBellows() && !isLit()) {
      int meta = McfrBlocks.STOVE.getMetaFromState(getWorld().getBlockState(getPos()));
      getWorld().setBlockState(getPos(), McfrBlocks.LIT_STOVE.getStateFromMeta(meta));
    }
    if (this.temperature == 0 && isLit()) {
      int meta = McfrBlocks.LIT_STOVE.getMetaFromState(getWorld().getBlockState(getPos()));
      getWorld().setBlockState(getPos(), McfrBlocks.STOVE.getStateFromMeta(meta));
    }

    this.lastTick = (this.lastTick + 1) % TICKS_PER_STEPS;
    if (hasActiveBellows() && this.lastTick == 0 && this.fuel != null) {
      powerStove();
    }
    if (hasActiveBellows() && isBurning()) {
      this.remainingFuelTicks--;
      if (this.lastTick == 0 && this.temperature < MAX_TEMPERATURE) this.temperature++;
    }
    else {
      if (this.lastTick == 0 && this.temperature > 0) this.temperature--;
    }
  }

  /**
   * Consomme un item de carburant.
   */
  private void powerStove() {
    if (this.fuel != null) {
      this.fuel.stackSize--;
      this.lastTick = 0;
      this.remainingFuelTicks = this.totalFuelTicks = TileEntityFurnace.getItemBurnTime(this.fuel);
      if (this.fuel.stackSize == 0) this.fuel = null;
    }
  }

  public int getTemperature() {
    return this.temperature;
  }

  /**
   * Indique s'il reste des tics à consommer.
   */
  public boolean isBurning() {
    return this.remainingFuelTicks > 0;
  }

  /**
   * @return le stack présent dans l'emplacement du carburant ou null s'il est vide
   */
  public ItemStack getFuel() {
    return this.fuel != null ? this.fuel.copy() : null;
  }

  public void setFuel(ItemStack fuel) {
    if (fuel == null) {
      this.fuel = null;
    }
    else if (TileEntityFurnace.isItemFuel(fuel)) {
      System.out.println(TileEntityFurnace.getItemBurnTime(fuel));
      this.fuel = fuel.stackSize > 0 ? fuel : null;
      this.lastTick = 0;
      this.remainingFuelTicks = this.totalFuelTicks = TileEntityFurnace.getItemBurnTime(fuel);
    }
  }

  public boolean isLit() {
    return this.temperature > 0;
  }

  private boolean hasActiveBellows() {
    BlockPos[] pos = {getPos().east(), getPos().east().south(), getPos().south(), getPos().west().south(), getPos().west(), //
      getPos().west().north(), getPos().north(), getPos().east().north()};

    for (BlockPos p : pos)
      if (hasActiveBellow(p)) return true;

    return false;
  }

  /**
   * Vérifie si il y a un soufflet activé à la position indiquée.
   * 
   * @param pos la position à vérifier
   * @return Présence d'un soufflet activé.
   */
  private boolean hasActiveBellow(BlockPos pos) {
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
    return index == 0 ? getFuel() : null;
  }

  @Override
  public ItemStack decrStackSize(int index, int count) {
    if (index == 0) {
      ItemStack stack = this.fuel.splitStack(count);
      if (this.fuel.stackSize == 0) this.fuel = null;

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
    if (isItemValidForSlot(index, stack)) setFuel(stack);
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
    return index == 0 && TileEntityFurnace.isItemFuel(stack);
  }

  /**
   * Retourne les champs suivants pour les valeurs suivantes :
   * <ul>
   * <li>0 -> température</li>
   * <li>1 -> tics totaux pour le carburant actuel</li>
   * <li>2 -> tics restants pour le carburant actuel</li>
   * </ul>
   * 
   * @return la valeur d'un champ
   */
  @Override
  public int getField(int id) {
    switch (id) {
      case 0:
        return this.temperature;
      case 1:
        return this.totalFuelTicks;
      case 2:
        return this.remainingFuelTicks;
    }

    return 0;
  }

  /**
   * Modifie les champs suivants pour les valeurs suivantes :
   * <ul>
   * <li>0 -> température</li>
   * <li>1 -> tics totaux pour le carburant actuel</li>
   * <li>2 -> tics restants pour le carburant actuel</li>
   * </ul>
   */
  @Override
  public void setField(int id, int value) {
    switch (id) {
      case 0:
        this.temperature = value;
        break;
      case 1:
        this.totalFuelTicks = value;
        break;
      case 2:
        this.remainingFuelTicks = value;
        break;
    }
  }

  @Override
  public int getFieldCount() {
    return 3;
  }

  @Override
  public void clear() {
    setFuel(null);
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
  public int[] getSlotsForFace(EnumFacing side) {
    return new int[]{0};
  }

  @Override
  public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
    return index == 0 && TileEntityFurnace.isItemFuel(itemStackIn);
  }

  @Override
  public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
    return true;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);

    this.lastTick = compound.getInteger("LastTick");
    this.temperature = compound.getInteger("Temperature");
    this.totalFuelTicks = compound.getInteger("TotalTicks");
    this.remainingFuelTicks = compound.getInteger("RemainingTicks");
    this.fuel = compound.hasKey("Item", 10) ? ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Item")) : null;
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);

    compound.setInteger("LastTick", this.lastTick);
    compound.setInteger("Temperature", getTemperature());
    compound.setInteger("TotalTicks", this.totalFuelTicks);
    compound.setInteger("RemainingTicks", this.remainingFuelTicks);
    if (this.fuel != null) {
      NBTTagCompound item = new NBTTagCompound();
      this.fuel.writeToNBT(item);
      compound.setTag("Item", item);
    }

    return compound;
  }
}
