package net.mcfr.decoration.misc;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockFeathers extends BlockFloorDecoration {
  public BlockFeathers() {
    super("feathers_block", Material.CLOTH, SoundType.CLOTH, 0.5f, null);
  }
}
