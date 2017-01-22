package net.mcfr.craftsmanship;

import net.mcfr.commons.CustomGuiScreens;
import net.mcfr.craftsmanship.tileEntities.TileEntityLoom;

public class BlockLoom extends BlockRack<TileEntityLoom> {
  public BlockLoom() {
    super("loom", TileEntityLoom.class);
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.LOOM;
  }
}
