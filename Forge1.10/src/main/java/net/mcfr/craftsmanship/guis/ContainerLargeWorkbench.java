package net.mcfr.craftsmanship.guis;

import static net.mcfr.utils.RenderUtils.*;

import net.mcfr.forge.recipes.LargeRecipesHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerLargeWorkbench extends Container {
  private InventoryCrafting craftMatrix = new InventoryCrafting(this, 5, 5);
  private IInventory craftResult = new InventoryCraftResult();
  private World world;
  private BlockPos pos;

  public ContainerLargeWorkbench(InventoryPlayer playerInventory, World worldIn, BlockPos posIn) {
    this.world = worldIn;
    this.pos = posIn;

    addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 130, 54));
    int yOffset = TOP_OFFSET;

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        addSlotToContainer(new Slot(this.craftMatrix, i * 5 + j, SIDE_OFFSET + j * SLOT_SIZE, yOffset + 1));
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

    onCraftMatrixChanged(this.craftMatrix);
  }

  protected InventoryCrafting getCraftMatrix() {
    return this.craftMatrix;
  }

  protected IInventory getCraftResult() {
    return this.craftResult;
  }

  protected World getWorld() {
    return this.world;
  }

  protected BlockPos getPos() {
    return this.pos;
  }

  @Override
  public void onCraftMatrixChanged(IInventory inventoryIn) {
    this.craftResult.setInventorySlotContents(0, LargeRecipesHandler.findMatchingRecipe(this.craftMatrix, this.world));
  }

  @Override
  public void onContainerClosed(EntityPlayer playerIn) {
    super.onContainerClosed(playerIn);

    if (!this.world.isRemote) {
      for (int i = 0; i < 25; ++i) {
        ItemStack stack = this.craftMatrix.removeStackFromSlot(i);

        if (stack != null) {
          playerIn.dropItem(stack, false);
        }
      }
    }
  }

  @Override
  public boolean canInteractWith(EntityPlayer playerIn) {
    return playerIn.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64;
  }

  @Override
  public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
    ItemStack stack = null;
    Slot slot = this.inventorySlots.get(index);

    if (slot != null && slot.getHasStack()) {
      ItemStack stack1 = slot.getStack();
      stack = stack1.copy();

      if (index == 0) {
        if (!mergeItemStack(stack1, 26, 62, true)) {
          return null;
        }

        slot.onSlotChange(stack1, stack);
      }
      else if (index >= 1 && index < 26) {
        if (!mergeItemStack(stack1, 26, 62, false)) {
          return null;
        }
      }
      else if (index >= 26 && index < 53) {
        if (!mergeItemStack(stack1, 53, 62, false)) {
          return null;
        }
      }
      else if (!mergeItemStack(stack1, 26, 53, false)) {
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
