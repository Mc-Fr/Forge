package net.mcfr.construction;

import java.util.Random;

import net.mcfr.McfrBlocks;
import net.mcfr.commons.IEnumType;
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

public abstract class BlockStoneSlab extends McfrBlockSlab<BlockStoneSlab.EnumType> {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  public BlockStoneSlab() {
    super("stone", Material.ROCK, SoundType.STONE, 5, 10, "pickaxe", 0, EnumType.class);
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
    return new ItemStack(McfrBlocks.STONE_SLAB, 1, getMetaFromState(state) & 7);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return super.getStateFromMeta(meta).withProperty(VARIANT, EnumType.byMetadata(meta & 7));
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
    Item item = Item.getItemFromBlock(McfrBlocks.STONE_SLAB);
    return item == null ? null : new ItemStack(item, 1, damageDropped(state));
  }

  @Override
  public Item getItemDropped(IBlockState blockState, Random random, int unused) {
    return Item.getItemFromBlock(McfrBlocks.STONE_SLAB);
  }

  public static enum EnumType implements IEnumType<EnumType> {
    YELLOW_STONE("yellow_stone"),
    YELLOW_COBBLESTONE("yellow_cobblestone"),
    OCHER_STONE("ocher_stone"),
    OCHER_COBBLESTONE("ocher_cobblestone"),
    SLATE("slate"),
    BRICK_TILES("brick_tiles"),
    SLATE_TILES("slate_tiles");

    private String name;

    private EnumType(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return this.name;
    }

    @Override
    public int getMetadata() {
      return ordinal();
    }

    @Override
    public String toString() {
      return getName();
    }

    public static EnumType byMetadata(int meta) {
      return values()[meta % values().length];
    }
  }

  public static class BlockHalfStoneSlab extends BlockStoneSlab {
    @Override
    public boolean isDouble() {
      return false;
    }
  }

  public static class BlockDoubleStoneSlab extends BlockStoneSlab {
    @Override
    public boolean isDouble() {
      return true;
    }
  }
}
