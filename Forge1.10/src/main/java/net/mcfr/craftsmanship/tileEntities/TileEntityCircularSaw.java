package net.mcfr.craftsmanship.tileEntities;

import net.mcfr.craftsmanship.BlockCircularSaw;
import net.mcfr.craftsmanship.guis.ContainerRack;

public class TileEntityCircularSaw extends TileEntityRack {
  public TileEntityCircularSaw() {
    super("circular_saw", BlockCircularSaw.class, ContainerRack.class);
  }
}
