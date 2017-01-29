package net.mcfr.environment.plants.treeGen;

import java.util.Random;

import net.mcfr.McfrBlocks;
import net.mcfr.environment.plants.BlockExoticLeaves;
import net.mcfr.environment.plants.BlockExoticWood;
import net.mcfr.environment.plants.EnumExoticWoodType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenAppleTree extends WorldGenMcfrTree {
  public static final IBlockState APPLE_TRUNK = McfrBlocks.EXOTIC_WOOD.getDefaultState().withProperty(BlockExoticWood.VARIANT, EnumExoticWoodType.APPLE_TREE);
  public static final IBlockState APPLE_LEAF = McfrBlocks.EXOTIC_LEAVES.getDefaultState().withProperty(BlockExoticLeaves.VARIANT, EnumExoticWoodType.APPLE_TREE).withProperty(BlockExoticLeaves.CHECK_DECAY, false);

  public WorldGenAppleTree(boolean notify) {
    super(notify, 4, APPLE_TRUNK, APPLE_LEAF);
  }

  @Override
  public boolean generate(World worldIn, Random rand, BlockPos position) {
    int height = rand.nextInt(3) + this.minTreeHeight;

    if (checkHeight(worldIn, position, height)) {
      IBlockState state = worldIn.getBlockState(position.down());

      if (state.getBlock().canSustainPlant(state, worldIn, position.down(), EnumFacing.UP, McfrBlocks.EXOTIC_SAPLING) && position.getY() < worldIn.getHeight() - height - 1) {
        setDirtAt(worldIn, position.down());

        for (int y = position.getY() - 3 + height; y <= position.getY() + height; y++) {
          int y1 = y - (position.getY() + height);
          int j = 1 - y1 / 2;

          for (int x = position.getX() - j; x <= position.getX() + j; x++) {
            int x1 = x - position.getX();

            for (int z = position.getZ() - j; z <= position.getZ() + j; z++) {
              int z1 = z - position.getZ();

              if (Math.abs(x1) != j || Math.abs(z1) != j || rand.nextInt(2) != 0 && y1 != 0) {
                BlockPos blockpos = new BlockPos(x, y, z);
                state = worldIn.getBlockState(blockpos);

                if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos) || state.getMaterial() == Material.VINE) {
                  setBlockAndNotifyAdequately(worldIn, blockpos, this.leaves);
                }
              }
            }
          }
        }

        for (int j = 0; j < height; j++) {
          BlockPos upN = position.up(j);
          state = worldIn.getBlockState(upN);

          if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN) || state.getMaterial() == Material.VINE) {
            setBlockAndNotifyAdequately(worldIn, position.up(j), this.wood);
          }
        }

        return true;
      }
    }

    return false;
  }
}
