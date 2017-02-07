package net.mcfr.craftsmanship.tile_entities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.mcfr.Constants;
import net.mcfr.craftsmanship.models.ModelCircularSaw;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

/**
 * Classe s'occupant du rendu de la scie circulaire.
 *
 * @author Mc-Fr
 */
public class TileEntityCircularSawRenderer extends TileEntitySpecialRenderer<TileEntityCircularSaw> {
  private static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/circular_saw.png");

  /** Le modèle de la scie */
  private ModelCircularSaw model;

  public TileEntityCircularSawRenderer() {
    this.model = new ModelCircularSaw();
  }

  @Override
  public void renderTileEntityAt(TileEntityCircularSaw te, double x, double y, double z, float partialTicks, int destroyStage) {
    EnumFacing facing = te.getFacing();

    bindTexture(TEXTURE);
    GL11.glPushMatrix();
    GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    GL11.glColor4f(1, 1, 1, 1);
    GL11.glTranslatef((float) x, (float) y + 1, (float) z + 1);
    GL11.glScalef(1.35F, -1.4F, -1.35F);
    GL11.glTranslatef(0.88F, 0.03F, 0.88F);
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    GL11.glRotatef((facing.getHorizontalIndex() - 1) * 90, 0, 1, 0);

    this.model.renderModel(0.05f);

    GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    GL11.glPopMatrix();
    GL11.glColor4f(1, 1, 1, 1);
  }
}
