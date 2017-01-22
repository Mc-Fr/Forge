package net.mcfr.construction;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class McfrBlockPyramid extends McfrBlock {
  public static final PropertyEnum<EnumHalf> HALF = PropertyEnum.create("half", EnumHalf.class);

  private final Block modelBlock;

  @SuppressWarnings("deprecation")
  public McfrBlockPyramid(Block block, int metadata, float hardness, float resistance, String materialName, String tool, int harvestLevel) {
    super(materialName + "_pyramid", block.getDefaultState().getMaterial(), block.getSoundType(), hardness, resistance, tool, harvestLevel, CreativeTabs.BUILDING_BLOCKS);
    this.modelBlock = block;
    setDefaultState(this.blockState.getBaseState().withProperty(HALF, EnumHalf.BOTTOM));
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
    return this.modelBlock.getBlockLayer();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, HALF);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(HALF) == EnumHalf.BOTTOM ? 0 : 1;
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(HALF, (meta & 1) == 0 ? EnumHalf.BOTTOM : EnumHalf.TOP);
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
    return facing != EnumFacing.DOWN && (facing == EnumFacing.UP || hitY <= 0.5) ? state.withProperty(HALF, BlockStairs.EnumHalf.BOTTOM) : state.withProperty(HALF, BlockStairs.EnumHalf.TOP);
  }

  @Override
  public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
    return false;
  }
}
