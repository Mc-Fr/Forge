package net.mcfr.craftsmanship.tile_entities;

import net.mcfr.craftsmanship.BlockLoom;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.minecraft.util.EnumFacing;

public class TileEntityLoom extends TileEntityRack {
  public TileEntityLoom() {
    this(EnumFacing.NORTH);
  }

  public TileEntityLoom(EnumFacing facing) {
    super("loom", facing, BlockLoom.class, ContainerRack.class);
  }
}
