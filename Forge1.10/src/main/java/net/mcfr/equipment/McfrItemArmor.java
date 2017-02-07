package net.mcfr.equipment;

import java.util.List;

import net.mcfr.Constants;
import net.mcfr.utils.NameUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class McfrItemArmor extends ItemArmor {
  /**
   * Crée une pièce d'armure.<br/>
   * Le type de la pièce d'armure sera ajouté au nom.
   *
   * @param name le nom
   * @param material le matériau
   * @param armorType le type d'armure (0, 1, 2 ou 3)
   * @see ItemArmor#armorType
   */
  public McfrItemArmor(String name, ArmorMaterial material, EntityEquipmentSlot armorType) {
    this(name, material, armorType, true);
  }

  /**
   * Crée une pièce d'armure.
   *
   * @param name le nom de la pièce
   * @param material le matériau
   * @param armorType le type d'armure (0, 1, 2 ou 3)
   * @param addType indique si le type d'armure doit être ajouté au nom de la pièce
   * @see ItemArmor#armorType
   */
  public McfrItemArmor(String name, ArmorMaterial material, EntityEquipmentSlot armorType, boolean addType) {
    super(material, -1, armorType);
    name = name + (addType ? "_" + getArmorType(armorType) : "");
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }

  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
    return Constants.MOD_ID + ":textures/armor/" + getArmorMaterial().getName() + "_" + (this.armorType == EntityEquipmentSlot.LEGS ? "2" : "1") + ".png";
  }

  /**
   * Retourne le nom de la partie d'armure indiquée par l'index donné. Les valeurs sont spécifiées
   * dans la classe ItemArmor.
   *
   * @param type l'index de la partie d'armure
   * @return le nom de l'armure ou null si l'index n'est pas compris entre 0 et 3 inclus
   * @see ItemArmor#armorType
   */
  public String getArmorType(EntityEquipmentSlot type) {
    switch (type) {
      case HEAD:
        return "helmet";
      case CHEST:
        return "chestplate";
      case LEGS:
        return "leggings";
      case FEET:
        return "boots";
      default:
        return null;
    }
  }
}
