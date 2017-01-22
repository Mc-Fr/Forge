package net.mcfr.mecanisms;

import javax.annotation.Nullable;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockWall;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Cette classe est une copie de la classe BlockFence car son constructeur n'est pas adapté.
 *
 * @author Mc-Fr
 */
public class McfrBlockFenceGate extends BlockHorizontal {
  public static final PropertyBool OPEN = PropertyBool.create("open");
  public static final PropertyBool POWERED = PropertyBool.create("powered");
  public static final PropertyBool IN_WALL = PropertyBool.create("in_wall");

  public McfrBlockFenceGate(String materialName) {
    super(Material.WOOD);
    String name = materialName + "_fence_gate";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setResistance(5);
    setHardness(2);
    setHarvestLevel("axe", 0);
    setSoundType(SoundType.WOOD);
    setCreativeTab(CreativeTabs.REDSTONE);
    setDefaultState(this.blockState.getBaseState().withProperty(OPEN, false).withProperty(POWERED, false).withProperty(IN_WALL, false));
  }

  @Override
  public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    EnumFacing.Axis axis = state.getValue(FACING).getAxis();

    if (axis == EnumFacing.Axis.Z
        && (worldIn.getBlockState(pos.west()).getBlock() instanceof BlockWall || worldIn.getBlockState(pos.east()).getBlock() instanceof BlockWall)
        || axis == EnumFacing.Axis.X
            && (worldIn.getBlockState(pos.north()).getBlock() instanceof BlockWall || worldIn.getBlockState(pos.south()).getBlock() instanceof BlockWall)) {
      state = state.withProperty(IN_WALL, true);
    }

    return state;
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos.down()).getMaterial().isSolid() ? super.canPlaceBlockAt(worldIn, pos) : false;
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
    if (state.getValue(OPEN)) {
      return NULL_AABB;
    }
    else {
      return state.getValue(FACING).getAxis() == EnumFacing.Axis.Z //
          ? new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ() + 0.375, pos.getX() + 1, pos.getY() + 1.5, pos.getZ() + 0.625) //
          : new AxisAlignedBB(pos.getX() + 0.375, pos.getY(), pos.getZ(), pos.getX() + 0.625, pos.getY() + 1.5, pos.getZ() + 1);
    }
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    if (source.getBlockState(pos).getValue(FACING).getAxis() == EnumFacing.Axis.Z) {
      return new AxisAlignedBB(0, 0, 0.375F, 1, 1, 0.625F);
    }
    else {
      return new AxisAlignedBB(0.375F, 0, 0, 0.625F, 1, 1);
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
  public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos).getValue(OPEN);
  }

  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    return getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(OPEN, false).withProperty(POWERED, false).withProperty(IN_WALL,
        false);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    if (state.getValue(OPEN)) {
      state = state.withProperty(OPEN, false);
      worldIn.setBlockState(pos, state, 2);
    }
    else {
      EnumFacing enumfacing = EnumFacing.fromAngle(playerIn.rotationYaw);

      if (state.getValue(FACING) == enumfacing.getOpposite()) {
        state = state.withProperty(FACING, enumfacing);
      }

      state = state.withProperty(OPEN, true);
      worldIn.setBlockState(pos, state, 2);
    }
    worldIn.playEvent(playerIn, state.getValue(OPEN) ? 1003 : 1006, pos, 0);

    return true;
  }

  @Override
  public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
    World worldIn = (World) world;
    IBlockState state = worldIn.getBlockState(pos);
    if (!worldIn.isRemote) {
      boolean flag = worldIn.isBlockPowered(pos);

      if (flag || worldIn.getBlockState(neighbor).canProvidePower()) {
        if (flag && !state.getValue(OPEN) && !state.getValue(POWERED)) {
          worldIn.setBlockState(pos, state.withProperty(OPEN, true).withProperty(POWERED, true), 2);
          worldIn.playEvent(null, 1003, pos, 0);
        }
        else if (!flag && state.getValue(OPEN) && state.getValue(POWERED)) {
          worldIn.setBlockState(pos, state.withProperty(OPEN, false).withProperty(POWERED, false), 2);
          worldIn.playEvent(null, 1006, pos, 0);
        }
        else if (flag != state.getValue(POWERED)) {
          worldIn.setBlockState(pos, state.withProperty(POWERED, flag), 2);
        }
      }
    }
  }

  @Override
  @SideOnly(Side.CLIENT)
  public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
    return true;
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta)).withProperty(OPEN, (meta & 4) != 0).withProperty(POWERED, (meta & 8) != 0);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    int i = state.getValue(FACING).getHorizontalIndex();

    if (state.getValue(POWERED)) {
      i |= 8;
    }
    if (state.getValue(OPEN)) {
      i |= 4;
    }

    return i;
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING, OPEN, POWERED, IN_WALL);
  }
}
