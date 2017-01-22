package net.mcfr.construction;

import net.minecraft.block.Block;

public class McfrBlockSlope extends McfrBlockInclined {
  public McfrBlockSlope(Block block, int metadata, String materialName, String tool, int harvestLevel) {
    super(block, metadata, materialName, "slope", tool, harvestLevel);
  }
}