package net.mcfr.construction;

import net.minecraft.block.Block;

/**
 * Classe de base des esaliers du mod.
 * 
 * @author Mc-Fr
 */
public class McfrBlockStairs extends McfrBlockInclined {
  /**
   * Crée un nouvel escalier.
   * 
   * @param block le bloc de base
   * @param metadata le metadata du bloc
   * @param name le nom (sans le suffixe '_stairs')
   * @param tool l'outil nécessaire
   * @param harvestLevel le niveau de récolte
   */
  public McfrBlockStairs(Block block, int metadata, String name, String tool, int harvestLevel) {
    super(block, metadata, name, "stairs", tool, harvestLevel);
  }
}