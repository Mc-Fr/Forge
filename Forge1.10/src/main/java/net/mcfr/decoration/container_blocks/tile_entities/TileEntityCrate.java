package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.BlockCrate;
import net.mcfr.decoration.container_blocks.guis.ContainerRestricted;

public class TileEntityCrate extends TileEntityRestricted {
  public TileEntityCrate() {
    super("crate", ContainerRestricted.SIZE, 64, true, BlockCrate.class, ContainerRestricted.class);
  }
}
