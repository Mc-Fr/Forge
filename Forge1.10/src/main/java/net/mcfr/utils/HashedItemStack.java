package net.mcfr.utils;

import net.minecraft.item.ItemStack;

/**
 * Cette classe permet d'encapsuler un {@link ItemStack} afin de pouvoir l'utiliser dans une
 * HashMap.
 * 
 * @author Mc-Fr
 */
public class HashedItemStack {
  public static HashedItemStack fromStack(ItemStack stack) {
    return new HashedItemStack(stack);
  }

  private ItemStack stack;

  private HashedItemStack(ItemStack stack) {
    this.stack = stack.copy();
  }

  public ItemStack getStack() {
    return this.stack;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;

    result = prime * result + (this.stack.getItem() != null ? this.stack.getItem().hashCode() : 0);
    result = prime * result + this.stack.stackSize;
    result = prime * result + this.stack.getMetadata();

    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof HashedItemStack) {
      ItemStack stack = ((HashedItemStack) o).getStack();
      return this.stack.getItem() == stack.getItem() && this.stack.stackSize == stack.stackSize && this.stack.getMetadata() == stack.getMetadata();
    }
    return false;
  }

  @Override
  public String toString() {
    return this.stack.toString();
  }
}
