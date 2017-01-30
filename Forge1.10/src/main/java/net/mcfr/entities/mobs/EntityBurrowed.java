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
    this.setBurrow(-1, getPosition());
    if (!this.worldObj.isRemote) {
      resetBurrowChange();
    }
  }

  public void setBurrow(int burrowId, int homeX, int homeY, int homeZ) {
    setSyncedInteger("Burrow", burrowId);
    setSyncedInteger("HomeX", homeX);
    setSyncedInteger("HomeY", homeY);
    setSyncedInteger("HomeZ", homeZ);
  }

  public void setBurrow(int burrowId, BlockPos position) {
    this.setBurrow(burrowId, position.getX(), position.getY(), position.getZ());
  }

  public int getBurrow() {
    return getSyncedInteger("Burrow");
  }

  public Vec3d getBurrowPosition() {
    return new Vec3d(getSyncedInteger("HomeX"), getSyncedInteger("HomeY"), getSyncedInteger("HomeZ"));
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
    resetBurrowChange();
    System.out.println(getUniqueID());
    setUniqueId(MathHelper.getRandomUuid(this.rand));
    System.out.println(getUniqueID());
    this.setBurrow(-2, getPosition());
  }

  @Override
  public void onLivingUpdate() {
    if (getBurrow() == -1 && !this.worldObj.isRemote) {
      this.setBurrow(-2, getPosition());
    }
    if (getHealth() < getMaxHealth() && this.ticksExisted - this.lastHealTime > 40 && this.ticksExisted - getLastAttackerTime() > 100) {
      heal(1);
      System.out.println("Healed ! Nouvelle santé : " + getHealth());
      this.lastHealTime = this.ticksExisted;
    }
    super.onLivingUpdate();
  }

  @Override
  public void setInLove(EntityPlayer player) {
    super.setInLove(player);
    if (!this.worldObj.isRemote) {
      increaseTimesInLove();
      if (this.timesInLove > 0) {
        setNewBurrow();
      }
    } else {
      if (this.timesInLove > 0) {
        this.setUniqueId(MathHelper.getRandomUuid(this.rand));
      }
    }
  }
}