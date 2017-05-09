package net.mcfr.decoration.misc.ornamented_blocks;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.IEnumType;
import net.minecraft.block.properties.PropertyEnum;

/**
 * Bloc ornementé (1) avec rotations.
 *
 * @author Mc-Fr
 *
 * @param <T> le type des variantes
 */
public class BlockOrnamentedRotations1 extends BlockOrnamentedRotations<BlockOrnamentedRotations1.EnumType> implements IBlockWithVariants {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  /**
   * Crée un bloc ornementé de type 1 avec rotations.
   * 
   * @param color la couleur
   */
  public BlockOrnamentedRotations1(String color) {
    super(color, "1", EnumType.CIRCLE_CORNER);
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
   * Variantes du bloc décoré 1 :
   * <ul>
   * <li>cercle - angle</li>
   * <li>cercle - bord</li>
   * <li>motif en diagonale</li>
   * <li>motif droit</li>
   * </ul>
   *
   * @author Mc-Fr
   */
  public enum EnumType implements IEnumType<EnumType> {
    CIRCLE_CORNER("circle_corner"),
    CIRCLE_EDGE("circle_edge"),
    DIAGONAL_PATTERN("diagonal_pattern"),
    STRAIGHT_PATTERN("straight_pattern");

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
