package net.mcfr.environment.plants;

import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockExoticWood extends BlockLog implements IBlockWithVariants {
  public static final PropertyEnum<EnumExoticWoodType> VARIANT = PropertyEnum.create("variant", EnumExoticWoodType.class);

  public BlockExoticWood() {
    super();
    String name = "exotic_wood";
    setRegistryName(name);
    setHarvestLevel("axe", 0);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumExoticWoodType.APPLE_TREE).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
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
  public IBlockState getStateFromMeta(int meta) {
    IBlockState state = getDefaultState().withProperty(VARIANT, EnumExoticWoodType.byMetadata(meta));

    switch (meta & 12) {
      case 0:
        state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
        break;
      case 4:
        state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
        break;
      case 8:
        state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
        break;
      default:
        state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
    }

    return state;
  }

  @Override
  @SuppressWarnings("incomplete-switch")
  public int getMetaFromState(IBlockState state) {
    int i = state.getValue(VARIANT).getMetadata();

    switch (state.getValue(LOG_AXIS)) {
      case X:
        i |= 4;
        break;
      case Z:
        i |= 8;
        break;
      case NONE:
        i |= 12;
    }

    return i;
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, VARIANT, LOG_AXIS);
  }

  @Override
  protected ItemStack createStackedBlock(IBlockState state) {
    return new ItemStack(Item.getItemFromBlock(this), 1, state.getValue(VARIANT).getMetadata());
  }

  @Override
  public int damageDropped(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }
}
