package net.mcfr.decoration.lighting;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockTorch;

public class BlockLargeTorch extends BlockTorch {
  public BlockLargeTorch() {
    super();
    String name = "large_torch";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setLightLevel(0.75f);
  }
}
