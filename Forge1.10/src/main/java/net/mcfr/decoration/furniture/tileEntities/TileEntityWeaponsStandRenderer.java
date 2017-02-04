package net.mcfr.decoration.furniture.tileEntities;

import static net.mcfr.utils.RenderUtils.*;

import net.mcfr.McfrBlocks;
import net.mcfr.decoration.furniture.BlockWeaponsStand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class TileEntityWeaponsStandRenderer extends TileEntitySpecialRenderer<TileEntityWeaponsStand> {
  @Override
  @SuppressWarnings("incomplete-switch")
  public void renderTileEntityAt(TileEntityWeaponsStand te, double x, double y, double z, float partialTicks, int destroyStage) {
    EnumFacing facing = getFacing(te);
    if (facing == null)
      return;

    final float scale = 0.75f;
    float xo = 0, yo = 0, zo = 0;

    GlStateManager.pushMatrix();
    GlStateManager.translate(x + 0.5, y + 1, z + 0.5);
    switch (facing) {
      case NORTH:
        GlStateManager.rotate(90, 0, 1, 0);
        GlStateManager.rotate(-120, 0, 0, 1);
        GlStateManager.translate(0.3f, 0.5f, 0);
        zo = 0.3f;
        break;
      case EAST:
        GlStateManager.translate(0.3f, -0.3f, 0);
        GlStateManager.rotate(-120, 0, 0, 1);
        zo = 0.3f;
        break;
      case SOUTH:
        GlStateManager.rotate(-90, 0, 1, 0);
        GlStateManager.rotate(-120, 0, 0, 1);
        GlStateManager.translate(0.3f, 0.5f, 0);
        zo = 0.3f;
        break;
      case WEST:
        GlStateManager.translate(-0.3f, -0.3f, 0);
        GlStateManager.rotate(210, 0, 0, 1);
        zo = 0.3f;
        break;
    }

    if (te.hasItem(0)) {
      GlStateManager.translate(xo, yo, zo);
      GlStateManager.scale(scale, scale, scale);
      renderItem(te.getItem(0));
      GlStateManager.translate(-xo, -yo, -zo);
    }
    if (te.hasItem(1)) {
      if (te.hasItem(0)) {
        GlStateManager.scale(scale / 1.5, scale / 1.5, scale / 1.5);
      }
      else
        GlStateManager.scale(scale, scale, scale);
      renderItem(te.getItem(1));
    }

    GlStateManager.popMatrix();
  }

  private EnumFacing getFacing(TileEntityWeaponsStand te) {
    IBlockState state = te.getWorld().getBlockState(te.getPos());
    return state.getBlock() == McfrBlocks.WEAPONS_STAND ? state.getValue(BlockWeaponsStand.FACING) : null;
  }
}
