package net.mcfr.decoration.container_blocks.tile_entities;

import java.util.Date;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * La tile entity associée aux tonneau.
 *
 * @author Mc-Fr
 */
public class TileEntityBarrel extends TileEntity {
  private long creationDate;
  private int durability;

  public TileEntityBarrel() {
    this(new Date().getTime(), -1);
  }

  public TileEntityBarrel(long creationDate, int durability) {
    super();
    this.creationDate = creationDate;
    this.durability = durability;
  }

  public long getCreationDate() {
    return this.creationDate;
  }

  public void setCreationDate(long creationDate) {
    this.creationDate = creationDate;
  }

  public int getDurability() {
    return this.durability;
  }

  public void setDurability(int durability) {
    this.durability = durability;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.creationDate = compound.getLong("creationDate");
    this.durability = compound.getInteger("durability");
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setLong("creationDate", this.creationDate);
    compound.setInteger("durability", this.durability);
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
