package net.mcfr.craftsmanship;

import java.lang.reflect.InvocationTargetException;

import net.mcfr.craftsmanship.tile_entities.TileEntityRack;
import net.mcfr.decoration.container_blocks.McfrBlockContainer;
import net.mcfr.utils.FacingUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Classe de base des blocs artisan.
 * 
 * @author Mc-Fr
 *
 * @param <T> le type de la tile entity asscoiée
 */
public abstract class BlockRack<T extends TileEntityRack> extends McfrBlockContainer<T> {
  public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

  /**
   * Crée un nouveau bloc artisan.
   * 
   * @param name le nom
   * @param teClass la classe de la tile entity. Elle doit posséder un constructeur prenant un seul
   *          argument, de type EnumFacing
   */
  public BlockRack(String name, Class<T> teClass) {
    super(name, Material.WOOD, SoundType.WOOD, 1, 3, "axe", teClass);
  }

  @Override
  public T createNewTileEntity(World worldIn, int meta) {
    T te = null;

    try {
      te = getTileEntityClass().getConstructor(EnumFacing.class).newInstance(getStateFromMeta(meta).getValue(FACING));
    }
    catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}

    return te;
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
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.CUTOUT_MIPPED;
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getHorizontalIndex();
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(FACING, EnumFacing.Plane.HORIZONTAL.facings()[meta & 3]);
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    worldIn.setBlockState(pos, state.withProperty(FACING, FacingUtils.getHorizontalFacing(placer)));
  }
}
