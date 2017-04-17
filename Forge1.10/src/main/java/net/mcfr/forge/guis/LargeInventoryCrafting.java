package net.mcfr.forge.guis;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

public class LargeInventoryCrafting extends InventoryCrafting {
  private boolean isAnvil;

  public LargeInventoryCrafting(Container eventHandlerIn, int width, int height) {
    super(eventHandlerIn, width, height);
    this.isAnvil = false;
  }

  public void setAnvil() {
    this.isAnvil = true;
  }

  public boolean isAnvil() {
    return this.isAnvil;
  }
}
