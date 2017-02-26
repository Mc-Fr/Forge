package net.mcfr.decoration.lighting;

import net.mcfr.utils.math.Point3d;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Bougie seule.
 * 
 * @author Mc-Fr
 */
public class BlockSimpleCandle extends BlockCandle {
  private static final AxisAlignedBB AABB = new AxisAlignedBB(0.46875, 0, 0.46875, 0.59375, 0.5, 0.59375);

  public BlockSimpleCandle() {
    super("simple", 0.5f, new Point3d(0.53125, 0.625, 0.53125));
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
  }
}
