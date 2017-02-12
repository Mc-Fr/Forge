package net.mcfr.guis;

import net.mcfr.guis.chat_bubble.ChatBubble;
import net.mcfr.guis.chat_bubble.DummyEntity;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.NotifyChatBubbleMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMcfrChat extends GuiChat {
  public static final double OFFSET = 2.3;

  @Override
  public void initGui() {
    super.initGui();
    EntityPlayer p = Minecraft.getMinecraft().thePlayer;
    McfrNetworkWrapper.getInstance().sendToServer(new NotifyChatBubbleMessage(true, new DummyEntity(p.posX, p.posY + OFFSET, p.posZ, -1)));
  }

  @Override
  public void onGuiClosed() {
    super.onGuiClosed();
    McfrNetworkWrapper.getInstance().sendToServer(new NotifyChatBubbleMessage(false, new DummyEntity(0, 0, 0, ChatBubble.currentEntityId)));
  }
}
