package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * Item des dalles de pierre.
 * 
 * @author Mc-Fr
 */
public class ItemStoneSlab extends McfrItemSlab<BlockStoneSlab> {
  /**
   * Crée un nouvel item.
   * 
   * @param block le bloc (dalle simple)
   * @param slab la dalle simple
   * @param doubleSlab la double dalle
   */
  public ItemStoneSlab(Block block, BlockStoneSlab slab, BlockStoneSlab doubleSlab) {
    super(block, slab, doubleSlab);
  }
}
