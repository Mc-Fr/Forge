package net.mcfr.food;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class McfrItemFood extends ItemFood {
  public McfrItemFood(String name, int amount, float saturation) {
    this(name, amount, saturation, false);
  }
  
  public McfrItemFood(String name, int amount, float saturation, boolean isWolfFood) {
    super(amount, saturation, isWolfFood);
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setAlwaysEdible();
  }
  
  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip, advanced);
  }
}
