package net.mcfr.decoration.lighting.tileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityChandelier extends TileEntity {
  private boolean isLarge;

  public TileEntityChandelier() {
    this(false);
  }

  public TileEntityChandelier(boolean isLarge) {
    super();
    this.isLarge = isLarge;
  }

  public boolean isLarge() {
    return this.isLarge;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.isLarge = compound.getBoolean("IsLarge");
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setBoolean("IsLarge", isLarge());
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
