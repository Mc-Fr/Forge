package net.mcfr.food;

import java.util.List;

import net.mcfr.commons.IEnumType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCandy extends McfrItemFood {
  public ItemCandy() {
    super("candy", 1, 0.2f);
    setHasSubtypes(true);
    setMaxStackSize(16);
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
  
  public static enum EnumType implements IEnumType<EnumType> {
    MAGENTA("magenta"),
    PURPLE("purple"),
    CYAN("cyan"),
    LIGHT_BLUE("light_blue"),
    BLUE("blue"),
    LIME("lime"),
    GREEN("green"),
    RED("red"),
    PINK("pink"),
    ORANGE("orange"),
    YELLOW("yellow"),
    CHOCOLATE("chocolate"),
    PIG("pig"),
    ENDERMAN("enderman"),
    GOLDEN("golden"),
    SUNRISE("sunrise"),
    MYSTERY("mystery");
    
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
