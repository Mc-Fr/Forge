package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.BlockPallet;
import net.mcfr.decoration.container_blocks.guis.ContainerRestrictable;

/**
 * Tile entity de la palette.
 *
 * @author Mc-Fr
 */
public class TileEntityPallet extends TileEntityRestrictable {
  public TileEntityPallet() {
    super("pallet", ContainerRestrictable.REDUCED_LINES_NB, 64, false, BlockPallet.class, ContainerRestrictable.class);
  }
}
