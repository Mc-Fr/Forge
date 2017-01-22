package net.mcfr.craftsmanship.tileEntities;

import net.mcfr.McfrItems;
import net.mcfr.craftsmanship.BlockLoom;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.mcfr.utils.ItemsLists;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TileEntityLoom extends TileEntityRack {
  public TileEntityLoom() {
    super("loom", BlockLoom.class, ContainerRack.class, ItemsLists.getLoomItems());
  }

  @Override
  public ItemStack transform(ItemStack from) {
    if (from.getItem().equals(Items.STRING) && from.stackSize >= 4) {
      from.stackSize -= 4;
      return new ItemStack(McfrItems.THREAD_COIL, 10);
    }
    return null;
  }
}
