package net.mcfr.utils.math;

public class Point2d {
  private double x, y;
  
  public Point2d(Point2d point) {
    this(point.getX(), point.getY());
  }
  
  public Point2d(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public double getX() {
    return x;
  }
  
  public double getY() {
    return y;
  }
  
  public Point2d addX(double tx) {
    return new Point2d(x + tx, y);
  }
  
  public Point2d addY(double ty) {
    return new Point2d(x, y + ty);
  }
}
