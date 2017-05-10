package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.guis.ContainerRestrictable;

/**
 * Tile entity du fût de nourriture.
 *
 * @author Mc-Fr
 */
public class TileEntityFoodCrate extends TileEntityRestrictable {
  public TileEntityFoodCrate() {
    super("food_crate", ContainerRestrictable.LARGE_LINES_NB, 64, true, null, ContainerRestrictable.class);
  }
}
