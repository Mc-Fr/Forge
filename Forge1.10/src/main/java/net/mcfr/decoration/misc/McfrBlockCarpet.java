package net.mcfr.decoration.misc;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.decoration.misc.tile_entities.TileEntityCarpet;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class McfrBlockCarpet extends BlockCarpet implements IBlockWithVariants, ITileEntityProvider {
  public McfrBlockCarpet() {
    String name = "carpet";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setSoundType(SoundType.CLOTH);
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(COLOR).getName();
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityCarpet(meta);
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    worldIn.setTileEntity(pos, createNewTileEntity(worldIn, stack.getMetadata()));
  }

  /**
   * Empêche les tapis d'être détruits si le bloc d'en-dessous est supprimé.
   */
  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {}

  /**
   * Permet de passer à travers le tapis.
   */
  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
    return NULL_AABB;
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.INVISIBLE;
  }
}
