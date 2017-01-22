package net.mcfr.decoration.furniture;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockWoodenChair extends BlockChair {
  public BlockWoodenChair(BlockPlanks.EnumType type, String seatType) {
    super(type.getName() + "_" + seatType, Material.WOOD, SoundType.WOOD, 1.5f, 5, "axe", 0);
  }
}
