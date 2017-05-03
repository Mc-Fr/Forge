package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.guis.ContainerRestrictable;

/**
 * Tile entity de la caisse.
 *
 * @author Mc-Fr
 */
public class TileEntityCrate extends TileEntityRestrictable {
  public TileEntityCrate() {
    super("crate", ContainerRestrictable.LARGE_LINES_NB, 64, true, null, ContainerRestrictable.class);
  }
}
