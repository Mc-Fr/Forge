package net.mcfr.craftsmanship.tile_entities;

import java.util.Map;

import net.mcfr.decoration.container_blocks.tile_entities.TileEntityRestricted;
import net.mcfr.utils.HashedItemStack;
import net.mcfr.utils.ItemsLists;
import net.mcfr.utils.NBTUtils;
import net.minecraft.block.Block;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

/**
 * Classe de base des tile entities des blocs artisan.
 *
 * @author Mc-Fr
 */
public abstract class TileEntityRack extends TileEntityRestricted implements ITickable {
  private static final int TRANSFORM_TIME = 20;

  /** L'item en train d'être transformé */
  private ItemStack currentStack;
  /** Le nombre de ticks restant pour la transformation */
  private int remainingTicks;
  /** L'orientation du bloc */
  private EnumFacing facing;

  /**
   * Crée une nouvelle tile entity.
   * 
   * @param name le nom
   * @param facing l'orientation
   * @param blockClass la classe du bloc correspondant
   * @param containerClass la classe du conteneur correspondant
   */
  public TileEntityRack(String name, EnumFacing facing, Class<? extends Block> blockClass, Class<? extends Container> containerClass) {
    super(name, 2, 64, false, blockClass, containerClass);
    this.currentStack = null;
    this.remainingTicks = TRANSFORM_TIME;
    this.facing = facing;
  }

  /**
   * @return l'orientation du bloc
   */
  public EnumFacing getFacing() {
    return this.facing;
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

  /**
   * Retourne la quantité nécessaire d'un item pour qu'il puisse être transformé.
   * 
   * @param stack le stack
   * @return la quantité nécessaire, ou une {@code IllegalStateException} si le stack est invalide
   */
  private int getNecessaryAmount(ItemStack stack) {
    for (HashedItemStack s : ItemsLists.getMapForClass(getBlockClass()).keySet()) {
      if (stack.getItem() == s.getStack().getItem() && stack.getMetadata() == s.getStack().getMetadata())
        return s.getStack().stackSize;
    }

    throw new IllegalStateException("Stack invalide : " + stack);
  }

  /**
   * Retourne l'espace nécessaire pour la transformation de l'item donné.
   * 
   * @param stack le stack
   * @return l'espace nécessaire
   */
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

  /**
   * @return l'espace disponible dans la case de sortie
   */
  private int getFreeOutputSpace() {
    ItemStack out = getStackInSlot(1);
    return out == null ? 64 : out.getItem().getItemStackLimit(out) - out.stackSize;
  }

  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    return index == 1 || stack == null || ItemsLists.isItemValid(getBlockClass(), stack);
  }

  /**
   * @return la progression de la transformation (entre 0 et 1)
   */
  public float getProgress() {
    return (float) (TRANSFORM_TIME - this.remainingTicks) / TRANSFORM_TIME;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.facing = EnumFacing.getHorizontal(compound.getInteger("Facing"));
    this.remainingTicks = compound.getInteger("RemainingTicks");
    this.currentStack = compound.hasKey("CurrentStack", NBTUtils.TAG_COMPOUND) ? ItemStack.loadItemStackFromNBT(compound.getCompoundTag("CurrentStack")) : null;
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setInteger("Facing", this.facing.getHorizontalIndex());
    compound.setInteger("RemainingTicks", this.remainingTicks);
    if (this.currentStack != null)
      compound.setTag("CurrentStack", this.currentStack.writeToNBT(new NBTTagCompound()));

    return compound;
  }
}
