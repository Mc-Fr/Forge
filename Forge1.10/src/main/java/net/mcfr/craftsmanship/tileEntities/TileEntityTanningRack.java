package net.mcfr.craftsmanship.tileEntities;

import net.mcfr.craftsmanship.BlockTanningRack;
import net.mcfr.craftsmanship.guis.ContainerRack;

public class TileEntityTanningRack extends TileEntityRack {
  public TileEntityTanningRack() {
    super("tanning_rack", BlockTanningRack.class, ContainerRack.class);
  }
}
