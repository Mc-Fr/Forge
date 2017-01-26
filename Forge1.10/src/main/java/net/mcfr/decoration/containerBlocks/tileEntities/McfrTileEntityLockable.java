package net.mcfr.decoration.containerBlocks.tileEntities;

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

public abstract class McfrTileEntityLockable extends TileEntityLockable {
  private String name;
  private ItemStack[] stacks;
  private int stackSize;
  private int numberPlayerUsing;
  private boolean playSounds;

  public McfrTileEntityLockable(String name, int size, int stackSize, boolean playSounds) {
    this.name = name;
    this.stacks = new ItemStack[size];
    this.stackSize = stackSize;
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

  @Override
  public ItemStack getStackInSlot(int index) {
    return this.stacks[index];
  }

  @Override
  public ItemStack decrStackSize(int index, int count) {
    if (this.stacks[index] != null) {
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
    if (this.stacks[index] != null) {
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
    return this.stackSize;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player) {
    return this.worldObj.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64;
  }

  @Override
  public void openInventory(EntityPlayer player) {
    if (!player.isSpectator()) {
      if (this.numberPlayerUsing < 0)
        this.numberPlayerUsing = 0;
      this.numberPlayerUsing++;

      if (this.playSounds && this.numberPlayerUsing > 0) {
        this.worldObj.playSound(player, new BlockPos(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5), SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 1);
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
        this.worldObj.playSound(player, new BlockPos(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5), SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 1.1F);
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
