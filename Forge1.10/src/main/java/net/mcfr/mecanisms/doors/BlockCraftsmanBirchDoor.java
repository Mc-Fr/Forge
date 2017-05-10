package net.mcfr.mecanisms.doors;

import net.mcfr.McfrItems;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemDoor;

/**
 * Porte artisant en bouleau.
 *
 * @author Mc-Fr
 */
public class BlockCraftsmanBirchDoor extends BlockCraftsmanDoor {
  public BlockCraftsmanBirchDoor() {
    super(BlockPlanks.EnumType.BIRCH);
  }

  @Override
  protected ItemDoor getItemToDrop() {
    return McfrItems.CRAFTSMAN_BIRCH_DOOR;
  }
}
