package net.mcfr.decoration.misc.tile_entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntitySupport extends TileEntity {
  private EnumFacing facing;
  private boolean isLong;

  public TileEntitySupport() {
    this(EnumFacing.NORTH, false);
  }

  public TileEntitySupport(EnumFacing facing, boolean isLong) {
    this.facing = facing;
    this.isLong = isLong;
  }

  public EnumFacing getFacing() {
    return this.facing;
  }

  public boolean isLong() {
    return this.isLong;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);

    this.facing = EnumFacing.getHorizontal(compound.getInteger("Facing"));
    this.isLong = compound.getBoolean("IsLong");
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);

    compound.setInteger("Facing", getFacing().getHorizontalIndex());
    compound.setBoolean("IsLong", isLong());

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
