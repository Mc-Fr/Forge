package net.mcfr.utils;

import java.util.HashMap;
import java.util.Map;

import net.mcfr.craftsmanship.LargeRecipe;
import net.mcfr.forge.recipes.AnvilRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public final class LargeRecipesUtils {
  /**
   * Ajoute une recette 5x5.
   * 
   * @param result
   *          le résultat de la recette
   * @param recipeComponents
   *          les composants de la recette
   */
  public static void addLargeRecipe(ItemStack result, Object... recipeComponents) {
    Components c = getComponents(recipeComponents);
    CraftingManager.getInstance().getRecipeList().add(new LargeRecipe(c.getWidth(), c.getHeight(), c.getItems(), result));
  }

  /**
   * Ajoute une recette 5x5 pour l'enclume.
   * 
   * @param result
   *          le résultat de la recette
   * @param temperature
   *          la température requise pour obtenir le résultat
   * @param recipeComponents
   *          les composants de la recette
   */
  public static void addAnvilRecipe(ItemStack result, int temperature, Object... recipeComponents) {
    Components c = getComponents(recipeComponents);
    CraftingManager.getInstance().getRecipeList().add(new AnvilRecipe(c.getWidth(), c.getHeight(), c.getItems(), result, temperature));
  }

  private static Components getComponents(Object... recipeComponents) {
    String pattern = "";
    int i = 0;
    int width = 0;
    int height = 0;

    while (recipeComponents[i] instanceof String) {
      String str = (String) recipeComponents[i++];

      height++;
      width = str.length();
      pattern += str;
    }

    Map<Character, ItemStack> charToItemStack = new HashMap<>();

    for (; i < recipeComponents.length; i += 2) {
      Character character = (Character) recipeComponents[i];
      ItemStack stack = null;

      if (recipeComponents[i + 1] instanceof Item) {
        stack = new ItemStack((Item) recipeComponents[i + 1]);
      } else if (recipeComponents[i + 1] instanceof Block) {
        stack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
      } else if (recipeComponents[i + 1] instanceof ItemStack) {
        stack = (ItemStack) recipeComponents[i + 1];
      }

      charToItemStack.put(character, stack);
    }

    ItemStack[] stacks = new ItemStack[width * height];

    for (int j = 0; j < width * height; j++) {
      char c = pattern.charAt(j);

      if (charToItemStack.containsKey(c)) {
        stacks[j] = charToItemStack.get(c).copy();
      } else {
        stacks[j] = null;
      }
    }

    return new Components(width, height, stacks);
  }

  private LargeRecipesUtils() {}

  private static class Components {
    private int w, h;
    private ItemStack[] items;

    public Components(int w, int h, ItemStack[] items) {
      this.w = w;
      this.h = h;
      this.items = items;
    }

    public int getWidth() {
      return this.w;
    }

    public int getHeight() {
      return this.h;
    }

    public ItemStack[] getItems() {
      return this.items;
    }
  }
}
