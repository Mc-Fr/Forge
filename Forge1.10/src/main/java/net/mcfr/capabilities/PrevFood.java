package net.mcfr.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PrevFood implements IPrevFood {
  int prevFood = 0;
  float prevSaturation = 0.0f;

  @Override
  public void set(int food, float saturation) {
    this.prevFood = food;
    this.prevSaturation = saturation;
  }

  @Override
  public int getFood() {
    return this.prevFood;
  }
  
  @Override
  public float getSaturation() {
    return this.prevSaturation;
  }

  public static class Storage implements IStorage<IPrevFood> {

    @Override
    public NBTBase writeNBT(Capability<IPrevFood> capability, IPrevFood instance, EnumFacing side) {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setInteger("prevFood", instance.getFood());
      tag.setFloat("prevSaturation", instance.getSaturation());
      return tag;
    }

    @Override
    public void readNBT(Capability<IPrevFood> capability, IPrevFood instance, EnumFacing side, NBTBase nbt) {
      NBTTagCompound tag = (NBTTagCompound) nbt;
      instance.set(tag.getInteger("prevFood"), tag.getFloat("prevSaturation"));
    }
  }
}
