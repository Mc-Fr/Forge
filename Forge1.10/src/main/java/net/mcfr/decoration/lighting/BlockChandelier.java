package net.mcfr.decoration.lighting;

import java.util.Random;

import net.mcfr.commons.McfrBlock;
import net.mcfr.decoration.lighting.tile_entities.TileEntityChandelier;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Lustre.
 *
 * @author Mc-Fr
 */
public class BlockChandelier extends McfrBlock implements ITileEntityProvider {
  /** Indique si le lustre est grand ou non */
  private boolean isLarge;

  /**
   * Crée un lustre.
   * 
   * @param isLarge grand ou non
   */
  public BlockChandelier(boolean isLarge) {
    super((isLarge ? "large_" : "") + "chandelier", Material.WOOD, SoundType.WOOD, 1, 0, "axe", 0, CreativeTabs.DECORATIONS);
    setLightLevel(isLarge ? 0.75f : 0.875f);
    setTickRandomly(true);
    this.isLarge = isLarge;
  }

  @Override
  public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
    double x = pos.getX() + 0.5;
    double y = pos.getY() + 0.73;
    double z = pos.getZ() + 0.5;

    if (this.isLarge) {
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.85, y, z, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x - 0.85, y, z, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x, y, z + 0.85, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x, y, z - 0.85, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.6, y, z + 0.6, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x - 0.6, y, z + 0.6, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.6, y, z - 0.6, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x - 0.6, y, z - 0.6, 0, 0, 0);
    }
    else {
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.65, y, z, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x - 0.65, y, z, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x, y, z + 0.65, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x, y, z - 0.65, 0, 0, 0);
    }
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityChandelier(this.isLarge);
  }
}
