package net.mcfr.commons;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

/**
 * Cette classe représente les items de Mc-Fr.
 *
 * @author Mc-Fr
 */
@SuppressWarnings("deprecation")
public class McfrItem extends Item {
  /**
   * Instancie un nouvel item avec une taille maximale de <i>stack</i> de 64.
   *
   * @param name le nom interne
   * @param tab l'onglet du menu Créatif
   */
  public McfrItem(String name, CreativeTabs tab) {
    this(name, 64, tab);
  }

  /**
   * Instancie un nouvel item.
   *
   * @param name le nom interne
   * @param maxStackSize la taille maximale d'un stack
   * @param tab l'onglet du menu Créatif
   */
  public McfrItem(String name, int maxStackSize, CreativeTabs tab) {
    super();
    setMaxStackSize(maxStackSize);
    setCreativeTab(tab);
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public EnumRarity getRarity(ItemStack stack) {
    Item item = stack.getItem();
    String rarityName = I18n.translateToLocal(item.getUnlocalizedName(stack) + ".rarity");

    EnumRarity rarity = super.getRarity(stack);
    if (!rarityName.equals(item.getUnlocalizedName(stack) + ".rarity")) {
      for (EnumRarity r : EnumRarity.values()) {
        if (rarityName.toLowerCase().equals(r.rarityName.toLowerCase())) {
          rarity = r;
          break;
        }
      }
    }
    return rarity;
  }

  @Override
  public boolean hasEffect(ItemStack stack) {
    Item item = stack.getItem();
    String hasEffectString = I18n.translateToLocal(item.getUnlocalizedName(stack) + ".effect");

    boolean hasEffect = super.hasEffect(stack);
    if (!hasEffectString.equals(item.getUnlocalizedName(stack) + ".effect")) {
      if (hasEffectString.equals("true")) {
        hasEffect = true;
      }
    }
    return hasEffect;
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(stack.getItem(), stack, playerIn, tooltip, advanced);
  }
}