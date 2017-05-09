package net.mcfr.decoration.misc.ornamented_blocks;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.IEnumType;
import net.minecraft.block.properties.PropertyEnum;

/**
 * Bloc ornementé (2) avec rotations.
 *
 * @author Mc-Fr
 *
 * @param <T> le type des variantes
 */
public class BlockOrnamentedRotations2 extends BlockOrnamentedRotations<BlockOrnamentedRotations2.EnumType> implements IBlockWithVariants {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  /**
   * Crée un bloc ornementé de type 2 avec rotations.
   * 
   * @param color la couleur
   */
  public BlockOrnamentedRotations2(String color) {
    super(color, "2", EnumType.LINE_END);
  }

  @Override
  protected PropertyEnum<EnumType> getVariantProperty() {
    return VARIANT;
  }

  @Override
  protected EnumType byMetadata(int meta) {
    return EnumType.byMetadata(meta);
  }

  @Override
  protected int variantsNumber() {
    return EnumType.values().length;
  }

  /**
   * Variantes du bloc décoré 2 :
   * <ul>
   * <li>ligne droite</li>
   * <li>3/4 de cercle</li>
   * <li>croisement 1</li>
   * <li>croisement 2</li>
   * </ul>
   *
   * @author Mc-Fr
   */
  public enum EnumType implements IEnumType<EnumType> {
    LINE_END("line_end"),
    CIRCLE_3_QUARTERS("circle_3_quarters"),
    CROSSING_1("crossing_1"),
    CROSSING_2("crossing_2");

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
