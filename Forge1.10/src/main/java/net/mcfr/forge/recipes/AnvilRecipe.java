package net.mcfr.forge.recipes;

import net.mcfr.craftsmanship.LargeRecipe;
import net.mcfr.forge.guis.LargeInventoryCrafting;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Ce type de recettes est utilisé par l'enclume de la forge.
 * 
 * @author Mc-Fr
 */
public class AnvilRecipe extends LargeRecipe {
  private final int temperatureMin;
  private final int temperatureMax;

  /**
   * Crée une recette pour l'enclume.
   * 
   * @param width la largeur de la zone
   * @param height la hauteur de la zone
   * @param items les ingrédients
   * @param output le résultat
   * @param temperatureMin la température minimale des haut-fourneaux pour débloquer cette recette
   * @param temperatureMin la température maximale des haut-fourneaux pour débloquer cette recette
   */
  public AnvilRecipe(int width, int height, ItemStack[] items, ItemStack output, int temperatureMin, int temperatureMax) {
    super(width, height, items, output);
    this.temperatureMin = temperatureMin;
    this.temperatureMax = temperatureMax;
  }

  /**
   * Indique si la température donnée correspond à celle nécessaire pour effectuer cette recette.
   * 
   * @param temperature la température
   * @return vrai si la température est correcte
   */
  public boolean temperatureMatches(int temperature) {
    return temperature >= this.temperatureMin && temperature <= this.temperatureMax;
  }

  @Override
  public boolean matches(InventoryCrafting inv, World worldIn) {
    if (inv instanceof LargeInventoryCrafting && ((LargeInventoryCrafting) inv).isAnvil()) {
      return super.matches(inv, worldIn);
    }
    return false;
  }
}
