package net.mcfr.decoration;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockRopeAttach extends McfrBlock {
  public BlockRopeAttach() {
    super("rope_attach", Material.CLOTH, SoundType.CLOTH, 0.5f, 0, null, -1, CreativeTabs.DECORATIONS);
  }


  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    float f = 0.3f, h = 0.5f;
    return new AxisAlignedBB(h - f, 0, h - f, h + f, 1, h + f);
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
