package net.mcfr.decoration.furniture.tile_entities;

import static net.mcfr.utils.RenderUtils.*;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

/**
 * Classe s'occupant du rendu du stand d'armes/outils.
 *
 * @author Mc-Fr
 */
public class TileEntityWeaponsStandRenderer extends TileEntitySpecialRenderer<TileEntityWeaponsStand> {
  @Override
  @SuppressWarnings("incomplete-switch")
  public void renderTileEntityAt(TileEntityWeaponsStand te, double x, double y, double z, float partialTicks, int destroyStage) {
    final float scale = 0.75f;
    float xo = 0, yo = 0, zo = 0;

    GlStateManager.pushMatrix();
    GlStateManager.translate(x + 0.5, y + 1, z + 0.5);
    switch (te.getFacing()) {
      case NORTH:
        GlStateManager.rotate(90, 0, 1, 0);
        GlStateManager.rotate(-120, 0, 0, 1);
        GlStateManager.translate(0.3f, 0.5f, 0);
        zo = 0.3f;
        break;
      case EAST:
        GlStateManager.translate(0.3f, -0.3f, 0);
        GlStateManager.rotate(-120, 0, 0, 1);
        zo = 0.3f;
        break;
      case SOUTH:
        GlStateManager.rotate(-90, 0, 1, 0);
        GlStateManager.rotate(-120, 0, 0, 1);
        GlStateManager.translate(0.3f, 0.5f, 0);
        zo = 0.3f;
        break;
      case WEST:
        GlStateManager.translate(-0.3f, -0.3f, 0);
        GlStateManager.rotate(210, 0, 0, 1);
        zo = 0.3f;
        break;
    }

    if (te.hasItem(0)) {
      GlStateManager.translate(xo, yo, zo);
      GlStateManager.scale(scale, scale, scale);
      renderItem(te.getItem(0));
      GlStateManager.translate(-xo, -yo, -zo);
    }
    if (te.hasItem(1)) {
      if (te.hasItem(0)) {
        GlStateManager.scale(scale / 1.5, scale / 1.5, scale / 1.5);
      }
      else
        GlStateManager.scale(scale, scale, scale);
      renderItem(te.getItem(1));
    }

    GlStateManager.popMatrix();
  }
}
