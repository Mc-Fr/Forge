package net.mcfr.decoration.tileEntities;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class TileEntitySupportRenderer extends TileEntitySpecialRenderer<TileEntitySupport> {
  @Override
  public void renderTileEntityAt(TileEntitySupport te, double x, double y, double z, float partialTicks, int destroyStage) {
    EnumFacing facing = te.getFacing();
    boolean isLong = te.isLong();

    // TODO
  }
}
