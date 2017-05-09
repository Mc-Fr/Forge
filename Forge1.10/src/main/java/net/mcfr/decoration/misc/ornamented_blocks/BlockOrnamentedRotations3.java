package net.mcfr.decoration.misc.ornamented_blocks;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.IEnumType;
import net.minecraft.block.properties.PropertyEnum;

/**
 * Bloc ornementé (3) avec rotations.
 *
 * @author Mc-Fr
 *
 * @param <T> le type des variantes
 */
public class BlockOrnamentedRotations3 extends BlockOrnamentedRotations<BlockOrnamentedRotations3.EnumType> implements IBlockWithVariants {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  /**
   * Crée un bloc ornementé de type 3 avec rotations.
   * 
   * @param color la couleur
   */
  public BlockOrnamentedRotations3(String color) {
    super(color, "3", EnumType.CORNER);
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
   * Variantes du bloc décoré 3 :
   * <ul>
   * <li>coin</li>
   * <li>croisement 0</li>
   * <li>croisement 1</li>
   * <li>croisement 2</li>
   * </ul>
   *
   * @author Mc-Fr
   */
  public enum EnumType implements IEnumType<EnumType> {
    CORNER("corner"),
    CROSSING_0("crossing_0"),
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
