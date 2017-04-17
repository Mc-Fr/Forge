package net.mcfr.construction;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockHay;
import net.minecraft.block.SoundType;

/**
 * Bloc de vieille paille.
 *
 * @author Mc-Fr
 */
public class BlockOldHay extends BlockHay {
  public BlockOldHay() {
    super();
    String name = "old_hay";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setSoundType(SoundType.PLANT);
    setHardness(0.5f);
  }
}
