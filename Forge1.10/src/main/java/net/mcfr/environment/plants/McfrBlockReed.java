package net.mcfr.environment.plants;

import java.util.Random;

import net.mcfr.McfrItems;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class McfrBlockReed extends McfrBlock implements IPlantable {
  private static final int MAX_AGE = 15;
  private static final int MAX_HEIGHT = 3;

  public static final PropertyInteger AGE = PropertyInteger.create("age", 0, MAX_AGE);
  public static final PropertyBool TOP = PropertyBool.create("top");

  public McfrBlockReed() {
    super("reed_block", Material.PLANTS, SoundType.PLANT, 0, 0, null, -1, null);
    setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0).withProperty(TOP, true));
    setTickRandomly(true);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    float f = 0.375f, h = 0.5f;
    return new AxisAlignedBB(h - f, 0, h - f, h + f, 1, h + f);
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
    return NULL_AABB;
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, AGE, TOP);
  }

  @Override
  public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    return state.withProperty(TOP, worldIn.getBlockState(pos.up()).getBlock() != this);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(AGE, meta);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(AGE);
  }

  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(McfrItems.REEDS);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.REEDS;
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return getItem(world, pos, state);
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    if (!canBlockStay(worldIn, pos)) {
      dropBlockAsItem(worldIn, pos, state, 0);
      worldIn.setBlockToAir(pos);
    }
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (worldIn.isAirBlock(pos.up())) {
      int i;

      for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; i++);

      if (i < MAX_HEIGHT) {
        int j = state.getValue(AGE);

        if (j == MAX_AGE) {
          worldIn.setBlockState(pos.up(), getDefaultState());
          worldIn.setBlockState(pos, state.withProperty(AGE, 0), 4);
        }
        else {
          worldIn.setBlockState(pos, state.withProperty(AGE, j + 1), 4);
        }
      }
    }
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    Block block = worldIn.getBlockState(pos.down()).getBlock();
    if (block.canSustainPlant(worldIn.getBlockState(pos.down()), worldIn, pos.down(), EnumFacing.UP, this))
      return true;

    if (block == this) {
      return true;
    }
    else if (block != Blocks.GRASS && block != Blocks.DIRT && block != Blocks.SAND) {
      return false;
    }
    else {
      for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
        if (worldIn.getBlockState(pos.offset(enumfacing).down()).getMaterial() == Material.WATER) {
          return true;
        }
      }

      return false;
    }
  }

  public boolean canBlockStay(World worldIn, BlockPos pos) {
    return canPlaceBlockAt(worldIn, pos);
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.CUTOUT;
  }

  @Override
  public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
    return EnumPlantType.Beach;
  }

  @Override
  public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
    return getDefaultState();
  }
}
