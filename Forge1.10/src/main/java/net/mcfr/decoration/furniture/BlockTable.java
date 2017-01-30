package net.mcfr.decoration.furniture;

import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockTable extends McfrBlock implements IBlockWithVariants {
  public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class);

  private AxisAlignedBB aabb;

  public BlockTable(String type) {
    this(type, FULL_BLOCK_AABB);
  }

  public BlockTable(String type, AxisAlignedBB aabb) {
    super(type + "_table", Material.WOOD, SoundType.WOOD, 1.5f, 5, "axe", 0, null);
    this.aabb = aabb;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return this.aabb;
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(VARIANT).getName();
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < BlockPlanks.EnumType.values().length; i++) {
      list.add(new ItemStack(itemIn, 1, i));
    }
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, VARIANT);
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
