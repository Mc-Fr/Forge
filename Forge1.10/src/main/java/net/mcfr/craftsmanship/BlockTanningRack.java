package net.mcfr.craftsmanship;

import net.mcfr.craftsmanship.tile_entities.TileEntityTanningRack;
import net.mcfr.guis.CustomGuiScreens;

public class BlockTanningRack extends BlockRack<TileEntityTanningRack> {
  public BlockTanningRack() {
    super("tanning_rack", TileEntityTanningRack.class);
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.TANNING_RACK;
  }
}
