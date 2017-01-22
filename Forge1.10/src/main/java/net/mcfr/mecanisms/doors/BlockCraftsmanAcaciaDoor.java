package net.mcfr.mecanisms.doors;

import net.mcfr.McfrItems;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemDoor;

public class BlockCraftsmanAcaciaDoor extends BlockCraftsmanDoor {
  public BlockCraftsmanAcaciaDoor() {
    super(BlockPlanks.EnumType.ACACIA);
  }
  
  @Override
  protected ItemDoor getItemToDrop() {
    return McfrItems.CRAFTSMAN_ACACIA_DOOR;
  }
}
