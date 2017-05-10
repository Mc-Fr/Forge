package net.mcfr.decoration.misc.blood_stains;

import net.mcfr.McfrItems;
import net.mcfr.commons.IEnumType;
import net.mcfr.commons.McfrBlockOrientable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Bloc de sang pouvant être posé sur le sol ou les murs.
 *
 * @author Mc-Fr
 */
public class BlockBloodWallsAndFloor extends McfrBlockOrientable {
  public static final PropertyBool ON_WALL = PropertyBool.create("on_wall");
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  private static final AxisAlignedBB FLOOR_AABB = new AxisAlignedBB(0, 0, 0, 1, 0, 1);
  private static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0, 0, 1, 1, 1, 1);
  private static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0, 0, 0, 1, 1, 0);
  private static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(1, 0, 0, 1, 1, 1);
  private static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0, 0, 0, 0, 1, 1);

  /**
   * Crée un bloc de sang.
   * 
   * @param name le nom
   */
  public BlockBloodWallsAndFloor(String name) {
    super("blood_stains_" + name, Material.CIRCUITS, SoundType.STONE, 0, 0, null, -1, null);
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(VARIANT, EnumType.SPLAT_1));
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullBlock(IBlockState state) {
    return false;
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World world, BlockPos pos) {
    return NULL_AABB;
  }

  @SuppressWarnings("incomplete-switch")
  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    if (state.getValue(ON_WALL)) {
      switch (state.getValue(FACING)) {
        case NORTH:
          return NORTH_AABB;
        case SOUTH:
          return SOUTH_AABB;
        case WEST:
          return WEST_AABB;
        case EAST:
          return EAST_AABB;
      }
    }

    return FLOOR_AABB;
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(McfrItems.BLOOD_STAIN, 1,
        ItemBlood.EnumType.fromBlockNameAndVariant(getRegistryName().toString(), state.getValue(VARIANT)).getMetadata());
  }

  @Override
  public int damageDropped(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  @Override
  public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
    return side != EnumFacing.DOWN && world.isSideSolid(pos.offset(side.getOpposite()), side);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING, ON_WALL, VARIANT);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return super.getStateFromMeta(meta).withProperty(ON_WALL, (meta & 4) != 0).withProperty(VARIANT, EnumType.byMetadata((meta >> 3) & 1));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return super.getMetaFromState(state) | ((state.getValue(ON_WALL) ? 0 : 1) << 2) | (state.getValue(VARIANT).getMetadata() << 3);
  }

  public static enum EnumType implements IEnumType<EnumType> {
    SPLAT_0("splat_0"),
    SPLAT_1("splat_1");

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
