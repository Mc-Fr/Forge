package net.mcfr.equipment;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class McfrItemPickaxe extends ItemPickaxe {
  
  public McfrItemPickaxe(String name, Item.ToolMaterial material) {
    super(material);
    name += "_pickaxe";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }
  
  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }
}
