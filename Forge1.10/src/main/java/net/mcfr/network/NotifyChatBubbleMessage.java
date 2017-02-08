package net.mcfr.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.mcfr.guis.chat_bubble.ChatBubble;
import net.mcfr.guis.chat_bubble.DummyPlayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NotifyChatBubbleMessage implements IMessage {
  private DummyPlayer player;
  private boolean addBubble;

  // Requis par Forge.
  public NotifyChatBubbleMessage() {
    this(false, null);
  }

  public NotifyChatBubbleMessage(boolean addBubble, DummyPlayer player) {
    this.addBubble = addBubble;
    this.player = player;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(isAddBubble());
    buf.writeDouble(getPlayer().getX());
    buf.writeDouble(getPlayer().getY());
    buf.writeDouble(getPlayer().getZ());
    buf.writeLong(getPlayer().getUniqueId().getMostSignificantBits());
    buf.writeLong(getPlayer().getUniqueId().getLeastSignificantBits());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.addBubble = buf.readBoolean();
    double x = buf.readDouble();
    double y = buf.readDouble();
    double z = buf.readDouble();
    UUID uuid = new UUID(buf.readLong(), buf.readLong());

    this.player = new DummyPlayer(x, y, z, uuid);
  }

  public boolean isAddBubble() {
    return this.addBubble;
  }

  public DummyPlayer getPlayer() {
    return this.player;
  }

  public static class ClientHandler implements IMessageHandler<NotifyChatBubbleMessage, IMessage> {
    @Override
    public IMessage onMessage(final NotifyChatBubbleMessage message, MessageContext ctx) {
      Minecraft.getMinecraft().addScheduledTask(() -> handlePacket(message.isAddBubble(), message.getPlayer()));
      return null;
    }

    @SideOnly(Side.CLIENT)
    public void handlePacket(boolean addBubble, DummyPlayer player) {
      if (addBubble)
        ChatBubble.addPlayer(player);
      else
        ChatBubble.removePlayer(player.getUniqueId());
    }
  }

  public static class ServerHandler implements IMessageHandler<NotifyChatBubbleMessage, IMessage> {
    @Override
    public IMessage onMessage(final NotifyChatBubbleMessage message, MessageContext ctx) {
      McfrNetworkWrapper.getInstance().sendToAll(message);
      return null;
    }
  }
}
