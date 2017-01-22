package net.mcfr.commons;

/**
 * Tout bloc ayant des items avec metadata doit implémenter cette interface.
 * 
 * @author Mc-Fr
 */
public interface IBlockWithVariants {
  /**
   * @return le nom de la variante pour le stack avec le metadata donné
   */
  String getVariantName(int meta);
}