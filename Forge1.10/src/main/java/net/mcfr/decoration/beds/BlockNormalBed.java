package net.mcfr.decoration.beds;

import net.mcfr.McfrItems;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBed;

public class BlockNormalBed extends McfrBlockBed {
  public BlockNormalBed() {
    super("normal_bed", 0.5625f, 1);
    setSoundType(SoundType.WOOD);
  }

  @Override
  protected ItemBed getItemToDrop() {
    return McfrItems.NORMAL_BED;
  }
}
