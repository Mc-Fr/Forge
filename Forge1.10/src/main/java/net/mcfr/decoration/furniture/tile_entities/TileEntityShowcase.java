package net.mcfr.decoration.furniture.tile_entities;

import net.mcfr.commons.TileEntityOriented;
import net.mcfr.decoration.furniture.BlockShowcase;
import net.mcfr.utils.ItemsLists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntityShowcase extends TileEntityOriented implements IInventory, ClickableTileEntity {
  private ItemStack shownItem;
  private boolean adjacentShowcaseChecked;
  private TileEntityShowcase adjacentShowcaseNorth;
  private TileEntityShowcase adjacentShowcaseSouth;
  private TileEntityShowcase adjacentShowcaseEast;
  private TileEntityShowcase adjacentShowcaseWest;
  private long lastClickedTime;

  public TileEntityShowcase() {
    this(EnumFacing.NORTH);
  }

  public TileEntityShowcase(EnumFacing facing) {
    super(facing);
    this.shownItem = null;
    this.adjacentShowcaseChecked = false;
    this.adjacentShowcaseEast = null;
    this.adjacentShowcaseWest = null;
    this.adjacentShowcaseSouth = null;
    this.adjacentShowcaseNorth = null;
    this.lastClickedTime = 0;
  }

  @Override
  public long getLastClickTime() {
    return this.lastClickedTime;
  }

  @Override
  public void updateContainingBlockInfo() {
    super.updateContainingBlockInfo();
    this.adjacentShowcaseChecked = false;
  }

  @SuppressWarnings("incomplete-switch")
  private void setNeighbor(TileEntityShowcase showcaseTe, EnumFacing side) {
    if (showcaseTe.isInvalid()) {
      this.adjacentShowcaseChecked = false;
    }
    else if (this.adjacentShowcaseChecked) {
      switch (side) {
        case NORTH:
          if (this.adjacentShowcaseNorth != showcaseTe)
            this.adjacentShowcaseChecked = false;
          break;
        case SOUTH:
          if (this.adjacentShowcaseSouth != showcaseTe)
            this.adjacentShowcaseChecked = false;
          break;
        case EAST:
          if (this.adjacentShowcaseEast != showcaseTe)
            this.adjacentShowcaseChecked = false;
          break;
        case WEST:
          if (this.adjacentShowcaseWest != showcaseTe)
            this.adjacentShowcaseChecked = false;
          break;
      }
    }
  }

  /**
   * Performs the check for adjacent chests to determine if this chest is double or not.
   */
  public void checkForAdjacentChests() {
    if (!this.adjacentShowcaseChecked) {
      this.adjacentShowcaseChecked = true;
      this.adjacentShowcaseNorth = getAdjacentShowcase(EnumFacing.NORTH);
      this.adjacentShowcaseSouth = getAdjacentShowcase(EnumFacing.SOUTH);
      this.adjacentShowcaseEast = getAdjacentShowcase(EnumFacing.EAST);
      this.adjacentShowcaseWest = getAdjacentShowcase(EnumFacing.WEST);
    }
  }

  protected TileEntityShowcase getAdjacentShowcase(EnumFacing side) {
    BlockPos blockpos = getPos().offset(side);

    if (isShowcaseAt(blockpos)) {
      TileEntity te = getWorld().getTileEntity(blockpos);

      if (te instanceof TileEntityShowcase) {
        TileEntityShowcase t = (TileEntityShowcase) te;
        t.setNeighbor(this, side.getOpposite());
        return t;
      }
    }

    return null;
  }

  private boolean isShowcaseAt(BlockPos posIn) {
    return getWorld().getBlockState(posIn).getBlock() instanceof BlockShowcase;
  }

  @Override
  public void invalidate() {
    super.invalidate();
    updateContainingBlockInfo();
    checkForAdjacentChests();
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

  public ItemStack getItem() {
    return this.shownItem;
  }

  public boolean setItem(ItemStack stack) {
    if (stack == null || (itemIsValid(stack) && !hasItem())) {
      this.shownItem = stack != null ? stack.copy() : null;
      this.lastClickedTime = System.currentTimeMillis();
      markDirty();

      return true;
    }

    return false;
  }

  public boolean hasItem() {
    return getItem() != null;
  }

  @Override
  public String getName() {
    return "container.showcase";
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
    return index == 0 ? this.shownItem : null;
  }

  @Override
  public ItemStack decrStackSize(int index, int count) {
    if (index == 0) {
      if (this.shownItem != null) {
        this.shownItem.stackSize -= count;
        if (this.shownItem.stackSize == 0)
          this.shownItem = null;
      }

      return this.shownItem;
    }
    return null;
  }

  @Override
  public ItemStack removeStackFromSlot(int index) {
    if (index == 0) {
      ItemStack stack = this.shownItem;
      this.shownItem = null;
      return stack;
    }
    return null;
  }

  @Override
  public void setInventorySlotContents(int index, ItemStack stack) {
    if (index == 0)
      this.shownItem = stack;
  }

  @Override
  public int getInventoryStackLimit() {
    return 1;
  }

  @Override
  public boolean isUseableByPlayer(EntityPlayer player) {
    return false;
  }

  @Override
  public void openInventory(EntityPlayer player) {}

  @Override
  public void closeInventory(EntityPlayer player) {}

  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    return index == 0 && itemIsValid(stack);
  }

  @Override
  public int getField(int id) {
    return -1;
  }

  @Override
  public void setField(int id, int value) {}

  @Override
  public int getFieldCount() {
    return 0;
  }

  @Override
  public void clear() {
    this.shownItem = null;
  }

  public static boolean itemIsValid(ItemStack stack) {
    return ItemsLists.getWeapons().contains(stack.getItem()) || ItemsLists.getTools().contains(stack.getItem());
  }
}
