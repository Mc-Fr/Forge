package net.mcfr.decoration.containerBlocks;

import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityLittleChest;
import net.mcfr.guis.CustomGuiScreens;
import net.mcfr.utils.FacingUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLittleChest extends McfrBlockContainer<TileEntityLittleChest> {
  public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

  public BlockLittleChest() {
    super("little_chest", Material.WOOD, SoundType.WOOD, 5, 2, "axe", TileEntityLittleChest.class);
  }

  @Override
  public TileEntityLittleChest createNewTileEntity(World worldIn, int meta) {
    return new TileEntityLittleChest();
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.LITTLE_CHEST;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    if (!(source.getBlockState(pos).getBlock() instanceof BlockLittleChest))
      return NULL_AABB;

    switch (source.getBlockState(pos).getValue(FACING)) {
      case NORTH:
        return new AxisAlignedBB(0.1, 0, 0.3, 0.9, 0.5, 1);
      case EAST:
        return new AxisAlignedBB(0, 0, 0.1, 0.7, 0.5, 0.9);
      case SOUTH:
        return new AxisAlignedBB(0.1, 0, 0, 0.9, 0.5, 0.7);
      case WEST:
        return new AxisAlignedBB(0.3, 0, 0.1, 1, 0.5, 0.9);
      default:
        return state.getBoundingBox(source, pos);
    }
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getHorizontalIndex();
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(FACING, EnumFacing.Plane.HORIZONTAL.facings()[meta & 3]);
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
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    worldIn.setBlockState(pos, state.withProperty(FACING, FacingUtils.getHorizontalFacing(placer)));
  }
}
