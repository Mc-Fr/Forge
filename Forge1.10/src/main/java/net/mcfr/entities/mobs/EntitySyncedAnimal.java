package net.mcfr.entities.mobs;

import java.util.HashMap;
import java.util.Map;

import net.mcfr.entities.mobs.gender.Genders;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.SyncEntityMessage;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntitySyncedAnimal extends EntityAnimal {
  public static Map<Integer, NBTTagCompound> syncedProps = new HashMap<>();
  protected NBTTagCompound syncedPropsCompound = new NBTTagCompound();
  protected boolean isInitialized;

  public EntitySyncedAnimal(World worldIn) {
    super(worldIn);
    isInitialized = false;
  }

  public void setGender(Genders gender) {
    NBTTagCompound nbt = this.getSyncedProps();
    nbt.setInteger("Gender", gender.getInt());
    this.setSyncedProps(nbt);
  }

  public void setSyncedInteger(String key, int value) {
    NBTTagCompound nbt = this.getSyncedProps();
    nbt.setInteger(key, value);
    this.setSyncedProps(nbt);
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

  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
    compound.setTag("SyncedProps", syncedPropsCompound);
  }

  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    this.syncedPropsCompound = (NBTTagCompound) compound.getTag("SyncedProps");
  }

  @Override
  public void onLivingUpdate() {
    if (!this.isInitialized && this.worldObj.isRemote) {
      this.askForSync();
    }
    super.onLivingUpdate();
  }
  
  public abstract EntityAgeable createChild(EntityAgeable ageable);
}
