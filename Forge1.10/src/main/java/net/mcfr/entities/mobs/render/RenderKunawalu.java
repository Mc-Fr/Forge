package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityKunawalu;
import net.mcfr.entities.mobs.model.ModelKunawalu;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu du Kunawalu.
 *
 * @author Mc-Fr
 */
public class RenderKunawalu extends RenderLiving<EntityKunawalu> {
  private static final ResourceLocation KUNAWALU_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/kunawalu.png");

  public RenderKunawalu(RenderManager renderManagerIn, ModelKunawalu modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntityKunawalu entity) {
    return KUNAWALU_TEXTURES;
  }
}
