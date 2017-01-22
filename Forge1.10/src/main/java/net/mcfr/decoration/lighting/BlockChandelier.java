package net.mcfr.decoration.lighting;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockChandelier extends McfrBlock {
  public BlockChandelier(boolean isLarge) {
    super((isLarge ? "large_" : "") + "chandelier", Material.WOOD, SoundType.WOOD, 1, 0, "axe", 0, CreativeTabs.DECORATIONS);
    setLightLevel(isLarge ? 0.75f : 0.875f);
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }
}
