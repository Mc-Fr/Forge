package net.mcfr.craftsmanship.tile_entities;

import net.mcfr.craftsmanship.BlockCircularSaw;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.minecraft.util.EnumFacing;

public class TileEntityCircularSaw extends TileEntityRack {
  public TileEntityCircularSaw() {
    this(EnumFacing.NORTH);
  }

  public TileEntityCircularSaw(EnumFacing facing) {
    super("circular_saw", facing, BlockCircularSaw.class, ContainerRack.class);
  }
}
