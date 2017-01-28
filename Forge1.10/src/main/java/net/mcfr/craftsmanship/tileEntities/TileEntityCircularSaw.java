package net.mcfr.craftsmanship.tileEntities;

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
