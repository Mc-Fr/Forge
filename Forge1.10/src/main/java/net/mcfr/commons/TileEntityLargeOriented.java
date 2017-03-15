package net.mcfr.commons;

import net.mcfr.decoration.container_blocks.tile_entities.TileEntityLarge;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;

public abstract class TileEntityLargeOriented extends TileEntityLarge {
  /** L'orientation */
  private EnumFacing facing;

  public TileEntityLargeOriented(String name) {
    this(EnumFacing.NORTH, name);
  }

  public TileEntityLargeOriented(EnumFacing facing, String name) {
    super(name);
    this.facing = facing;
  }

  /**
   * @return l'orientation
   */
  public EnumFacing getFacing() {
    return this.facing;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.facing = EnumFacing.getHorizontal(compound.getInteger("Facing"));
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setInteger("Facing", getFacing().getHorizontalIndex());
    return compound;
  }

  @Override
  public final SPacketUpdateTileEntity getUpdatePacket() {
    return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
  }

  @Override
  public final void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
    readFromNBT(pkt.getNbtCompound());
  }

  @Override
  public final NBTTagCompound getUpdateTag() {
    return writeToNBT(new NBTTagCompound());
  }
}
