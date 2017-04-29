package net.mcfr.farming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Cette classe représente le bloc du haut d'un buisson en deux parties.
 *
 * @author Mc-Fr
 */
public abstract class BlockBushTop extends BlockBush {
  /** L'âge maximal */
  private static final int MAX = 4;
  public static final PropertyInteger AGE = PropertyInteger.create("age", 0, MAX);

  /**
   * Crée un bloc de buisson haut.
   * 
   * @param name le nom
   */
  public BlockBushTop(String name) {
    super();
    name = name + "_block";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setSoundType(SoundType.PLANT);
    setHardness(0.2F);
    setCreativeTab(CreativeTabs.DECORATIONS);
    setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
    setTickRandomly(true);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return new AxisAlignedBB(0, 0, 0, 1, 0.5f, 1);
  }

  /**
   * @return le bloc sur lequel celui-ci peut être posé
   */
  public abstract BlockBushBase getBaseBlock();

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos.down()).getBlock() == getBaseBlock();
  }

  @Override
  public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
    return worldIn.getBlockState(pos.down()).getBlock() == getBaseBlock();
  }

  @Override
  public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
    World worldIn = (World) world;
    IBlockState state = worldIn.getBlockState(pos);
    if (!canBlockStay(worldIn, pos, state)) {
      worldIn.setBlockToAir(pos);
    }
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
      int i = state.getValue(AGE);

      if (i < MAX) {
        worldIn.setBlockState(pos, state.withProperty(AGE, i + 1));
      }
    }
    super.updateTick(worldIn, pos, state, rand);
  }

  /**
   * @return les items
   */
  public abstract List<ItemStack> getItems();

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return getBaseBlock().getPickBlock(state, target, world, pos, player);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return null;
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(AGE, meta);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(AGE);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, AGE);
  }

  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    List<ItemStack> drops = new ArrayList<>();
    int age = state.getValue(AGE);

    if (age >= MAX) {
      drops.addAll(getItems());
    }

    return drops;
  }
}
