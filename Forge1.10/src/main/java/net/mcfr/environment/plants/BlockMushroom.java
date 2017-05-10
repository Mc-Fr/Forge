package net.mcfr.environment.plants;

import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.IEnumType;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Bloc de champignon géant.
 *
 * @author Mc-Fr
 */
public class BlockMushroom extends McfrBlock implements IBlockWithVariants {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  public BlockMushroom() {
    super("mushroom_block", Material.SPONGE, SoundType.WOOD, 2, 3, null, -1, CreativeTabs.BUILDING_BLOCKS);
    setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.RED_CAP));
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(VARIANT).getName();
  }

  @Override
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < EnumType.values().length; i++) {
      list.add(new ItemStack(itemIn, 1, i));
    }
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(this, 1, getMetaFromState(state));
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, VARIANT);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  /**
   * Types de blocs :
   * <ul>
   * <li>chapeau rouge</li>
   * <li>chapeau rose</li>
   * <li>chapeau orange</li>
   * <li>chapeau jaune</li>
   * <li>chapeau bleu</li>
   * <li>chapeau bleu clair</li>
   * <li>chapeau vert</li>
   * <li>chapeau lime</li>
   * <li>chapeau violet</li>
   * <li>chapeau magenta</li>
   * <li>chapeau cyan</li>
   * <li>pied blanc</li>
   * <li>pied bleu clair</li>
   * <li>lamelles cyan</li>
   * <li>lamelles blanches</li>
   * <li>lamelles bleu clair</li>
   * </ul>
   *
   * @author Mc-Fr
   */
  public static enum EnumType implements IEnumType<EnumType> {
    RED_CAP("red_cap"),
    PINK_CAP("pink_cap"),
    ORANGE_CAP("orange_cap"),
    YELLOW_CAP("yellow_cap"),
    BLUE_CAP("blue_cap"),
    LIGHT_BLUE_CAP("light_blue_cap"),
    GREEN_CAP("green_cap"),
    LIME_CAP("lime_cap"),
    PURPLE_CAP("purple_cap"),
    MAGENTA_CAP("magenta_cap"),
    CYAN_CAP("cyan_cap"),
    WHITE_STEM("white_stem"),
    LIGHT_BLUE_STEM("light_blue_stem"),
    CYAN_GILLS("cyan_gills"),
    WHITE_GILLS("white_gills"),
    LIGHT_BLUE_GILLS("light_blue_gills");

    private final String name;

    private EnumType(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return this.name;
    }

    @Override
    public int getMetadata() {
      return ordinal();
    }

    @Override
    public String toString() {
      return getName();
    }

    public static EnumType byMetadata(int meta) {
      return values()[meta % values().length];
    }
  }
}
