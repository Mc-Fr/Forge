package net.mcfr.decoration.container_blocks.guis;

import static net.mcfr.utils.RenderUtils.HOTBAR_SEPARATOR;
import static net.mcfr.utils.RenderUtils.INV_SEPARATOR;
import static net.mcfr.utils.RenderUtils.SIDE_OFFSET;
import static net.mcfr.utils.RenderUtils.SLOT_SIZE;
import static net.mcfr.utils.RenderUtils.TOP_OFFSET;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLarge extends Container {
  public static final int SIZE = 63;

  /** L'inventaire */
  private IInventory chestInventory;

  /**
   * Crée un conteneur.
   * 
   * @param playerInventory l'inventaire du joueur
   * @param chestInventory l'invetaire du conteneur
   * @param player le joueur
   * @param blockClass la classe du bloc
   */
  public ContainerLarge(IInventory playerInventory, IInventory chestInventory, EntityPlayer player, final Class<? extends Block> blockClass) {
    this.chestInventory = chestInventory;
    chestInventory.openInventory(player);

    int yOffset = TOP_OFFSET-9;

    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 9; j++) {
        addSlotToContainer(new Slot(chestInventory, i * 9 + j, SIDE_OFFSET + j * SLOT_SIZE, yOffset));
      }
      yOffset += SLOT_SIZE;
    }

    yOffset += INV_SEPARATOR;
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

  /**
   * @return la taille de l'inventaire
   */
  public int getContainerSize() {
    return SIZE;
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return this.chestInventory.isUseableByPlayer(playerIn);
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {    
    ItemStack stack = null;
    Slot slot = this.inventorySlots.get(index);

    if (slot != null && slot.getHasStack()) {
      ItemStack stack1 = slot.getStack();
      stack = stack1.copy();

      if (index < 63) {
        if (!mergeItemStack(stack1, 63, 99, false)) {
          return null;
        }
      }
      else if (index >= 63 && index < 100) {
        if (!mergeItemStack(stack1, 0, 62, false)) {
          return null;
        }
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

  @Override
  public void onContainerClosed(EntityPlayer playerIn) {
    super.onContainerClosed(playerIn);
    this.chestInventory.closeInventory(playerIn);
  }
}
