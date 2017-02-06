package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * Item des dalles de planches raffinées.
 * 
 * @author Mc-Fr
 */
public class ItemRefinedPlanksSlab extends McfrItemSlab<BlockRefinedPlanksSlab> {
  /**
   * Crée un nouvel item.
   * 
   * @param block le bloc (dalle simple)
   * @param slab la dalle simple
   * @param doubleSlab la double dalle
   */
  public ItemRefinedPlanksSlab(Block block, BlockRefinedPlanksSlab slab, BlockRefinedPlanksSlab doubleSlab) {
    super(block, slab, doubleSlab);
  }
}
