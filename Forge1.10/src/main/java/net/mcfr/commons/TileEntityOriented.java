package net.mcfr.commons;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

/**
 * Classe fournissant une gestion de l'orientation aux tile entities.
 * 
 * @author Mc-Fr
 */
public abstract class TileEntityOriented extends TileEntity {
  /** L'orientation */
  private EnumFacing facing;

  /**
   * Crée une tile entity orientée vers le nord.
   */
  public TileEntityOriented() {
    this(EnumFacing.NORTH);
  }

  /**
   * Crée une tile entity avec l'orientation donnée.
   */
  public TileEntityOriented(EnumFacing facing) {
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
