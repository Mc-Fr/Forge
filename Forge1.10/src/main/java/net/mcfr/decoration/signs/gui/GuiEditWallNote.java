package net.mcfr.decoration.signs.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.mcfr.decoration.signs.tileEntities.TileEntityWallNote;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.text.TextComponentString;

/**
 * Adapté de {@link net.minecraft.client.gui.inventory.GuiEditSign}.
 *
 * @author Mc-Fr
 */
public class GuiEditWallNote extends GuiScreen {
  private TileEntityWallNote tileSign;
  /** Compte le nombre de mises à jour de l'écran. */
  private int updateCounter;
  private int editLine;
  private GuiButton doneBtn;

  public GuiEditWallNote(TileEntityWallNote teSign) {
    this.tileSign = teSign;
  }

  @Override
  public void initGui() {
    this.buttonList.clear();
    Keyboard.enableRepeatEvents(true);
    this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, I18n.format("gui.done")));
  }

  @Override
  public void onGuiClosed() {
    Keyboard.enableRepeatEvents(false);
    NetHandlerPlayClient nethandlerplayclient = this.mc.getConnection();
    if (nethandlerplayclient != null) {
      // TODO : utiliser un paquet personnalisé.
      nethandlerplayclient.sendPacket(new CPacketUpdateSign(this.tileSign.getPos(), this.tileSign.getText()));
    }
  }

  @Override
  public void updateScreen() {
    this.updateCounter++;
  }

  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    if (button.enabled) {
      if (button.id == 0) {
        this.tileSign.markDirty();
        this.mc.displayGuiScreen(null);
      }
    }
  }

  @Override
  protected void keyTyped(char typedChar, int keyCode) throws IOException {
    if (keyCode == 200) {
      this.editLine = this.editLine - 1 & 3;
    }

    if (keyCode == 208 || keyCode == 28 || keyCode == 156) {
      this.editLine = this.editLine + 1 & 3;
    }

    String s = this.tileSign.getText()[this.editLine].getUnformattedText();

    if (keyCode == 14 && s.length() > 0) {
      s = s.substring(0, s.length() - 1);
    }

    if (ChatAllowedCharacters.isAllowedCharacter(typedChar) && this.fontRendererObj.getStringWidth(s + typedChar) <= 90) {
      s = s + typedChar;
    }

    this.tileSign.getText()[this.editLine] = new TextComponentString(s);

    if (keyCode == 1) {
      actionPerformed(this.doneBtn);
    }
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    drawDefaultBackground();
    drawCenteredString(this.fontRendererObj, I18n.format("sign.edit"), this.width / 2, 40, 16777215);
    GlStateManager.color(1, 1, 1, 1);
    GlStateManager.pushMatrix();

    if (this.updateCounter / 6 % 2 == 0) {
      this.tileSign.setLineBeingEdited(this.editLine);
    }

    // TODO afficher le texte.

    this.tileSign.setLineBeingEdited(-1);
    GlStateManager.popMatrix();

    super.drawScreen(mouseX, mouseY, partialTicks);
  }
}
