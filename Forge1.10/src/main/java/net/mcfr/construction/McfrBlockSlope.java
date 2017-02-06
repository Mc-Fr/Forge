package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * La classe de base des pentes du mod.
 * 
 * @author Mc-Fr
 */
public class McfrBlockSlope extends McfrBlockInclined {
  /**
   * Crée une nouvelle pente.
   * 
   * @param block le bloc de base
   * @param metadata le metadata du bloc
   * @param name le nom (sans le suffixe '_slope')
   * @param tool l'outil nécessaire
   * @param harvestLevel le niveau de récolte
   */
  public McfrBlockSlope(Block block, int metadata, String name, String tool, int harvestLevel) {
    super(block, metadata, name, "slope", tool, harvestLevel);
  }
}