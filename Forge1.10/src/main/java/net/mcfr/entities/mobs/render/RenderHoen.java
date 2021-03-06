package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityHoen;
import net.mcfr.entities.mobs.gender.Genders;
import net.mcfr.entities.mobs.model.ModelHoen;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Rendu du Hoen.
 *
 * @author Mc-Fr
 */
public class RenderHoen extends RenderLiving<EntityHoen> {
  private static final ResourceLocation HOEN_FEMALE_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/hoen_female.png");
  private static final ResourceLocation HOEN_MALE_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/hoen_male.png");
  private static final ResourceLocation HOEN_CHILD_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/hoen_child.png");

  public RenderHoen(RenderManager renderManagerIn, ModelHoen modelbaseIn, float shadowSizeIn) {
    super(renderManagerIn, modelbaseIn, shadowSizeIn);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntityHoen entity) {
    if (entity.isChild()) {
      return HOEN_CHILD_TEXTURES;
    }
    if (entity.getGender() == Genders.FEMALE) {
      return HOEN_FEMALE_TEXTURES;
    }
    return HOEN_MALE_TEXTURES;
  }

  @Override
  public void doRender(EntityHoen entity, double x, double y, double z, float entityYaw, float partialTicks) {
    entity.incrementTicks();
    super.doRender(entity, x, y, z, entityYaw, partialTicks);
  }
}
