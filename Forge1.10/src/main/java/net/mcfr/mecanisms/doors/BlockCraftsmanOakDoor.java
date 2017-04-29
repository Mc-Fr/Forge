package net.mcfr.mecanisms.doors;

import net.mcfr.McfrItems;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemDoor;

/**
 * Porte artisant en chêne.
 *
 * @author Mc-Fr
 */
public class BlockCraftsmanOakDoor extends BlockCraftsmanDoor {
  public BlockCraftsmanOakDoor() {
    super(BlockPlanks.EnumType.OAK);
  }

  @Override
  protected ItemDoor getItemToDrop() {
    return McfrItems.CRAFTSMAN_OAK_DOOR;
  }
}
