package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityGronle;
import net.mcfr.entities.mobs.model.ModelGronle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGronle extends RenderLiving<EntityGronle> {
  private static final ResourceLocation GRONLE_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/gronle.png");

  public RenderGronle(RenderManager renderManagerIn, ModelGronle modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  /**
   * Returns the location of an entity's texture. Doesn't seem to be called
   * unless you call Render.bindEntityTexture.
   */
  protected ResourceLocation getEntityTexture(EntityGronle entity) {
    return GRONLE_TEXTURES;
  }

  /**
   * Renders the desired {@code T} type Entity.
   */
  @Override
  public void doRender(EntityGronle entity, double x, double y, double z, float entityYaw, float partialTicks) {
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }
}
