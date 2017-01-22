package net.mcfr.craftsmanship.guis;

import static net.mcfr.utils.RenderUtils.*;

import net.mcfr.utils.ItemsLists;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRack extends Container {
  private IInventory rackInventory;

  public ContainerRack(IInventory playerInventory, IInventory rackInventory, EntityPlayer player, final Class<? extends Block> blockClass) {
    this.rackInventory = rackInventory;
    this.rackInventory.openInventory(player);

    addSlotToContainer(new Slot(rackInventory, 0, 33, 53) {
      @Override
      public boolean isItemValid(ItemStack stack) {
        return ItemsLists.isItemValid(blockClass, stack);
      }
    });
    addSlotToContainer(new Slot(rackInventory, 1, 127, 53) {
      @Override
      public boolean isItemValid(ItemStack stack) {
        return false;
      }
    });

    int yOffset = TOP_OFFSET + 67;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++) {
        addSlotToContainer(new Slot(playerInventory, 9 + i * 9 + j, SIDE_OFFSET + j * SLOT_SIZE, yOffset));
      }
      yOffset += SLOT_SIZE;
    }

    yOffset += HOTBAR_SEPARATOR;
    for (int i = 0; i < 9; i++) {
      addSlotToContainer(new Slot(playerInventory, i, SIDE_OFFSET + i * SLOT_SIZE, yOffset));
    }
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return this.rackInventory.isUseableByPlayer(playerIn);
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
    ItemStack previous = null;
    Slot slot = this.inventorySlots.get(index);

    if (slot != null && slot.getHasStack() && slot.canTakeStack(playerIn)) {
      ItemStack current = slot.getStack();
      previous = current.copy();

      if (index < 2) {
        if (!mergeItemStack(current, 2, this.inventorySlots.size(), true)) {
          return null;
        }
      }
      else if (slot.isItemValid(current)) {
        if (!mergeItemStack(current, 0, 1, false)) {
          return null;
        }
      }
      else {
        return null;
      }

      if (current.stackSize == 0)
        slot.putStack(null);
      else
        slot.onSlotChanged();

      if (current.stackSize == previous.stackSize)
        return null;

      slot.onPickupFromSlot(playerIn, current);
    }

    return previous;
  }
}
