package net.mcfr.environment.plants;

import java.util.Random;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSpores extends McfrBlock {
  public BlockSpores() {
    super("spores", Material.GLASS, SoundType.GLASS, 2, 3, null, -1, CreativeTabs.BUILDING_BLOCKS);
    setLightLevel(1);
    setTickRandomly(true);
  }

  @Override
  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    float d = 5;
    for (int i = 0; i < 5; i++) {
      worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, pos.getX() + rand.nextDouble() * d - d / 2, pos.getY() + rand.nextDouble() * d - d / 2,
          pos.getZ() + rand.nextDouble() * d - d / 2, 0, 0, 0);
    }
  }
}
