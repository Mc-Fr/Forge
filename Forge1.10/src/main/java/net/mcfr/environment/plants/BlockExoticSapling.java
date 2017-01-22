package net.mcfr.environment.plants;

import java.util.List;
import java.util.Random;

import net.mcfr.commons.IBlockWithVariants;
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
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockExoticSapling extends BlockBush implements IBlockWithVariants, IGrowable {
  public static final PropertyEnum<EnumExoticWoodType> VARIANT = PropertyEnum.create("type", EnumExoticWoodType.class);
  public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

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
    float f = 0.4F, h = 0.5F;
    return new AxisAlignedBB(h - f, 0, h - f, h + f, f * 2, h + f);
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

  // TODO : génération des arbres.
  @SuppressWarnings("unused")
  public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (true)
      return; // TEMP
    if (!TerrainGen.saplingGrowTree(worldIn, rand, pos))
      return;

    WorldGenerator worldgenerator = rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
    int i = 0;
    int j = 0;
    boolean flag = false;

    // switch (state.getValue(TYPE)) {
    // case SPRUCE:
    // label114: for (i = 0; i >= -1; --i) {
    // for (j = 0; j >= -1; --j) {
    // if (func_181624_a(worldIn, pos, i, j, ExoticWoodType.SPRUCE)) {
    // worldgenerator = new WorldGenMegaPineTree(false, rand.nextBoolean());
    // flag = true;
    // break label114;
    // }
    // }
    // }
    //
    // if (!flag) {
    // j = 0;
    // i = 0;
    // worldgenerator = new WorldGenTaiga2(true);
    // }
    //
    // break;
    // case BIRCH:
    // worldgenerator = new WorldGenForest(true, false);
    // break;
    // case JUNGLE:
    // IBlockState iblockstate = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT,
    // ExoticWoodType.JUNGLE);
    // IBlockState iblockstate1 = Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT,
    // ExoticWoodType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    // label269: for (i = 0; i >= -1; --i) {
    // for (j = 0; j >= -1; --j) {
    // if (func_181624_a(worldIn, pos, i, j, ExoticWoodType.JUNGLE)) {
    // worldgenerator = new WorldGenMegaJungle(true, 10, 20, iblockstate, iblockstate1);
    // flag = true;
    // break label269;
    // }
    // }
    // }
    //
    // if (!flag) {
    // j = 0;
    // i = 0;
    // worldgenerator = new WorldGenTrees(true, 4 + rand.nextInt(7), iblockstate, iblockstate1,
    // false);
    // }
    //
    // break;
    // case ACACIA:
    // worldgenerator = new WorldGenSavannaTree(true);
    // break;
    // case DARK_OAK:
    // label390: for (i = 0; i >= -1; --i) {
    // for (j = 0; j >= -1; --j) {
    // if (func_181624_a(worldIn, pos, i, j, ExoticWoodType.DARK_OAK)) {
    // worldgenerator = new WorldGenCanopyTree(true);
    // flag = true;
    // break label390;
    // }
    // }
    // }
    //
    // if (!flag) {
    // return;
    // }
    // case OAK:
    // }

    IBlockState iblockstate2 = Blocks.AIR.getDefaultState();

    if (flag) {
      worldIn.setBlockState(pos.add(i, 0, j), iblockstate2, 4);
      worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate2, 4);
      worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate2, 4);
      worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate2, 4);
    }
    else {
      worldIn.setBlockState(pos, iblockstate2, 4);
    }

    if (!worldgenerator.generate(worldIn, rand, pos.add(i, 0, j))) {
      if (flag) {
        worldIn.setBlockState(pos.add(i, 0, j), state, 4);
        worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
        worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
        worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
      }
      else {
        worldIn.setBlockState(pos, state, 4);
      }
    }
  }

  // Copiée de BlockSapling
  @SuppressWarnings("unused")
  private boolean func_181624_a(World worldIn, BlockPos pos, int i, int j, EnumExoticWoodType type) {
    return isTypeAt(worldIn, pos.add(i, 0, j), type) && isTypeAt(worldIn, pos.add(i + 1, 0, j), type) && isTypeAt(worldIn, pos.add(i, 0, j + 1), type) && isTypeAt(worldIn, pos.add(i + 1, 0, j + 1), type);
  }

  /**
   * Vérifie que le bloc à une position donnée possède une pousse exotique du type indiqué.
   */
  public boolean isTypeAt(World worldIn, BlockPos pos, EnumExoticWoodType type) {
    IBlockState state = worldIn.getBlockState(pos);
    return state.getBlock() == this && state.getValue(VARIANT) == type;
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
