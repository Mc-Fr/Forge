package net.mcfr.mecanisms.trapdoors;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class McfrBlockTrapDoor extends BlockTrapDoor {
  public McfrBlockTrapDoor(String name, Material materialIn, SoundType sound, float hardness, float resistance, String tool, int harvestLevel) {
    super(materialIn);
    name = name + "_trapdoor";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(resistance);
    setHardness(hardness);
    if (tool != null) setHarvestLevel(tool, harvestLevel);
    setSoundType(sound);
  }
}
