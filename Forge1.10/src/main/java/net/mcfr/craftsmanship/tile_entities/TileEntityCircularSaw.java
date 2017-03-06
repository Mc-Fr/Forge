package net.mcfr.craftsmanship.tile_entities;

import net.mcfr.craftsmanship.BlockCircularSaw;
import net.minecraft.util.EnumFacing;

/**
 * Tile entity de la scie circulaire.
 *
 * @author Mc-Fr
 */
public class TileEntityCircularSaw extends TileEntityRack {
  /**
   * Crée une tile entity orientée vers le nord.<br/>
   * <i>Constructeur requis par Forge</i>.
   */
  public TileEntityCircularSaw() {
    this(EnumFacing.NORTH);
  }

  public TileEntityCircularSaw(EnumFacing facing) {
    super("circular_saw", facing, BlockCircularSaw.class);
  }
}
