package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityGalt;
import net.mcfr.entities.mobs.model.ModelGalt;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu du Galt.
 *
 * @author Mc-Fr
 */
public class RenderGalt extends RenderLiving<EntityGalt> {
  private static final ResourceLocation GALT_TEXTURES_1 = new ResourceLocation(Constants.MOD_ID, "textures/entity/galt1.png");
  private static final ResourceLocation GALT_TEXTURES_2 = new ResourceLocation(Constants.MOD_ID, "textures/entity/galt2.png");

  public RenderGalt(RenderManager renderManagerIn, ModelGalt modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntityGalt entity) {
    return entity.getFurType() == 1 ? GALT_TEXTURES_1 : GALT_TEXTURES_2;
  }
}
