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
 * Muret de marbre.
 * 
 * @author Mc-Fr
 */
public class BlockMarbleWall extends McfrBlockWall {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  public BlockMarbleWall() {
    super("marble", Material.ROCK, SoundType.STONE, 1.5f, 10, "pickaxe", 0);
    setDefaultState(getDefaultState().withProperty(VARIANT, EnumType.SMOOTH_SAND));
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
   * Variantes du muret de marbre :
   * <ul>
   * <li>couleur sable, lisse</li>
   * <li>couleur sable, colonne</li>
   * <li>blanc, lisse</li>
   * <li>blanc, colonne</li>
   * <li>noir, lisse</li>
   * <li>noir, colonne</li>
   * </ul>
   * 
   * @author Mc-Fr
   */
  public static enum EnumType implements IEnumType<EnumType> {
    SMOOTH_SAND("smooth_sand"),
    COLUMN_SAND("column_sand"),
    SMOOTH_WHITE("smooth_white"),
    COLUMN_WHITE("column_white"),
    SMOOTH_BLACK("smooth_black"),
    COLUMN_BLACK("column_black");

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
