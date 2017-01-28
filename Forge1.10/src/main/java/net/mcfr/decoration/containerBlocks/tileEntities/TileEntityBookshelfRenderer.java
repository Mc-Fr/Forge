package net.mcfr.decoration.containerBlocks.tileEntities;

import java.util.HashMap;
import java.util.Map;

import net.mcfr.Constants;
import net.mcfr.decoration.containerBlocks.models.ModelBookShelf;
import net.mcfr.utils.RenderUtils;
import net.minecraft.block.BlockPlanks;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TileEntityBookshelfRenderer extends TileEntitySpecialRenderer<TileEntityBookshelf> {
  private static final Map<Integer, Map<Integer, ResourceLocation>> RESOURCES;

  static {
    RESOURCES = new HashMap<>();
    for (BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
      Map<Integer, ResourceLocation> resources = new HashMap<>();

      for (int i = 0; i < 4; i++)
        resources.put(i, new ResourceLocation(Constants.MOD_ID, String.format("textures/entity/bookshelves/%s_bookshelf_%d.png", type, i)));
      RESOURCES.put(type.getMetadata(), resources);
    }
  }

  private ModelBookShelf model;

  public TileEntityBookshelfRenderer() {
    this.model = new ModelBookShelf();
  }

  @Override
  public void renderTileEntityAt(TileEntityBookshelf te, double x, double y, double z, float partialTicks, int destroyStage) {
    int meta = te.getBlockMetadata();
    float fillingRatio = 0;
    int step = 0;

    for (int i = 0; i < te.getSizeInventory(); i++) {
      ItemStack stack = te.getStackInSlot(i);

      if (stack != null) {
        fillingRatio += ((float) stack.stackSize / te.getInventoryStackLimit()) / te.getSizeInventory();
      }
    }

    if (fillingRatio <= 0.25f)
      step = 0;
    else if (fillingRatio <= 0.5f)
      step = 1;
    else if (fillingRatio <= 0.75f)
      step = 2;
    else
      step = 3;

    bindTexture(RESOURCES.get(meta).get(step));
    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);
    RenderUtils.fixLighting(te.getWorld(), te.getPos());
    this.model.renderModel(0.03126f);
    GlStateManager.popMatrix();
  }
}