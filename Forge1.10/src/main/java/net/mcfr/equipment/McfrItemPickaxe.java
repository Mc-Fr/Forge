package net.mcfr.equipment;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

/**
 * Pioche.
 *
 * @author Mc-Fr
 */
public class McfrItemPickaxe extends ItemPickaxe {
  /**
   * Crée une pioche.
   * 
   * @param name le nom
   * @param material le matériau
   */
  public McfrItemPickaxe(String name, Item.ToolMaterial material) {
    super(material);
    name += "_pickaxe";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }
}
