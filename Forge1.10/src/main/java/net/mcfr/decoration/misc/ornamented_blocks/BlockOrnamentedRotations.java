package net.mcfr.decoration.misc.ornamented_blocks;

import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrBlockOrientable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Bloc ornementé (1) avec rotations.
 *
 * @author Mc-Fr
 *
 * @param <T> le type des variantes
 */
public abstract class BlockOrnamentedRotations<T extends Enum<T> & IEnumType<T>> extends McfrBlockOrientable implements IBlockWithVariants {
  /**
   * Crée un bloc ornementé avec rotations.
   * 
   * @param color la couleur
   * @param name le nom
   * @param defaultVariant la variante par défaut
   */
  public BlockOrnamentedRotations(String color, String name, T defaultVariant) {
    super(color + "_ornate_block_" + name, Material.ROCK, SoundType.STONE, 2, 5, "pickaxe", 0, CreativeTabs.DECORATIONS);
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(getVariantProperty(), defaultVariant));
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta << 2).getValue(getVariantProperty()).getName();
  }

  /**
   * @return la propriété des variantes
   */
  protected abstract PropertyEnum<T> getVariantProperty();

  /**
   * Retourne la variante correspondant au metadata donné.
   * 
   * @param meta le metadata
   * @return la variante
   */
  protected abstract T byMetadata(int meta);

  /**
   * @return le nombre de variantes
   */
  protected abstract int variantsNumber();

  @Override
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < variantsNumber(); i++) {
      list.add(new ItemStack(itemIn, 1, i));
    }
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(this, 1, damageDropped(state));
  }

  @Override
  public int damageDropped(IBlockState state) {
    return state.getValue(getVariantProperty()).getMetadata();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING, getVariantProperty());
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return super.getStateFromMeta(meta).withProperty(getVariantProperty(), byMetadata((meta >> 2) & 3));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return super.getMetaFromState(state) | (state.getValue(getVariantProperty()).getMetadata() << 2);
  }
}
