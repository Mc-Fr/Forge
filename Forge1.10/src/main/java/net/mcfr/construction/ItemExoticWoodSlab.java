package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * Item des dalles de bois exotique.
 * 
 * @author Mc-Fr
 */
public class ItemExoticWoodSlab extends McfrItemSlab<BlockExoticWoodSlab> {
  /**
   * Crée un nouvel item.
   * 
   * @param block le bloc (dalle simple)
   * @param slab la dalle simple
   * @param doubleSlab la double dalle
   */
  public ItemExoticWoodSlab(Block block, BlockExoticWoodSlab slab, BlockExoticWoodSlab doubleSlab) {
    super(block, slab, doubleSlab);
  }
}
