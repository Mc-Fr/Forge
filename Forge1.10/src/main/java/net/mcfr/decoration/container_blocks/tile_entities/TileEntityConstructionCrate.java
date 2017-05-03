package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.guis.ContainerRestrictable;

/**
 * Tile entity de la caisse de construction.
 *
 * @author Mc-Fr
 */
public class TileEntityConstructionCrate extends TileEntityRestrictable {
  public TileEntityConstructionCrate() {
    super("construction_crate", ContainerRestrictable.LARGE_LINES_NB, 64, true, null, ContainerRestrictable.class);
  }
}
