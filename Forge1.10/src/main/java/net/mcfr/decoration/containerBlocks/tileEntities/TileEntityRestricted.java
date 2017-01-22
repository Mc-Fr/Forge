package net.mcfr.decoration.containerBlocks.tileEntities;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class TileEntityRestricted extends McfrTileEntityLockable {
  private final List<Item> authorized;
  private final Class<? extends Container> containerClass;
  private final Class<? extends Block> blockClass;
  
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
  public TileEntityRestricted(String name, int size, int stackSize, boolean playSounds, Class<? extends Block> blockClass, Class<? extends Container> containerClass, List<Item> authorized) {
    super(name, size, stackSize, playSounds);
    this.authorized = Collections.unmodifiableList(authorized);
    this.containerClass = containerClass;
    this.blockClass = blockClass;
  }
  
  @Override
  public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
    Exception ex = null;
    
    try {
      return containerClass.getConstructor(IInventory.class, IInventory.class, EntityPlayer.class, Class.class).newInstance(this, playerInventory, playerIn, blockClass);
    }
    catch (InstantiationException e) {
      ex = e;
    }
    catch (IllegalAccessException e) {
      ex = e;
    }
    catch (IllegalArgumentException e) {
      ex = e;
    }
    catch (InvocationTargetException e) {
      ex = e;
    }
    catch (NoSuchMethodException e) {
      ex = e;
    }
    catch (SecurityException e) {
      ex = e;
    }
    
    throw new RuntimeException(ex);
  }
  
  @Override
  public boolean isItemValidForSlot(int index, ItemStack stack) {
    return stack == null || authorized.contains(stack.getItem());
  }
}
