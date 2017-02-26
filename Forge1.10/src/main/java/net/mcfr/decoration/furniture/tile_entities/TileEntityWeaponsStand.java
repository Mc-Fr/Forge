package net.mcfr.decoration.furniture.tile_entities;

import java.util.Arrays;

import net.mcfr.commons.TileEntityOriented;
import net.mcfr.utils.ItemsLists;
import net.mcfr.utils.NBTUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;

/**
 * Tile entity du stand d'armes/outils.
 *
 * @author Mc-Fr
 */
public class TileEntityWeaponsStand extends TileEntityOriented implements ClickableTileEntity {
  /** Taille de l'inventaire */
  public static final int INVENTORY_SIZE = 2;

  /** Invetaire */
  private ItemStack[] stacks;
  /** Temps du dernier clic */
  private long lastClickedTime;

  public TileEntityWeaponsStand() {
    this(EnumFacing.NORTH);
  }

  public TileEntityWeaponsStand(EnumFacing facing) {
    super(facing);
    this.stacks = new ItemStack[INVENTORY_SIZE];
    this.lastClickedTime = 0;
  }

  @Override
  public long getLastClickTime() {
    return this.lastClickedTime;
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
        setItem(ItemStack.loadItemStackFromNBT(item), slotId);
      }
    }
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    NBTTagList list = new NBTTagList();

    for (int i = 0; i < this.stacks.length; ++i) {
      if (hasItem(i)) {
        NBTTagCompound slot = new NBTTagCompound();
        NBTTagCompound item = new NBTTagCompound();

        slot.setByte("Slot", (byte) i);
        getItem(i).writeToNBT(item);
        slot.setTag("Item", item);
        list.appendTag(slot);
      }
    }
    compound.setTag("Items", list);
    return compound;
  }

  @Override
  public ItemStack getItem(int slot) {
    return this.stacks[slot];
  }

  @Override
  public boolean setItem(ItemStack stack, int slot) {
    if (stack == null || (itemIsValid(stack) && !hasItem(slot))) {
      this.stacks[slot] = stack != null ? stack.copy() : null;
      markDirty();

      return true;
    }

    return false;
  }

  @Override
  public boolean hasItem(int slot) {
    return slot >= 0 && slot < this.stacks.length && this.stacks[slot] != null;
  }

  /**
   * Indique si l'item donné est valide pour le stand.
   * 
   * @param stack l'item
   * @return vrai si l'item est accepté ; faux sinon
   */
  public static boolean itemIsValid(ItemStack stack) {
    return ItemsLists.getWeapons().contains(stack.getItem()) || ItemsLists.getTools().contains(stack.getItem());
  }
}
