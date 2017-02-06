package net.mcfr.construction;

import java.util.Random;

import net.mcfr.McfrBlocks;
import net.mcfr.environment.plants.EnumExoticWoodType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Dalle de bois exotique.
 * 
 * @author Mc-Fr
 */
public abstract class BlockExoticWoodSlab extends McfrBlockSlab<EnumExoticWoodType> {
  public static final PropertyEnum<EnumExoticWoodType> VARIANT = PropertyEnum.create("variant", EnumExoticWoodType.class);

  public BlockExoticWoodSlab() {
    super("exotic_wood", Material.WOOD, SoundType.WOOD, 2, 5, "axe", 0, EnumExoticWoodType.class);
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(VARIANT).getName();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
  }

  @Override
  public String getUnlocalizedName(int meta) {
    return super.getUnlocalizedName() + "." + getVariantName(meta);
  }

  @Override
  public IProperty<?> getVariantProperty() {
    return VARIANT;
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(McfrBlocks.EXOTIC_WOOD_SLAB, 1, getMetaFromState(state) & 7);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return super.getStateFromMeta(meta).withProperty(VARIANT, EnumExoticWoodType.byMetadata(meta & 7));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return super.getMetaFromState(state) | state.getValue(VARIANT).getMetadata();
  }

  @Override
  public int damageDropped(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  @SideOnly(Side.CLIENT)
  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    Item item = Item.getItemFromBlock(McfrBlocks.EXOTIC_WOOD_SLAB);
    return item == null ? null : new ItemStack(item, 1, damageDropped(state));

  }

  @Override
  public Item getItemDropped(IBlockState blockState, Random random, int unused) {
    return Item.getItemFromBlock(McfrBlocks.EXOTIC_WOOD_SLAB);
  }

  /**
   * Dalle de bois exotique simple.
   * 
   * @author Mc-Fr
   */
  public static class BlockHalfExoticWoodSlab extends BlockExoticWoodSlab {
    @Override
    public boolean isDouble() {
      return false;
    }
  }

  /**
   * Dalle de bois exotique double.
   * 
   * @author Mc-Fr
   */
  public static class BlockDoubleExoticWoodSlab extends BlockExoticWoodSlab {
    @Override
    public boolean isDouble() {
      return true;
    }
  }
}
