package net.mcfr.construction;

import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.McfrBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Classe de base des murets du mod.
 * 
 * @author Mc-Fr
 */
public abstract class McfrBlockWall extends McfrBlock implements IBlockWithVariants {
  public static final PropertyBool UP = PropertyBool.create("up");
  public static final PropertyBool NORTH = PropertyBool.create("north");
  public static final PropertyBool EAST = PropertyBool.create("east");
  public static final PropertyBool SOUTH = PropertyBool.create("south");
  public static final PropertyBool WEST = PropertyBool.create("west");

  /** Boîte de selection pour chaque connexion. */
  // @f0
  protected static final AxisAlignedBB[] AABB_BY_INDEX = {
    new AxisAlignedBB(0.25,   0, 0.25,   0.75,   1,     0.75),
    new AxisAlignedBB(0.25,   0, 0.25,   0.75,   1,     1),
    new AxisAlignedBB(0,      0, 0.25,   0.75,   1,     0.75),
    new AxisAlignedBB(0,      0, 0.25,   0.75,   1,     1),
    new AxisAlignedBB(0.25,   0, 0,      0.75,   1,     0.75),
    new AxisAlignedBB(0.3125, 0, 0,      0.6875, 0.875, 1),
    new AxisAlignedBB(0,      0, 0,      0.75,   1,     0.75),
    new AxisAlignedBB(0,      0, 0,      0.75,   1,     1),
    new AxisAlignedBB(0.25,   0, 0.25,   1,      1,     0.75),
    new AxisAlignedBB(0.25,   0, 0.25,   1,      1,     1),
    new AxisAlignedBB(0,      0, 0.3125, 1,      0.875, 0.6875),
    new AxisAlignedBB(0,      0, 0.25,   1,      1,     1),
    new AxisAlignedBB(0.25,   0, 0,      1,      1,     0.75),
    new AxisAlignedBB(0.25,   0, 0,      1,      1,     1),
    new AxisAlignedBB(0,      0, 0,      1,      1,     0.75),
    new AxisAlignedBB(0,      0, 0,      1,      1,     1)
  };
  // @f1
  /** Boîtes de collision pour chaque connexion. */
  protected static final AxisAlignedBB[] CLIP_AABB_BY_INDEX;

  static {
    CLIP_AABB_BY_INDEX = new AxisAlignedBB[16];
    for (int i = 0; i < CLIP_AABB_BY_INDEX.length; i++)
      CLIP_AABB_BY_INDEX[i] = AABB_BY_INDEX[i].setMaxY(1.5);
  }

  /**
   * Crée un nouveau mur.
   * 
   * @param name le nom (sans le suffixe '_wall')
   * @param material le matériau
   * @param sound le type de son
   * @param hardness la dureté
   * @param resistance la résistance aux explosions
   * @param tool l'outil nécessaire
   * @param harvestLevel le niveau de récolte
   */
  public McfrBlockWall(String name, Material material, SoundType sound, float hardness, float resistance, String tool, int harvestLevel) {
    super(name + "_wall", material, sound, hardness, resistance, tool, harvestLevel, CreativeTabs.BUILDING_BLOCKS);
    setDefaultState(this.blockState.getBaseState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    state = getActualState(state, source, pos);
    return AABB_BY_INDEX[getAABBIndex(state)];
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
    blockState = getActualState(blockState, worldIn, pos);
    return CLIP_AABB_BY_INDEX[getAABBIndex(blockState)];
  }

  /**
   * Retourne l'indice du AABB en fonction de l'état.
   * 
   * @param state l'état du bloc
   * @return l'indice du AABB correspondant.
   */
  private static int getAABBIndex(IBlockState state) {
    int i = 0;

    if (state.getValue(NORTH)) {
      i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
    }
    if (state.getValue(EAST)) {
      i |= 1 << EnumFacing.EAST.getHorizontalIndex();
    }
    if (state.getValue(SOUTH)) {
      i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
    }
    if (state.getValue(WEST)) {
      i |= 1 << EnumFacing.WEST.getHorizontalIndex();
    }

    return i;
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
  public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
    return false;
  }

  /**
   * Indique si le bloc peut se connecter au bloc à la position donnée.
   * 
   * @param worldIn le monde
   * @param pos la position
   * @return vrai si la connexion est possible
   */
  @SuppressWarnings("deprecation")
  private boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
    IBlockState state = worldIn.getBlockState(pos);
    Block block = state.getBlock();
    return block == Blocks.BARRIER ? false : (block != this && !(block instanceof BlockFenceGate) ? (block.getMaterial(state).isOpaque() && state.isFullCube() ? block.getMaterial(state) != Material.GOURD : false) : true);
  }

  @SuppressWarnings("deprecation")
  @Override
  @SideOnly(Side.CLIENT)
  public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
    return side == EnumFacing.DOWN ? super.shouldSideBeRendered(blockState, blockAccess, pos, side) : true;
  }

  @Override
  public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    boolean north = canConnectTo(worldIn, pos.north());
    boolean east = canConnectTo(worldIn, pos.east());
    boolean south = canConnectTo(worldIn, pos.south());
    boolean west = canConnectTo(worldIn, pos.west());
    boolean straight = north && !east && south && !west || !north && east && !south && west;

    return state.withProperty(UP, !straight || !worldIn.isAirBlock(pos.up())).withProperty(NORTH, north).withProperty(EAST, east).withProperty(SOUTH, south).withProperty(WEST, west);
  }

  /**
   * @return la propriété de variante
   */
  protected abstract PropertyEnum<? extends Enum<?>> getVariantProperty();

  @Override
  public abstract void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list);

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(this, 1, getMetaFromState(state));
  }

  @Override
  public int damageDropped(IBlockState state) {
    return getMetaFromState(state);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, UP, NORTH, SOUTH, EAST, WEST, getVariantProperty());
  }

  @Override
  public abstract IBlockState getStateFromMeta(int meta);

  @Override
  public abstract int getMetaFromState(IBlockState state);
}
