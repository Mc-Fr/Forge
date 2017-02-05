package net.mcfr.decoration.signs.tileEntities;

import java.util.List;

import net.mcfr.decoration.signs.McfrBlockStandingSign;
import net.mcfr.decoration.signs.McfrBlockSuspendedSign;
import net.mcfr.decoration.signs.models.ModelSign;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * @see net.minecraft.client.renderer.tileentity.TileEntitySignRenderer
 * @author Mc-Fr
 */
public abstract class TileEntitySignRenderer<T extends TileEntityMcfrSign> extends TileEntitySpecialRenderer<T> {
  /** La texture à afficher. */
  private final ResourceLocation signTexture;
  /** Le modèle de panneau. */
  private final ModelSign model;

  public TileEntitySignRenderer(String domain, String texture) {
    this.signTexture = new ResourceLocation(domain, texture);
    this.model = new ModelSign();
  }

  @Override
  public void renderTileEntityAt(T te, double x, double y, double z, float partialTicks, int destroyStage) {
    Block block = te.getBlockType();
    GlStateManager.pushMatrix();

    if (block instanceof McfrBlockStandingSign || block instanceof McfrBlockSuspendedSign) {
      float meta = te.getBlockMetadata() * 360 / 16;

      GlStateManager.translate((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f);
      GlStateManager.rotate(-meta, 0, 1, 0);
      if (block instanceof McfrBlockStandingSign) {
        this.model.signStick.showModel = true;
        this.model.signRope1.showModel = false;
        this.model.signRope2.showModel = false;
      }
      else if (block instanceof McfrBlockSuspendedSign) {
        GlStateManager.translate(0, -0.4f, 0);
        this.model.signStick.showModel = false;
        this.model.signRope1.showModel = true;
        this.model.signRope2.showModel = true;
      }
    }
    else {
      int meta = te.getBlockMetadata();
      float angle = 0;

      if (meta == 2)
        angle = 180;
      if (meta == 4)
        angle = 90;
      if (meta == 5)
        angle = 270;

      GlStateManager.translate((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f);
      GlStateManager.rotate(-angle, 0, 1, 0);
      GlStateManager.translate(0, -0.3125f, -0.4375f);
      this.model.signStick.showModel = false;
      this.model.signRope1.showModel = false;
      this.model.signRope2.showModel = false;
    }

    if (destroyStage >= 0) {
      bindTexture(DESTROY_STAGES[destroyStage]);
      GlStateManager.matrixMode(5890);
      GlStateManager.pushMatrix();
      GlStateManager.scale(4, 2, 1);
      GlStateManager.translate(0.0625f, 0.0625f, 0.0625f);
      GlStateManager.matrixMode(5888);
    }
    else {
      bindTexture(this.signTexture);
    }

    GlStateManager.enableRescaleNormal();
    GlStateManager.pushMatrix();
    GlStateManager.scale(0.6666667f, -0.6666667f, -0.6666667f);

    this.model.renderSign();

    GlStateManager.popMatrix();
    GlStateManager.translate(0, 0.33333334f, 0.046666667f);
    GlStateManager.scale(0.010416667f, -0.010416667f, 0.010416667f);
    GlStateManager.glNormal3f(0, 0, -0.010416667f);
    GlStateManager.depthMask(false);

    if (destroyStage < 0) {
      FontRenderer fontRenderer = getFontRenderer();

      for (int j = 0; j < te.signText.length; ++j) {
        if (te.signText[j] != null) {
          ITextComponent itextcomponent = te.signText[j];
          List<ITextComponent> list = GuiUtilRenderComponents.splitText(itextcomponent, 90, fontRenderer, false, true);
          String s = list != null && !list.isEmpty() ? (te instanceof TileEntityOrpSign ? list.get(0).getUnformattedText() : list.get(0).getFormattedText()) : "";

          if (j == te.lineBeingEdited)
            s = "> " + s + " <";
          if (te instanceof TileEntityOrpSign)
            s = "§f" + s;

          fontRenderer.drawString(s, -fontRenderer.getStringWidth(s) / 2, j * 10 - te.signText.length * 5, 0);
        }
      }
    }

    GlStateManager.depthMask(true);
    GlStateManager.color(1, 1, 1, 1);
    GlStateManager.popMatrix();

    if (destroyStage >= 0) {
      GlStateManager.matrixMode(5890);
      GlStateManager.popMatrix();
      GlStateManager.matrixMode(5888);
    }
  }
}
