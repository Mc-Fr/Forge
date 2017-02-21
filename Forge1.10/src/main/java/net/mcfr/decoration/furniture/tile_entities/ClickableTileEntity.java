package net.mcfr.decoration.furniture.tile_entities;

import net.minecraft.item.ItemStack;

/**
 * Une tile entity cliquable possède un temps de repos avant de pouvoir être utilisée de nouveau.
 * Cela permet d'éviter plusieurs clics simultanés.
 *
 * @author Mc-Fr
 */
public interface ClickableTileEntity {
  /** Temps de repos par défaut (1s) */
  static final int DEFAULT_COOLDOWN = 20;

  /**
   * @return le temps du dernier clic
   */
  long getLastClickTime();

  /**
   * @return le temps de repos
   */
  default int getCoolDown() {
    return DEFAULT_COOLDOWN;
  }

  /**
   * Indique si le joueur peut interagir avec cette tile entity.
   * 
   * @return vrai si le joueur peu interagir, faux sinon
   */
  default boolean canPlayerInteract() {
    return getLastClickTime() < System.currentTimeMillis() - getCoolDown();
  }

  /**
   * Retourne l'item présent à l'emplacement donné.
   * 
   * @param slot l'indice
   * @return l'item ou null si l'emplacement est vide
   */
  ItemStack getItem(int slot);

  /**
   * Modifie l'item à l'emplacement donné.
   * 
   * @param stack le nouvel item ou null
   * @param slot l'indice
   * @return vrai si l'action a été prise en compte ; faux sinon
   */
  boolean setItem(ItemStack stack, int slot);

  /**
   * Indique si un item est présent à l'emplacement donné.
   * 
   * @param slot l'indice
   * @return vrai si un item est présent ; faux sinon
   */
  boolean hasItem(int slot);
}
