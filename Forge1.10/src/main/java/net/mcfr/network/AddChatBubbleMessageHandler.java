package net.mcfr.network;

import net.mcfr.guis.chat_bubble.ChatBubble;
import net.mcfr.guis.chat_bubble.DummyPlayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AddChatBubbleMessageHandler implements IMessageHandler<AddChatBubbleMessage, IMessage> {
  @Override
  public IMessage onMessage(final AddChatBubbleMessage message, MessageContext ctx) {
    Minecraft.getMinecraft().addScheduledTask(() -> handlePacket(message.getPlayer()));
    return null;
  }

  @SideOnly(Side.CLIENT)
  public void handlePacket(DummyPlayer player) {
    ChatBubble.addPlayer(player);
  }
}
