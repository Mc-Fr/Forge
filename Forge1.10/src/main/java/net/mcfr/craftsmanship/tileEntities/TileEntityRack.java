package net.mcfr.craftsmanship.tileEntities;

import java.util.List;

import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityRestricted;
import net.minecraft.block.Block;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;

public abstract class TileEntityRack extends TileEntityRestricted implements ITickable {
  public TileEntityRack(String name, Class<? extends Block> blockClass, Class<? extends Container> containerClass, List<Item> authorized) {
    super(name, 2, 64, false, blockClass, containerClass, authorized);
  }

  @Override
  public void update() {
    // FIXME
    ItemStack it = getStackInSlot(0);
    if (it != null) {
      ItemStack product = transform(it);
      if (product != null) {
        ItemStack to = getStackInSlot(1);
        if (to == null || to.getItem() == product.getItem() && to.stackSize < 64 - product.stackSize) {
          if (to != null) {
            product.stackSize += to.stackSize;
          }
          setInventorySlotContents(1, product);
        }
      }
    }
  }

  public abstract ItemStack transform(ItemStack from);
}
