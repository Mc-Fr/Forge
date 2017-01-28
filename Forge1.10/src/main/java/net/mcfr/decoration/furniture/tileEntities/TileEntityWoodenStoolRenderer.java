package net.mcfr.decoration.furniture.tileEntities;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class TileEntityWoodenStoolRenderer extends TileEntitySpecialRenderer<TileEntityWoodenStool> {
  @Override
  public void renderTileEntityAt(TileEntityWoodenStool te, double x, double y, double z, float partialTicks, int destroyStage) {
    EnumFacing facing = te.getFacing();
    boolean isTall = te.isTall();

    // TODO
  }
}
