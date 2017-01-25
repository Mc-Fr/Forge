package net.mcfr.entities.mobs.gender;

import net.mcfr.entities.mobs.EntitySyncedAnimal;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public abstract class EntityGendered extends EntitySyncedAnimal {
  public EntityGendered(World worldIn) {
    super(worldIn);
    this.setGender(Genders.getRandomGender());
  }

  public void setGender(Genders gender) {
    this.setSyncedInteger("Gender", gender.getInt());
  }
  
  /**
   * Returns true if the mob is currently able to mate with the specified mob.
   */
  @Override
  public final boolean canMateWith(EntityAnimal otherAnimal) {
    if (otherAnimal instanceof EntityGendered) {
      if (this.getGender() != ((EntityGendered) otherAnimal).getGender()) {
        return super.canMateWith(otherAnimal);
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public Genders getGender() {
    return Genders.getByInt(this.getSyncedInteger("Gender"));
  }

  @Override
  protected float getSoundPitch() {
    float pitch = this.rand.nextFloat() - this.rand.nextFloat() * 0.2F;
    if (this.isChild()) {
      pitch += 1.5F;
    } else if (this.getGender() == Genders.FEMALE) {
      pitch += 1.2F;
    } else {
      pitch += 1.0F;
    }
    return pitch;
  }

  @Override
  protected boolean canDespawn() {
    return false;
  }
}
