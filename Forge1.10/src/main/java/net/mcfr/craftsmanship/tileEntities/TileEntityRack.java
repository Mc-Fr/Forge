package net.mcfr.craftsmanship.tileEntities;

import java.util.Map;

import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityRestricted;
import net.mcfr.utils.HashedItemStack;
import net.mcfr.utils.ItemsLists;
import net.minecraft.block.Block;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public abstract class TileEntityRack extends TileEntityRestricted implements ITickable {
  private static final int TRANSFORM_TIME = 20;

  private ItemStack currentStack;
  private int remainingTicks;

  public TileEntityRack(String name, Class<? extends Block> blockClass, Class<? extends Container> containerClass) {
    super(name, 2, 64, false, blockClass, containerClass);
    this.currentStack = null;
    this.remainingTicks = TRANSFORM_TIME;
  }

  @Override
  public void update() {
    ItemStack in = getStackInSlot(0);

    if (checkOutput() && this.remainingTicks == TRANSFORM_TIME) {
      int amount = getNecessaryAmount(in);

      if (in.stackSize >= amount && getFreeOutputSpace() >= getNecessaryOutSpace(in)) {
        in.stackSize -= amount;
        if (in.stackSize == 0)
          setInventorySlotContents(0, null);
        this.currentStack = in.copy();
        this.currentStack.stackSize = amount;
        this.remainingTicks--;
      }
    }
    else if (this.currentStack != null) {
      if (this.remainingTicks == 0) {
        HashedItemStack hashedStack = ItemsLists.getMapForClass(getBlockClass()).get(HashedItemStack.fromStack(this.currentStack));

        if (hashedStack != null) {
          ItemStack product = hashedStack.getStack();
          ItemStack out = getStackInSlot(1);

          if (out != null)
            out.stackSize += product.stackSize;
          else
            setInventorySlotContents(1, product.copy());
          this.currentStack = null;
          this.remainingTicks = TRANSFORM_TIME;
        }
      }
      else {
        this.remainingTicks--;
      }
    }
  }

  private int getNecessaryAmount(ItemStack stack) {
    for (HashedItemStack s : ItemsLists.getMapForClass(getBlockClass()).keySet()) {
      if (stack.getItem() == s.getStack().getItem() && stack.getMetadata() == s.getStack().getMetadata())
        return s.getStack().stackSize;
    }

    throw new IllegalStateException("Stack invalide : " + stack);
  }

  private int getNecessaryOutSpace(ItemStack stack) {
    for (Map.Entry<HashedItemStack, HashedItemStack> entry : ItemsLists.getMapForClass(getBlockClass()).entrySet()) {
      ItemStack stackIn = entry.getKey().getStack();

      if (stack.getItem() == stackIn.getItem() && stack.getMetadata() == stackIn.getMetadata())
        return entry.getValue().getStack().stackSize;
    }

    return -1;
  }

  /**
   * Vérifie que le stack déjà présent en sortie est compatible avec le stack transformé.
   */
  private boolean checkOutput() {
    ItemStack in = getStackInSlot(0);

    if (in != null) {
      HashedItemStack hashedStack = null;

      for (Map.Entry<HashedItemStack, HashedItemStack> entry : ItemsLists.getMapForClass(getBlockClass()).entrySet()) {
        ItemStack stackIn = entry.getKey().getStack();

        if (in.getItem() == stackIn.getItem() && in.getMetadata() == stackIn.getMetadata())
          hashedStack = entry.getValue();
      }

      if (hashedStack != null) {
        ItemStack stack = hashedStack.getStack();
        ItemStack out = getStackInSlot(1);

        return out == null || stack != null && out.getItem() == stack.getItem();
      }
    }

    return false;
  }

  private int getFreeOutputSpace() {
    ItemStack out = getStackInSlot(1);
    return out == null ? 64 : out.getItem().getItemStackLimit(out) - out.stackSize;
  }

  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    return index == 1 || stack == null || ItemsLists.isItemValid(getBlockClass(), stack);
  }

  public float getProgress() {
    return (float) (TRANSFORM_TIME - this.remainingTicks) / TRANSFORM_TIME;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.remainingTicks = compound.getInteger("RemainingTicks");
    this.currentStack = compound.hasKey("CurrentStack") ? ItemStack.loadItemStackFromNBT(compound.getCompoundTag("CurrentStack")) : null;
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setLong("RemainingTicks", this.remainingTicks);
    if (this.currentStack != null)
      compound.setTag("CurrentStack", this.currentStack.writeToNBT(new NBTTagCompound()));

    return compound;
  }
}
