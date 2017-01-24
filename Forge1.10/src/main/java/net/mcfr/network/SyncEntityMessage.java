package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.entities.mobs.EntitySyncedAnimal;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncEntityMessage implements IMessage {
  public SyncEntityMessage() {
  }

  private int entityId;
  private NBTTagCompound entitySyncDataCompound;

  public SyncEntityMessage(EntitySyncedAnimal entity) {
    entityId = entity.getEntityId();
    entitySyncDataCompound = entity.getSyncedProps();
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    entityId = ByteBufUtils.readVarInt(buf, 4);
    entitySyncDataCompound = ByteBufUtils.readTag(buf);
  }

  @Override
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeVarInt(buf, entityId, 4);
    ByteBufUtils.writeTag(buf, entitySyncDataCompound);
  }

  public static class ClientHandler implements IMessageHandler<SyncEntityMessage, IMessage> {
    @Override
    public IMessage onMessage(SyncEntityMessage message, MessageContext ctx) {
      // EntitySyncedAnimal.syncedProps.put(message.entityId, message.entitySyncDataCompound);
      EntitySyncedAnimal entity = (EntitySyncedAnimal) Minecraft.getMinecraft().thePlayer.worldObj.getEntityByID(message.entityId);
      if (entity != null) {
        entity.setSyncedProps(message.entitySyncDataCompound);
        entity.setInitialized(true);
      }
      return null;
    }
  }
  
  public static class ServerHandler implements IMessageHandler<SyncEntityMessage, IMessage> {
    @Override
    public IMessage onMessage(SyncEntityMessage message, MessageContext ctx) {
      EntitySyncedAnimal entity = (EntitySyncedAnimal) ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityId);
      if (entity != null) {
        entity.syncToPlayer(ctx.getServerHandler().playerEntity);
      }
      return null;
    }
  }
}
