package net.mcfr.decoration;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockPebbles extends BlockFloorDecoration {
  public BlockPebbles() {
    super("pebbles_block", Material.ROCK, SoundType.GROUND, 0.5f, null);
  }
}
