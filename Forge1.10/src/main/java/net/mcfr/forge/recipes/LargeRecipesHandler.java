package net.mcfr.forge.recipes;

import net.mcfr.craftsmanship.LargeRecipe;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

/**
 * Cette classe permet de gérer les recettes de l'enclume de la forge.
 * 
 * @author Mc-Fr
 */
public final class LargeRecipesHandler {
  /**
   * Retourne l'item correspondant à la recette et la température données.
   * 
   * @param craftMatrix
   *          la grille de construction de l'enclume
   * @param temperature
   *          la température du haut-fourneau le plus proche
   * @param world
   *          le monde
   * @return l'item
   */
  public static ItemStack findMatchingAnvilRecipe(InventoryCrafting craftMatrix, int temperature, World world) {
    for (IRecipe recipe : CraftingManager.getInstance().getRecipeList()) {
      if (recipe instanceof AnvilRecipe && recipe.matches(craftMatrix, world)
          && ((AnvilRecipe) recipe).temperatureMatches(temperature)) {
        return recipe.getCraftingResult(craftMatrix);
      }
    }

    return null;
  }

  /**
   * Retourne l'item correspondant à la recette donnée.
   * 
   * @param craftMatrix
   *          la grille de construction
   * @param world
   *          le monde
   * @return l'item
   */
  public static ItemStack findMatchingRecipe(InventoryCrafting craftMatrix, World world) {
    for (IRecipe recipe : CraftingManager.getInstance().getRecipeList()) {
      if (recipe instanceof LargeRecipe && !(recipe instanceof AnvilRecipe) && recipe.matches(craftMatrix, world)) {
        return recipe.getCraftingResult(craftMatrix);
      }
    }

    return null;
  }

  private LargeRecipesHandler() {
  }
}