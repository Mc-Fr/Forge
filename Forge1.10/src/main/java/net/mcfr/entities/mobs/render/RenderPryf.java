package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityPryf;
import net.mcfr.entities.mobs.model.ModelPryf;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu du Pryf.
 *
 * @author Mc-Fr
 */
public class RenderPryf extends RenderLiving<EntityPryf> {
  private static final ResourceLocation PRYF_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/pryf.png");

  public RenderPryf(RenderManager renderManagerIn, ModelPryf modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntityPryf entity) {
    return PRYF_TEXTURES;
  }

  @Override
  public void doRender(EntityPryf entity, double x, double y, double z, float entityYaw, float partialTicks) {
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }
}
