package net.mcfr.mecanisms.doors;

import net.mcfr.McfrItems;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemDoor;

public class BlockCraftsmanJungleDoor extends BlockCraftsmanDoor {
  public BlockCraftsmanJungleDoor() {
    super(BlockPlanks.EnumType.JUNGLE);
  }
  
  @Override
  protected ItemDoor getItemToDrop() {
    return McfrItems.CRAFTSMAN_JUNGLE_DOOR;
  }
}
