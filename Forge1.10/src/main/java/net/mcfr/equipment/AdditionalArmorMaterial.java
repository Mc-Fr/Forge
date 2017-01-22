package net.mcfr.equipment;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Cette classe regroupe tous les matériaux d'armures propres au serveur.
 *
 * @author Mc-Fr
 */
// TODO : équilibrages
public final class AdditionalArmorMaterial {
  public static final ArmorMaterial ASSASSIN = EnumHelper.addArmorMaterial("ASSASSIN", "assassin", 0, new int[] { 0, 0, 0, 0 }, 0,
      SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0F);
  public static final ArmorMaterial BARBARIAN = EnumHelper.addArmorMaterial("BARBARIAN", "barbarian", 0, new int[] { 0, 0, 0, 0 }, 0,
      SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0F);
  public static final ArmorMaterial GOLDEN_CHAIN = EnumHelper.addArmorMaterial("GOLDEN_CHAIN", "golden_chain", 0, new int[] { 0, 0, 0, 0 }, 0,
      SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0F);

  private AdditionalArmorMaterial() {}
}
