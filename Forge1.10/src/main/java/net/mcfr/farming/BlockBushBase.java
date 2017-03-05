package net.mcfr.farming;

import java.util.Random;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockBushBase extends BlockBush {
  public BlockBushBase(String name) {
    super();
    name = name + "_base";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setSoundType(SoundType.PLANT);
    setHardness(1.5F);
    setCreativeTab(CreativeTabs.DECORATIONS);
    setTickRandomly(true);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
  }

  public abstract BlockBushTop getTopBlock();

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    Block block = worldIn.getBlockState(pos.down()).getBlock();
    return block == Blocks.DIRT || block == Blocks.GRASS || block == Blocks.FARMLAND;
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && worldIn.isAirBlock(pos.up()))
      worldIn.setBlockState(pos.up(), getTopBlock().getDefaultState());
    if (worldIn.isAirBlock(pos.down()))
      worldIn.setBlockToAir(pos);
  }
}
