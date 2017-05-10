package net.mcfr.equipment.fishing;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;

/**
 * Classe représentant cannes et filets de pêche.
 *
 * @author Mc-Fr
 */
public class McfrItemFishingRod extends ItemFishingRod {
  /**
   * Crée un objet de pêche.
   * 
   * @param name le nom
   */
  public McfrItemFishingRod(String name) {
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }
}
