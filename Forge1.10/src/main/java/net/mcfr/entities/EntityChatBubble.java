package net.mcfr.entities;

import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.SyncChatBubbleTypeMessage;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityChatBubble extends Entity {
  private ChatBubbleType type;
  private int lifespan;
  private boolean synced;

  public EntityChatBubble(World world) {
    this(ChatBubbleType.NONE, world, 0, 0, 0);
  }

  public EntityChatBubble(ChatBubbleType type, World world, double x, double y, double z) {
    super(world);
    setSize(0.3f, 0.3f);
    this.type = type;
    this.posX = x;
    this.posY = y;
    this.posZ = z;
    this.lifespan = 3 * 60 * 20;
    this.synced = false;
  }

  public ChatBubbleType getType() {
    return this.type;
  }

  public void setType(ChatBubbleType type) {
    this.type = type;
  }

  public void setSynced(boolean synced) {
    this.synced = synced;
  }

  @Override
  protected void entityInit() {}

  @Override
  protected void readEntityFromNBT(NBTTagCompound compound) {}

  @Override
  protected void writeEntityToNBT(NBTTagCompound compound) {}

  @Override
  public void onEntityUpdate() {
    super.onEntityUpdate();

    if (this.worldObj.isRemote && !this.synced) {
      McfrNetworkWrapper.getInstance().sendToServer(new SyncChatBubbleTypeMessage(ChatBubbleType.NONE, getEntityId()));
    }

    if (this.lifespan <= 0)
      setDead();
    else
      this.lifespan--;

    // TEMP
    // if (!this.worldObj.isRemote && isRiding()) {
    // Entity entity = getRidingEntity();
    //
    // this.posX = entity.posX;
    // this.posY = entity.posY + 2.3;
    // this.posZ = entity.posZ;
    // McfrNetworkWrapper.getInstance().sendToAll(new UpdateChatBubblePositionMessage(this.posX,
    // this.posY, this.posZ, getEntityId()));
    // }
  }
}
