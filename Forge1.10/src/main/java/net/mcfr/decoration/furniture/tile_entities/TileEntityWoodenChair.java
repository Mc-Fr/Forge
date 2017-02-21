package net.mcfr.decoration.furniture.tile_entities;

import net.mcfr.commons.TileEntityOriented;
import net.minecraft.block.BlockPlanks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

/**
 * Tile entity des fauteuils en bois.
 *
 * @author Mc-Fr
 */
public class TileEntityWoodenChair extends TileEntityOriented {
  /** Le type de bois */
  private BlockPlanks.EnumType type;

  /**
   * Crée un fauteuil en chêne orienté vers le nord.
   */
  public TileEntityWoodenChair() {
    this(EnumFacing.NORTH, BlockPlanks.EnumType.OAK);
  }

  /**
   * Crée un fauteuil.
   * 
   * @param facing l'orientation
   * @param type le type de bois
   */
  public TileEntityWoodenChair(EnumFacing facing, BlockPlanks.EnumType type) {
    super(facing);
    this.type = type;
  }

  /**
   * @return le type de bois
   */
  public BlockPlanks.EnumType getType() {
    return this.type;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.type = BlockPlanks.EnumType.byMetadata(compound.getInteger("WoodType"));
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setInteger("WoodType", getType().getMetadata());
    return compound;
  }
}
