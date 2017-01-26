package net.mcfr.decoration.signs;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class McfrBlockWallSign extends McfrBlockSign {
  public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

  public McfrBlockWallSign(String name, Material material, SoundType sound, float hardness, String tool) {
    super(name, material, sound, hardness, tool);
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    EnumFacing enumfacing = state.getValue(FACING);
    float f = 0.28125F;
    float f1 = 0.78125F;
    float f2 = 0.0F;
    float f3 = 1.0F;
    float f4 = 0.125F;

    switch (enumfacing) {
      case NORTH:
        return new AxisAlignedBB(f2, f, 1.0F - f4, f3, f1, 1.0F);
      case SOUTH:
        return new AxisAlignedBB(f2, f, 0.0F, f3, f1, f4);
      case WEST:
        return new AxisAlignedBB(1.0F - f4, f, f2, 1.0F, f1, f3);
      case EAST:
        return new AxisAlignedBB(0.0F, f, f2, f4, f1, f3);
      default:
        return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
  }

  @Override
  public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
    World worldIn = (World) world;
    IBlockState state = worldIn.getBlockState(pos);
    EnumFacing enumfacing = state.getValue(FACING);

    if (!worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getMaterial().isSolid()) {
      dropBlockAsItem(worldIn, pos, state, 0);
      worldIn.setBlockToAir(pos);
    }

    super.onNeighborChange(worldIn, pos, neighbor);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    EnumFacing enumfacing = EnumFacing.getFront(meta);

    if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
      enumfacing = EnumFacing.NORTH;
    }

    return getDefaultState().withProperty(FACING, enumfacing);
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
