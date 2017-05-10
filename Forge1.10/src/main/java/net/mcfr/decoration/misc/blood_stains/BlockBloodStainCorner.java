package net.mcfr.decoration.misc.blood_stains;

import net.mcfr.McfrItems;
import net.mcfr.commons.McfrBlockOrientable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Bloc de sang en forme d'angle.
 *
 * @author Mc-Fr
 */
public class BlockBloodStainCorner extends McfrBlockOrientable {
  private static final AxisAlignedBB FLOOR_AABB = new AxisAlignedBB(0, 0, 0, 1, 0, 1);

  /**
   * Crée un bloc de sang en angle.
   * 
   * @param name le nom
   */
  public BlockBloodStainCorner() {
    super("blood_stain_corner", Material.CIRCUITS, SoundType.STONE, 0, 0, null, -1, null);
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullBlock(IBlockState state) {
    return false;
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World world, BlockPos pos) {
    return NULL_AABB;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return FLOOR_AABB;
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(McfrItems.BLOOD_STAIN, 1, ItemBlood.EnumType.TRAIL_CORNER.getMetadata());
  }

  @Override
  public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
    return side == EnumFacing.UP && world.isSideSolid(pos.down(), EnumFacing.UP);
  }
}
