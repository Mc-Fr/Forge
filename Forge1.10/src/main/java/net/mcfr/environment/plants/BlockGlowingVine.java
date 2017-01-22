package net.mcfr.environment.plants;

import java.util.Random;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlowingVine extends McfrBlock {
  public static final PropertyEnum<EnumFacing> FACING = BlockDirectional.FACING;

  private static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0, 0.9375, 0, 1, 1, 1);
  private static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.0625, 1);
  private static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0, 0, 0, 0.0625, 1, 1);
  private static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.9375, 0, 0, 1, 1, 1);
  private static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 0.0625);
  private static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0, 0, 0.9375, 1, 1, 1);

  public BlockGlowingVine() {
    super("glowing_vine", Material.PLANTS, SoundType.PLANT, 0, 0, null, -1, CreativeTabs.DECORATIONS);
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    setLightLevel(0.6875f);
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
    return NULL_AABB;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    switch (state.getValue(FACING)) {
      case UP:
        return UP_AABB;
      case DOWN:
        return DOWN_AABB;
      case NORTH:
        return NORTH_AABB;
      case SOUTH:
        return SOUTH_AABB;
      case EAST:
        return EAST_AABB;
      case WEST:
        return WEST_AABB;
    }

    return null;
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
  public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
    return true;
  }

  @Override
  public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
    switch (side) {
      case UP:
        return canAttachVineOn(worldIn.getBlockState(pos.down()));
      case DOWN:
        return canAttachVineOn(worldIn.getBlockState(pos.up()));
      case NORTH:
      case SOUTH:
      case EAST:
      case WEST:
        return canAttachVineOn(worldIn.getBlockState(pos.offset(side.getOpposite())));
      default:
        return false;
    }
  }

  private boolean canAttachVineOn(IBlockState state) {
    return state.isFullCube() && state.getMaterial().blocksMovement();
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    return getDefaultState().withProperty(FACING, facing.getOpposite());
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    boolean drop = false;
    IBlockState s;

    switch (state.getValue(FACING)) {
      case UP:
        s = worldIn.getBlockState(pos.up());
        drop = !s.isFullCube() || !s.getMaterial().blocksMovement();
        break;
      case DOWN:
        s = worldIn.getBlockState(pos.down());
        drop = !s.isFullCube() || !s.getMaterial().blocksMovement();
        break;
      case NORTH:
        s = worldIn.getBlockState(pos.north());
        drop = !s.isFullCube() || !s.getMaterial().blocksMovement();
        break;
      case SOUTH:
        s = worldIn.getBlockState(pos.south());
        drop = !s.isFullCube() || !s.getMaterial().blocksMovement();
        break;
      case WEST:
        s = worldIn.getBlockState(pos.west());
        drop = !s.isFullCube() || !s.getMaterial().blocksMovement();
        break;
      case EAST:
        s = worldIn.getBlockState(pos.east());
        drop = !s.isFullCube() || !s.getMaterial().blocksMovement();
        break;
    }

    if (drop)
      worldIn.setBlockToAir(pos);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return null;
  }

  @Override
  public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
    if (!worldIn.isRemote && stack != null && stack.getItem() == Items.SHEARS) {
      spawnAsEntity(worldIn, pos, new ItemStack(this));
    }
    else {
      super.harvestBlock(worldIn, player, pos, state, te, stack);
    }
  }

  @Override
  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.CUTOUT;
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getIndex();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING);
  }
}
