package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * Pyramides de feuilles.
 * 
 * @author Mc-Fr
 */
public class McfrBlockLeavesPyramid extends McfrBlockPyramid {
  /**
   * Crée une nouvelle pyramide de feuilles.
   * 
   * @param block le bloc de base
   * @param metadata le metadta du bloc de base
   * @param name le nom (sans le suffixe '_leaves_pyramid')
   */
  public McfrBlockLeavesPyramid(Block block, int metadata, String name) {
    super(block, metadata, 2, 0, name + "_leaves", null, -1);
  }
}
