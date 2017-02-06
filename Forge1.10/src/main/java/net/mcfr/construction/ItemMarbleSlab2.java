package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * Item des dalles de marbre (2).
 * 
 * @author Mc-Fr
 */
public class ItemMarbleSlab2 extends McfrItemSlab<BlockMarbleSlab2> {
  /**
   * Crée un nouvel item.
   * 
   * @param block le bloc (dalle simple)
   * @param slab la dalle simple
   * @param doubleSlab la double dalle
   */
  public ItemMarbleSlab2(Block block, BlockMarbleSlab2 slab, BlockMarbleSlab2 doubleSlab) {
    super(block, slab, doubleSlab);
  }
}
