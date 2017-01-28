package net.mcfr.decoration.misc;

import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMoucharabieh extends McfrBlock implements IBlockWithVariants {
  public static final PropertyEnum<EnumMoucharabiehType> VARIANT = PropertyEnum.create("variant", EnumMoucharabiehType.class);

  public BlockMoucharabieh() {
    super("moucharabieh", Material.ROCK, SoundType.STONE, 0.3f, 1, "pickaxe", 0, CreativeTabs.BUILDING_BLOCKS);
    setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumMoucharabiehType.STONE));
  }

  @Override
  public String getVariantName(int meta) {
    return EnumMoucharabiehType.byMetadata(meta & 3).getName();
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (EnumMoucharabiehType type : EnumMoucharabiehType.values()) {
      list.add(new ItemStack(itemIn, 1, type.getMetadata()));
    }
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(this, 1, getMetaFromState(state));
  }

  @Override
  public int damageDropped(IBlockState state) {
    return getMetaFromState(state);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(VARIANT, EnumMoucharabiehType.byMetadata(meta & 3));
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, VARIANT);
  }

  @Override
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.CUTOUT_MIPPED;
  }
}
