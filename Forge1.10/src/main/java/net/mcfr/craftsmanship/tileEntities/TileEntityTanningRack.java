package net.mcfr.craftsmanship.tileEntities;

import net.mcfr.craftsmanship.BlockTanningRack;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.minecraft.util.EnumFacing;

public class TileEntityTanningRack extends TileEntityRack {
  public TileEntityTanningRack() {
    this(EnumFacing.NORTH);
  }

  public TileEntityTanningRack(EnumFacing facing) {
    super("tanning_rack", facing, BlockTanningRack.class, ContainerRack.class);
  }
}
