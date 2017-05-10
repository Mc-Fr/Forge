package net.mcfr.decoration.lighting.tile_entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Tile entity des lustres.
 *
 * @author Mc-Fr
 */
public class TileEntityChandelier extends TileEntity {
  private boolean isLarge;

  /**
   * Crée une tile entity pour un petit lustre
   */
  public TileEntityChandelier() {
    this(false);
  }

  /**
   * Crée une tile entity pour un lustre de taille indiquée.
   * 
   * @param isLarge le lustre est-il large ?
   */
  public TileEntityChandelier(boolean isLarge) {
    this.isLarge = isLarge;
  }

  /**
   * @return true si le lustre associé est large
   */
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
