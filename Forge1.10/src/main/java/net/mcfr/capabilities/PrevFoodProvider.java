package net.mcfr.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PrevFoodProvider implements ICapabilitySerializable<NBTBase> {
  
  @CapabilityInject(IPrevFood.class)
  public static final Capability<IPrevFood> PREV_FOOD_CAP = null;
  
  private IPrevFood instance = PREV_FOOD_CAP.getDefaultInstance();

  @Override
  public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
    return capability == PREV_FOOD_CAP;
  }

  @Override
  public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
    return capability == PREV_FOOD_CAP ? PREV_FOOD_CAP.<T>cast(this.instance) : null;
  }

  @Override
  public NBTBase serializeNBT() {
    return PREV_FOOD_CAP.getStorage().writeNBT(PREV_FOOD_CAP, this.instance, null);
  }

  @Override
  public void deserializeNBT(NBTBase nbt) {
    PREV_FOOD_CAP.getStorage().readNBT(PREV_FOOD_CAP, this.instance, null, nbt);
  }
}