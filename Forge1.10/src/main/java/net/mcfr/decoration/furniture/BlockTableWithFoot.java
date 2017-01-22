package net.mcfr.decoration.furniture;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTableWithFoot extends McfrBlock {
  public BlockTableWithFoot() {
    super("foot_table", Material.WOOD, SoundType.WOOD, 1.5f, 5, "axe", 0, CreativeTabs.DECORATIONS);
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }
}
