package net.mcfr.decoration.misc;

import net.mcfr.commons.IEnumType;

/**
 * Types de moucharabieh :
 * <ul>
 * <li>pierre</li>
 * <li>bois</li>
 * <li>fer</li>
 * </ul>
 * 
 * @author Mc-Fr
 */
public enum EnumMoucharabiehType implements IEnumType<EnumMoucharabiehType> {
  STONE("stone"),
  WOOD("wood"),
  IRON("iron");

  private final String name;

  private EnumMoucharabiehType(String name) {
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

  public static EnumMoucharabiehType byMetadata(int meta) {
    return values()[meta % values().length];
  }
}
