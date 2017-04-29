package net.mcfr.environment.plants.tree_gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.mcfr.McfrBlocks;
import net.mcfr.environment.plants.BlockExoticLeaves;
import net.mcfr.environment.plants.BlockExoticLog;
import net.mcfr.environment.plants.EnumExoticWoodType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Générateur de béluxiers.
 *
 * @author Mc-Fr
 */
public class WorldGenBeluxier extends WorldGenMcfrTree {
  public static final IBlockState BELUXIER_TRUNK = McfrBlocks.EXOTIC_LOG.getDefaultState().withProperty(BlockExoticLog.VARIANT,
      EnumExoticWoodType.BELUXIER);
  public static final IBlockState BELUXIER_LEAF = McfrBlocks.EXOTIC_LEAVES.getDefaultState()
      .withProperty(BlockExoticLeaves.VARIANT, EnumExoticWoodType.BELUXIER).withProperty(BlockExoticLeaves.CHECK_DECAY, false);

  /**
   * Crée un générateur.
   * 
   * @param notify doit-on avertir de la mise à jour ?
   */
  public WorldGenBeluxier(boolean notify) {
    super(notify, 4, BELUXIER_TRUNK, BELUXIER_LEAF);
  }

  @Override
  public boolean generate(World worldIn, Random rand, BlockPos position) {
    int base = this.minTreeHeight + rand.nextInt(2);
    int height = 5 + base;

    if (checkHeight(worldIn, position, height)) {
      List<LeafSphere> spheres = new ArrayList<>();
      IBlockState state = worldIn.getBlockState(position.down());

      if (state.getBlock().canSustainPlant(state, worldIn, position.down(), EnumFacing.UP, McfrBlocks.EXOTIC_SAPLING)
          && position.getY() < worldIn.getHeight() - height - 1) {
        setDirtAt(worldIn, position.down());

        for (int y = 0; y < height; y++)
          setBlockAndNotifyAdequately(worldIn, position.up(y), this.wood);

        int xo = 0;
        int zo = 0;
        EnumAxis axis = EnumAxis.Y;
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
          axis = EnumAxis.X;
        else
          axis = EnumAxis.Z;

        setBlockAndNotifyAdequately(worldIn, position.up(base), Blocks.AIR.getDefaultState());
        setBlockAndNotifyAdequately(worldIn, position.add(xo, base - 1, zo), this.wood);
        setBlockAndNotifyAdequately(worldIn, position.add(xo, base, zo), this.wood);
        setBlockAndNotifyAdequately(worldIn, position.add(xo, base + 1, zo), this.wood);

        BlockPos pos = position.add(-xo, 0, -zo);
        // Sol
        if (worldIn.isAirBlock(pos))
          setBlockAndNotifyAdequately(worldIn, pos, this.wood.withProperty(BlockExoticLog.LOG_AXIS, axis));

        for (int x = -1; x <= 1; x++) {
          for (int z = -1; z <= 1; z++) {
            pos = position.add(x, -1, z);
            Block block = worldIn.getBlockState(pos).getBlock();

            if (block == Blocks.DIRT || block == Blocks.GRASS) {
              if (rand.nextInt(4) == 0)
                setBlockAndNotifyAdequately(worldIn, pos, this.wood);
            }
          }
        }

        // Branches
        for (int b = 0; b < 4; b++) {
          xo = 0;
          zo = 0;
          if (b == 0)
            xo = -1;
          else if (b == 1)
            xo = 1;
          else if (b == 2)
            zo = -1;
          else if (b == 3)
            zo = 1;

          if (b == 0 || b == 1)
            axis = EnumAxis.X;
          else
            axis = EnumAxis.Z;

          for (int i = 1; i <= 3; i++)
            setBlockAndNotifyAdequately(worldIn, position.add(i * xo, height, i * zo), this.wood.withProperty(BlockExoticLog.LOG_AXIS, axis));
          setBlockAndNotifyAdequately(worldIn, position.add(3 * xo, height - 1, 3 * zo), this.wood);
          setBlockAndNotifyAdequately(worldIn, position.add(4 * xo, height - 1, 4 * zo), this.wood.withProperty(BlockExoticLog.LOG_AXIS, axis));
          setBlockAndNotifyAdequately(worldIn, position.add(4 * xo, height - 2, 4 * zo), this.wood);
        }

        for (int i = 0, n = 1 + rand.nextInt(3); i < n; i++) {
          spheres.add(new LeafSphere(position.up(base + 3 - i), 5, 6, 6, 100, i == n - 1 ? 1 : 0));
        }
        spheres.add(new LeafSphere(position.up(base + 4), 5, 5, 6, 100, -1));
        spheres.add(new LeafSphere(position.up(base + 5), 4, 4, 4, 100, -1));
        spheres.add(new LeafSphere(position.up(base + 6), 3, 0, 3, 100, 0));

        for (LeafSphere sphere : spheres)
          generateLeaves(worldIn, rand, sphere);

        return true;
      }
    }

    return false;
  }

  private void generateLeaves(World worldIn, Random rand, LeafSphere sphere) {
    BlockPos position = sphere.getPos();
    int diameter = sphere.getDiameter();
    int minOverflow = sphere.getMinOverflow();
    int maxOverflow = sphere.getMaxOverflow();
    int smear = sphere.getSmear();

    for (int x = -diameter; x < diameter + 1; x++) {
      for (int z = -diameter; z < diameter + 1; z++) {
        BlockPos pos = sphere.getPos().add(x, 0, z);
        int n = Math.abs(x) + Math.abs(z);

        if (n >= minOverflow && n <= maxOverflow && rand.nextInt(100) <= sphere.getRatio()
            && worldIn.getBlockState(pos).getMaterial().isReplaceable()) {
          setBlockAndNotifyAdequately(worldIn, pos, this.leaves);

          if (worldIn.getBlockState(pos.down(smear)).getMaterial().isReplaceable()) {
            if (smear < 0) {
              if (n == maxOverflow - 1 && smear != 0) {
                if (rand.nextInt(3) == 0)
                  setBlockAndNotifyAdequately(worldIn, pos.down(smear), this.leaves);
              }
              else if (n == maxOverflow && smear != 0) {
                if (rand.nextInt(5) == 0)
                  setBlockAndNotifyAdequately(worldIn, pos.down(smear), this.leaves);
              }
            }
            else {
              if (n == maxOverflow && smear != 0 && rand.nextBoolean()) {
                setBlockAndNotifyAdequately(worldIn, pos.down(smear), this.leaves);

                int offset = 0;
                int meta = 0;

                if (pos.getX() > position.getX()) {
                  offset = 1;
                  meta = 2;
                }
                if (pos.getX() < position.getX()) {
                  offset = -1;
                  meta = 8;
                }
                spawnVines(worldIn, pos.add(offset, -smear, 0), meta);

                if (pos.getZ() > position.getZ()) {
                  offset = 1;
                  meta = 4;
                }
                if (pos.getZ() < position.getZ()) {
                  offset = -1;
                  meta = 1;
                }
                spawnVines(worldIn, pos.add(0, -smear, offset), meta);
              }
            }
          }
        }
      }
    }
  }

  @SuppressWarnings("deprecation")
  private void spawnVines(World world, BlockPos pos, int meta) {
    int maxLength = 4;

    setBlockAndNotifyAdequately(world, pos, Blocks.VINE.getStateFromMeta(meta));
    while (world.isAirBlock(pos = pos.down()) && maxLength > 0) {
      setBlockAndNotifyAdequately(world, pos, Blocks.VINE.getStateFromMeta(meta));
      maxLength--;
    }
  }
}
