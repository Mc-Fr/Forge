package net.mcfr.decoration.furniture;

import net.mcfr.commons.McfrBlockOrientable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Étagère.
 *
 * @author Mc-Fr
 */
public class BlockShelf extends McfrBlockOrientable {
  private static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0.7, 0, 1, 1, 1);

  /**
   * Crée une étagère.
   * 
   * @param name le nom (sans le suffixe '_shelf')
   * @param material la matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance aux explosions
   * @param tool l'outil nécessaire
   * @param harvestLevel le niveau de récolte
   */
  public BlockShelf(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel) {
    super(name + "_shelf", material, sound, hardness, resistance, tool, harvestLevel, null);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
  }
}
