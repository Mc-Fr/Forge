package net.mcfr.decoration.furniture.tile_entities;

import net.mcfr.decoration.container_blocks.guis.ContainerRestrictable;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityRestrictable;

/**
 * Tile entity des tables.
 *
 * @author Mc-Fr
 */
public class TileEntityTable extends TileEntityRestrictable {
  public TileEntityTable() {
    super("normal_table", ContainerRestrictable.LARGE_LINES_NB, 64, false, null, ContainerRestrictable.class);
  }
}
