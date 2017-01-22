package net.mcfr.decoration.lighting;

import net.mcfr.commons.IEnumType;

public enum EnumLanternColor implements IEnumType<EnumLanternColor> {
  WHITE("white"),
  ORANGE("orange"),
  YELLOW("yellow"),
  PURPLE("purple"),
  BLUE("blue"),
  GREEN("green"),
  RED("red");
  
  private final String name;
  
  private EnumLanternColor(String name) {
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
  
  public static EnumLanternColor byMetadata(int meta) {
    return values()[meta % values().length];
  }
}
