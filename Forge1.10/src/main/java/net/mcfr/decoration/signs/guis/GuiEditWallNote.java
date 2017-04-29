package net.mcfr.decoration.signs.guis;

import java.io.IOException;
import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import net.mcfr.decoration.signs.tile_entities.TileEntityLargeSign;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.UpdateWallNoteMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.text.TextComponentString;

/**
 * Interface d'édition de la note murale. Adaptée de
 * {@link net.minecraft.client.gui.inventory.GuiEditSign}.
 *
 * @author Mc-Fr
 */
public class GuiEditWallNote extends GuiScreen {
  private TileEntityLargeSign tileNote;
  /** Compte le nombre de mises à jour de l'écran */
  private int updateCounter;
  /** Indice de la ligne éditée */
  private int editLine;
  private GuiButton doneBtn;

  public GuiEditWallNote(TileEntityLargeSign teSign) {
    this.tileNote = teSign;
  }

  @Override
  public void initGui() {
    this.buttonList.clear();
    Keyboard.enableRepeatEvents(true);
    this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 140, I18n.format("gui.done")));
  }

  @Override
  public void onGuiClosed() {
    Keyboard.enableRepeatEvents(false);
    // @f0
    String[] lines = Arrays.asList(this.tileNote.getText()).stream().map(text -> text != null ? text.getFormattedText() : null)
        .toArray(String[]::new);
    // @f1
    McfrNetworkWrapper.getInstance().sendToServer(new UpdateWallNoteMessage(this.tileNote.getPos(), lines));
  }

  @Override
  public void updateScreen() {
    this.updateCounter++;
  }

  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    if (button.enabled) {
      if (button.id == 0) {
        this.tileNote.markDirty();
        this.mc.displayGuiScreen(null);
      }
    }
  }

  @Override
  protected void keyTyped(char typedChar, int keyCode) throws IOException {
    if (keyCode == 200) {
      if (this.editLine == 0)
        this.editLine = 14;
      else
        this.editLine--;
    }

    if (keyCode == 208 || keyCode == 28 || keyCode == 156) {
      this.editLine = (this.editLine + 1) % 15;
    }

    String s = this.tileNote.getText()[this.editLine].getUnformattedText();

    if (keyCode == 14 && s.length() > 0) {
      s = s.substring(0, s.length() - 1);
    }

    if (ChatAllowedCharacters.isAllowedCharacter(typedChar) && this.fontRendererObj.getStringWidth(s + typedChar) <= 90) {
      s = s + typedChar;
    }

    this.tileNote.getText()[this.editLine] = new TextComponentString(s);

    if (keyCode == 1) {
      actionPerformed(this.doneBtn);
    }
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    drawDefaultBackground();

    GlStateManager.color(1, 1, 1, 1);
    drawCenteredString(this.fontRendererObj, "Éditer la note", this.width / 2, 30, 0xffffff);

    GlStateManager.color(1, 1, 1, 1);
    GlStateManager.pushMatrix();
    GlStateManager.translate(this.width / 2, 0, 50);
    float f = -102f;
    GlStateManager.scale(f, f, f);
    GlStateManager.rotate(180, 0, 1, 0);

    int meta = this.tileNote.getBlockMetadata();
    float angle = 0;

    if (meta == 2)
      angle = 180;
    if (meta == 4)
      angle = 90;
    if (meta == 5)
      angle = -270;

    GlStateManager.rotate(angle, 0, 1, 0);
    GlStateManager.translate(0, -1.0625F, 0);

    if (this.updateCounter / 6 % 2 == 0) {
      this.tileNote.setLineBeingEdited(this.editLine);
    }

    TileEntityRendererDispatcher.instance.renderTileEntityAt(this.tileNote, -0.5, -0.75, -0.5, 0);
    this.tileNote.setLineBeingEdited(-1);
    GlStateManager.popMatrix();

    super.drawScreen(mouseX, mouseY, partialTicks);
  }
}
