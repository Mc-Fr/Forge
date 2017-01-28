package net.mcfr.decoration.misc.tileEntities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.mcfr.Constants;
import net.mcfr.decoration.misc.models.ModelLongSupport;
import net.mcfr.decoration.misc.models.ModelSupport;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class TileEntitySupportRenderer extends TileEntitySpecialRenderer<TileEntitySupport> {
  private static final ResourceLocation SUPPORT_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/support.png");
  private static final ResourceLocation LONG_SUPPORT_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/long_support.png");

  private ModelSupport support;
  private ModelLongSupport longSupport;

  public TileEntitySupportRenderer() {
    this.support = new ModelSupport();
    this.longSupport = new ModelLongSupport();
  }

  @Override
  @SuppressWarnings("incomplete-switch")
  public void renderTileEntityAt(TileEntitySupport te, double x, double y, double z, float partialTicks, int destroyStage) {
    boolean isLong = te.isLong();
    float angle = 0;

    switch (te.getFacing()) {
      case NORTH:
        angle = 0;
        break;
      case SOUTH:
        angle = 180;
        break;
      case EAST:
        angle = 90;
        break;
      case WEST:
        angle = 270;
        break;
    }

    if (isLong)
      bindTexture(LONG_SUPPORT_TEXTURE);
    else
      bindTexture(SUPPORT_TEXTURE);

    GL11.glPushMatrix();
    GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    GL11.glColor4f(1, 1, 1, 1);
    GL11.glTranslatef((float) x, (float) y + 1, (float) z + 1);
    GL11.glScalef(1.35f, -1.4f, -1.35f);

    GL11.glTranslatef(0.88f, 0.03f, 0.88f);
    GL11.glRotatef(0, 0, 1, 0);
    GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
    GL11.glRotatef(angle, 0, 1, 0);

    if (isLong)
      this.longSupport.renderModel(0.05f);
    else
      this.support.renderModel(0.05f);

    GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    GL11.glPopMatrix();
    GL11.glColor4f(1, 1, 1, 1);
  }
}
