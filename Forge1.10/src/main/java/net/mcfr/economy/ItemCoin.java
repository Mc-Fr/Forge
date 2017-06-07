package net.mcfr.economy;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Pièces de monnaie.
 *
 * @author Mc-Fr
 */
public class ItemCoin extends McfrItem {
  public ItemCoin() {
    super("coin", 64, CreativeTabs.MISC);
    setHasSubtypes(true);
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
   * Types de pièces :
   * <ul>
   * <li>laiton</li>
   * <li>élinvar</li>
   * <li>or</li>
   * <li>platine</li>
   * <li>hexane</li>
   * <li>once</li>
   * <li>nef</li>
   * <li>galion</li>
   * <li>zéphir d'argent</li>
   * <li>zéphir d'or</li>
   * </ul>
   * 
   * @author Darmo
   */
  public static enum EnumType implements IEnumType<EnumType> {
    BRASS("brass"),
    ELINVAR("elinvar"),
    GOLD("gold"),
    PLATINUM("platinum"),
    HEXANE("hexane"),
    ONCE("once"),
    NEF("nef"),
    GALION("galion"),
    SILVER_ZEPHIR("silver_zephir"),
    GOLDEN_ZEPHIR("golden_zephir");

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
