package net.mcfr.decoration.lighting;

import java.util.Random;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBrazier extends McfrBlock {
  private final boolean lit;

  public BlockBrazier(boolean lit) {
    super((lit ? "lit_" : "") + "brazier", Material.ROCK, SoundType.STONE, 2, 5, "pickaxe", 0, CreativeTabs.DECORATIONS);

    this.lit = lit;
    if (lit)
      setLightLevel(0.9375f);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
  }

  @Override
  public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
    if (this.lit) {
      for (int i = 0; i < 2; i++) {
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + rand.nextDouble(), pos.getY() + rand.nextDouble(), pos.getZ() + rand.nextDouble(), 0, 0, 0);
      }
      for (int i = 0; i < 2; i++) {
        worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5, 0, 0, 0);
      }
    }
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullBlock(IBlockState state) {
    return false;
  }
}
