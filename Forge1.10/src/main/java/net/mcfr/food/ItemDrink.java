package net.mcfr.food;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemDrink<T extends Enum<T> & IEnumType<T>> extends McfrItemFood {
  private Item emptyContainer;
  private Class<T> variantsClass;

  public ItemDrink(String name, Item emptyContainer, int nutrition, float saturation, Class<T> variantsClass) {
    super(name, nutrition, saturation);
    this.emptyContainer = emptyContainer;
    this.variantsClass = variantsClass;
    setHasSubtypes(true);
    setMaxStackSize(1);
  }

  @Override
  public abstract String getUnlocalizedName(ItemStack stack);

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    for (int i = 0; i < this.variantsClass.getEnumConstants().length; i++)
      subItems.add(new ItemStack(itemIn, 1, i));
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

  @Override
  public EnumAction getItemUseAction(ItemStack stack) {
    return EnumAction.DRINK;
  }
}
