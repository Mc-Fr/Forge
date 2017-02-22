package net.mcfr.decoration.lighting;

import java.util.Random;

import net.mcfr.commons.McfrBlock;
import net.mcfr.utils.math.Point3d;
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
 * Bloc de base des bougies.
 *
 * @author Mc-Fr
 */
public abstract class BlockCandle extends McfrBlock {
  /** Les coordonnées des flammes */
  private final Point3d[] flames;

  /**
   * Crée une bougie.
   * 
   * @param name le nom
   * @param lightLevel le niveau d'éclairement
   * @param flamesCoord les coordonées des flammes
   */
  public BlockCandle(String name, float lightLevel, Point3d... flamesCoord) {
    super(name + "_candle", Material.WOOD, SoundType.WOOD, 0, 0, null, 0, CreativeTabs.DECORATIONS);
    setLightLevel(lightLevel);
    this.flames = flamesCoord;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
    for (Point3d flame : this.flames) {
      for (int i = 0; i < 2; i++) {
        worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + flame.getX(), pos.getY() + flame.getY(), pos.getZ() + flame.getZ(), 0, 0, 0);
      }
    }
  }

  @SuppressWarnings("deprecation")
  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos) {
    return getBoundingBox(state, world, pos);
  }

  @Override
  public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
    return true;
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
