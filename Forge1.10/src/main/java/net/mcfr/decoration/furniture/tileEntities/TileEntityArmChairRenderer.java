package net.mcfr.decoration.furniture.tileEntities;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import net.mcfr.Constants;
import net.mcfr.decoration.furniture.models.ArmChairModel;
import net.mcfr.decoration.furniture.models.ModelStoneArmChair;
import net.mcfr.decoration.furniture.models.ModelWoodenArmChair;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntityArmChairRenderer extends TileEntitySpecialRenderer<TileEntityArmChair> {
  private final Map<String, ResourceLocation> resources;
  private final Map<String, ArmChairModel> models;

  public TileEntityArmChairRenderer() {
    this.resources = new HashMap<>();
    this.resources.put("wooden", new ResourceLocation(Constants.MOD_ID, "textures/entity/wooden_armchair.png"));
    this.resources.put("stone", new ResourceLocation(Constants.MOD_ID, "textures/entity/stone_armchair.png"));
    this.models = new HashMap<>();
    this.models.put("wooden", new ModelWoodenArmChair());
    this.models.put("stone", new ModelStoneArmChair());
  }

  @Override
  @SuppressWarnings("incomplete-switch")
  public void renderTileEntityAt(TileEntityArmChair te, double x, double y, double z, float partialTicks, int destroyStage) {
    EnumFacing facing = te.getFacing();
    String type = te.getType();

    bindTexture(this.resources.get(type));

    GL11.glPushMatrix();
    GL11.glDisable(GL11.GL_CULL_FACE);
    GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
    switch (facing) {
      case NORTH:
        GL11.glRotatef(180, 1, 0, 0);
        break;
      case SOUTH:
        GL11.glRotatef(180, 0, 0, 1);
        break;
      case EAST:
        GL11.glRotatef(90, 0, 1, 0);
        GL11.glRotatef(180, 0, 0, 1);
        break;
      case WEST:
        GL11.glRotatef(90, 0, 1, 0);
        GL11.glRotatef(180, 0, 0, 0);
        break;
    }

    this.models.get(type).renderModel(0.0625f);

    GL11.glEnable(GL11.GL_CULL_FACE);
    GL11.glPopMatrix();
  }
}
