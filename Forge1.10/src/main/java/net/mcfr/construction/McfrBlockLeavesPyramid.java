package net.mcfr.construction;

import net.minecraft.block.Block;

public class McfrBlockLeavesPyramid extends McfrBlockPyramid {
  public McfrBlockLeavesPyramid(Block block, int metadata, String materialName) {
    super(block, metadata, 2, 0, materialName + "_leaves", null, -1);
  }
}
