package net.mcfr.decoration.container_blocks.tile_entities;

import java.lang.reflect.InvocationTargetException;

import net.mcfr.utils.ItemsLists;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public abstract class TileEntityRestricted extends McfrTileEntityLockable {
  private final Class<? extends Block> blockClass;
  private final Class<? extends Container> containerClass;

  /**
   * Crée une nouvelle Tile Entity.
   * 
   * @param name le nom
   * @param size le nombre d'emplacement
   * @param stackSize la taille maximale de chaque stack
   * @param playSounds indique si un son doit être joué à l'ouverture et la fermeture du conteneur
   * @param blockClass la classe du bloc associé
   * @param containerClass la classe du conteneur associé
   * @param authorized la liste des items autorisés
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

  protected Class<? extends Block> getBlockClass() {
    return this.blockClass;
  }
}
