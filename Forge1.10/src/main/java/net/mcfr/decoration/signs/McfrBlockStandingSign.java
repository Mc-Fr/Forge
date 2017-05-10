package net.mcfr.decoration.signs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Classe de base des panneaux droits.
 *
 * @author Mc-Fr
 */
public abstract class McfrBlockStandingSign extends McfrBlockSign {
  public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);

  /**
   * Crée un panneau droit.
   * 
   * @param name le nom
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param tool l'outil nécessaire
   */
  public McfrBlockStandingSign(String name, Material material, SoundType sound, float hardness, String tool) {
    super(name, material, sound, hardness, tool);
    setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, 0));
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    if (!worldIn.getBlockState(pos.down()).getMaterial().isSolid()) {
      dropBlockAsItem(worldIn, pos, state, 0);
      worldIn.setBlockToAir(pos);
    }
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos.down()).getMaterial().isSolid() && super.canPlaceBlockAt(worldIn, pos);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(ROTATION, meta);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(ROTATION).intValue();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, ROTATION);
  }
}
