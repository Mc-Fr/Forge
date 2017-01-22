package net.mcfr.construction;

import net.minecraft.block.Block;

public class McfrBlockStairs extends McfrBlockInclined {
  public McfrBlockStairs(Block block, int metadata, String materialName, String tool, int harvestLevel) {
    super(block, metadata, materialName, "stairs", tool, harvestLevel);
  }
}