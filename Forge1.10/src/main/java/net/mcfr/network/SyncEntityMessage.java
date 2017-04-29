package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.entities.mobs.EntitySyncedAnimal;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Paquet permattant la synchronisation d'entités entre les clients et le serveur.
 *
 * @author Mc-Fr
 */
public class SyncEntityMessage implements IMessage {
  public SyncEntityMessage() {}

  private int entityId;
  private NBTTagCompound entitySyncDataCompound;

  /**
   * Crée un paquet.
   * 
   * @param entity l'entité
   */
  public SyncEntityMessage(EntitySyncedAnimal entity) {
    this.entityId = entity.getEntityId();
    this.entitySyncDataCompound = entity.getSyncedProps();
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.entityId = ByteBufUtils.readVarInt(buf, 4);
    this.entitySyncDataCompound = ByteBufUtils.readTag(buf);
  }

  @Override
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeVarInt(buf, this.entityId, 4);
    ByteBufUtils.writeTag(buf, this.entitySyncDataCompound);
  }

  /**
   * Gestionnaire côté client.
   *
   * @author Mc-Fr
   */
  public static class ClientHandler implements IMessageHandler<SyncEntityMessage, IMessage> {
    @Override
    public IMessage onMessage(SyncEntityMessage message, MessageContext ctx) {
      EntitySyncedAnimal entity = (EntitySyncedAnimal) Minecraft.getMinecraft().thePlayer.worldObj.getEntityByID(message.entityId);
      if (entity != null) {
        entity.setSyncedProps(message.entitySyncDataCompound);
        entity.setInitialized(true);
      }
      return null;
    }
  }

  /**
   * Gestionnaire côté serveur.
   *
   * @author Mc-Fr
   */
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
