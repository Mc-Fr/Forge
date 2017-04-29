package net.mcfr.decoration.misc.tile_entities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Cette tile entity est utilisée pour le rendu des tapis.
 *
 * @author Mc-Fr
 */
public class TileEntityCarpet extends TileEntity {
  /** Metadata du tapis accocié. */
  private int metadata;

  /**
   * Crée une tile entity avec un metadata de 0.
   */
  public TileEntityCarpet() {
    this(0);
  }

  /**
   * Crée une tile entity de metadata donné.
   * 
   * @param metadata le metadata
   */
  public TileEntityCarpet(int metadata) {
    super();
    this.metadata = metadata;
  }

  /**
   * @return le metadata
   */
  public int getMetadata() {
    return this.metadata;
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setInteger("Metadata", getMetadata());
    return compound;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.metadata = compound.getInteger("Metadata");
  }
}
