package net.mcfr.environment.plants.tree_gen;

import net.minecraft.util.math.BlockPos;

/**
 * Cette classe représente une sphère de feuilles.
 *
 * @author Mc-Fr
 */
class LeafSphere {
  /** La position */
  private final BlockPos pos;
  private final int diameter, minOverflow, maxOverflow, ratio, smear;

  /**
   * Crée une sphère de feuille.
   * 
   * @param pos la position
   * @param diameter le diamètre
   * @param minOverflow le débordement min
   * @param ratio le ratio
   * @param smear la "coulure"
   */
  public LeafSphere(BlockPos pos, int diameter, int minOverflow, int ratio, int smear) {
    this(pos, diameter, minOverflow, 0, ratio, smear);
  }

  /**
   * Crée une sphère de feuille.
   * 
   * @param pos la position
   * @param diameter le diamètre
   * @param minOverflow le débordement min
   * @param maxOverflow le débordement min
   * @param ratio le ratio
   * @param smear la "coulure"
   */
  public LeafSphere(BlockPos pos, int diameter, int minOverflow, int maxOverflow, int ratio, int smear) {
    this.pos = pos;
    this.diameter = diameter;
    this.minOverflow = minOverflow;
    this.maxOverflow = maxOverflow;
    this.ratio = ratio;
    this.smear = smear;
  }

  /**
   * @return la position
   */
  public BlockPos getPos() {
    return this.pos;
  }

  /**
   * @return le diamètre
   */
  public int getDiameter() {
    return this.diameter;
  }

  /**
   * @return le débordement min
   */
  public int getMinOverflow() {
    return this.minOverflow;
  }

  /**
   * @return le débordement max
   */
  public int getMaxOverflow() {
    return this.maxOverflow;
  }

  /**
   * @return le ratio
   */
  public int getRatio() {
    return this.ratio;
  }

  /**
   * @return la "coulure"
   */
  public int getSmear() {
    return this.smear;
  }
}