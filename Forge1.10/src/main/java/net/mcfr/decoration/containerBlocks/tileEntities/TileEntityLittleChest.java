package net.mcfr.decoration.containerBlocks.tileEntities;

import net.mcfr.decoration.containerBlocks.BlockLittleChest;
import net.mcfr.decoration.containerBlocks.guis.ContainerRestricted;
import net.mcfr.utils.ItemsLists;

public class TileEntityLittleChest extends TileEntityRestricted {
  public TileEntityLittleChest() {
    super("little_chest", ContainerRestricted.SIZE, 64, true, BlockLittleChest.class, ContainerRestricted.class, ItemsLists.getLittleChestItems());
  }
}
