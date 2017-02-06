package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * Item des dalles de briques de pierre.
 * 
 * @author Mc-Fr
 */
public class ItemStonebrickSlab extends McfrItemSlab<BlockStonebrickSlab> {
  /**
   * Crée un nouvel item.
   * 
   * @param block le bloc (dalle simple)
   * @param slab la dalle simple
   * @param doubleSlab la double dalle
   */
  public ItemStonebrickSlab(Block block, BlockStonebrickSlab slab, BlockStonebrickSlab doubleSlab) {
    super(block, slab, doubleSlab);
  }
}
