package net.mcfr.decoration.furniture;

import net.mcfr.commons.McfrBlockOrientable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Classe de base pour toutes les chaises.
 *
 * @author Mc-Fr
 */
public abstract class BlockChair extends McfrBlockOrientable implements ITileEntityProvider {
  private static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);

  /**
   * Crée une chaise.
   * 
   * @param name le nom (sans le suffixe '_shelf')
   * @param material la matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance aux explosions
   * @param tool l'outil nécessaire
   * @param harvestLevel le niveau de récolte
   */
  public BlockChair(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel) {
    super(name, material, sound, hardness, resistance, tool, harvestLevel, CreativeTabs.DECORATIONS);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }
}
