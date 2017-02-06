package net.mcfr.decoration.furniture.tile_entities;

import net.mcfr.commons.TileEntityOriented;
import net.minecraft.block.BlockPlanks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileEntityWoodenChair extends TileEntityOriented {
  private BlockPlanks.EnumType type;

  public TileEntityWoodenChair() {
    this(EnumFacing.NORTH, BlockPlanks.EnumType.OAK);
  }

  public TileEntityWoodenChair(EnumFacing facing, BlockPlanks.EnumType type) {
    super(facing);
    this.type = type;
  }

  public BlockPlanks.EnumType getType() {
    return this.type;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.type = BlockPlanks.EnumType.byMetadata(compound.getInteger("WoodType"));
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setInteger("WoodType", getType().getMetadata());
    return compound;
  }
}
