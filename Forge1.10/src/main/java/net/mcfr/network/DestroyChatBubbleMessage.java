package net.mcfr.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.mcfr.guis.chat_bubble.ChatBubble;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DestroyChatBubbleMessage implements IMessage {
  private UUID playerUuid;

  // Requis par Forge.
  public DestroyChatBubbleMessage() {
    this(null);
  }

  public DestroyChatBubbleMessage(UUID playerUuid) {
    this.playerUuid = playerUuid;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeLong(getPlayerUuid().getLeastSignificantBits());
    buf.writeLong(getPlayerUuid().getMostSignificantBits());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    long lsb = buf.readLong();
    long msb = buf.readLong();
    this.playerUuid = new UUID(msb, lsb);
  }

  public UUID getPlayerUuid() {
    return this.playerUuid;
  }

  public static class ServerHandler implements IMessageHandler<DestroyChatBubbleMessage, IMessage> {
    @Override
    public IMessage onMessage(final DestroyChatBubbleMessage message, MessageContext ctx) {
      ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(() -> {
        World world = ctx.getServerHandler().playerEntity.worldObj;
        int id = ChatBubble.PLAYER_BUBBLE.get(message.getPlayerUuid());
        Entity e = world.getEntityByID(id);

        if (e != null) {
          e.setDead();
          ChatBubble.PLAYER_BUBBLE.remove(message.getPlayerUuid());
        }
      });

      return null;
    }
  }
}
