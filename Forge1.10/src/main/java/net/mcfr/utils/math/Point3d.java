package net.mcfr.utils.math;

public class Point3d {
  private double x, y, z;
  
  public Point3d(Point3d point) {
    this(point.getX(), point.getY(), point.getZ());
  }
  
  public Point3d(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public double getX() {
    return x;
  }
  
  public double getY() {
    return y;
  }
  
  public double getZ() {
    return z;
  }
  
  public Point3d addX(double tx) {
    return new Point3d(x + tx, y, z);
  }
  
  public Point3d addY(double ty) {
    return new Point3d(x, y + ty, z);
  }
  
  public Point3d addZ(double tz) {
    return new Point3d(x, y, z + tz);
  }
}
