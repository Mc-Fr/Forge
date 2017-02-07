package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.BlockLittleChest;
import net.mcfr.decoration.container_blocks.guis.ContainerRestricted;

/**
 * Tile entity de la caissette.
 *
 * @author Mc-Fr
 */
public class TileEntityLittleChest extends TileEntityRestricted {
  public TileEntityLittleChest() {
    super("little_chest", ContainerRestricted.SIZE, 64, true, BlockLittleChest.class, ContainerRestricted.class);
  }
}
