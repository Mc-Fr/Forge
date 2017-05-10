package net.mcfr.decoration.container_blocks.tile_entities;

import java.util.Arrays;

import net.mcfr.Constants;
import net.mcfr.utils.NBTUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

/**
 * Tile entity de base pour les conteneurs du mod.
 *
 * @author Mc-Fr
 */
public abstract class McfrTileEntityLockable extends TileEntityLockable {
  /** Le nom */
  private String name;
  /** L'inventaire */
  private ItemStack[] stacks;
  /** La taille maximale des stacks */
  private int maxStackSize;
  /** Le nombre de joueurs utilisant le conteneur */
  private int numberPlayerUsing;
  /** Indique si un son doit être joué à l'ouverture et la fermeture du conteneur */
  private boolean playSounds;

  /**
   * Crée une tile entity.
   * 
   * @param name le nom
   * @param linesNumber le nombre de lignes de l'inventaire
   * @param maxStackSize la taille maximale des stacks
   * @param playSounds indique si un son doit être joué à l'ouverture et la fermeture du conteneur
   */
  public McfrTileEntityLockable(String name, int linesNumber, int maxStackSize, boolean playSounds) {
    this.name = name;
    this.stacks = new ItemStack[linesNumber * 9];
    this.maxStackSize = maxStackSize;
    this.numberPlayerUsing = 0;
    this.playSounds = playSounds;
  }

  @Override
  public String getName() {
    return "container." + this.name;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    NBTTagList list = compound.getTagList("Items", NBTUtils.TAG_COMPOUND);

    Arrays.fill(this.stacks, null);
    for (int i = 0; i < list.tagCount(); ++i) {
      NBTTagCompound slot = list.getCompoundTagAt(i);
      byte slotId = slot.getByte("Slot");
      NBTTagCompound item = slot.getCompoundTag("Item");

      if (slotId >= 0 && slotId < this.stacks.length) {
        this.stacks[slotId] = ItemStack.loadItemStackFromNBT(item);
      }
    }
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    NBTTagList list = new NBTTagList();

    for (int i = 0; i < this.stacks.length; ++i) {
      if (this.stacks[i] != null) {
        NBTTagCompound slot = new NBTTagCompound();
        NBTTagCompound item = new NBTTagCompound();

        slot.setByte("Slot", (byte) i);
        this.stacks[i].writeToNBT(item);
        slot.setTag("Item", item);
        list.appendTag(slot);
      }
    }
    compound.setTag("Items", list);

    return compound;
  }

  @Override
  public boolean hasCustomName() {
    return false;
  }

  @Override
  public int getSizeInventory() {
    return this.stacks.length;
  }

  public int getLinesNumber() {
    return this.stacks.length / 9;
  }

  @Override
  public ItemStack getStackInSlot(int index) {
    return index >= 0 && index < this.stacks.length ? this.stacks[index] : null;
  }

  @Override
  public ItemStack decrStackSize(int index, int count) {
    if (index >= 0 && index < this.stacks.length && this.stacks[index] != null) {
      if (this.stacks[index].stackSize <= count) {
        ItemStack stack = this.stacks[index];

        this.stacks[index] = null;
        markDirty();

        return stack;
      }
      else {
        ItemStack stack = this.stacks[index].splitStack(count);

        if (this.stacks[index].stackSize == 0)
          this.stacks[index] = null;
        markDirty();

        return stack;
      }
    }

    return null;
  }

  @Override
  public ItemStack removeStackFromSlot(int index) {
    if (index >= 0 && index < this.stacks.length && this.stacks[index] != null) {
      ItemStack stack = this.stacks[index];

      this.stacks[index] = null;
      markDirty();

      return stack;
    }

    return null;
  }

  @Override
  public void setInventorySlotContents(int index, ItemStack stack) {
    if (isItemValidForSlot(index, stack)) {
      this.stacks[index] = stack;

      if (stack != null && stack.stackSize > getInventoryStackLimit()) {
        stack.stackSize = getInventoryStackLimit();
      }

      markDirty();
    }
  }

  @Override
  public int getInventoryStackLimit() {
    return this.maxStackSize;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player) {
    return this.worldObj.getTileEntity(this.pos) == this
        && player.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64;
  }

  @Override
  public void openInventory(EntityPlayer player) {
    if (!player.isSpectator()) {
      if (this.numberPlayerUsing < 0)
        this.numberPlayerUsing = 0;
      this.numberPlayerUsing++;

      if (this.playSounds && this.numberPlayerUsing > 0) {
        this.worldObj.playSound(player, new BlockPos(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5),
            SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5f, this.worldObj.rand.nextFloat() * 0.1f + 1);
      }

      this.worldObj.addBlockEvent(this.pos, getBlockType(), 1, this.numberPlayerUsing);
      this.worldObj.notifyNeighborsOfStateChange(this.pos, getBlockType());
      this.worldObj.notifyNeighborsOfStateChange(this.pos.down(), getBlockType());
    }
  }

  @Override
  public void closeInventory(EntityPlayer player) {
    if (!player.isSpectator()) {
      this.numberPlayerUsing--;

      if (this.playSounds) {
        this.worldObj.playSound(player, new BlockPos(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5),
            SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5f, this.worldObj.rand.nextFloat() * 0.1f + 1.1f);
      }

      this.worldObj.addBlockEvent(this.pos, getBlockType(), 1, this.numberPlayerUsing);
      this.worldObj.notifyNeighborsOfStateChange(this.pos, getBlockType());
      this.worldObj.notifyNeighborsOfStateChange(this.pos.down(), getBlockType());
    }
  }

  @Override
  public int getField(int id) {
    return 0;
  }

  @Override
  public void setField(int id, int value) {}

  @Override
  public int getFieldCount() {
    return 0;
  }

  @Override
  public void clear() {
    for (int i = 0; i < this.stacks.length; i++) {
      this.stacks[i] = null;
    }
  }

  @Override
  public String getGuiID() {
    return Constants.MOD_ID + ":" + this.name;
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
