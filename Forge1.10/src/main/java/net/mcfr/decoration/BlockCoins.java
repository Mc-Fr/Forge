package net.mcfr.decoration;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCoins extends BlockFloorDecoration {
  public BlockCoins() {
    super("coins_block", Material.IRON, SoundType.METAL, 0.5f, null);
  }
}
