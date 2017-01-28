package net.mcfr.decoration.signs.tileEntities;

import net.mcfr.Constants;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileEntityWallNoteRenderer extends TileEntitySpecialRenderer<TileEntityWallNote> {
  private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "");

  @Override
  public void renderTileEntityAt(TileEntityWallNote te, double x, double y, double z, float partialTicks, int destroyStage) {

    if (destroyStage >= 0) {
      bindTexture(DESTROY_STAGES[destroyStage]);
      GlStateManager.matrixMode(5890);
      GlStateManager.pushMatrix();
      GlStateManager.scale(4, 2, 1);
      GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
      GlStateManager.matrixMode(5888);
    }
    else {
      bindTexture(TEXTURE);
    }
  }
}
