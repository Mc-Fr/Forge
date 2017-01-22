package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityNiale;
import net.mcfr.entities.mobs.model.ModelNiale;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderNiale extends RenderLiving<EntityNiale> {
  private static final ResourceLocation NIALE_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/niale.png");

  public RenderNiale(RenderManager renderManagerIn, ModelNiale modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  /**
   * Returns the location of an entity's texture. Doesn't seem to be called
   * unless you call Render.bindEntityTexture.
   */
  protected ResourceLocation getEntityTexture(EntityNiale entity) {
    return NIALE_TEXTURES;
  }

  /**
   * Renders the desired {@code T} type Entity.
   */
  @Override
  public void doRender(EntityNiale entity, double x, double y, double z, float entityYaw, float partialTicks) {
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }
}
