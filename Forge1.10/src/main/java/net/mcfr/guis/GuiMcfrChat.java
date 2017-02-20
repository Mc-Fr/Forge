package net.mcfr.guis;

import java.util.ArrayList;
import java.util.List;

import net.mcfr.network.CreateChatBubbleMessage;
import net.mcfr.network.DestroyChatBubbleMessage;
import net.mcfr.network.McfrNetworkWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMcfrChat extends GuiChat {
  private static final List<String> SPECIAL_CHARS = new ArrayList<>();

  static {
    SPECIAL_CHARS.add("/");
    SPECIAL_CHARS.add("?");
  }

  public static boolean startsWithSpecialChar(String str) {
    for (String s : SPECIAL_CHARS) {
      if (str.startsWith(s))
        return true;
    }
    return false;
  }

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

    if (shouldAddBubble()) {
      McfrNetworkWrapper.getInstance().sendToServer(new CreateChatBubbleMessage());
      this.bubbleDisplayed = true;
    }
    else if (shouldRemoveBubble()) {
      McfrNetworkWrapper.getInstance().sendToServer(new DestroyChatBubbleMessage());
      this.bubbleDisplayed = false;
    }
  }

  private boolean shouldAddBubble() {
    // #f:0
    return !this.bubbleDisplayed &&
        !this.inputField.getText().isEmpty() &&
        !startsWithSpecialChar(this.inputField.getText());
    // #f:1
  }

  private boolean shouldRemoveBubble() {
    // #f:0
    return this.bubbleDisplayed && (
        Minecraft.getMinecraft().thePlayer.isSpectator() ||
        this.inputField.getText().isEmpty() ||
        startsWithSpecialChar(this.inputField.getText()));
    // #f:1
  }
}
