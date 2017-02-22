package net.mcfr.decoration.lighting;

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
  // TEMP
  private static final AxisAlignedBB AABB = FULL_BLOCK_AABB;

  public BlockDoubleCandle() {
    super("double", 0.5f);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
  }
}
