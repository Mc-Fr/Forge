package net.mcfr.entities.mobs.ai;

import net.mcfr.entities.mobs.entity.EntitySiker;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAvoidEntity;

/**
 * Cycle d'évitement du Siker.
 * 
 * @author Mc-Fr
 *
 * @param <T> type de l'entité à éviter
 */
public class EntityAiAvoidEntityCycle<T extends EntityLivingBase> extends EntityAIAvoidEntity<T> {
  private EntitySiker entity;
  private final int ticksAvoidingDelay;
  private int ticksAvoiding;
  private boolean isBeginning;

  public EntityAiAvoidEntityCycle(EntitySiker theEntityIn, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn,
      int ticksAvoiding) {
    super(theEntityIn, classToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
    this.entity = theEntityIn;
    this.ticksAvoidingDelay = ticksAvoiding;
    this.ticksAvoiding = ticksAvoiding;
  }

  @Override
  public boolean shouldExecute() {
    if (this.entity.ticksExisted - this.entity.getLastAttackerTime() < 20) {
      this.entity.setCycle(AiCycle.IDLE);
      return false;
    }

    boolean shouldExecute = super.shouldExecute();

    if (this.entity.getCycle() == AiCycle.MUST_FLEE) {
      if (shouldExecute) {
        this.entity.setCycle(AiCycle.FLEEING);
        this.isBeginning = true;
        return true;
      }
    }
    if (this.entity.getCycle() == AiCycle.FLEEING) {
      this.ticksAvoiding--;
      if (this.ticksAvoiding > 0) {
        return shouldExecute;
      }
      else {
        this.entity.setCycle(AiCycle.IDLE);
      }
    }
    return false;
  }

  @Override
  public void startExecuting() {
    super.startExecuting();
    if (this.isBeginning) {
      this.ticksAvoiding = this.ticksAvoidingDelay;
      this.isBeginning = false;
    }
  }

  @Override
  public boolean continueExecuting() {
    if (this.entity.ticksExisted - this.entity.getLastAttackerTime() < 20) {
      this.entity.setCycle(AiCycle.IDLE);
      return false;
    }
    if (this.ticksAvoiding-- > 0) {
      return super.continueExecuting() && (this.entity.ticksExisted - this.entity.getLastAttackerTime()) > 20;
    }
    else {
      this.entity.setCycle(AiCycle.IDLE);
    }
    return false;
  }
}
