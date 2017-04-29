package net.mcfr.farming;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;

/**
 * Classe représentant des graines.
 *
 * @author Mc-Fr
 */
public class McfrItemSeeds extends ItemSeeds {
  /**
   * Crée des graines.
   * 
   * @param name le nom
   * @param crops le bloc à poser
   * @param soil le bloc sur lequel poser
   */
  public McfrItemSeeds(String name, Block crops, Block soil) {
    super(crops, soil);
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }
}
