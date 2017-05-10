package net.mcfr.forge.guis;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;

/**
 * Cette classe permet fournit un moyen d'avoir une matrice de craft de dimensions voulues.
 *
 * @author Mc-Fr
 */
public class LargeInventoryCrafting extends InventoryCrafting {
  private boolean isAnvil;

  /**
   * Crée une matrice de craft aux dimensions désirées.
   * 
   * @param eventHandler le conteneur
   * @param width la largeur
   * @param height la hauteur
   */
  public LargeInventoryCrafting(Container eventHandler, int width, int height) {
    super(eventHandler, width, height);
    this.isAnvil = false;
  }

  /**
   * Transforme cette matrice en matrice d'enclume.
   */
  public void setAnvil() {
    this.isAnvil = true;
  }

  /**
   * @return true si cette matrice est celle d'une enclume
   */
  public boolean isAnvil() {
    return this.isAnvil;
  }
}
