package net.mcfr.forge.tile_entities;

import org.lwjgl.opengl.GL11;

import net.mcfr.Constants;
import net.mcfr.forge.ModelBellows;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu du soufflet.
 *
 * @author Mc-Fr
 */
public class TileEntityBellowsRenderer extends TileEntitySpecialRenderer<TileEntityBellows> {
  private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/bellows.png");

  /** Modèle du soufflet */
  private ModelBellows model;

  public TileEntityBellowsRenderer() {
    this.model = new ModelBellows();
  }

  @Override
  public void renderTileEntityAt(TileEntityBellows te, double x, double y, double z, float partialTicks, int destroyStage) {
    bindTexture(TEXTURE);
    GL11.glPushMatrix();
    GL11.glEnable(32826);
    GL11.glColor4f(1, 1, 1, 1);
    GL11.glTranslatef((float) x, (float) y, (float) z);

    this.model.renderModel(TileEntityBellows.STEPS - te.getStep());

    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }
}
