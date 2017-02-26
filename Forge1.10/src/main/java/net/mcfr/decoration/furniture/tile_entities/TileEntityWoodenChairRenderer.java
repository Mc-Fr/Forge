package net.mcfr.decoration.furniture.tile_entities;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

/**
 * Classe s'occupant du rendu des chaises.
 *
 * @author Mc-Fr
 */
public class TileEntityWoodenChairRenderer extends TileEntitySpecialRenderer<TileEntityWoodenChair> {
  @Override
  public void renderTileEntityAt(TileEntityWoodenChair te, double x, double y, double z, float partialTicks, int destroyStage) {
    EnumFacing facing = te.getFacing();

    // TODO
  }
}
