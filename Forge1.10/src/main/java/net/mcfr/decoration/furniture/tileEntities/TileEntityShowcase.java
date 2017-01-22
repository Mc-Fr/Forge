package net.mcfr.decoration.furniture.tileEntities;

import net.mcfr.utils.ItemsLists;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityShowcase extends TileEntity {
  private ItemStack shownItem;

  public TileEntityShowcase() {
    this.shownItem = null;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.shownItem = compound.hasKey("Item") ? ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Item")) : null;
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);

    if (hasItem()) {
      NBTTagCompound c = new NBTTagCompound();
      this.shownItem.writeToNBT(c);
      compound.setTag("Item", c);
    }
    return compound;
  }

  @Override
  public SPacketUpdateTileEntity getUpdatePacket() {
    NBTTagCompound c = new NBTTagCompound();
    writeToNBT(c);
    SPacketUpdateTileEntity packet = new SPacketUpdateTileEntity(getPos(), 0, c);

    return packet;
  }

  @Override
  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
    readFromNBT(pkt.getNbtCompound());
  }

  public ItemStack getItem() {
    return this.shownItem;
  }

  public boolean setItem(ItemStack stack) {
    if (stack == null || (itemIsValid(stack) && !hasItem())) {
      this.shownItem = stack != null ? stack.copy() : null;
      markDirty();

      return true;
    }

    return false;
  }

  public boolean hasItem() {
    return getItem() != null;
  }

  public static boolean itemIsValid(ItemStack stack) {
    return ItemsLists.getWeapons().contains(stack.getItem()) || ItemsLists.getTools().contains(stack.getItem());
  }
}
