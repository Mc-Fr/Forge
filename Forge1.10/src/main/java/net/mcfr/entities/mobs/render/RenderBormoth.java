package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityBormoth;
import net.mcfr.entities.mobs.model.ModelBormoth;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu du Bormoth.
 *
 * @author Mc-Fr
 */
public class RenderBormoth extends RenderLiving<EntityBormoth> {
  private static final ResourceLocation BORMOTH_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/bormoth.png");

  public RenderBormoth(RenderManager renderManagerIn, ModelBormoth modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntityBormoth entity) {
    return BORMOTH_TEXTURES;
  }
}
