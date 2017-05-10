package net.mcfr.proxy;

import static net.mcfr.McfrBlocks.*;
import static net.mcfr.McfrItems.*;

import net.mcfr.Constants;
import net.mcfr.McfrBlocks;
import net.mcfr.McfrItems;
import net.mcfr.commons.IEnumType;
import net.mcfr.construction.BlockCarvedClay;
import net.mcfr.construction.BlockColoredStonebrick;
import net.mcfr.construction.BlockHaySlab;
import net.mcfr.construction.BlockMarble;
import net.mcfr.construction.BlockMarbleColumn;
import net.mcfr.construction.BlockMarbleSlab;
import net.mcfr.construction.BlockMarbleSlab2;
import net.mcfr.construction.BlockMarbleWall;
import net.mcfr.construction.BlockMetalSlab;
import net.mcfr.construction.BlockStoneSlab;
import net.mcfr.construction.BlockStoneWall;
import net.mcfr.construction.BlockStonebrickSlab;
import net.mcfr.construction.BlockTiles;
import net.mcfr.construction.BlockTimbered;
import net.mcfr.construction.McfrBlockSlab;
import net.mcfr.craftsmanship.ItemStitch;
import net.mcfr.craftsmanship.ItemSwordHandle;
import net.mcfr.craftsmanship.tile_entities.TileEntityCircularSaw;
import net.mcfr.craftsmanship.tile_entities.TileEntityCircularSawRenderer;
import net.mcfr.decoration.container_blocks.ItemBarrel;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityBookshelf;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityBookshelfRenderer;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityPallet;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityPalletRenderer;
import net.mcfr.decoration.furniture.tile_entities.TileEntityArmChair;
import net.mcfr.decoration.furniture.tile_entities.TileEntityArmChairRenderer;
import net.mcfr.decoration.furniture.tile_entities.TileEntityShowcase;
import net.mcfr.decoration.furniture.tile_entities.TileEntityShowcaseRenderer;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWeaponsStand;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWeaponsStandRenderer;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWoodenBench;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWoodenBenchRenderer;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWoodenChair;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWoodenChairRenderer;
import net.mcfr.decoration.lighting.EnumLanternColor;
import net.mcfr.decoration.lighting.tile_entities.TileEntityCampfire;
import net.mcfr.decoration.lighting.tile_entities.TileEntityCampfireRenderer;
import net.mcfr.decoration.lighting.tile_entities.TileEntityChandelier;
import net.mcfr.decoration.lighting.tile_entities.TileEntityChandelierRenderer;
import net.mcfr.decoration.misc.BlockFloorDecoration;
import net.mcfr.decoration.misc.EnumMoucharabiehType;
import net.mcfr.decoration.misc.tile_entities.TileEntityCarpet;
import net.mcfr.decoration.misc.tile_entities.TileEntityCarpetRenderer;
import net.mcfr.decoration.misc.tile_entities.TileEntitySupport;
import net.mcfr.decoration.misc.tile_entities.TileEntitySupportRenderer;
import net.mcfr.decoration.signs.tile_entities.TileEntityNormalSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityNormalSignRenderer;
import net.mcfr.decoration.signs.tile_entities.TileEntityOrpSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityOrpSignRenderer;
import net.mcfr.decoration.signs.tile_entities.TileEntityPaperSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityPaperSignRenderer;
import net.mcfr.decoration.signs.tile_entities.TileEntityWallNote;
import net.mcfr.decoration.signs.tile_entities.TileEntityWallNoteRenderer;
import net.mcfr.economy.ItemClawMoney;
import net.mcfr.economy.ItemCoin;
import net.mcfr.economy.ItemToken;
import net.mcfr.entities.EntityChatBubble;
import net.mcfr.entities.RenderChatBubble;
import net.mcfr.entities.mobs.entity.EntityBormoth;
import net.mcfr.entities.mobs.entity.EntityFjalla;
import net.mcfr.entities.mobs.entity.EntityGalt;
import net.mcfr.entities.mobs.entity.EntityGronle;
import net.mcfr.entities.mobs.entity.EntityHoen;
import net.mcfr.entities.mobs.entity.EntityNiale;
import net.mcfr.entities.mobs.entity.EntityPryf;
import net.mcfr.entities.mobs.entity.EntitySiker;
import net.mcfr.entities.mobs.entity.EntityTaure;
import net.mcfr.entities.mobs.model.ModelBormoth;
import net.mcfr.entities.mobs.model.ModelFjalla;
import net.mcfr.entities.mobs.model.ModelGalt;
import net.mcfr.entities.mobs.model.ModelGronle;
import net.mcfr.entities.mobs.model.ModelHoen;
import net.mcfr.entities.mobs.model.ModelNiale;
import net.mcfr.entities.mobs.model.ModelPryf;
import net.mcfr.entities.mobs.model.ModelSiker;
import net.mcfr.entities.mobs.model.ModelTaure;
import net.mcfr.entities.mobs.render.RenderBormoth;
import net.mcfr.entities.mobs.render.RenderFjalla;
import net.mcfr.entities.mobs.render.RenderGalt;
import net.mcfr.entities.mobs.render.RenderGronle;
import net.mcfr.entities.mobs.render.RenderHoen;
import net.mcfr.entities.mobs.render.RenderNiale;
import net.mcfr.entities.mobs.render.RenderPryf;
import net.mcfr.entities.mobs.render.RenderSiker;
import net.mcfr.entities.mobs.render.RenderTaure;
import net.mcfr.environment.BlockStalactite;
import net.mcfr.environment.ItemOre;
import net.mcfr.environment.plants.BlockMushroom;
import net.mcfr.environment.plants.BlockSandBush;
import net.mcfr.environment.plants.BlockWildGrass;
import net.mcfr.environment.plants.EnumExoticWoodType;
import net.mcfr.equipment.ItemGrapnel;
import net.mcfr.farming.ItemFodder;
import net.mcfr.food.ustensils.ItemFlask;
import net.mcfr.forge.tile_entities.TileEntityBellows;
import net.mcfr.forge.tile_entities.TileEntityBellowsRenderer;
import net.mcfr.misc.ItemDecoratedRing;
import net.mcfr.misc.ItemRing;
import net.mcfr.utils.BiomeColorHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * Proxy utilisé par le client.
 *
 * @author Mc-Fr
 */
public class ClientProxy extends CommonProxy {
  @SuppressWarnings("deprecation")
  @Override
  public void register() {
    EXOTIC_LEAVES.setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);

    /**
     * Blocs.
     */

    registerBlock(CHANGER);

    registerBlock(PULSOR);

    registerBlock(SMOKE_GENERATOR);
    registerBlock(McfrBlocks.CAMPFIRE);
    registerBlock(McfrBlocks.LIT_CAMPFIRE);

    registerBlock(ANVIL);
    registerBlock(STOVE);
    registerBlock(LIT_STOVE);
    registerBlock(BELLOWS);

    registerBlock(OLD_HAY_BLOCK);
    registerBlock(REFINED_GOLD_BLOCK);
    registerBlock(REFINED_IRON_BLOCK);
    registerBlockWoodVariants(REFINED_PLANKS);
    registerBlock(ROUGH_SANDSTONE);
    registerBlockVariants(YELLOW_STONEBRICK, BlockColoredStonebrick.EnumType.class);
    registerBlockVariants(OCHER_STONEBRICK, BlockColoredStonebrick.EnumType.class);
    registerBlock(DAUB);
    registerBlockVariants(TIMBERED_BLOCK, BlockTimbered.EnumType.class);
    registerBlockVariants(TILES, BlockTiles.EnumType.class);
    registerBlockVariants(MARBLE, BlockMarble.EnumType.class);
    registerBlockVariants(MARBLE_COLUMN, BlockMarbleColumn.EnumType.class);
    registerBlockVariants(CARVED_CLAY, BlockCarvedClay.EnumType.class);

    registerBlock(McfrBlocks.BARLEY);
    registerBlock(VINE_BASE);
    registerBlock(VINE_TOP);
    registerBlock(McfrBlocks.SUGAR_CANES);
    registerBlock(HEMP_BASE);
    registerBlock(HEMP_TOP);
    registerBlock(McfrBlocks.FERTILIZER);

    registerBlock(LOOM);
    registerBlock(TANNING_RACK);
    registerBlock(LARGE_WORKBENCH);
    registerBlock(CIRCULAR_SAW);

    registerBlockVariants(EXOTIC_PLANKS, EnumExoticWoodType.class);
    registerBlockVariants(EXOTIC_SAPLING, EnumExoticWoodType.class);
    registerBlockVariants(EXOTIC_LOG, EnumExoticWoodType.class);
    registerBlockVariants(EXOTIC_LEAVES, EnumExoticWoodType.class);

    registerBlockVariants(STONE_STALACTITE, BlockStalactite.EnumSize.class);
    registerBlockVariants(ICICLE, BlockStalactite.EnumSize.class);
    registerBlock(BEEHIVE);
    registerBlock(GLOWING_VINE);
    registerBlock(GLOWING_MUSHROOM);
    registerBlockVariants(WILD_GRASS, BlockWildGrass.EnumType.class);
    registerBlockVariants(SAND_BUSH, BlockSandBush.EnumType.class);
    registerBlock(McfrBlocks.REEDS);

    registerBlockVariants(MUSHROOM_CAP, BlockMushroom.EnumType.class);
    registerBlock(SPORES);

    registerBlock(McfrBlocks.EMPTY_BARREL);
    registerBlock(McfrBlocks.BEER_BARREL);
    registerBlock(McfrBlocks.CIDER_BARREL);
    registerBlock(McfrBlocks.WINE_BARREL);
    registerBlock(McfrBlocks.RUM_BARREL);

    registerBlock(LITTLE_CHEST);
    registerBlock(CRATE);
    registerBlock(CONSTRUCTION_CRATE);
    registerBlock(FOOD_CRATE);
    registerBlock(PALLET);
    registerBlockWoodVariants(BOOKSHELF);
    registerBlock(BOOKSHELF);

    registerSlabVariants(HAY_SLAB, DOUBLE_HAY_SLAB, BlockHaySlab.EnumType.class);
    registerWoodSlabVariants(REFINED_PLANKS_SLAB, DOUBLE_REFINED_PLANKS_SLAB);
    registerSlabVariants(EXOTIC_WOOD_SLAB, DOUBLE_EXOTIC_WOOD_SLAB, EnumExoticWoodType.class);
    registerSlabVariants(METAL_SLAB, DOUBLE_METAL_SLAB, BlockMetalSlab.EnumType.class);
    registerSlabVariants(STONE_SLAB, DOUBLE_STONE_SLAB, BlockStoneSlab.EnumType.class);
    registerSlabVariants(STONEBRICK_SLAB, DOUBLE_STONEBRICK_SLAB, BlockStonebrickSlab.EnumType.class);
    registerSlabVariants(MARBLE_SLAB, DOUBLE_MARBLE_SLAB, BlockMarbleSlab.EnumType.class);
    registerSlabVariants(MARBLE_SLAB2, DOUBLE_MARBLE_SLAB2, BlockMarbleSlab2.EnumType.class);

    registerBlock(STONE_STAIRS);
    registerBlock(OAK_LOG_STAIRS);
    registerBlock(SPRUCE_LOG_STAIRS);
    registerBlock(BIRCH_LOG_STAIRS);
    registerBlock(JUNGLE_LOG_STAIRS);
    registerBlock(ACACIA_LOG_STAIRS);
    registerBlock(DARK_OAK_LOG_STAIRS);
    registerBlock(CHISELED_SANDSTONE_STAIRS);
    registerBlock(SMOOTH_SANDSTONE_STAIRS);
    registerBlock(WHITE_WOOL_STAIRS);
    registerBlock(ORANGE_WOOL_STAIRS);
    registerBlock(MAGENTA_WOOL_STAIRS);
    registerBlock(LIGHT_BLUE_WOOL_STAIRS);
    registerBlock(YELLOW_WOOL_STAIRS);
    registerBlock(LIME_WOOL_STAIRS);
    registerBlock(PINK_WOOL_STAIRS);
    registerBlock(GRAY_WOOL_STAIRS);
    registerBlock(LIGHT_GRAY_WOOL_STAIRS);
    registerBlock(CYAN_WOOL_STAIRS);
    registerBlock(PURPLE_WOOL_STAIRS);
    registerBlock(BLUE_WOOL_STAIRS);
    registerBlock(BROWN_WOOL_STAIRS);
    registerBlock(GREEN_WOOL_STAIRS);
    registerBlock(RED_WOOL_STAIRS);
    registerBlock(BLACK_WOOL_STAIRS);
    registerBlock(GOLDEN_STAIRS);
    registerBlock(IRON_STAIRS);
    registerBlock(MOSSY_COBBLESTONE_STAIRS);
    registerBlock(MOSSY_STONEBRICK_STAIRS);
    registerBlock(CRACKED_STONEBRICK_STAIRS);
    registerBlock(WHITE_CLAY_STAIRS);
    registerBlock(ORANGE_CLAY_STAIRS);
    registerBlock(MAGENTA_CLAY_STAIRS);
    registerBlock(LIGHT_BLUE_CLAY_STAIRS);
    registerBlock(YELLOW_CLAY_STAIRS);
    registerBlock(LIME_CLAY_STAIRS);
    registerBlock(PINK_CLAY_STAIRS);
    registerBlock(GRAY_CLAY_STAIRS);
    registerBlock(LIGHT_GRAY_CLAY_STAIRS);
    registerBlock(CYAN_CLAY_STAIRS);
    registerBlock(PURPLE_CLAY_STAIRS);
    registerBlock(BLUE_CLAY_STAIRS);
    registerBlock(BROWN_CLAY_STAIRS);
    registerBlock(GREEN_CLAY_STAIRS);
    registerBlock(RED_CLAY_STAIRS);
    registerBlock(BLACK_CLAY_STAIRS);
    registerBlock(HAY_STAIRS);
    registerBlock(OLD_HAY_STAIRS);
    registerBlock(APPLE_PLANKS_STAIRS);
    registerBlock(CHERRY_PLANKS_STAIRS);
    registerBlock(PALM_PLANKS_STAIRS);
    registerBlock(BELUXIER_PLANKS_STAIRS);
    registerBlock(APPLE_LOG_STAIRS);
    registerBlock(CHERRY_LOG_STAIRS);
    registerBlock(PALM_LOG_STAIRS);
    registerBlock(BELUXIER_LOG_STAIRS);
    registerBlock(REFINED_OAK_STAIRS);
    registerBlock(REFINED_SPRUCE_STAIRS);
    registerBlock(REFINED_BIRCH_STAIRS);
    registerBlock(REFINED_JUNGLE_STAIRS);
    registerBlock(REFINED_ACACIA_STAIRS);
    registerBlock(REFINED_DARK_OAK_STAIRS);
    registerBlock(ROUGH_SANDSTONE_STAIRS);
    registerBlock(YELLOW_STONE_STAIRS);
    registerBlock(OCHER_STONE_STAIRS);
    registerBlock(YELLOW_COBBLESTONE_STAIRS);
    registerBlock(OCHER_COBBLESTONE_STAIRS);
    registerBlock(YELLOW_STONEBRICK_STAIRS);
    registerBlock(YELLOW_MOSSY_STONEBRICK_STAIRS);
    registerBlock(YELLOW_CRACKED_STONEBRICK_STAIRS);
    registerBlock(OCHER_STONEBRICK_STAIRS);
    registerBlock(OCHER_MOSSY_STONEBRICK_STAIRS);
    registerBlock(OCHER_CRACKED_STONEBRICK_STAIRS);
    registerBlock(BRICK_TILES_STAIRS);
    registerBlock(SLATE_TILES_STAIRS);
    registerBlock(LIGHT_CARVED_CLAY_STAIRS);
    registerBlock(DARK_CARVED_CLAY_STAIRS);
    registerBlock(MARBLE_ROUGH_SAND_STAIRS);
    registerBlock(MARBLE_SMOOTH_SAND_STAIRS);
    registerBlock(MARBLE_COLUMN_SAND_STAIRS);
    registerBlock(MARBLE_ROUGH_WHITE_STAIRS);
    registerBlock(MARBLE_SMOOTH_WHITE_STAIRS);
    registerBlock(MARBLE_COLUMN_WHITE_STAIRS);
    registerBlock(MARBLE_ROUGH_BLACK_STAIRS);
    registerBlock(MARBLE_SMOOTH_BLACK_STAIRS);
    registerBlock(MARBLE_COLUMN_BLACK_STAIRS);

    registerBlock(STONE_SLOPE);
    registerBlock(COBBLESTONE_SLOPE);
    registerBlock(OAK_PLANKS_SLOPE);
    registerBlock(SPRUCE_PLANKS_SLOPE);
    registerBlock(BIRCH_PLANKS_SLOPE);
    registerBlock(JUNGLE_PLANKS_SLOPE);
    registerBlock(ACACIA_PLANKS_SLOPE);
    registerBlock(DARK_OAK_PLANKS_SLOPE);
    registerBlock(OAK_LOG_SLOPE);
    registerBlock(SPRUCE_LOG_SLOPE);
    registerBlock(BIRCH_LOG_SLOPE);
    registerBlock(JUNGLE_LOG_SLOPE);
    registerBlock(ACACIA_LOG_SLOPE);
    registerBlock(DARK_OAK_LOG_SLOPE);
    registerBlock(OAK_LEAVES_SLOPE);
    registerBlock(SPRUCE_LEAVES_SLOPE);
    registerBlock(BIRCH_LEAVES_SLOPE);
    registerBlock(JUNGLE_LEAVES_SLOPE);
    registerBlock(ACACIA_LEAVES_SLOPE);
    registerBlock(DARK_OAK_LEAVES_SLOPE);
    registerBlock(SANDSTONE_SLOPE);
    registerBlock(CHISELED_SANDSTONE_SLOPE);
    registerBlock(SMOOTH_SANDSTONE_SLOPE);
    registerBlock(WHITE_WOOL_SLOPE);
    registerBlock(ORANGE_WOOL_SLOPE);
    registerBlock(MAGENTA_WOOL_SLOPE);
    registerBlock(LIGHT_BLUE_WOOL_SLOPE);
    registerBlock(YELLOW_WOOL_SLOPE);
    registerBlock(LIME_WOOL_SLOPE);
    registerBlock(PINK_WOOL_SLOPE);
    registerBlock(GRAY_WOOL_SLOPE);
    registerBlock(LIGHT_GRAY_WOOL_SLOPE);
    registerBlock(CYAN_WOOL_SLOPE);
    registerBlock(PURPLE_WOOL_SLOPE);
    registerBlock(BLUE_WOOL_SLOPE);
    registerBlock(BROWN_WOOL_SLOPE);
    registerBlock(GREEN_WOOL_SLOPE);
    registerBlock(RED_WOOL_SLOPE);
    registerBlock(BLACK_WOOL_SLOPE);
    registerBlock(GOLDEN_SLOPE);
    registerBlock(IRON_SLOPE);
    registerBlock(BRICK_SLOPE);
    registerBlock(MOSSY_COBBLESTONE_SLOPE);
    registerBlock(STONEBRICK_SLOPE);
    registerBlock(MOSSY_STONEBRICK_SLOPE);
    registerBlock(CRACKED_STONEBRICK_SLOPE);
    registerBlock(WHITE_CLAY_SLOPE);
    registerBlock(ORANGE_CLAY_SLOPE);
    registerBlock(MAGENTA_CLAY_SLOPE);
    registerBlock(LIGHT_BLUE_CLAY_SLOPE);
    registerBlock(YELLOW_CLAY_SLOPE);
    registerBlock(LIME_CLAY_SLOPE);
    registerBlock(PINK_CLAY_SLOPE);
    registerBlock(GRAY_CLAY_SLOPE);
    registerBlock(LIGHT_GRAY_CLAY_SLOPE);
    registerBlock(CYAN_CLAY_SLOPE);
    registerBlock(PURPLE_CLAY_SLOPE);
    registerBlock(BLUE_CLAY_SLOPE);
    registerBlock(BROWN_CLAY_SLOPE);
    registerBlock(GREEN_CLAY_SLOPE);
    registerBlock(RED_CLAY_SLOPE);
    registerBlock(BLACK_CLAY_SLOPE);
    registerBlock(HAY_SLOPE);
    registerBlock(APPLE_PLANKS_SLOPE);
    registerBlock(CHERRY_PLANKS_SLOPE);
    registerBlock(PALM_PLANKS_SLOPE);
    registerBlock(BELUXIER_PLANKS_SLOPE);
    registerBlock(APPLE_LOG_SLOPE);
    registerBlock(CHERRY_LOG_SLOPE);
    registerBlock(PALM_LOG_SLOPE);
    registerBlock(BELUXIER_LOG_SLOPE);
    registerBlock(APPLE_LEAVES_SLOPE);
    registerBlock(CHERRY_LEAVES_SLOPE);
    registerBlock(PALM_LEAVES_SLOPE);
    registerBlock(BELUXIER_LEAVES_SLOPE);
    registerBlock(REFINED_OAK_SLOPE);
    registerBlock(REFINED_SPRUCE_SLOPE);
    registerBlock(REFINED_BIRCH_SLOPE);
    registerBlock(REFINED_JUNGLE_SLOPE);
    registerBlock(REFINED_ACACIA_SLOPE);
    registerBlock(REFINED_DARK_OAK_SLOPE);
    registerBlock(ROUGH_SANDSTONE_SLOPE);
    registerBlock(YELLOW_STONE_SLOPE);
    registerBlock(OCHER_STONE_SLOPE);
    registerBlock(YELLOW_COBBLESTONE_SLOPE);
    registerBlock(OCHER_COBBLESTONE_SLOPE);
    registerBlock(YELLOW_STONEBRICK_SLOPE);
    registerBlock(YELLOW_MOSSY_STONEBRICK_SLOPE);
    registerBlock(YELLOW_CRACKED_STONEBRICK_SLOPE);
    registerBlock(OCHER_STONEBRICK_SLOPE);
    registerBlock(OCHER_MOSSY_STONEBRICK_SLOPE);
    registerBlock(OCHER_CRACKED_STONEBRICK_SLOPE);
    registerBlock(BRICK_TILES_SLOPE);
    registerBlock(SLATE_TILES_SLOPE);
    registerBlock(LIGHT_CARVED_CLAY_SLOPE);
    registerBlock(DARK_CARVED_CLAY_SLOPE);

    registerBlock(STONE_PYRAMID);
    registerBlock(COBBLESTONE_PYRAMID);
    registerBlock(OAK_PLANKS_PYRAMID);
    registerBlock(SPRUCE_PLANKS_PYRAMID);
    registerBlock(BIRCH_PLANKS_PYRAMID);
    registerBlock(JUNGLE_PLANKS_PYRAMID);
    registerBlock(ACACIA_PLANKS_PYRAMID);
    registerBlock(DARK_OAK_PLANKS_PYRAMID);
    registerBlock(OAK_LOG_PYRAMID);
    registerBlock(SPRUCE_LOG_PYRAMID);
    registerBlock(BIRCH_LOG_PYRAMID);
    registerBlock(JUNGLE_LOG_PYRAMID);
    registerBlock(ACACIA_LOG_PYRAMID);
    registerBlock(DARK_OAK_LOG_PYRAMID);
    registerBlock(OAK_LEAVES_PYRAMID);
    registerBlock(SPRUCE_LEAVES_PYRAMID);
    registerBlock(BIRCH_LEAVES_PYRAMID);
    registerBlock(JUNGLE_LEAVES_PYRAMID);
    registerBlock(ACACIA_LEAVES_PYRAMID);
    registerBlock(DARK_OAK_LEAVES_PYRAMID);
    registerBlock(SANDSTONE_PYRAMID);
    registerBlock(CHISELED_SANDSTONE_PYRAMID);
    registerBlock(SMOOTH_SANDSTONE_PYRAMID);
    registerBlock(WHITE_WOOL_PYRAMID);
    registerBlock(ORANGE_WOOL_PYRAMID);
    registerBlock(MAGENTA_WOOL_PYRAMID);
    registerBlock(LIGHT_BLUE_WOOL_PYRAMID);
    registerBlock(YELLOW_WOOL_PYRAMID);
    registerBlock(LIME_WOOL_PYRAMID);
    registerBlock(PINK_WOOL_PYRAMID);
    registerBlock(GRAY_WOOL_PYRAMID);
    registerBlock(LIGHT_GRAY_WOOL_PYRAMID);
    registerBlock(CYAN_WOOL_PYRAMID);
    registerBlock(PURPLE_WOOL_PYRAMID);
    registerBlock(BLUE_WOOL_PYRAMID);
    registerBlock(BROWN_WOOL_PYRAMID);
    registerBlock(GREEN_WOOL_PYRAMID);
    registerBlock(RED_WOOL_PYRAMID);
    registerBlock(BLACK_WOOL_PYRAMID);
    registerBlock(GOLDEN_PYRAMID);
    registerBlock(IRON_PYRAMID);
    registerBlock(BRICK_PYRAMID);
    registerBlock(MOSSY_COBBLESTONE_PYRAMID);
    registerBlock(STONEBRICK_PYRAMID);
    registerBlock(MOSSY_STONEBRICK_PYRAMID);
    registerBlock(CRACKED_STONEBRICK_PYRAMID);
    registerBlock(WHITE_CLAY_PYRAMID);
    registerBlock(ORANGE_CLAY_PYRAMID);
    registerBlock(MAGENTA_CLAY_PYRAMID);
    registerBlock(LIGHT_BLUE_CLAY_PYRAMID);
    registerBlock(YELLOW_CLAY_PYRAMID);
    registerBlock(LIME_CLAY_PYRAMID);
    registerBlock(PINK_CLAY_PYRAMID);
    registerBlock(GRAY_CLAY_PYRAMID);
    registerBlock(LIGHT_GRAY_CLAY_PYRAMID);
    registerBlock(CYAN_CLAY_PYRAMID);
    registerBlock(PURPLE_CLAY_PYRAMID);
    registerBlock(BLUE_CLAY_PYRAMID);
    registerBlock(BROWN_CLAY_PYRAMID);
    registerBlock(GREEN_CLAY_PYRAMID);
    registerBlock(RED_CLAY_PYRAMID);
    registerBlock(BLACK_CLAY_PYRAMID);
    registerBlock(HAY_PYRAMID);
    registerBlock(APPLE_PLANKS_PYRAMID);
    registerBlock(CHERRY_PLANKS_PYRAMID);
    registerBlock(PALM_PLANKS_PYRAMID);
    registerBlock(BELUXIER_PLANKS_PYRAMID);
    registerBlock(APPLE_LOG_PYRAMID);
    registerBlock(CHERRY_LOG_PYRAMID);
    registerBlock(PALM_LOG_PYRAMID);
    registerBlock(BELUXIER_LOG_PYRAMID);
    registerBlock(APPLE_LEAVES_PYRAMID);
    registerBlock(CHERRY_LEAVES_PYRAMID);
    registerBlock(PALM_LEAVES_PYRAMID);
    registerBlock(BELUXIER_LEAVES_PYRAMID);
    registerBlock(REFINED_OAK_PYRAMID);
    registerBlock(REFINED_SPRUCE_PYRAMID);
    registerBlock(REFINED_BIRCH_PYRAMID);
    registerBlock(REFINED_JUNGLE_PYRAMID);
    registerBlock(REFINED_ACACIA_PYRAMID);
    registerBlock(REFINED_DARK_OAK_PYRAMID);
    registerBlock(ROUGH_SANDSTONE_PYRAMID);
    registerBlock(YELLOW_STONE_PYRAMID);
    registerBlock(OCHER_STONE_PYRAMID);
    registerBlock(YELLOW_COBBLESTONE_PYRAMID);
    registerBlock(OCHER_COBBLESTONE_PYRAMID);
    registerBlock(YELLOW_STONEBRICK_PYRAMID);
    registerBlock(YELLOW_MOSSY_STONEBRICK_PYRAMID);
    registerBlock(YELLOW_CRACKED_STONEBRICK_PYRAMID);
    registerBlock(OCHER_STONEBRICK_PYRAMID);
    registerBlock(OCHER_MOSSY_STONEBRICK_PYRAMID);
    registerBlock(OCHER_CRACKED_STONEBRICK_PYRAMID);
    registerBlock(BRICK_TILES_PYRAMID);
    registerBlock(SLATE_TILES_PYRAMID);
    registerBlock(LIGHT_CARVED_CLAY_PYRAMID);
    registerBlock(DARK_CARVED_CLAY_PYRAMID);

    registerBlock(STRONG_OAK_TRAPDOOR);
    registerBlock(STRONG_SPRUCE_TRAPDOOR);
    registerBlock(STRONG_BIRCH_TRAPDOOR);
    registerBlock(STRONG_JUNGLE_TRAPDOOR);
    registerBlock(STRONG_ACACIA_TRAPDOOR);
    registerBlock(STRONG_DARK_OAK_TRAPDOOR);

    registerBlock(CRAFTSMAN_OAK_TRAPDOOR);
    registerBlock(CRAFTSMAN_SPRUCE_TRAPDOOR);
    registerBlock(CRAFTSMAN_BIRCH_TRAPDOOR);
    registerBlock(CRAFTSMAN_JUNGLE_TRAPDOOR);
    registerBlock(CRAFTSMAN_ACACIA_TRAPDOOR);
    registerBlock(CRAFTSMAN_DARK_OAK_TRAPDOOR);

    registerBlock(McfrBlocks.STRONG_OAK_DOOR);

    registerBlock(McfrBlocks.CRAFTSMAN_OAK_DOOR);
    registerBlock(McfrBlocks.CRAFTSMAN_SPRUCE_DOOR);
    registerBlock(McfrBlocks.CRAFTSMAN_BIRCH_DOOR);
    registerBlock(McfrBlocks.CRAFTSMAN_JUNGLE_DOOR);
    registerBlock(McfrBlocks.CRAFTSMAN_ACACIA_DOOR);
    registerBlock(McfrBlocks.CRAFTSMAN_DARK_OAK_DOOR);

    registerBlock(REFINED_OAK_GATE);
    registerBlock(REFINED_SPRUCE_GATE);
    registerBlock(REFINED_BIRCH_GATE);
    registerBlock(REFINED_JUNGLE_GATE);
    registerBlock(REFINED_ACACIA_GATE);
    registerBlock(REFINED_DARK_OAK_GATE);

    registerBlock(REFINED_OAK_FENCE);
    registerBlock(REFINED_SPRUCE_FENCE);
    registerBlock(REFINED_BIRCH_FENCE);
    registerBlock(REFINED_JUNGLE_FENCE);
    registerBlock(REFINED_ACACIA_FENCE);
    registerBlock(REFINED_DARK_OAK_FENCE);
    registerBlock(APPLE_WOOD_FENCE);
    registerBlock(CHERRY_WOOD_FENCE);
    registerBlock(PALM_FENCE);
    registerBlock(BELUXIER_FENCE);

    registerBlockWoodVariants(WOODEN_WALL);
    registerBlockVariants(EXOTIC_WOOD_WALL, EnumExoticWoodType.class);
    registerBlockVariants(STONE_WALL, BlockStoneWall.EnumType.class);
    registerBlockVariants(MARBLE_WALL, BlockMarbleWall.EnumType.class);

    registerBlock(McfrBlocks.HAY_BED);
    registerBlock(McfrBlocks.NORMAL_BED);
    registerBlock(McfrBlocks.STONE_BED);
    registerBlock(McfrBlocks.SLEEPING_BAG);

    registerBlockColorVariants(CARPET);
    registerBlockVariants(MOUCHARABIEH, EnumMoucharabiehType.class);
    registerBlockVariants(MOUCHARABIEH_PANE, EnumMoucharabiehType.class);

    registerBlock(ROPE_LADDER);
    registerBlock(CHAIN_LADDER);

    registerBlock(CHAIN);
    registerBlock(ROPE);
    registerBlock(ROPE_ATTACH);

    registerBlockVariants(FEATHERS, BlockFloorDecoration.EnumType.class);
    registerBlockVariants(PEBBLES, BlockFloorDecoration.EnumType.class);
    registerBlockVariants(COINS, BlockFloorDecoration.EnumType.class);

    registerBlock(CANDLE);
    registerBlock(DOUBLE_CANDLE);
    registerBlock(TRIPLE_CANDLE);
    registerBlock(CHANDELIER);
    registerBlock(LARGE_CHANDELIER);
    registerBlock(LARGE_TORCH);
    registerBlock(TIKI_TORCH);
    registerBlock(BRAZIER);
    registerBlock(LIT_BRAZIER);

    registerBlock(WOODEN_LAMP);

    registerBlock(WHITE_LANTERN);
    registerBlock(ORANGE_LANTERN);
    registerBlock(YELLOW_LANTERN);
    registerBlock(PURPLE_LANTERN);
    registerBlock(BLUE_LANTERN);
    registerBlock(GREEN_LANTERN);
    registerBlock(RED_LANTERN);

    registerBlock(WHITE_PAPER_LANTERN);
    registerBlock(ORANGE_PAPER_LANTERN);
    registerBlock(YELLOW_PAPER_LANTERN);
    registerBlock(PURPLE_PAPER_LANTERN);
    registerBlock(BLUE_PAPER_LANTERN);
    registerBlock(GREEN_PAPER_LANTERN);
    registerBlock(RED_PAPER_LANTERN);

    registerBlock(McfrBlocks.WALL_NOTE);
    registerBlock(McfrBlocks.TOMBSTONE);
    registerBlock(STANDING_SIGN);
    registerBlock(WALL_SIGN);
    registerBlock(SUSPENDED_SIGN);
    registerBlock(STANDING_PAPER_SIGN);
    registerBlock(WALL_PAPER_SIGN);
    registerBlock(SUSPENDED_PAPER_SIGN);
    registerBlock(STANDING_ORP_SIGN);
    registerBlock(WALL_ORP_SIGN);

    registerBlock(McfrBlocks.ARMOR_STAND);
    registerBlock(McfrBlocks.WEAPONS_STAND);
    registerBlock(McfrBlocks.SHOWCASE);

    registerBlock(OAK_CHAIR);
    registerBlock(SPRUCE_CHAIR);
    registerBlock(BIRCH_CHAIR);
    registerBlock(JUNGLE_CHAIR);
    registerBlock(ACACIA_CHAIR);
    registerBlock(DARK_OAK_CHAIR);

    registerBlock(OAK_STOOL);
    registerBlock(SPRUCE_STOOL);
    registerBlock(BIRCH_STOOL);
    registerBlock(JUNGLE_STOOL);
    registerBlock(ACACIA_STOOL);
    registerBlock(DARK_OAK_STOOL);
    registerBlock(TALL_OAK_STOOL);
    registerBlock(TALL_SPRUCE_STOOL);
    registerBlock(TALL_BIRCH_STOOL);
    registerBlock(TALL_JUNGLE_STOOL);
    registerBlock(TALL_ACACIA_STOOL);
    registerBlock(TALL_DARK_OAK_STOOL);

    registerBlock(WOODEN_ARMCHAIR);
    registerBlock(STONE_ARMCHAIR);

    registerBlock(OAK_BENCH);
    registerBlock(SPRUCE_BENCH);
    registerBlock(BIRCH_BENCH);
    registerBlock(JUNGLE_BENCH);
    registerBlock(ACACIA_BENCH);
    registerBlock(DARK_OAK_BENCH);

    registerBlockWoodVariants(TABLE);
    registerBlockWoodVariants(END_TABLE);
    registerBlock(TABLE_WITH_FOOT);

    registerBlock(OAK_SHELF);
    registerBlock(SPRUCE_SHELF);
    registerBlock(BIRCH_SHELF);
    registerBlock(JUNGLE_SHELF);
    registerBlock(ACACIA_SHELF);
    registerBlock(DARK_OAK_SHELF);
    registerBlock(STONE_SHELF);

    registerBlock(SUPPORT);
    registerBlock(LONG_SUPPORT);

    registerBlock(CHESSBOARD);

    registerBlock(McfrBlocks.CHOCOLATE_CAKE);

    registerBlock(MORTAR);

    registerBlock(LONG_REPEATER_OFF);
    registerBlock(LONG_REPEATER_ON);

    /**
     * Items.
     */

    registerItemVariants(COIN, ItemCoin.EnumType.class);
    registerItemVariants(TOKEN, ItemToken.EnumType.class);
    registerItemVariants(CLAW_MONEY, ItemClawMoney.EnumType.class);

    registerItemVariants(ORE, ItemOre.EnumType.class);
    registerItem(INK);
    registerItemVariants(FODDER, ItemFodder.EnumType.class);
    registerItem(PIPE);
    registerItem(WRITEABLE_PAPER);
    registerItem(SIGNED_PAPER);
    registerItemVariants(SILVER_RING, ItemRing.EnumType.class);
    registerItemVariants(GOLDEN_RING, ItemRing.EnumType.class);
    registerItemVariants(DECORATED_RING, ItemDecoratedRing.EnumType.class);
    registerItem(SAILBOAT);

    registerItem(SAW_SUPPORT);
    registerItem(SAW_BLADE);
    registerItem(THREAD_COIL);
    registerItem(CLOTH_ROLL);
    registerItem(STEEL_PLATE);
    registerItemVariants(STITCH, ItemStitch.EnumType.class);
    registerItemVariants(SWORD_HANDLE, ItemSwordHandle.EnumType.class);

    registerItem(KEYRING);
    registerItem(KEY);

    registerItem(BARLEY_SEEDS);
    registerItem(McfrItems.BARLEY);
    registerItem(GRAPE_SEEDS);
    registerItem(GRAPES);
    registerItem(McfrItems.SUGAR_CANES);
    registerItem(HEMP);
    registerItem(HEMP_LEAF);
    registerItem(HEMP_FIBER);
    registerItem(HEMP_OIL);
    registerItem(POOP);
    registerItem(McfrItems.FERTILIZER);

    registerItem(WAX);
    registerItem(McfrItems.REEDS);

    registerItem(McfrItems.EMPTY_BARREL);
    registerItemVariants(McfrItems.BEER_BARREL, ItemBarrel.EnumType.class);
    registerItemVariants(McfrItems.CIDER_BARREL, ItemBarrel.EnumType.class);
    registerItemVariants(McfrItems.WINE_BARREL, ItemBarrel.EnumType.class);
    registerItemVariants(McfrItems.RUM_BARREL, ItemBarrel.EnumType.class);

    registerItem(EMPTY_TANKARD);
    registerItemVariants(BEER_TANKARD, ItemBarrel.EnumType.class);
    registerItem(EMPTY_BOWL);
    registerItemVariants(CIDER_BOWL, ItemBarrel.EnumType.class);
    registerItem(EMPTY_GLASS);
    registerItemVariants(WINE_GLASS, ItemBarrel.EnumType.class);
    registerItem(EMPTY_BOTTLE);
    registerItemVariants(RUM_BOTTLE, ItemBarrel.EnumType.class);
    registerItemVariants(FLASK, ItemFlask.EnumType.class);

    registerItem(CAKE_DOUGH);
    registerItem(McfrItems.CHOCOLATE_CAKE);
    registerItem(APPLE_PIE);
    registerItem(PUMPKIN_SOUP);
    registerItem(FLOUR);
    registerItem(BREAD_DOUGH);
    registerItem(RAW_SWORDFISH);
    registerItem(COOKED_SWORDFISH);
    registerItem(RAW_SARDINE);
    registerItem(COOKED_SARDINE);
    registerItem(HONEY);
    registerItem(POIGRUME);
    registerItem(POIGRUME_COOKIE);
    registerItem(COCOA);
    registerItem(ORP_FOOD);

    registerItem(RAW_HUNTED_LEG);
    registerItem(COOKED_HUNTED_LEG);
    registerItem(RAW_HUNTED_STEAK);
    registerItem(COOKED_HUNTED_STEAK);
    registerItem(RAW_HUNTED_POULTRY);
    registerItem(COOKED_HUNTED_POULTRY);
    registerItem(HUNTED_SKIN);

    registerItem(RAW_NIALE_MEAT);
    registerItem(COOKED_NIALE_MEAT);
    registerItem(RAW_HOEN_MEAT);
    registerItem(COOKED_HOEN_MEAT);
    registerItem(RAW_GALT_MEAT);
    registerItem(COOKED_GALT_MEAT);

    registerItem(McfrItems.STRONG_OAK_DOOR);
    registerItem(McfrItems.CRAFTSMAN_OAK_DOOR);
    registerItem(McfrItems.CRAFTSMAN_SPRUCE_DOOR);
    registerItem(McfrItems.CRAFTSMAN_BIRCH_DOOR);
    registerItem(McfrItems.CRAFTSMAN_JUNGLE_DOOR);
    registerItem(McfrItems.CRAFTSMAN_ACACIA_DOOR);
    registerItem(McfrItems.CRAFTSMAN_DARK_OAK_DOOR);

    registerItem(McfrItems.HAY_BED);
    registerItem(McfrItems.NORMAL_BED);
    registerItem(McfrItems.STONE_BED);
    registerItem(McfrItems.SLEEPING_BAG);

    registerItem(McfrItems.CAMPFIRE);
    registerItem(McfrItems.LIT_CAMPFIRE);
    registerItemVariants(LANTERN, EnumLanternColor.class);
    registerItemVariants(PAPER_LANTERN, EnumLanternColor.class);

    registerItem(McfrItems.ARMOR_STAND);
    registerItem(McfrItems.WEAPONS_STAND);
    registerItem(McfrItems.SHOWCASE);

    registerItem(McfrItems.WALL_NOTE);
    registerItem(McfrItems.TOMBSTONE);
    registerItem(SIGN);
    registerItem(PAPER_SIGN);
    registerItem(ORP_SIGN);

    registerItem(LONG_REPEATER);

    registerItem(HAMMER);
    registerItemVariants(GRAPNEL, ItemGrapnel.EnumType.class);

    registerItem(GOOD_FISHING_ROD);
    registerItem(FISHING_NET);

    registerItem(BRONZE_SWORD);
    registerItem(BRONZE_PICKAXE);
    registerItem(BRONZE_SPADE);
    registerItem(BRONZE_AXE);

    registerItem(CROSSBOW_BOLT);

    registerItem(BRONZE_BOW);
    registerItem(IRON_BOW);
    registerItem(GOLDEN_BOW);
    registerItem(STEEL_BOW);
    registerItem(BARBARIAN_BOW);
    registerItem(LONG_BOW);
    registerItem(HUNTER_BOW);
    registerItem(LONG_HUNTER_BOW);
    registerItem(ANCIENT_BOW);

    registerItem(WOODEN_STAFF);
    registerItem(BRONZE_STAFF);
    registerItem(IRON_STAFF);
    registerItem(GOLDEN_STAFF);
    registerItem(STEEL_STAFF);

    registerItem(WOODEN_DAGGER);
    registerItem(BONE_DAGGER);
    registerItem(STONE_DAGGER);
    registerItem(BRONZE_DAGGER);
    registerItem(IRON_DAGGER);
    registerItem(GOLDEN_DAGGER);
    registerItem(STEEL_DAGGER);
    registerItem(GEROUN_DAGGER);

    registerItem(BRONZE_LONG_SWORD);
    registerItem(IRON_LONG_SWORD);
    registerItem(GOLDEN_LONG_SWORD);
    registerItem(STEEL_LONG_SWORD);

    registerItem(BARBARIAN_SWORD);
    registerItem(BRONZE_BASTARD);
    registerItem(IRON_BASTARD);
    registerItem(GOLDEN_BASTARD);
    registerItem(STEEL_BASTARD);

    registerItem(WOODEN_FLAIL);
    registerItem(STONE_FLAIL);
    registerItem(BRONZE_FLAIL);
    registerItem(IRON_FLAIL);
    registerItem(GOLDEN_FLAIL);
    registerItem(STEEL_FLAIL);

    registerItem(BEAR_CLAWS);
    registerItem(KATAR);
    registerItem(NINJA_CLAWS);
    registerItem(STEEL_CLAWS);

    registerItem(STONE_BATTLE_AXE);
    registerItem(BRONZE_BATTLE_AXE);
    registerItem(IRON_BATTLE_AXE);
    registerItem(GOLDEN_BATTLE_AXE);
    registerItem(STEEL_BATTLE_AXE);
    registerItem(BARBARIAN_BATTLE_AXE);

    registerItem(BRONZE_HALBERD);
    registerItem(IRON_HALBERD);
    registerItem(GOLDEN_HALBERD);
    registerItem(STEEL_HALBERD);

    registerItem(POINTY_STICK);
    registerItem(BONE_SPEAR);
    registerItem(STONE_SPEAR);
    registerItem(BRONZE_SPEAR);
    registerItem(IRON_SPEAR);
    registerItem(GOLDEN_SPEAR);
    registerItem(STEEL_SPEAR);

    registerItem(STONE_WAR_HAMMER);
    registerItem(BRONZE_WAR_HAMMER);
    registerItem(IRON_WAR_HAMMER);
    registerItem(GOLDEN_WAR_HAMMER);
    registerItem(STEEL_WAR_HAMMER);

    registerItem(WOODEN_MACE);
    registerItem(STONE_MACE);
    registerItem(BRONZE_MACE);
    registerItem(IRON_MACE);
    registerItem(GOLDEN_MACE);
    registerItem(STEEL_MACE);

    registerItem(BRONZE_RAPIER);
    registerItem(IRON_RAPIER);
    registerItem(GOLDEN_RAPIER);
    registerItem(STEEL_RAPIER);

    registerItem(BRONZE_SCIMITAR);
    registerItem(IRON_SCIMITAR);
    registerItem(GOLDEN_SCIMITAR);
    registerItem(STEEL_SCIMITAR);

    registerItem(WHIP);

    registerItem(ASSASSIN_HELMET);
    registerItem(ASSASSIN_CHESTPLATE);
    registerItem(ASSASSIN_LEGGINGS);
    registerItem(ASSASSIN_BOOTS);

    registerItem(BARBARIAN_HELMET);
    registerItem(BARBARIAN_CHESTPLATE);
    registerItem(BARBARIAN_LEGGINGS);
    registerItem(BARBARIAN_BOOTS);

    registerItem(GOLDEN_CHAINMAIL_HELMET);
    registerItem(GOLDEN_CHAINMAIL_CHESTPLATE);
    registerItem(GOLDEN_CHAINMAIL_LEGGINGS);
    registerItem(GOLDEN_CHAINMAIL_BOOTS);

    registerItem(CROWN);

    registerItem(MESUREING_TAPE);
    registerItem(BLUNDERBUSS);
    registerItem(MAGIC_WAND);
    registerItem(LIGHTNING);
    registerItem(SELECTOR);

    // Suppression du rendu des panneaux par défaut.
    TileEntityRendererDispatcher.instance.mapSpecialRenderers.remove(TileEntitySign.class);
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBellows.class, new TileEntityBellowsRenderer());

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new TileEntityCampfireRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChandelier.class, new TileEntityChandelierRenderer());

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCarpet.class, new TileEntityCarpetRenderer());

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCircularSaw.class, new TileEntityCircularSawRenderer());

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNormalSign.class, new TileEntityNormalSignRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOrpSign.class, new TileEntityOrpSignRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPaperSign.class, new TileEntityPaperSignRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWallNote.class, new TileEntityWallNoteRenderer());

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBookshelf.class, new TileEntityBookshelfRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPallet.class, new TileEntityPalletRenderer());

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySupport.class, new TileEntitySupportRenderer());

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShowcase.class, new TileEntityShowcaseRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWeaponsStand.class, new TileEntityWeaponsStandRenderer());

    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArmChair.class, new TileEntityArmChairRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenBench.class, new TileEntityWoodenBenchRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenChair.class, new TileEntityWoodenChairRenderer());

    // TODO 1.11
    // RenderingRegistry.registerEntityRenderingHandler(EntitySiker.class, new
    // IRenderFactory<EntitySiker>() {
    // @Override
    // public Render<EntitySiker> createRenderFor(RenderManager manager) {
    // return new RenderSiker(manager, new ModelSiker(), 0.5F);
    // }
    // });
    // RenderingRegistry.registerEntityRenderingHandler(EntityBormoth.class, new
    // IRenderFactory<EntityBormoth>() {
    // @Override
    // public Render<? super EntityBormoth> createRenderFor(RenderManager manager) {
    // return new RenderBormoth(manager, new ModelBormoth(true), new ModelBormoth(false), 0.5F);
    // }
    // });

    RenderManager render = Minecraft.getMinecraft().getRenderManager();

    RenderingRegistry.registerEntityRenderingHandler(EntityChatBubble.class, new RenderChatBubble(render));
    RenderingRegistry.registerEntityRenderingHandler(EntitySiker.class, new RenderSiker(render, new ModelSiker(), 2.0F));
    RenderingRegistry.registerEntityRenderingHandler(EntityBormoth.class, new RenderBormoth(render, new ModelBormoth(), 2.0F));
    RenderingRegistry.registerEntityRenderingHandler(EntityHoen.class, new RenderHoen(render, new ModelHoen(), 0.2F));
    RenderingRegistry.registerEntityRenderingHandler(EntityGalt.class, new RenderGalt(render, new ModelGalt(), 1.2F));
    RenderingRegistry.registerEntityRenderingHandler(EntityNiale.class, new RenderNiale(render, new ModelNiale(), 0.8F));
    RenderingRegistry.registerEntityRenderingHandler(EntityFjalla.class, new RenderFjalla(render, new ModelFjalla(), 0.8F));
    RenderingRegistry.registerEntityRenderingHandler(EntityGronle.class, new RenderGronle(render, new ModelGronle(), 0.8F));
    RenderingRegistry.registerEntityRenderingHandler(EntityPryf.class, new RenderPryf(render, new ModelPryf(), 0.8F));
    RenderingRegistry.registerEntityRenderingHandler(EntityTaure.class, new RenderTaure(render, new ModelTaure(), 0.8F));

    BiomeColorHandler.init();
  }

  /**
   * Enregistre un item.
   *
   * @param item l'item
   */
  private static void registerItem(Item item) {
    registerItem(item, 0, item.getRegistryName().getResourcePath());
  }

  /**
   * Enregistre un item.
   *
   * @param item l'item
   * @param metadata le metadata de l'item
   * @param name le nom interne de l'item
   */
  private static void registerItem(Item item, int metadata, String name) {
    if (item == null)
      return;
    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata,
        new ModelResourceLocation(item.getRegistryName().getResourceDomain() + ":" + name, "inventory"));
  }

  /**
   * Enregistre un item avec toutes ses variantes.
   *
   * @param item l'item
   * @param variants l'énumération des variantes
   */
  private static <T extends Enum<T> & IEnumType<T>> void registerItemVariants(Item item, Class<T> variants) {
    T[] types = variants.getEnumConstants();
    ResourceLocation[] names = new ResourceLocation[types.length];

    for (T variant : types) {
      registerItem(item, variant.getMetadata(), item.getRegistryName().getResourcePath() + "_" + variant.getName());
      names[variant.getMetadata()] = new ResourceLocation(Constants.MOD_ID,
          item.getRegistryName().getResourcePath().toString() + "_" + variant.getName());
    }

    ModelBakery.registerItemVariants(item, names);
  }

  /**
   * Enregistre un bloc.
   *
   * @param block le bloc
   */
  private static void registerBlock(Block block) {
    registerBlock(block, 0, block.getRegistryName().getResourcePath());
  }

  /**
   * Enregistre un bloc.
   *
   * @param block le bloc
   * @param metadata le metadata du bloc
   * @param name le nom interne du bloc
   */
  private static void registerBlock(Block block, int metadata, String name) {
    registerItem(Item.getItemFromBlock(block), metadata, name);
  }

  /**
   * Enregistre un bloc avec toutes ses variantes.
   *
   * @param block le bloc
   * @param variants l'énumération des variantes
   * @param name le nom interne du bloc
   */
  private static <T extends Enum<T> & IEnumType<T>> void registerBlockVariants(Block block, Class<T> variants) {
    T[] types = variants.getEnumConstants();
    ResourceLocation[] names = new ResourceLocation[types.length];
    Item item = Item.getItemFromBlock(block);

    for (T variant : types) {
      registerBlock(block, variant.getMetadata(), block.getRegistryName().getResourcePath() + "_" + variant.getName());
      names[variant.getMetadata()] = new ResourceLocation(block.getRegistryName().toString() + "_" + variant.getName());
    }

    if (item != null) {
      ModelBakery.registerItemVariants(item, names);
    }
  }

  /**
   * Enregistre un bloc avec toutes ses variantes de bois.
   *
   * @param block le bloc
   */
  private static void registerBlockWoodVariants(Block block) {
    BlockPlanks.EnumType[] types = BlockPlanks.EnumType.values();
    ResourceLocation[] names = new ResourceLocation[types.length];
    Item item = Item.getItemFromBlock(block);

    for (BlockPlanks.EnumType variant : types) {
      registerBlock(block, variant.getMetadata(), block.getRegistryName().getResourcePath() + "_" + variant.getName());
      names[variant.getMetadata()] = new ResourceLocation(block.getRegistryName().toString() + "_" + variant.getName());
    }

    if (item != null) {
      ModelBakery.registerItemVariants(item, names);
    }
  }

  /**
   * Enregistre un bloc avec toutes ses variantes de couleur.
   *
   * @param block le bloc
   */
  private static void registerBlockColorVariants(Block block) {
    EnumDyeColor[] types = EnumDyeColor.values();
    ResourceLocation[] names = new ResourceLocation[types.length];
    Item item = Item.getItemFromBlock(block);

    for (EnumDyeColor variant : types) {
      registerBlock(block, variant.getMetadata(), block.getRegistryName().getResourcePath() + "_" + variant.getName());
      names[variant.getMetadata()] = new ResourceLocation(block.getRegistryName().toString() + "_" + variant.getName());
    }

    if (item != null) {
      ModelBakery.registerItemVariants(item, names);
    }
  }

  /**
   * Enregistre une dalle avec toutes ses variantes.
   *
   * @param slab la dalle
   * @param doubleSlab la double dalle
   * @param variants l'énumération des variantes
   */
  private static <T extends Enum<T> & IEnumType<T>> void registerSlabVariants(McfrBlockSlab<?> slab, McfrBlockSlab<?> doubleSlab, Class<T> variants) {
    registerBlockVariants(slab, variants);
    registerBlockVariants(doubleSlab, variants);
  }

  /**
   * Enregistre une dalle avec toutes ses variantes de bois.
   *
   * @param slab la dalle
   * @param doubleSlab la double dalle
   */
  private static void registerWoodSlabVariants(McfrBlockSlab<?> slab, McfrBlockSlab<?> doubleSlab) {
    registerBlockWoodVariants(slab);
    registerBlockWoodVariants(doubleSlab);
  }
}
