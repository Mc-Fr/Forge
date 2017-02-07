package net.mcfr.decoration.furniture;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Étagère en bois.
 *
 * @author Mc-Fr
 */
public class BlockWoodenShelf extends BlockShelf {
  /**
   * Crée une étagère en bois.
   * 
   * @param type le type de bois
   */
  public BlockWoodenShelf(BlockPlanks.EnumType type) {
    super(type.getName(), Material.WOOD, SoundType.WOOD, 1, 3, "axe", 0);
  }
}
