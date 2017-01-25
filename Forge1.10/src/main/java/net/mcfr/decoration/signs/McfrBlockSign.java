package net.mcfr.decoration.signs;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.commons.McfrBlock;
import net.mcfr.decoration.signs.tileEntities.TileEntityMcfrSign;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class McfrBlockSign extends McfrBlock implements ITileEntityProvider {
  public McfrBlockSign(String name, Material material, SoundType sound, float hardness, String tool) {
    super(name, material, sound, hardness, 0, tool, 0, null);
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
    return NULL_AABB;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    double f = 0.25, h = 0.5;
    return new AxisAlignedBB(h - f, 0, h - f, h + f, 1, h + f);
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
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.INVISIBLE;
  }

  @Override
  public abstract Item getItemDropped(IBlockState state, Random rand, int fortune);

  @Override
  @SideOnly(Side.CLIENT)
  public abstract ItemStack getItem(World worldIn, BlockPos pos, IBlockState state);

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return getItem(world, pos, state);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote) {
      return true;
    }
    else {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      return tileentity instanceof TileEntityMcfrSign ? ((TileEntityMcfrSign) tileentity).executeCommand(playerIn) : false;
    }
  }
}
