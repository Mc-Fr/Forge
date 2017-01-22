package net.mcfr.construction;

import java.util.List;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockWoodenWall extends McfrBlockWall {
  public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class);

  public BlockWoodenWall() {
    super("wooden", Material.WOOD, SoundType.WOOD, 2, 5, "axe", 0);
    setDefaultState(getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.OAK));
  }

  @Override
  public String getVariantName(int meta) {
    return BlockPlanks.EnumType.byMetadata(meta).getName();
  }

  @Override
  protected PropertyEnum<? extends Enum<?>> getVariantProperty() {
    return VARIANT;
  }

  @Override
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < BlockPlanks.EnumType.values().length; i++) {
      list.add(new ItemStack(itemIn, 1, i));
    }
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata(meta));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }
}
