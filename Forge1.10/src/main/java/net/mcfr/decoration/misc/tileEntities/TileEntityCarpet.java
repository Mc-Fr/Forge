package net.mcfr.decoration.misc.tileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Cette tile entity est utilisée pour faire le rendu des tapis.
 *
 * @author Mc-Fr
 */
public class TileEntityCarpet extends TileEntity {
  private int metadata;

  public TileEntityCarpet() {
    this(0);
  }

  public TileEntityCarpet(int metadata) {
    super();
    this.metadata = metadata;
  }

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
