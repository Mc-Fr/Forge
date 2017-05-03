package net.mcfr.entities.mobs.gender;

import net.mcfr.entities.mobs.EntitySyncedAnimal;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

/**
 * Cette classe représente un animal possédant un genre (mâle/femelle).
 *
 * @author Mc-Fr
 */
public abstract class EntityGendered extends EntitySyncedAnimal {
  public EntityGendered(World worldIn) {
    super(worldIn);
    setGender(Genders.getRandomGender());
  }

  @Override
  public void setGender(Genders gender) {
    setSyncedInteger("Gender", gender.ordinal());
  }

  @Override
  public final boolean canMateWith(EntityAnimal otherAnimal) {
    return otherAnimal instanceof EntityGendered && getGender() != ((EntityGendered) otherAnimal).getGender() && super.canMateWith(otherAnimal);
  }

  public Genders getGender() {
    return Genders.getByInt(getSyncedInteger("Gender"));
  }

  @Override
  protected float getSoundPitch() {
    float pitch = this.rand.nextFloat() - this.rand.nextFloat() * 0.2F + 1;
    if (isChild()) {
      pitch += 0.5F;
    }
    else if (getGender() == Genders.FEMALE) {
      pitch += 0.2F;
    }
    return pitch;
  }
}