package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * Item des dalles de paille.
 * 
 * @author Mc-Fr
 */
public class ItemHaySlab extends McfrItemSlab<BlockHaySlab> {
  /**
   * Crée un nouvel item.
   * 
   * @param block le bloc (dalle simple)
   * @param slab la dalle simple
   * @param doubleSlab la double dalle
   */
  public ItemHaySlab(Block block, BlockHaySlab slab, BlockHaySlab doubleSlab) {
    super(block, slab, doubleSlab);
  }
}
