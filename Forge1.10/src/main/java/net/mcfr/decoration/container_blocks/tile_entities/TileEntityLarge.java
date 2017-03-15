package net.mcfr.decoration.container_blocks.tile_entities;

import net.mcfr.decoration.container_blocks.guis.ContainerLarge;
import net.mcfr.decoration.furniture.BlockTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class TileEntityLarge extends McfrTileEntityLockable {
  
  public TileEntityLarge(String name) {
    super(name, ContainerLarge.SIZE, 64, true);
  }

  @Override
  public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
    return new ContainerLarge(playerInventory, this, playerIn, BlockTable.class);
  }

  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    return index >= 0 && index < getSizeInventory();
  }
}
