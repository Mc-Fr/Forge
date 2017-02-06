package net.mcfr.commons;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Cette classe représente les blocs de Mc-Fr.
 * 
 * @author Mc-Fr
 */
public class McfrBlock extends Block {
  /**
   * Instancie un nouveau bloc.
   * 
   * @param name le nom interne
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance
   * @param tool l'outils nécessaire pour le récupérer
   * @param harvestLevel le niveau de l'outils
   * @param tab l'onglet du menu Créatif
   */
  public McfrBlock(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel, CreativeTabs tab) {
    this(name, material, material.getMaterialMapColor(), sound, hardness, resistance, tool, harvestLevel, tab);
  }

  /**
   * Instancie un nouveau bloc.
   * 
   * @param name le nom interne
   * @param material le matériau
   * @param mapColor la couleur sur les cartes
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance
   * @param tool l'outils nécessaire pour le récupérer
   * @param harvestLevel le niveau de l'outils
   * @param tab l'onglet du menu Créatif
   */
  public McfrBlock(String name, Material material, MapColor mapColor, SoundType sound, float hardness, float resistance, String tool, int harvestLevel, CreativeTabs tab) {
    super(material, mapColor);
    setResistance(resistance);
    setHardness(hardness);
    if (tool != null)
      setHarvestLevel(tool, harvestLevel);
    setSoundType(sound);
    setCreativeTab(tab);
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }
}