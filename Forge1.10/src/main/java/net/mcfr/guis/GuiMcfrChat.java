package net.mcfr.guis;

import net.mcfr.network.CreateChatBubbleMessage;
import net.mcfr.network.DestroyChatBubbleMessage;
import net.mcfr.network.McfrNetworkWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMcfrChat extends GuiChat {
  @Override
  public void initGui() {
    super.initGui();
    McfrNetworkWrapper.getInstance().sendToServer(new CreateChatBubbleMessage());
  }

  @Override
  public void onGuiClosed() {
    super.onGuiClosed();
    McfrNetworkWrapper.getInstance().sendToServer(new DestroyChatBubbleMessage(Minecraft.getMinecraft().thePlayer.getUniqueID()));
  }
}
