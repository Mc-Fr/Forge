package net.mcfr.decoration.misc;

import javax.annotation.Nullable;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDecoration extends McfrBlock {
  /** Hitbox */
  private static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0, 0, 1, 0.3, 1);

  /**
   * Crée une chaîne.
   * 
   * @param type le type (nom du block)
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   */
  public BlockDecoration(String name, Material material, SoundType sound, float hardness) {
    super(name, material, sound, hardness, 0, null, -1, CreativeTabs.DECORATIONS);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
  }
  
  @Nullable
  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
      return NULL_AABB;
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
  public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
      return true;
  }
}
