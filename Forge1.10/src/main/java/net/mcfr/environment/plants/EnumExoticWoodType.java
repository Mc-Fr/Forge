package net.mcfr.environment.plants;

import net.mcfr.commons.IEnumType;

/**
 * Types de bois "exotiques" :
 * <ul>
 * <li>pommier</li>
 * <li>cerisier</li>
 * <li>palmier</li>
 * <li>béluxier</li>
 * </ul>
 *
 * @author Mc-Fr
 */
public enum EnumExoticWoodType implements IEnumType<EnumExoticWoodType> {
  APPLE_TREE("apple"),
  CHERRY_TREE("cherry"),
  PALM_TREE("palm"),
  BELUXIER("beluxier");

  private String name;

  private EnumExoticWoodType(String name) {
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

  public static EnumExoticWoodType byMetadata(int meta) {
    return values()[meta % values().length];
  }
}
