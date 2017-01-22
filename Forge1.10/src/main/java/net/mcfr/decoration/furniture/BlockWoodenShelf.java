package net.mcfr.decoration.furniture;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockWoodenShelf extends BlockShelf {
  public BlockWoodenShelf(BlockPlanks.EnumType type) {
    super(type.getName(), Material.WOOD, SoundType.WOOD, 1, 3, "axe", 0, CreativeTabs.DECORATIONS);
  }
}
