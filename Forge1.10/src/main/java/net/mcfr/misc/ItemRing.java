package net.mcfr.misc;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Anneau.
 *
 * @author Mc-Fr
 */
public class ItemRing extends McfrItem {
  public ItemRing(String name) {
    super(name + "_ring", CreativeTabs.MISC);
    setHasSubtypes(true);
    setMaxStackSize(1);
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return getUnlocalizedName() + "." + EnumType.byMetadata(stack.getMetadata()).getName();
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    for (int i = 0; i < EnumType.values().length; i++) {
      subItems.add(new ItemStack(itemIn, 1, i));
    }
  }

  /**
   * Types d'anneaux :
   * <ul>
   * <li>normal</li>
   * <li>renforcé</li>
   * <li>émeraude</li>
   * <li>rubis</li>
   * <li>saphire</li>
   * <li>grenat</li>
   * <li>jade</li>
   * <li>topaze</li>
   * <li>ancien</li>
   * </ul>
   *
   * @author Mc-Fr
   */
  public static enum EnumType implements IEnumType<EnumType> {
    NORMAL("normal"),
    REINFORCED("reinforced"),
    EMERALD("emerald"),
    RUBY("ruby"),
    SAPPHIRE("sapphire"),
    GARNET("garnet"),
    JADE("jade"),
    TOPAZ("topaz"),
    ANCIENT("ancient");

    private final String name;

    private EnumType(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return this.name;
    }

    @Override
    public int getMetadata() {
      return ordinal();
    }

    @Override
    public String toString() {
      return getName();
    }

    public static EnumType byMetadata(int meta) {
      return values()[meta % values().length];
    }
  }
}
