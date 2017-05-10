package net.mcfr.decoration.misc;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Échiquier.
 *
 * @author Mc-Fr
 */
public class BlockChessboard extends McfrBlock {
  public BlockChessboard() {
    super("chessboard", Material.WOOD, SoundType.WOOD, 0.2f, 0, null, -1, CreativeTabs.DECORATIONS);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return new AxisAlignedBB(1 / 16f, 0, 1 / 16f, 15 / 16f, 1 / 32f, 15 / 16f);
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return super.canPlaceBlockAt(worldIn, pos) && canBlockStay(worldIn, pos);
  }

  @Override
  public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
    World worldIn = (World) world;
    IBlockState state = worldIn.getBlockState(pos);
    checkForDrop(worldIn, pos, state);
  }

  /**
   * Vérifie si le bloc peut rester à sa position.
   * 
   * @param world le monde
   * @param pos la position
   * @param state l'état
   * @return true si le bloc n'a pas été supprimé
   */
  private boolean checkForDrop(World world, BlockPos pos, IBlockState state) {
    if (!canBlockStay(world, pos)) {
      dropBlockAsItem(world, pos, state, 0);
      world.setBlockToAir(pos);

      return false;
    }
    return true;
  }

  /**
   * Indique si le bloc peut rester à son emplacement.
   * 
   * @param world le monde
   * @param pos la position
   * @return true si le bloc peut rester
   */
  private boolean canBlockStay(World world, BlockPos pos) {
    return !world.isAirBlock(pos.down()) && world.getBlockState(pos.down()).getBlock() != this;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }
}
