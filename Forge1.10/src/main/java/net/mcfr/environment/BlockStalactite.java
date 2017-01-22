package net.mcfr.environment;

import java.util.List;
import java.util.Random;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStalactite extends McfrBlock implements IBlockWithVariants {
  public static final PropertyEnum<EnumBlockHalf> HALF = PropertyEnum.create("half", EnumBlockHalf.class);
  public static final PropertyEnum<EnumSize> SIZE = PropertyEnum.create("size", EnumSize.class);

  private static final AxisAlignedBB BOTTOM_HALF_AABB = new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
  private static final AxisAlignedBB TOP_HALF_AABB = new AxisAlignedBB(0, 0.5, 0, 1, 1, 1);

  public BlockStalactite(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel) {
    super(name + "_stalactite", material, sound, hardness, resistance, null, 0, CreativeTabs.DECORATIONS);
    if (tool != null) {
      setHarvestLevel(tool, harvestLevel);
    }
    setDefaultState(this.blockState.getBaseState().withProperty(SIZE, EnumSize.SMALL).withProperty(HALF, EnumBlockHalf.BOTTOM));
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return getBoundingBox(state);
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
    return getBoundingBox(state);
  }

  private AxisAlignedBB getBoundingBox(IBlockState state) {
    if (state.getValue(SIZE) == EnumSize.SMALL) {
      if (state.getValue(HALF) == EnumBlockHalf.BOTTOM)
        return BOTTOM_HALF_AABB;
      else
        return TOP_HALF_AABB;
    }

    return FULL_BLOCK_AABB;
  }

  @Override
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.CUTOUT_MIPPED;
  }

  @Override
  public String getVariantName(int meta) {
    return EnumSize.values()[meta & 7].getName();
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
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (EnumSize size : EnumSize.values()) {
      list.add(new ItemStack(itemIn, 1, size.getMetadata()));
    }
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(this, 1, getMetaFromState(state) & 3);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, HALF, SIZE);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState() //
        .withProperty(HALF, (meta & 4) == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP) //
        .withProperty(SIZE, EnumSize.byMetadata(meta & 3));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(SIZE).getMetadata() | (state.getValue(HALF) == EnumBlockHalf.BOTTOM ? 0 : 4);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return null;
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos.up()).getMaterial().isSolid() && !(up(worldIn, pos) instanceof BlockStalactite) || worldIn.getBlockState(pos.down()).getMaterial().isSolid() && !(down(worldIn, pos) instanceof BlockStalactite);
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    if (!canPlaceBlockAt(worldIn, pos)) {
      worldIn.setBlockToAir(pos);
    }
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(HALF, EnumBlockHalf.BOTTOM);
    return (facing == EnumFacing.UP || hitY <= 0.5) && !worldIn.isAirBlock(pos.down()) && !(down(worldIn, pos) instanceof BlockStalactite) //
        || (facing == EnumFacing.DOWN || hitY > 0.5) && (worldIn.isAirBlock(pos.up()) || up(worldIn, pos) instanceof BlockStalactite) //
            ? state : state.withProperty(HALF, EnumBlockHalf.TOP);
  }

  private Block up(World worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos.up()).getBlock();
  }

  private Block down(World worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos.down()).getBlock();
  }

  public static enum EnumSize implements IEnumType<EnumSize> {
    SMALL("small"),
    MEDIUM("medium"),
    BIG("big");
    private final String name;

    private EnumSize(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return this.name;
    }

    @Override
    public int getMetadata() {
      return ordinal();
    }

    @Override
    public String toString() {
      return getName();
    }

    public static EnumSize byMetadata(int meta) {
      return values()[meta % values().length];
    }
  }
}
