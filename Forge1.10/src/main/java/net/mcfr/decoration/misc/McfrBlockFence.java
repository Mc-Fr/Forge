package net.mcfr.decoration.misc;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * Barière.
 *
 * @author Mc-Fr
 */
public class McfrBlockFence extends BlockFence {
  /**
   * Crée une barrière.
   * 
   * @param materialName le nom du matériau
   * @param hardness la dureté
   * @param resistance la résistance aux explosions
   * @param material le matériau
   * @param sound le type de son
   * @param tool l'outil nécessaire
   * @param harvestLevel le niveau de récolte
   */
  public McfrBlockFence(String materialName, float hardness, float resistance, Material material, SoundType sound, String tool, int harvestLevel) {
    super(material, MapColor.BROWN);
    String name = materialName + "_fence";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(resistance);
    setHardness(hardness);
    setSoundType(sound);
    if (tool != null)
      setHarvestLevel(tool, harvestLevel);
  }
}
