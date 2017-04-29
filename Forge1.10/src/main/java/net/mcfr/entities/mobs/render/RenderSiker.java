package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntitySiker;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu du Siker.
 *
 * @author Mc-Fr
 */
public class RenderSiker extends RenderLiving<EntitySiker> {
  private static final ResourceLocation SIKER_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/siker.png");

  public RenderSiker(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelBaseIn, shadowSizeIn);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntitySiker entity) {
    return SIKER_TEXTURES;
  }
}
