package net.mcfr.craftsmanship;

import net.mcfr.McfrBlocks;
import net.mcfr.craftsmanship.tile_entities.TileEntityMortar;
import net.mcfr.guis.CustomGuiScreens;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMortar extends BlockRack<TileEntityMortar> {
  public static final PropertyBool BOTTOM = PropertyBool.create("bottom");
  private static final AxisAlignedBB HALF_AABB = new AxisAlignedBB(0.25, 0, 0.25, 0.75, 0.4, 0.75);

  public BlockMortar() {
    super("mortar", TileEntityMortar.class);
    setDefaultState(this.blockState.getBaseState().withProperty(BOTTOM, false));
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return HALF_AABB;
  }

  @Override
  public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
    IBlockState blockState = world.getBlockState(pos.down());
    Block block = blockState.getBlock();
    boolean b = block instanceof BlockSlab && !((BlockSlab) block).isDouble() && blockState.getValue(BlockSlab.HALF) == EnumBlockHalf.BOTTOM
        || block == McfrBlocks.END_TABLE;

    return state.withProperty(BOTTOM, b);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING, BOTTOM);
  }

  @Override
  public TileEntityMortar createNewTileEntity(World world, int meta) {
    return new TileEntityMortar();
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.MORTAR;
  }
}
