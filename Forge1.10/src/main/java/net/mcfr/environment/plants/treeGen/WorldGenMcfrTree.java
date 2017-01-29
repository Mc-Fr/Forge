package net.mcfr.environment.plants.treeGen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class WorldGenMcfrTree extends WorldGenAbstractTree {
  /** The minimum height of a generated tree. */
  protected final int minTreeHeight;
  /** The metadata value of the wood to use in tree generation. */
  protected final IBlockState wood;
  /** The metadata value of the leaves to use in tree generation. */
  protected final IBlockState leaves;

  public WorldGenMcfrTree(boolean notify, int minTreeHeight, IBlockState wood, IBlockState leaves) {
    super(notify);
    this.minTreeHeight = minTreeHeight;
    this.wood = wood;
    this.leaves = leaves;
  }

  protected boolean checkHeight(World worldIn, BlockPos position, int height) {
    boolean canGrow = true;

    if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getHeight()) {
      for (int y = position.getY(); y <= position.getY() + 1 + height; y++) {
        int i = 1;

        if (y == position.getY())
          i = 0;
        if (y >= position.getY() + 1 + height - 2)
          i = 2;

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = position.getX() - i; x <= position.getX() + i && canGrow; x++) {
          for (int z = position.getZ() - i; z <= position.getZ() + i && canGrow; z++) {
            if (y < 0 && y >= worldIn.getHeight() || !isReplaceable(worldIn, pos.setPos(x, y, z))) {
              canGrow = false;
              break;
            }
          }
        }
      }
    }
    else
      canGrow = false;

    return canGrow;
  }
}
