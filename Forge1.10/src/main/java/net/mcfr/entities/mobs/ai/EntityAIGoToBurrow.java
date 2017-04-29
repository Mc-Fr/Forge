package net.mcfr.entities.mobs.ai;

import net.mcfr.entities.mobs.EntityBurrowed;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

/**
 * Cycle d'animation lorsque l'entité se dirige vers son terrier.
 *
 * @author Mc-Fr
 */
public class EntityAIGoToBurrow extends EntityAIBase {
  private final EntityBurrowed entity;
  private final double speed;
  private final int burrowSize;
  private Vec3d targetPos;

  public EntityAIGoToBurrow(EntityCreature creatureIn, double speedIn, int burrowSize) {
    this.entity = (EntityBurrowed) creatureIn;
    this.speed = speedIn;
    this.burrowSize = burrowSize;
    this.setMutexBits(1);
  }

  /**
   * Returns whether the EntityAIBase should begin execution.
   */
  @Override
  public boolean shouldExecute() {
    if (!this.entity.worldObj.isDaytime()) {
      Vec3d burrowPos = this.entity.getBurrowPosition();
      if (this.entity.getPositionVector().distanceTo(burrowPos) > this.burrowSize) {
        this.targetPos = RandomPositionGenerator.findRandomTargetBlockTowards(this.entity, this.burrowSize, 1, burrowPos);
        return this.targetPos != null;
      }
    }
    return false;
  }

  /**
   * Returns whether an in-progress EntityAIBase should continue executing
   */
  @Override
  public boolean continueExecuting() {
    return !this.entity.getNavigator().noPath();
  }

  /**
   * Execute a one shot task or start executing a continuous task
   */
  @Override
  public void startExecuting() {
    this.entity.getNavigator().tryMoveToXYZ(this.targetPos.xCoord, this.targetPos.yCoord, this.targetPos.zCoord, this.speed);
  }
}
