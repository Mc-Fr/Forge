package net.mcfr.food;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Œuf d'araignée.
 *
 * @author Mc-Fr
 */
public class ItemSpiderEgg extends McfrItemFood {
  public ItemSpiderEgg() {
    super("spider_egg", 1, 0.5f);
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
   * Types d'œufs :
   * <ul>
   * <li>normal</li>
   * <li>bleu</li>
   * </ul>
   *
   * @author Mc-Fr
   */
  public static enum EnumType implements IEnumType<EnumType> {
    NORMAL("normal"),
    BLUE("blue");

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
