package net.mcfr.equipment;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;

// TODO : changer la puissance de tir à travers l'évènement
// net.minecraftforge.event.entity.player.ArrowLooseEvent
/**
 * Arc.
 * 
 * @author Mc-Fr
 * @see ItemBow#onPlayerStoppedUsing
 */
public class McfrItemBow extends ItemBow {
  private final float charge;

  /**
   * Crée un arc.
   * 
   * @param name le nom
   * @param charge la charge
   */
  public McfrItemBow(String name, float charge) {
    super();
    name = name + "_bow";
    setRegistryName(name);
    this.charge = charge;
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }

  /**
   * @return la charge
   */
  public float getCharge() {
    return this.charge;
  }
}
