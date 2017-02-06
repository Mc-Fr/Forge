package net.mcfr.decoration.container_blocks.guis;

import static net.mcfr.utils.RenderUtils.*;

import net.mcfr.utils.ItemsLists;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRestricted extends Container {
  public static final int SIZE = 9;

  private IInventory chestInventory;

  public ContainerRestricted(IInventory playerInventory, IInventory chestInventory, EntityPlayer player, final Class<? extends Block> blockClass) {
    this.chestInventory = chestInventory;
    chestInventory.openInventory(player);

    int yOffset = SLOT_SIZE;

    for (int i = 0; i < SIZE; i++) {
      addSlotToContainer(new Slot(chestInventory, i, SIDE_OFFSET + i * SLOT_SIZE, yOffset) {
        @Override
        public boolean isItemValid(ItemStack stack) {
          return ItemsLists.isItemValid(blockClass, stack);
        }
      });
    }

    yOffset += TOP_OFFSET + INV_SEPARATOR;
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < SIZE; ++j) {
        addSlotToContainer(new Slot(playerInventory, 9 + i * 9 + j, SIDE_OFFSET + j * SLOT_SIZE, yOffset));
      }
      yOffset += SLOT_SIZE;
    }

    yOffset += HOTBAR_SEPARATOR;
    for (int i = 0; i < SIZE; ++i) {
      addSlotToContainer(new Slot(playerInventory, i, SIDE_OFFSET + i * SLOT_SIZE, yOffset));
    }
  }

  public int getChestSize() {
    return SIZE;
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return this.chestInventory.isUseableByPlayer(playerIn);
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
    ItemStack previous = null;
    Slot slot = this.inventorySlots.get(index);

    if (slot != null && slot.getHasStack() && slot.canTakeStack(playerIn)) {
      ItemStack current = slot.getStack();
      previous = current.copy();

      if (index < 9) {
        if (!mergeItemStack(current, getChestSize(), this.inventorySlots.size(), true)) {
          return null;
        }
      }
      else if (slot.isItemValid(current)) {
        if (!mergeItemStack(current, 0, getChestSize(), false)) {
          return null;
        }
      }
      else {
        return null;
      }

      if (current.stackSize == 0) slot.putStack(null);
      else slot.onSlotChanged();

      if (current.stackSize == previous.stackSize) return null;

      slot.onPickupFromSlot(playerIn, current);
    }

    return previous;
  }

  @Override
  public void onContainerClosed(EntityPlayer playerIn) {
    super.onContainerClosed(playerIn);
    this.chestInventory.closeInventory(playerIn);
  }
}
