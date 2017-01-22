package net.mcfr.decoration.lighting;

import java.util.Random;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockCandle extends McfrBlock {
  public BlockCandle(String name, float lightLevel) {
    super(name + "_candle", Material.WOOD, SoundType.WOOD, 0, 0, null, 0, CreativeTabs.DECORATIONS);
    setLightLevel(lightLevel);
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
    return NULL_AABB;
  }

  @Override
  public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
    return true;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public abstract Item getItemDropped(IBlockState state, Random rand, int fortune);

  @Override
  @SideOnly(Side.CLIENT)
  public abstract ItemStack getItem(World worldIn, BlockPos pos, IBlockState state);
}
