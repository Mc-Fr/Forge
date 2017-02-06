package net.mcfr.craftsmanship;

import net.mcfr.craftsmanship.tile_entities.TileEntityLoom;
import net.mcfr.guis.CustomGuiScreens;

public class BlockLoom extends BlockRack<TileEntityLoom> {
  public BlockLoom() {
    super("loom", TileEntityLoom.class);
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.LOOM;
  }
}
