package net.mcfr.decoration.signs;

import net.minecraft.block.Block;
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

/**
 * Classe de base des panneaux muraux.
 *
 * @author Mc-Fr
 */
public abstract class McfrBlockWallSign extends McfrBlockSign {
  public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

  /**
   * Crée un panneau mural.
   * 
   * @param name le nom
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param tool l'outil nécessaire
   */
  public McfrBlockWallSign(String name, Material material, SoundType sound, float hardness, String tool) {
    super(name, material, sound, hardness, tool);
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    float yMin = 0.28125F;
    float yMax = 0.78125F;
    float w = 0.125F;

    switch (state.getValue(FACING)) {
      case NORTH:
        return new AxisAlignedBB(0, yMin, 1 - w, 1, yMax, 1);
      case SOUTH:
        return new AxisAlignedBB(0, yMin, 0, 1, yMax, w);
      case WEST:
        return new AxisAlignedBB(1 - w, yMin, 0, 1, yMax, 1);
      case EAST:
        return new AxisAlignedBB(0, yMin, 0, w, yMax, 1);
      default:
        return FULL_BLOCK_AABB;
    }
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    EnumFacing facing = state.getValue(FACING);

    if (!worldIn.getBlockState(pos.offset(facing.getOpposite())).getMaterial().isSolid()) {
      dropBlockAsItem(worldIn, pos, state, 0);
      worldIn.setBlockToAir(pos);
    }
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
