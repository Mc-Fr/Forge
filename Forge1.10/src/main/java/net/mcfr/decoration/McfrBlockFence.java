package net.mcfr.decoration;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class McfrBlockFence extends BlockFence {
  public McfrBlockFence(String materialName, float hardness, float resistance, Material materialIn, SoundType sound, String tool, int harvestLevel) {
    super(materialIn, MapColor.BROWN);
    String name = materialName + "_fence";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(resistance);
    setHardness(hardness);
    setSoundType(sound);
    if (tool != null) setHarvestLevel(tool, harvestLevel);
  }
}
