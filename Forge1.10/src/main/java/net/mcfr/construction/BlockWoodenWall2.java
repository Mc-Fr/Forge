package net.mcfr.construction;

import java.util.List;

import net.mcfr.environment.plants.EnumExoticWoodType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockWoodenWall2 extends McfrBlockWall {
  public static final PropertyEnum<EnumExoticWoodType> VARIANT = PropertyEnum.create("variant", EnumExoticWoodType.class);

  public BlockWoodenWall2() {
    super("exotic_wood", Material.WOOD, SoundType.WOOD, 2, 5, "axe", 0);
    setDefaultState(getDefaultState().withProperty(VARIANT, EnumExoticWoodType.APPLE_TREE));
  }

  @Override
  public String getVariantName(int meta) {
    return EnumExoticWoodType.byMetadata(meta).getName();
  }

  @Override
  protected PropertyEnum<? extends Enum<?>> getVariantProperty() {
    return VARIANT;
  }

  @Override
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < EnumExoticWoodType.values().length; i++) {
      list.add(new ItemStack(itemIn, 1, i));
    }
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(VARIANT, EnumExoticWoodType.byMetadata(meta));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }
}
