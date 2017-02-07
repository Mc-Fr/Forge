package net.mcfr.utils.math;

/**
 * Classe représentant un point en 2D.
 *
 * @author Mc-Fr
 */
public final class Point2d {
  private final double x, y;

  /**
   * Copie un point.
   * 
   * @param point le point à copier
   */
  public Point2d(Point2d point) {
    this(point.getX(), point.getY());
  }

  public Point2d(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  /**
   * Ajoute une valeur à la composante x.
   * 
   * @param tx la valeur
   * @return le nouveau point
   */
  public Point2d addX(double tx) {
    return new Point2d(this.x + tx, this.y);
  }

  /**
   * Ajoute une valeur à la composante y.
   * 
   * @param ty la valeur
   * @return le nouveau point
   */
  public Point2d addY(double ty) {
    return new Point2d(this.x, this.y + ty);
  }
}
