package net.mcfr.decoration.misc;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Classe représentant une chaîne/corde à laquelle on peut grimper.
 *
 * @author Mc-Fr
 */
public class BlockChain extends McfrBlock {
  /** Hitbox */
  private static final AxisAlignedBB AABB = new AxisAlignedBB(0.35, 0, 0.35, 0.65, 1, 0.65);

  /**
   * Crée une chaîne.
   * 
   * @param type le type (nom du block)
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   */
  public BlockChain(String type, Material material, SoundType sound, float hardness) {
    super(type, material, sound, hardness, 0, null, -1, CreativeTabs.DECORATIONS);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
  }

  @Override
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.CUTOUT_MIPPED;
  }

  @Override
  public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
    return true;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }
}
