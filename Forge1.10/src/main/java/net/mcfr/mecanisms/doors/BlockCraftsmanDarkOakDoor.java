package net.mcfr.mecanisms.doors;

import net.mcfr.McfrItems;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemDoor;

public class BlockCraftsmanDarkOakDoor extends BlockCraftsmanDoor {
  public BlockCraftsmanDarkOakDoor() {
    super(BlockPlanks.EnumType.DARK_OAK);
  }
  
  @Override
  protected ItemDoor getItemToDrop() {
    return McfrItems.CRAFTSMAN_DARK_OAK_DOOR;
  }
}
