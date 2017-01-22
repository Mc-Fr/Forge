package net.mcfr.decoration.containerBlocks.tileEntities;

import net.mcfr.decoration.containerBlocks.BlockBookshelf;
import net.mcfr.decoration.containerBlocks.guis.ContainerRestricted;

public class TileEntityBookshelf extends TileEntityRestricted {
  public TileEntityBookshelf() {
    super("bookshelf", ContainerRestricted.SIZE, 64, false, BlockBookshelf.class, ContainerRestricted.class);
  }
}
