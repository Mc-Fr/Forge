package net.mcfr.decoration.furniture.tile_entities;

import net.mcfr.commons.TileEntityOriented;
import net.minecraft.util.EnumFacing;

/**
 * Tile entity des bancs en bois.
 *
 * @author Mc-Fr
 */
public class TileEntityWoodenBench extends TileEntityOriented {
  /**
   * Crée un banc orienté vers le nord.
   */
  public TileEntityWoodenBench() {
    super();
  }

  /**
   * Crée un banc.
   * 
   * @param facing l'orientation
   */
  public TileEntityWoodenBench(EnumFacing facing) {
    super(facing);
  }
}
