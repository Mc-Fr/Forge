package net.mcfr.decoration.lighting;

import net.mcfr.commons.IEnumType;
import net.minecraft.item.EnumDyeColor;

/**
 * Couleurs des lanternes :
 * <ul>
 * <li>blanc</li>
 * <li>orange</li>
 * <li>jaune</li>
 * <li>violet</li>
 * <li>bleu</li>
 * <li>vert</li>
 * <li>rouge</li>
 * </ul>
 *
 * @author Mc-Fr
 */
public enum EnumLanternColor implements IEnumType<EnumLanternColor> {
  WHITE("white", EnumDyeColor.WHITE),
  ORANGE("orange", EnumDyeColor.ORANGE),
  YELLOW("yellow", EnumDyeColor.YELLOW),
  PURPLE("purple", EnumDyeColor.PURPLE),
  BLUE("blue", EnumDyeColor.BLUE),
  GREEN("green", EnumDyeColor.GREEN),
  RED("red", EnumDyeColor.RED);

  private final String name;
  private final EnumDyeColor color;

  private EnumLanternColor(String name, EnumDyeColor color) {
    this.name = name;
    this.color = color;
  }

  @Override
  public String getName() {
    return this.name;
  }

  public EnumDyeColor getDyeColor() {
    return this.color;
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
