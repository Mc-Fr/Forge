package net.mcfr.utils.math;

/**
 * Classe représentant un point en 2D.
 *
 * @author Mc-Fr
 */
public final class Point3d {
  private final double x, y, z;

  /**
   * Copie un point.
   * 
   * @param point le point à copier
   */
  public Point3d(Point3d point) {
    this(point.getX(), point.getY(), point.getZ());
  }

  public Point3d(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public double getZ() {
    return this.z;
  }

  /**
   * Ajoute une valeur à la composante x.
   * 
   * @param tx la valeur
   * @return le nouveau point
   */
  public Point3d addX(double tx) {
    return new Point3d(this.x + tx, this.y, this.z);
  }

  /**
   * Ajoute une valeur à la composante y.
   * 
   * @param ty la valeur
   * @return le nouveau point
   */
  public Point3d addY(double ty) {
    return new Point3d(this.x, this.y + ty, this.z);
  }

  /**
   * Ajoute une valeur à la composante z.
   * 
   * @param tz la valeur
   * @return le nouveau point
   */
  public Point3d addZ(double tz) {
    return new Point3d(this.x, this.y, this.z + tz);
  }
}
