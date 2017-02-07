package net.mcfr.commons;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockSpecial;
import net.minecraft.item.ItemStack;

/**
 * Classe facilitant la déclaration d'{@link ItemBlockSpecial items spéciaux}.
 * 
 * @author Mc-Fr
 * @see ItemBlockSpecial
 */
public class McfrItemBlockSpecial extends ItemBlockSpecial {
  /**
   * Crée un nouvel item spécial.
   * 
   * @param name le nom interne
   * @param block le bloc associé
   * @param creativeTabs l'onglet du menu Créatif
   */
  public McfrItemBlockSpecial(String name, Block block, CreativeTabs creativeTabs) {
    super(block);
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setCreativeTab(creativeTabs);
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }
}
