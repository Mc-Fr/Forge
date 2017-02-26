package net.mcfr.guis;

import java.io.IOException;

import net.mcfr.network.NetworkUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiLockIconButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class GuiMcfrOptions extends GuiScreen {
  private static final GameSettings.Options[] SCREEN_OPTIONS = {GameSettings.Options.FOV};

  private final GuiScreen lastScreen;
  private final GameSettings settings;
  private GuiButton difficultyButton;
  private GuiLockIconButton lockButton;
  private String title;

  public GuiMcfrOptions(GuiScreen lastScreen, GameSettings settings) {
    this.lastScreen = lastScreen;
    this.settings = settings;
    this.difficultyButton = null;
    this.lockButton = null;
    this.title = null;
  }

  @Override
  public void initGui() {
    this.title = I18n.format("options.title");
    int i = 0;

    for (GameSettings.Options options : SCREEN_OPTIONS) {
      if (options.getEnumFloat()) {
        this.buttonList.add(new GuiOptionSlider(options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), options));
      }
      else {
        this.buttonList.add(new GuiOptionButton(options.returnEnumOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), options, this.settings.getKeyBinding(options)));
      }
      i++;
    }

    if (this.mc.theWorld != null) {
      EnumDifficulty difficulty = this.mc.theWorld.getDifficulty();

      this.difficultyButton = new GuiButton(108, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150, 20, this.getDifficultyText(difficulty));
      this.buttonList.add(this.difficultyButton);

      if (this.mc.isSingleplayer() && !this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
        this.difficultyButton.setWidth(this.difficultyButton.getButtonWidth() - 20);
        this.lockButton = new GuiLockIconButton(109, this.difficultyButton.xPosition + this.difficultyButton.getButtonWidth(), this.difficultyButton.yPosition);
        this.buttonList.add(this.lockButton);
        this.lockButton.setLocked(this.mc.theWorld.getWorldInfo().isDifficultyLocked());
        this.lockButton.enabled = !this.lockButton.isLocked();
        this.difficultyButton.enabled = !this.lockButton.isLocked();
      }
      else {
        this.difficultyButton.enabled = false;
        this.difficultyButton.visible = false;
      }
    }

    this.buttonList.add(new GuiButton(110, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format("options.skinCustomisation")));
    this.buttonList.add(new GuiButton(106, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, I18n.format("options.sounds")));
    this.buttonList.add(new GuiButton(101, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, I18n.format("options.video")));
    this.buttonList.add(new GuiButton(100, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, I18n.format("options.controls")));
    this.buttonList.add(new GuiButton(103, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, I18n.format("options.chat.title")));
    this.buttonList.add(new GuiButton(104, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, I18n.format("options.snooper.view")));
    if (NetworkUtils.isPlayerAdmin())
      this.buttonList.add(new GuiButton(105, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, I18n.format("options.resourcepack")));
    this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done")));
  }

  public String getDifficultyText(EnumDifficulty difficulty) {
    ITextComponent component = new TextComponentString("");

    component.appendSibling(new TextComponentTranslation("options.difficulty"));
    component.appendText(": ");
    component.appendSibling(new TextComponentTranslation(difficulty.getDifficultyResourceKey()));

    return component.getFormattedText();
  }

  @Override
  public void confirmClicked(boolean result, int id) {
    this.mc.displayGuiScreen(this);

    if (id == 109 && result && this.mc.theWorld != null) {
      this.mc.theWorld.getWorldInfo().setDifficultyLocked(true);
      this.lockButton.setLocked(true);
      this.lockButton.enabled = false;
      this.difficultyButton.enabled = false;
    }
  }

  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    if (button.enabled) {
      switch (button.id) {
        case 100:
          this.mc.gameSettings.saveOptions();
          this.mc.displayGuiScreen(new GuiControls(this, this.settings));
          break;
        case 101:
          this.mc.gameSettings.saveOptions();
          this.mc.displayGuiScreen(new GuiVideoSettings(this, this.settings));
          break;
        case 103:
          this.mc.gameSettings.saveOptions();
          this.mc.displayGuiScreen(new ScreenChatOptions(this, this.settings));
          break;
        case 104:
          this.mc.gameSettings.saveOptions();
          this.mc.displayGuiScreen(new GuiSnooper(this, this.settings));
          break;
        case 105:
          this.mc.gameSettings.saveOptions();
          this.mc.displayGuiScreen(new GuiScreenResourcePacks(this));
          break;
        case 106:
          this.mc.gameSettings.saveOptions();
          this.mc.displayGuiScreen(new GuiScreenOptionsSounds(this, this.settings));
          break;
        case 108:
          this.mc.theWorld.getWorldInfo().setDifficulty(EnumDifficulty.getDifficultyEnum(this.mc.theWorld.getDifficulty().getDifficultyId() + 1));
          this.difficultyButton.displayString = getDifficultyText(this.mc.theWorld.getDifficulty());
          break;
        case 109:
          this.mc.displayGuiScreen(new GuiYesNo(this, new TextComponentTranslation("difficulty.lock.title").getFormattedText(), new TextComponentTranslation("difficulty.lock.question", new TextComponentTranslation(this.mc.theWorld.getWorldInfo().getDifficulty().getDifficultyResourceKey())).getFormattedText(), 109));
          break;
        case 110:
          this.mc.gameSettings.saveOptions();
          this.mc.displayGuiScreen(new GuiCustomizeSkin(this));
          break;
        case 200:
          this.mc.gameSettings.saveOptions();
          this.mc.displayGuiScreen(this.lastScreen);
          break;
        default:
          if (button.id < 100 && button instanceof GuiOptionButton) {
            this.settings.setOptionValue(((GuiOptionButton) button).returnEnumOptions(), 1);
            button.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
          }
      }
    }
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    drawDefaultBackground();
    drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 16777215);
    super.drawScreen(mouseX, mouseY, partialTicks);
  }
}
