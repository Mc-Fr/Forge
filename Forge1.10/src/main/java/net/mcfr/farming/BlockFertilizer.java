package net.mcfr.farming;

import java.util.Random;

import net.mcfr.McfrBlocks;
import net.mcfr.McfrItems;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Bloc d'engrais. Ce bloc nécessite un certains ensoleillement pour pouvoir maturer.
 *
 * @author Mc-Fr
 */
public class BlockFertilizer extends McfrBlock {
  public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);

  public BlockFertilizer() {
    super("fertilizer_block", Material.GROUND, SoundType.PLANT, 0.5f, 0, "shovel", 0, CreativeTabs.DECORATIONS);
    setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
    setTickRandomly(true);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return new AxisAlignedBB(0, 0, 0, 1, 1 - state.getValue(AGE) / 16f, 1);
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    Block b = worldIn.getBlockState(pos.down()).getBlock();
    return b == Blocks.GRASS || b == Blocks.DIRT;
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
      int age = worldIn.getBlockState(pos).getValue(AGE);

      if (age < 7) {
        if (rand.nextInt((int) (25 / getCoef(worldIn, pos)) + 1) == 0) {
          worldIn.setBlockState(pos, getDefaultState().withProperty(AGE, age + 1));
        }
      }
    }
  }

  /**
   * Calcule le coefficient permettant l'évolution du bloc.
   * 
   * @param world le monde
   * @param pos la position
   * @return le coefficient
   */
  private float getCoef(World world, BlockPos pos) {
    float coef = 1;
    Block north = world.getBlockState(pos.north()).getBlock();
    Block south = world.getBlockState(pos.south()).getBlock();
    Block east = world.getBlockState(pos.east()).getBlock();
    Block west = world.getBlockState(pos.west()).getBlock();
    Block northEast = world.getBlockState(pos.north().east()).getBlock();
    Block northWest = world.getBlockState(pos.north().west()).getBlock();
    Block southEast = world.getBlockState(pos.south().east()).getBlock();
    Block southWest = world.getBlockState(pos.south().west()).getBlock();
    boolean onX = east == this || west == this;
    boolean onZ = north == this || south == this;
    boolean onDiagonal = northEast == this || northWest == this || southEast == this || southWest == this;

    for (int x = pos.getX() - 1; x <= pos.getX() + 1; x++) {
      for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z++) {
        Block block = world.getBlockState(new BlockPos(x, pos.getY(), z)).getBlock();
        float i = 0;

        if (block == McfrBlocks.FERTILIZER)
          i = 3;
        if (x != pos.getX() || z != pos.getZ())
          i /= 4;
        coef += i;
      }
    }

    if (onDiagonal || onX && onZ) {
      coef /= 2;
    }

    if (world.getLightFromNeighbors(pos.up()) > 6) {
      coef /= 4;
    }

    return coef / 3;
  }

  @Override
  public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
    if (!worldIn.isRemote) {
      if (playerIn.getHeldItemMainhand() != null && playerIn.getHeldItemMainhand().getItem() instanceof ItemSpade) {
        Random random = new Random();
        Item item = McfrItems.POOP;

        if (worldIn.getBlockState(pos).getValue(AGE) == 7)
          item = McfrItems.FERTILIZER;

        EntityItem loot = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(item, random.nextInt(4) + 1));

        loot.setPickupDelay(10);
        worldIn.spawnEntityInWorld(loot);
      }
    }
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return null;
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(Item.getItemFromBlock(this));
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, AGE);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(AGE, meta & 7);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(AGE);
  }
}
