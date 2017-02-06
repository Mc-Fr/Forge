package net.mcfr.construction;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Pentes de feuilles.
 * 
 * @author Mc-Fr
 */
public class McfrBlockLeavesSlope extends McfrBlockSlope {
  /**
   * Crée une nouvelle pente de feuilles.
   * 
   * @param block le bloc de base
   * @param metadata le metadta du bloc de base
   * @param name le nom (sans le suffixe '_leaves_slope')
   */
  public McfrBlockLeavesSlope(Block block, int metadata, String name) {
    super(block, metadata, name + "_leaves", null, -1);
  }

  @Override
  public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
    return false;
  }
}
