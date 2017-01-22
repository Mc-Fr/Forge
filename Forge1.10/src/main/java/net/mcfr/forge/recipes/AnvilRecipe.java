package net.mcfr.forge.recipes;

import net.mcfr.craftsmanship.LargeRecipe;
import net.minecraft.item.ItemStack;

/**
 * Ce type de recettes est utilisé par l'enclume de la forge.
 * 
 * @author Mc-Fr
 */
public class AnvilRecipe extends LargeRecipe {
  private final int temperature;

  /**
   * Crée une recette pour l'enclume.
   * 
   * @param width la largeur de la zone
   * @param height la hauteur de la zone
   * @param items les ingrédients
   * @param output le résultat
   * @param temperature la température des haut-fourneaux nécessaire pour débloquer cette recette
   */
  public AnvilRecipe(int width, int height, ItemStack[] items, ItemStack output, int temperature) {
    super(width, height, items, output);
    this.temperature = temperature;
  }

  /**
   * Indique si la température donnée correspond à celle nécessaire pour effectuer cette recette.
   * 
   * @param temperature la température
   * @return vrai si la température est correcte
   */
  public boolean temperatureMatches(int temperature) {
    return temperature >= this.temperature;
  }
}
