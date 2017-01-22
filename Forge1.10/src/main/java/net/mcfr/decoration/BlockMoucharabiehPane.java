package net.mcfr.decoration;

import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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

public class BlockMoucharabiehPane extends BlockPane implements IBlockWithVariants {
  public static final PropertyEnum<EnumMoucharabiehType> VARIANT = PropertyEnum.create("variant", EnumMoucharabiehType.class);

  public BlockMoucharabiehPane() {
    super(Material.ROCK, true);
    String name = "moucharabieh_pane";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setSoundType(SoundType.STONE);
    setResistance(1);
    setHardness(0.3f);
    setHarvestLevel("pickaxe", 0);
    setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumMoucharabiehType.STONE));
  }

  @Override
  public String getVariantName(int meta) {
    return EnumMoucharabiehType.byMetadata(meta & 3).getName();
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
    return new BlockStateContainer(this, VARIANT, NORTH, EAST, WEST, SOUTH);
  }
}
