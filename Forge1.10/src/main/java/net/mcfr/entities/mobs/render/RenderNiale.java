package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityNiale;
import net.mcfr.entities.mobs.model.ModelNiale;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu du Niale.
 *
 * @author Mc-Fr
 */
public class RenderNiale extends RenderLiving<EntityNiale> {
  private static final ResourceLocation NIALE_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/niale.png");

  public RenderNiale(RenderManager renderManagerIn, ModelNiale modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntityNiale entity) {
    return NIALE_TEXTURES;
  }
}
