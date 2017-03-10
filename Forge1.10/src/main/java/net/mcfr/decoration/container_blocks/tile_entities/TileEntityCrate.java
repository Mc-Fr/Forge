package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.BlockCrate;
import net.mcfr.decoration.container_blocks.guis.ContainerRestricted;
import net.minecraft.item.ItemStack;

/**
 * Tile entity de la caisse.
 *
 * @author Mc-Fr
 */
public class TileEntityCrate extends TileEntityRestricted {
  public TileEntityCrate() {
    super("crate", ContainerRestricted.SIZE, 64, true, BlockCrate.class, ContainerRestricted.class);
  }
  
  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    return true;
  }
}
