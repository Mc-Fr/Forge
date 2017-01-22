package net.mcfr.food;

import net.mcfr.decoration.containerBlocks.ItemBarrel;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemAlcoholDrink extends ItemDrink<ItemBarrel.EnumType> {
  public ItemAlcoholDrink(String name, Item emptyContainer, int nutrition, float saturation) {
    super(name, emptyContainer, nutrition, saturation, ItemBarrel.EnumType.class);
    setPotionEffect(new PotionEffect(MobEffects.HUNGER, 60, 0), 1);
    setMaxStackSize(8);
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return getUnlocalizedName() + "." + ItemBarrel.EnumType.byMetadata(stack.getMetadata()).getName();
  }
}
