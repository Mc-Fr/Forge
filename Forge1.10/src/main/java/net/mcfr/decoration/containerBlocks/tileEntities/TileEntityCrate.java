package net.mcfr.decoration.containerBlocks.tileEntities;

import net.mcfr.decoration.containerBlocks.BlockCrate;
import net.mcfr.decoration.containerBlocks.guis.ContainerRestricted;

public class TileEntityCrate extends TileEntityRestricted {
  public TileEntityCrate() {
    super("crate", ContainerRestricted.SIZE, 64, true, BlockCrate.class, ContainerRestricted.class);
  }
}
