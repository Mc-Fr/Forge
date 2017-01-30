package net.mcfr.entities.mobs;

import java.util.HashMap;
import java.util.Map;

import net.mcfr.entities.mobs.gender.Genders;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.SyncEntityMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntitySyncedAnimal extends EntityAnimal {
  public static Map<Integer, NBTTagCompound> syncedProps = new HashMap<>();
  protected NBTTagCompound syncedPropsCompound = new NBTTagCompound();
  protected boolean isInitialized;

  public EntitySyncedAnimal(World worldIn) {
    super(worldIn);
    this.isInitialized = false;
  }

  public void setGender(Genders gender) {
    NBTTagCompound nbt = getSyncedProps();
    nbt.setInteger("Gender", gender.getInt());
    setSyncedProps(nbt);
  }

  public void setSyncedBoolean(String key, boolean value) {
    NBTTagCompound nbt = getSyncedProps();
    nbt.setBoolean(key, value);
    setSyncedProps(nbt);
  }

  public boolean getSyncedBoolean(String key) {
    return this.syncedPropsCompound.getBoolean(key);
  }

  public void setSyncedInteger(String key, int value) {
    NBTTagCompound nbt = getSyncedProps();
    nbt.setInteger(key, value);
    setSyncedProps(nbt);
  }

  public int getSyncedInteger(String key) {
    return this.syncedPropsCompound.getInteger(key);
  }

  public NBTTagCompound getSyncedProps() {
    return this.syncedPropsCompound;
  }

  public void setSyncedProps(NBTTagCompound parCompound) {
    this.syncedPropsCompound = parCompound;
    if (!this.worldObj.isRemote) {
      McfrNetworkWrapper.getInstance().sendToAll(new SyncEntityMessage(this));
    }
  }

  public void syncToPlayer(EntityPlayerMP player) {
    if (!this.worldObj.isRemote) {
      McfrNetworkWrapper.getInstance().sendTo(new SyncEntityMessage(this), player);
    }
  }

  public void askForSync() {
    if (this.worldObj.isRemote) {
      McfrNetworkWrapper.getInstance().sendToServer(new SyncEntityMessage(this));
    }
  }

  public void setInitialized(boolean initialized) {
    this.isInitialized = initialized;
  }

  @Override
  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
    compound.setTag("SyncedProps", this.syncedPropsCompound);
  }

  @Override
  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    this.syncedPropsCompound = (NBTTagCompound) compound.getTag("SyncedProps");
  }

  @Override
  public void onLivingUpdate() {
    if (!this.isInitialized && this.worldObj.isRemote) {
      askForSync();
    }
    super.onLivingUpdate();
  }

  @Override
  public boolean attackEntityAsMob(Entity entityTarget) {
    return entityTarget.attackEntityFrom(DamageSource.causeMobDamage(this),
        (float) getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
  }

  @Override
  public boolean attackEntityFrom(DamageSource source, float amount) {
    setLastAttacker(source.getEntity());
    return super.attackEntityFrom(source, amount);
  }
}