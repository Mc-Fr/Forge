package net.mcfr.mecanisms.doors;

import net.mcfr.McfrItems;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemDoor;

/**
 * Porte artisant en acacia.
 *
 * @author Mc-Fr
 */
public class BlockCraftsmanAcaciaDoor extends BlockCraftsmanDoor {
  public BlockCraftsmanAcaciaDoor() {
    super(BlockPlanks.EnumType.ACACIA);
  }

  @Override
  protected ItemDoor getItemToDrop() {
    return McfrItems.CRAFTSMAN_ACACIA_DOOR;
  }
}
