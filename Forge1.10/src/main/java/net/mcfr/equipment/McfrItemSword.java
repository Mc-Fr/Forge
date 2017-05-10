package net.mcfr.equipment;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

/**
 * Classe représentant tout type d'arme ressemblant à une épée.
 *
 * @author Mc-Fr
 */
public class McfrItemSword extends ItemSword {
  private final String type;

  /**
   * Crée une épée.
   * 
   * @param name le nom
   * @param material le matériau
   */
  public McfrItemSword(String name, Item.ToolMaterial material) {
    this(name, "sword", material);
  }

  /**
   * Crée une arme de type épée.
   * 
   * @param name le nom
   * @param type le type
   * @param material le matériau
   */
  public McfrItemSword(String name, String type, Item.ToolMaterial material) {
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

  /**
   * @return le type d'arme
   */
  public String getType() {
    return this.type;
  }
}
