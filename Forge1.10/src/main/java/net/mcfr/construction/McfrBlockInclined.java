package net.mcfr.construction;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

/**
 * Classe représentant les blocs "inclinés" comme les escaliers et pentes.
 *
 * @author Mc-Fr
 */
abstract class McfrBlockInclined extends BlockStairs {
  /** Le bloc de base. */
  private final Block block;
  /** Le metadata du bloc de base. */
  private final int metadata;

  /**
   * Crée un nouveau bloc incliné.
   * 
   * @param block le bloc de base
   * @param metadata le metadata du bloc
   * @param name le nom (sans le suffixe)
   * @param suffix le suffixe (ex : '_stairs')
   * @param tool l'outil nécessaire
   * @param harvestLevel le niveau de récolte
   */
  @SuppressWarnings("deprecation")
  public McfrBlockInclined(Block block, int metadata, String name, String suffix, String tool, int harvestLevel) {
    super(block.getStateFromMeta(metadata));

    this.block = block;
    this.metadata = metadata;
    String registryName = name + "_" + suffix;
    if (tool != null)
      setHarvestLevel(tool, harvestLevel);
    setRegistryName(registryName);
    setUnlocalizedName(NameUtils.getUnlocalizedName(registryName));
  }

  /**
   * @return le bloc de base
   */
  public final Block getBlock() {
    return this.block;
  }

  /**
   * @return le metadata du bloc de base
   */
  public final int getBlockMetadata() {
    return this.metadata;
  }
}