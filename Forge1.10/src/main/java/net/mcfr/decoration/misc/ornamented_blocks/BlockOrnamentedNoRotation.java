package net.mcfr.decoration.misc.ornamented_blocks;

import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Bloc ornementé sans rotation.
 *
 * @author Mc-Fr
 */
public class BlockOrnamentedNoRotation extends McfrBlock implements IBlockWithVariants {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  /**
   * Crée un bloc.
   * 
   * @param color la couleur
   * @param name le nom
   */
  public BlockOrnamentedNoRotation(String color) {
    super(color + "_ornate_block_nr", Material.ROCK, SoundType.STONE, 2, 5, "pickaxe", 0, CreativeTabs.DECORATIONS);
    setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.STAR));
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(VARIANT).getName();
  }

  @Override
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < EnumType.values().length; i++) {
      list.add(new ItemStack(itemIn, 1, i));
    }
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(this, 1, getMetaFromState(state));
  }

  @Override
  public int damageDropped(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, VARIANT);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta & 7));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  /**
   * Variantes du bloc ornementé sans rotation :
   * <ul>
   * <li>étoile</li>
   * <li>crois droite</li>
   * <li>centre (du motif en forme de cercle)</li>
   * </ul>
   * 
   * @author Mc-Fr
   */
  public static enum EnumType implements IEnumType<EnumType> {
    STAR("star"),
    CROSS("cross"),
    CIRCLE_CENTER("circle_center");

    private final String name;

    private EnumType(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return this.name;
    }

    @Override
    public int getMetadata() {
      return ordinal();
    }

    @Override
    public String toString() {
      return getName();
    }

    public static EnumType byMetadata(int meta) {
      return values()[meta % values().length];
    }
  }
}
