package net.mcfr.guis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

import com.google.common.collect.Lists;

import net.mcfr.network.NetworkUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.Language;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.FMLClientHandler;

/**
 * Interface du menu principal.
 *
 * @author Mc-Fr
 */
public class GuiMcfrMainMenu extends GuiScreen {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final Random RANDOM = new Random();
  private static final ResourceLocation SPLASH_TEXTS = new ResourceLocation("texts/splashes.txt");
  private static final ResourceLocation MINECRAFT_TITLE_TEXTURES = new ResourceLocation("textures/gui/title/minecraft.png");
  // #f:0
  private static final ResourceLocation[] TITLE_PANORAMA_PATHS = {
    new ResourceLocation("textures/gui/title/background/panorama_0.png"),
    new ResourceLocation("textures/gui/title/background/panorama_1.png"),
    new ResourceLocation("textures/gui/title/background/panorama_2.png"),
    new ResourceLocation("textures/gui/title/background/panorama_3.png"),
    new ResourceLocation("textures/gui/title/background/panorama_4.png"),
    new ResourceLocation("textures/gui/title/background/panorama_5.png")
  };
  // #f:1
  public static final String MORE_INFO_TEXT = "Please click " + TextFormatting.UNDERLINE + "here" + TextFormatting.RESET + " for more information.";

  /** The Object object utilized as a thread lock when performing non thread-safe operations */
  private final Object threadLock = new Object();
  /** The splash message. */
  private String splashText;
  /** Counts the number of screen updates. */
  private final float updateCounter;
  /** Timer used to rotate the panorama, increases every tick. */
  private int panoramaTimer;
  /** Texture allocated for the current viewport of the main menu's panorama background. */
  private DynamicTexture viewportTexture;
  private ResourceLocation backgroundTexture;

  /** OpenGL graphics card warning. */
  private String openGLWarning1;
  /** OpenGL graphics card warning. */
  private String openGLWarning2;
  /** Link to the Mojang Support about minimum requirements */
  private String openGLWarningLink;
  /** Width of openGLWarning2 */
  private int openGLWarning2Width;
  /** Width of openGLWarning1 */
  private int openGLWarning1Width;
  /** Left x coordinate of the OpenGL warning */
  private int openGLWarningX1;
  /** Top y coordinate of the OpenGL warning */
  private int openGLWarningY1;
  /** Right x coordinate of the OpenGL warning */
  private int openGLWarningX2;
  /** Bottom y coordinate of the OpenGL warning */
  private int openGLWarningY2;

  public GuiMcfrMainMenu() {
    this.openGLWarning2 = MORE_INFO_TEXT;
    this.splashText = "missingno";
    IResource splashes = null;

    try {
      List<String> list = Lists.newArrayList();
      splashes = Minecraft.getMinecraft().getResourceManager().getResource(SPLASH_TEXTS);
      BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(splashes.getInputStream(), Charsets.UTF_8));
      String s;

      while ((s = bufferedreader.readLine()) != null) {
        s = s.trim();

        if (!s.isEmpty()) {
          list.add(s);
        }
      }

      if (!list.isEmpty()) {
        while (true) {
          this.splashText = list.get(RANDOM.nextInt(list.size()));
          if (this.splashText.hashCode() != 125780783) {
            break;
          }
        }
      }
    }
    catch (IOException e) {}
    finally {
      IOUtils.closeQuietly(splashes);
    }
    FMLClientHandler.instance().setupServerList();

    this.updateCounter = RANDOM.nextFloat();
    this.openGLWarning1 = "";

    if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported()) {
      this.openGLWarning1 = I18n.format("title.oldgl1");
      this.openGLWarning2 = I18n.format("title.oldgl2");
      this.openGLWarningLink = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
    }
  }

  @Override
  public void updateScreen() {
    this.panoramaTimer++;
  }

  @Override
  public boolean doesGuiPauseGame() {
    return false;
  }

  @Override
  protected void keyTyped(char typedChar, int keyCode) throws IOException {}

  @Override
  public void initGui() {
    // Met le jeu en français s'il ne l'est pas déjà.
    if (!this.mc.gameSettings.language.equals("fr_FR")) {
      for (Language language : this.mc.getLanguageManager().getLanguages()) {
        if (language.getLanguageCode().equals("fr_FR")) {
          this.mc.gameSettings.language = "fr_FR";
          this.mc.getLanguageManager().setCurrentLanguage(language);
          this.mc.gameSettings.saveOptions();
          break;
        }
      }
      this.mc.refreshResources();
    }

    this.viewportTexture = new DynamicTexture(256, 256);
    this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());

    if (calendar.get(2) + 1 == 12 && calendar.get(5) == 24) {
      this.splashText = "Joyeux Noël !";
    }
    else if (calendar.get(2) + 1 == 1 && calendar.get(5) == 1) {
      this.splashText = "Bonne année !";
    }
    else if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31) {
      this.splashText = "HOOoooOOOoooo ! Spouky !";
    }

    addButtons(this.height / 4 + 40, 24);

    synchronized (this.threadLock) {
      this.openGLWarning1Width = this.fontRendererObj.getStringWidth(this.openGLWarning1);
      this.openGLWarning2Width = this.fontRendererObj.getStringWidth(this.openGLWarning2);
      int width = Math.max(this.openGLWarning1Width, this.openGLWarning2Width);
      this.openGLWarningX1 = (this.width - width) / 2;
      this.openGLWarningY1 = this.buttonList.get(0).yPosition - 24;
      this.openGLWarningX2 = this.openGLWarningX1 + width;
      this.openGLWarningY2 = this.openGLWarningY1 + 24;
    }

    this.mc.setConnectedToRealms(false);
  }

  /**
   * Ajoute les boutons.
   * 
   * @param y hauteur initiale
   * @param step taille du pas
   */
  private void addButtons(int y, int step) {
    int yOffset = y;

    this.buttonList.add(new GuiButton(1, this.width / 2 - 100, yOffset, "Serveur Roleplay"));
    this.buttonList.add(new GuiButton(2, this.width / 2 - 100, yOffset += step, "Serveur Freebuild"));
    yOffset += step;
    this.buttonList.add(new GuiButton(3, this.width / 2 - 100, yOffset, 66, 20, "Site"));
    this.buttonList.add(new GuiButton(4, this.width / 2 - 32, yOffset, 65, 20, "Discord"));
    this.buttonList.add(new GuiButton(5, this.width / 2 + 34, yOffset, 66, 20, "Wiki"));
    yOffset += step + 10;
    this.buttonList.add(new GuiButton(0, this.width / 2 - 100, yOffset, 98, 20, I18n.format("menu.options")));
    this.buttonList.add(new GuiButton(7, this.width / 2 + 2, yOffset, 98, 20, I18n.format("menu.quit")));
    if (NetworkUtils.isPlayerAdmin()) {
      this.buttonList.add(new GuiButton(8, 0, 0, 50, 20, "Solo"));
      this.buttonList.add(new GuiButton(9, 55, 0, 50, 20, "Serveurs"));
    }
  }

  @Override
  protected void actionPerformed(GuiButton button) {
    switch (button.id) {
      case 0: // Options
        this.mc.displayGuiScreen(new GuiMcfrOptions(this, this.mc.gameSettings));
        break;
      case 1: // Serveur Roleplay
        connectToServer("minecraft-fr.net:23457");
        break;
      case 2: // Serveur Freebuild
        connectToServer("minecraft-fr.net:23461");
        break;
      case 3: // Lien site
        openLink("http://www.minecraft-fr.net");
        break;
      case 4: // Lien Discord
        openLink("https://discord.gg/vrDZa7Z");
        break;
      case 5: // Lien Wiki
        openLink("http://www.minecraft-fr.net/wiki/index.php");
        break;
      case 7: // Quitter
        this.mc.shutdown();
        break;
      case 8: // Partie solo test
        this.mc.displayGuiScreen(new GuiWorldSelection(this));
        break;
      case 9: // Liste serveurs test
        this.mc.displayGuiScreen(new GuiMultiplayer(this));
        break;
    }
  }

  /**
   * Connecte le joueur à un serveur.
   * 
   * @param ip l'IP du serveur
   */
  private void connectToServer(String ip) {
    FMLClientHandler.instance().connectToServer(this, new ServerData("", ip, false));
  }

  /**
   * Ouvre un lien avec le navigateur par défaut.
   * 
   * @param url l'URL à atteindre
   */
  private void openLink(String url) {
    try {
      Object o = Class.forName("java.awt.Desktop").getMethod("getDesktop").invoke(null);
      o.getClass().getMethod("browse", URI.class).invoke(o, new URL(url).toURI());
    }
    catch (Throwable e) {
      LOGGER.error("Couldn't open link", e);
    }
  }

  @Override
  public void confirmClicked(boolean result, int id) {
    if (id == 13) {
      openLink(this.openGLWarningLink);
      this.mc.displayGuiScreen(this);
    }
  }

  /**
   * Dessine le panorama.
   * 
   * @param mouseX
   * @param mouseY
   * @param partialTicks
   */
  private void drawPanorama(int mouseX, int mouseY, float partialTicks) {
    Tessellator tessellator = Tessellator.getInstance();
    VertexBuffer vertexbuffer = tessellator.getBuffer();
    GlStateManager.matrixMode(5889);
    GlStateManager.pushMatrix();
    GlStateManager.loadIdentity();
    Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
    GlStateManager.matrixMode(5888);
    GlStateManager.pushMatrix();
    GlStateManager.loadIdentity();
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
    GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
    GlStateManager.enableBlend();
    GlStateManager.disableAlpha();
    GlStateManager.disableCull();
    GlStateManager.depthMask(false);
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
        GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

    for (int j = 0; j < 64; ++j) {
      GlStateManager.pushMatrix();
      float f = (j % 8 / 8.0F - 0.5F) / 64.0F;
      float f1 = (j / 8 / 8.0F - 0.5F) / 64.0F;
      GlStateManager.translate(f, f1, 0.0F);
      GlStateManager.rotate(MathHelper.sin((this.panoramaTimer + partialTicks) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(-(this.panoramaTimer + partialTicks) * 0.1F, 0.0F, 1.0F, 0.0F);

      for (int k = 0; k < 6; ++k) {
        GlStateManager.pushMatrix();

        if (k == 1) {
          GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        }

        if (k == 2) {
          GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        }

        if (k == 3) {
          GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        }

        if (k == 4) {
          GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
        }

        if (k == 5) {
          GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
        }

        this.mc.getTextureManager().bindTexture(TITLE_PANORAMA_PATHS[k]);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        int l = 255 / (j + 1);
        vertexbuffer.pos(-1.0D, -1.0D, 1.0D).tex(0.0D, 0.0D).color(255, 255, 255, l).endVertex();
        vertexbuffer.pos(1.0D, -1.0D, 1.0D).tex(1.0D, 0.0D).color(255, 255, 255, l).endVertex();
        vertexbuffer.pos(1.0D, 1.0D, 1.0D).tex(1.0D, 1.0D).color(255, 255, 255, l).endVertex();
        vertexbuffer.pos(-1.0D, 1.0D, 1.0D).tex(0.0D, 1.0D).color(255, 255, 255, l).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
      }

      GlStateManager.popMatrix();
      GlStateManager.colorMask(true, true, true, false);
    }

    vertexbuffer.setTranslation(0.0D, 0.0D, 0.0D);
    GlStateManager.colorMask(true, true, true, true);
    GlStateManager.matrixMode(5889);
    GlStateManager.popMatrix();
    GlStateManager.matrixMode(5888);
    GlStateManager.popMatrix();
    GlStateManager.depthMask(true);
    GlStateManager.enableCull();
    GlStateManager.enableDepth();
  }

  /**
   * Pivote et floute l'arrière plan.
   */
  private void rotateAndBlurSkybox(float partialTicks) {
    this.mc.getTextureManager().bindTexture(this.backgroundTexture);
    GlStateManager.glTexParameteri(3553, 10241, 9729);
    GlStateManager.glTexParameteri(3553, 10240, 9729);
    GlStateManager.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
    GlStateManager.enableBlend();
    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
        GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    GlStateManager.colorMask(true, true, true, false);
    Tessellator tessellator = Tessellator.getInstance();
    VertexBuffer vertexbuffer = tessellator.getBuffer();
    vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
    GlStateManager.disableAlpha();

    for (int j = 0; j < 3; ++j) {
      float f = 1.0F / (j + 1);
      int k = this.width;
      int l = this.height;
      float f1 = (j - 1) / 256.0F;
      vertexbuffer.pos(k, l, this.zLevel).tex(0.0F + f1, 1.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
      vertexbuffer.pos(k, 0.0D, this.zLevel).tex(1.0F + f1, 1.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
      vertexbuffer.pos(0.0D, 0.0D, this.zLevel).tex(1.0F + f1, 0.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
      vertexbuffer.pos(0.0D, l, this.zLevel).tex(0.0F + f1, 0.0D).color(1.0F, 1.0F, 1.0F, f).endVertex();
    }

    tessellator.draw();
    GlStateManager.enableAlpha();
    GlStateManager.colorMask(true, true, true, true);
  }

  /**
   * Affiche l'arrière plan.
   * 
   * @param mouseX
   * @param mouseY
   * @param partialTicks
   */
  private void renderSkybox(int mouseX, int mouseY, float partialTicks) {
    this.mc.getFramebuffer().unbindFramebuffer();
    GlStateManager.viewport(0, 0, 256, 256);
    drawPanorama(mouseX, mouseY, partialTicks);
    rotateAndBlurSkybox(partialTicks);
    rotateAndBlurSkybox(partialTicks);
    rotateAndBlurSkybox(partialTicks);
    rotateAndBlurSkybox(partialTicks);
    rotateAndBlurSkybox(partialTicks);
    rotateAndBlurSkybox(partialTicks);
    rotateAndBlurSkybox(partialTicks);
    this.mc.getFramebuffer().bindFramebuffer(true);
    GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
    float f = 120.0F / (this.width > this.height ? this.width : this.height);
    float f1 = this.height * f / 256.0F;
    float f2 = this.width * f / 256.0F;
    int i = this.width;
    int j = this.height;
    Tessellator tessellator = Tessellator.getInstance();
    VertexBuffer vertexbuffer = tessellator.getBuffer();
    vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
    vertexbuffer.pos(0.0D, j, this.zLevel).tex(0.5F - f1, 0.5F + f2).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
    vertexbuffer.pos(i, j, this.zLevel).tex(0.5F - f1, 0.5F - f2).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
    vertexbuffer.pos(i, 0.0D, this.zLevel).tex(0.5F + f1, 0.5F - f2).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
    vertexbuffer.pos(0.0D, 0.0D, this.zLevel).tex(0.5F + f1, 0.5F + f2).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
    tessellator.draw();
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    GlStateManager.disableAlpha();
    renderSkybox(mouseX, mouseY, partialTicks);
    GlStateManager.enableAlpha();
    int j = this.width / 2 - 137;
    drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
    drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
    this.mc.getTextureManager().bindTexture(MINECRAFT_TITLE_TEXTURES);
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

    if (this.updateCounter < 1.0E-4D) {
      drawTexturedModalRect(j + 0, 30, 0, 0, 99, 44);
      drawTexturedModalRect(j + 99, 30, 129, 0, 27, 44);
      drawTexturedModalRect(j + 99 + 26, 30, 126, 0, 3, 44);
      drawTexturedModalRect(j + 99 + 26 + 3, 30, 99, 0, 26, 44);
      drawTexturedModalRect(j + 155, 30, 0, 45, 155, 44);
    }
    else {
      drawTexturedModalRect(j + 0, 30, 0, 0, 155, 44);
      drawTexturedModalRect(j + 155, 30, 0, 45, 155, 44);
    }

    GlStateManager.pushMatrix();
    GlStateManager.translate(this.width / 2 + 90, 70.0F, 0.0F);
    GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
    float f = 1.8F - MathHelper.abs(MathHelper.sin(Minecraft.getSystemTime() % 1000L / 1000.0F * ((float) Math.PI * 2F)) * 0.1F);
    f = f * 100.0F / (this.fontRendererObj.getStringWidth(this.splashText) + 32);
    GlStateManager.scale(f, f, f);
    drawCenteredString(this.fontRendererObj, this.splashText, 0, -8, -256);
    GlStateManager.popMatrix();
    String disclaimer = "Copyright Mojang AB. Do not distribute!";

    drawString(this.fontRendererObj, disclaimer, this.width - this.fontRendererObj.getStringWidth(disclaimer) - 2, this.height - 10, -1);

    if (this.openGLWarning1 != null && !this.openGLWarning1.isEmpty()) {
      drawRect(this.openGLWarningX1 - 2, this.openGLWarningY1 - 2, this.openGLWarningX2 + 2, this.openGLWarningY2 - 1, 1428160512);
      drawString(this.fontRendererObj, this.openGLWarning1, this.openGLWarningX1, this.openGLWarningY1, -1);
      drawString(this.fontRendererObj, this.openGLWarning2, (this.width - this.openGLWarning2Width) / 2, this.buttonList.get(0).yPosition - 12, -1);
    }

    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  @Override
  protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
    super.mouseClicked(mouseX, mouseY, mouseButton);

    synchronized (this.threadLock) {
      if (!this.openGLWarning1.isEmpty() && mouseX >= this.openGLWarningX1 && mouseX <= this.openGLWarningX2 && mouseY >= this.openGLWarningY1
          && mouseY <= this.openGLWarningY2) {
        GuiConfirmOpenLink guiConfirmOpenLink = new GuiConfirmOpenLink(this, this.openGLWarningLink, 13, true);
        guiConfirmOpenLink.disableSecurityWarning();
        this.mc.displayGuiScreen(guiConfirmOpenLink);
      }
    }

    ForgeHooksClient.mainMenuMouseClick(mouseX, mouseY, mouseButton, this.fontRendererObj, this.width);
  }
}
