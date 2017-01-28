package net.mcfr.decoration.furniture.tileEntities;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class TileEntityArmChairRenderer extends TileEntitySpecialRenderer<TileEntityArmChair> {
  @Override
  public void renderTileEntityAt(TileEntityArmChair te, double x, double y, double z, float partialTicks, int destroyStage) {
    EnumFacing facing = te.getFacing();
    String type = te.getType();

    // TODO
  }
}
