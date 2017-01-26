package net.mcfr.decoration.furniture.tileEntities;

import net.mcfr.decoration.furniture.BlockShowcase;
import net.mcfr.utils.ItemsLists;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntityShowcase extends TileEntity implements ClickableTileEntity {
  private static final int COOLDOWN = 20;

  private ItemStack shownItem;
  private boolean adjacentShowcaseChecked;
  private TileEntityShowcase adjacentShowcaseNorth;
  private TileEntityShowcase adjacentShowcaseSouth;
  private TileEntityShowcase adjacentShowcaseEast;
  private TileEntityShowcase adjacentShowcaseWest;

  private long lastClickedTime;

  public TileEntityShowcase() {
    super();
    this.shownItem = null;
    this.adjacentShowcaseChecked = false;
    this.adjacentShowcaseEast = null;
    this.adjacentShowcaseWest = null;
    this.adjacentShowcaseSouth = null;
    this.adjacentShowcaseNorth = null;
    this.lastClickedTime = 0;
  }

  @Override
  public boolean canPlayerInteract() {
    return this.lastClickedTime < System.currentTimeMillis() - COOLDOWN;
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

  @Override
  public SPacketUpdateTileEntity getUpdatePacket() {
    return new SPacketUpdateTileEntity(getPos(), 0, writeToNBT(new NBTTagCompound()));
  }

  @Override
  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
    readFromNBT(pkt.getNbtCompound());
  }

  @Override
  public NBTTagCompound getUpdateTag() {
    return writeToNBT(new NBTTagCompound());
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

  public static boolean itemIsValid(ItemStack stack) {
    return ItemsLists.getWeapons().contains(stack.getItem()) || ItemsLists.getTools().contains(stack.getItem());
  }
}
