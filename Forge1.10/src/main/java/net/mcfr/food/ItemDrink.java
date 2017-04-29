package net.mcfr.food;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Classe de base des boissons.
 * 
 * @author Mc-Fr
 *
 * @param <T> type des variantes
 */
public abstract class ItemDrink<T extends Enum<T> & IEnumType<T>> extends ItemContainedFood {
  private Class<T> variantsClass;

  /**
   * Crée une boisson.
   * 
   * @param name le nom
   * @param emptyContainer le récipient vide
   * @param nutrition la quantité de faim restituée
   * @param saturation le taux de saturation
   * @param variantsClass le type des variantes
   */
  public ItemDrink(String name, Item emptyContainer, int nutrition, float saturation, Class<T> variantsClass) {
    super(name, emptyContainer, nutrition, saturation);
    this.variantsClass = variantsClass;
    setHasSubtypes(true);
  }

  @Override
  public abstract String getUnlocalizedName(ItemStack stack);

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    for (int i = 0; i < this.variantsClass.getEnumConstants().length; i++)
      subItems.add(new ItemStack(itemIn, 1, i));
  }

  @Override
  public EnumAction getItemUseAction(ItemStack stack) {
    return EnumAction.DRINK;
  }
}
