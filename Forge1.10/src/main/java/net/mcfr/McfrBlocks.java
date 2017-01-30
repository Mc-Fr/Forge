package net.mcfr;

import net.mcfr.commons.IBlockWithVariants;
import net.mcfr.commons.ItemBlockWithVariants;
import net.mcfr.commons.McfrBlock;
import net.mcfr.commons.McfrBlockOrientable;
import net.mcfr.construction.BlockCarvedClay;
import net.mcfr.construction.BlockColoredStonebrick;
import net.mcfr.construction.BlockExoticWoodSlab;
import net.mcfr.construction.BlockExoticWoodSlab.BlockDoubleExoticWoodSlab;
import net.mcfr.construction.BlockExoticWoodSlab.BlockHalfExoticWoodSlab;
import net.mcfr.construction.BlockHaySlab;
import net.mcfr.construction.BlockHaySlab.BlockDoubleHaySlab;
import net.mcfr.construction.BlockHaySlab.BlockHalfHaySlab;
import net.mcfr.construction.BlockMarble;
import net.mcfr.construction.BlockMarbleColumn;
import net.mcfr.construction.BlockMarbleSlab;
import net.mcfr.construction.BlockMarbleSlab.BlockDoubleMarbleSlab;
import net.mcfr.construction.BlockMarbleSlab.BlockHalfMarbleSlab;
import net.mcfr.construction.BlockMarbleSlab2;
import net.mcfr.construction.BlockMarbleSlab2.BlockDoubleMarbleSlab2;
import net.mcfr.construction.BlockMarbleSlab2.BlockHalfMarbleSlab2;
import net.mcfr.construction.BlockMarbleWall;
import net.mcfr.construction.BlockMetalSlab;
import net.mcfr.construction.BlockMetalSlab.BlockDoubleMetalSlab;
import net.mcfr.construction.BlockMetalSlab.BlockHalfMetalSlab;
import net.mcfr.construction.BlockOldHay;
import net.mcfr.construction.BlockRefinedPlanks;
import net.mcfr.construction.BlockRefinedPlanksSlab;
import net.mcfr.construction.BlockRefinedPlanksSlab.BlockDoubleRefinedPlanksSlab;
import net.mcfr.construction.BlockRefinedPlanksSlab.BlockHalfRefinedPlanksSlab;
import net.mcfr.construction.BlockStoneSlab;
import net.mcfr.construction.BlockStoneSlab.BlockDoubleStoneSlab;
import net.mcfr.construction.BlockStoneSlab.BlockHalfStoneSlab;
import net.mcfr.construction.BlockStoneWall;
import net.mcfr.construction.BlockStonebrickSlab;
import net.mcfr.construction.BlockStonebrickSlab.BlockDoubleStonebrickSlab;
import net.mcfr.construction.BlockStonebrickSlab.BlockHalfStonebrickSlab;
import net.mcfr.construction.BlockTiles;
import net.mcfr.construction.BlockTimbered;
import net.mcfr.construction.BlockWoodenWall;
import net.mcfr.construction.BlockWoodenWall2;
import net.mcfr.construction.ItemExoticWoodSlab;
import net.mcfr.construction.ItemHaySlab;
import net.mcfr.construction.ItemMarbleSlab;
import net.mcfr.construction.ItemMarbleSlab2;
import net.mcfr.construction.ItemMetalSlab;
import net.mcfr.construction.ItemRefinedPlanksSlab;
import net.mcfr.construction.ItemStoneSlab;
import net.mcfr.construction.ItemStonebrickSlab;
import net.mcfr.construction.McfrBlockLeavesPyramid;
import net.mcfr.construction.McfrBlockLeavesSlope;
import net.mcfr.construction.McfrBlockPyramid;
import net.mcfr.construction.McfrBlockSlab;
import net.mcfr.construction.McfrBlockSlope;
import net.mcfr.construction.McfrBlockStairs;
import net.mcfr.construction.McfrItemSlab;
import net.mcfr.craftsmanship.BlockCircularSaw;
import net.mcfr.craftsmanship.BlockLargeWorkbench;
import net.mcfr.craftsmanship.BlockLoom;
import net.mcfr.craftsmanship.BlockTanningRack;
import net.mcfr.decoration.beds.BlockHayBed;
import net.mcfr.decoration.beds.BlockNormalBed;
import net.mcfr.decoration.beds.BlockSleepingBag;
import net.mcfr.decoration.beds.BlockStoneBed;
import net.mcfr.decoration.containerBlocks.BlockBeerBarrel;
import net.mcfr.decoration.containerBlocks.BlockBookshelf;
import net.mcfr.decoration.containerBlocks.BlockCiderBarrel;
import net.mcfr.decoration.containerBlocks.BlockCrate;
import net.mcfr.decoration.containerBlocks.BlockEmptyBarrel;
import net.mcfr.decoration.containerBlocks.BlockFoodCrate;
import net.mcfr.decoration.containerBlocks.BlockLittleChest;
import net.mcfr.decoration.containerBlocks.BlockPallet;
import net.mcfr.decoration.containerBlocks.BlockRumBarrel;
import net.mcfr.decoration.containerBlocks.BlockWineBarrel;
import net.mcfr.decoration.furniture.BlockArmChair;
import net.mcfr.decoration.furniture.BlockArmorStand;
import net.mcfr.decoration.furniture.BlockBench;
import net.mcfr.decoration.furniture.BlockChair;
import net.mcfr.decoration.furniture.BlockShelf;
import net.mcfr.decoration.furniture.BlockShowcase;
import net.mcfr.decoration.furniture.BlockStool;
import net.mcfr.decoration.furniture.BlockTable;
import net.mcfr.decoration.furniture.BlockTableWithFoot;
import net.mcfr.decoration.furniture.BlockWeaponsStand;
import net.mcfr.decoration.furniture.BlockWoodenChair;
import net.mcfr.decoration.furniture.BlockWoodenShelf;
import net.mcfr.decoration.lighting.BlockBrazier;
import net.mcfr.decoration.lighting.BlockCampfire;
import net.mcfr.decoration.lighting.BlockChandelier;
import net.mcfr.decoration.lighting.BlockLantern;
import net.mcfr.decoration.lighting.BlockLargeTorch;
import net.mcfr.decoration.lighting.BlockLitCampFire;
import net.mcfr.decoration.lighting.BlockSimpleCandle;
import net.mcfr.decoration.lighting.BlockTripleCandle;
import net.mcfr.decoration.lighting.EnumLanternColor;
import net.mcfr.decoration.misc.BlockChain;
import net.mcfr.decoration.misc.BlockChessboard;
import net.mcfr.decoration.misc.BlockCoins;
import net.mcfr.decoration.misc.BlockFeathers;
import net.mcfr.decoration.misc.BlockHangedLadder;
import net.mcfr.decoration.misc.BlockMoucharabieh;
import net.mcfr.decoration.misc.BlockMoucharabiehPane;
import net.mcfr.decoration.misc.BlockPebbles;
import net.mcfr.decoration.misc.BlockRope;
import net.mcfr.decoration.misc.BlockRopeAttach;
import net.mcfr.decoration.misc.BlockSmokeGenerator;
import net.mcfr.decoration.misc.BlockSupport;
import net.mcfr.decoration.misc.McfrBlockCarpet;
import net.mcfr.decoration.misc.McfrBlockFence;
import net.mcfr.decoration.signs.BlockStandingNormalSign;
import net.mcfr.decoration.signs.BlockStandingOrpSign;
import net.mcfr.decoration.signs.BlockStandingPaperSign;
import net.mcfr.decoration.signs.BlockSuspendedNormalSign;
import net.mcfr.decoration.signs.BlockSuspendedPaperSign;
import net.mcfr.decoration.signs.BlockTombstone;
import net.mcfr.decoration.signs.BlockWallNormalSign;
import net.mcfr.decoration.signs.BlockWallNote;
import net.mcfr.decoration.signs.BlockWallOrpSign;
import net.mcfr.decoration.signs.BlockWallPaperSign;
import net.mcfr.economy.BlockChanger;
import net.mcfr.environment.BlockBeehive;
import net.mcfr.environment.BlockStalactite;
import net.mcfr.environment.plants.BlockExoticLeaves;
import net.mcfr.environment.plants.BlockExoticPlanks;
import net.mcfr.environment.plants.BlockExoticSapling;
import net.mcfr.environment.plants.BlockExoticWood;
import net.mcfr.environment.plants.BlockGlowingMushroom;
import net.mcfr.environment.plants.BlockGlowingVine;
import net.mcfr.environment.plants.BlockMushroom;
import net.mcfr.environment.plants.BlockSandBush;
import net.mcfr.environment.plants.BlockSpores;
import net.mcfr.environment.plants.BlockWildGrass;
import net.mcfr.environment.plants.McfrBlockReed;
import net.mcfr.farming.BlockBarleyCrops;
import net.mcfr.farming.BlockFertilizer;
import net.mcfr.farming.BlockHempBase;
import net.mcfr.farming.BlockHempTop;
import net.mcfr.farming.BlockSugarCanes;
import net.mcfr.farming.BlockVineBase;
import net.mcfr.farming.BlockVineTop;
import net.mcfr.food.McfrBlockCake;
import net.mcfr.forge.BlockBellows;
import net.mcfr.forge.BlockStove;
import net.mcfr.forge.McfrBlockAnvil;
import net.mcfr.mecanisms.BlockLongRedstoneRepeater;
import net.mcfr.mecanisms.BlockPulsor;
import net.mcfr.mecanisms.McfrBlockFenceGate;
import net.mcfr.mecanisms.doors.BlockCraftsmanAcaciaDoor;
import net.mcfr.mecanisms.doors.BlockCraftsmanBirchDoor;
import net.mcfr.mecanisms.doors.BlockCraftsmanDarkOakDoor;
import net.mcfr.mecanisms.doors.BlockCraftsmanJungleDoor;
import net.mcfr.mecanisms.doors.BlockCraftsmanOakDoor;
import net.mcfr.mecanisms.doors.BlockCraftsmanSpruceDoor;
import net.mcfr.mecanisms.doors.BlockStrongOakDoor;
import net.mcfr.mecanisms.trapdoors.BlockCraftsmanTrapdoor;
import net.mcfr.mecanisms.trapdoors.BlockStrongTrapdoor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Cette classe regroupe tous les blocs du serveur.
 * <p>
 * Les blocs <i>vanilla</i> suivant ont été modifiés&nbsp;:
 * <ul>
 * <li>granite brut (1/1) -> roche jaune</li>
 * <li>granite lisse (1/2) -> pierre jaune</li>
 * <li>diorite brute (1/3) -> roche ocre</li>
 * <li>diorite lisse (1/4) -> pierre ocre</li>
 * <li>andésite brute (1/5) -> ardoise</li>
 * </ul>
 * </p>
 *
 * @author Mc-Fr
 */
public final class McfrBlocks {
  public static final BlockChanger CHANGER = new BlockChanger();

  public static final BlockPulsor PULSOR = new BlockPulsor();

  public static final BlockSmokeGenerator SMOKE_GENERATOR = new BlockSmokeGenerator();
  /** Feu de camp */
  public static final BlockCampfire CAMPFIRE = new BlockCampfire();
  /** Feu de camp allumé (allumé) */
  public static final BlockLitCampFire LIT_CAMPFIRE = new BlockLitCampFire();

  // Forge
  public static final McfrBlockAnvil ANVIL = new McfrBlockAnvil();
  /** Haut-fourneau */
  public static final BlockStove STOVE = new BlockStove(false);
  /** Haut-fourneau allumé */
  public static final BlockStove LIT_STOVE = new BlockStove(true);
  /** Soufflet */
  public static final BlockBellows BELLOWS = new BlockBellows();
  // Construction
  public static final BlockOldHay OLD_HAY = new BlockOldHay();
  public static final McfrBlock REFINED_GOLD_BLOCK = new McfrBlock("refined_gold_block", Material.IRON, MapColor.GOLD, SoundType.METAL, 3, 10, "pickaxe", 2, CreativeTabs.BUILDING_BLOCKS);
  public static final McfrBlock REFINED_IRON_BLOCK = new McfrBlock("refined_iron_block", Material.IRON, SoundType.METAL, 5, 10, "pickaxe", 1, CreativeTabs.BUILDING_BLOCKS);
  public static final BlockRefinedPlanks REFINED_PLANKS = new BlockRefinedPlanks();
  public static final McfrBlock ROUGH_SANDSTONE = new McfrBlock("rough_sandstone", Material.ROCK, SoundType.STONE, 0.8f, 0, "pickaxe", 0, CreativeTabs.BUILDING_BLOCKS);
  public static final BlockColoredStonebrick YELLOW_STONEBRICK = new BlockColoredStonebrick("yellow");
  public static final BlockColoredStonebrick OCHER_STONEBRICK = new BlockColoredStonebrick("ocher");
  /** Torchis */
  public static final McfrBlock DAUB = new McfrBlock("daub", Material.CLAY, SoundType.STONE, 1, 10, "shovel", 0, CreativeTabs.BUILDING_BLOCKS);
  /** Colombages */
  public static final BlockTimbered TIMBERED_BLOCK = new BlockTimbered();
  /** Tuiles */
  public static final BlockTiles TILES = new BlockTiles();
  public static final BlockMarble MARBLE = new BlockMarble();
  public static final BlockMarbleColumn MARBLE_COLUMN = new BlockMarbleColumn();
  public static final BlockCarvedClay CARVED_CLAY = new BlockCarvedClay();
  // Agriculture
  /** Orge */
  public static final BlockBarleyCrops BARLEY = new BlockBarleyCrops();
  public static final BlockVineBase VINE_BASE = new BlockVineBase();
  public static final BlockVineTop VINE_TOP = new BlockVineTop();
  public static final BlockSugarCanes SUGAR_CANES = new BlockSugarCanes();
  /** Chanvre (base) */
  public static final BlockHempBase HEMP_BASE = new BlockHempBase();
  /** Chanvre (haut) */
  public static final BlockHempTop HEMP_TOP = new BlockHempTop();
  public static final BlockFertilizer FERTILIZER = new BlockFertilizer();
  // Ateliers
  /** Métier à tisser */
  public static final BlockLoom LOOM = new BlockLoom();
  public static final BlockTanningRack TANNING_RACK = new BlockTanningRack();
  public static final BlockCircularSaw CIRCULAR_SAW = new BlockCircularSaw();
  public static final BlockLargeWorkbench LARGE_WORKBENCH = new BlockLargeWorkbench();
  // Bois
  public static final BlockExoticPlanks EXOTIC_PLANKS = new BlockExoticPlanks();
  public static final BlockExoticSapling EXOTIC_SAPLING = new BlockExoticSapling();
  public static final BlockExoticWood EXOTIC_WOOD = new BlockExoticWood();
  public static final BlockExoticLeaves EXOTIC_LEAVES = new BlockExoticLeaves();
  // Environnement
  public static final BlockStalactite STONE_STALACTITE = new BlockStalactite("stone", Material.ROCK, SoundType.STONE, 1.5f, 0, "pickaxe", 0);
  public static final BlockStalactite ICICLE = new BlockStalactite("ice", Material.ICE, SoundType.GLASS, 1, 0, null, -1);
  public static final BlockBeehive BEEHIVE = new BlockBeehive();
  public static final BlockGlowingVine GLOWING_VINE = new BlockGlowingVine();
  public static final BlockGlowingMushroom GLOWING_MUSHROOM = new BlockGlowingMushroom();
  public static final BlockWildGrass WILD_GRASS = new BlockWildGrass();
  public static final BlockSandBush SAND_BUSH = new BlockSandBush();
  /** Roseaux */
  public static final McfrBlockReed REEDS = new McfrBlockReed();
  // Champignons géants
  public static final BlockMushroom MUSHROOM_CAP = new BlockMushroom();
  public static final BlockSpores SPORES = new BlockSpores();
  // Tonneaux
  public static final BlockEmptyBarrel EMPTY_BARREL = new BlockEmptyBarrel();
  public static final BlockBeerBarrel BEER_BARREL = new BlockBeerBarrel();
  public static final BlockCiderBarrel CIDER_BARREL = new BlockCiderBarrel();
  public static final BlockWineBarrel WINE_BARREL = new BlockWineBarrel();
  public static final BlockRumBarrel RUM_BARREL = new BlockRumBarrel();
  // Coffres
  public static final BlockLittleChest LITTLE_CHEST = new BlockLittleChest();
  public static final BlockCrate CRATE = new BlockCrate();
  public static final BlockFoodCrate FOOD_CRATE = new BlockFoodCrate();
  public static final BlockPallet PALLET = new BlockPallet();
  public static final BlockBookshelf BOOKSHELF = new BlockBookshelf();
  // Dalles
  public static final BlockHaySlab HAY_SLAB = new BlockHalfHaySlab();
  public static final BlockHaySlab DOUBLE_HAY_SLAB = new BlockDoubleHaySlab();
  public static final BlockRefinedPlanksSlab REFINED_PLANKS_SLAB = new BlockHalfRefinedPlanksSlab();
  public static final BlockRefinedPlanksSlab DOUBLE_REFINED_PLANKS_SLAB = new BlockDoubleRefinedPlanksSlab();
  public static final BlockExoticWoodSlab EXOTIC_WOOD_SLAB = new BlockHalfExoticWoodSlab();
  public static final BlockExoticWoodSlab DOUBLE_EXOTIC_WOOD_SLAB = new BlockDoubleExoticWoodSlab();
  public static final BlockMetalSlab METAL_SLAB = new BlockHalfMetalSlab();
  public static final BlockMetalSlab DOUBLE_METAL_SLAB = new BlockDoubleMetalSlab();
  public static final BlockStoneSlab STONE_SLAB = new BlockHalfStoneSlab();
  public static final BlockStoneSlab DOUBLE_STONE_SLAB = new BlockDoubleStoneSlab();
  public static final BlockStonebrickSlab STONEBRICK_SLAB = new BlockHalfStonebrickSlab();
  public static final BlockStonebrickSlab DOUBLE_STONEBRICK_SLAB = new BlockDoubleStonebrickSlab();
  public static final BlockMarbleSlab MARBLE_SLAB = new BlockHalfMarbleSlab();
  public static final BlockMarbleSlab DOUBLE_MARBLE_SLAB = new BlockDoubleMarbleSlab();
  public static final BlockMarbleSlab2 MARBLE_SLAB2 = new BlockHalfMarbleSlab2();
  public static final BlockMarbleSlab2 DOUBLE_MARBLE_SLAB2 = new BlockDoubleMarbleSlab2();
  // Escaliers
  public static final McfrBlockStairs STONE_STAIRS = new McfrBlockStairs(Blocks.STONE, 0, "stone", "pickaxe", 0);
  public static final McfrBlockStairs OAK_LOG_STAIRS = new McfrBlockStairs(Blocks.LOG, 0, "oak_log", "axe", 0);
  public static final McfrBlockStairs SPRUCE_LOG_STAIRS = new McfrBlockStairs(Blocks.LOG, 1, "spruce_log", "axe", 0);
  public static final McfrBlockStairs BIRCH_LOG_STAIRS = new McfrBlockStairs(Blocks.LOG, 2, "birch_log", "axe", 0);
  public static final McfrBlockStairs JUNGLE_LOG_STAIRS = new McfrBlockStairs(Blocks.LOG, 3, "jungle_log", "axe", 0);
  public static final McfrBlockStairs ACACIA_LOG_STAIRS = new McfrBlockStairs(Blocks.LOG2, 0, "acacia_log", "axe", 0);
  public static final McfrBlockStairs DARK_OAK_LOG_STAIRS = new McfrBlockStairs(Blocks.LOG2, 1, "dark_oak_log", "axe", 0);
  public static final McfrBlockStairs CHISELED_SANDSTONE_STAIRS = new McfrBlockStairs(Blocks.SANDSTONE, 1, "chiseled_sandstone", "pickaxe", 0);
  public static final McfrBlockStairs SMOOTH_SANDSTONE_STAIRS = new McfrBlockStairs(Blocks.SANDSTONE, 2, "smooth_sandstone", "pickaxe", 0);
  public static final McfrBlockStairs WHITE_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 0, "white_wool", null, -1);
  public static final McfrBlockStairs ORANGE_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 1, "orange_wool", null, -1);
  public static final McfrBlockStairs MAGENTA_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 2, "magenta_wool", null, -1);
  public static final McfrBlockStairs LIGHT_BLUE_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 3, "light_blue_wool", null, -1);
  public static final McfrBlockStairs YELLOW_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 4, "yellow_wool", null, -1);
  public static final McfrBlockStairs LIME_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 5, "lime_wool", null, -1);
  public static final McfrBlockStairs PINK_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 6, "pink_wool", null, -1);
  public static final McfrBlockStairs GRAY_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 7, "gray_wool", null, -1);
  public static final McfrBlockStairs LIGHT_GRAY_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 8, "light_gray_wool", null, -1);
  public static final McfrBlockStairs CYAN_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 9, "cyan_wool", null, -1);
  public static final McfrBlockStairs PURPLE_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 10, "purple_wool", null, -1);
  public static final McfrBlockStairs BLUE_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 11, "blue_wool", null, -1);
  public static final McfrBlockStairs BROWN_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 12, "brown_wool", null, -1);
  public static final McfrBlockStairs GREEN_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 13, "green_wool", null, -1);
  public static final McfrBlockStairs RED_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 14, "red_wool", null, -1);
  public static final McfrBlockStairs BLACK_WOOL_STAIRS = new McfrBlockStairs(Blocks.WOOL, 15, "black_wool", null, -1);
  public static final McfrBlockStairs GOLDEN_STAIRS = new McfrBlockStairs(Blocks.GOLD_BLOCK, 0, "golden", "pickaxe", 2);
  public static final McfrBlockStairs IRON_STAIRS = new McfrBlockStairs(Blocks.IRON_BLOCK, 0, "iron", "pickaxe", 1);
  public static final McfrBlockStairs MOSSY_COBBLESTONE_STAIRS = new McfrBlockStairs(Blocks.MOSSY_COBBLESTONE, 0, "mossy_cobblestone", "pickaxe", 0);
  public static final McfrBlockStairs MOSSY_STONEBRICK_STAIRS = new McfrBlockStairs(Blocks.STONEBRICK, 1, "mossy_stonebrick", "pickaxe", 0);
  public static final McfrBlockStairs CRACKED_STONEBRICK_STAIRS = new McfrBlockStairs(Blocks.STONEBRICK, 2, "cracked_stonebrick", "pickaxe", 0);
  public static final McfrBlockStairs WHITE_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 0, "white_clay", "pickaxe", 0);
  public static final McfrBlockStairs ORANGE_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 1, "orange_clay", "pickaxe", 0);
  public static final McfrBlockStairs MAGENTA_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 2, "magenta_clay", "pickaxe", 0);
  public static final McfrBlockStairs LIGHT_BLUE_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 3, "light_blue_clay", "pickaxe", 0);
  public static final McfrBlockStairs YELLOW_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 4, "yellow_clay", "pickaxe", 0);
  public static final McfrBlockStairs LIME_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 5, "lime_clay", "pickaxe", 0);
  public static final McfrBlockStairs PINK_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 6, "pink_clay", "pickaxe", 0);
  public static final McfrBlockStairs GRAY_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 7, "gray_clay", "pickaxe", 0);
  public static final McfrBlockStairs LIGHT_GRAY_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 8, "light_gray_clay", "pickaxe", 0);
  public static final McfrBlockStairs CYAN_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 9, "cyan_clay", "pickaxe", 0);
  public static final McfrBlockStairs PURPLE_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 10, "purple_clay", "pickaxe", 0);
  public static final McfrBlockStairs BLUE_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 11, "blue_clay", "pickaxe", 0);
  public static final McfrBlockStairs BROWN_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 12, "brown_clay", "pickaxe", 0);
  public static final McfrBlockStairs GREEN_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 13, "green_clay", "pickaxe", 0);
  public static final McfrBlockStairs RED_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 14, "red_clay", "pickaxe", 0);
  public static final McfrBlockStairs BLACK_CLAY_STAIRS = new McfrBlockStairs(Blocks.STAINED_HARDENED_CLAY, 15, "black_clay", "pickaxe", 0);
  public static final McfrBlockStairs HAY_STAIRS = new McfrBlockStairs(Blocks.HAY_BLOCK, 0, "hay", null, -1);
  public static final McfrBlockStairs OLD_HAY_STAIRS = new McfrBlockStairs(OLD_HAY, 0, "old_hay", null, -1);
  public static final McfrBlockStairs APPLE_PLANKS_STAIRS = new McfrBlockStairs(EXOTIC_PLANKS, 0, "apple_planks", "axe", 0);
  public static final McfrBlockStairs CHERRY_PLANKS_STAIRS = new McfrBlockStairs(EXOTIC_PLANKS, 1, "cherry_planks", "axe", 0);
  public static final McfrBlockStairs PALM_PLANKS_STAIRS = new McfrBlockStairs(EXOTIC_PLANKS, 2, "palm_planks", "axe", 0);
  public static final McfrBlockStairs BELUXIER_PLANKS_STAIRS = new McfrBlockStairs(EXOTIC_PLANKS, 3, "beluxier_planks", "axe", 0);
  public static final McfrBlockStairs APPLE_LOG_STAIRS = new McfrBlockStairs(EXOTIC_WOOD, 0, "apple_log", "axe", 0);
  public static final McfrBlockStairs CHERRY_LOG_STAIRS = new McfrBlockStairs(EXOTIC_WOOD, 1, "cherry_log", "axe", 0);
  public static final McfrBlockStairs PALM_LOG_STAIRS = new McfrBlockStairs(EXOTIC_WOOD, 2, "palm_log", "axe", 0);
  public static final McfrBlockStairs BELUXIER_LOG_STAIRS = new McfrBlockStairs(EXOTIC_WOOD, 3, "beluxier_log", "axe", 0);
  public static final McfrBlockStairs REFINED_OAK_STAIRS = new McfrBlockStairs(REFINED_PLANKS, 0, "refined_oak_planks", "axe", 0);
  public static final McfrBlockStairs REFINED_SPRUCE_STAIRS = new McfrBlockStairs(REFINED_PLANKS, 1, "refined_spruce_planks", "axe", 0);
  public static final McfrBlockStairs REFINED_BIRCH_STAIRS = new McfrBlockStairs(REFINED_PLANKS, 2, "refined_birch_planks", "axe", 0);
  public static final McfrBlockStairs REFINED_JUNGLE_STAIRS = new McfrBlockStairs(REFINED_PLANKS, 3, "refined_jungle_planks", "axe", 0);
  public static final McfrBlockStairs REFINED_ACACIA_STAIRS = new McfrBlockStairs(REFINED_PLANKS, 4, "refined_acacia_planks", "axe", 0);
  public static final McfrBlockStairs REFINED_DARK_OAK_STAIRS = new McfrBlockStairs(REFINED_PLANKS, 5, "refined_dark_oak_planks", "axe", 0);
  public static final McfrBlockStairs ROUGH_SANDSTONE_STAIRS = new McfrBlockStairs(ROUGH_SANDSTONE, 0, "rough_sandstone", "pickaxe", 0);
  public static final McfrBlockStairs YELLOW_STONE_STAIRS = new McfrBlockStairs(Blocks.STONE, 2, "yellow_stone", "pickaxe", 0);
  public static final McfrBlockStairs OCHER_STONE_STAIRS = new McfrBlockStairs(Blocks.STONE, 4, "ocher_stone", "pickaxe", 0);
  public static final McfrBlockStairs YELLOW_COBBLESTONE_STAIRS = new McfrBlockStairs(Blocks.STONE, 1, "yellow_cobblestone", "pickaxe", 0);
  public static final McfrBlockStairs OCHER_COBBLESTONE_STAIRS = new McfrBlockStairs(Blocks.STONE, 3, "ocher_cobblestone", "pickaxe", 0);
  public static final McfrBlockStairs YELLOW_STONEBRICK_STAIRS = new McfrBlockStairs(YELLOW_STONEBRICK, 0, "yellow_stonebrick", "pickaxe", 0);
  public static final McfrBlockStairs YELLOW_MOSSY_STONEBRICK_STAIRS = new McfrBlockStairs(YELLOW_STONEBRICK, 1, "yellow_mossy_stonebrick", "pickaxe", 0);
  public static final McfrBlockStairs YELLOW_CRACKED_STONEBRICK_STAIRS = new McfrBlockStairs(YELLOW_STONEBRICK, 2, "yellow_cracked_stonebrick", "pickaxe", 0);
  public static final McfrBlockStairs OCHER_STONEBRICK_STAIRS = new McfrBlockStairs(OCHER_STONEBRICK, 0, "ocher_stonebrick", "pickaxe", 0);
  public static final McfrBlockStairs OCHER_MOSSY_STONEBRICK_STAIRS = new McfrBlockStairs(OCHER_STONEBRICK, 1, "ocher_mossy_stonebrick", "pickaxe", 0);
  public static final McfrBlockStairs OCHER_CRACKED_STONEBRICK_STAIRS = new McfrBlockStairs(OCHER_STONEBRICK, 2, "ocher_cracked_stonebrick", "pickaxe", 0);
  public static final McfrBlockStairs BRICK_TILES_STAIRS = new McfrBlockStairs(TILES, 0, "brick_tiles", "pickaxe", 0);
  public static final McfrBlockStairs SLATE_TILES_STAIRS = new McfrBlockStairs(TILES, 1, "slate_tiles", "pickaxe", 0);
  public static final McfrBlockStairs LIGHT_CARVED_CLAY_STAIRS = new McfrBlockStairs(CARVED_CLAY, 0, "light_carved_clay", "pickaxe", 0);
  public static final McfrBlockStairs DARK_CARVED_CLAY_STAIRS = new McfrBlockStairs(CARVED_CLAY, 1, "dark_carved_clay", "pickaxe", 0);
  public static final McfrBlockStairs MARBLE_ROUGH_SAND_STAIRS = new McfrBlockStairs(MARBLE, 0, "marble_rough_sand", "pickaxe", 0);
  public static final McfrBlockStairs MARBLE_SMOOTH_SAND_STAIRS = new McfrBlockStairs(MARBLE, 1, "marble_smooth_sand", "pickaxe", 0);
  public static final McfrBlockStairs MARBLE_COLUMN_SAND_STAIRS = new McfrBlockStairs(MARBLE, 2, "marble_column_sand", "pickaxe", 0);
  public static final McfrBlockStairs MARBLE_ROUGH_WHITE_STAIRS = new McfrBlockStairs(MARBLE, 3, "marble_rough_white", "pickaxe", 0);
  public static final McfrBlockStairs MARBLE_SMOOTH_WHITE_STAIRS = new McfrBlockStairs(MARBLE, 4, "marble_smooth_white", "pickaxe", 0);
  public static final McfrBlockStairs MARBLE_COLUMN_WHITE_STAIRS = new McfrBlockStairs(MARBLE, 5, "marble_column_white", "pickaxe", 0);
  public static final McfrBlockStairs MARBLE_ROUGH_BLACK_STAIRS = new McfrBlockStairs(MARBLE, 6, "marble_rough_black", "pickaxe", 0);
  public static final McfrBlockStairs MARBLE_SMOOTH_BLACK_STAIRS = new McfrBlockStairs(MARBLE, 7, "marble_smooth_black", "pickaxe", 0);
  public static final McfrBlockStairs MARBLE_COLUMN_BLACK_STAIRS = new McfrBlockStairs(MARBLE, 8, "marble_column_black", "pickaxe", 0);
  // Pentes
  public static final McfrBlockSlope STONE_SLOPE = new McfrBlockSlope(Blocks.STONE, 0, "stone", "pickaxe", 0);
  public static final McfrBlockSlope COBBLESTONE_SLOPE = new McfrBlockSlope(Blocks.COBBLESTONE, 0, "cobblestone", "pickaxe", 0);
  public static final McfrBlockSlope OAK_PLANKS_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 0, "oak_planks", "axe", 0);
  public static final McfrBlockSlope SPRUCE_PLANKS_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 1, "spruce_planks", "axe", 0);
  public static final McfrBlockSlope BIRCH_PLANKS_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 2, "birch_planks", "axe", 0);
  public static final McfrBlockSlope JUNGLE_PLANKS_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 3, "jungle_planks", "axe", 0);
  public static final McfrBlockSlope ACACIA_PLANKS_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 4, "acacia_planks", "axe", 0);
  public static final McfrBlockSlope DARK_OAK_PLANKS_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 5, "dark_oak_planks", "axe", 0);
  public static final McfrBlockSlope OAK_LOG_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 0, "oak_log", "axe", 0);
  public static final McfrBlockSlope SPRUCE_LOG_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 1, "spruce_log", "axe", 0);
  public static final McfrBlockSlope BIRCH_LOG_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 2, "birch_log", "axe", 0);
  public static final McfrBlockSlope JUNGLE_LOG_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 3, "jungle_log", "axe", 0);
  public static final McfrBlockSlope ACACIA_LOG_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 4, "acacia_log", "axe", 0);
  public static final McfrBlockSlope DARK_OAK_LOG_SLOPE = new McfrBlockSlope(Blocks.PLANKS, 5, "dark_oak_log", "axe", 0);
  public static final McfrBlockLeavesSlope OAK_LEAVES_SLOPE = new McfrBlockLeavesSlope(Blocks.LEAVES, 0, "oak");
  public static final McfrBlockLeavesSlope SPRUCE_LEAVES_SLOPE = new McfrBlockLeavesSlope(Blocks.LEAVES, 1, "spruce");
  public static final McfrBlockLeavesSlope BIRCH_LEAVES_SLOPE = new McfrBlockLeavesSlope(Blocks.LEAVES, 2, "birch");
  public static final McfrBlockLeavesSlope JUNGLE_LEAVES_SLOPE = new McfrBlockLeavesSlope(Blocks.LEAVES, 3, "jungle");
  public static final McfrBlockLeavesSlope ACACIA_LEAVES_SLOPE = new McfrBlockLeavesSlope(Blocks.LEAVES2, 0, "acacia");
  public static final McfrBlockLeavesSlope DARK_OAK_LEAVES_SLOPE = new McfrBlockLeavesSlope(Blocks.LEAVES2, 1, "dark_oak");
  public static final McfrBlockSlope SANDSTONE_SLOPE = new McfrBlockSlope(Blocks.SANDSTONE, 0, "sandstone", "pickaxe", 0);
  public static final McfrBlockSlope CHISELED_SANDSTONE_SLOPE = new McfrBlockSlope(Blocks.SANDSTONE, 1, "chiseled_sandstone", "pickaxe", 0);
  public static final McfrBlockSlope SMOOTH_SANDSTONE_SLOPE = new McfrBlockSlope(Blocks.SANDSTONE, 2, "smooth_sandstone", "pickaxe", 0);
  public static final McfrBlockSlope WHITE_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 0, "white_wool", null, -1);
  public static final McfrBlockSlope ORANGE_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 1, "orange_wool", null, -1);
  public static final McfrBlockSlope MAGENTA_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 2, "magenta_wool", null, -1);
  public static final McfrBlockSlope LIGHT_BLUE_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 3, "light_blue_wool", null, -1);
  public static final McfrBlockSlope YELLOW_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 4, "yellow_wool", null, -1);
  public static final McfrBlockSlope LIME_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 5, "lime_wool", null, -1);
  public static final McfrBlockSlope PINK_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 6, "pink_wool", null, -1);
  public static final McfrBlockSlope GRAY_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 7, "gray_wool", null, -1);
  public static final McfrBlockSlope LIGHT_GRAY_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 8, "light_gray_wool", null, -1);
  public static final McfrBlockSlope CYAN_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 9, "cyan_wool", null, -1);
  public static final McfrBlockSlope PURPLE_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 10, "purple_wool", null, -1);
  public static final McfrBlockSlope BLUE_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 11, "blue_wool", null, -1);
  public static final McfrBlockSlope BROWN_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 12, "brown_wool", null, -1);
  public static final McfrBlockSlope GREEN_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 13, "green_wool", null, -1);
  public static final McfrBlockSlope RED_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 14, "red_wool", null, -1);
  public static final McfrBlockSlope BLACK_WOOL_SLOPE = new McfrBlockSlope(Blocks.WOOL, 15, "black_wool", null, -1);
  public static final McfrBlockSlope GOLDEN_SLOPE = new McfrBlockSlope(Blocks.GOLD_BLOCK, 0, "golden", "pickaxe", 2);
  public static final McfrBlockSlope IRON_SLOPE = new McfrBlockSlope(Blocks.IRON_BLOCK, 0, "iron", "pickaxe", 1);
  public static final McfrBlockSlope BRICK_SLOPE = new McfrBlockSlope(Blocks.BRICK_BLOCK, 0, "brick", "pickaxe", 0);
  public static final McfrBlockSlope MOSSY_COBBLESTONE_SLOPE = new McfrBlockSlope(Blocks.MOSSY_COBBLESTONE, 0, "mossy_cobblestone", "pickaxe", 0);
  public static final McfrBlockSlope STONEBRICK_SLOPE = new McfrBlockSlope(Blocks.STONEBRICK, 0, "stonebrick", "pickaxe", 0);
  public static final McfrBlockSlope MOSSY_STONEBRICK_SLOPE = new McfrBlockSlope(Blocks.STONEBRICK, 1, "mossy_stonebrick", "pickaxe", 0);
  public static final McfrBlockSlope CRACKED_STONEBRICK_SLOPE = new McfrBlockSlope(Blocks.STONEBRICK, 1, "cracked_stonebrick", "pickaxe", 0);
  public static final McfrBlockSlope WHITE_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 0, "white_clay", "pickaxe", 0);
  public static final McfrBlockSlope ORANGE_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 1, "orange_clay", "pickaxe", 0);
  public static final McfrBlockSlope MAGENTA_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 2, "magenta_clay", "pickaxe", 0);
  public static final McfrBlockSlope LIGHT_BLUE_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 3, "light_blue_clay", "pickaxe", 0);
  public static final McfrBlockSlope YELLOW_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 4, "yellow_clay", "pickaxe", 0);
  public static final McfrBlockSlope LIME_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 5, "lime_clay", "pickaxe", 0);
  public static final McfrBlockSlope PINK_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 6, "pink_clay", "pickaxe", 0);
  public static final McfrBlockSlope GRAY_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 7, "gray_clay", "pickaxe", 0);
  public static final McfrBlockSlope LIGHT_GRAY_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 8, "light_gray_clay", "pickaxe", 0);
  public static final McfrBlockSlope CYAN_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 9, "cyan_clay", "pickaxe", 0);
  public static final McfrBlockSlope PURPLE_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 10, "purple_clay", "pickaxe", 0);
  public static final McfrBlockSlope BLUE_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 11, "blue_clay", "pickaxe", 0);
  public static final McfrBlockSlope BROWN_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 12, "brown_clay", "pickaxe", 0);
  public static final McfrBlockSlope GREEN_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 13, "green_clay", "pickaxe", 0);
  public static final McfrBlockSlope RED_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 14, "red_clay", "pickaxe", 0);
  public static final McfrBlockSlope BLACK_CLAY_SLOPE = new McfrBlockSlope(Blocks.STAINED_HARDENED_CLAY, 15, "black_clay", "pickaxe", 0);
  public static final McfrBlockSlope HAY_SLOPE = new McfrBlockSlope(Blocks.HAY_BLOCK, 0, "hay", null, -1);
  public static final McfrBlockSlope APPLE_PLANKS_SLOPE = new McfrBlockSlope(EXOTIC_PLANKS, 0, "apple_planks", "axe", 0);
  public static final McfrBlockSlope CHERRY_PLANKS_SLOPE = new McfrBlockSlope(EXOTIC_PLANKS, 1, "cherry_planks", "axe", 0);
  public static final McfrBlockSlope PALM_PLANKS_SLOPE = new McfrBlockSlope(EXOTIC_PLANKS, 2, "palm_planks", "axe", 0);
  public static final McfrBlockSlope BELUXIER_PLANKS_SLOPE = new McfrBlockSlope(EXOTIC_PLANKS, 3, "beluxier_planks", "axe", 0);
  public static final McfrBlockSlope APPLE_LOG_SLOPE = new McfrBlockSlope(EXOTIC_WOOD, 0, "apple_log", "axe", 0);
  public static final McfrBlockSlope CHERRY_LOG_SLOPE = new McfrBlockSlope(EXOTIC_WOOD, 1, "cherry_log", "axe", 0);
  public static final McfrBlockSlope PALM_LOG_SLOPE = new McfrBlockSlope(EXOTIC_WOOD, 2, "palm_log", "axe", 0);
  public static final McfrBlockSlope BELUXIER_LOG_SLOPE = new McfrBlockSlope(EXOTIC_WOOD, 3, "beluxier_log", "axe", 0);
  public static final McfrBlockLeavesSlope APPLE_LEAVES_SLOPE = new McfrBlockLeavesSlope(EXOTIC_LEAVES, 0, "apple");
  public static final McfrBlockLeavesSlope CHERRY_LEAVES_SLOPE = new McfrBlockLeavesSlope(EXOTIC_LEAVES, 1, "cherry");
  public static final McfrBlockLeavesSlope PALM_LEAVES_SLOPE = new McfrBlockLeavesSlope(EXOTIC_LEAVES, 2, "palm");
  public static final McfrBlockLeavesSlope BELUXIER_LEAVES_SLOPE = new McfrBlockLeavesSlope(EXOTIC_LEAVES, 3, "beluxier");
  public static final McfrBlockSlope REFINED_OAK_SLOPE = new McfrBlockSlope(REFINED_PLANKS, 0, "refined_oak_planks", "axe", 0);
  public static final McfrBlockSlope REFINED_SPRUCE_SLOPE = new McfrBlockSlope(REFINED_PLANKS, 1, "refined_spruce_planks", "axe", 0);
  public static final McfrBlockSlope REFINED_BIRCH_SLOPE = new McfrBlockSlope(REFINED_PLANKS, 2, "refined_birch_planks", "axe", 0);
  public static final McfrBlockSlope REFINED_JUNGLE_SLOPE = new McfrBlockSlope(REFINED_PLANKS, 3, "refined_jungle_planks", "axe", 0);
  public static final McfrBlockSlope REFINED_ACACIA_SLOPE = new McfrBlockSlope(REFINED_PLANKS, 4, "refined_acacia_planks", "axe", 0);
  public static final McfrBlockSlope REFINED_DARK_OAK_SLOPE = new McfrBlockSlope(REFINED_PLANKS, 5, "refined_dark_oak_planks", "axe", 0);
  public static final McfrBlockSlope ROUGH_SANDSTONE_SLOPE = new McfrBlockSlope(ROUGH_SANDSTONE, 0, "rough_sandstone", "pickaxe", 0);
  public static final McfrBlockSlope YELLOW_STONE_SLOPE = new McfrBlockSlope(Blocks.STONE, 2, "yellow_stone", "pickaxe", 0);
  public static final McfrBlockSlope OCHER_STONE_SLOPE = new McfrBlockSlope(Blocks.STONE, 4, "ocher_stone", "pickaxe", 0);
  public static final McfrBlockSlope YELLOW_COBBLESTONE_SLOPE = new McfrBlockSlope(Blocks.STONE, 1, "yellow_cobblestone", "pickaxe", 0);
  public static final McfrBlockSlope OCHER_COBBLESTONE_SLOPE = new McfrBlockSlope(Blocks.STONE, 3, "ocher_cobblestone", "pickaxe", 0);
  public static final McfrBlockSlope YELLOW_STONEBRICK_SLOPE = new McfrBlockSlope(YELLOW_STONEBRICK, 0, "yellow_stonebrick", "pickaxe", 0);
  public static final McfrBlockSlope YELLOW_MOSSY_STONEBRICK_SLOPE = new McfrBlockSlope(YELLOW_STONEBRICK, 1, "yellow_mossy_stonebrick", "pickaxe", 0);
  public static final McfrBlockSlope YELLOW_CRACKED_STONEBRICK_SLOPE = new McfrBlockSlope(YELLOW_STONEBRICK, 2, "yellow_cracked_stonebrick", "pickaxe", 0);
  public static final McfrBlockSlope OCHER_STONEBRICK_SLOPE = new McfrBlockSlope(OCHER_STONEBRICK, 0, "ocher_stonebrick", "pickaxe", 0);
  public static final McfrBlockSlope OCHER_MOSSY_STONEBRICK_SLOPE = new McfrBlockSlope(OCHER_STONEBRICK, 1, "ocher_mossy_stonebrick", "pickaxe", 0);
  public static final McfrBlockSlope OCHER_CRACKED_STONEBRICK_SLOPE = new McfrBlockSlope(OCHER_STONEBRICK, 2, "ocher_cracked_stonebrick", "pickaxe", 0);
  public static final McfrBlockSlope BRICK_TILES_SLOPE = new McfrBlockSlope(TILES, 0, "brick_tiles", "pickaxe", 0);
  public static final McfrBlockSlope SLATE_TILES_SLOPE = new McfrBlockSlope(TILES, 1, "slate_tiles", "pickaxe", 0);
  public static final McfrBlockSlope LIGHT_CARVED_CLAY_SLOPE = new McfrBlockSlope(CARVED_CLAY, 0, "light_carved_clay", "pickaxe", 0);
  public static final McfrBlockSlope DARK_CARVED_CLAY_SLOPE = new McfrBlockSlope(CARVED_CLAY, 1, "dark_carved_clay", "pickaxe", 0);
  // Pyramides
  public static final McfrBlockPyramid STONE_PYRAMID = new McfrBlockPyramid(Blocks.STONE, 0, 1.5f, 10, "stone", "pickaxe", 0);
  public static final McfrBlockPyramid COBBLESTONE_PYRAMID = new McfrBlockPyramid(Blocks.COBBLESTONE, 0, 2, 10, "cobblestone", "pickaxe", 0);
  public static final McfrBlockPyramid OAK_PLANKS_PYRAMID = new McfrBlockPyramid(Blocks.PLANKS, 0, 2, 5, "oak_planks", "axe", 0);
  public static final McfrBlockPyramid SPRUCE_PLANKS_PYRAMID = new McfrBlockPyramid(Blocks.PLANKS, 1, 2, 5, "spruce_planks", "axe", 0);
  public static final McfrBlockPyramid BIRCH_PLANKS_PYRAMID = new McfrBlockPyramid(Blocks.PLANKS, 2, 2, 5, "birch_planks", "axe", 0);
  public static final McfrBlockPyramid JUNGLE_PLANKS_PYRAMID = new McfrBlockPyramid(Blocks.PLANKS, 3, 2, 5, "jungle_planks", "axe", 0);
  public static final McfrBlockPyramid ACACIA_PLANKS_PYRAMID = new McfrBlockPyramid(Blocks.PLANKS, 4, 2, 5, "acacia_planks", "axe", 0);
  public static final McfrBlockPyramid DARK_OAK_PLANKS_PYRAMID = new McfrBlockPyramid(Blocks.PLANKS, 5, 2, 5, "dark_oak_planks", "axe", 0);
  public static final McfrBlockPyramid OAK_LOG_PYRAMID = new McfrBlockPyramid(Blocks.LOG, 0, 2, 5, "oak_log", "axe", 0);
  public static final McfrBlockPyramid SPRUCE_LOG_PYRAMID = new McfrBlockPyramid(Blocks.LOG, 1, 2, 5, "spruce_log", "axe", 0);
  public static final McfrBlockPyramid BIRCH_LOG_PYRAMID = new McfrBlockPyramid(Blocks.LOG, 2, 2, 5, "birch_log", "axe", 0);
  public static final McfrBlockPyramid JUNGLE_LOG_PYRAMID = new McfrBlockPyramid(Blocks.LOG, 3, 2, 5, "jungle_log", "axe", 0);
  public static final McfrBlockPyramid ACACIA_LOG_PYRAMID = new McfrBlockPyramid(Blocks.LOG2, 0, 2, 5, "acacia_log", "axe", 0);
  public static final McfrBlockPyramid DARK_OAK_LOG_PYRAMID = new McfrBlockPyramid(Blocks.LOG2, 1, 2, 5, "dark_oak_log", "axe", 0);
  public static final McfrBlockLeavesPyramid OAK_LEAVES_PYRAMID = new McfrBlockLeavesPyramid(Blocks.LEAVES, 0, "oak");
  public static final McfrBlockLeavesPyramid SPRUCE_LEAVES_PYRAMID = new McfrBlockLeavesPyramid(Blocks.LEAVES, 1, "spruce");
  public static final McfrBlockLeavesPyramid BIRCH_LEAVES_PYRAMID = new McfrBlockLeavesPyramid(Blocks.LEAVES, 2, "birch");
  public static final McfrBlockLeavesPyramid JUNGLE_LEAVES_PYRAMID = new McfrBlockLeavesPyramid(Blocks.LEAVES, 3, "jungle");
  public static final McfrBlockLeavesPyramid ACACIA_LEAVES_PYRAMID = new McfrBlockLeavesPyramid(Blocks.LEAVES2, 0, "acacia");
  public static final McfrBlockLeavesPyramid DARK_OAK_LEAVES_PYRAMID = new McfrBlockLeavesPyramid(Blocks.LEAVES2, 1, "dark_oak");
  public static final McfrBlockPyramid SANDSTONE_PYRAMID = new McfrBlockPyramid(Blocks.SANDSTONE, 0, 0.8f, 0, "sandstone", "pickaxe", 0);
  public static final McfrBlockPyramid CHISELED_SANDSTONE_PYRAMID = new McfrBlockPyramid(Blocks.SANDSTONE, 1, 0.8f, 0, "chiseled_sandstone", "pickaxe", 0);
  public static final McfrBlockPyramid SMOOTH_SANDSTONE_PYRAMID = new McfrBlockPyramid(Blocks.SANDSTONE, 2, 0.8f, 0, "smooth_sandstone", "pickaxe", 0);
  public static final McfrBlockPyramid WHITE_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 0, 0.8f, 0, "white_wool", null, -1);
  public static final McfrBlockPyramid ORANGE_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 1, 0.8f, 0, "orange_wool", null, -1);
  public static final McfrBlockPyramid MAGENTA_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 2, 0.8f, 0, "magenta_wool", null, -1);
  public static final McfrBlockPyramid LIGHT_BLUE_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 3, 0.8f, 0, "light_blue_wool", null, -1);
  public static final McfrBlockPyramid YELLOW_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 4, 0.8f, 0, "yellow_wool", null, -1);
  public static final McfrBlockPyramid LIME_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 5, 0.8f, 0, "lime_wool", null, -1);
  public static final McfrBlockPyramid PINK_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 6, 0.8f, 0, "pink_wool", null, -1);
  public static final McfrBlockPyramid GRAY_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 7, 0.8f, 0, "gray_wool", null, -1);
  public static final McfrBlockPyramid LIGHT_GRAY_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 8, 0.8f, 0, "light_gray_wool", null, -1);
  public static final McfrBlockPyramid CYAN_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 9, 0.8f, 0, "cyan_wool", null, -1);
  public static final McfrBlockPyramid PURPLE_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 10, 0.8f, 0, "purple_wool", null, -1);
  public static final McfrBlockPyramid BLUE_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 11, 0.8f, 0, "blue_wool", null, -1);
  public static final McfrBlockPyramid BROWN_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 12, 0.8f, 0, "brown_wool", null, -1);
  public static final McfrBlockPyramid GREEN_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 13, 0.8f, 0, "green_wool", null, -1);
  public static final McfrBlockPyramid RED_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 14, 0.8f, 0, "red_wool", null, -1);
  public static final McfrBlockPyramid BLACK_WOOL_PYRAMID = new McfrBlockPyramid(Blocks.WOOL, 15, 0.8f, 0, "black_wool", null, -1);
  public static final McfrBlockPyramid GOLDEN_PYRAMID = new McfrBlockPyramid(Blocks.GOLD_BLOCK, 0, 3, 10, "golden", "pickaxe", 2);
  public static final McfrBlockPyramid IRON_PYRAMID = new McfrBlockPyramid(Blocks.IRON_BLOCK, 0, 5, 10, "iron", "pickaxe", 1);
  public static final McfrBlockPyramid BRICK_PYRAMID = new McfrBlockPyramid(Blocks.BRICK_BLOCK, 0, 2, 10, "brick", "pickaxe", 0);
  public static final McfrBlockPyramid MOSSY_COBBLESTONE_PYRAMID = new McfrBlockPyramid(Blocks.MOSSY_COBBLESTONE, 0, 2, 10, "mossy_cobblestone", "pickaxe", 0);
  public static final McfrBlockPyramid STONEBRICK_PYRAMID = new McfrBlockPyramid(Blocks.STONEBRICK, 0, 1.5f, 10, "stonebrick", "pickaxe", 0);
  public static final McfrBlockPyramid MOSSY_STONEBRICK_PYRAMID = new McfrBlockPyramid(Blocks.STONEBRICK, 1, 1.5f, 10, "mossy_stonebrick", "pickaxe", 0);
  public static final McfrBlockPyramid CRACKED_STONEBRICK_PYRAMID = new McfrBlockPyramid(Blocks.STONEBRICK, 2, 1.5f, 10, "cracked_stonebrick", "pickaxe", 0);
  public static final McfrBlockPyramid WHITE_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 0, 1.25f, 7, "white_clay", "pickaxe", 0);
  public static final McfrBlockPyramid ORANGE_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 1, 1.25f, 7, "orange_clay", "pickaxe", 0);
  public static final McfrBlockPyramid MAGENTA_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 2, 1.25f, 7, "magenta_clay", "pickaxe", 0);
  public static final McfrBlockPyramid LIGHT_BLUE_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 3, 1.25f, 7, "light_blue_clay", "pickaxe", 0);
  public static final McfrBlockPyramid YELLOW_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 4, 1.25f, 7, "yellow_clay", "pickaxe", 0);
  public static final McfrBlockPyramid LIME_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 5, 1.25f, 7, "lime_clay", "pickaxe", 0);
  public static final McfrBlockPyramid PINK_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 6, 1.25f, 7, "pink_clay", "pickaxe", 0);
  public static final McfrBlockPyramid GRAY_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 7, 1.25f, 7, "gray_clay", "pickaxe", 0);
  public static final McfrBlockPyramid LIGHT_GRAY_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 8, 1.25f, 7, "light_gray_clay", "pickaxe", 0);
  public static final McfrBlockPyramid CYAN_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 9, 1.25f, 7, "cyan_clay", "pickaxe", 0);
  public static final McfrBlockPyramid PURPLE_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 10, 1.25f, 7, "purple_clay", "pickaxe", 0);
  public static final McfrBlockPyramid BLUE_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 11, 1.25f, 7, "blue_clay", "pickaxe", 0);
  public static final McfrBlockPyramid BROWN_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 12, 1.25f, 7, "brown_clay", "pickaxe", 0);
  public static final McfrBlockPyramid GREEN_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 13, 1.25f, 7, "green_clay", "pickaxe", 0);
  public static final McfrBlockPyramid RED_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 14, 1.25f, 7, "red_clay", "pickaxe", 0);
  public static final McfrBlockPyramid BLACK_CLAY_PYRAMID = new McfrBlockPyramid(Blocks.STAINED_HARDENED_CLAY, 15, 1.25f, 7, "black_clay", "pickaxe", 0);
  public static final McfrBlockPyramid HAY_PYRAMID = new McfrBlockPyramid(Blocks.HAY_BLOCK, 0, 0.5f, 0, "hay", null, -1);
  public static final McfrBlockPyramid APPLE_PLANKS_PYRAMID = new McfrBlockPyramid(EXOTIC_PLANKS, 0, 2, 5, "apple_planks", "axe", 0);
  public static final McfrBlockPyramid CHERRY_PLANKS_PYRAMID = new McfrBlockPyramid(EXOTIC_PLANKS, 1, 2, 5, "cherry_planks", "axe", 0);
  public static final McfrBlockPyramid PALM_PLANKS_PYRAMID = new McfrBlockPyramid(EXOTIC_PLANKS, 2, 2, 5, "palm_planks", "axe", 0);
  public static final McfrBlockPyramid BELUXIER_PLANKS_PYRAMID = new McfrBlockPyramid(EXOTIC_PLANKS, 3, 2, 5, "beluxier_planks", "axe", 0);
  public static final McfrBlockPyramid APPLE_LOG_PYRAMID = new McfrBlockPyramid(EXOTIC_WOOD, 0, 2, 5, "apple_log", "axe", 0);
  public static final McfrBlockPyramid CHERRY_LOG_PYRAMID = new McfrBlockPyramid(EXOTIC_WOOD, 1, 2, 5, "cherry_log", "axe", 0);
  public static final McfrBlockPyramid PALM_LOG_PYRAMID = new McfrBlockPyramid(EXOTIC_WOOD, 2, 2, 5, "palm_log", "axe", 0);
  public static final McfrBlockPyramid BELUXIER_LOG_PYRAMID = new McfrBlockPyramid(EXOTIC_WOOD, 3, 2, 5, "beluxier_log", "axe", 0);
  public static final McfrBlockLeavesPyramid APPLE_LEAVES_PYRAMID = new McfrBlockLeavesPyramid(EXOTIC_LEAVES, 0, "apple");
  public static final McfrBlockLeavesPyramid CHERRY_LEAVES_PYRAMID = new McfrBlockLeavesPyramid(EXOTIC_LEAVES, 1, "cherry");
  public static final McfrBlockLeavesPyramid PALM_LEAVES_PYRAMID = new McfrBlockLeavesPyramid(EXOTIC_LEAVES, 2, "palm");
  public static final McfrBlockLeavesPyramid BELUXIER_LEAVES_PYRAMID = new McfrBlockLeavesPyramid(EXOTIC_LEAVES, 3, "beluxier");
  public static final McfrBlockPyramid REFINED_OAK_PYRAMID = new McfrBlockPyramid(REFINED_PLANKS, 0, 2, 5, "refined_oak_planks", "axe", 0);
  public static final McfrBlockPyramid REFINED_SPRUCE_PYRAMID = new McfrBlockPyramid(REFINED_PLANKS, 1, 2, 5, "refined_spruce_planks", "axe", 0);
  public static final McfrBlockPyramid REFINED_BIRCH_PYRAMID = new McfrBlockPyramid(REFINED_PLANKS, 2, 2, 5, "refined_birch_planks", "axe", 0);
  public static final McfrBlockPyramid REFINED_JUNGLE_PYRAMID = new McfrBlockPyramid(REFINED_PLANKS, 3, 2, 5, "refined_jungle_planks", "axe", 0);
  public static final McfrBlockPyramid REFINED_ACACIA_PYRAMID = new McfrBlockPyramid(REFINED_PLANKS, 4, 2, 5, "refined_acacia_planks", "axe", 0);
  public static final McfrBlockPyramid REFINED_DARK_OAK_PYRAMID = new McfrBlockPyramid(REFINED_PLANKS, 5, 2, 5, "refined_dark_oak_planks", "axe", 0);
  public static final McfrBlockPyramid ROUGH_SANDSTONE_PYRAMID = new McfrBlockPyramid(ROUGH_SANDSTONE, 0, 0.8f, 0, "rough_sandstone", "pickaxe", 0);
  public static final McfrBlockPyramid YELLOW_STONE_PYRAMID = new McfrBlockPyramid(Blocks.STONE, 2, 1.5f, 10, "yellow_stone", "pickaxe", 0);
  public static final McfrBlockPyramid OCHER_STONE_PYRAMID = new McfrBlockPyramid(Blocks.STONE, 4, 1.5f, 10, "ocher_stone", "pickaxe", 0);
  public static final McfrBlockPyramid YELLOW_COBBLESTONE_PYRAMID = new McfrBlockPyramid(Blocks.STONE, 1, 1.5f, 10, "yellow_cobblestone", "pickaxe", 0);
  public static final McfrBlockPyramid OCHER_COBBLESTONE_PYRAMID = new McfrBlockPyramid(Blocks.STONE, 3, 1.5f, 10, "ocher_cobblestone", "pickaxe", 0);
  public static final McfrBlockPyramid YELLOW_STONEBRICK_PYRAMID = new McfrBlockPyramid(YELLOW_STONEBRICK, 0, 1.5f, 10, "yellow_stonebrick", "pickaxe", 0);
  public static final McfrBlockPyramid YELLOW_MOSSY_STONEBRICK_PYRAMID = new McfrBlockPyramid(YELLOW_STONEBRICK, 1, 1.5f, 10, "yellow_mossy_stonebrick", "pickaxe", 0);
  public static final McfrBlockPyramid YELLOW_CRACKED_STONEBRICK_PYRAMID = new McfrBlockPyramid(YELLOW_STONEBRICK, 2, 1.5f, 10, "yellow_cracked_stonebrick", "pickaxe", 0);
  public static final McfrBlockPyramid OCHER_STONEBRICK_PYRAMID = new McfrBlockPyramid(OCHER_STONEBRICK, 0, 1.5f, 10, "ocher_stonebrick", "pickaxe", 0);
  public static final McfrBlockPyramid OCHER_MOSSY_STONEBRICK_PYRAMID = new McfrBlockPyramid(OCHER_STONEBRICK, 1, 1.5f, 10, "ocher_mossy_stonebrick", "pickaxe", 0);
  public static final McfrBlockPyramid OCHER_CRACKED_STONEBRICK_PYRAMID = new McfrBlockPyramid(OCHER_STONEBRICK, 2, 1.5f, 10, "ocher_cracked_stonebrick", "pickaxe", 0);
  public static final McfrBlockPyramid BRICK_TILES_PYRAMID = new McfrBlockPyramid(TILES, 0, 1.5f, 10, "brick_tiles", "pickaxe", 0);
  public static final McfrBlockPyramid SLATE_TILES_PYRAMID = new McfrBlockPyramid(TILES, 1, 1.5f, 10, "slate_tiles", "pickaxe", 0);
  public static final McfrBlockPyramid LIGHT_CARVED_CLAY_PYRAMID = new McfrBlockPyramid(CARVED_CLAY, 0, 1.5f, 10, "light_carved_clay", "pickaxe", 0);
  public static final McfrBlockPyramid DARK_CARVED_CLAY_PYRAMID = new McfrBlockPyramid(CARVED_CLAY, 1, 1.5f, 10, "dark_carved_clay", "pickaxe", 0);
  // Trappes solides
  public static final BlockStrongTrapdoor STRONG_OAK_TRAPDOOR = new BlockStrongTrapdoor(BlockPlanks.EnumType.OAK);
  public static final BlockStrongTrapdoor STRONG_SPRUCE_TRAPDOOR = new BlockStrongTrapdoor(BlockPlanks.EnumType.SPRUCE);
  public static final BlockStrongTrapdoor STRONG_BIRCH_TRAPDOOR = new BlockStrongTrapdoor(BlockPlanks.EnumType.BIRCH);
  public static final BlockStrongTrapdoor STRONG_JUNGLE_TRAPDOOR = new BlockStrongTrapdoor(BlockPlanks.EnumType.JUNGLE);
  public static final BlockStrongTrapdoor STRONG_ACACIA_TRAPDOOR = new BlockStrongTrapdoor(BlockPlanks.EnumType.ACACIA);
  public static final BlockStrongTrapdoor STRONG_DARK_OAK_TRAPDOOR = new BlockStrongTrapdoor(BlockPlanks.EnumType.DARK_OAK);
  // Trappes artisan
  public static final BlockCraftsmanTrapdoor CRAFTSMAN_OAK_TRAPDOOR = new BlockCraftsmanTrapdoor(BlockPlanks.EnumType.OAK);
  public static final BlockCraftsmanTrapdoor CRAFTSMAN_SPRUCE_TRAPDOOR = new BlockCraftsmanTrapdoor(BlockPlanks.EnumType.SPRUCE);
  public static final BlockCraftsmanTrapdoor CRAFTSMAN_BIRCH_TRAPDOOR = new BlockCraftsmanTrapdoor(BlockPlanks.EnumType.BIRCH);
  public static final BlockCraftsmanTrapdoor CRAFTSMAN_JUNGLE_TRAPDOOR = new BlockCraftsmanTrapdoor(BlockPlanks.EnumType.JUNGLE);
  public static final BlockCraftsmanTrapdoor CRAFTSMAN_ACACIA_TRAPDOOR = new BlockCraftsmanTrapdoor(BlockPlanks.EnumType.ACACIA);
  public static final BlockCraftsmanTrapdoor CRAFTSMAN_DARK_OAK_TRAPDOOR = new BlockCraftsmanTrapdoor(BlockPlanks.EnumType.DARK_OAK);
  // Porte solide
  public static final BlockStrongOakDoor STRONG_OAK_DOOR = new BlockStrongOakDoor();
  // Portes artisant
  public static final BlockCraftsmanOakDoor CRAFTSMAN_OAK_DOOR = new BlockCraftsmanOakDoor();
  public static final BlockCraftsmanSpruceDoor CRAFTSMAN_SPRUCE_DOOR = new BlockCraftsmanSpruceDoor();
  public static final BlockCraftsmanBirchDoor CRAFTSMAN_BIRCH_DOOR = new BlockCraftsmanBirchDoor();
  public static final BlockCraftsmanJungleDoor CRAFTSMAN_JUNGLE_DOOR = new BlockCraftsmanJungleDoor();
  public static final BlockCraftsmanAcaciaDoor CRAFTSMAN_ACACIA_DOOR = new BlockCraftsmanAcaciaDoor();
  public static final BlockCraftsmanDarkOakDoor CRAFTSMAN_DARK_OAK_DOOR = new BlockCraftsmanDarkOakDoor();
  // Portillons
  public static final McfrBlockFenceGate REFINED_OAK_GATE = new McfrBlockFenceGate("refined_oak");
  public static final McfrBlockFenceGate REFINED_SPRUCE_GATE = new McfrBlockFenceGate("refined_spruce");
  public static final McfrBlockFenceGate REFINED_BIRCH_GATE = new McfrBlockFenceGate("refined_birch");
  public static final McfrBlockFenceGate REFINED_JUNGLE_GATE = new McfrBlockFenceGate("refined_jungle");
  public static final McfrBlockFenceGate REFINED_ACACIA_GATE = new McfrBlockFenceGate("refined_acacia");
  public static final McfrBlockFenceGate REFINED_DARK_OAK_GATE = new McfrBlockFenceGate("refined_dark_oak");
  // Barrières
  public static final McfrBlockFence REFINED_OAK_FENCE = new McfrBlockFence("refined_oak", 2, 5, Material.WOOD, SoundType.WOOD, "axe", 0);
  public static final McfrBlockFence REFINED_SPRUCE_FENCE = new McfrBlockFence("refined_spruce", 2, 5, Material.WOOD, SoundType.WOOD, "axe", 0);
  public static final McfrBlockFence REFINED_BIRCH_FENCE = new McfrBlockFence("refined_birch", 2, 5, Material.WOOD, SoundType.WOOD, "axe", 0);
  public static final McfrBlockFence REFINED_JUNGLE_FENCE = new McfrBlockFence("refined_jungle", 2, 5, Material.WOOD, SoundType.WOOD, "axe", 0);
  public static final McfrBlockFence REFINED_ACACIA_FENCE = new McfrBlockFence("refined_acacia", 2, 5, Material.WOOD, SoundType.WOOD, "axe", 0);
  public static final McfrBlockFence REFINED_DARK_OAK_FENCE = new McfrBlockFence("refined_dark_oak", 2, 5, Material.WOOD, SoundType.WOOD, "axe", 0);
  public static final McfrBlockFence APPLE_WOOD_FENCE = new McfrBlockFence("apple_wood", 2, 5, Material.WOOD, SoundType.WOOD, "axe", 0);
  public static final McfrBlockFence CHERRY_WOOD_FENCE = new McfrBlockFence("cherry_wood", 2, 5, Material.WOOD, SoundType.WOOD, "axe", 0);
  public static final McfrBlockFence PALM_FENCE = new McfrBlockFence("palm", 2, 5, Material.WOOD, SoundType.WOOD, "axe", 0);
  public static final McfrBlockFence BELUXIER_FENCE = new McfrBlockFence("beluxier", 2, 5, Material.WOOD, SoundType.WOOD, "axe", 0);
  // Murets
  public static final BlockWoodenWall WOODEN_WALL = new BlockWoodenWall();
  public static final BlockWoodenWall2 WOODEN_WALL2 = new BlockWoodenWall2();
  public static final BlockStoneWall STONE_WALL = new BlockStoneWall();
  public static final BlockMarbleWall MARBLE_WALL = new BlockMarbleWall();
  // Lits
  public static final BlockHayBed HAY_BED = new BlockHayBed();
  public static final BlockNormalBed NORMAL_BED = new BlockNormalBed();
  public static final BlockStoneBed STONE_BED = new BlockStoneBed();
  public static final BlockSleepingBag SLEEPING_BAG = new BlockSleepingBag();

  public static final McfrBlockCarpet CARPET = new McfrBlockCarpet();
  public static final BlockMoucharabieh MOUCHARABIEH = new BlockMoucharabieh();
  public static final BlockMoucharabiehPane MOUCHARABIEH_PANE = new BlockMoucharabiehPane();
  // Échelles
  public static final BlockHangedLadder ROPE_LADDER = new BlockHangedLadder("rope", SoundType.CLOTH, 2.5f);
  public static final BlockHangedLadder CHAIN_LADDER = new BlockHangedLadder("chain", SoundType.METAL, 9);
  // Cordes
  public static final BlockRope FLOOR_ROPE = new BlockRope();
  public static final BlockChain CHAIN = new BlockChain("chain", Material.IRON, SoundType.METAL, 1);
  public static final BlockChain ROPE = new BlockChain("rope", Material.CLOTH, SoundType.CLOTH, 0.5f);
  public static final BlockRopeAttach ROPE_ATTACH = new BlockRopeAttach();

  public static final BlockFeathers FEATHERS = new BlockFeathers();
  /** Cailloux */
  public static final BlockPebbles PEBBLES = new BlockPebbles();
  public static final BlockCoins COINS = new BlockCoins();
  // Éclairage
  public static final BlockSimpleCandle CANDLE = new BlockSimpleCandle();
  public static final BlockTripleCandle TRIPLE_CANDLE = new BlockTripleCandle();
  /** Lustre */
  public static final BlockChandelier CHANDELIER = new BlockChandelier(false);
  /** Grand lustre */
  public static final BlockChandelier LARGE_CHANDELIER = new BlockChandelier(true);
  public static final BlockLargeTorch LARGE_TORCH = new BlockLargeTorch();
  /** Braséro */
  public static final BlockBrazier BRAZIER = new BlockBrazier(false);
  public static final BlockBrazier LIT_BRAZIER = new BlockBrazier(true);

  public static final McfrBlockOrientable WOODEN_LAMP = new McfrBlockOrientable("wooden_lamp", Material.WOOD, SoundType.WOOD, 2, 5, "axe", 0, CreativeTabs.BUILDING_BLOCKS);
  // Lanternes
  public static final BlockLantern WHITE_LANTERN = new BlockLantern(EnumLanternColor.WHITE, false);
  public static final BlockLantern ORANGE_LANTERN = new BlockLantern(EnumLanternColor.ORANGE, false);
  public static final BlockLantern YELLOW_LANTERN = new BlockLantern(EnumLanternColor.YELLOW, false);
  public static final BlockLantern PURPLE_LANTERN = new BlockLantern(EnumLanternColor.PURPLE, false);
  public static final BlockLantern BLUE_LANTERN = new BlockLantern(EnumLanternColor.BLUE, false);
  public static final BlockLantern GREEN_LANTERN = new BlockLantern(EnumLanternColor.GREEN, false);
  public static final BlockLantern RED_LANTERN = new BlockLantern(EnumLanternColor.RED, false);

  public static final BlockLantern WHITE_PAPER_LANTERN = new BlockLantern(EnumLanternColor.WHITE, true);
  public static final BlockLantern ORANGE_PAPER_LANTERN = new BlockLantern(EnumLanternColor.ORANGE, true);
  public static final BlockLantern YELLOW_PAPER_LANTERN = new BlockLantern(EnumLanternColor.YELLOW, true);
  public static final BlockLantern PURPLE_PAPER_LANTERN = new BlockLantern(EnumLanternColor.PURPLE, true);
  public static final BlockLantern BLUE_PAPER_LANTERN = new BlockLantern(EnumLanternColor.BLUE, true);
  public static final BlockLantern GREEN_PAPER_LANTERN = new BlockLantern(EnumLanternColor.GREEN, true);
  public static final BlockLantern RED_PAPER_LANTERN = new BlockLantern(EnumLanternColor.RED, true);

  // Panneaux
  public static final BlockWallNote WALL_NOTE = new BlockWallNote();
  public static final BlockTombstone TOMBSTONE = new BlockTombstone();
  public static final BlockStandingNormalSign STANDING_SIGN = new BlockStandingNormalSign();
  public static final BlockWallNormalSign WALL_SIGN = new BlockWallNormalSign();
  public static final BlockSuspendedNormalSign SUSPENDED_SIGN = new BlockSuspendedNormalSign();
  public static final BlockStandingPaperSign STANDING_PAPER_SIGN = new BlockStandingPaperSign();
  public static final BlockWallPaperSign WALL_PAPER_SIGN = new BlockWallPaperSign();
  public static final BlockSuspendedPaperSign SUSPENDED_PAPER_SIGN = new BlockSuspendedPaperSign();
  public static final BlockStandingOrpSign STANDING_ORP_SIGN = new BlockStandingOrpSign();
  public static final BlockWallOrpSign WALL_ORP_SIGN = new BlockWallOrpSign();
  // Vitrines/stands
  public static final BlockArmorStand ARMOR_STAND = new BlockArmorStand();
  public static final BlockWeaponsStand WEAPONS_STAND = new BlockWeaponsStand();
  /** Vitrine */
  public static final BlockShowcase SHOWCASE = new BlockShowcase();
  // Chaises
  public static final BlockWoodenChair OAK_CHAIR = new BlockWoodenChair(BlockPlanks.EnumType.OAK, "chair");
  public static final BlockWoodenChair SPRUCE_CHAIR = new BlockWoodenChair(BlockPlanks.EnumType.SPRUCE, "chair");
  public static final BlockWoodenChair BIRCH_CHAIR = new BlockWoodenChair(BlockPlanks.EnumType.BIRCH, "chair");
  public static final BlockWoodenChair JUNGLE_CHAIR = new BlockWoodenChair(BlockPlanks.EnumType.JUNGLE, "chair");
  public static final BlockWoodenChair ACACIA_CHAIR = new BlockWoodenChair(BlockPlanks.EnumType.ACACIA, "chair");
  public static final BlockWoodenChair DARK_OAK_CHAIR = new BlockWoodenChair(BlockPlanks.EnumType.DARK_OAK, "chair");
  // Tabourets
  public static final BlockWoodenChair OAK_STOOL = new BlockStool(BlockPlanks.EnumType.OAK, false);
  public static final BlockWoodenChair SPRUCE_STOOL = new BlockStool(BlockPlanks.EnumType.SPRUCE, false);
  public static final BlockWoodenChair BIRCH_STOOL = new BlockStool(BlockPlanks.EnumType.BIRCH, false);
  public static final BlockWoodenChair JUNGLE_STOOL = new BlockStool(BlockPlanks.EnumType.JUNGLE, false);
  public static final BlockWoodenChair ACACIA_STOOL = new BlockStool(BlockPlanks.EnumType.ACACIA, false);
  public static final BlockWoodenChair DARK_OAK_STOOL = new BlockStool(BlockPlanks.EnumType.DARK_OAK, false);
  public static final BlockWoodenChair TALL_OAK_STOOL = new BlockStool(BlockPlanks.EnumType.OAK, true);
  public static final BlockWoodenChair TALL_SPRUCE_STOOL = new BlockStool(BlockPlanks.EnumType.SPRUCE, true);
  public static final BlockWoodenChair TALL_BIRCH_STOOL = new BlockStool(BlockPlanks.EnumType.BIRCH, true);
  public static final BlockWoodenChair TALL_JUNGLE_STOOL = new BlockStool(BlockPlanks.EnumType.JUNGLE, true);
  public static final BlockWoodenChair TALL_ACACIA_STOOL = new BlockStool(BlockPlanks.EnumType.ACACIA, true);
  public static final BlockWoodenChair TALL_DARK_OAK_STOOL = new BlockStool(BlockPlanks.EnumType.DARK_OAK, true);
  // Fauteuils
  public static final BlockChair WOODEN_ARMCHAIR = new BlockArmChair("wooden", Material.WOOD, SoundType.WOOD, 2, 5, "axe", 0);
  public static final BlockChair STONE_ARMCHAIR = new BlockArmChair("stone", Material.ROCK, SoundType.STONE, 2.5f, 5, "pickaxe", 0);
  // Bancs
  public static final BlockBench OAK_BENCH = new BlockBench(BlockPlanks.EnumType.OAK);
  public static final BlockBench SPRUCE_BENCH = new BlockBench(BlockPlanks.EnumType.SPRUCE);
  public static final BlockBench BIRCH_BENCH = new BlockBench(BlockPlanks.EnumType.BIRCH);
  public static final BlockBench JUNGLE_BENCH = new BlockBench(BlockPlanks.EnumType.JUNGLE);
  public static final BlockBench ACACIA_BENCH = new BlockBench(BlockPlanks.EnumType.ACACIA);
  public static final BlockBench DARK_OAK_BENCH = new BlockBench(BlockPlanks.EnumType.DARK_OAK);
  // Tables
  public static final BlockTable TABLE = new BlockTable("normal");
  /** Table basse */
  public static final BlockTable END_TABLE = new BlockTable("end", new AxisAlignedBB(0, 0, 0, 1, 0.5f, 1));
  public static final BlockTableWithFoot TABLE_WITH_FOOT = new BlockTableWithFoot();
  // Étagères
  public static final BlockWoodenShelf OAK_SHELF = new BlockWoodenShelf(BlockPlanks.EnumType.OAK);
  public static final BlockWoodenShelf SPRUCE_SHELF = new BlockWoodenShelf(BlockPlanks.EnumType.SPRUCE);
  public static final BlockWoodenShelf BIRCH_SHELF = new BlockWoodenShelf(BlockPlanks.EnumType.BIRCH);
  public static final BlockWoodenShelf JUNGLE_SHELF = new BlockWoodenShelf(BlockPlanks.EnumType.JUNGLE);
  public static final BlockWoodenShelf ACACIA_SHELF = new BlockWoodenShelf(BlockPlanks.EnumType.ACACIA);
  public static final BlockWoodenShelf DARK_OAK_SHELF = new BlockWoodenShelf(BlockPlanks.EnumType.DARK_OAK);
  public static final BlockShelf STONE_SHELF = new BlockShelf("stone", Material.ROCK, SoundType.STONE, 2, 4, "pickaxe", 0);
  // Équerres
  public static final BlockSupport SUPPORT = new BlockSupport(false);
  public static final BlockSupport LONG_SUPPORT = new BlockSupport(true);

  public static final BlockChessboard CHESSBOARD = new BlockChessboard();
  // Nourriture
  public static final McfrBlockCake CHOCOLATE_CAKE = new McfrBlockCake("chocolate");
  // Redstone
  public static final BlockLongRedstoneRepeater LONG_REPEATER_OFF = new BlockLongRedstoneRepeater(false);
  public static final BlockLongRedstoneRepeater LONG_REPEATER_ON = new BlockLongRedstoneRepeater(true);

  /**
   * Initialise tous les blocs.
   */
  public static void init() {
    Blocks.MONSTER_EGG.setCreativeTab(null);
    Blocks.ANVIL.setCreativeTab(null);
    WOODEN_LAMP.setLightLevel(0.938f);
    // TEMP
    OAK_CHAIR.setCreativeTab(null);
    SPRUCE_CHAIR.setCreativeTab(null);
    BIRCH_CHAIR.setCreativeTab(null);
    JUNGLE_CHAIR.setCreativeTab(null);
    ACACIA_CHAIR.setCreativeTab(null);
    DARK_OAK_CHAIR.setCreativeTab(null);

    register(CHANGER);

    register(PULSOR);

    register(SMOKE_GENERATOR);
    register(CAMPFIRE);
    register(LIT_CAMPFIRE);

    register(ANVIL);
    register(STOVE);
    register(LIT_STOVE);
    register(BELLOWS);

    register(OLD_HAY);
    register(REFINED_GOLD_BLOCK);
    register(REFINED_IRON_BLOCK);
    registerVariants(REFINED_PLANKS);
    register(ROUGH_SANDSTONE);
    registerVariants(YELLOW_STONEBRICK);
    registerVariants(OCHER_STONEBRICK);
    register(DAUB);
    registerVariants(TIMBERED_BLOCK);
    registerVariants(TILES);
    registerVariants(MARBLE);
    registerVariants(MARBLE_COLUMN);
    registerVariants(CARVED_CLAY);

    register(BARLEY);
    register(VINE_BASE);
    register(VINE_TOP);
    register(SUGAR_CANES);
    register(HEMP_BASE);
    register(HEMP_TOP);
    register(FERTILIZER);

    register(LOOM);
    register(TANNING_RACK);
    register(LARGE_WORKBENCH);
    register(CIRCULAR_SAW);

    registerVariants(EXOTIC_PLANKS);
    registerVariants(EXOTIC_SAPLING);
    registerVariants(EXOTIC_WOOD);
    registerVariants(EXOTIC_LEAVES);

    registerVariants(STONE_STALACTITE);
    registerVariants(ICICLE);
    register(BEEHIVE);
    register(GLOWING_VINE);
    register(GLOWING_MUSHROOM);
    registerVariants(WILD_GRASS);
    registerVariants(SAND_BUSH);
    register(REEDS);

    registerVariants(MUSHROOM_CAP);
    register(SPORES);

    register(EMPTY_BARREL);
    register(BEER_BARREL);
    register(CIDER_BARREL);
    register(WINE_BARREL);
    register(RUM_BARREL);

    register(LITTLE_CHEST);
    register(CRATE);
    register(FOOD_CRATE);
    register(PALLET);
    registerVariants(BOOKSHELF);

    registerSlab(HAY_SLAB, DOUBLE_HAY_SLAB, new ItemHaySlab(HAY_SLAB, HAY_SLAB, DOUBLE_HAY_SLAB));
    registerSlab(REFINED_PLANKS_SLAB, DOUBLE_REFINED_PLANKS_SLAB, new ItemRefinedPlanksSlab(REFINED_PLANKS_SLAB, REFINED_PLANKS_SLAB, DOUBLE_REFINED_PLANKS_SLAB));
    registerSlab(EXOTIC_WOOD_SLAB, DOUBLE_EXOTIC_WOOD_SLAB, new ItemExoticWoodSlab(EXOTIC_WOOD_SLAB, EXOTIC_WOOD_SLAB, DOUBLE_EXOTIC_WOOD_SLAB));
    registerSlab(METAL_SLAB, DOUBLE_METAL_SLAB, new ItemMetalSlab(METAL_SLAB, METAL_SLAB, DOUBLE_METAL_SLAB));
    registerSlab(STONE_SLAB, DOUBLE_STONE_SLAB, new ItemStoneSlab(STONE_SLAB, STONE_SLAB, DOUBLE_STONE_SLAB));
    registerSlab(STONEBRICK_SLAB, DOUBLE_STONEBRICK_SLAB, new ItemStonebrickSlab(STONEBRICK_SLAB, STONEBRICK_SLAB, DOUBLE_STONEBRICK_SLAB));
    registerSlab(MARBLE_SLAB, DOUBLE_MARBLE_SLAB, new ItemMarbleSlab(MARBLE_SLAB, MARBLE_SLAB, DOUBLE_MARBLE_SLAB));
    registerSlab(MARBLE_SLAB2, DOUBLE_MARBLE_SLAB2, new ItemMarbleSlab2(MARBLE_SLAB2, MARBLE_SLAB2, DOUBLE_MARBLE_SLAB2));

    register(STONE_STAIRS);
    register(OAK_LOG_STAIRS);
    register(SPRUCE_LOG_STAIRS);
    register(BIRCH_LOG_STAIRS);
    register(JUNGLE_LOG_STAIRS);
    register(ACACIA_LOG_STAIRS);
    register(DARK_OAK_LOG_STAIRS);
    register(CHISELED_SANDSTONE_STAIRS);
    register(SMOOTH_SANDSTONE_STAIRS);
    register(WHITE_WOOL_STAIRS);
    register(ORANGE_WOOL_STAIRS);
    register(MAGENTA_WOOL_STAIRS);
    register(LIGHT_BLUE_WOOL_STAIRS);
    register(YELLOW_WOOL_STAIRS);
    register(LIME_WOOL_STAIRS);
    register(PINK_WOOL_STAIRS);
    register(GRAY_WOOL_STAIRS);
    register(LIGHT_GRAY_WOOL_STAIRS);
    register(CYAN_WOOL_STAIRS);
    register(PURPLE_WOOL_STAIRS);
    register(BLUE_WOOL_STAIRS);
    register(BROWN_WOOL_STAIRS);
    register(GREEN_WOOL_STAIRS);
    register(RED_WOOL_STAIRS);
    register(BLACK_WOOL_STAIRS);
    register(GOLDEN_STAIRS);
    register(IRON_STAIRS);
    register(MOSSY_COBBLESTONE_STAIRS);
    register(MOSSY_STONEBRICK_STAIRS);
    register(CRACKED_STONEBRICK_STAIRS);
    register(WHITE_CLAY_STAIRS);
    register(ORANGE_CLAY_STAIRS);
    register(MAGENTA_CLAY_STAIRS);
    register(LIGHT_BLUE_CLAY_STAIRS);
    register(YELLOW_CLAY_STAIRS);
    register(LIME_CLAY_STAIRS);
    register(PINK_CLAY_STAIRS);
    register(GRAY_CLAY_STAIRS);
    register(LIGHT_GRAY_CLAY_STAIRS);
    register(CYAN_CLAY_STAIRS);
    register(PURPLE_CLAY_STAIRS);
    register(BLUE_CLAY_STAIRS);
    register(BROWN_CLAY_STAIRS);
    register(GREEN_CLAY_STAIRS);
    register(RED_CLAY_STAIRS);
    register(BLACK_CLAY_STAIRS);
    register(HAY_STAIRS);
    register(OLD_HAY_STAIRS);
    register(APPLE_PLANKS_STAIRS);
    register(CHERRY_PLANKS_STAIRS);
    register(PALM_PLANKS_STAIRS);
    register(BELUXIER_PLANKS_STAIRS);
    register(APPLE_LOG_STAIRS);
    register(CHERRY_LOG_STAIRS);
    register(PALM_LOG_STAIRS);
    register(BELUXIER_LOG_STAIRS);
    register(REFINED_OAK_STAIRS);
    register(REFINED_SPRUCE_STAIRS);
    register(REFINED_BIRCH_STAIRS);
    register(REFINED_JUNGLE_STAIRS);
    register(REFINED_ACACIA_STAIRS);
    register(REFINED_DARK_OAK_STAIRS);
    register(ROUGH_SANDSTONE_STAIRS);
    register(YELLOW_STONE_STAIRS);
    register(OCHER_STONE_STAIRS);
    register(YELLOW_COBBLESTONE_STAIRS);
    register(OCHER_COBBLESTONE_STAIRS);
    register(YELLOW_STONEBRICK_STAIRS);
    register(YELLOW_MOSSY_STONEBRICK_STAIRS);
    register(YELLOW_CRACKED_STONEBRICK_STAIRS);
    register(OCHER_STONEBRICK_STAIRS);
    register(OCHER_MOSSY_STONEBRICK_STAIRS);
    register(OCHER_CRACKED_STONEBRICK_STAIRS);
    register(BRICK_TILES_STAIRS);
    register(SLATE_TILES_STAIRS);
    register(LIGHT_CARVED_CLAY_STAIRS);
    register(DARK_CARVED_CLAY_STAIRS);
    register(MARBLE_ROUGH_SAND_STAIRS);
    register(MARBLE_SMOOTH_SAND_STAIRS);
    register(MARBLE_COLUMN_SAND_STAIRS);
    register(MARBLE_ROUGH_WHITE_STAIRS);
    register(MARBLE_SMOOTH_WHITE_STAIRS);
    register(MARBLE_COLUMN_WHITE_STAIRS);
    register(MARBLE_ROUGH_BLACK_STAIRS);
    register(MARBLE_SMOOTH_BLACK_STAIRS);
    register(MARBLE_COLUMN_BLACK_STAIRS);

    register(STONE_SLOPE);
    register(COBBLESTONE_SLOPE);
    register(OAK_PLANKS_SLOPE);
    register(SPRUCE_PLANKS_SLOPE);
    register(BIRCH_PLANKS_SLOPE);
    register(JUNGLE_PLANKS_SLOPE);
    register(ACACIA_PLANKS_SLOPE);
    register(DARK_OAK_PLANKS_SLOPE);
    register(OAK_LOG_SLOPE);
    register(SPRUCE_LOG_SLOPE);
    register(BIRCH_LOG_SLOPE);
    register(JUNGLE_LOG_SLOPE);
    register(ACACIA_LOG_SLOPE);
    register(DARK_OAK_LOG_SLOPE);
    register(OAK_LEAVES_SLOPE);
    register(SPRUCE_LEAVES_SLOPE);
    register(BIRCH_LEAVES_SLOPE);
    register(JUNGLE_LEAVES_SLOPE);
    register(ACACIA_LEAVES_SLOPE);
    register(DARK_OAK_LEAVES_SLOPE);
    register(SANDSTONE_SLOPE);
    register(CHISELED_SANDSTONE_SLOPE);
    register(SMOOTH_SANDSTONE_SLOPE);
    register(WHITE_WOOL_SLOPE);
    register(ORANGE_WOOL_SLOPE);
    register(MAGENTA_WOOL_SLOPE);
    register(LIGHT_BLUE_WOOL_SLOPE);
    register(YELLOW_WOOL_SLOPE);
    register(LIME_WOOL_SLOPE);
    register(PINK_WOOL_SLOPE);
    register(GRAY_WOOL_SLOPE);
    register(LIGHT_GRAY_WOOL_SLOPE);
    register(CYAN_WOOL_SLOPE);
    register(PURPLE_WOOL_SLOPE);
    register(BLUE_WOOL_SLOPE);
    register(BROWN_WOOL_SLOPE);
    register(GREEN_WOOL_SLOPE);
    register(RED_WOOL_SLOPE);
    register(BLACK_WOOL_SLOPE);
    register(GOLDEN_SLOPE);
    register(IRON_SLOPE);
    register(BRICK_SLOPE);
    register(MOSSY_COBBLESTONE_SLOPE);
    register(STONEBRICK_SLOPE);
    register(MOSSY_STONEBRICK_SLOPE);
    register(CRACKED_STONEBRICK_SLOPE);
    register(WHITE_CLAY_SLOPE);
    register(ORANGE_CLAY_SLOPE);
    register(MAGENTA_CLAY_SLOPE);
    register(LIGHT_BLUE_CLAY_SLOPE);
    register(YELLOW_CLAY_SLOPE);
    register(LIME_CLAY_SLOPE);
    register(PINK_CLAY_SLOPE);
    register(GRAY_CLAY_SLOPE);
    register(LIGHT_GRAY_CLAY_SLOPE);
    register(CYAN_CLAY_SLOPE);
    register(PURPLE_CLAY_SLOPE);
    register(BLUE_CLAY_SLOPE);
    register(BROWN_CLAY_SLOPE);
    register(GREEN_CLAY_SLOPE);
    register(RED_CLAY_SLOPE);
    register(BLACK_CLAY_SLOPE);
    register(HAY_SLOPE);
    register(APPLE_PLANKS_SLOPE);
    register(CHERRY_PLANKS_SLOPE);
    register(PALM_PLANKS_SLOPE);
    register(BELUXIER_PLANKS_SLOPE);
    register(APPLE_LOG_SLOPE);
    register(CHERRY_LOG_SLOPE);
    register(PALM_LOG_SLOPE);
    register(BELUXIER_LOG_SLOPE);
    register(APPLE_LEAVES_SLOPE);
    register(CHERRY_LEAVES_SLOPE);
    register(PALM_LEAVES_SLOPE);
    register(BELUXIER_LEAVES_SLOPE);
    register(REFINED_OAK_SLOPE);
    register(REFINED_SPRUCE_SLOPE);
    register(REFINED_BIRCH_SLOPE);
    register(REFINED_JUNGLE_SLOPE);
    register(REFINED_ACACIA_SLOPE);
    register(REFINED_DARK_OAK_SLOPE);
    register(ROUGH_SANDSTONE_SLOPE);
    register(YELLOW_STONE_SLOPE);
    register(OCHER_STONE_SLOPE);
    register(YELLOW_COBBLESTONE_SLOPE);
    register(OCHER_COBBLESTONE_SLOPE);
    register(YELLOW_STONEBRICK_SLOPE);
    register(YELLOW_MOSSY_STONEBRICK_SLOPE);
    register(YELLOW_CRACKED_STONEBRICK_SLOPE);
    register(OCHER_STONEBRICK_SLOPE);
    register(OCHER_MOSSY_STONEBRICK_SLOPE);
    register(OCHER_CRACKED_STONEBRICK_SLOPE);
    register(BRICK_TILES_SLOPE);
    register(SLATE_TILES_SLOPE);
    register(LIGHT_CARVED_CLAY_SLOPE);
    register(DARK_CARVED_CLAY_SLOPE);

    register(STONE_PYRAMID);
    register(COBBLESTONE_PYRAMID);
    register(OAK_PLANKS_PYRAMID);
    register(SPRUCE_PLANKS_PYRAMID);
    register(BIRCH_PLANKS_PYRAMID);
    register(JUNGLE_PLANKS_PYRAMID);
    register(ACACIA_PLANKS_PYRAMID);
    register(DARK_OAK_PLANKS_PYRAMID);
    register(OAK_LOG_PYRAMID);
    register(SPRUCE_LOG_PYRAMID);
    register(BIRCH_LOG_PYRAMID);
    register(JUNGLE_LOG_PYRAMID);
    register(ACACIA_LOG_PYRAMID);
    register(DARK_OAK_LOG_PYRAMID);
    register(OAK_LEAVES_PYRAMID);
    register(SPRUCE_LEAVES_PYRAMID);
    register(BIRCH_LEAVES_PYRAMID);
    register(JUNGLE_LEAVES_PYRAMID);
    register(ACACIA_LEAVES_PYRAMID);
    register(DARK_OAK_LEAVES_PYRAMID);
    register(SANDSTONE_PYRAMID);
    register(CHISELED_SANDSTONE_PYRAMID);
    register(SMOOTH_SANDSTONE_PYRAMID);
    register(WHITE_WOOL_PYRAMID);
    register(ORANGE_WOOL_PYRAMID);
    register(MAGENTA_WOOL_PYRAMID);
    register(LIGHT_BLUE_WOOL_PYRAMID);
    register(YELLOW_WOOL_PYRAMID);
    register(LIME_WOOL_PYRAMID);
    register(PINK_WOOL_PYRAMID);
    register(GRAY_WOOL_PYRAMID);
    register(LIGHT_GRAY_WOOL_PYRAMID);
    register(CYAN_WOOL_PYRAMID);
    register(PURPLE_WOOL_PYRAMID);
    register(BLUE_WOOL_PYRAMID);
    register(BROWN_WOOL_PYRAMID);
    register(GREEN_WOOL_PYRAMID);
    register(RED_WOOL_PYRAMID);
    register(BLACK_WOOL_PYRAMID);
    register(GOLDEN_PYRAMID);
    register(IRON_PYRAMID);
    register(BRICK_PYRAMID);
    register(MOSSY_COBBLESTONE_PYRAMID);
    register(STONEBRICK_PYRAMID);
    register(MOSSY_STONEBRICK_PYRAMID);
    register(CRACKED_STONEBRICK_PYRAMID);
    register(WHITE_CLAY_PYRAMID);
    register(ORANGE_CLAY_PYRAMID);
    register(MAGENTA_CLAY_PYRAMID);
    register(LIGHT_BLUE_CLAY_PYRAMID);
    register(YELLOW_CLAY_PYRAMID);
    register(LIME_CLAY_PYRAMID);
    register(PINK_CLAY_PYRAMID);
    register(GRAY_CLAY_PYRAMID);
    register(LIGHT_GRAY_CLAY_PYRAMID);
    register(CYAN_CLAY_PYRAMID);
    register(PURPLE_CLAY_PYRAMID);
    register(BLUE_CLAY_PYRAMID);
    register(BROWN_CLAY_PYRAMID);
    register(GREEN_CLAY_PYRAMID);
    register(RED_CLAY_PYRAMID);
    register(BLACK_CLAY_PYRAMID);
    register(HAY_PYRAMID);
    register(APPLE_PLANKS_PYRAMID);
    register(CHERRY_PLANKS_PYRAMID);
    register(PALM_PLANKS_PYRAMID);
    register(BELUXIER_PLANKS_PYRAMID);
    register(APPLE_LOG_PYRAMID);
    register(CHERRY_LOG_PYRAMID);
    register(PALM_LOG_PYRAMID);
    register(BELUXIER_LOG_PYRAMID);
    register(APPLE_LEAVES_PYRAMID);
    register(CHERRY_LEAVES_PYRAMID);
    register(PALM_LEAVES_PYRAMID);
    register(BELUXIER_LEAVES_PYRAMID);
    register(REFINED_OAK_PYRAMID);
    register(REFINED_SPRUCE_PYRAMID);
    register(REFINED_BIRCH_PYRAMID);
    register(REFINED_JUNGLE_PYRAMID);
    register(REFINED_ACACIA_PYRAMID);
    register(REFINED_DARK_OAK_PYRAMID);
    register(ROUGH_SANDSTONE_PYRAMID);
    register(YELLOW_STONE_PYRAMID);
    register(OCHER_STONE_PYRAMID);
    register(YELLOW_COBBLESTONE_PYRAMID);
    register(OCHER_COBBLESTONE_PYRAMID);
    register(YELLOW_STONEBRICK_PYRAMID);
    register(YELLOW_MOSSY_STONEBRICK_PYRAMID);
    register(YELLOW_CRACKED_STONEBRICK_PYRAMID);
    register(OCHER_STONEBRICK_PYRAMID);
    register(OCHER_MOSSY_STONEBRICK_PYRAMID);
    register(OCHER_CRACKED_STONEBRICK_PYRAMID);
    register(BRICK_TILES_PYRAMID);
    register(SLATE_TILES_PYRAMID);
    register(LIGHT_CARVED_CLAY_PYRAMID);
    register(DARK_CARVED_CLAY_PYRAMID);

    register(STRONG_OAK_TRAPDOOR);
    register(STRONG_SPRUCE_TRAPDOOR);
    register(STRONG_BIRCH_TRAPDOOR);
    register(STRONG_JUNGLE_TRAPDOOR);
    register(STRONG_ACACIA_TRAPDOOR);
    register(STRONG_DARK_OAK_TRAPDOOR);

    register(CRAFTSMAN_OAK_TRAPDOOR);
    register(CRAFTSMAN_SPRUCE_TRAPDOOR);
    register(CRAFTSMAN_BIRCH_TRAPDOOR);
    register(CRAFTSMAN_JUNGLE_TRAPDOOR);
    register(CRAFTSMAN_ACACIA_TRAPDOOR);
    register(CRAFTSMAN_DARK_OAK_TRAPDOOR);

    register(STRONG_OAK_DOOR);

    register(CRAFTSMAN_OAK_DOOR);
    register(CRAFTSMAN_SPRUCE_DOOR);
    register(CRAFTSMAN_BIRCH_DOOR);
    register(CRAFTSMAN_JUNGLE_DOOR);
    register(CRAFTSMAN_ACACIA_DOOR);
    register(CRAFTSMAN_DARK_OAK_DOOR);

    register(REFINED_OAK_GATE);
    register(REFINED_SPRUCE_GATE);
    register(REFINED_BIRCH_GATE);
    register(REFINED_JUNGLE_GATE);
    register(REFINED_ACACIA_GATE);
    register(REFINED_DARK_OAK_GATE);

    register(REFINED_OAK_FENCE);
    register(REFINED_SPRUCE_FENCE);
    register(REFINED_BIRCH_FENCE);
    register(REFINED_JUNGLE_FENCE);
    register(REFINED_ACACIA_FENCE);
    register(REFINED_DARK_OAK_FENCE);
    register(APPLE_WOOD_FENCE);
    register(CHERRY_WOOD_FENCE);
    register(PALM_FENCE);
    register(BELUXIER_FENCE);

    registerVariants(WOODEN_WALL);
    registerVariants(WOODEN_WALL2);
    registerVariants(STONE_WALL);
    registerVariants(MARBLE_WALL);

    register(HAY_BED);
    register(NORMAL_BED);
    register(STONE_BED);
    register(SLEEPING_BAG);

    registerVariants(CARPET);
    registerVariants(MOUCHARABIEH);
    registerVariants(MOUCHARABIEH_PANE);

    register(ROPE_LADDER);
    register(CHAIN_LADDER);

    register(FLOOR_ROPE);
    register(CHAIN);
    register(ROPE);
    register(ROPE_ATTACH);

    registerVariants(FEATHERS);
    registerVariants(PEBBLES);
    registerVariants(COINS);

    register(CANDLE);
    register(TRIPLE_CANDLE);
    register(CHANDELIER);
    register(LARGE_CHANDELIER);
    register(LARGE_TORCH);
    register(BRAZIER);
    register(LIT_BRAZIER);

    register(WOODEN_LAMP);

    register(WHITE_LANTERN);
    register(ORANGE_LANTERN);
    register(YELLOW_LANTERN);
    register(PURPLE_LANTERN);
    register(BLUE_LANTERN);
    register(GREEN_LANTERN);
    register(RED_LANTERN);

    register(WHITE_PAPER_LANTERN);
    register(ORANGE_PAPER_LANTERN);
    register(YELLOW_PAPER_LANTERN);
    register(PURPLE_PAPER_LANTERN);
    register(BLUE_PAPER_LANTERN);
    register(GREEN_PAPER_LANTERN);
    register(RED_PAPER_LANTERN);

    register(WALL_NOTE);
    register(TOMBSTONE);
    register(STANDING_SIGN);
    register(WALL_SIGN);
    register(SUSPENDED_SIGN);
    register(STANDING_PAPER_SIGN);
    register(WALL_PAPER_SIGN);
    register(SUSPENDED_PAPER_SIGN);
    register(STANDING_ORP_SIGN);
    register(WALL_ORP_SIGN);

    register(ARMOR_STAND);
    register(WEAPONS_STAND);
    register(SHOWCASE);

    register(OAK_CHAIR);
    register(SPRUCE_CHAIR);
    register(BIRCH_CHAIR);
    register(JUNGLE_CHAIR);
    register(ACACIA_CHAIR);
    register(DARK_OAK_CHAIR);

    register(OAK_STOOL);
    register(SPRUCE_STOOL);
    register(BIRCH_STOOL);
    register(JUNGLE_STOOL);
    register(ACACIA_STOOL);
    register(DARK_OAK_STOOL);
    register(TALL_OAK_STOOL);
    register(TALL_SPRUCE_STOOL);
    register(TALL_BIRCH_STOOL);
    register(TALL_JUNGLE_STOOL);
    register(TALL_ACACIA_STOOL);
    register(TALL_DARK_OAK_STOOL);

    register(WOODEN_ARMCHAIR);
    register(STONE_ARMCHAIR);

    register(OAK_BENCH);
    register(SPRUCE_BENCH);
    register(BIRCH_BENCH);
    register(JUNGLE_BENCH);
    register(ACACIA_BENCH);
    register(DARK_OAK_BENCH);

    registerVariants(TABLE);
    registerVariants(END_TABLE);
    register(TABLE_WITH_FOOT);

    register(OAK_SHELF);
    register(SPRUCE_SHELF);
    register(BIRCH_SHELF);
    register(JUNGLE_SHELF);
    register(ACACIA_SHELF);
    register(DARK_OAK_SHELF);
    register(STONE_SHELF);

    register(SUPPORT);
    register(LONG_SUPPORT);

    register(CHESSBOARD);

    register(CHOCOLATE_CAKE);

    register(LONG_REPEATER_OFF);
    register(LONG_REPEATER_ON);
  }

  /**
   * Enregistre un bloc.
   *
   * @param block le bloc
   */
  private static void register(Block block) {
    GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    GameRegistry.register(block);
  }

  /**
   * Enregistre un bloc ayant des variantes.
   *
   * @param block le bloc
   * @param clazz la classe de l'item correspondant
   */
  private static <T extends Block & IBlockWithVariants> void registerVariants(T block) {
    GameRegistry.register(new ItemBlockWithVariants(block));
    GameRegistry.register(block);
  }

  /**
   * Enregistre une dalle.
   *
   * @param halfSlab la dalle
   * @param doubleSlab la double dalle
   * @param item l'item correspondant
   */
  private static <T extends McfrBlockSlab<?>> void registerSlab(T halfSlab, T doubleSlab, McfrItemSlab<T> item) {
    GameRegistry.register(item);
    GameRegistry.register(halfSlab);
    GameRegistry.register(doubleSlab);
  }

  /**
   * Ajoute une recette pour le four.
   *
   * @param block le bloc
   * @param result l'item résultant
   * @param quantity la quantité
   * @param meta le metadata
   * @param xp la quantité d'XP
   */
  @SuppressWarnings("unused")
  private static void addSmelting(Block block, Item result, int quantity, int meta, float xp) {
    GameRegistry.addSmelting(block, new ItemStack(result, quantity, meta), xp);
  }

  /**
   * Cette méthode retourne la lanterne correspondant à la couleur demandée et au type demandés.
   *
   * @param color la couleur
   * @param isPaper la lanterne est en papier ou non
   * @return la lanterne ou null si la couleur est null
   */
  public static BlockLantern getLantern(EnumLanternColor color, boolean isPaper) {
    switch (color) {
      case WHITE:
        return isPaper ? WHITE_PAPER_LANTERN : WHITE_LANTERN;
      case ORANGE:
        return isPaper ? ORANGE_PAPER_LANTERN : ORANGE_LANTERN;
      case YELLOW:
        return isPaper ? YELLOW_PAPER_LANTERN : YELLOW_LANTERN;
      case PURPLE:
        return isPaper ? PURPLE_PAPER_LANTERN : PURPLE_LANTERN;
      case BLUE:
        return isPaper ? BLUE_PAPER_LANTERN : BLUE_LANTERN;
      case GREEN:
        return isPaper ? GREEN_PAPER_LANTERN : GREEN_LANTERN;
      case RED:
        return isPaper ? RED_PAPER_LANTERN : RED_LANTERN;
      default:
        return null;
    }
  }

  private McfrBlocks() {}
}
