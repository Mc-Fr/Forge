package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.BlockPallet;
import net.mcfr.decoration.container_blocks.guis.ContainerRestricted;

public class TileEntityPallet extends TileEntityRestricted {
  public TileEntityPallet() {
    super("pallet", ContainerRestricted.SIZE, 64, false, BlockPallet.class, ContainerRestricted.class);
  }
}
