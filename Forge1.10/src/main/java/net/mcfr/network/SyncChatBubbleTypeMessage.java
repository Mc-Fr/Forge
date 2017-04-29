package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.entities.ChatBubbleType;
import net.mcfr.entities.EntityChatBubble;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Paquet permettant la synchronisation des bulles de tchat entre les clients et le serveur.
 *
 * @author Mc-Fr
 */
public class SyncChatBubbleTypeMessage implements IMessage {
  private int entityId;
  private ChatBubbleType type;

  /**
   * Crée un paquet. Constructeur requis par forge.
   */
  public SyncChatBubbleTypeMessage() {
    this(ChatBubbleType.TALKING, 0);
  }

  /**
   * Crée un paquet.
   * 
   * @param type le type de bulle
   * @param entityId l'ID de l'entité
   */
  public SyncChatBubbleTypeMessage(ChatBubbleType type, int entityId) {
    this.type = type;
    this.entityId = entityId;
  }

  /**
   * @return le type de bulle
   */
  public ChatBubbleType getType() {
    return this.type;
  }

  /**
   * @return l'ID de l'entité
   */
  public int getEntityId() {
    return this.entityId;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.type.ordinal());
    buf.writeInt(this.entityId);
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.type = ChatBubbleType.byOrdinal(buf.readInt());
    this.entityId = buf.readInt();
  }

  /**
   * Gestionnaire côté client.
   *
   * @author Mc-Fr
   */
  public static class ClientHandler implements IMessageHandler<SyncChatBubbleTypeMessage, IMessage> {
    @Override
    public IMessage onMessage(SyncChatBubbleTypeMessage message, MessageContext ctx) {
      Minecraft.getMinecraft().addScheduledTask(() -> {
        Entity e = NetworkUtils.getLocalWorld().getEntityByID(message.getEntityId());

        if (e instanceof EntityChatBubble) {
          EntityChatBubble b = (EntityChatBubble) e;
          b.setType(message.getType());
          b.setSynced(true);
        }
      });

      return null;
    }
  }

  /**
   * Gestionnaire côté serveur.
   *
   * @author Mc-Fr
   */
  public static class ServerHandler implements IMessageHandler<SyncChatBubbleTypeMessage, IMessage> {
    @Override
    public IMessage onMessage(SyncChatBubbleTypeMessage message, MessageContext ctx) {
      EntityPlayerMP player = ctx.getServerHandler().playerEntity;

      player.getServerWorld().addScheduledTask(() -> {
        Entity e = player.worldObj.getEntityByID(message.getEntityId());

        if (e instanceof EntityChatBubble) {
          EntityChatBubble b = (EntityChatBubble) e;
          McfrNetworkWrapper.getInstance().sendTo(new SyncChatBubbleTypeMessage(b.getType(), e.getEntityId()), player);
          b.setSynced(true);
        }
      });

      return null;
    }
  }
}
