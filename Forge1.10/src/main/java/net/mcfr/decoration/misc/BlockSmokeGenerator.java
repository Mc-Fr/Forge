package net.mcfr.decoration.misc;

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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Bloc générateur de fumée.
 *
 * @author Mc-Fr
 */
public class BlockSmokeGenerator extends McfrBlock {
  public BlockSmokeGenerator() {
    super("smoke_generator", Material.ROCK, SoundType.WOOD, 1.5f, 10, "pickaxe", 0, CreativeTabs.DECORATIONS);
    setTickRandomly(true);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return new AxisAlignedBB(0.3F, 0, 0.3F, 0.7F, 0.4F, 0.7F);
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
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    for (int i = 0; i < 10; i++) {
      worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + rand.nextDouble(), pos.getY() + 0.4 + rand.nextDouble(),
          pos.getZ() + rand.nextDouble(), 0, 0, 0);
    }
    worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5, 0, 0, 0);
  }
}
