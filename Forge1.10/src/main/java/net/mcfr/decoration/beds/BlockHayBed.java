package net.mcfr.decoration.beds;

import net.mcfr.McfrItems;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBed;

public class BlockHayBed extends McfrBlockBed {
  public BlockHayBed() {
    super("hay_bed", 0.5625f, 0.2f);
    setSoundType(SoundType.WOOD);
  }

  @Override
  protected ItemBed getItemToDrop() {
    return McfrItems.HAY_BED;
  }
}
