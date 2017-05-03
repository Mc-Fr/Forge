package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityTaure;
import net.mcfr.entities.mobs.model.ModelTaure;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu du Taure.
 *
 * @author Mc-Fr
 */
public class RenderTaure extends RenderLiving<EntityTaure> {
  private static final ResourceLocation TAURE_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/taure.png");

  public RenderTaure(RenderManager renderManagerIn, ModelTaure modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntityTaure entity) {
    return TAURE_TEXTURES;
  }
}
