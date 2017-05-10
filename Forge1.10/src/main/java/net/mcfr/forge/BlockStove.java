package net.mcfr.forge;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrBlocks;
import net.mcfr.McfrMain;
import net.mcfr.forge.tile_entities.TileEntityStove;
import net.mcfr.guis.CustomGuiScreens;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Haut-fourneau.
 *
 * @author Mc-Fr
 */
public class BlockStove extends BlockContainer {
  public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

  private final boolean isBurning;

  /**
   * Crée un haut-fourneau.
   * 
   * @param isBurning indique s'il est allumé
   */
  public BlockStove(boolean isBurning) {
    super(Material.ROCK);
    String name = (isBurning ? "lit_" : "") + "stove";
    setRegistryName(name);
    this.isBurning = isBurning;
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setSoundType(SoundType.STONE);
    setHardness(3.5f);
    setHarvestLevel("pickaxe", 0);
    if (isBurning)
      setLightLevel(0.875f);
    else
      setCreativeTab(CreativeTabs.DECORATIONS);
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
  }

  /**
   * @return true si le haut-fourneau est allumé
   */
  public boolean isLit() {
    return this.isBurning;
  }

  @Override
  public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
    setDefaultFacing(worldIn, pos, state);
  }

  /**
   * Calcule l'orientation par défaut.
   * 
   * @param world le monde
   * @param pos la position
   * @param state l'état
   */
  private void setDefaultFacing(World world, BlockPos pos, IBlockState state) {
    if (!world.isRemote) {
      IBlockState stateNorth = world.getBlockState(pos.north());
      IBlockState stateSouth = world.getBlockState(pos.south());
      IBlockState stateWest = world.getBlockState(pos.west());
      IBlockState stateEast = world.getBlockState(pos.east());
      EnumFacing facing = state.getValue(FACING);

      if (facing == EnumFacing.NORTH && stateNorth.isFullBlock() && !stateSouth.isFullBlock()) {
        facing = EnumFacing.SOUTH;
      }
      else if (facing == EnumFacing.SOUTH && stateSouth.isFullBlock() && !stateNorth.isFullBlock()) {
        facing = EnumFacing.NORTH;
      }
      else if (facing == EnumFacing.WEST && stateWest.isFullBlock() && !stateEast.isFullBlock()) {
        facing = EnumFacing.EAST;
      }
      else if (facing == EnumFacing.EAST && stateEast.isFullBlock() && !stateWest.isFullBlock()) {
        facing = EnumFacing.WEST;
      }

      world.setBlockState(pos, state.withProperty(FACING, facing), 2);
    }
  }

  @Override
  @SideOnly(Side.CLIENT)
  @SuppressWarnings("incomplete-switch")
  public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
    if (this.isBurning) {
      EnumFacing enumfacing = state.getValue(FACING);
      double d0 = pos.getX() + 0.5;
      double d1 = pos.getY() + rand.nextDouble() * 6.0 / 16.0;
      double d2 = pos.getZ() + 0.5;
      double d3 = 0.52;
      double d4 = rand.nextDouble() * 0.6D - 0.3D;

      switch (enumfacing) {
        case WEST:
          worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
          worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
          break;
        case EAST:
          worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
          worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
          break;
        case NORTH:
          worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
          worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
          break;
        case SOUTH:
          worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
          worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
          break;
      }
    }
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    if (!worldIn.isRemote) {
      TileEntity tileentity = worldIn.getTileEntity(pos);

      if (tileentity instanceof TileEntityStove) {
        playerIn.openGui(McfrMain.instance, CustomGuiScreens.STOVE.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
      }
    }

    return true;
  }

  /**
   * Modifie l'état du bloc.
   * 
   * @param active indique si le bloc est actif
   * @param world le monde
   * @param pos la position
   */
  public static void setState(boolean active, World world, BlockPos pos) {
    IBlockState iblockstate = world.getBlockState(pos);
    TileEntity tileentity = world.getTileEntity(pos);

    if (active) {
      world.setBlockState(pos, McfrBlocks.LIT_STOVE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
      world.setBlockState(pos, McfrBlocks.LIT_STOVE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
    }
    else {
      world.setBlockState(pos, McfrBlocks.STOVE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
      world.setBlockState(pos, McfrBlocks.STOVE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
    }

    if (tileentity != null) {
      tileentity.validate();
      world.setTileEntity(pos, tileentity);
    }
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
      EntityLivingBase placer) {
    return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

    if (stack.hasDisplayName()) {
      TileEntity te = worldIn.getTileEntity(pos);

      if (te instanceof TileEntityFurnace) {
        ((TileEntityFurnace) te).setCustomInventoryName(stack.getDisplayName());
      }
    }
  }

  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    TileEntity tileentity = worldIn.getTileEntity(pos);

    if (tileentity instanceof TileEntityStove) {
      InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityStove) tileentity);
      worldIn.updateComparatorOutputLevel(pos, this);
    }

    super.breakBlock(worldIn, pos, state);
  }

  @Override
  public boolean hasComparatorInputOverride(IBlockState state) {
    return true;
  }

  @Override
  public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
    return Container.calcRedstone(worldIn.getTileEntity(pos));
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.MODEL;
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    EnumFacing enumfacing = EnumFacing.getFront(meta);

    if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
      enumfacing = EnumFacing.NORTH;
    }

    return getDefaultState().withProperty(FACING, enumfacing);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getIndex();
  }

  @Override
  public BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, new IProperty[]{FACING});
  }

  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(McfrBlocks.STOVE);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return Item.getItemFromBlock(McfrBlocks.STOVE);
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return getItem(world, pos, state);
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityStove();
  }
}
