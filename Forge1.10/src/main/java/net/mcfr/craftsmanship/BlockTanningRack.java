package net.mcfr.craftsmanship;

import net.mcfr.commons.CustomGuiScreens;
import net.mcfr.craftsmanship.tileEntities.TileEntityTanningRack;

public class BlockTanningRack extends BlockRack<TileEntityTanningRack> {
  public BlockTanningRack() {
    super("tanning_rack", TileEntityTanningRack.class);
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.TANNING_RACK;
  }
}
