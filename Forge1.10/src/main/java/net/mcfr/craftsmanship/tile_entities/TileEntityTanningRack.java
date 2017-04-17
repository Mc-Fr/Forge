package net.mcfr.craftsmanship.tile_entities;

import net.mcfr.craftsmanship.BlockTanningRack;
import net.minecraft.util.EnumFacing;

/**
 * Tile entity de l'atelier de tannage.
 *
 * @author Mc-Fr
 */
public class TileEntityTanningRack extends TileEntityRack {
  /**
   * Crée une tile entity orientée vers le nord.<br/>
   * <i>Constructeur requis par Forge</i>.
   */
  public TileEntityTanningRack() {
    this(EnumFacing.NORTH);
  }

  public TileEntityTanningRack(EnumFacing facing) {
    super("tanning_rack", facing, BlockTanningRack.class);
  }
}
