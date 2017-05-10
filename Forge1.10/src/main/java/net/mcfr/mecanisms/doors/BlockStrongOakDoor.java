package net.mcfr.mecanisms.doors;

import net.mcfr.McfrItems;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemDoor;

/**
 * Porte renforcée en chêne.
 *
 * @author Mc-Fr
 */
public class BlockStrongOakDoor extends BlockStrongDoor {
  public BlockStrongOakDoor() {
    super(BlockPlanks.EnumType.OAK);
  }

  @Override
  protected ItemDoor getItemToDrop() {
    return McfrItems.STRONG_OAK_DOOR;
  }
}
