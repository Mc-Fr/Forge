package net.mcfr.decoration.container_blocks;

import net.mcfr.McfrItems;
import net.minecraft.item.Item;

/**
 * Tonneau de vin.
 *
 * @author Mc-Fr
 */
public class BlockWineBarrel extends BlockBarrel {
  public BlockWineBarrel() {
    super("wine");
  }

  @Override
  public Item getItemDropped() {
    return McfrItems.WINE_BARREL;
  }

  @Override
  public Item getEmptyContainer() {
    return McfrItems.EMPTY_GLASS;
  }

  @Override
  public Item getFullContainer() {
    return McfrItems.WINE_GLASS;
  }
}
