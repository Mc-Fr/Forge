package net.mcfr.environment.plants;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrBlocks;
import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockExoticLeaves extends BlockLeaves implements IBlockWithVariants {
  public static final PropertyEnum<EnumExoticWoodType> VARIANT = PropertyEnum.create("variant", EnumExoticWoodType.class);

  public BlockExoticLeaves() {
    super();
    setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumExoticWoodType.APPLE_TREE).withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
    String name = "exotic_leaves";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(VARIANT).getName();
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (EnumExoticWoodType type : EnumExoticWoodType.values()) {
      list.add(new ItemStack(itemIn, 1, type.getMetadata()));
    }
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(this, 1, getMetaFromState(state) & 3);
  }

  @Override
  protected ItemStack createStackedBlock(IBlockState state) {
    return new ItemStack(Item.getItemFromBlock(this), 1, state.getValue(VARIANT).getMetadata());
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(VARIANT, getExoticWoodType(meta)).withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    int i = state.getValue(VARIANT).getMetadata();

    if (!state.getValue(DECAYABLE)) {
      i |= 4;
    }
    if (state.getValue(CHECK_DECAY)) {
      i |= 8;
    }

    return i;
  }

  public EnumExoticWoodType getExoticWoodType(int meta) {
    return EnumExoticWoodType.byMetadata(meta);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, new IProperty[]{VARIANT, CHECK_DECAY, DECAYABLE});
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return Item.getItemFromBlock(McfrBlocks.EXOTIC_SAPLING);
  }

  @Override
  public int damageDropped(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  @Override
  public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
    if (worldIn.isRemote && player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == Items.SHEARS) {
      super.harvestBlock(worldIn, player, pos, state, te, stack);
    }
  }

  @Override
  public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
    return Arrays.asList(new ItemStack(this, 1, damageDropped(world.getBlockState(pos))));
  }

  @Override
  public BlockPlanks.EnumType getWoodType(int meta) {
    throw new UnsupportedOperationException();
  }
}
