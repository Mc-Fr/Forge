package net.mcfr.craftsmanship.tileEntities;

import net.mcfr.craftsmanship.BlockLoom;
import net.mcfr.craftsmanship.guis.ContainerRack;

public class TileEntityLoom extends TileEntityRack {
  public TileEntityLoom() {
    super("loom", BlockLoom.class, ContainerRack.class);
  }
}
