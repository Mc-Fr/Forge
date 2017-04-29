package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityFjalla;
import net.mcfr.entities.mobs.model.ModelFjalla;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu du Fjalla.
 *
 * @author Mc-Fr
 */
public class RenderFjalla extends RenderLiving<EntityFjalla> {
  private static final ResourceLocation FJALLA_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/fjalla.png");

  public RenderFjalla(RenderManager renderManagerIn, ModelFjalla modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntityFjalla entity) {
    return FJALLA_TEXTURES;
  }

  @Override
  public void doRender(EntityFjalla entity, double x, double y, double z, float entityYaw, float partialTicks) {
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }
}
