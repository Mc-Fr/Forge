package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.BlockBookshelf;
import net.mcfr.decoration.container_blocks.guis.ContainerRestricted;

public class TileEntityBookshelf extends TileEntityRestricted {
  public TileEntityBookshelf() {
    super("bookshelf", ContainerRestricted.SIZE, 64, false, BlockBookshelf.class, ContainerRestricted.class);
  }
}
