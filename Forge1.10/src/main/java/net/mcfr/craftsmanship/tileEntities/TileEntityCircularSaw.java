package net.mcfr.craftsmanship.tileEntities;

import net.mcfr.craftsmanship.BlockCircularSaw;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.mcfr.utils.ItemsLists;
import net.minecraft.item.ItemStack;

public class TileEntityCircularSaw extends TileEntityRack {
  public TileEntityCircularSaw() {
    super("circular_saw", BlockCircularSaw.class, ContainerRack.class, ItemsLists.getCircularSawItems());
  }

  @Override
  public ItemStack transform(ItemStack from) {
    // TODO Auto-generated method stub
    return null;
  }
}
