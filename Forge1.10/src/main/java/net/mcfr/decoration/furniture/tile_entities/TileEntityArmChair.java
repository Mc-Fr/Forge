package net.mcfr.decoration.furniture.tile_entities;

import net.mcfr.commons.TileEntityOriented;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileEntityArmChair extends TileEntityOriented {
  private String type;

  public TileEntityArmChair() {
    this(EnumFacing.NORTH, "");
  }

  public TileEntityArmChair(EnumFacing facing, String type) {
    super(facing);
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.type = compound.getString("Type");
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setString("Type", getType());
    return compound;
  }
}
