package net.mcfr.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityChatBubble extends Entity {
  public EntityChatBubble(World world) {
    this(world, 0, 0, 0);
  }

  public EntityChatBubble(World world, double x, double y, double z) {
    super(world);
    this.posX = x;
    this.posY = y;
    this.posZ = z;
  }

  @Override
  protected void entityInit() {}

  @Override
  protected void readEntityFromNBT(NBTTagCompound compound) {}

  @Override
  protected void writeEntityToNBT(NBTTagCompound compound) {}
}
