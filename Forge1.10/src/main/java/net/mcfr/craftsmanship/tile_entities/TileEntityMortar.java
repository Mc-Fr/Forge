package net.mcfr.craftsmanship.tile_entities;

import net.mcfr.craftsmanship.BlockMortar;
import net.minecraft.util.EnumFacing;

/**
 * Tile entity du mortier
 * 
 * @author Mc-Fr
 */
public class TileEntityMortar extends TileEntityRack {
  /**
   * Crée une tile entity orientée vers le nord.<br/>
   * <i>Constructeur requis par Forge</i>.
   */
  public TileEntityMortar() {
    this(EnumFacing.NORTH);
  }

  public TileEntityMortar(EnumFacing facing) {
    super("mortar", facing, BlockMortar.class);
  }
}
