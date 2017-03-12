package net.mcfr.equipment;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class McfrItemAxe extends ItemAxe {
  private final String type;

  public McfrItemAxe(String name, Item.ToolMaterial material) {
    this(name, "axe", material);
  }

  public McfrItemAxe(String name, String type, Item.ToolMaterial material) {
    super(material);
    this.type = type;
    name = name + "_" + type;
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }

  public String getType() {
    return this.type;
  }
}
