package net.mcfr.mecanisms;

import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrBlocks;
import net.mcfr.McfrItems;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Répéteur long.
 *
 * @author Mc-Fr
 */
public class BlockLongRedstoneRepeater extends BlockRedstoneDiode {
  public static final int COEF = 20;

  public static final PropertyBool LOCKED = PropertyBool.create("locked");
  public static final PropertyInteger DELAY = PropertyInteger.create("delay", 1, 4);

  /**
   * Crée un répéteur long.
   * 
   * @param poweredindique s'il est alimenté
   */
  public BlockLongRedstoneRepeater(boolean powered) {
    super(powered);
    String name = (powered ? "" : "un") + "powered_long_repeater";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(DELAY, 1).withProperty(LOCKED, false));
  }

  @Override
  public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    return state.withProperty(LOCKED, isLocked(worldIn, pos, state));
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    if (!playerIn.capabilities.allowEdit) {
      return false;
    }
    else {
      worldIn.setBlockState(pos, state.cycleProperty(DELAY), 3);
      return true;
    }
  }

  @Override
  protected int getDelay(IBlockState state) {
    return state.getValue(DELAY) * 2 * COEF;
  }

  @Override
  protected IBlockState getPoweredState(IBlockState unpoweredState) {
    int delay = unpoweredState.getValue(DELAY);
    boolean locked = unpoweredState.getValue(LOCKED);
    EnumFacing enumfacing = unpoweredState.getValue(FACING);

    return McfrBlocks.LONG_REPEATER_ON.getDefaultState().withProperty(FACING, enumfacing).withProperty(DELAY, delay).withProperty(LOCKED, locked);
  }

  @Override
  protected IBlockState getUnpoweredState(IBlockState poweredState) {
    int delay = poweredState.getValue(DELAY);
    boolean locked = poweredState.getValue(LOCKED);
    EnumFacing enumfacing = poweredState.getValue(FACING);

    return McfrBlocks.LONG_REPEATER_OFF.getDefaultState().withProperty(FACING, enumfacing).withProperty(DELAY, delay).withProperty(LOCKED, locked);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.LONG_REPEATER;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(McfrItems.LONG_REPEATER);
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return getItem(world, pos, state);
  }

  @Override
  public boolean isLocked(IBlockAccess worldIn, BlockPos pos, IBlockState state) {
    return getPowerOnSides(worldIn, pos, state) > 0;
  }

  @Override
  protected boolean isAlternateInput(IBlockState state) {
    return isLongRedstoneRepeaterBlockId(state.getBlock());
  }

  @Override
  public boolean isFacingTowardsRepeater(World worldIn, BlockPos pos, IBlockState state) {
    EnumFacing enumfacing = state.getValue(FACING).getOpposite();
    pos = pos.offset(enumfacing);

    return super.isFacingTowardsRepeater(worldIn, pos, state) //
        || isLongRedstoneRepeaterBlockId(worldIn.getBlockState(pos).getBlock()) ? worldIn.getBlockState(pos).getValue(FACING) != enumfacing : false;
  }

  /**
   * Indique si le block est un répéteur long.
   * 
   * @param block le bloc
   * @return true si c'est un répérteur long
   */
  public static boolean isLongRedstoneRepeaterBlockId(Block block) {
    return McfrBlocks.LONG_REPEATER_ON.isAssociatedBlock(block) || McfrBlocks.LONG_REPEATER_OFF.isAssociatedBlock(block);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
    if (this.isRepeaterPowered) {
      EnumFacing enumfacing = state.getValue(FACING);
      double d0 = pos.getX() + 0.5 + (rand.nextFloat() - 0.5) * 0.2;
      double d1 = pos.getY() + 0.4 + (rand.nextFloat() - 0.5) * 0.2;
      double d2 = pos.getZ() + 0.5 + (rand.nextFloat() - 0.5) * 0.2;
      float f = -5;

      if (rand.nextBoolean()) {
        f = state.getValue(DELAY) * 2 - 1;
      }

      f /= 16;
      double d3 = f * enumfacing.getFrontOffsetX();
      double d4 = f * enumfacing.getFrontOffsetZ();

      worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0 + d3, d1, d2 + d4, 0, 0, 0, new int[0]);
    }
  }

  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    super.breakBlock(worldIn, pos, state);
    notifyNeighbors(worldIn, pos, state);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta)).withProperty(LOCKED, false).withProperty(DELAY, 1 + (meta >> 2));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getHorizontalIndex() | state.getValue(DELAY) - 1 << 2;
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING, DELAY, LOCKED);
  }
}
