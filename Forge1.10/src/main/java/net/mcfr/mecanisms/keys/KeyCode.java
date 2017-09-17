package net.mcfr.mecanisms.keys;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class KeyCode implements IKeyCode {
  private int code = 0;

  @Override
  public int get() {
    return this.code;
  }

  @Override
  public void set(int code) {
    this.code = code;
  }
  
  public static class Storage implements IStorage<IKeyCode> {

    @Override
    public NBTBase writeNBT(Capability<IKeyCode> capability, IKeyCode instance, EnumFacing side) {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setInteger("keyCode", instance.get());
      return tag;
    }

    @Override
    public void readNBT(Capability<IKeyCode> capability, IKeyCode instance, EnumFacing side, NBTBase nbt) {
      NBTTagCompound tag = (NBTTagCompound) nbt;
      instance.set(tag.getInteger("keyCode"));
    }
  }
  
  public static class Provider implements ICapabilitySerializable<NBTBase> {
    
    @CapabilityInject(IKeyCode.class)
    public static final Capability<IKeyCode> KEY_CODE_CAP = null;
    
    private IKeyCode instance = KEY_CODE_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
      return capability == KEY_CODE_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
      return capability == KEY_CODE_CAP ? KEY_CODE_CAP.<T>cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
      return KEY_CODE_CAP.getStorage().writeNBT(KEY_CODE_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
      KEY_CODE_CAP.getStorage().readNBT(KEY_CODE_CAP, this.instance, null, nbt);
    }
  }
}
