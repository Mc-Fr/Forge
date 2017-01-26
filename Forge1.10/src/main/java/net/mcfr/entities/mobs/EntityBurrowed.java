package net.mcfr.entities.mobs;

import net.mcfr.entities.mobs.gender.EntityGendered;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class EntityBurrowed extends EntityGendered {
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
  
  public Vec3d getBurrowPosition() {
    return new Vec3d(this.getSyncedInteger("HomeX"), this.getSyncedInteger("HomeY"), this.getSyncedInteger("HomeZ"));
  }
  
  @Override
  public void onLivingUpdate() {
    if (this.getBurrow() == -1 && !this.worldObj.isRemote) {
      this.setBurrow(-2, this.posX, this.posY, this.posZ);
    }
    super.onLivingUpdate();
  }
  
  @Override
  protected boolean canDespawn() {
    return false;
  }
}
