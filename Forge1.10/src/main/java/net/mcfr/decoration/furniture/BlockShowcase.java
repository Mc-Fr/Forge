package net.mcfr.decoration.furniture;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.decoration.furniture.tile_entities.TileEntityShowcase;
import net.mcfr.utils.FacingUtils;
import net.mcfr.utils.NameUtils;
import net.mcfr.utils.TileEntityUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Vitrine d'armes/outils.
 *
 * @author Mc-Fr
 */
public class BlockShowcase extends BlockContainer {
  public static final PropertyDirection FACING = BlockHorizontal.FACING;

  public BlockShowcase() {
    super(Material.WOOD);
    String name = "showcase_block";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(5);
    setHardness(2);
    setSoundType(SoundType.WOOD);
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
  }

  @Override
  public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
    checkForSurroundingShowcases(worldIn, pos, state);

    for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL) {
      BlockPos pos1 = pos.offset(facing);
      IBlockState state1 = worldIn.getBlockState(pos1);

      if (state1.getBlock() == this) {
        checkForSurroundingShowcases(worldIn, pos1, state1);
      }
    }
  }

  /**
   * Vérifie l'état des blocs alentours.
   * 
   * @param world
   *          le monde
   * @param pos
   *          la position
   * @param state
   *          l'état courant
   * @return le nouvel état
   */
  private IBlockState checkForSurroundingShowcases(World world, BlockPos pos, IBlockState state) {
    if (world.isRemote)
      return state;
    else {
      IBlockState stateNorth = world.getBlockState(pos.north());
      IBlockState stateSouth = world.getBlockState(pos.south());
      IBlockState stateWest = world.getBlockState(pos.west());
      IBlockState stateEast = world.getBlockState(pos.east());
      EnumFacing facing = state.getValue(FACING);

      if (stateNorth.getBlock() != this && stateSouth.getBlock() != this) {
        boolean northFull = stateNorth.isFullBlock();
        boolean southFull = stateSouth.isFullBlock();

        if (stateWest.getBlock() == this || stateEast.getBlock() == this) {
          BlockPos pos1 = stateWest.getBlock() == this ? pos.west() : pos.east();
          IBlockState stateNorth1 = world.getBlockState(pos1.north());
          IBlockState stateSouth1 = world.getBlockState(pos1.south());
          facing = EnumFacing.SOUTH;
          EnumFacing facing1;

          if (stateWest.getBlock() == this) {
            facing1 = stateWest.getValue(FACING);
          } else {
            facing1 = stateEast.getValue(FACING);
          }

          if (facing1 == EnumFacing.NORTH) {
            facing = EnumFacing.NORTH;
          }

          if ((northFull || stateNorth1.isFullBlock()) && !southFull && !stateSouth1.isFullBlock()) {
            facing = EnumFacing.SOUTH;
          }

          if ((southFull || stateSouth1.isFullBlock()) && !northFull && !stateNorth1.isFullBlock()) {
            facing = EnumFacing.NORTH;
          }
        }
      } else {
        BlockPos pos1 = stateNorth.getBlock() == this ? pos.north() : pos.south();
        IBlockState stateWest1 = world.getBlockState(pos1.west());
        IBlockState stateEast1 = world.getBlockState(pos1.east());
        facing = EnumFacing.EAST;
        EnumFacing facing1;

        if (stateNorth.getBlock() == this) {
          facing1 = stateNorth.getValue(FACING);
        } else {
          facing1 = stateSouth.getValue(FACING);
        }

        if (facing1 == EnumFacing.WEST) {
          facing = EnumFacing.WEST;
        }

        if ((stateWest.isFullBlock() || stateWest1.isFullBlock()) && !stateEast.isFullBlock() && !stateEast1.isFullBlock()) {
          facing = EnumFacing.EAST;
        }

        if ((stateEast.isFullBlock() || stateEast1.isFullBlock()) && !stateWest.isFullBlock() && !stateWest1.isFullBlock()) {
          facing = EnumFacing.WEST;
        }
      }

      state = state.withProperty(FACING, facing);
      world.setBlockState(pos, state, 3);

      return state;
    }
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    int i = 0;
    BlockPos westPos = pos.west();
    BlockPos eastPos = pos.east();
    BlockPos northPos = pos.north();
    BlockPos southPos = pos.south();

    if (worldIn.getBlockState(westPos).getBlock() == this) {
      if (isDoubleShowcase(worldIn, westPos))
        return false;
      i++;
    }

    if (worldIn.getBlockState(eastPos).getBlock() == this) {
      if (isDoubleShowcase(worldIn, eastPos))
        return false;
      i++;
    }

    if (worldIn.getBlockState(northPos).getBlock() == this) {
      if (isDoubleShowcase(worldIn, northPos))
        return false;
      i++;
    }

    if (worldIn.getBlockState(southPos).getBlock() == this) {
      if (isDoubleShowcase(worldIn, southPos))
        return false;
      i++;
    }

    return i <= 1;
  }

  /**
   * Indique si ce bloc est une double vitrine.
   * 
   * @param world
   *          le monde
   * @param pos
   *          la position
   * @return vrai si cette vitrine est double ; faux sinon
   */
  private boolean isDoubleShowcase(World world, BlockPos pos) {
    if (world.getBlockState(pos).getBlock() == this) {
      for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL) {
        if (world.getBlockState(pos.offset(facing)).getBlock() == this)
          return true;
      }
    }

    return false;
  }

  // FIXME conserver la tile entity.
  @SuppressWarnings("deprecation")
  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    super.neighborChanged(state, worldIn, pos, blockIn);
    TileEntity tileEntity = worldIn.getTileEntity(pos);

    if (tileEntity instanceof TileEntityShowcase) {
      tileEntity.updateContainingBlockInfo();
    }
  }

  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    TileEntity tileEntity = worldIn.getTileEntity(pos);

    if (tileEntity instanceof IInventory) {
      InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileEntity);
      worldIn.updateComparatorOutputLevel(pos, this);
    }

    super.breakBlock(worldIn, pos, state);
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
  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
      EntityLivingBase placer) {
    return getDefaultState().withProperty(FACING, FacingUtils.getHorizontalFacing(placer));
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    EnumFacing enumfacing = FacingUtils.getHorizontalFacing(placer);
    state = state.withProperty(FACING, enumfacing);
    BlockPos northPos = pos.north();
    BlockPos southPos = pos.south();
    BlockPos westPos = pos.west();
    BlockPos eastPos = pos.east();
    boolean northIsShowcase = this == worldIn.getBlockState(northPos).getBlock();
    boolean southIsShowcase = this == worldIn.getBlockState(southPos).getBlock();
    boolean westIsShowcase = this == worldIn.getBlockState(westPos).getBlock();
    boolean eastIsShowcase = this == worldIn.getBlockState(eastPos).getBlock();

    if (!northIsShowcase && !southIsShowcase && !westIsShowcase && !eastIsShowcase) {
      worldIn.setBlockState(pos, state, 3);
    } else if (enumfacing.getAxis() != EnumFacing.Axis.X || !northIsShowcase && !southIsShowcase) {
      if (enumfacing.getAxis() == EnumFacing.Axis.Z && (westIsShowcase || eastIsShowcase)) {
        worldIn.setBlockState(westIsShowcase ? westPos : eastPos, state, 3);
        worldIn.setBlockState(pos, state, 3);
      }
    } else {
      worldIn.setBlockState(northIsShowcase ? northPos : southPos, state, 3);
      worldIn.setBlockState(pos, state, 3);
    }
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    if (!worldIn.isRemote) {
      TileEntity te = worldIn.getTileEntity(pos);

      if (te instanceof TileEntityShowcase) {
        TileEntityShowcase t = (TileEntityShowcase) te;

        if (t.canPlayerInteract()) {
          if (t.hasItem(0) && playerIn.getHeldItemMainhand() == null) {
            playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, t.getItem(0));
            t.setItem(null, 0);
            TileEntityUtils.sendTileEntityUpdate(te.getWorld(), t);

            return true;
          } else if (!t.hasItem(0) && playerIn.getHeldItemMainhand() != null && TileEntityShowcase.itemIsValid(playerIn.getHeldItemMainhand())) {
            t.setItem(playerIn.getHeldItemMainhand(), 0);
            playerIn.getHeldItemMainhand().stackSize--;
            TileEntityUtils.sendTileEntityUpdate(te.getWorld(), t);

            return true;
          }
        }
      }
    }

    return false;
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.SHOWCASE;
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getHorizontalIndex();
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(FACING, EnumFacing.Plane.HORIZONTAL.facings()[meta & 7]);
  }

  @Override
  public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityShowcase(getStateFromMeta(meta).getValue(FACING));
  }
}
