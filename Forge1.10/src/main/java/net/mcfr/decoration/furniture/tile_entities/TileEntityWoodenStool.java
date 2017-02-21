package net.mcfr.decoration.furniture.tile_entities;

import net.minecraft.block.BlockPlanks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

/**
 * Tile entity des tabourets en bois.
 *
 * @author Mc-Fr
 */
public class TileEntityWoodenStool extends TileEntityWoodenChair {
  /** Le tabouret est grand ou non */
  private boolean isTall;

  /**
   * Crée un petit tabouret en chêne orienté vers le nord.
   */
  public TileEntityWoodenStool() {
    this(EnumFacing.NORTH, BlockPlanks.EnumType.OAK, false);
  }

  /**
   * Crée un tabouret en bois
   * 
   * @param facing l'orientation
   * @param type le type de bois
   * @param isTall est-il grand ou non
   */
  public TileEntityWoodenStool(EnumFacing facing, BlockPlanks.EnumType type, boolean isTall) {
    super(facing, type);
    this.isTall = isTall;
  }

  /**
   * @return vrai si ce tabouret est grand ; faux sinon
   */
  public boolean isTall() {
    return this.isTall;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.isTall = compound.getBoolean("IsTall");
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setBoolean("IsTall", isTall());
    return compound;
  }
}
