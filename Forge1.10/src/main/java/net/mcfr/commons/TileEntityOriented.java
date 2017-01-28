package net.mcfr.commons;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public abstract class TileEntityOriented extends TileEntity {
  private EnumFacing facing;

  public TileEntityOriented() {
    this(EnumFacing.NORTH);
  }

  public TileEntityOriented(EnumFacing facing) {
    this.facing = facing;
  }

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
