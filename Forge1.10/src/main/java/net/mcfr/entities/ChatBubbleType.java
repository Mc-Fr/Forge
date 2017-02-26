package net.mcfr.entities;

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