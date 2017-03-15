package net.mcfr.decoration.furniture;

import java.util.List;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.McfrBlockOrientableContainer;
import net.mcfr.decoration.furniture.tile_entities.TileEntityTable;
import net.mcfr.guis.CustomGuiScreens;
import net.mcfr.utils.FacingUtils;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Classe de base pour les tables.
 * 
 * @author Mc-Fr
 */
public class BlockTable extends McfrBlockOrientableContainer<TileEntityTable> implements IBlockWithVariants {
  public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class);

  private AxisAlignedBB aabb;
  private String type;

  /**
   * Crée un table
   * 
   * @param type le type (sans le suffixe '_table')
   */
  public BlockTable(String type) {
    this(type, FULL_BLOCK_AABB);
  }

  /**
   * Crée un table
   * 
   * @param type le type (sans le suffixe '_table')
   * @param aabb la hitbox
   */
  public BlockTable(String type, AxisAlignedBB aabb) {
    super(type + "_table", Material.WOOD, SoundType.WOOD, 1.5f, 5, "axe", TileEntityTable.class, CreativeTabs.DECORATIONS);
    this.type = type;
    this.aabb = aabb;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return this.aabb;
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(VARIANT).getName();
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
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < BlockPlanks.EnumType.values().length; i++) {
      list.add(new ItemStack(itemIn, 1, i));
    }
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING, VARIANT);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata(meta % 6)).withProperty(FACING, EnumFacing.Plane.HORIZONTAL.facings()[meta / 6]);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(VARIANT).getMetadata() + (state.getValue(FACING).getHorizontalIndex() % 2) * 6;
  } 
  
  @Override
  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
      EntityLivingBase placer) {
    EnumFacing placerFacing = FacingUtils.getHorizontalFacing(placer);
    if (placerFacing == EnumFacing.SOUTH)
        placerFacing = EnumFacing.NORTH;
    if (placerFacing == EnumFacing.WEST)
        placerFacing = EnumFacing.EAST;
    return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, placerFacing);
  }

  @Override
  public TileEntityTable createNewTileEntity(World worldIn, int meta) {
    return new TileEntityTable(this.type);
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.TABLE;
  }
}
