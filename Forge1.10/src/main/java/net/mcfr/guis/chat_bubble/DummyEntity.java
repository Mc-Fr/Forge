package net.mcfr.guis.chat_bubble;

public class DummyEntity {
  private final double x, y, z;
  private final int id;

  public DummyEntity(double x, double y, double z, int id) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.id = id;
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

  public int getId() {
    return this.id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;

    result = prime * result + getId();
    temp = Double.doubleToLongBits(getX());
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(getY());
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(getZ());
    result = prime * result + (int) (temp ^ (temp >>> 32));

    return result;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof DummyEntity && getId() == ((DummyEntity) o).getId();
  }
}