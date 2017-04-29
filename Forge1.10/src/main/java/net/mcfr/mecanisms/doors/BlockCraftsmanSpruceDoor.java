package net.mcfr.mecanisms.doors;

import net.mcfr.McfrItems;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemDoor;

/**
 * Porte artisant en pin.
 *
 * @author Mc-Fr
 */
public class BlockCraftsmanSpruceDoor extends BlockCraftsmanDoor {
  public BlockCraftsmanSpruceDoor() {
    super(BlockPlanks.EnumType.SPRUCE);
  }

  @Override
  protected ItemDoor getItemToDrop() {
    return McfrItems.CRAFTSMAN_SPRUCE_DOOR;
  }
}
