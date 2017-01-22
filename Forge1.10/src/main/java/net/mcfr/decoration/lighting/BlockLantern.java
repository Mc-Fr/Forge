package net.mcfr.decoration.lighting;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.commons.McfrBlockOrientable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLantern extends McfrBlockOrientable {
  public static final PropertyInteger POSITION = PropertyInteger.create("position", 0, 2);

  private final EnumDyeColor color;

  public BlockLantern(EnumDyeColor color, boolean isPaper) {
    super(color.getName() + (isPaper ? "_paper" : "") + "_lantern", isPaper ? Material.CLOTH : Material.GLASS, isPaper ? SoundType.CLOTH : SoundType.GLASS, 0.5f, 0, null, -1, null);
    setDefaultState(this.blockState.getBaseState().withProperty(POSITION, 0));
    setLightLevel(0.875f);
    this.color = color;
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (state.getValue(POSITION) == 0 || state.getValue(POSITION) == 2)
      worldIn.setBlockState(pos, state.withProperty(FACING, state.getValue(FACING).rotateY()));
    else {
      int index = state.getValue(FACING).getHorizontalIndex();

      for (int i = 1; i < 4; i++) {
        EnumFacing facing = EnumFacing.getHorizontal((index + i) % 4);
        BlockPos pos1 = pos.offset(facing.getOpposite());

        if (!worldIn.getBlockState(pos1).isSideSolid(worldIn, pos1, facing)) {
          worldIn.setBlockState(pos, state.withProperty(FACING, facing));
          return true;
        }
      }
    }
    return true;
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(POSITION, facing == EnumFacing.UP ? 0 : facing == EnumFacing.DOWN ? 2 : 3).withProperty(FACING, facing);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    float f = 0.25f, h = 0.5f;

    if (state.getValue(POSITION) == 0)
      return new AxisAlignedBB(h - f, 0, h - f, h + f, 0.6f, h + f);
    else if (state.getValue(POSITION) == 2)
      return new AxisAlignedBB(h - f, 0.4f, h - f, h + f, 1, h + f);
    else {
      switch (state.getValue(FACING)) {
        case NORTH:
          return new AxisAlignedBB(h - f, 0.2f, h, h + f, 0.8f, 1);
        case EAST:
          return new AxisAlignedBB(0, 0.2f, h - f, h, 0.8f, h + f);
        case SOUTH:
          return new AxisAlignedBB(h - f, 0.2f, 0, h + f, 0.8f, h);
        case WEST:
          return new AxisAlignedBB(h, 0.2f, h - f, 1, 0.8f, h + f);
        default:
          return NULL_AABB;
      }
    }
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
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, POSITION, FACING);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(POSITION, (meta & 0xf0) >> 2).withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return (state.getValue(POSITION) << 2) | state.getValue(FACING).getHorizontalIndex();
  }

  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(getRegistryName().getResourcePath().contains("paper") ? McfrItems.PAPER_LANTERN : McfrItems.LANTERN);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return getRegistryName().getResourcePath().contains("paper") ? McfrItems.PAPER_LANTERN : McfrItems.LANTERN;
  }

  @Override
  public int damageDropped(IBlockState state) {
    return this.color.getMetadata();
  }

  @Override
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.CUTOUT_MIPPED;
  }
}
