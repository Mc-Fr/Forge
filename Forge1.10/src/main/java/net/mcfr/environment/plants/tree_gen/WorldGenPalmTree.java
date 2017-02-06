package net.mcfr.environment.plants.tree_gen;

import java.util.Random;

import net.mcfr.McfrBlocks;
import net.mcfr.environment.plants.BlockExoticLeaves;
import net.mcfr.environment.plants.BlockExoticLog;
import net.mcfr.environment.plants.EnumExoticWoodType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenPalmTree extends WorldGenMcfrTree {
  public static final IBlockState PALM_TRUNK = McfrBlocks.EXOTIC_LOG.getDefaultState().withProperty(BlockExoticLog.VARIANT, EnumExoticWoodType.PALM_TREE);
  public static final IBlockState PALM_LEAF = McfrBlocks.EXOTIC_LEAVES.getDefaultState().withProperty(BlockExoticLeaves.VARIANT, EnumExoticWoodType.PALM_TREE).withProperty(BlockExoticLeaves.CHECK_DECAY, false);

  public WorldGenPalmTree(boolean notify) {
    super(notify, 4, PALM_TRUNK, PALM_LEAF);
  }

  @SuppressWarnings("incomplete-switch")
  @Override
  public boolean generate(World worldIn, Random rand, BlockPos position) {
    int idx = rand.nextInt(3);
    int height = (3 * idx) + this.minTreeHeight;
    EnumFacing axis = EnumFacing.getHorizontal(rand.nextInt(4));

    int[] dir = {0, 0, 0, 0};

    switch (axis) {
      case EAST:
        dir = new int[]{0, 0, 0, 1};
        break;
      case SOUTH:
        dir = new int[]{1, 0, 0, 0};
        break;
      case WEST:
        dir = new int[]{0, -1, 0, 0};
        break;
      case NORTH:
        dir = new int[]{0, 0, -1, 0};
        break;
    }

    if (checkHeight(worldIn, position, height)) {
      IBlockState state = worldIn.getBlockState(position.down());

      if (state.getBlock().canSustainPlant(state, worldIn, position.down(), EnumFacing.UP, McfrBlocks.EXOTIC_SAPLING) && position.getY() < worldIn.getHeight() - height - 1) {
        setDirtAt(worldIn, position.down());

        BlockPos pos = new BlockPos(position);

        for (int i = 0; i < 4; i++) {
          int maxY = (i == 0 ? idx + 3 : (i == 1 ? 2 : i == 2 ? idx + 2 : (i == 3 ? idx + 4 : 1)));

          for (int y = 0; y < maxY; y++) {
            BlockPos pos1 = pos.add(dir[axis.getHorizontalIndex()] * i, 0, dir[axis.getHorizontalIndex()] * i);

            if (isReplaceable(worldIn, pos1))
              setBlockAndNotifyAdequately(worldIn, pos1, this.wood);

            // Colerette
            if (i == 3 && y == maxY - 2) {
              if (isReplaceable(worldIn, pos1.north()))
                setBlockAndNotifyAdequately(worldIn, pos1.north(), this.wood);
              if (isReplaceable(worldIn, pos1.south()))
                setBlockAndNotifyAdequately(worldIn, pos1.south(), this.wood);
              if (isReplaceable(worldIn, pos1.west()))
                setBlockAndNotifyAdequately(worldIn, pos1.west(), this.wood);
              if (isReplaceable(worldIn, pos1.east()))
                setBlockAndNotifyAdequately(worldIn, pos1.east(), this.wood);
            }
            if (y != maxY - 1)
              pos = pos.up();
          }
        }

        pos = pos.add(dir[axis.getHorizontalIndex()] * 3, 1, dir[axis.getHorizontalIndex()] * 3);

        // Placement des feuilles
        if (isReplaceable(worldIn, pos))
          setBlockAndNotifyAdequately(worldIn, pos, this.leaves);

        pos = pos.down();
        for (int x = -1; x <= 1; x++) {
          for (int z = -1; z <= 1; z++) {
            if (x != 0 || z != 0) {
              BlockPos pos1 = pos.add(x, 0, z);

              if (isReplaceable(worldIn, pos1))
                setBlockAndNotifyAdequately(worldIn, pos1, this.leaves);
            }
          }
        }

        for (int y1 = 0; y1 < idx + 5; y1++) {
          int offset = 2;

          if (y1 > 1)
            offset = 3;
          if (isReplaceable(worldIn, pos.north(offset)))
            setBlockAndNotifyAdequately(worldIn, pos.north(offset), this.leaves);
          if (isReplaceable(worldIn, pos.south(offset)))
            setBlockAndNotifyAdequately(worldIn, pos.south(offset), this.leaves);
          if (isReplaceable(worldIn, pos.east(offset)))
            setBlockAndNotifyAdequately(worldIn, pos.east(offset), this.leaves);
          if (isReplaceable(worldIn, pos.west(offset)))
            setBlockAndNotifyAdequately(worldIn, pos.west(offset), this.leaves);
          if (y1 != 1)
            pos = pos.down();
        }

        return true;
      }
    }

    return false;
  }

  @Override
  public boolean isReplaceable(World world, BlockPos pos) {
    return world.getBlockState(pos).getMaterial().isReplaceable();
  }
}
