package net.mcfr.guis;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.resources.I18n;

public class GuiMcfrIngameMenu extends GuiScreen {
  @Override
  public void initGui() {
    this.buttonList.clear();
    this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + -16, I18n.format("menu.returnToGame")));
    this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 48 + -16, 98, 20, I18n.format("gui.achievements")));
    this.buttonList.add(new GuiButton(6, this.width / 2 + 2, this.height / 4 + 48 + -16, 98, 20, I18n.format("gui.stats")));
    GuiButton guibutton = addButton(new GuiButton(7, this.width / 2 - 100, this.height / 4 + 72 + -16, 200, 20, I18n.format("menu.shareToLan")));
    guibutton.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();
    this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + -16, 200, 20, I18n.format("menu.options")));
    this.buttonList.add(guibutton = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + -16, I18n.format("menu.returnToMenu")));

    if (!this.mc.isIntegratedServerRunning()) {
      guibutton.displayString = I18n.format("menu.disconnect");
    }
  }

  /**
   * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
   */
  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    switch (button.id) {
    case 0:
      this.mc.displayGuiScreen(new GuiMcfrOptions(this, this.mc.gameSettings));
      break;
    case 1:
      button.enabled = false;
      this.mc.theWorld.sendQuittingDisconnectingPacket();
      this.mc.loadWorld(null);
      this.mc.displayGuiScreen(new GuiMcfrMainMenu());
    case 4:
      this.mc.displayGuiScreen((GuiScreen) null);
      this.mc.setIngameFocus();
      break;
    case 5:
      if (this.mc.thePlayer != null) {
        this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
      }
      break;
    case 6:
      if (this.mc.thePlayer != null) {
        this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
      }
      break;
    case 7:
      this.mc.displayGuiScreen(new GuiShareToLan(this));
      break;
    }
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    drawDefaultBackground();
    drawCenteredString(this.fontRendererObj, I18n.format("menu.game"), this.width / 2, 40, 16777215);
    super.drawScreen(mouseX, mouseY, partialTicks);
  }
}
