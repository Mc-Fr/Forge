package net.mcfr.decoration.container_blocks;

import net.mcfr.decoration.container_blocks.tile_entities.TileEntityPallet;
import net.mcfr.guis.CustomGuiScreens;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Palette.
 *
 * @author Mc-Fr
 */
public class BlockPallet extends McfrBlockContainer<TileEntityPallet> {
  private static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0, 0, 1, 0.1875f, 1);

  public BlockPallet() {
    super("pallet", Material.WOOD, SoundType.WOOD, 2, 5, "axe", TileEntityPallet.class);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
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
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.MODEL;
  }

  @Override
  public TileEntityPallet createNewTileEntity(World worldIn, int meta) {
    return new TileEntityPallet();
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.PALLET;
  }
}
