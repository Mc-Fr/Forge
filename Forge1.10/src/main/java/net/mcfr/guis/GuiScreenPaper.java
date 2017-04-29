package net.mcfr.guis;

import java.io.IOException;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;

import io.netty.buffer.Unpooled;
import net.mcfr.misc.ItemSignedPaper;
import net.mcfr.misc.ItemWriteablePaper;
import net.mcfr.utils.NBTUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Interface du papier signé.
 *
 * @author Mc-Fr
 */
public class GuiScreenPaper extends GuiScreen {
  private static final ResourceLocation PAPER_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");

  private final EntityPlayer editingPlayer;
  private final ItemStack paper;
  private final boolean paperIsUnsigned;
  private boolean paperIsModified;
  private boolean paperGettingSigned;
  private int updateCount;
  private int paperTotalPages;
  private int currPage;
  private NBTTagList paperPages;
  private String paperTitle;
  private List<ITextComponent> cachedComponents;
  private int cachedPage = -1;
  private NextPageButton buttonNextPage;
  private NextPageButton buttonPreviousPage;
  private GuiButton buttonDone;
  private GuiButton buttonSign;
  private GuiButton buttonFinalize;
  private GuiButton buttonCancel;

  /**
   * Crée une interface d'édition.
   * 
   * @param player le joueur
   * @param paper le papier
   * @param isUnsigned indique si le papier est signé
   */
  public GuiScreenPaper(EntityPlayer player, ItemStack paper, boolean isUnsigned) {
    this.editingPlayer = player;
    this.paperTitle = "";
    this.paper = paper;
    this.paperIsUnsigned = isUnsigned;
    this.paperTotalPages = 1;

    if (this.paper.hasTagCompound()) {
      this.paperPages = this.paper.getTagCompound().getTagList("Pages", NBTUtils.TAG_STRING);

      if (this.paperPages != null) {
        this.paperPages = this.paperPages.copy();
        this.paperTotalPages = this.paperPages.tagCount();

        if (this.paperTotalPages < 1) {
          this.paperTotalPages = 1;
        }
      }
    }

    if (this.paperPages == null && isUnsigned) {
      this.paperPages = new NBTTagList();
      this.paperPages.appendTag(new NBTTagString(""));
      this.paperTotalPages = 1;
    }
  }

  @Override
  public void updateScreen() {
    super.updateScreen();
    this.updateCount++;
  }

  @Override
  public void initGui() {
    this.buttonList.clear();
    Keyboard.enableRepeatEvents(true);

    if (this.paperIsUnsigned) {
      this.buttonSign = addButton(new GuiButton(3, this.width / 2 - 100, 196, 98, 20, I18n.format("book.signButton")));
      this.buttonDone = addButton(new GuiButton(0, this.width / 2 + 2, 196, 98, 20, I18n.format("gui.done")));
      this.buttonFinalize = addButton(new GuiButton(5, this.width / 2 - 100, 196, 98, 20, I18n.format("book.finalizeButton")));
      this.buttonCancel = addButton(new GuiButton(4, this.width / 2 + 2, 196, 98, 20, I18n.format("gui.cancel")));
    }
    else {
      this.buttonDone = addButton(new GuiButton(0, this.width / 2 - 100, 196, 200, 20, I18n.format("gui.done")));
    }

    int i = (this.width - 192) / 2;
    this.buttonNextPage = addButton(new NextPageButton(1, i + 120, 156, true));
    this.buttonPreviousPage = addButton(new NextPageButton(2, i + 38, 156, false));
    updateButtons();
  }

  @Override
  public void onGuiClosed() {
    Keyboard.enableRepeatEvents(false);
  }

  /**
   * Met à jour les boutons.
   */
  private void updateButtons() {
    this.buttonNextPage.visible = this.currPage < ItemWriteablePaper.MAX_PAGES_NB - 1 && !this.paperGettingSigned
        && (this.currPage < this.paperTotalPages - 1 || this.paperIsUnsigned);
    this.buttonPreviousPage.visible = !this.paperGettingSigned && this.currPage > 0;
    this.buttonDone.visible = !this.paperIsUnsigned || !this.paperGettingSigned;

    if (this.paperIsUnsigned) {
      this.buttonSign.visible = !this.paperGettingSigned;
      this.buttonCancel.visible = this.paperGettingSigned;
      this.buttonFinalize.visible = this.paperGettingSigned;
      this.buttonFinalize.enabled = !this.paperTitle.trim().isEmpty();
    }
  }

  /**
   * Envoie le papier au serveur.
   * 
   * @param publish le papier doit-il être signé ?
   */
  private void sendPaperToServer(boolean publish) {
    if (this.paperIsUnsigned && this.paperIsModified) {
      if (this.paperPages != null) {
        while (this.paperPages.tagCount() > 1) {
          String s = this.paperPages.getStringTagAt(this.paperPages.tagCount() - 1);

          if (!s.isEmpty()) {
            break;
          }

          this.paperPages.removeTag(this.paperPages.tagCount() - 1);
        }

        if (this.paper.hasTagCompound()) {
          this.paper.getTagCompound().setTag("Pages", this.paperPages);
        }
        else {
          this.paper.setTagInfo("Pages", this.paperPages);
        }

        String s1 = "MC|BEdit";

        if (publish) {
          s1 = "MC|BSign";
          this.paper.setTagInfo("Author", new NBTTagString(this.editingPlayer.getName()));
          this.paper.setTagInfo("Title", new NBTTagString(this.paperTitle.trim()));
        }

        // TODO paquet
        PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
        packetbuffer.writeItemStackToBuffer(this.paper);
        this.mc.getConnection().sendPacket(new CPacketCustomPayload(s1, packetbuffer));
      }
    }
  }

  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    if (button.enabled) {
      if (button.id == 0) {
        this.mc.displayGuiScreen(null);
        sendPaperToServer(false);
      }
      else if (button.id == 3 && this.paperIsUnsigned) {
        this.paperGettingSigned = true;
      }
      else if (button.id == 1) {
        if (this.currPage < this.paperTotalPages - 1) {
          this.currPage++;
        }
        else if (this.paperIsUnsigned) {
          addNewPage();

          if (this.currPage < this.paperTotalPages - 1) {
            this.currPage++;
          }
        }
      }
      else if (button.id == 2) {
        if (this.currPage > 0) {
          this.currPage--;
        }
      }
      else if (button.id == 5 && this.paperGettingSigned) {
        sendPaperToServer(true);
        this.mc.displayGuiScreen(null);
      }
      else if (button.id == 4 && this.paperGettingSigned) {
        this.paperGettingSigned = false;
      }

      updateButtons();
    }
  }

  /**
   * Ajoute une page dans la limite du nombre maximal.
   */
  private void addNewPage() {
    if (this.paperPages != null && this.paperPages.tagCount() < ItemWriteablePaper.MAX_PAGES_NB) {
      this.paperPages.appendTag(new NBTTagString(""));
      this.paperTotalPages++;
      this.paperIsModified = true;
    }
  }

  @Override
  protected void keyTyped(char typedChar, int keyCode) throws IOException {
    super.keyTyped(typedChar, keyCode);

    if (this.paperIsUnsigned) {
      if (this.paperGettingSigned) {
        keyTypedInTitle(typedChar, keyCode);
      }
      else {
        keyTypedInPaper(typedChar, keyCode);
      }
    }
  }

  /**
   * Gère les frappes au clavier dans la page.
   * 
   * @param typedChar le caractère tapé
   * @param keyCode le code de la touche
   */
  private void keyTypedInPaper(char typedChar, int keyCode) {
    if (isKeyComboCtrlV(keyCode)) {
      pageInsertIntoCurrent(getClipboardString());
    }
    else {
      switch (keyCode) {
        case 14:
          String s = pageGetCurrent();
          if (!s.isEmpty()) {
            pageSetCurrent(s.substring(0, s.length() - 1));
          }
          break;
        case 28:
        case 156:
          pageInsertIntoCurrent("\n");
          break;
        default:
          if (ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
            pageInsertIntoCurrent("" + typedChar);
          }
          break;
      }
    }
  }

  /**
   * Gère les frappes au clavier dans le titre.
   * 
   * @param typedChar le caractère tapé
   * @param keyCode le code de la touche
   */
  private void keyTypedInTitle(char typedChar, int keyCode) {
    switch (keyCode) {
      case 14:
        if (!this.paperTitle.isEmpty()) {
          this.paperTitle = this.paperTitle.substring(0, this.paperTitle.length() - 1);
          updateButtons();
        }
        break;
      case 28:
      case 156:
        if (!this.paperTitle.isEmpty()) {
          sendPaperToServer(true);
          this.mc.displayGuiScreen(null);
        }
        break;
      default:
        if (this.paperTitle.length() < 16 && ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
          this.paperTitle = this.paperTitle + typedChar;
          updateButtons();
          this.paperIsModified = true;
        }
        break;
    }
  }

  /**
   * @return le texte de la page courante
   */
  private String pageGetCurrent() {
    return this.paperPages != null && this.currPage >= 0 && this.currPage < this.paperPages.tagCount() ? this.paperPages.getStringTagAt(this.currPage)
        : "";
  }

  /**
   * Modifie le texte de la page courante.
   * 
   * @param text le nouveau texte
   */
  private void pageSetCurrent(String text) {
    if (this.paperPages != null && this.currPage >= 0 && this.currPage < this.paperPages.tagCount()) {
      this.paperPages.set(this.currPage, new NBTTagString(text));
      this.paperIsModified = true;
    }
  }

  /**
   * Gère l'insertion de texte.
   * 
   * @param text le texte à insérer
   */
  private void pageInsertIntoCurrent(String text) {
    String s = pageGetCurrent() + text;
    int i = this.fontRendererObj.splitStringWidth(s + "" + TextFormatting.BLACK + "_", 118);

    if (i <= 128 && s.length() < 256) {
      pageSetCurrent(s);
    }
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    GlStateManager.color(1, 1, 1, 1);
    this.mc.getTextureManager().bindTexture(PAPER_GUI_TEXTURES);
    int i = (this.width - 192) / 2;
    drawTexturedModalRect(i, 2, 0, 0, 192, 192);

    if (this.paperGettingSigned) {
      String s = this.paperTitle;

      if (this.paperIsUnsigned) {
        if (this.updateCount / 6 % 2 == 0) {
          s = s + "" + TextFormatting.BLACK + "_";
        }
        else {
          s = s + "" + TextFormatting.GRAY + "_";
        }
      }

      String editTitle = I18n.format("book.editTitle");
      this.fontRendererObj.drawString(editTitle, i + 36 + (116 - this.fontRendererObj.getStringWidth(editTitle)) / 2, 34, 0);
      this.fontRendererObj.drawString(s, i + 36 + (116 - this.fontRendererObj.getStringWidth(s)) / 2, 50, 0);
      String byAuthor = I18n.format("book.byAuthor", this.editingPlayer.getName());
      this.fontRendererObj.drawString(TextFormatting.DARK_GRAY + byAuthor, i + 36 + (116 - this.fontRendererObj.getStringWidth(byAuthor)) / 2, 60, 0);
      this.fontRendererObj.drawSplitString(I18n.format("book.finalizeWarning"), i + 36, 82, 116, 0);
    }
    else {
      String pageIndicator = I18n.format("book.pageIndicator", this.currPage + 1, this.paperTotalPages);
      String str = "";

      if (this.paperPages != null && this.currPage >= 0 && this.currPage < this.paperPages.tagCount()) {
        str = this.paperPages.getStringTagAt(this.currPage);
      }

      if (this.paperIsUnsigned) {
        if (this.fontRendererObj.getBidiFlag()) {
          str += "_";
        }
        else if (this.updateCount / 6 % 2 == 0) {
          str += TextFormatting.BLACK + "_";
        }
        else {
          str += TextFormatting.GRAY + "_";
        }
      }
      else if (this.cachedPage != this.currPage) {
        if (ItemSignedPaper.validPaperTagContents(this.paper.getTagCompound())) {
          try {
            ITextComponent component = ITextComponent.Serializer.jsonToComponent(str);
            this.cachedComponents = component != null ? GuiUtilRenderComponents.splitText(component, 116, this.fontRendererObj, true, true) : null;
          }
          catch (JsonParseException e) {
            this.cachedComponents = null;
          }
        }
        else {
          this.cachedComponents = Lists.newArrayList(new TextComponentString(TextFormatting.DARK_RED + "* Invalid book tag *"));
        }

        this.cachedPage = this.currPage;
      }

      this.fontRendererObj.drawString(pageIndicator, i - this.fontRendererObj.getStringWidth(pageIndicator) + 192 - 44, 18, 0);

      if (this.cachedComponents == null) {
        this.fontRendererObj.drawSplitString(str, i + 36, 34, 116, 0);
      }
      else {
        int h = Math.min(128 / this.fontRendererObj.FONT_HEIGHT, this.cachedComponents.size());

        for (int j = 0; j < h; ++j) {
          this.fontRendererObj.drawString(this.cachedComponents.get(j).getUnformattedText(), i + 36, 34 + j * this.fontRendererObj.FONT_HEIGHT, 0);
        }

        ITextComponent component = getClickedComponentAt(mouseX, mouseY);

        if (component != null) {
          handleComponentHover(component, mouseX, mouseY);
        }
      }
    }

    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  @Override
  protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
    if (mouseButton == 0) {
      ITextComponent component = getClickedComponentAt(mouseX, mouseY);

      if (component != null && handleComponentClick(component))
        return;
    }

    super.mouseClicked(mouseX, mouseY, mouseButton);
  }

  @Override
  protected boolean handleComponentClick(ITextComponent component) {
    ClickEvent event = component.getStyle().getClickEvent();

    if (event == null)
      return false;
    else if (event.getAction() == ClickEvent.Action.CHANGE_PAGE) {
      String s = event.getValue();

      try {
        int i = Integer.parseInt(s) - 1;

        if (i >= 0 && i < this.paperTotalPages && i != this.currPage) {
          this.currPage = i;
          updateButtons();

          return true;
        }
      }
      catch (NumberFormatException e) {}

      return false;
    }
    else {
      boolean flag = super.handleComponentClick(component);

      if (flag && event.getAction() == ClickEvent.Action.RUN_COMMAND) {
        this.mc.displayGuiScreen(null);
      }

      return flag;
    }
  }

  /**
   * Retourne le composant aux coordonnées données.
   * 
   * @param mouseX position x du curseur
   * @param mouseY position y du curseur
   * @return le composant
   */
  public ITextComponent getClickedComponentAt(int mouseX, int mouseY) {
    if (this.cachedComponents != null) {
      int x = mouseX - (this.width - 192) / 2 - 36;
      int y = mouseY - 2 - 16 - 16;

      if (x >= 0 && y >= 0) {
        int h = Math.min(128 / this.fontRendererObj.FONT_HEIGHT, this.cachedComponents.size());

        if (x <= 116 && y < this.mc.fontRendererObj.FONT_HEIGHT * h + h) {
          int h1 = y / this.mc.fontRendererObj.FONT_HEIGHT;

          if (h1 >= 0 && h1 < this.cachedComponents.size()) {
            int i = 0;

            for (ITextComponent component : this.cachedComponents.get(h1)) {
              if (component instanceof TextComponentString) {
                i += this.mc.fontRendererObj.getStringWidth(((TextComponentString) component).getText());

                if (i > x)
                  return component;
              }
            }
          }
        }
      }
    }

    return null;
  }

  /**
   * Bouton permettant de changer de page.
   *
   * @author Mc-Fr
   */
  @SideOnly(Side.CLIENT)
  private class NextPageButton extends GuiButton {
    private final boolean isForward;

    /**
     * Crée un bouton.
     * 
     * @param id l'ID
     * @param x position x
     * @param y position y
     * @param isForward indique si le bouton passe à la page suivante
     */
    public NextPageButton(int id, int x, int y, boolean isForward) {
      super(id, x, y, 23, 13, "");
      this.isForward = isForward;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
      if (this.visible) {
        // @f0
        boolean isMouseOverButton = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width
            && mouseY < this.yPosition + this.height;
        // @f1

        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(PAPER_GUI_TEXTURES);
        int textureX = 0, textureY = 192;

        if (isMouseOverButton) {
          textureX += 23;
        }
        if (!this.isForward) {
          textureY += 13;
        }

        drawTexturedModalRect(this.xPosition, this.yPosition, textureX, textureY, 23, 13);
      }
    }
  }
}
