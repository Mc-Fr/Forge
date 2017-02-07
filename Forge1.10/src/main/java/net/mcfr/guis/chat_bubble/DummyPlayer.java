package net.mcfr.guis.chat_bubble;

import java.util.Objects;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class DummyPlayer {
  public static DummyPlayer getFromPlayer() {
    EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    return new DummyPlayer(player.posX, player.posY, player.posZ, player.getUniqueID());
  }

  private final double x, y, z;
  private final UUID uuid;

  public DummyPlayer(double x, double y, double z, UUID uuid) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.uuid = Objects.requireNonNull(uuid);
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

  public UUID getUniqueId() {
    return this.uuid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;

    result = prime * result + getUniqueId().hashCode();
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
    return o instanceof DummyPlayer && getUniqueId().equals(((DummyPlayer) o).getUniqueId());
  }
}