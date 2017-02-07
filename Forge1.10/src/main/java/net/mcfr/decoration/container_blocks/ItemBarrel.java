package net.mcfr.decoration.container_blocks;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrItemBlockSpecial;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Item du tonneau.
 *
 * @author Mc-Fr
 */
public class ItemBarrel extends McfrItemBlockSpecial {
  /**
   * Crée un item.
   * 
   * @param block le tonneau à poser
   */
  public ItemBarrel(BlockBarrel block) {
    super(block.getRegistryName().getResourcePath().substring(0, block.getRegistryName().getResourcePath().lastIndexOf("_")), block, CreativeTabs.DECORATIONS);
    setMaxStackSize(1);
    setHasSubtypes(true);
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return getUnlocalizedName() + "." + EnumType.byMetadata(stack.getMetadata()).getName();
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    for (int i = 0; i < EnumType.values().length; i++)
      subItems.add(new ItemStack(itemIn, 1, i));
  }

  /**
   * Variantes de l'item du tonneau :
   * <ul>
   * <li>âge 0</li>
   * <li>âge 1</li>
   * <li>âge 2</li>
   * <li>âge 3</li>
   * <li>âge 4</li>
   * <li>âge 5</li>
   * <li>âge 6</li>
   * <li>âge 7</li>
   * </ul>
   *
   * @author Mc-Fr
   */
  public static enum EnumType implements IEnumType<EnumType> {
    AGE_0("age_0"),
    AGE_1("age_1"),
    AGE_2("age_2"),
    AGE_3("age_3"),
    AGE_4("age_4"),
    AGE_5("age_5"),
    AGE_6("age_6"),
    AGE_7("age_7");

    private String name;

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
