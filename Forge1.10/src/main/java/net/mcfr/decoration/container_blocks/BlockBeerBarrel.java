package net.mcfr.decoration.container_blocks;

import net.mcfr.McfrItems;
import net.minecraft.item.Item;

/**
 * Tonneau de bière.
 *
 * @author Mc-Fr
 */
public class BlockBeerBarrel extends BlockBarrel {
  public BlockBeerBarrel() {
    super("beer");
  }

  @Override
  public Item getItemDropped() {
    return McfrItems.BEER_BARREL;
  }

  @Override
  public Item getEmptyContainer() {
    return McfrItems.EMPTY_TANKARD;
  }

  @Override
  public Item getFullContainer() {
    return McfrItems.BEER_TANKARD;
  }
}
