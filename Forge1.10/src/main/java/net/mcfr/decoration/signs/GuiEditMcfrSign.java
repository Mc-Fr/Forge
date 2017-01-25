package net.mcfr.decoration.signs;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.text.TextComponentString;

/**
 * Copié de {@link net.minecraft.client.gui.inventory.GuiEditSign}.
 *
 * @author Mc-Fr
 */
public class GuiEditMcfrSign extends GuiScreen {
  private TileEntitySign tileSign;
  /** Compte le nombre de mises à jour de l'écran. */
  private int updateCounter;
  private int editLine;
  private GuiButton doneBtn;

  public GuiEditMcfrSign(TileEntitySign teSign) {
    this.tileSign = teSign;
  }

  @Override
  public void initGui() {
    this.buttonList.clear();
    Keyboard.enableRepeatEvents(true);
    this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, I18n.format("gui.done")));
    this.tileSign.setEditable(false);
  }

  @Override
  public void onGuiClosed() {
    Keyboard.enableRepeatEvents(false);
    NetHandlerPlayClient nethandlerplayclient = this.mc.getConnection();
    if (nethandlerplayclient != null) {
      nethandlerplayclient.sendPacket(new CPacketUpdateSign(this.tileSign.getPos(), this.tileSign.signText));
    }

    this.tileSign.setEditable(true);
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

    String s = this.tileSign.signText[this.editLine].getUnformattedText();

    if (keyCode == 14 && s.length() > 0) {
      s = s.substring(0, s.length() - 1);
    }

    if (ChatAllowedCharacters.isAllowedCharacter(typedChar) && this.fontRendererObj.getStringWidth(s + typedChar) <= 90) {
      s = s + typedChar;
    }

    this.tileSign.signText[this.editLine] = new TextComponentString(s);

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
    GlStateManager.translate(this.width / 2, 0, 50);
    float f = 93.75F;
    GlStateManager.scale(-f, -f, -f);
    GlStateManager.rotate(180, 0, 1, 0);
    Block block = this.tileSign.getBlockType();

    if (block instanceof McfrBlockStandingSign || block instanceof McfrBlockSuspendedSign) {
      // TODO réparer panneaux suspendus.
      float angle = (this.tileSign.getBlockMetadata() * 360) / 16.0F;
      float y = (block instanceof McfrBlockStandingSign) ? -1.0625F : -1.5F;

      GlStateManager.rotate(angle, 0, 1, 0);
      GlStateManager.translate(0, y, 0);
    }
    else if (block instanceof McfrBlockWallSign) {
      int meta = this.tileSign.getBlockMetadata();
      float angle = 0;

      if (meta == 2) {
        angle = 180;
      }
      if (meta == 4) {
        angle = 90;
      }
      if (meta == 5) {
        angle = -90;
      }

      GlStateManager.rotate(angle, 0, 1, 0);
      GlStateManager.translate(0, -1.0625F, 0);
    }

    if (this.updateCounter / 6 % 2 == 0) {
      this.tileSign.lineBeingEdited = this.editLine;
    }

    TileEntityRendererDispatcher.instance.renderTileEntityAt(this.tileSign, -0.5, -0.75, -0.5, 0);
    this.tileSign.lineBeingEdited = -1;
    GlStateManager.popMatrix();

    super.drawScreen(mouseX, mouseY, partialTicks);
  }
}
