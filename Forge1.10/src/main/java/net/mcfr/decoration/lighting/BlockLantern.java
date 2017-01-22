package net.mcfr.decoration.lighting;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
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

public class BlockLantern extends McfrBlock {
  public static final PropertyBool ON_GROUND = PropertyBool.create("on_ground");
  public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 7);
  
  private final EnumDyeColor color;
  
  public BlockLantern(EnumDyeColor color, boolean isPaper) {
    super(color.getName() + (isPaper ? "_paper" : "") + "_lantern", isPaper ? Material.CLOTH : Material.GLASS, isPaper ? SoundType.CLOTH : SoundType.GLASS, 0.5f, 0, null, -1, null);
    setDefaultState(this.blockState.getBaseState().withProperty(ON_GROUND, true).withProperty(ROTATION, 0));
    setLightLevel(0.875f);
    this.color = color;
  }
  
  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    worldIn.setBlockState(pos, state.withProperty(ROTATION, (state.getValue(ROTATION) + 1) % 8));
    return true;
  }
  
  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(ON_GROUND, facing == EnumFacing.UP);
  }
  
  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    float f = 0.25f, h = 0.5f;
    
    if (state.getValue(ON_GROUND)) return new AxisAlignedBB(h - f, 0, h - f, h + f, 0.6f, h + f);
    else return new AxisAlignedBB(h - f, 0.4f, h - f, h + f, 1, h + f);
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
    return new BlockStateContainer(this, ON_GROUND, ROTATION);
  }
  
  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(ON_GROUND, (meta & 8) >> 3 == 0).withProperty(ROTATION, meta & 7);
  }
  
  @Override
  public int getMetaFromState(IBlockState state) {
    return (state.getValue(ON_GROUND) ? 0 : 8) | (state.getValue(ROTATION));
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
