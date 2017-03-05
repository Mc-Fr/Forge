package net.mcfr.craftsmanship.tile_entities;

import net.mcfr.craftsmanship.BlockMortar;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.minecraft.util.EnumFacing;

public class TileEntityMortar extends TileEntityRack {
  public TileEntityMortar() {
    super("mortar", EnumFacing.NORTH, BlockMortar.class, ContainerRack.class);
  }
}
