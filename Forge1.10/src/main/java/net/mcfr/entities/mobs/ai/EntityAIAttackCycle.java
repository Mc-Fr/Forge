package net.mcfr.entities.mobs.ai;

import net.mcfr.entities.mobs.entity.EntitySiker;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIAttackCycle extends EntityAIAttackMelee {
  private EntitySiker entity;
  private final int ticksAttackingDelay;
  private int ticksAttacking;
  
  public EntityAIAttackCycle (EntitySiker creature, double speedIn, boolean useLongMemory, int ticksAttacking) {
    super(creature, speedIn, useLongMemory);
    this.entity = creature;
    this.ticksAttacking = ticksAttacking;
    this.ticksAttackingDelay = ticksAttacking;
  }
  
  @Override
  public boolean shouldExecute() {
    if (super.shouldExecute() && this.entity.getCycle() == AICycle.IDLE) {
      this.entity.setCycle(AICycle.ATTACKING);
      return true;
    }
    return false;
  }
  
  @Override
  public void startExecuting() {
    this.ticksAttacking = this.ticksAttackingDelay;
  }
  
  @Override
  public boolean continueExecuting() {
    if (this.ticksAttacking-- > 0 && super.continueExecuting()) {
      return true;
    } else {
      this.entity.setCycle(AICycle.MUSTFLEE);
      return false;
    }
  }
}
