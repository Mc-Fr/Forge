package net.mcfr.food;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

/**
 * Classe de base pour la nourriture.
 *
 * @author Mc-Fr
 */
public class McfrItemFood extends ItemFood {
  /**
   * Crée un item de nourriture. Il ne pourra pas être utilisé sur les loups.
   * 
   * @param name le nom
   * @param amount la quantité de faim restituée
   * @param saturation le taux de saturation
   */
  public McfrItemFood(String name, int amount, float saturation) {
    this(name, amount, saturation, false);
  }

  /**
   * Crée un item de nourriture.
   * 
   * @param name le nom
   * @param amount la quantité de faim restituée
   * @param saturation le taux de saturation
   * @param isWolfFood indique si l'item peut être utilisé sur un loup
   */
  public McfrItemFood(String name, int amount, float saturation, boolean isWolfFood) {
    super(amount, saturation, isWolfFood);
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setAlwaysEdible();
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }
}
