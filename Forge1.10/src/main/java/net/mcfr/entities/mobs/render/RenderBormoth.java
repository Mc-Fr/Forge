package net.mcfr.entities.mobs.render;

import net.mcfr.Constants;
import net.mcfr.entities.mobs.entity.EntityBormoth;
import net.mcfr.entities.mobs.model.ModelBormoth;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBormoth extends RenderLiving<EntityBormoth>{
	private static final ResourceLocation BORMOTH_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/entity/bormoth.png");

	public RenderBormoth(RenderManager renderManagerIn, ModelBormoth modelbaseIn, float shadowSizeIn) {
		super(renderManagerIn, modelbaseIn, shadowSizeIn);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityBormoth entity) {
		return BORMOTH_TEXTURES;
	}
	
	/**
     * Renders the desired {@code T} type Entity.
     */
	@Override
    public void doRender(EntityBormoth entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
