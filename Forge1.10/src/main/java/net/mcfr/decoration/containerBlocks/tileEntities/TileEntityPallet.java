package net.mcfr.decoration.containerBlocks.tileEntities;

import net.mcfr.decoration.containerBlocks.BlockPallet;
import net.mcfr.decoration.containerBlocks.guis.ContainerRestricted;

public class TileEntityPallet extends TileEntityRestricted {
  public TileEntityPallet() {
    super("pallet", ContainerRestricted.SIZE, 64, false, BlockPallet.class, ContainerRestricted.class);
  }
}
