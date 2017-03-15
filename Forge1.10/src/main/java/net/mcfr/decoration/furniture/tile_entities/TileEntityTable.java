package net.mcfr.decoration.furniture.tile_entities;

import net.mcfr.commons.TileEntityLargeOriented;
import net.minecraft.util.EnumFacing;

public class TileEntityTable extends TileEntityLargeOriented {
  
  public TileEntityTable(String type) {
    super(type + "_table");
  }

  public TileEntityTable(EnumFacing facing, String type) {
    super(facing, type + "_table");
  }
}
