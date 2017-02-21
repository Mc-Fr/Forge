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

/**
 * Tile entity de la vitrine.
 *
 * @author Mc-Fr
 */
public class TileEntityShowcase extends TileEntityOriented implements IInventory, ClickableTileEntity {
  /** Item affiché */
  private ItemStack shownItem;
  /** Vitrines adjacentes vérifiées ou non */
  private boolean adjacentShowcaseChecked;
  private TileEntityShowcase adjacentShowcaseNorth;
  private TileEntityShowcase adjacentShowcaseSouth;
  private TileEntityShowcase adjacentShowcaseEast;
  private TileEntityShowcase adjacentShowcaseWest;
  /** Temps du dernier clic */
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
   * Vérifie s'il y a des vitrines simples à côté pour savoir si celle-ci est double ou non.
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

  /**
   * Cherche la vitrine du côté indiqué.
   * 
   * @param side le côté
   * @return la vitrine ou null s'il n'y en a pas
   */
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

  /**
   * Indique si le bloc à une certaines position est une vitrine.
   * 
   * @param pos la position
   * @return vrai si le bloc est une vitrine ; faux sinon
   */
  private boolean isShowcaseAt(BlockPos pos) {
    return getWorld().getBlockState(pos).getBlock() instanceof BlockShowcase;
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

    if (hasItem(0)) {
      NBTTagCompound c = new NBTTagCompound();
      this.shownItem.writeToNBT(c);
      compound.setTag("Item", c);
    }
    return compound;
  }

  @Override
  public ItemStack getItem(int slot) {
    return this.shownItem;
  }

  @Override
  public boolean setItem(ItemStack stack, int slot) {
    if (stack == null || (itemIsValid(stack) && !hasItem(0))) {
      this.shownItem = stack != null ? stack.copy() : null;
      this.lastClickedTime = System.currentTimeMillis();
      markDirty();

      return true;
    }

    return false;
  }

  @Override
  public boolean hasItem(int slot) {
    return getItem(0) != null;
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

  /**
   * Indique si l'item donné est valide pour la vitrine.
   * 
   * @param stack l'item
   * @return vrai si l'item est accepté ; faux sinon
   */
  public static boolean itemIsValid(ItemStack stack) {
    return ItemsLists.getWeapons().contains(stack.getItem()) || ItemsLists.getTools().contains(stack.getItem());
  }
}
