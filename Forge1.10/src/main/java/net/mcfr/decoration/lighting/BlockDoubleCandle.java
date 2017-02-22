package net.mcfr.decoration.lighting;

import net.mcfr.utils.math.Point3d;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Doubles bougies.
 *
 * @author Mc-Fr
 */
public class BlockDoubleCandle extends BlockCandle {
  private static final AxisAlignedBB AABB = new AxisAlignedBB(0.25, 0, 0.1875, 0.75, 0.625, 0.71875);

  public BlockDoubleCandle() {
    super("double", 0.55f, new Point3d(0.3125, 0.625, 0.25), new Point3d(0.65625, 0.75, 0.625));
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
  }
}
