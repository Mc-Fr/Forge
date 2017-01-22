package net.mcfr.decoration.containerBlocks.tileEntities;

import net.mcfr.decoration.containerBlocks.BlockFoodCrate;
import net.mcfr.decoration.containerBlocks.guis.ContainerRestricted;
import net.mcfr.utils.ItemsLists;

public class TileEntityFoodCrate extends TileEntityRestricted {
  public TileEntityFoodCrate() {
    super("food_crate", ContainerRestricted.SIZE, 64, true, BlockFoodCrate.class, ContainerRestricted.class, ItemsLists.getFoodCrateItems());
  }
}
