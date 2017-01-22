package net.mcfr.construction;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

/**
 * Classe représentant les blocs "inclinés" comme les escaliers et pentes.
 *
 * @author Mc-Fr
 */
class McfrBlockInclined extends BlockStairs {
  private final Block block;
  private final int metadata;
  
  @SuppressWarnings("deprecation")
  public McfrBlockInclined(Block block, int metadata, String materialName, String suffix, String tool, int harvestLevel) {
    super(block.getStateFromMeta(metadata));
    
    this.block = block;
    this.metadata = metadata;
    String name = materialName + "_" + suffix;
    if (tool != null) setHarvestLevel(tool, harvestLevel);
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }
  
  public final Block getBlock() {
    return this.block;
  }
  
  public final int getBlockMetadata() {
    return this.metadata;
  }
}