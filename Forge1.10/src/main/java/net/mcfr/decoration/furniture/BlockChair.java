package net.mcfr.decoration.furniture;

import net.mcfr.commons.McfrBlockOrientable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockChair extends McfrBlockOrientable {
  public BlockChair(String unlocalizedName, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel) {
    super(unlocalizedName, material, sound, hardness, resistance, tool, harvestLevel, CreativeTabs.DECORATIONS);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return new AxisAlignedBB(0, 0, 0, 1, 0.5f, 1);
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
