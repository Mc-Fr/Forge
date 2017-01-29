package net.mcfr.environment.plants.treeGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.mcfr.McfrBlocks;
import net.mcfr.environment.plants.BlockExoticLeaves;
import net.mcfr.environment.plants.BlockExoticWood;
import net.mcfr.environment.plants.EnumExoticWoodType;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenCherryTree extends WorldGenMcfrTree {
  public static final IBlockState CHERRY_TRUNK = McfrBlocks.EXOTIC_WOOD.getDefaultState().withProperty(BlockExoticWood.VARIANT, EnumExoticWoodType.CHERRY_TREE);
  public static final IBlockState CHERRY_LEAF = McfrBlocks.EXOTIC_LEAVES.getDefaultState().withProperty(BlockExoticLeaves.VARIANT, EnumExoticWoodType.CHERRY_TREE).withProperty(BlockExoticLeaves.CHECK_DECAY, false);

  public WorldGenCherryTree(boolean notify) {
    super(notify, 4, CHERRY_TRUNK, CHERRY_LEAF);
  }

  @Override
  public boolean generate(World worldIn, Random rand, BlockPos position) {
    List<LeafSphere> spheres = new ArrayList<>();
    int height = rand.nextInt(3) + this.minTreeHeight;

    if (checkHeight(worldIn, position, height)) {
      IBlockState state = worldIn.getBlockState(position.down());

      if (state.getBlock().canSustainPlant(state, worldIn, position.down(), EnumFacing.UP, McfrBlocks.EXOTIC_SAPLING) && position.getY() < worldIn.getHeight() - height - 1) {
        setDirtAt(worldIn, position.down());

        int y = 0;
        int xo = 0;
        int zo = 0;
        int orientation = 0;

        for (y = 0; y < height; y++)
          setBlockAndNotifyAdequately(worldIn, position.up(y), this.wood);

        int random = rand.nextInt(4);
        if (random == 0)
          xo = -1;
        else if (random == 1)
          xo = 1;
        else if (random == 2)
          zo = -1;
        else if (random == 3)
          zo = 1;

        if (random == 0 || random == 1)
          orientation = 4;
        else
          orientation = 8;

        y--;
        if (worldIn.isAirBlock(position.add(xo, 0, zo)))
          setBlockAndNotifyAdequately(worldIn, position.add(xo, 0, zo), this.wood);

        for (int i = 0; i < 3; i++) {
          int xOffset, zOffset;

          if (i > 0) {
            double theta = (1 + 2 * i) * Math.PI / 4;

            xOffset = (int) Math.round(xo * Math.cos(theta) - zo * Math.sin(theta));
            zOffset = (int) Math.round(zo * Math.cos(theta) + xo * Math.sin(theta));
          }
          else {
            xOffset = xo;
            zOffset = zo;
          }

          int x = position.getX() + xOffset;
          int z = position.getZ() + zOffset;

          BlockPos pos = new BlockPos(x, position.getY() - 1, z);
          Block block = worldIn.getBlockState(pos).getBlock();

          if (block == Blocks.DIRT || block == Blocks.GRASS) {
            if (rand.nextBoolean())
              setBlockAndNotifyAdequately(worldIn, pos, this.wood);
          }

          pos = new BlockPos(x, position.getY() + y, z);
          setBlockAndNotifyAdequately(worldIn, pos, this.wood);
          setBlockAndNotifyAdequately(worldIn, pos.up(), this.wood);
          setBlockAndNotifyAdequately(worldIn, pos.up(2), this.wood);

          if (orientation == 4) {
            x = position.getX() + xOffset * 2;
          }
          else {
            z = position.getZ() + zOffset * 2;
          }
          if (i == 2) {
            if (orientation == 4) {
              x = position.getX() + xOffset;
              z = position.getX() + zOffset * 2;
            }
            else {
              x = position.getX() + xOffset * 2;
              z = position.getX() + zOffset;
            }
          }

          pos = new BlockPos(x, position.getY() + y + 1, z);
          setBlockAndNotifyAdequately(worldIn, pos, this.wood);
          setBlockAndNotifyAdequately(worldIn, pos.up(), this.wood);
        }

        spheres.add(new LeafSphere(position.up(y - 1), 3, 4, 80, 1));
        spheres.add(new LeafSphere(position.up(y), 4, 5, 80, 0));
        spheres.add(new LeafSphere(position.up(y + 1), 4, 5, 90, 0));
        spheres.add(new LeafSphere(position.up(y + 2), 3, 4, 90, 0));
        spheres.add(new LeafSphere(position.up(y + 3), 2, 3, 90, 0));

        for (LeafSphere sphere : spheres) {
          generateLeaves(worldIn, rand, sphere);
        }

        return true;
      }
    }

    return false;
  }

  private void generateLeaves(World worldIn, Random rand, LeafSphere sphere) {
    BlockPos pos = sphere.getPos();
    int diameter = sphere.getDiameter();
    int overflow = sphere.getOverflow();
    int ratio = sphere.getRatio();

    for (int x = -diameter; x < diameter + 1; x++) {
      for (int z = -diameter; z < diameter + 1; z++) {
        BlockPos pos1 = pos.add(x, 0, z);
        int i = Math.abs(x) + Math.abs(z);

        if ((i <= overflow || overflow == 0) && rand.nextInt(100) <= ratio && worldIn.getBlockState(pos1).getMaterial().isReplaceable()) {
          setBlockAndNotifyAdequately(worldIn, pos1, this.leaves);

          if (i == overflow && sphere.getSmear() >= 1 && worldIn.getBlockState(pos1.down()).getMaterial().isReplaceable() && rand.nextInt(2) == 0)
            setBlockAndNotifyAdequately(worldIn, pos1.down(), this.leaves);
        }
      }
    }
  }

  private class LeafSphere {
    private final BlockPos pos;
    private final int diameter, overflow, ratio, smear;

    public LeafSphere(BlockPos pos, int diameter, int overflowing, int ratio, int smear) {
      this.pos = pos;
      this.diameter = diameter;
      this.overflow = overflowing;
      this.ratio = ratio;
      this.smear = smear;
    }

    public BlockPos getPos() {
      return this.pos;
    }

    public int getDiameter() {
      return this.diameter;
    }

    public int getOverflow() {
      return this.overflow;
    }

    public int getRatio() {
      return this.ratio;
    }

    public int getSmear() {
      return this.smear;
    }
  }
}
