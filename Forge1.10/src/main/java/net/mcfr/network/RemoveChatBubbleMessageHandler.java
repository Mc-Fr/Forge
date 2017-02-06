package net.mcfr.network;

import java.util.UUID;

import net.mcfr.guis.chat_bubble.ChatBubble;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RemoveChatBubbleMessageHandler implements IMessageHandler<RemoveChatBubbleMessage, IMessage> {
  @Override
  public IMessage onMessage(final RemoveChatBubbleMessage message, MessageContext ctx) {
    Minecraft.getMinecraft().addScheduledTask(() -> handlePacket(message.getUniqueId()));
    return null;
  }

  @SideOnly(Side.CLIENT)
  public void handlePacket(UUID uuid) {
    ChatBubble.removePlayer(uuid);
  }
}
