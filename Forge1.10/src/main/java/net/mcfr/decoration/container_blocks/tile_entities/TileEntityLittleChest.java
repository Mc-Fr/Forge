package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.guis.ContainerRestrictable;

/**
 * Tile entity de la caissette.
 *
 * @author Mc-Fr
 */
public class TileEntityLittleChest extends TileEntityRestrictable {
  public TileEntityLittleChest() {
    super("little_chest", ContainerRestrictable.LARGE_LINES_NB, 64, true, null, ContainerRestrictable.class);
  }
}
