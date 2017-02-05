package net.mcfr.entities.mobs;

import java.util.List;

import net.mcfr.entities.mobs.gender.EntityGendered;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
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
    setSyncedBoolean("ToRemove", false);
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
  
  public boolean mustRemove() {
    return getSyncedBoolean("ToRemove");
  }
  
  public void setToRemove(boolean value) {
    setBurrow(-2, getPosition());
    setSyncedBoolean("ToRemove", value);
  }

  @SideOnly(Side.SERVER)
  public void resetBurrowChange() {
    this.timesInLove = 0;
    this.changeBurrowLimit = this.rand.nextInt(3);
  }

  @SideOnly(Side.SERVER)
  public void increaseTimesInLove() {
    this.timesInLove++;
  }

  public void detachFromBurrow() {
    resetBurrowChange();
    this.setBurrow(-2, getPosition());
  }

  @Override
  public void onLivingUpdate() {
    if (getBurrow() == -1 && !this.worldObj.isRemote) {
      this.setBurrow(-2, getPosition());
    }
    if (getHealth() < getMaxHealth() && this.ticksExisted - this.lastHealTime > 40 && this.ticksExisted - getLastAttackerTime() > 100) {
      heal(1);
      this.lastHealTime = this.ticksExisted;
    }
    
    if (mustRemove()) {
      this.setDead();
    }
    
    super.onLivingUpdate();
  }
  
  public abstract List<ItemStack> getLoots();
  
  @Override
  protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
    getLoots().forEach(i -> this.entityDropItem(i, 0.0F));
  }
  
  public int getRandomQuantity(float value) {
    int baseValue = (int) Math.floor(value);
    float chance = value - baseValue;
    int bonusValue = (this.rand.nextFloat() < chance ? 1 : 0);
    return baseValue + bonusValue;
  }

  @Override
  public void setInLove(EntityPlayer player) {
    super.setInLove(player);
    if (!this.worldObj.isRemote) {
      increaseTimesInLove();
      if (this.timesInLove > this.changeBurrowLimit) {
        detachFromBurrow();
      }
    }
  }
}