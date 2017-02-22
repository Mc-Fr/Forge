package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.guis.chat_bubble.ChatBubble;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DestroyChatBubbleMessage implements IMessage {
  @Override
  public void toBytes(ByteBuf buf) {}

  @Override
  public void fromBytes(ByteBuf buf) {}

  public static class ServerHandler implements IMessageHandler<DestroyChatBubbleMessage, IMessage> {
    @Override
    public IMessage onMessage(final DestroyChatBubbleMessage message, MessageContext ctx) {
      EntityPlayerMP player = ctx.getServerHandler().playerEntity;

      player.getServerWorld().addScheduledTask(() -> {
        Integer id = ChatBubble.PLAYER_BUBBLE.get(player.getUniqueID());

        if (id != null) {
          Entity e = player.worldObj.getEntityByID(id);

          if (e != null) {
            e.setDead();
            ChatBubble.PLAYER_BUBBLE.remove(player.getUniqueID());
          }
        }
      });

      return null;
    }
  }
}
