package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.BlockBookshelf;
import net.mcfr.decoration.container_blocks.guis.ContainerRestrictable;

/**
 * Tile entity de la bibliothèque.
 *
 * @author Mc-Fr
 */
public class TileEntityBookshelf extends TileEntityRestrictable {
  public TileEntityBookshelf() {
    super("bookshelf", ContainerRestrictable.REDUCED_LINES_NB, 64, false, BlockBookshelf.class, ContainerRestrictable.class);
  }
}
