package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityHoen;
import net.mcfr.entities.mobs.gender.Genders;
import net.mcfr.entities.mobs.model.ModelHoen;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHoen extends RenderLiving<EntityHoen>{
	private static final ResourceLocation HOEN_FEMALE_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/hoen_female.png");
	private static final ResourceLocation HOEN_MALE_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/hoen_male.png");
	private static final ResourceLocation HOEN_CHILD_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/hoen_child.png");

	public RenderHoen(RenderManager renderManagerIn, ModelHoen modelbaseIn, float shadowSizeIn) {
		super(renderManagerIn, modelbaseIn, shadowSizeIn);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityHoen entity) {
	  if (entity.isChild()) {
	    return HOEN_CHILD_TEXTURES;
	  } else if (entity.getGender() == Genders.FEMALE) {
	    return HOEN_FEMALE_TEXTURES;
	  } else {
	    return HOEN_MALE_TEXTURES;
	  }
	}
	
	/**
     * Renders the desired {@code T} type Entity.
     */
	@Override
    public void doRender(EntityHoen entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
		entity.incrementTicks();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
