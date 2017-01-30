package net.mcfr.entities.mobs.gender;

import net.mcfr.entities.mobs.EntitySyncedAnimal;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public abstract class EntityGendered extends EntitySyncedAnimal {
  public EntityGendered(World worldIn) {
    super(worldIn);
    setGender(Genders.getRandomGender());
  }

  @Override
  public void setGender(Genders gender) {
    setSyncedInteger("Gender", gender.getInt());
  }

  /**
   * Returns true if the mob is currently able to mate with the specified mob.
   */
  @Override
  public final boolean canMateWith(EntityAnimal otherAnimal) {
    return otherAnimal instanceof EntityGendered && getGender() != ((EntityGendered) otherAnimal).getGender() && super.canMateWith(otherAnimal);
  }

  public Genders getGender() {
    return Genders.getByInt(getSyncedInteger("Gender"));
  }

  @Override
  protected float getSoundPitch() {
    float pitch = this.rand.nextFloat() - this.rand.nextFloat() * 0.2F;
    if (isChild()) {
      pitch += 1.5F;
    } else if (getGender() == Genders.FEMALE) {
      pitch += 1.2F;
    } else {
      pitch += 1.0F;
    }
    return pitch;
  }
}