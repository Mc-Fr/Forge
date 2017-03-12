package net.mcfr.food;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemContainedFood extends McfrItemFood {
  private Item emptyContainer;
  
  public ItemContainedFood(String name, Item emptyContainer, int amount, float saturation) {
    super(name, amount, saturation, false);
    setMaxStackSize(1);
    this.emptyContainer = emptyContainer;
  }
  
  @Override
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
    ItemStack s = super.onItemUseFinish(stack, worldIn, entityLiving);
    return s.stackSize <= 0 ? new ItemStack(this.emptyContainer) : s;
    // if (!playerIn.capabilities.isCreativeMode) {
    // InventoryPlayer inv = playerIn.inventory;
    // int slot = stack.stackSize == 1 ? inv.currentItem : inv.getFirstEmptyStack();
    //
    // FoodStats food = playerIn.getFoodStats();
    // if (food.needFood()) {
    // food.setFoodLevel(food.getFoodLevel() + this.nutrition);
    // food.setFoodSaturationLevel(food.getSaturationLevel() + this.saturation);
    // }
    //
    // for (PotionEffect effect : getEffects())
    // playerIn.addPotionEffect(effect);
    // // inv.setInventorySlotContents(slot, new ItemStack(this.emptyContainer));
    // --stack.stackSize;
    // }
    //
    // if (!worldIn.isRemote) {
    // playerIn.curePotionEffects(stack);
    // }
    //
    // return stack.stackSize <= 0 ? new ItemStack(this.emptyContainer) : stack;
  }
}
