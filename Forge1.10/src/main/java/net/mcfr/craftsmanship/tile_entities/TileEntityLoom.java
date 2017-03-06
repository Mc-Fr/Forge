package net.mcfr.craftsmanship.tile_entities;

import net.mcfr.craftsmanship.BlockLoom;
import net.minecraft.util.EnumFacing;

/**
 * Tile entity du métier à tisser.
 *
 * @author Mc-Fr
 */
public class TileEntityLoom extends TileEntityRack {
  /**
   * Crée une tile entity orientée vers le nord.<br/>
   * <i>Constructeur requis par Forge</i>.
   */
  public TileEntityLoom() {
    this(EnumFacing.NORTH);
  }

  public TileEntityLoom(EnumFacing facing) {
    super("loom", facing, BlockLoom.class);
  }
}
