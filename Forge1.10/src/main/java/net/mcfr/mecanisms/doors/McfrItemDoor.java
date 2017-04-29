package net.mcfr.mecanisms.doors;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockDoor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;

/**
 * Item de porte.
 *
 * @author Mc-Fr
 */
public class McfrItemDoor extends ItemDoor {
  /**
   * Crée un item de porte.
   * 
   * @param name le nom
   * @param block le bloc à poser
   */
  public McfrItemDoor(String name, BlockDoor block) {
    super(block);
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }
}
