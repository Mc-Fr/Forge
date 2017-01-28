package net.mcfr.decoration.containerBlocks.tileEntities;

import static net.mcfr.utils.RenderUtils.*;

import net.mcfr.McfrBlocks;
import net.mcfr.utils.math.Point2d;
import net.mcfr.utils.math.Point3d;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;

public class TileEntityPalletRenderer extends TileEntitySpecialRenderer<TileEntityPallet> {
  private static final float HEIGHT = 2 / 16f;
  private static final float W = 1 / 16f;

  @Override
  public void renderTileEntityAt(TileEntityPallet te, double x, double y, double z, float partialTicks, int destroyStage) {
    ItemStack[] stacks = new ItemStack[te.getSizeInventory()];
    for (int i = 0; i < stacks.length; i++) {
      stacks[i] = te.getStackInSlot(i);
    }

    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);

    if (te.getWorld().getBlockState(te.getPos().up()).getBlock() == McfrBlocks.PALLET)
      drawSupports();
    drawContent(stacks);

    GlStateManager.popMatrix();
  }

  private static void drawSupports() {
    bindTex("minecraft:textures/blocks/planks_birch.png");

    float f = 15 / 16f;

    drawSupport(0, 0);
    drawSupport(f, 0);
    drawSupport(f, f);
    drawSupport(0, f);

    draw();
  }

  private static void drawSupport(float xOffset, float zOffset) {
    float h = HEIGHT + 1 / 16f;
    Point3d[] p = {new Point3d(xOffset, h, zOffset), new Point3d(W + xOffset, h, zOffset), new Point3d(W + xOffset, h, W + zOffset), new Point3d(xOffset, h, W + zOffset), //
      new Point3d(xOffset, 1, zOffset), new Point3d(W + xOffset, 1, zOffset), new Point3d(W + xOffset, 1, W + zOffset), new Point3d(xOffset, 1, W + zOffset)};
    Point2d[] t = {new Point2d(0, 0), new Point2d(1, 0), new Point2d(0, W), new Point2d(1, W)};

    // Nord
    drawQuad(p[0], p[4], p[5], p[1], t[3], t[2], t[0], t[1]);
    // Est
    drawQuad(p[1], p[5], p[6], p[2], t[3], t[2], t[0], t[1]);
    // Sud
    drawQuad(p[2], p[6], p[7], p[3], t[3], t[2], t[0], t[1]);
    // Ouest
    drawQuad(p[3], p[7], p[4], p[0], t[3], t[2], t[0], t[1]);
  }

  private static void drawContent(ItemStack[] stacks) {
    final float px = 1 / 16f;
    final float scaleFactor = 1f;
    final float blockW = 4 / scaleFactor;

    GlStateManager.translate(3 * px, 2 * px, 3 * px);

    for (int i = 0; i < stacks.length; i++) {
      if (stacks[i] == null)
        continue;
      float xo = scaleFactor * ((1 + blockW) * px * (i % 3));
      float zo = scaleFactor * ((1 + blockW) * px * (i / 3));

      if (i > 0)
        GlStateManager.translate(xo, 0, zo);
      for (int j = 0; j < 3 * ((float) stacks[i].stackSize / stacks[i].getMaxStackSize()); j++) {
        final float yo = scaleFactor * (blockW * px * j);

        GlStateManager.translate(0, yo, 0);
        renderItem(stacks[i]);
        GlStateManager.translate(0, -yo, 0);
      }
      GlStateManager.translate(-xo, 0, -zo);
    }
  }
}
