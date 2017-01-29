package net.mcfr.environment.plants;

import java.util.List;
import java.util.Random;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.environment.plants.treeGen.WorldGenAppleTree;
import net.mcfr.environment.plants.treeGen.WorldGenBeluxier;
import net.mcfr.environment.plants.treeGen.WorldGenCherryTree;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockExoticSapling extends BlockBush implements IBlockWithVariants, IGrowable {
  public static final PropertyEnum<EnumExoticWoodType> VARIANT = PropertyEnum.create("type", EnumExoticWoodType.class);
  public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
  private static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.8, 0.9);

  public BlockExoticSapling() {
    super();
    String name = "exotic_sapling";
    setRegistryName(name);
    setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumExoticWoodType.APPLE_TREE).withProperty(STAGE, 0));
    setCreativeTab(CreativeTabs.DECORATIONS);
    setSoundType(SoundType.PLANT);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return SAPLING_AABB;
  }

  @Override
  public String getVariantName(int meta) {
    return getStateFromMeta(meta).getValue(VARIANT).getName();
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (!worldIn.isRemote) {
      super.updateTick(worldIn, pos, state, rand);

      if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
        grow(worldIn, pos, state, rand);
      }
    }
  }

  public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (state.getValue(STAGE) == 0) {
      worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
    }
    else {
      generateTree(worldIn, pos, state, rand);
    }
  }

  public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    WorldGenerator worldGenerator = null;
    int x = 0;
    int z = 0;
    boolean flag = false;

    switch (state.getValue(VARIANT)) {
      case APPLE_TREE:
        worldGenerator = new WorldGenAppleTree(true);
        break;
      case CHERRY_TREE:
        worldGenerator = new WorldGenCherryTree(true);
        break;
      // case PALM_TREE:
      // worldGenerator = new WorldGenPalmTree(true);
      // break;
      case BELUXIER:
        worldGenerator = new WorldGenBeluxier(true);
        break;
    }

    IBlockState air = Blocks.AIR.getDefaultState();

    if (flag) {
      worldIn.setBlockState(pos.add(x, 0, z), air, 4);
      worldIn.setBlockState(pos.add(x + 1, 0, z), air, 4);
      worldIn.setBlockState(pos.add(x, 0, z + 1), air, 4);
      worldIn.setBlockState(pos.add(x + 1, 0, z + 1), air, 4);
    }
    else {
      worldIn.setBlockState(pos, air, 4);
    }

    if (!worldGenerator.generate(worldIn, rand, pos.add(x, 0, z))) {
      if (flag) {
        worldIn.setBlockState(pos.add(x, 0, z), state, 4);
        worldIn.setBlockState(pos.add(x + 1, 0, z), state, 4);
        worldIn.setBlockState(pos.add(x, 0, z + 1), state, 4);
        worldIn.setBlockState(pos.add(x + 1, 0, z + 1), state, 4);
      }
      else {
        worldIn.setBlockState(pos, state, 4);
      }
    }
  }

  @Override
  public int damageDropped(IBlockState state) {
    return state.getValue(VARIANT).getMetadata();
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
    for (EnumExoticWoodType type : EnumExoticWoodType.values()) {
      list.add(new ItemStack(itemIn, 1, type.getMetadata()));
    }
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(this, 1, getMetaFromState(state));
  }

  @Override
  public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
    return true;
  }

  @Override
  public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
    return worldIn.rand.nextFloat() < 0.45;
  }

  @Override
  public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
    grow(worldIn, pos, state, rand);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty(VARIANT, EnumExoticWoodType.byMetadata(meta)).withProperty(STAGE, (meta & 8) >> 3);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(VARIANT).getMetadata() | (state.getValue(STAGE) << 3);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, VARIANT, STAGE);
  }
}
