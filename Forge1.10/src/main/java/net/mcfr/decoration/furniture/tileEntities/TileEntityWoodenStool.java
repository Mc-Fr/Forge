package net.mcfr.decoration.furniture.tileEntities;

import net.minecraft.block.BlockPlanks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileEntityWoodenStool extends TileEntityWoodenChair {
  private boolean isTall;

  public TileEntityWoodenStool() {
    this(EnumFacing.NORTH, BlockPlanks.EnumType.OAK, false);
  }

  public TileEntityWoodenStool(EnumFacing facing, BlockPlanks.EnumType type, boolean isTall) {
    super(facing, type);
    this.isTall = isTall;
  }

  public boolean isTall() {
    return this.isTall;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.isTall = compound.getBoolean("IsTall");
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setBoolean("IsTall", isTall());
    return compound;
  }
}
