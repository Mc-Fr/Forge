package net.mcfr.craftsmanship;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Manche d'épée.
 *
 * @author Mc-Fr
 */
public class ItemSwordHandle extends McfrItem {
  public ItemSwordHandle() {
    super("sword_handle", 16, CreativeTabs.MATERIALS);
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
   * Variantes du manche d'épée :
   * <ul>
   * <li>cuir</li>
   * <li>fer</li>
   * <li>or</li>
   * <li>acier</li>
   * </ul>
   * 
   * @author Mc-Fr
   */
  public static enum EnumType implements IEnumType<EnumType> {
    LEATHER("leather"),
    IRON("iron"),
    GOLDEN("golden"),
    STEEL("steel");

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
