package net.mcfr.decoration.furniture.tileEntities;

public interface ClickableTileEntity {
  static final int DEFAULT_COOLDOWN = 20;

  long getLastClickTime();

  default int getCoolDown() {
    return DEFAULT_COOLDOWN;
  }

  default boolean canPlayerInteract() {
    return getLastClickTime() < System.currentTimeMillis() - getCoolDown();
  }
}
