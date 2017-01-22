package net.mcfr.decoration.lighting;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.commons.McfrBlockOrientable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWallLantern extends McfrBlockOrientable {
  private final EnumDyeColor color;
  
  public BlockWallLantern(EnumDyeColor color, boolean isPaper) {
    super(color.getName() + (isPaper ? "_paper" : "") + "_wall_lantern", isPaper ? Material.CLOTH : Material.GLASS, isPaper ? SoundType.CLOTH : SoundType.GLASS, 0.5f, 0, null, -1, null);
    setLightLevel(0.875f);
    this.color = color;
  }
  
  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    float f = 0.25f, h = 0.5f;
    
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
  
  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    int index = state.getValue(FACING).getHorizontalIndex();
    
    for (int i = 1; i < 4; i++) {
      EnumFacing facing = EnumFacing.getHorizontal((index + i) % 4);
      
      if (!worldIn.isAirBlock(pos.offset(facing.getOpposite()))) {
        worldIn.setBlockState(pos, state.withProperty(FACING, facing));
        return true;
      }
    }
    
    return false;
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
}
