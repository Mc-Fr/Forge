package net.mcfr.decoration.signs.tile_entities;

import java.util.List;

import net.mcfr.Constants;
import net.mcfr.decoration.signs.models.ModelWallNote;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * Cette classe s'occupe du rendu des notes murales.
 *
 * @author Mc-Fr
 */
public class TileEntityWallNoteRenderer extends TileEntitySpecialRenderer<TileEntityWallNote> {
  private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/wall_note.png");

  /** Le modèle de la note */
  private ModelWallNote model;

  public TileEntityWallNoteRenderer() {
    this.model = new ModelWallNote();
  }

  @Override
  public void renderTileEntityAt(TileEntityWallNote te, double x, double y, double z, float partialTicks, int destroyStage) {
    int meta = te.getBlockMetadata();
    float angle = 0;

    GlStateManager.pushMatrix();

    if (meta == 2)
      angle = 180;
    if (meta == 4)
      angle = -90;
    if (meta == 5)
      angle = 90;

    GlStateManager.translate((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f);
    GlStateManager.rotate(angle, 0, 1, 0);
    GlStateManager.translate(0, -0.3125f, -0.49f);

    if (destroyStage >= 0) {
      bindTexture(DESTROY_STAGES[destroyStage]);
      GlStateManager.matrixMode(5890);
      GlStateManager.pushMatrix();
      GlStateManager.scale(4, 2, 1);
      GlStateManager.translate(0.0625f, 0.0625f, 0.0625f);
      GlStateManager.matrixMode(5888);
    }
    else {
      bindTexture(TEXTURE);
    }

    GlStateManager.enableRescaleNormal();
    GlStateManager.pushMatrix();
    float scale = 0.5f;
    GlStateManager.scale(scale / 2, -scale, -scale);

    this.model.renderModel();

    GlStateManager.popMatrix();
    GlStateManager.translate(0, 0.33333334f, 0.01f);
    scale = 0.005f;
    GlStateManager.scale(scale, -scale, scale);
    GlStateManager.glNormal3f(0, 0, -0.010416667f);
    GlStateManager.depthMask(false);

    if (destroyStage < 0) {
      FontRenderer fontrenderer = getFontRenderer();

      for (int j = 0; j < te.getText().length; ++j) {
        if (te.getText()[j] != null) {
          ITextComponent itextcomponent = te.getText()[j];
          List<ITextComponent> list = GuiUtilRenderComponents.splitText(itextcomponent, 90, fontrenderer, false, true);
          String s = list != null && !list.isEmpty() ? list.get(0).getFormattedText() : "";

          if (j == te.getLineBeingEdited()) {
            s = "> " + s + " <";
            fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - te.getText().length * 5, 0);
          }
          else {
            fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - te.getText().length * 5, 0);
          }
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
