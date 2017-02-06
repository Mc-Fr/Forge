package net.mcfr.construction;

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
 * Bloc de marbre.
 * 
 * @author Mc-Fr
 */
public class BlockMarble extends McfrBlock implements IBlockWithVariants {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  public BlockMarble() {
    super("marble", Material.ROCK, SoundType.STONE, 2.5f, 10, "pickaxe", 0, CreativeTabs.BUILDING_BLOCKS);
    setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.ROUGH_SAND));
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
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, VARIANT);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  /**
   * Variantes du bloc de marbre :
   * <ul>
   * <li>couleur sable, brut</li>
   * <li>couleur sable, sculpté</li>
   * <li>couleur sable, lisse</li>
   * <li>blanc, brut</li>
   * <li>blanc, sculpté</li>
   * <li>blanc, lisse</li>
   * <li>noir, brut</li>
   * <li>noir, sculpté</li>
   * <li>noir, lisse</li>
   * </ul>
   * 
   * @author Mc-Fr
   */
  public static enum EnumType implements IEnumType<EnumType> {
    ROUGH_SAND("rough_sand"),
    CHISELED_SAND("chiseled_sand"),
    SMOOTH_SAND("smooth_sand"),
    ROUGH_WHITE("rough_white"),
    CHISELED_WHITE("chiseled_white"),
    SMOOTH_WHITE("smooth_white"),
    ROUGH_BLACK("rough_black"),
    CHISELED_BLACK("chiseled_black"),
    SMOOTH_BLACK("smooth_black");

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
