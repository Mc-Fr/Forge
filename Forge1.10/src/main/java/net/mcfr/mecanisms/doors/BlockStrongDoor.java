package net.mcfr.mecanisms.doors;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public abstract class BlockStrongDoor extends McfrBlockDoor {
  public BlockStrongDoor(BlockPlanks.EnumType type) {
    super("strong_" + type.getName(), Material.WOOD, SoundType.WOOD, 0, 3, "axe", 0);
  }
}
