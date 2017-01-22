package net.mcfr.farming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.mcfr.McfrBlocks;
import net.mcfr.McfrItems;
import net.minecraft.item.ItemStack;

public class BlockHempTop extends BlockBushTop {
  public BlockHempTop() {
    super("hemp");
  }
  
  @Override
  public BlockBushBase getBaseBlock() {
    return (BlockBushBase) McfrBlocks.HEMP_BASE;
  }
  
  @Override
  public List<ItemStack> getItems() {
    List<ItemStack> list = new ArrayList<ItemStack>();
    
    list.add(new ItemStack(McfrItems.HEMP));
    list.add(new ItemStack(McfrItems.HEMP_LEAF, new Random().nextInt(3) + 1));
    
    return list;
  }
}
