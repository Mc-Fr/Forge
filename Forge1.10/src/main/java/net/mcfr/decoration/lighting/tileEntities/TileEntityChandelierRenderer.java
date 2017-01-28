package net.mcfr.decoration.lighting.tileEntities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.mcfr.Constants;
import net.mcfr.decoration.lighting.models.ModelChandelier;
import net.mcfr.decoration.lighting.models.ModelLargeChandelier;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileEntityChandelierRenderer extends TileEntitySpecialRenderer<TileEntityChandelier> {
  private static final ResourceLocation CHANDELIER_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/chandelier.png");
  private static final ResourceLocation LARGE_CHANDELIER_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/large_chandelier.png");

  private ModelChandelier chandelier;
  private ModelLargeChandelier largeChandelier;

  public TileEntityChandelierRenderer() {
    this.chandelier = new ModelChandelier();
    this.largeChandelier = new ModelLargeChandelier();
  }

  @Override
  public void renderTileEntityAt(TileEntityChandelier te, double x, double y, double z, float partialTicks, int destroyStage) {
    boolean isLarge = te.isLarge();

    if (isLarge)
      bindTexture(LARGE_CHANDELIER_TEXTURE);
    else
      bindTexture(CHANDELIER_TEXTURE);
    GL11.glPushMatrix();
    GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    GL11.glColor4f(1, 1, 1, 1);
    GL11.glTranslatef((float) x, (float) y + 1, (float) z + 1);
    GL11.glScalef(1, -1, -1);

    GL11.glTranslatef(0.5f, -0.05f, 0.5f);
    if (isLarge)
      this.largeChandelier.renderModel(0.05f);
    else
      this.chandelier.renderModel(0.05f);
    GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    GL11.glPopMatrix();
    GL11.glColor4f(1, 1, 1, 1);
  }
}
