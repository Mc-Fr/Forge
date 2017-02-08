package net.mcfr.guis;

import net.mcfr.guis.chat_bubble.DummyPlayer;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.NotifyChatBubbleMessage;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMcfrChat extends GuiChat {
  @Override
  public void initGui() {
    super.initGui();
    McfrNetworkWrapper.getInstance().sendToServer(new NotifyChatBubbleMessage(true, DummyPlayer.getFromPlayer()));
  }

  @Override
  public void onGuiClosed() {
    super.onGuiClosed();
    McfrNetworkWrapper.getInstance().sendToServer(new NotifyChatBubbleMessage(false, DummyPlayer.getFromPlayer()));
  }
}
