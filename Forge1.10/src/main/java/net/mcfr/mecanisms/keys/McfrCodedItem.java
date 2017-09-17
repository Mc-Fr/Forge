package net.mcfr.mecanisms.keys;

import java.util.List;
import java.util.Optional;

import net.mcfr.commons.McfrItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class McfrCodedItem extends McfrItem {

  public McfrCodedItem(String name, CreativeTabs tab) {
    super(name, 1, tab);
    setHasSubtypes(true);
  }
  
  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return getUnlocalizedName() + "." + (stack.getMetadata() == 0 ? "broken" : (stack.getMetadata() == 1 ? "coded" : "blank"));
  }
  
  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    subItems.add(new ItemStack(itemIn, 1, 0)); // Broken
    subItems.add(new ItemStack(itemIn, 1, 1)); // Coded
    subItems.add(new ItemStack(itemIn, 1, 2)); // Blank
  }
  
  public boolean setCode(EntityPlayer player, int code) {
    if (player.getHeldItemMainhand().getMetadata() != 2)
      return false;
    
    ItemStack stack = new ItemStack(player.getHeldItemMainhand().getItem(), 1, 1);
    
    if (stack.hasCapability(KeyCode.Provider.KEY_CODE_CAP, null)) {
      IKeyCode keyCode = stack.getCapability(KeyCode.Provider.KEY_CODE_CAP, null);
      keyCode.set(code);
    }
    
    player.setHeldItem(EnumHand.MAIN_HAND, stack);
    return true;
  }

  @SideOnly(Side.SERVER)
  public Optional<Integer> getCode(EntityPlayer player) {
    ItemStack stack = player.getHeldItemMainhand();
    if (stack.getMetadata() == 1 && stack.hasCapability(KeyCode.Provider.KEY_CODE_CAP, null))
      return Optional.of(stack.getCapability(KeyCode.Provider.KEY_CODE_CAP, null).get());
    else
      return Optional.empty();
  }
}
