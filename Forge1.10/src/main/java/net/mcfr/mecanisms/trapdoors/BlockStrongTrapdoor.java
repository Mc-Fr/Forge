package net.mcfr.mecanisms.trapdoors;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Trappe renforcée.
 *
 * @author Mc-Fr
 */
public class BlockStrongTrapdoor extends McfrBlockTrapDoor {
  /**
   * Crée une trappe.
   * 
   * @param type le type de bois
   */
  public BlockStrongTrapdoor(BlockPlanks.EnumType type) {
    super("strong_" + type.getName(), Material.WOOD, SoundType.WOOD, 3, 0, "axe", 0);
  }
}
