package net.mcfr.decoration.beds;

import net.mcfr.McfrItems;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBed;

/**
 * Sac de couchage.
 *
 * @author Mc-Fr
 */
public class BlockSleepingBag extends McfrBlockBed {
  public BlockSleepingBag() {
    super("sleeping_bag", 0.25f, 0.2f);
    setSoundType(SoundType.CLOTH);
  }

  @Override
  protected ItemBed getItemToDrop() {
    return McfrItems.SLEEPING_BAG;
  }
}
