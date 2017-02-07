package net.mcfr.decoration.furniture;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.utils.FacingUtils;
import net.mcfr.utils.NameUtils;
import net.mcfr.utils.TileEntityUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockDoublePlant.EnumBlockHalf;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
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

/**
 * Support d'armure.
 *
 * @author Mc-Fr
 */
public class BlockArmorStand extends BlockContainer {
  public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
  public static final PropertyEnum<EnumBlockHalf> HALF = PropertyEnum.create("half", EnumBlockHalf.class);

  public BlockArmorStand() {
    super(Material.WOOD);
    String name = "armor_stand_block";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(5);
    setHardness(2);
    setSoundType(SoundType.WOOD);
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, BlockDoublePlant.EnumBlockHalf.LOWER));
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    if (source.getBlockState(pos).getBlock() != this)
      return NULL_AABB;

    if (source.getBlockState(pos).getValue(HALF) == EnumBlockHalf.LOWER) {
      return new AxisAlignedBB(0, 0, 0, 1, 2, 1);
    }
    else {
      return new AxisAlignedBB(0, -1, 0, 1, 1, 1);
    }
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
    // TEMP
    return EnumBlockRenderType.MODEL; // EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, FacingUtils.getHorizontalFacing(placer)).withProperty(HALF, EnumBlockHalf.LOWER);
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    worldIn.setBlockState(pos.up(), getDefaultState().withProperty(FACING, FacingUtils.getHorizontalFacing(placer)).withProperty(HALF, EnumBlockHalf.UPPER));
  }

  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    TileEntity tileEntity = worldIn.getTileEntity(pos);

    if (tileEntity instanceof IInventory) {
      InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
    }

    if (state.getValue(HALF) == EnumBlockHalf.UPPER) {
      worldIn.setBlockToAir(pos.down());
    }
    else {
      worldIn.setBlockToAir(pos.up());
    }

    super.breakBlock(worldIn, pos, state);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (!worldIn.isRemote) {
      if (state.getValue(HALF) == EnumBlockHalf.UPPER)
        pos = pos.up();
      TileEntity te = worldIn.getTileEntity(pos);

      // TODO

      TileEntityUtils.sendTileEntityUpdate(te.getWorld(), te);
    }

    return true;
  }

  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(McfrItems.ARMOR_STAND);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.ARMOR_STAND;
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return getItem(world, pos, state);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING, HALF);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getHorizontalIndex() | (state.getValue(HALF) == EnumBlockHalf.UPPER ? 8 : 0);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(FACING, EnumFacing.Plane.HORIZONTAL.facings()[meta & 7]).withProperty(HALF, EnumBlockHalf.values()[(meta & 8) >> 3]);
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return null; // TODO
  }
}
