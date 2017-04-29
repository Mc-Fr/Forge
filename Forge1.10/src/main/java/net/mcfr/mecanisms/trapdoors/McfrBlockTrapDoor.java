package net.mcfr.mecanisms.trapdoors;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Classe de base des trappes.
 *
 * @author Mc-Fr
 */
public class McfrBlockTrapDoor extends BlockTrapDoor {
  /**
   * Crée une trappe.
   * 
   * @param name le nom
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance aux explosions
   * @param tool l'outil nécessaire
   * @param harvestLevel le niveau de récolte
   */
  public McfrBlockTrapDoor(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel) {
    super(material);
    name = name + "_trapdoor";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(resistance);
    setHardness(hardness);
    if (tool != null)
      setHarvestLevel(tool, harvestLevel);
    setSoundType(sound);
  }
}
