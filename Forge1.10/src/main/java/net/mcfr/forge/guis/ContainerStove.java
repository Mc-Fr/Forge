package net.mcfr.forge.guis;

import static net.mcfr.utils.RenderUtils.*;

import net.mcfr.forge.tileEntities.TileEntityStove;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerStove extends Container {
  private final TileEntityStove tileStove;
  private int temperature;
  private int totalItemBurnTime;
  private int currentItemBurnTime;

  public ContainerStove(InventoryPlayer playerInventory, TileEntityStove stoveInventory) {
    this.tileStove = stoveInventory;

    addSlotToContainer(new Slot(stoveInventory, 0, 56, 53) {
      @Override
      public boolean isItemValid(ItemStack stack) {
        return TileEntityFurnace.isItemFuel(stack);
      }
    });

    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 9; ++j) {
        addSlotToContainer(new Slot(playerInventory, 9 + i * 9 + j, SIDE_OFFSET + j * SLOT_SIZE, 84 + i * SLOT_SIZE));
      }
    }

    for (int i = 0; i < 9; ++i) {
      addSlotToContainer(new Slot(playerInventory, i, SIDE_OFFSET + i * SLOT_SIZE, 142));
    }
  }

  @Override
  public void addListener(IContainerListener listener) {
    super.addListener(listener);
    listener.sendAllWindowProperties(this, this.tileStove);
  }

  @Override
  public void detectAndSendChanges() {
    super.detectAndSendChanges();

    for (IContainerListener listener : this.listeners) {
      if (this.temperature != this.tileStove.getField(0)) {
        listener.sendProgressBarUpdate(this, 0, this.tileStove.getField(0));
      }

      if (this.totalItemBurnTime != this.tileStove.getField(1)) {
        listener.sendProgressBarUpdate(this, 1, this.tileStove.getField(1));
      }

      if (this.currentItemBurnTime != this.tileStove.getField(2)) {
        listener.sendProgressBarUpdate(this, 2, this.tileStove.getField(2));
      }
    }

    this.temperature = this.tileStove.getField(0);
    this.totalItemBurnTime = this.tileStove.getField(1);
    this.currentItemBurnTime = this.tileStove.getField(2);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void updateProgressBar(int id, int data) {
    this.tileStove.setField(id, data);
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return this.tileStove.isUseableByPlayer(playerIn);
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
    ItemStack stack = null;
    Slot slot = this.inventorySlots.get(index);

    if (slot != null && slot.getHasStack()) {
      ItemStack stack1 = slot.getStack();
      stack = stack1.copy();

      if (index != 0) {
        if (TileEntityFurnace.isItemFuel(stack1)) {
          if (!mergeItemStack(stack1, 0, 1, false)) {
            return null;
          }
        }
        else if (index >= 1 && index < 28) {
          if (!mergeItemStack(stack1, 28, 37, false)) {
            return null;
          }
        }
        else if (index >= 28 && index < 37) {
          if (!mergeItemStack(stack1, 1, 28, false)) {
            return null;
          }
        }
      }
      else if (!mergeItemStack(stack1, 1, 37, false)) {
        return null;
      }

      if (stack1.stackSize == 0) {
        slot.putStack(null);
      }
      else {
        slot.onSlotChanged();
      }

      if (stack1.stackSize == stack.stackSize) {
        return null;
      }

      slot.onPickupFromSlot(playerIn, stack1);
    }

    return stack;
  }
}