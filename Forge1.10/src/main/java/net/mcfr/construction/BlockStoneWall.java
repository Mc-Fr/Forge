package net.mcfr.construction;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Muret en prierre.
 *
 * @author Mc-Fr
 */
public class BlockStoneWall extends McfrBlockWall {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  public BlockStoneWall() {
    super("stone", Material.ROCK, SoundType.STONE, 1.5f, 10, "pickaxe", 0);
    setDefaultState(getDefaultState().withProperty(VARIANT, EnumType.STONEBRICK));
  }

  @Override
  public String getVariantName(int meta) {
    return EnumType.byMetadata(meta).getName();
  }

  @Override
  protected PropertyEnum<? extends Enum<?>> getVariantProperty() {
    return VARIANT;
  }

  @Override
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < EnumType.values().length; i++) {
      list.add(new ItemStack(itemIn, 1, i));
    }
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
   * Variantes du muret en pierre :
   * <ul>
   * <li>stonebrick</li>
   * <li>stonebrick moussue</li>
   * <li>stonebrick craquelée</li>
   * <li>pierre jaune</li>
   * <li>pierre ocre</li>
   * <li>stonebrick jaune</li>
   * <li>stonebrick jaune moussue</li>
   * <li>stonebrick jaune craquelée</li>
   * <li>stonebrick ocre</li>
   * <li>stonebrick ocre moussue</li>
   * <li>stonebrick ocre craquelée</li>
   * <li>argile sculptée claire</li>
   * <li>argile sculptée foncée</li>
   * <li>sandstone</li>
   * </ul>
   *
   * @author Mc-Fr
   */
  public static enum EnumType implements IEnumType<EnumType> {
    STONEBRICK("stonebrick"),
    MOSSY_STONEBRICK("mossy_stonebrick"),
    CRACKED_STONEBRICK("cracked_stonebrick"),
    YELLOW_COBBLE("yellow_cobblestone"),
    OCHER_COBBLE("ocher_cobblestone"),
    YELLOW_STONEBRICK("yellow_stonebrick"),
    YELLOW_MOSSY_STONEBRICK("yellow_mossy_stonebrick"),
    YELLOW_CRACKED_STONEBRICK("yellow_cracked_stonebrick"),
    OCHER_STONEBRICK("ocher_stonebrick"),
    OCHER_MOSSY_STONEBRICK("ocher_mossy_stonebrick"),
    OCHER_CRACKED_STONEBRICK("ocher_cracked_stonebrick"),
    LIGHT_CARVED_CLAY("light_carved_clay"),
    DARK_CARVED_CLAY("dark_carved_clay"),
    SANDSTONE("sandstone");

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
