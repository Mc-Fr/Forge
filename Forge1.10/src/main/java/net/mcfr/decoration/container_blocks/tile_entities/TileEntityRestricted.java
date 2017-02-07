package net.mcfr.decoration.container_blocks.tile_entities;

import java.lang.reflect.InvocationTargetException;

import net.mcfr.utils.ItemsLists;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Tile entity permettant de restraindre le stockage de certains items.
 *
 * @author Mc-Fr
 */
public abstract class TileEntityRestricted extends McfrTileEntityLockable {
  /** La classe du bloc */
  private final Class<? extends Block> blockClass;
  /** La classe du conteneur */
  private final Class<? extends Container> containerClass;

  /**
   * Crée une nouvelle tile entity.
   * 
   * @param name le nom
   * @param size la taille de l'inventaire
   * @param stackSize la taille maximale des stacks
   * @param playSounds indique si un son doit être joué à l'ouverture et la fermeture du conteneur
   * @param blockClass la classe du bloc
   * @param containerClass la classe du conteneur. Elle doit posséder un constructeur ayant la
   *          signature suivante : {@code <init>(IInventory, IInventory, EntityPlayer, Class)}
   */
  public TileEntityRestricted(String name, int size, int stackSize, boolean playSounds, Class<? extends Block> blockClass, Class<? extends Container> containerClass) {
    super(name, size, stackSize, playSounds);
    this.blockClass = blockClass;
    this.containerClass = containerClass;
  }

  @Override
  public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
    try {
      return this.containerClass.getConstructor(IInventory.class, IInventory.class, EntityPlayer.class, Class.class).newInstance(this, playerInventory, playerIn, this.blockClass);
    }
    catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    return stack == null || ItemsLists.isItemValid(this.blockClass, stack);
  }

  /**
   * @return la classe du bloc correspondant
   */
  protected Class<? extends Block> getBlockClass() {
    return this.blockClass;
  }
}
