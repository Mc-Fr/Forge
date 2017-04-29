package net.mcfr.mecanisms.doors;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Classe de base des portes renforcées. Seule la porte en chêne est concernée car les portes de
 * base en autre bois sont utilisées pour figurer les portes renforcées dans les différentes
 * essences de bois.
 *
 * @author Mc-Fr
 */
public abstract class BlockStrongDoor extends McfrBlockDoor {
  public BlockStrongDoor(BlockPlanks.EnumType type) {
    super("strong_" + type.getName(), Material.WOOD, SoundType.WOOD, 0, 3, "axe", 0);
  }
}
