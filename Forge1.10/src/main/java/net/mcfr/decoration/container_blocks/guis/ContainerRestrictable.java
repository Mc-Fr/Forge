package net.mcfr.decoration.container_blocks.guis;

import static net.mcfr.utils.RenderUtils.*;

import net.mcfr.decoration.container_blocks.tile_entities.McfrTileEntityLockable;
import net.mcfr.utils.ItemsLists;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Conteneur permettant de restreindre (éventuellement) le stockage de certains items.
 *
 * @author Mc-Fr
 */
public class ContainerRestrictable extends Container {
  public static final int REDUCED_LINES_NB = 1;
  public static final int LARGE_LINES_NB = 7;

  /** L'inventaire */
  private McfrTileEntityLockable chestInventory;

  /**
   * Crée un conteneur non restreint.
   * 
   * @param playerInventory l'inventaire du joueur
   * @param chestInventory l'invetaire du conteneur
   * @param player le joueur
   */
  public ContainerRestrictable(IInventory playerInventory, McfrTileEntityLockable chestInventory, EntityPlayer player) {
    this(playerInventory, chestInventory, player, null);
  }

  /**
   * Crée un conteneur restreint.
   * 
   * @param playerInventory l'inventaire du joueur
   * @param chestInventory l'invetaire du conteneur
   * @param player le joueur
   * @param blockClass la classe du bloc si le conteneur doit être restreint, null sinon
   */
  public ContainerRestrictable(IInventory playerInventory, McfrTileEntityLockable chestInventory, EntityPlayer player,
      final Class<? extends Block> blockClass) {
    this.chestInventory = chestInventory;
    chestInventory.openInventory(player);

    int yOffset = 0;

    for (int i = 0; i < this.chestInventory.getLinesNumber(); i++) {
      yOffset += SLOT_SIZE;
      for (int j = 0; j < 9; j++) {
        addSlotToContainer(new Slot(chestInventory, i * 9 + j, SIDE_OFFSET + j * SLOT_SIZE, yOffset) {
          @Override
          public boolean isItemValid(ItemStack stack) {
            return ItemsLists.isItemValid(blockClass, stack);
          }
        });
      }
    }

    yOffset += TOP_OFFSET + INV_SEPARATOR;
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
    return this.chestInventory.getLinesNumber() * 9;
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

      if (index < getContainerSize()) {
        if (!mergeItemStack(current, getContainerSize(), this.inventorySlots.size(), true)) {
          return null;
        }
      }
      else if (slot.isItemValid(current)) {
        if (!mergeItemStack(current, 0, getContainerSize(), false)) {
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

  @Override
  public void onContainerClosed(EntityPlayer playerIn) {
    super.onContainerClosed(playerIn);
    this.chestInventory.closeInventory(playerIn);
  }
}
