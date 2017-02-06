package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * Item des dalles de métal.
 * 
 * @author Mc-Fr
 */
public class ItemMetalSlab extends McfrItemSlab<BlockMetalSlab> {
  /**
   * Crée un nouvel item.
   * 
   * @param block le bloc (dalle simple)
   * @param slab la dalle simple
   * @param doubleSlab la double dalle
   */
  public ItemMetalSlab(Block block, BlockMetalSlab slab, BlockMetalSlab doubleSlab) {
    super(block, slab, doubleSlab);
  }
}
