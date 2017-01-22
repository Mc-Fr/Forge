package net.mcfr.craftsmanship.tileEntities;

import net.mcfr.craftsmanship.BlockTanningRack;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.mcfr.utils.ItemsLists;
import net.minecraft.item.ItemStack;

public class TileEntityTanningRack extends TileEntityRack {
  public TileEntityTanningRack() {
    super("tanning_rack", BlockTanningRack.class, ContainerRack.class, ItemsLists.getTanningRackItems());
  }

  @Override
  public ItemStack transform(ItemStack from) {
    // TODO Auto-generated method stub
    return null;
  }

}
