package net.mcfr.decoration.signs;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class McfrBlockSuspendedSign extends McfrBlockSign {
  public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);

  public McfrBlockSuspendedSign(String name, Material material, SoundType sound, float hardness, String tool) {
    super(name, material, sound, hardness, tool);
    setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, 0));
  }

  @Override
  public void onNeighborChange(IBlockAccess worldIn, BlockPos pos, BlockPos neighbor) {
    if (!worldIn.getBlockState(pos.up()).getMaterial().isSolid()) {
      dropBlockAsItem((World) worldIn, pos, worldIn.getBlockState(pos), 0);
      ((World) worldIn).setBlockToAir(pos);
    }

    super.onNeighborChange(worldIn, pos, neighbor);
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos.up()).getMaterial().isSolid() && super.canPlaceBlockAt(worldIn, pos);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(ROTATION, meta);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(ROTATION).intValue();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, ROTATION);
  }
}
