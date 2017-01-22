package net.mcfr.utils;

import java.util.List;

import net.mcfr.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

/**
 * Cette classe met à disposition des méthodes pour traiter les noms des blocs/items.
 *
 * @author Mc-Fr
 */
@SuppressWarnings("deprecation")
public final class NameUtils {
  /**
   * Retourne le nom non localisé à partir du nom simple.
   *
   * @param name le nom simple
   * @return le nom non localisé
   */
  public static String getUnlocalizedName(String name) {
    return Constants.MOD_ID + "_" + name;
  }
  
  public static void addItemInformation(Item item, ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    String translation = I18n.translateToLocal(item.getUnlocalizedName(stack) + ".lore");
    
    if (!translation.equals(item.getUnlocalizedName(stack) + ".lore")) {
      tooltip.add(translation);
    }
  }
  
  private NameUtils() {}
}
