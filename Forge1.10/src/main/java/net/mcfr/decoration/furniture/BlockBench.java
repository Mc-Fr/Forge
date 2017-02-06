package net.mcfr.decoration.furniture;

import net.mcfr.decoration.furniture.tile_entities.TileEntityWoodenBench;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBench extends BlockWoodenChair {
  public static final PropertyBool NORTH = PropertyBool.create("north");
  public static final PropertyBool EAST = PropertyBool.create("east");
  public static final PropertyBool SOUTH = PropertyBool.create("south");
  public static final PropertyBool WEST = PropertyBool.create("west");

  public BlockBench(BlockPlanks.EnumType type) {
    super(type, "bench");
    // TEMP
    setCreativeTab(null);
    setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, NORTH, EAST, WEST, SOUTH, FACING);
  }

  @Override
  public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    return state.withProperty(NORTH, isBench(worldIn, pos.north())).withProperty(EAST, isBench(worldIn, pos.east())).withProperty(SOUTH, isBench(worldIn, pos.south())).withProperty(WEST, isBench(worldIn, pos.west()));
  }

  private boolean isBench(IBlockAccess world, BlockPos pos) {
    return world.getBlockState(pos).getBlock() instanceof BlockBench;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityWoodenBench(getStateFromMeta(meta).getValue(FACING));
  }
}
