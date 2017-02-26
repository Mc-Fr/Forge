package net.mcfr.decoration.furniture.tile_entities;

import net.mcfr.commons.TileEntityOriented;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

/**
 * Tile entity du fauteuil.
 * 
 * @author Mc-Fr
 */
public class TileEntityArmChair extends TileEntityOriented {
  /** Le type de fauteuil (ex. : stone, wood...) */
  private String type;

  /**
   * Crée une tile entity orientée vers le nord et sans type.
   */
  public TileEntityArmChair() {
    this(EnumFacing.NORTH, "");
  }

  /**
   * Crée une tile entity d'orientation et de type donnés.
   */
  public TileEntityArmChair(EnumFacing facing, String type) {
    super(facing);
    this.type = type;
  }

  /**
   * @return le type de fauteuil
   */
  public String getType() {
    return this.type;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.type = compound.getString("Type");
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setString("Type", getType());
    return compound;
  }
}
