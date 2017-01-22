package net.mcfr.craftsmanship;

import net.mcfr.commons.CustomGuiScreens;
import net.mcfr.craftsmanship.tileEntities.TileEntityCircularSaw;

public class BlockCircularSaw extends BlockRack<TileEntityCircularSaw> {
  public BlockCircularSaw() {
    super("circular_saw", TileEntityCircularSaw.class);
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.SAW;
  }
}
