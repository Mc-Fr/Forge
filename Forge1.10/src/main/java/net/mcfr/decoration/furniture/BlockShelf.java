package net.mcfr.decoration.furniture;

import net.mcfr.commons.McfrBlockOrientable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockShelf extends McfrBlockOrientable {
  public BlockShelf(String unlocalizedName, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel, CreativeTabs tab) {
    super(unlocalizedName + "_shelf", material, sound, hardness, resistance, tool, harvestLevel, tab);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return new AxisAlignedBB(0, 0.7f, 0, 1, 1, 1);
  }
}
