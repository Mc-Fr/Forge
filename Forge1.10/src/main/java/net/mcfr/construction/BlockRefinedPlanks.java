package net.mcfr.construction;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockPlanks;
import net.minecraft.creativetab.CreativeTabs;

public class BlockRefinedPlanks extends BlockPlanks implements IBlockWithVariants {
  public BlockRefinedPlanks() {
    super();
    String name = "refined_planks";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(5);
    setHardness(2);
    setHarvestLevel("axe", 0);
    setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
  }
  
  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(VARIANT).getName();
  }
}
