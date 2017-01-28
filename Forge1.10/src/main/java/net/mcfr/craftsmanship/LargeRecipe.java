package net.mcfr.craftsmanship;

import java.util.Objects;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

/**
 * Ce type de recette est utilisé dans l'atelier large (5x5).
 * 
 * @author Mc-Fr
 */
public class LargeRecipe implements IRecipe {
  private final int width;
  private final int height;
  private final ItemStack[] items;
  private final ItemStack output;

  /**
   * Crée une recette 5x5.
   * 
   * @param width
   *          la largeur de la zone
   * @param height
   *          la hauteur de la zone
   * @param items
   *          les ingrédients
   * @param output
   *          le résultat
   */
  public LargeRecipe(int width, int height, ItemStack[] items, ItemStack output) {
    checkParameters(width, height, items, output);
    this.width = width;
    this.height = height;
    this.items = items;
    this.output = output;
  }

  private void checkParameters(int width, int height, ItemStack[] items, ItemStack output) {
    if (width < 1 || width > 5)
      throw new IllegalArgumentException("recipe width must be between 1 and 5 inclusive");
    if (height < 1 || height > 5)
      throw new IllegalArgumentException("recipe height must be between 1 and 5 inclusive");
    Objects.requireNonNull(items);
    if (items.length < 1 || items.length > 25)
      throw new IllegalArgumentException("array size must be between 1 and 25 inclusive");
    Objects.requireNonNull(output);
  }

  @Override
  public ItemStack getRecipeOutput() {
    return this.output;
  }

  @Override
  public ItemStack[] getRemainingItems(InventoryCrafting inv) {
    ItemStack[] stack = new ItemStack[inv.getSizeInventory()];

    for (int i = 0; i < stack.length; ++i) {
      stack[i] = ForgeHooks.getContainerItem(inv.getStackInSlot(i));
    }

    return stack;
  }

  @Override
  public boolean matches(InventoryCrafting inv, World worldIn) {
    for (int i = 0; i <= 5 - this.width; i++) {
      for (int j = 0; j <= 5 - this.height; j++) {
        if (checkMatch(inv, i, j, true))
          return true;

        if (checkMatch(inv, i, j, false))
          return true;
      }
    }

    return false;
  }

  /**
   * Checks if the region of a crafting inventory is match for the recipe.<br />
   * <i>Adapté de {@link ShapedRecipes#checkMatch}.</i>
   */
  private boolean checkMatch(InventoryCrafting grid, int x, int y, boolean b) {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        int k = i - x;
        int l = j - y;
        ItemStack stack = null;

        if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
          if (b) {
            stack = this.items[this.width - k - 1 + l * this.width];
          } else {
            stack = this.items[k + l * this.width];
          }
        }

        ItemStack stack1 = grid.getStackInRowAndColumn(i, j);

        if (stack1 != null || stack != null) {
          if (stack1 == null && stack != null || stack1 != null && stack == null)
            return false;
          if (stack.getItem() != stack1.getItem())
            return false;
          if (stack.getMetadata() != 32767 && stack.getMetadata() != stack1.getMetadata())
            return false;
        }
      }
    }

    return true;
  }

  @Override
  public ItemStack getCraftingResult(InventoryCrafting inv) {
    ItemStack stack = getRecipeOutput().copy();

    for (int i = 0; i < inv.getSizeInventory(); ++i) {
      ItemStack stack1 = inv.getStackInSlot(i);

      if (stack1 != null && stack1.hasTagCompound()) {
        stack.setTagCompound(stack1.getTagCompound().copy());
      }
    }

    return stack;
  }

  @Override
  public int getRecipeSize() {
    return this.width * this.height;
  }
}
