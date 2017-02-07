package net.mcfr.decoration.beds;

import net.mcfr.McfrItems;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBed;

/**
 * Paillasse en pierre.
 *
 * @author Mc-Fr
 */
public class BlockStoneBed extends McfrBlockBed {
  public BlockStoneBed() {
    super("stone_bed", 0.5625f, 0.5f);
    setSoundType(SoundType.STONE);
  }

  @Override
  protected ItemBed getItemToDrop() {
    return McfrItems.STONE_BED;
  }
}
