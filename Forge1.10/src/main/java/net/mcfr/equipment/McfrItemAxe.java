package net.mcfr.equipment;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

/**
 * Hache.
 *
 * @author Mc-Fr
 */
public class McfrItemAxe extends ItemAxe {
  /**
   * Crée une hache.
   * 
   * @param name le nom
   * @param material le matériau
   */
  public McfrItemAxe(String name, Item.ToolMaterial material) {
    super(material, 8.0F, -3.1F);
    name += "_axe";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }
}
