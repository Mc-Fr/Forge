package net.mcfr.decoration.lighting;

import net.mcfr.utils.math.Point3d;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockTripleCandle extends BlockCandle {
  private static final AxisAlignedBB AABB = new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.75, 0.9);

  public BlockTripleCandle() {
    super("triple", 0.5625f, new Point3d(0.3125, 0.625, 0.34375), new Point3d(0.65625, 0.75, 0.40625), new Point3d(0.46875, 0.875, 0.71875));
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
  }
}
