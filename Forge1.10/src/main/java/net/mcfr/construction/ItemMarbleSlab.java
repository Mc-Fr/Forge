package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * Item des dalles de marbre.
 * 
 * @author Mc-Fr
 */
public class ItemMarbleSlab extends McfrItemSlab<BlockMarbleSlab> {
  /**
   * Crée un nouvel item.
   * 
   * @param block le bloc (dalle simple)
   * @param slab la dalle simple
   * @param doubleSlab la double dalle
   */
  public ItemMarbleSlab(Block block, BlockMarbleSlab slab, BlockMarbleSlab doubleSlab) {
    super(block, slab, doubleSlab);
  }
}
