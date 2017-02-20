package net.mcfr.guis;

import net.mcfr.network.CreateChatBubbleMessage;
import net.mcfr.network.DestroyChatBubbleMessage;
import net.mcfr.network.McfrNetworkWrapper;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMcfrChat extends GuiChat {
  private boolean bubbleDisplayed;

  @Override
  public void initGui() {
    super.initGui();
    this.bubbleDisplayed = false;
  }

  @Override
  public void onGuiClosed() {
    super.onGuiClosed();
    if (this.bubbleDisplayed)
      McfrNetworkWrapper.getInstance().sendToServer(new DestroyChatBubbleMessage());
  }

  @Override
  public void updateScreen() {
    super.updateScreen();

    if (!this.bubbleDisplayed && !this.inputField.getText().isEmpty() && !this.inputField.getText().startsWith("/")) {
      McfrNetworkWrapper.getInstance().sendToServer(new CreateChatBubbleMessage());
      this.bubbleDisplayed = true;
    }
    else if (this.bubbleDisplayed && (this.inputField.getText().isEmpty() || this.inputField.getText().startsWith("/"))) {
      McfrNetworkWrapper.getInstance().sendToServer(new DestroyChatBubbleMessage());
      this.bubbleDisplayed = false;
    }
  }
}
