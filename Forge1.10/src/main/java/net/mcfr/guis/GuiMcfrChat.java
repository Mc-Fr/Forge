package net.mcfr.guis;

import java.util.HashMap;
import java.util.Map;

import net.mcfr.entities.ChatBubbleType;
import net.mcfr.network.CreateChatBubbleMessage;
import net.mcfr.network.DestroyChatBubbleMessage;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.NetworkUtils;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMcfrChat extends GuiChat {
  private static final Map<String, ChatBubbleType> SPECIAL_CHARS = new HashMap<>();

  static {
    SPECIAL_CHARS.put("/", ChatBubbleType.COMMAND);
    SPECIAL_CHARS.put("*", ChatBubbleType.ACTION);
    SPECIAL_CHARS.put("(", ChatBubbleType.ORP);
    SPECIAL_CHARS.put("%", ChatBubbleType.NONE);
    SPECIAL_CHARS.put("$", ChatBubbleType.NONE);
    SPECIAL_CHARS.put("?", ChatBubbleType.NONE);
    SPECIAL_CHARS.put("@", ChatBubbleType.NONE);
    SPECIAL_CHARS.put("=", ChatBubbleType.NONE);
  }

  public static ChatBubbleType getBubbleType(String str) {
    for (Map.Entry<String, ChatBubbleType> entry : SPECIAL_CHARS.entrySet()) {
      if (str.startsWith(entry.getKey()))
        return entry.getValue();
    }
    return ChatBubbleType.TALKING;
  }

  private boolean bubbleDisplayed;
  private String lastChar;

  public GuiMcfrChat(String defaultText) {
    super(defaultText);
  }

  @Override
  public void initGui() {
    super.initGui();
    this.bubbleDisplayed = false;
    this.lastChar = "";
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

    String currentChar = this.inputField.getText().isEmpty() ? "" : this.inputField.getText().charAt(0) + "";
    if (shouldAddBubble()) {
      McfrNetworkWrapper.getInstance().sendToServer(new CreateChatBubbleMessage(getBubbleType(currentChar)));
      this.lastChar = currentChar;
      this.bubbleDisplayed = true;
    }
    else if (!this.lastChar.equals(currentChar) || shouldRemoveBubble()) {
      McfrNetworkWrapper.getInstance().sendToServer(new DestroyChatBubbleMessage());
      this.bubbleDisplayed = false;
    }
  }

  private boolean shouldAddBubble() {
    // #f:0
    return !this.bubbleDisplayed &&
        !NetworkUtils.getLocalPlayer().isInvisible() &&
        !NetworkUtils.getLocalPlayer().isSpectator() &&
        !this.inputField.getText().isEmpty() &&
        !getBubbleType(this.inputField.getText()).isNone();
    // #f:1
  }

  private boolean shouldRemoveBubble() {
    // #f:0
    return this.bubbleDisplayed && (
        NetworkUtils.getLocalPlayer().isInvisible() ||
        NetworkUtils.getLocalPlayer().isSpectator() ||
        this.inputField.getText().isEmpty() ||
        getBubbleType(this.inputField.getText()).isNone());
    // #f:1
  }
}
