package net.mcfr.construction;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class McfrBlockLeavesSlope extends McfrBlockSlope {
  public McfrBlockLeavesSlope(Block block, int metadata, String materialName) {
    super(block, metadata, materialName + "_leaves", null, -1);
  }

  @Override
  public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
    return false;
  }
}
