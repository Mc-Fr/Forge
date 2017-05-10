package net.mcfr.entities;

/**
 * Types de bulles de tchat :
 * <ul>
 * <li>dialogue</li>
 * <li>commande</li>
 * <li>action</li>
 * <li>HRP</li>
 * <li>rien</li>
 * </ul>
 *
 * @author Mc-Fr
 */
public enum ChatBubbleType {
  TALKING,
  COMMAND,
  ACTION,
  ORP,
  NONE;

  public boolean isNone() {
    return this == NONE;
  }

  public static ChatBubbleType byOrdinal(int ordinal) {
    return values()[ordinal];
  }
}