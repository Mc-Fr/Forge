package net.mcfr.entities.mobs;

import net.mcfr.entities.mobs.gender.EntityGendered;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class EntityBurrowed extends EntityGendered {
  public EntityBurrowed (World worldIn) {
    super(worldIn);
    this.setBurrow(-1, this.posX, this.posY, this.posZ);
  }
  
  public void setBurrow(int burrowId, int homeX, int homeY, int homeZ) {
    this.setSyncedInteger("Burrow", burrowId);
    this.setSyncedInteger("HomeX", homeX);
    this.setSyncedInteger("HomeY", homeY);
    this.setSyncedInteger("HomeZ", homeZ);
  }
  
  public void setBurrow(int burrowId, double homeX, double homeY, double homeZ) {
    this.setBurrow(burrowId, (int) Math.floor(homeX), (int) Math.floor(homeY), (int) Math.floor(homeZ));
  }
  
  public int getBurrow() {
    return this.getSyncedInteger("Burrow");
  }
  
  public Vec3i getBurrowPosition() {
    return new Vec3i(this.getSyncedInteger("HomeX"), this.getSyncedInteger("HomeY"), this.getSyncedInteger("HomeZ"));
  }
  
  @Override
  protected boolean canDespawn() {
    return false;
  }
}
