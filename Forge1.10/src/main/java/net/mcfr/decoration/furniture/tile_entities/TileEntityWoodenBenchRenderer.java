package net.mcfr.decoration.furniture.tile_entities;

import net.mcfr.decoration.furniture.BlockBench;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

/**
 * Classe s'occupant du rendu des bancs en bois.
 *
 * @author Mc-Fr
 */
public class TileEntityWoodenBenchRenderer extends TileEntitySpecialRenderer<TileEntityWoodenBench> {
  @Override
  public void renderTileEntityAt(TileEntityWoodenBench te, double x, double y, double z, float partialTicks, int destroyStage) {
    EnumFacing facing = te.getFacing();
    IBlockState state = getWorld().getBlockState(te.getPos());
    boolean connectedNorth = state.getValue(BlockBench.NORTH);
    boolean connectedSouth = state.getValue(BlockBench.SOUTH);
    boolean connectedWest = state.getValue(BlockBench.WEST);
    boolean connectedEast = state.getValue(BlockBench.EAST);

    // TODO
  }
}
