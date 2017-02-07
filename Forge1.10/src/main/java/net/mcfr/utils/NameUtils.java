package net.mcfr.utils;

import java.util.List;

import net.mcfr.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

/**
 * Cette classe fournit des méthodes pour traiter les noms des blocs/items.
 *
 * @author Mc-Fr
 */
@SuppressWarnings("deprecation")
public final class NameUtils {
  /**
   * Retourne le nom non localisé à partir du nom interne.
   *
   * @param name le nom interne
   * @return le nom non localisé
   */
  public static String getUnlocalizedName(String name) {
    return Constants.MOD_ID + "_" + name;
  }

  /**
   * Permet d'ajouter des informations à un item à partir du fichier de langues.
   * 
   * @param item l'item
   * @param stack le stack
   * @param player le joueur
   * @param tooltip l'infobulle
   */
  public static void addItemInformation(Item item, ItemStack stack, EntityPlayer player, List<String> tooltip) {
    String translation = I18n.translateToLocal(item.getUnlocalizedName(stack) + ".lore");

    if (!translation.equals(item.getUnlocalizedName(stack) + ".lore")) {
      tooltip.add(translation);
    }
  }

  private NameUtils() {}
}
