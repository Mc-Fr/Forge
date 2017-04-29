package net.mcfr.mecanisms.doors;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Classe de base pour les portes artisant.
 *
 * @author Mc-Fr
 */
public abstract class BlockCraftsmanDoor extends McfrBlockDoor {
  public BlockCraftsmanDoor(BlockPlanks.EnumType type) {
    super("craftsman_" + type.getName(), Material.WOOD, SoundType.WOOD, 6, 0, "axe", 0);
  }
}
