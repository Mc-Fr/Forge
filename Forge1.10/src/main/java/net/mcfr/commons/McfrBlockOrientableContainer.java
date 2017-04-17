package net.mcfr.commons;

import net.mcfr.decoration.container_blocks.McfrBlockContainer;
import net.mcfr.utils.FacingUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class McfrBlockOrientableContainer<T extends TileEntity & IInventory> extends McfrBlockContainer<T> {
  public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

  public McfrBlockOrientableContainer(String name, Material material, SoundType sound, float hardness, float resistance, String tool,
      Class<T> teClass) {
    this(name, material, sound, hardness, resistance, tool, teClass, CreativeTabs.DECORATIONS);
  }

  public McfrBlockOrientableContainer(String name, Material material, SoundType sound, float hardness, float resistance, String tool,
      Class<T> teClass, CreativeTabs creativeTab) {
    super(name, material, sound, hardness, resistance, tool, teClass, creativeTab);
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
      EntityLivingBase placer) {
    return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, FacingUtils.getHorizontalFacing(placer));
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(FACING, EnumFacing.Plane.HORIZONTAL.facings()[meta & 3]);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getHorizontalIndex();
  }
}
