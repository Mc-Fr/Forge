package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityKunawalu;
import net.mcfr.entities.mobs.model.ModelKunawalu;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKunawalu extends RenderLiving<EntityKunawalu> {
  private static final ResourceLocation KUNAWALU_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/kunawalu.png");

  public RenderKunawalu(RenderManager renderManagerIn, ModelKunawalu modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  /**
   * Returns the location of an entity's texture. Doesn't seem to be called
   * unless you call Render.bindEntityTexture.
   */
  protected ResourceLocation getEntityTexture(EntityKunawalu entity) {
    return KUNAWALU_TEXTURES;
  }

  /**
   * Renders the desired {@code T} type Entity.
   */
  @Override
  public void doRender(EntityKunawalu entity, double x, double y, double z, float entityYaw, float partialTicks) {
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }
}
