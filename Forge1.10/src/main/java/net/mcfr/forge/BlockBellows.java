package net.mcfr.forge;

import net.mcfr.forge.tile_entities.TileEntityBellows;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBellows extends BlockContainer {
  public BlockBellows() {
    super(Material.WOOD);
    String name = "bellows";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setHardness(2);
    setSoundType(SoundType.WOOD);
    setCreativeTab(CreativeTabs.DECORATIONS);
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
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    if (!worldIn.isRemote) {
      TileEntity te = worldIn.getTileEntity(pos);

      if (te instanceof TileEntityBellows) {
        ((TileEntityBellows) te).setPowered(worldIn.isBlockPowered(pos));
      }
    }
  }

  @Override
  public boolean shouldCheckWeakPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
    return true;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    TileEntity te = source.getTileEntity(pos);

    if (te instanceof TileEntityBellows) {
      float y = 1 - ((TileEntityBellows) te).getStep() / 16f;
      return new AxisAlignedBB(0, 0, 0, 1, y, 1);
    }

    return null;
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityBellows();
  }
}
