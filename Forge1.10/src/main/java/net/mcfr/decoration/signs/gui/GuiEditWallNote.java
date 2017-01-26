package net.mcfr.decoration.signs.gui;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.mcfr.Constants;
import net.mcfr.decoration.signs.tileEntities.TileEntityWallNote;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.UpdateWallNoteMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

/**
 * Adapté de {@link net.minecraft.client.gui.inventory.GuiEditSign}.
 *
 * @author Mc-Fr
 */
public class GuiEditWallNote extends GuiScreen {
  private static final ResourceLocation NOTE_TEXTURE = new ResourceLocation(Constants.MOD_ID, "");

  private TileEntityWallNote tileNote;
  /** Compte le nombre de mises à jour de l'écran. */
  private int updateCounter;
  private int editLine;
  private GuiButton doneBtn;

  public GuiEditWallNote(TileEntityWallNote teSign) {
    this.tileNote = teSign;
  }

  @Override
  public void initGui() {
    this.buttonList.clear();
    Keyboard.enableRepeatEvents(true);
    this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 130, I18n.format("gui.done")));
  }

  @Override
  public void onGuiClosed() {
    Keyboard.enableRepeatEvents(false);
    // @f0
    String[] lines = Arrays.asList(this.tileNote.getText())
        .stream()
        .map(text -> text != null ? text.getFormattedText() : null)
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
    drawCenteredString(this.fontRendererObj, "Éditer la note", this.width / 2, 30, 16777215);
    // this.mc.getTextureManager().bindTexture(NOTE_TEXTURE);
    // drawTexturedModalRect(0, 0, 0, 0, 200, 200); // TEMP valeurs

    if (this.updateCounter / 6 % 2 == 0) {
      this.tileNote.setLineBeingEdited(this.editLine);
    }

    for (int j = 0; j < this.tileNote.getText().length; j++) {
      if (this.tileNote.getText()[j] != null) {
        ITextComponent text = this.tileNote.getText()[j];
        List<ITextComponent> list = GuiUtilRenderComponents.splitText(text, 90, this.fontRendererObj, false, true);
        String s = list != null && !list.isEmpty() ? list.get(0).getFormattedText() : "";

        if (j == this.tileNote.getLineBeingEdited())
          s = "> " + s + " <";
        int y = 120 + j * 10 - this.tileNote.getText().length * 5;
        drawCenteredString(this.fontRendererObj, s, this.width / 2, y, 16777215);
      }
    }

    this.tileNote.setLineBeingEdited(-1);

    super.drawScreen(mouseX, mouseY, partialTicks);
  }
}
