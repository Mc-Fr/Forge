package net.mcfr.entities.mobs;

import net.mcfr.entities.mobs.gender.EntityGendered;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityBurrowed extends EntityGendered {
  private int lastHealTime;
  @SideOnly(Side.SERVER)
  private int timesInLove;
  @SideOnly(Side.SERVER)
  private int changeBurrowLimit;

  public EntityBurrowed(World worldIn) {
    super(worldIn);
    this.setBurrow(-1, this.getPosition());
    if (!this.worldObj.isRemote) {
      this.resetBurrowChange();
    }
  }

  public void setBurrow(int burrowId, int homeX, int homeY, int homeZ) {
    this.setSyncedInteger("Burrow", burrowId);
    this.setSyncedInteger("HomeX", homeX);
    this.setSyncedInteger("HomeY", homeY);
    this.setSyncedInteger("HomeZ", homeZ);
  }

  public void setBurrow(int burrowId, BlockPos position) {
    this.setBurrow(burrowId, position.getX(), position.getY(), position.getZ());
  }

  public int getBurrow() {
    return this.getSyncedInteger("Burrow");
  }

  public Vec3d getBurrowPosition() {
    return new Vec3d(this.getSyncedInteger("HomeX"), this.getSyncedInteger("HomeY"), this.getSyncedInteger("HomeZ"));
  }

  @SideOnly(Side.SERVER)
  public void resetBurrowChange() {
    this.timesInLove = 0;
    this.changeBurrowLimit = this.rand.nextInt(3) + 1;
  }

  @SideOnly(Side.SERVER)
  public void increaseTimesInLove() {
    this.timesInLove++;
  }

  public void setNewBurrow() {
    this.resetBurrowChange();
    System.out.println(this.getUniqueID());
    this.setUniqueId(MathHelper.getRandomUuid(this.rand));
    System.out.println(this.getUniqueID());
    this.setBurrow(-2, this.getPosition());
  }

  @Override
  public void onLivingUpdate() {
    if (this.getBurrow() == -1 && !this.worldObj.isRemote) {
      this.setBurrow(-2, this.getPosition());
    }
    if (this.getHealth() < this.getMaxHealth() && (this.ticksExisted - this.lastHealTime) > 40
        && (this.ticksExisted - this.getLastAttackerTime()) > 100) {
      this.heal(1);
      System.out.println("Healed ! Nouvelle santé : " + this.getHealth());
      this.lastHealTime = this.ticksExisted;
    }
    super.onLivingUpdate();
  }

  @Override
  public void setInLove(EntityPlayer player) {
    super.setInLove(player);
    if (!this.worldObj.isRemote) {
      this.increaseTimesInLove();
      if (this.timesInLove > 0) {
        this.setNewBurrow();
      }
    }
  }

  @Override
  protected boolean canDespawn() {
    return false;
  }
}
