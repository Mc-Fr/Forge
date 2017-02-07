package net.mcfr.guis;

import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMcfrChat extends GuiChat {
  @Override
  public void initGui() {
    super.initGui();
    // FIXME marche pas en serveur
    // McfrNetworkWrapper.getInstance().sendToAll(new
    // AddChatBubbleMessage(DummyPlayer.getFromPlayer()));
  }

  @Override
  public void onGuiClosed() {
    super.onGuiClosed();
    // FIXME marche pas en serveur
    // McfrNetworkWrapper.getInstance().sendToAll(new
    // RemoveChatBubbleMessage(Minecraft.getMinecraft().thePlayer.getUniqueID()));
  }
}
