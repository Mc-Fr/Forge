package net.mcfr.mecanisms.trapdoors;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Trappe artisant.
 *
 * @author Mc-Fr
 */
public class BlockCraftsmanTrapdoor extends McfrBlockTrapDoor {
  /**
   * Crée une trappe.
   * 
   * @param type le type de bois
   */
  public BlockCraftsmanTrapdoor(BlockPlanks.EnumType type) {
    super("craftsman_" + type.getName(), Material.WOOD, SoundType.WOOD, 6, 0, "axe", 0);
  }
}
