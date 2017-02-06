package net.mcfr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mcfr.craftsmanship.LargeRecipe;
import net.mcfr.decoration.lighting.EnumLanternColor;
import net.mcfr.environment.plants.EnumExoticWoodType;
import net.mcfr.forge.recipes.AnvilRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;

/**
 * Classe enregistrant toutes les recettes.
 * 
 * @author Mc-Fr
 */
public final class McfrCrafts {
  private static final List<Block> LOGS;
  private static final List<Block> PLANKS;
  private static final List<Block> WOODEN_SLABS;
  private static final List<Block> WOODEN_FENCES;

  static {
    LOGS = new ArrayList<>();
    LOGS.add(Blocks.LOG);
    LOGS.add(Blocks.LOG2);
    LOGS.add(McfrBlocks.EXOTIC_LOG);

    PLANKS = new ArrayList<>();
    PLANKS.add(Blocks.PLANKS);
    PLANKS.add(McfrBlocks.EXOTIC_PLANKS);
    PLANKS.add(McfrBlocks.REFINED_PLANKS);

    WOODEN_SLABS = new ArrayList<>();
    WOODEN_SLABS.add(Blocks.WOODEN_SLAB);
    WOODEN_SLABS.add(McfrBlocks.EXOTIC_WOOD_SLAB);
    WOODEN_SLABS.add(McfrBlocks.REFINED_PLANKS_SLAB);

    WOODEN_FENCES = new ArrayList<>();
    WOODEN_FENCES.add(Blocks.OAK_FENCE);
    WOODEN_FENCES.add(Blocks.SPRUCE_FENCE);
    WOODEN_FENCES.add(Blocks.BIRCH_FENCE);
    WOODEN_FENCES.add(Blocks.JUNGLE_FENCE);
    WOODEN_FENCES.add(Blocks.ACACIA_FENCE);
    WOODEN_FENCES.add(Blocks.DARK_OAK_FENCE);
    WOODEN_FENCES.add(McfrBlocks.APPLE_WOOD_FENCE);
    WOODEN_FENCES.add(McfrBlocks.CHERRY_WOOD_FENCE);
    WOODEN_FENCES.add(McfrBlocks.PALM_FENCE);
    WOODEN_FENCES.add(McfrBlocks.BELUXIER_FENCE);
  }

  /**
   * Enregistre les recettes 3x3 et 5x5.
   */
  public static void registerCrafts() {
    RecipeSorter.register(Constants.MOD_ID + ":large", LargeRecipe.class, Category.SHAPED, "before:minecraft:shaped");

    removeRecipe(new ItemStack(Blocks.SANDSTONE_STAIRS, 4));
    removeRecipe(new ItemStack(Blocks.WOOL));
    removeRecipe(new ItemStack(Blocks.PISTON));
    removeRecipe(new ItemStack(Blocks.STICKY_PISTON));
    removeRecipe(new ItemStack(Blocks.CHEST));
    removeRecipe(new ItemStack(Blocks.TRAPPED_CHEST));
    removeRecipe(new ItemStack(Blocks.FURNACE));
    removeRecipe(new ItemStack(Blocks.STAINED_GLASS));
    removeRecipe(new ItemStack(Blocks.STAINED_GLASS_PANE));
    removeRecipe(new ItemStack(Blocks.REDSTONE_LAMP));
    for (EnumDyeColor color : EnumDyeColor.values())
      removeRecipe(new ItemStack(Blocks.HARDENED_CLAY, 8, color.getMetadata()));

    removeRecipe(new ItemStack(Items.SIGN));
    removeRecipe(new ItemStack(Items.SUGAR));
    removeRecipe(new ItemStack(Items.COOKIE));
    removeRecipe(new ItemStack(Items.PUMPKIN_PIE));
    removeRecipe(new ItemStack(Items.CAKE));
    removeRecipe(new ItemStack(Items.FLINT_AND_STEEL));
    removeRecipe(new ItemStack(Items.BOW));
    for (EnumDyeColor color : EnumDyeColor.values())
      removeRecipe(new ItemStack(Items.DYE, 1, color.getMetadata()));
    removeRecipe(new ItemStack(Items.BED));

    /*
     * Blocs
     */

    /* Minecraft */

    addShapedRecipe(new ItemStack(Blocks.STONE, 8, 1), "###", "#C#", "###", 'C', new ItemStack(Items.DYE, 1, 11), '#', new ItemStack(Blocks.STONE, 1, 0));
    addShapedRecipe(new ItemStack(Blocks.STONE, 8, 3), "###", "#C#", "###", 'C', new ItemStack(Items.DYE, 1, 14), '#', new ItemStack(Blocks.STONE, 1, 0));

    addShapedRecipe(new ItemStack(Blocks.SANDSTONE_STAIRS, 4), "#  ", "## ", "###", '#', McfrBlocks.ROUGH_SANDSTONE);

    for (Block log : LOGS)
      addLargeRecipe(new ItemStack(Blocks.PISTON), "#####", "FFFFF", "CRRRC", "CRRRC", "CCCCC", '#', log, 'C', Blocks.COBBLESTONE, 'R', Items.REDSTONE, 'F', Items.IRON_INGOT);

    for (Block planks : PLANKS) {
      addLargeRecipe(new ItemStack(Blocks.CHEST), "IWWWI", "WWWWW", "WWHWW", "WWWWW", "IWWWI", 'I', Items.IRON_INGOT, 'W', planks, 'H', Blocks.TRIPWIRE_HOOK);
      for (Block slab : WOODEN_SLABS)
        addLargeRecipe(new ItemStack(Blocks.FURNACE), "SSSSS", "CCCCC", "WCCCW", "WC CW", "WC CW", 'C', Blocks.COBBLESTONE, 'S', slab, 'W', planks, 'F', McfrBlocks.CAMPFIRE);
    }

    addShapedRecipe(new ItemStack(Blocks.FARMLAND, 2), "G", "T", 'T', new ItemStack(Blocks.DIRT), 'G', Blocks.GRAVEL);

    addShapedRecipe(new ItemStack(Blocks.STONEBRICK, 1, 2), "C", "#", 'C', Blocks.COBBLESTONE, '#', new ItemStack(Blocks.STONEBRICK));

    // Vitres colorées
    for (EnumDyeColor color : EnumDyeColor.values()) {
      int meta = color.getMetadata();
      int damage = color.getDyeDamage();

      addLargeRecipe(new ItemStack(Blocks.STAINED_GLASS, 2, meta), "SSSSS", "SGGGS", "SGCGS", "SGGGS", "SSSSS", 'C', new ItemStack(Items.DYE, 1, damage), 'S', Items.STICK, 'G', Blocks.GLASS);
      for (Block log : LOGS)
        addLargeRecipe(new ItemStack(Blocks.STAINED_GLASS_PANE, 4, meta), "BGGGB", "GGGGG", "GGCGG", "GGGGG", "BGGGB", 'C', new ItemStack(Items.DYE, 1, damage), 'B', log, 'G', Blocks.GLASS);
    }

    addLargeRecipe(new ItemStack(Blocks.REDSTONE_LAMP), "GGGGG", "GRRRG", "GRLRG", "GRRRG", "GGGGG", 'R', Items.REDSTONE, 'L', Items.LAVA_BUCKET, 'G', Blocks.GLASS);

    /* Mc-Fr */

    // Forge
    for (Block planks : PLANKS)
      addLargeRecipe(new ItemStack(McfrBlocks.BELLOWS, 1), "PPPPP", "RLLLR", "RLLLR", "RLLLR", "PPPPP", 'P', planks, 'R', McfrItems.THREAD_COIL, 'L', Blocks.WOOL);

    // Construction
    addShapelessRecipe(new ItemStack(McfrBlocks.OLD_HAY_BLOCK), Blocks.HAY_BLOCK);
    addShapelessRecipe(new ItemStack(Blocks.HAY_BLOCK), McfrBlocks.OLD_HAY_BLOCK);

    addShapedRecipe(new ItemStack(McfrBlocks.REFINED_IRON_BLOCK, 1, 0), "FF", "FF", 'F', Items.IRON_INGOT);
    addShapedRecipe(new ItemStack(McfrBlocks.REFINED_GOLD_BLOCK, 1, 0), "FF", "FF", 'F', Items.GOLD_INGOT);
    addShapedRecipe(new ItemStack(Items.IRON_INGOT, 4, 0), "#", '#', McfrBlocks.REFINED_IRON_BLOCK);
    addShapedRecipe(new ItemStack(Items.GOLD_INGOT, 4, 0), "#", '#', McfrBlocks.REFINED_GOLD_BLOCK);

    addShapedRecipe(new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 4, 0), "##", "##", '#', new ItemStack(Blocks.STONE, 1, 1));
    addShapedRecipe(new ItemStack(McfrBlocks.OCHER_STONEBRICK, 4, 0), "##", "##", '#', new ItemStack(Blocks.STONE, 1, 3));
    addShapedRecipe(new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 1), "L", "#", 'L', Blocks.VINE, '#', new ItemStack(McfrBlocks.YELLOW_STONEBRICK));
    addShapedRecipe(new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 1), "L", "#", 'L', Blocks.VINE, '#', new ItemStack(McfrBlocks.OCHER_STONEBRICK));
    addShapedRecipe(new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 2), "C", "#", 'C', Blocks.COBBLESTONE, '#', new ItemStack(McfrBlocks.OCHER_STONEBRICK));
    addShapedRecipe(new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 2), "C", "#", 'C', Blocks.COBBLESTONE, '#', new ItemStack(McfrBlocks.OCHER_STONEBRICK));

    addShapedRecipe(new ItemStack(McfrBlocks.DAUB, 2), " F ", "BBB", "AAA", 'F', new ItemStack(Items.POTIONITEM), 'B', Items.WHEAT, 'A', Items.CLAY_BALL);
    addLargeRecipe(new ItemStack(McfrBlocks.TIMBERED_BLOCK, 16, 0), "STTTS", "TSTST", "TTSTT", "TSTST", "STTTS", 'S', Items.STICK, 'T', McfrBlocks.DAUB);
    addLargeRecipe(new ItemStack(McfrBlocks.TIMBERED_BLOCK, 12, 1), "STTTS", "STTSS", "STSTS", "SSTTS", "STTTS", 'S', Items.STICK, 'T', McfrBlocks.DAUB);
    addLargeRecipe(new ItemStack(McfrBlocks.TIMBERED_BLOCK, 16, 3), "SSSSS", "TTTTT", "TTTTT", "TTTTT", "SSSSS", 'S', Items.STICK, 'T', McfrBlocks.DAUB);
    addLargeRecipe(new ItemStack(McfrBlocks.TIMBERED_BLOCK, 16, 4), "STTTS", "STTTS", "STTTS", "STTTS", "STTTS", 'S', Items.STICK, 'T', McfrBlocks.DAUB);
    addShapedRecipe(new ItemStack(McfrBlocks.TIMBERED_BLOCK, 1, 1), "#", '#', new ItemStack(McfrBlocks.TIMBERED_BLOCK, 1, 2));
    addShapedRecipe(new ItemStack(McfrBlocks.TIMBERED_BLOCK, 1, 2), "#", '#', new ItemStack(McfrBlocks.TIMBERED_BLOCK, 1, 1));

    addLargeRecipe(new ItemStack(McfrBlocks.TILES, 4, 0), "B B B", "B B B", " B B ", 'B', Items.BRICK);
    addLargeRecipe(new ItemStack(McfrBlocks.TILES, 4, 1), "B B B", "B B B", " B B ", 'B', new ItemStack(Blocks.STONE, 1, 5));

    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE, 6, 1), "SSSSS", "S S S", "SS SS", "S S S", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 2));
    addShapedRecipe(new ItemStack(McfrBlocks.MARBLE, 2, 2), "SSS", "SES", "SSS", 'S', Blocks.SANDSTONE, 'E', new ItemStack(Items.POTIONITEM, 1, 0));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE, 6, 4), "SSSSS", "S S S", "SS SS", "S S S", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 5));
    addShapedRecipe(new ItemStack(McfrBlocks.MARBLE, 2, 5), "SSS", "SES", "SSS", 'S', Blocks.STONE, 'E', new ItemStack(Items.POTIONITEM, 1, 0));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE, 6, 7), "SSSSS", "S S S", "SS SS", "S S S", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 8));
    addShapedRecipe(new ItemStack(McfrBlocks.MARBLE, 2, 8), "SSS", "SES", "SSS", 'S', Blocks.OBSIDIAN, 'E', Items.WATER_BUCKET);

    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_COLUMN, 8, 0), "S S S", "S S S", "S S S", "S S S", "S S S", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 2));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_COLUMN, 8, 1), "S S S", "S S S", "S S S", "S S S", "S S S", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 5));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_COLUMN, 8, 2), "S S S", "S S S", "S S S", "S S S", "S S S", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 8));

    // Ateliers
    for (Block planks : PLANKS) {
      for (Block fence : WOODEN_FENCES) {
        addLargeRecipe(new ItemStack(McfrBlocks.LOOM), "FSSSF", "FIIIF", "FIIIF", "FSSSF", "W   W", 'F', fence, 'S', Items.STICK, 'I', Items.STRING, 'W', planks);
        addLargeRecipe(new ItemStack(McfrBlocks.TANNING_RACK), "FMSMF", "FCCCF", "FCCCF", "FMSMF", "W   W", 'F', fence, 'S', Items.STICK, 'M', McfrItems.STITCH, 'C', Items.LEATHER, 'W', planks);
      }
    }
    addShapedRecipe(new ItemStack(McfrBlocks.CIRCULAR_SAW, 1), "S", "B", 'S', McfrItems.SAW_BLADE, 'B', McfrItems.SAW_SUPPORT);
    for (Block planks : PLANKS)
      addShapedRecipe(new ItemStack(McfrBlocks.LARGE_WORKBENCH, 1), "IWI", "WWW", "IWI", 'I', Items.IRON_INGOT, 'W', planks);

    // Coffres
    for (Block planks : PLANKS) {
      addLargeRecipe(new ItemStack(McfrBlocks.LITTLE_CHEST, 1), "IIIII", "IWWWI", "IIHII", "IWWWI", "IIWII", 'I', Items.IRON_INGOT, 'W', planks, 'H', Blocks.TRIPWIRE_HOOK);
      addShapedRecipe(new ItemStack(McfrBlocks.CRATE, 1), "###", "###", "###", '#', planks);
      addShapedRecipe(new ItemStack(McfrBlocks.FOOD_CRATE, 1), "WWW", "FFF", "WWW", 'W', planks, 'F', Items.STRING);
      addShapedRecipe(new ItemStack(McfrBlocks.PALLET, 1), "SSS", "WWW", 'W', planks, 'S', Items.STICK);
    }
    for (int i = 0; i < BlockPlanks.EnumType.values().length; i++)
      addLargeRecipe(new ItemStack(McfrBlocks.BOOKSHELF, 1, i), "#####", "#   #", "#SSS#", "#   #", "#####", '#', new ItemStack(Blocks.PLANKS, 1, i), 'S', new ItemStack(Blocks.WOODEN_SLAB, 1, i));

    // Dalles
    addShapedRecipe(new ItemStack(McfrBlocks.HAY_SLAB, 6, 0), "###", '#', Blocks.HAY_BLOCK);
    addShapedRecipe(new ItemStack(McfrBlocks.HAY_SLAB, 6, 1), "###", '#', McfrBlocks.OLD_HAY_BLOCK);

    for (BlockPlanks.EnumType type : BlockPlanks.EnumType.values())
      addLargeRecipe(new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 8, type.getMetadata()), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, type.getMetadata()));

    for (EnumExoticWoodType type : EnumExoticWoodType.values())
      addShapedRecipe(new ItemStack(McfrBlocks.EXOTIC_WOOD_SLAB, 6, type.getMetadata()), "###", '#', new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, type.getMetadata()));

    addLargeRecipe(new ItemStack(McfrBlocks.METAL_SLAB, 8, 0), "SSSSS", "SSSSS", 'S', McfrBlocks.REFINED_IRON_BLOCK);
    addLargeRecipe(new ItemStack(McfrBlocks.METAL_SLAB, 8, 1), "SSSSS", "SSSSS", 'S', McfrBlocks.REFINED_GOLD_BLOCK);

    addShapedRecipe(new ItemStack(McfrBlocks.STONE_SLAB, 6, 0), "###", '#', new ItemStack(Blocks.STONE, 1, 1));
    addShapedRecipe(new ItemStack(McfrBlocks.STONE_SLAB, 6, 1), "###", '#', new ItemStack(Blocks.STONE, 1, 2));
    addShapedRecipe(new ItemStack(McfrBlocks.STONE_SLAB, 6, 2), "###", '#', new ItemStack(Blocks.STONE, 1, 3));
    addShapedRecipe(new ItemStack(McfrBlocks.STONE_SLAB, 6, 3), "###", '#', new ItemStack(Blocks.STONE, 1, 4));
    addLargeRecipe(new ItemStack(McfrBlocks.STONE_SLAB, 8, 4), "SSSSS", "SSSSS", 'S', new ItemStack(Blocks.STONE, 1, 5));
    addLargeRecipe(new ItemStack(McfrBlocks.STONE_SLAB, 8, 5), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.TILES, 1, 0));
    addLargeRecipe(new ItemStack(McfrBlocks.STONE_SLAB, 8, 6), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.TILES, 1, 1));

    addShapedRecipe(new ItemStack(McfrBlocks.STONEBRICK_SLAB, 6, 0), "###", '#', new ItemStack(Blocks.STONEBRICK, 1, 1));
    addShapedRecipe(new ItemStack(McfrBlocks.STONEBRICK_SLAB, 6, 1), "###", '#', new ItemStack(Blocks.STONEBRICK, 1, 2));
    addShapedRecipe(new ItemStack(McfrBlocks.STONEBRICK_SLAB, 6, 2), "###", '#', new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 0));
    addShapedRecipe(new ItemStack(McfrBlocks.STONEBRICK_SLAB, 6, 3), "###", '#', new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 1));
    addShapedRecipe(new ItemStack(McfrBlocks.STONEBRICK_SLAB, 6, 4), "###", '#', new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 2));
    addShapedRecipe(new ItemStack(McfrBlocks.STONEBRICK_SLAB, 6, 5), "###", '#', new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 0));
    addShapedRecipe(new ItemStack(McfrBlocks.STONEBRICK_SLAB, 6, 6), "###", '#', new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 1));
    addShapedRecipe(new ItemStack(McfrBlocks.STONEBRICK_SLAB, 6, 7), "###", '#', new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 2));

    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_SLAB, 8, 0), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 0));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_SLAB, 8, 1), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE_COLUMN, 1, 0));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_SLAB, 8, 2), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 2));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_SLAB, 8, 3), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 3));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_SLAB, 8, 4), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE_COLUMN, 1, 1));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_SLAB, 8, 5), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 4));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_SLAB2, 8, 0), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 6));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_SLAB2, 8, 1), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE_COLUMN, 1, 2));
    addLargeRecipe(new ItemStack(McfrBlocks.MARBLE_SLAB2, 8, 2), "SSSSS", "SSSSS", 'S', new ItemStack(McfrBlocks.MARBLE, 1, 8));

    // Escaliers
    addStairsRecipe(McfrBlocks.STONE_STAIRS, new ItemStack(Blocks.STONE));
    addStairsRecipe(Blocks.STONE_STAIRS, new ItemStack(Blocks.COBBLESTONE));
    addStairsRecipe(Blocks.OAK_STAIRS, new ItemStack(Blocks.PLANKS, 1, 0));
    addStairsRecipe(Blocks.SPRUCE_STAIRS, new ItemStack(Blocks.PLANKS, 1, 1));
    addStairsRecipe(Blocks.BIRCH_STAIRS, new ItemStack(Blocks.PLANKS, 1, 2));
    addStairsRecipe(Blocks.JUNGLE_STAIRS, new ItemStack(Blocks.PLANKS, 1, 3));
    addStairsRecipe(Blocks.ACACIA_STAIRS, new ItemStack(Blocks.PLANKS, 1, 4));
    addStairsRecipe(Blocks.DARK_OAK_STAIRS, new ItemStack(Blocks.PLANKS, 1, 5));
    addStairsRecipe(McfrBlocks.OAK_LOG_STAIRS, new ItemStack(Blocks.LOG, 1, 0));
    addStairsRecipe(McfrBlocks.SPRUCE_LOG_STAIRS, new ItemStack(Blocks.LOG, 1, 1));
    addStairsRecipe(McfrBlocks.BIRCH_LOG_STAIRS, new ItemStack(Blocks.LOG, 1, 2));
    addStairsRecipe(McfrBlocks.JUNGLE_LOG_STAIRS, new ItemStack(Blocks.LOG, 1, 3));
    addStairsRecipe(McfrBlocks.ACACIA_LOG_STAIRS, new ItemStack(Blocks.LOG2, 1, 0));
    addStairsRecipe(McfrBlocks.DARK_OAK_LOG_STAIRS, new ItemStack(Blocks.LOG2, 1, 1));
    addStairsRecipe(Blocks.SANDSTONE_STAIRS, new ItemStack(Blocks.SANDSTONE, 1, 0));
    addStairsRecipe(McfrBlocks.CHISELED_SANDSTONE_STAIRS, new ItemStack(Blocks.SANDSTONE, 1, 1));
    addStairsRecipe(McfrBlocks.SMOOTH_SANDSTONE_STAIRS, new ItemStack(Blocks.SANDSTONE, 1, 2));
    addStairsRecipe(McfrBlocks.WHITE_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 0));
    addStairsRecipe(McfrBlocks.ORANGE_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 1));
    addStairsRecipe(McfrBlocks.MAGENTA_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 2));
    addStairsRecipe(McfrBlocks.LIGHT_BLUE_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 3));
    addStairsRecipe(McfrBlocks.YELLOW_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 4));
    addStairsRecipe(McfrBlocks.LIME_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 5));
    addStairsRecipe(McfrBlocks.PINK_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 6));
    addStairsRecipe(McfrBlocks.GRAY_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 7));
    addStairsRecipe(McfrBlocks.LIGHT_GRAY_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 8));
    addStairsRecipe(McfrBlocks.CYAN_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 9));
    addStairsRecipe(McfrBlocks.PURPLE_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 10));
    addStairsRecipe(McfrBlocks.BLUE_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 11));
    addStairsRecipe(McfrBlocks.BROWN_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 12));
    addStairsRecipe(McfrBlocks.GREEN_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 13));
    addStairsRecipe(McfrBlocks.RED_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 14));
    addStairsRecipe(McfrBlocks.BLACK_WOOL_STAIRS, new ItemStack(Blocks.WOOL, 1, 15));
    addStairsRecipe(McfrBlocks.GOLDEN_STAIRS, new ItemStack(McfrBlocks.REFINED_GOLD_BLOCK));
    addStairsRecipe(McfrBlocks.IRON_STAIRS, new ItemStack(McfrBlocks.REFINED_IRON_BLOCK));
    addStairsRecipe(Blocks.BRICK_STAIRS, new ItemStack(Blocks.BRICK_BLOCK));
    addStairsRecipe(McfrBlocks.MOSSY_COBBLESTONE_STAIRS, new ItemStack(Blocks.MOSSY_COBBLESTONE));
    addStairsRecipe(Blocks.STONE_BRICK_STAIRS, new ItemStack(Blocks.STONEBRICK, 1, 0));
    addStairsRecipe(McfrBlocks.MOSSY_STONEBRICK_STAIRS, new ItemStack(Blocks.STONEBRICK, 1, 1));
    addStairsRecipe(McfrBlocks.CRACKED_STONEBRICK_STAIRS, new ItemStack(Blocks.STONEBRICK, 1, 2));
    addStairsRecipe(McfrBlocks.WHITE_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0));
    addStairsRecipe(McfrBlocks.ORANGE_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 1));
    addStairsRecipe(McfrBlocks.MAGENTA_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 2));
    addStairsRecipe(McfrBlocks.LIGHT_BLUE_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 3));
    addStairsRecipe(McfrBlocks.YELLOW_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 4));
    addStairsRecipe(McfrBlocks.LIME_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 5));
    addStairsRecipe(McfrBlocks.PINK_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 6));
    addStairsRecipe(McfrBlocks.GRAY_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 7));
    addStairsRecipe(McfrBlocks.LIGHT_GRAY_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 8));
    addStairsRecipe(McfrBlocks.CYAN_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 9));
    addStairsRecipe(McfrBlocks.PURPLE_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 10));
    addStairsRecipe(McfrBlocks.BLUE_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 11));
    addStairsRecipe(McfrBlocks.BROWN_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 12));
    addStairsRecipe(McfrBlocks.GREEN_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 13));
    addStairsRecipe(McfrBlocks.RED_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 14));
    addStairsRecipe(McfrBlocks.BLACK_CLAY_STAIRS, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 15));
    addStairsRecipe(McfrBlocks.HAY_STAIRS, new ItemStack(Blocks.HAY_BLOCK));
    addStairsRecipe(McfrBlocks.APPLE_PLANKS_STAIRS, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 0));
    addStairsRecipe(McfrBlocks.CHERRY_PLANKS_STAIRS, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 1));
    addStairsRecipe(McfrBlocks.PALM_PLANKS_STAIRS, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 2));
    addStairsRecipe(McfrBlocks.BELUXIER_PLANKS_STAIRS, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 3));
    addStairsRecipe(McfrBlocks.APPLE_LOG_STAIRS, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 0));
    addStairsRecipe(McfrBlocks.CHERRY_LOG_STAIRS, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 1));
    addStairsRecipe(McfrBlocks.PALM_LOG_STAIRS, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 2));
    addStairsRecipe(McfrBlocks.BELUXIER_LOG_STAIRS, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 3));
    addStairsRecipe(McfrBlocks.REFINED_OAK_STAIRS, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0));
    addStairsRecipe(McfrBlocks.REFINED_SPRUCE_STAIRS, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1));
    addStairsRecipe(McfrBlocks.REFINED_BIRCH_STAIRS, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2));
    addStairsRecipe(McfrBlocks.REFINED_JUNGLE_STAIRS, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3));
    addStairsRecipe(McfrBlocks.REFINED_ACACIA_STAIRS, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 4));
    addStairsRecipe(McfrBlocks.REFINED_DARK_OAK_STAIRS, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 5));
    addStairsRecipe(McfrBlocks.ROUGH_SANDSTONE_STAIRS, new ItemStack(McfrBlocks.ROUGH_SANDSTONE));
    addStairsRecipe(McfrBlocks.YELLOW_STONE_STAIRS, new ItemStack(Blocks.STONE, 1, 1));
    addStairsRecipe(McfrBlocks.OCHER_STONE_STAIRS, new ItemStack(Blocks.STONE, 1, 3));
    addStairsRecipe(McfrBlocks.YELLOW_COBBLESTONE_STAIRS, new ItemStack(Blocks.STONE, 1, 2));
    addStairsRecipe(McfrBlocks.OCHER_COBBLESTONE_STAIRS, new ItemStack(Blocks.STONE, 1, 4));
    addStairsRecipe(McfrBlocks.YELLOW_STONEBRICK_STAIRS, new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 0));
    addStairsRecipe(McfrBlocks.YELLOW_MOSSY_STONEBRICK_STAIRS, new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 1));
    addStairsRecipe(McfrBlocks.YELLOW_CRACKED_STONEBRICK_STAIRS, new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 2));
    addStairsRecipe(McfrBlocks.OCHER_STONEBRICK_STAIRS, new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 0));
    addStairsRecipe(McfrBlocks.OCHER_MOSSY_STONEBRICK_STAIRS, new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 1));
    addStairsRecipe(McfrBlocks.OCHER_CRACKED_STONEBRICK_STAIRS, new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 2));
    addStairsRecipe(McfrBlocks.BRICK_TILES_STAIRS, new ItemStack(McfrBlocks.TILES, 1, 0));
    addStairsRecipe(McfrBlocks.SLATE_TILES_STAIRS, new ItemStack(McfrBlocks.TILES, 1, 1));
    addStairsRecipe(McfrBlocks.LIGHT_CARVED_CLAY_STAIRS, new ItemStack(McfrBlocks.CARVED_CLAY, 1, 0));
    addStairsRecipe(McfrBlocks.DARK_CARVED_CLAY_STAIRS, new ItemStack(McfrBlocks.CARVED_CLAY, 1, 1));

    // Pentes
    addSlopeRecipe(McfrBlocks.STONE_SLOPE, new ItemStack(Blocks.STONE));
    addSlopeRecipe(McfrBlocks.COBBLESTONE_SLOPE, new ItemStack(Blocks.COBBLESTONE));
    addSlopeRecipe(McfrBlocks.OAK_PLANKS_SLOPE, new ItemStack(Blocks.PLANKS, 1, 0));
    addSlopeRecipe(McfrBlocks.SPRUCE_PLANKS_SLOPE, new ItemStack(Blocks.PLANKS, 1, 1));
    addSlopeRecipe(McfrBlocks.BIRCH_PLANKS_SLOPE, new ItemStack(Blocks.PLANKS, 1, 2));
    addSlopeRecipe(McfrBlocks.JUNGLE_PLANKS_SLOPE, new ItemStack(Blocks.PLANKS, 1, 3));
    addSlopeRecipe(McfrBlocks.ACACIA_PLANKS_SLOPE, new ItemStack(Blocks.PLANKS, 1, 4));
    addSlopeRecipe(McfrBlocks.DARK_OAK_PLANKS_SLOPE, new ItemStack(Blocks.PLANKS, 1, 5));
    addSlopeRecipe(McfrBlocks.OAK_LOG_SLOPE, new ItemStack(Blocks.LOG, 1, 0));
    addSlopeRecipe(McfrBlocks.SPRUCE_LOG_SLOPE, new ItemStack(Blocks.LOG, 1, 1));
    addSlopeRecipe(McfrBlocks.BIRCH_LOG_SLOPE, new ItemStack(Blocks.LOG, 1, 2));
    addSlopeRecipe(McfrBlocks.JUNGLE_LOG_SLOPE, new ItemStack(Blocks.LOG, 1, 3));
    addSlopeRecipe(McfrBlocks.ACACIA_LOG_SLOPE, new ItemStack(Blocks.LOG2, 1, 0));
    addSlopeRecipe(McfrBlocks.DARK_OAK_LOG_SLOPE, new ItemStack(Blocks.LOG2, 1, 1));
    addSlopeRecipe(McfrBlocks.OAK_LEAVES_SLOPE, new ItemStack(Blocks.LEAVES, 1, 0));
    addSlopeRecipe(McfrBlocks.SPRUCE_LEAVES_SLOPE, new ItemStack(Blocks.LEAVES, 1, 1));
    addSlopeRecipe(McfrBlocks.BIRCH_LEAVES_SLOPE, new ItemStack(Blocks.LEAVES, 1, 2));
    addSlopeRecipe(McfrBlocks.JUNGLE_LEAVES_SLOPE, new ItemStack(Blocks.LEAVES, 1, 3));
    addSlopeRecipe(McfrBlocks.ACACIA_LEAVES_SLOPE, new ItemStack(Blocks.LEAVES2, 1, 0));
    addSlopeRecipe(McfrBlocks.DARK_OAK_LEAVES_SLOPE, new ItemStack(Blocks.LEAVES2, 1, 1));
    addSlopeRecipe(McfrBlocks.SANDSTONE_SLOPE, new ItemStack(Blocks.SANDSTONE, 1, 0));
    addSlopeRecipe(McfrBlocks.CHISELED_SANDSTONE_SLOPE, new ItemStack(Blocks.SANDSTONE, 1, 1));
    addSlopeRecipe(McfrBlocks.SMOOTH_SANDSTONE_SLOPE, new ItemStack(Blocks.SANDSTONE, 1, 2));
    addSlopeRecipe(McfrBlocks.WHITE_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 0));
    addSlopeRecipe(McfrBlocks.ORANGE_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 1));
    addSlopeRecipe(McfrBlocks.MAGENTA_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 2));
    addSlopeRecipe(McfrBlocks.LIGHT_BLUE_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 3));
    addSlopeRecipe(McfrBlocks.YELLOW_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 4));
    addSlopeRecipe(McfrBlocks.LIME_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 5));
    addSlopeRecipe(McfrBlocks.PINK_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 6));
    addSlopeRecipe(McfrBlocks.GRAY_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 7));
    addSlopeRecipe(McfrBlocks.LIGHT_GRAY_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 8));
    addSlopeRecipe(McfrBlocks.CYAN_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 9));
    addSlopeRecipe(McfrBlocks.PURPLE_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 10));
    addSlopeRecipe(McfrBlocks.BLUE_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 11));
    addSlopeRecipe(McfrBlocks.BROWN_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 12));
    addSlopeRecipe(McfrBlocks.GREEN_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 13));
    addSlopeRecipe(McfrBlocks.RED_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 14));
    addSlopeRecipe(McfrBlocks.BLACK_WOOL_SLOPE, new ItemStack(Blocks.WOOL, 1, 15));
    addSlopeRecipe(McfrBlocks.GOLDEN_SLOPE, new ItemStack(McfrBlocks.REFINED_GOLD_BLOCK));
    addSlopeRecipe(McfrBlocks.IRON_SLOPE, new ItemStack(McfrBlocks.REFINED_IRON_BLOCK));
    addSlopeRecipe(McfrBlocks.BRICK_SLOPE, new ItemStack(Blocks.BRICK_BLOCK));
    addSlopeRecipe(McfrBlocks.MOSSY_COBBLESTONE_SLOPE, new ItemStack(Blocks.MOSSY_COBBLESTONE));
    addSlopeRecipe(McfrBlocks.STONEBRICK_SLOPE, new ItemStack(Blocks.STONEBRICK, 1, 0));
    addSlopeRecipe(McfrBlocks.MOSSY_STONEBRICK_SLOPE, new ItemStack(Blocks.STONEBRICK, 1, 1));
    addSlopeRecipe(McfrBlocks.CRACKED_STONEBRICK_SLOPE, new ItemStack(Blocks.STONEBRICK, 1, 2));
    addSlopeRecipe(McfrBlocks.WHITE_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0));
    addSlopeRecipe(McfrBlocks.ORANGE_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 1));
    addSlopeRecipe(McfrBlocks.MAGENTA_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 2));
    addSlopeRecipe(McfrBlocks.LIGHT_BLUE_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 3));
    addSlopeRecipe(McfrBlocks.YELLOW_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 4));
    addSlopeRecipe(McfrBlocks.LIME_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 5));
    addSlopeRecipe(McfrBlocks.PINK_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 6));
    addSlopeRecipe(McfrBlocks.GRAY_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 7));
    addSlopeRecipe(McfrBlocks.LIGHT_GRAY_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 8));
    addSlopeRecipe(McfrBlocks.CYAN_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 9));
    addSlopeRecipe(McfrBlocks.PURPLE_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 10));
    addSlopeRecipe(McfrBlocks.BLUE_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 11));
    addSlopeRecipe(McfrBlocks.BROWN_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 12));
    addSlopeRecipe(McfrBlocks.GREEN_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 13));
    addSlopeRecipe(McfrBlocks.RED_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 14));
    addSlopeRecipe(McfrBlocks.BLACK_CLAY_SLOPE, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 15));
    addSlopeRecipe(McfrBlocks.HAY_SLOPE, new ItemStack(Blocks.HAY_BLOCK));
    addSlopeRecipe(McfrBlocks.APPLE_PLANKS_SLOPE, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 0));
    addSlopeRecipe(McfrBlocks.CHERRY_PLANKS_SLOPE, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 1));
    addSlopeRecipe(McfrBlocks.PALM_PLANKS_SLOPE, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 2));
    addSlopeRecipe(McfrBlocks.BELUXIER_PLANKS_SLOPE, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 3));
    addSlopeRecipe(McfrBlocks.APPLE_LOG_SLOPE, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 0));
    addSlopeRecipe(McfrBlocks.CHERRY_LOG_SLOPE, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 1));
    addSlopeRecipe(McfrBlocks.PALM_LOG_SLOPE, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 2));
    addSlopeRecipe(McfrBlocks.BELUXIER_LOG_SLOPE, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 3));
    addSlopeRecipe(McfrBlocks.APPLE_LEAVES_SLOPE, new ItemStack(McfrBlocks.EXOTIC_LEAVES, 1, 0));
    addSlopeRecipe(McfrBlocks.CHERRY_LEAVES_SLOPE, new ItemStack(McfrBlocks.EXOTIC_LEAVES, 1, 1));
    addSlopeRecipe(McfrBlocks.PALM_LEAVES_SLOPE, new ItemStack(McfrBlocks.EXOTIC_LEAVES, 1, 2));
    addSlopeRecipe(McfrBlocks.BELUXIER_LEAVES_SLOPE, new ItemStack(McfrBlocks.EXOTIC_LEAVES, 1, 3));
    addSlopeRecipe(McfrBlocks.REFINED_OAK_SLOPE, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0));
    addSlopeRecipe(McfrBlocks.REFINED_SPRUCE_SLOPE, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1));
    addSlopeRecipe(McfrBlocks.REFINED_BIRCH_SLOPE, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2));
    addSlopeRecipe(McfrBlocks.REFINED_JUNGLE_SLOPE, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3));
    addSlopeRecipe(McfrBlocks.REFINED_ACACIA_SLOPE, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 4));
    addSlopeRecipe(McfrBlocks.REFINED_DARK_OAK_SLOPE, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 5));
    addSlopeRecipe(McfrBlocks.ROUGH_SANDSTONE_SLOPE, new ItemStack(McfrBlocks.ROUGH_SANDSTONE));
    addSlopeRecipe(McfrBlocks.YELLOW_STONE_SLOPE, new ItemStack(Blocks.STONE, 1, 1));
    addSlopeRecipe(McfrBlocks.OCHER_STONE_SLOPE, new ItemStack(Blocks.STONE, 1, 3));
    addSlopeRecipe(McfrBlocks.YELLOW_COBBLESTONE_SLOPE, new ItemStack(Blocks.STONE, 1, 2));
    addSlopeRecipe(McfrBlocks.OCHER_COBBLESTONE_SLOPE, new ItemStack(Blocks.STONE, 1, 4));
    addSlopeRecipe(McfrBlocks.YELLOW_STONEBRICK_SLOPE, new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 0));
    addSlopeRecipe(McfrBlocks.YELLOW_MOSSY_STONEBRICK_SLOPE, new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 1));
    addSlopeRecipe(McfrBlocks.YELLOW_CRACKED_STONEBRICK_SLOPE, new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 2));
    addSlopeRecipe(McfrBlocks.OCHER_STONEBRICK_SLOPE, new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 0));
    addSlopeRecipe(McfrBlocks.OCHER_MOSSY_STONEBRICK_SLOPE, new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 1));
    addSlopeRecipe(McfrBlocks.OCHER_CRACKED_STONEBRICK_SLOPE, new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 2));
    addSlopeRecipe(McfrBlocks.BRICK_TILES_SLOPE, new ItemStack(McfrBlocks.TILES, 1, 0));
    addSlopeRecipe(McfrBlocks.SLATE_TILES_SLOPE, new ItemStack(McfrBlocks.TILES, 1, 1));
    addSlopeRecipe(McfrBlocks.LIGHT_CARVED_CLAY_SLOPE, new ItemStack(McfrBlocks.CARVED_CLAY, 1, 0));
    addSlopeRecipe(McfrBlocks.DARK_CARVED_CLAY_SLOPE, new ItemStack(McfrBlocks.CARVED_CLAY, 1, 1));

    // Pyramides
    addPyramidRecipe(McfrBlocks.STONE_PYRAMID, new ItemStack(Blocks.STONE));
    addPyramidRecipe(McfrBlocks.COBBLESTONE_PYRAMID, new ItemStack(Blocks.COBBLESTONE));
    addPyramidRecipe(McfrBlocks.OAK_PLANKS_PYRAMID, new ItemStack(Blocks.PLANKS, 1, 0));
    addPyramidRecipe(McfrBlocks.SPRUCE_PLANKS_PYRAMID, new ItemStack(Blocks.PLANKS, 1, 1));
    addPyramidRecipe(McfrBlocks.BIRCH_PLANKS_PYRAMID, new ItemStack(Blocks.PLANKS, 1, 2));
    addPyramidRecipe(McfrBlocks.JUNGLE_PLANKS_PYRAMID, new ItemStack(Blocks.PLANKS, 1, 3));
    addPyramidRecipe(McfrBlocks.ACACIA_PLANKS_PYRAMID, new ItemStack(Blocks.PLANKS, 1, 4));
    addPyramidRecipe(McfrBlocks.DARK_OAK_PLANKS_PYRAMID, new ItemStack(Blocks.PLANKS, 1, 5));
    addPyramidRecipe(McfrBlocks.OAK_LOG_PYRAMID, new ItemStack(Blocks.LOG, 1, 0));
    addPyramidRecipe(McfrBlocks.SPRUCE_LOG_PYRAMID, new ItemStack(Blocks.LOG, 1, 1));
    addPyramidRecipe(McfrBlocks.BIRCH_LOG_PYRAMID, new ItemStack(Blocks.LOG, 1, 2));
    addPyramidRecipe(McfrBlocks.JUNGLE_LOG_PYRAMID, new ItemStack(Blocks.LOG, 1, 3));
    addPyramidRecipe(McfrBlocks.ACACIA_LOG_PYRAMID, new ItemStack(Blocks.LOG2, 1, 0));
    addPyramidRecipe(McfrBlocks.DARK_OAK_LOG_PYRAMID, new ItemStack(Blocks.LOG2, 1, 1));
    addPyramidRecipe(McfrBlocks.OAK_LEAVES_PYRAMID, new ItemStack(Blocks.LEAVES, 1, 0));
    addPyramidRecipe(McfrBlocks.SPRUCE_LEAVES_PYRAMID, new ItemStack(Blocks.LEAVES, 1, 1));
    addPyramidRecipe(McfrBlocks.BIRCH_LEAVES_PYRAMID, new ItemStack(Blocks.LEAVES, 1, 2));
    addPyramidRecipe(McfrBlocks.JUNGLE_LEAVES_PYRAMID, new ItemStack(Blocks.LEAVES, 1, 3));
    addPyramidRecipe(McfrBlocks.ACACIA_LEAVES_PYRAMID, new ItemStack(Blocks.LEAVES2, 1, 0));
    addPyramidRecipe(McfrBlocks.DARK_OAK_LEAVES_PYRAMID, new ItemStack(Blocks.LEAVES2, 1, 1));
    addPyramidRecipe(McfrBlocks.SANDSTONE_PYRAMID, new ItemStack(Blocks.SANDSTONE, 1, 0));
    addPyramidRecipe(McfrBlocks.CHISELED_SANDSTONE_PYRAMID, new ItemStack(Blocks.SANDSTONE, 1, 1));
    addPyramidRecipe(McfrBlocks.SMOOTH_SANDSTONE_PYRAMID, new ItemStack(Blocks.SANDSTONE, 1, 2));
    addPyramidRecipe(McfrBlocks.WHITE_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 0));
    addPyramidRecipe(McfrBlocks.ORANGE_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 1));
    addPyramidRecipe(McfrBlocks.MAGENTA_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 2));
    addPyramidRecipe(McfrBlocks.LIGHT_BLUE_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 3));
    addPyramidRecipe(McfrBlocks.YELLOW_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 4));
    addPyramidRecipe(McfrBlocks.LIME_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 5));
    addPyramidRecipe(McfrBlocks.PINK_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 6));
    addPyramidRecipe(McfrBlocks.GRAY_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 7));
    addPyramidRecipe(McfrBlocks.LIGHT_GRAY_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 8));
    addPyramidRecipe(McfrBlocks.CYAN_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 9));
    addPyramidRecipe(McfrBlocks.PURPLE_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 10));
    addPyramidRecipe(McfrBlocks.BLUE_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 11));
    addPyramidRecipe(McfrBlocks.BROWN_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 12));
    addPyramidRecipe(McfrBlocks.GREEN_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 13));
    addPyramidRecipe(McfrBlocks.RED_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 14));
    addPyramidRecipe(McfrBlocks.BLACK_WOOL_PYRAMID, new ItemStack(Blocks.WOOL, 1, 15));
    addPyramidRecipe(McfrBlocks.GOLDEN_PYRAMID, new ItemStack(McfrBlocks.REFINED_GOLD_BLOCK));
    addPyramidRecipe(McfrBlocks.IRON_PYRAMID, new ItemStack(McfrBlocks.REFINED_IRON_BLOCK));
    addPyramidRecipe(McfrBlocks.BRICK_PYRAMID, new ItemStack(Blocks.BRICK_BLOCK));
    addPyramidRecipe(McfrBlocks.MOSSY_COBBLESTONE_PYRAMID, new ItemStack(Blocks.MOSSY_COBBLESTONE));
    addPyramidRecipe(McfrBlocks.STONEBRICK_PYRAMID, new ItemStack(Blocks.STONEBRICK, 1, 0));
    addPyramidRecipe(McfrBlocks.MOSSY_STONEBRICK_PYRAMID, new ItemStack(Blocks.STONEBRICK, 1, 1));
    addPyramidRecipe(McfrBlocks.CRACKED_STONEBRICK_PYRAMID, new ItemStack(Blocks.STONEBRICK, 1, 2));
    addPyramidRecipe(McfrBlocks.WHITE_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0));
    addPyramidRecipe(McfrBlocks.ORANGE_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 1));
    addPyramidRecipe(McfrBlocks.MAGENTA_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 2));
    addPyramidRecipe(McfrBlocks.LIGHT_BLUE_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 3));
    addPyramidRecipe(McfrBlocks.YELLOW_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 4));
    addPyramidRecipe(McfrBlocks.LIME_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 5));
    addPyramidRecipe(McfrBlocks.PINK_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 6));
    addPyramidRecipe(McfrBlocks.GRAY_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 7));
    addPyramidRecipe(McfrBlocks.LIGHT_GRAY_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 8));
    addPyramidRecipe(McfrBlocks.CYAN_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 9));
    addPyramidRecipe(McfrBlocks.PURPLE_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 10));
    addPyramidRecipe(McfrBlocks.BLUE_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 11));
    addPyramidRecipe(McfrBlocks.BROWN_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 12));
    addPyramidRecipe(McfrBlocks.GREEN_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 13));
    addPyramidRecipe(McfrBlocks.RED_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 14));
    addPyramidRecipe(McfrBlocks.BLACK_CLAY_PYRAMID, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 15));
    addPyramidRecipe(McfrBlocks.HAY_PYRAMID, new ItemStack(Blocks.HAY_BLOCK));
    addPyramidRecipe(McfrBlocks.APPLE_PLANKS_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 0));
    addPyramidRecipe(McfrBlocks.CHERRY_PLANKS_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 1));
    addPyramidRecipe(McfrBlocks.PALM_PLANKS_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 2));
    addPyramidRecipe(McfrBlocks.BELUXIER_PLANKS_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 3));
    addPyramidRecipe(McfrBlocks.APPLE_LOG_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 0));
    addPyramidRecipe(McfrBlocks.CHERRY_LOG_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 1));
    addPyramidRecipe(McfrBlocks.PALM_LOG_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 2));
    addPyramidRecipe(McfrBlocks.BELUXIER_LOG_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_LOG, 1, 3));
    addPyramidRecipe(McfrBlocks.APPLE_LEAVES_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_LEAVES, 1, 0));
    addPyramidRecipe(McfrBlocks.CHERRY_LEAVES_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_LEAVES, 1, 1));
    addPyramidRecipe(McfrBlocks.PALM_LEAVES_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_LEAVES, 1, 2));
    addPyramidRecipe(McfrBlocks.BELUXIER_LEAVES_PYRAMID, new ItemStack(McfrBlocks.EXOTIC_LEAVES, 1, 3));
    addPyramidRecipe(McfrBlocks.REFINED_OAK_PYRAMID, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0));
    addPyramidRecipe(McfrBlocks.REFINED_SPRUCE_PYRAMID, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1));
    addPyramidRecipe(McfrBlocks.REFINED_BIRCH_PYRAMID, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2));
    addPyramidRecipe(McfrBlocks.REFINED_JUNGLE_PYRAMID, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3));
    addPyramidRecipe(McfrBlocks.REFINED_ACACIA_PYRAMID, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 4));
    addPyramidRecipe(McfrBlocks.REFINED_DARK_OAK_PYRAMID, new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 5));
    addPyramidRecipe(McfrBlocks.ROUGH_SANDSTONE_PYRAMID, new ItemStack(McfrBlocks.ROUGH_SANDSTONE));
    addPyramidRecipe(McfrBlocks.YELLOW_STONE_PYRAMID, new ItemStack(Blocks.STONE, 1, 1));
    addPyramidRecipe(McfrBlocks.OCHER_STONE_PYRAMID, new ItemStack(Blocks.STONE, 1, 3));
    addPyramidRecipe(McfrBlocks.YELLOW_COBBLESTONE_PYRAMID, new ItemStack(Blocks.STONE, 1, 2));
    addPyramidRecipe(McfrBlocks.OCHER_COBBLESTONE_PYRAMID, new ItemStack(Blocks.STONE, 1, 4));
    addPyramidRecipe(McfrBlocks.YELLOW_STONEBRICK_PYRAMID, new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 0));
    addPyramidRecipe(McfrBlocks.YELLOW_MOSSY_STONEBRICK_PYRAMID, new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 1));
    addPyramidRecipe(McfrBlocks.YELLOW_CRACKED_STONEBRICK_PYRAMID, new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 2));
    addPyramidRecipe(McfrBlocks.OCHER_STONEBRICK_PYRAMID, new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 0));
    addPyramidRecipe(McfrBlocks.OCHER_MOSSY_STONEBRICK_PYRAMID, new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 1));
    addPyramidRecipe(McfrBlocks.OCHER_CRACKED_STONEBRICK_PYRAMID, new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 2));
    addPyramidRecipe(McfrBlocks.BRICK_TILES_PYRAMID, new ItemStack(McfrBlocks.TILES, 1, 0));
    addPyramidRecipe(McfrBlocks.SLATE_TILES_PYRAMID, new ItemStack(McfrBlocks.TILES, 1, 1));
    addPyramidRecipe(McfrBlocks.LIGHT_CARVED_CLAY_PYRAMID, new ItemStack(McfrBlocks.CARVED_CLAY, 1, 0));
    addPyramidRecipe(McfrBlocks.DARK_CARVED_CLAY_PYRAMID, new ItemStack(McfrBlocks.CARVED_CLAY, 1, 1));

    // Trappes solides
    addShapedRecipe(new ItemStack(McfrBlocks.STRONG_OAK_TRAPDOOR), "###", "###", '#', new ItemStack(Blocks.LOG, 1, 0));
    addShapedRecipe(new ItemStack(McfrBlocks.STRONG_SPRUCE_TRAPDOOR), "###", "###", '#', new ItemStack(Blocks.LOG, 1, 1));
    addShapedRecipe(new ItemStack(McfrBlocks.STRONG_BIRCH_TRAPDOOR), "###", "###", '#', new ItemStack(Blocks.LOG, 1, 2));
    addShapedRecipe(new ItemStack(McfrBlocks.STRONG_JUNGLE_TRAPDOOR), "###", "###", '#', new ItemStack(Blocks.LOG, 1, 3));
    addShapedRecipe(new ItemStack(McfrBlocks.STRONG_ACACIA_TRAPDOOR), "###", "###", '#', new ItemStack(Blocks.LOG, 1, 4));
    addShapedRecipe(new ItemStack(McfrBlocks.STRONG_DARK_OAK_TRAPDOOR), "###", "###", '#', new ItemStack(Blocks.LOG, 1, 5));

    // Trappes artisan
    addLargeRecipe(new ItemStack(McfrBlocks.CRAFTSMAN_OAK_TRAPDOOR), "FLLLF", "LWWWL", "LWVWL", "LWWWL", "FLLLF", 'L', new ItemStack(Blocks.LOG, 1, 0), 'W', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0), 'F', Items.IRON_INGOT, 'V', new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 1, 2));
    addLargeRecipe(new ItemStack(McfrBlocks.CRAFTSMAN_SPRUCE_TRAPDOOR), "FLLLF", "LWWWL", "LWVWL", "LWWWL", "FLLLF", 'L', new ItemStack(Blocks.LOG, 1, 1), 'W', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1), 'F', Items.IRON_INGOT, 'V', new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 1, 2));
    addLargeRecipe(new ItemStack(McfrBlocks.CRAFTSMAN_BIRCH_TRAPDOOR), "FLLLF", "LWWWL", "LWVWL", "LWWWL", "FLLLF", 'L', new ItemStack(Blocks.LOG, 1, 2), 'W', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2), 'F', Items.IRON_INGOT, 'V', new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 1, 2));
    addLargeRecipe(new ItemStack(McfrBlocks.CRAFTSMAN_JUNGLE_TRAPDOOR), "FLLLF", "LWWWL", "LWVWL", "LWWWL", "FLLLF", 'L', new ItemStack(Blocks.LOG, 1, 3), 'W', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3), 'F', Items.IRON_INGOT, 'V', new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 1, 2));

    // Barrières
    addFenceRecipe(new ItemStack(Blocks.OAK_FENCE, 12), new ItemStack(Blocks.LOG, 1, 0));
    addFenceRecipe(new ItemStack(Blocks.SPRUCE_FENCE, 12), new ItemStack(Blocks.LOG, 1, 1));
    addFenceRecipe(new ItemStack(Blocks.BIRCH_FENCE, 12), new ItemStack(Blocks.LOG, 1, 2));
    addFenceRecipe(new ItemStack(Blocks.JUNGLE_FENCE, 12), new ItemStack(Blocks.LOG, 1, 3));
    addFenceRecipe(new ItemStack(Blocks.ACACIA_FENCE, 12), new ItemStack(Blocks.LOG2, 1, 0));
    addFenceRecipe(new ItemStack(Blocks.DARK_OAK_FENCE, 12), new ItemStack(Blocks.LOG2, 1, 1));
    addFenceRecipe(new ItemStack(McfrBlocks.REFINED_OAK_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0));
    addFenceRecipe(new ItemStack(McfrBlocks.REFINED_SPRUCE_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1));
    addFenceRecipe(new ItemStack(McfrBlocks.REFINED_BIRCH_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2));
    addFenceRecipe(new ItemStack(McfrBlocks.REFINED_JUNGLE_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3));
    addFenceRecipe(new ItemStack(McfrBlocks.REFINED_ACACIA_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 4));
    addFenceRecipe(new ItemStack(McfrBlocks.REFINED_DARK_OAK_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 5));
    addFenceRecipe(new ItemStack(McfrBlocks.APPLE_WOOD_FENCE, 12), new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 0));
    addFenceRecipe(new ItemStack(McfrBlocks.CHERRY_WOOD_FENCE, 12), new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 1));
    addFenceRecipe(new ItemStack(McfrBlocks.PALM_FENCE, 12), new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 2));
    addFenceRecipe(new ItemStack(McfrBlocks.BELUXIER_FENCE, 12), new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 3));

    // Portillons
    addFenceGateRecipe(new ItemStack(Blocks.OAK_FENCE, 12), new ItemStack(Blocks.LOG, 1, 0));
    addFenceGateRecipe(new ItemStack(Blocks.SPRUCE_FENCE, 12), new ItemStack(Blocks.LOG, 1, 1));
    addFenceGateRecipe(new ItemStack(Blocks.BIRCH_FENCE, 12), new ItemStack(Blocks.LOG, 1, 2));
    addFenceGateRecipe(new ItemStack(Blocks.JUNGLE_FENCE, 12), new ItemStack(Blocks.LOG, 1, 3));
    addFenceGateRecipe(new ItemStack(Blocks.ACACIA_FENCE, 12), new ItemStack(Blocks.LOG2, 1, 0));
    addFenceGateRecipe(new ItemStack(Blocks.DARK_OAK_FENCE, 12), new ItemStack(Blocks.LOG2, 1, 1));
    addFenceGateRecipe(new ItemStack(McfrBlocks.REFINED_OAK_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0));
    addFenceGateRecipe(new ItemStack(McfrBlocks.REFINED_SPRUCE_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1));
    addFenceGateRecipe(new ItemStack(McfrBlocks.REFINED_BIRCH_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2));
    addFenceGateRecipe(new ItemStack(McfrBlocks.REFINED_JUNGLE_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3));
    addFenceGateRecipe(new ItemStack(McfrBlocks.REFINED_ACACIA_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 4));
    addFenceGateRecipe(new ItemStack(McfrBlocks.REFINED_DARK_OAK_FENCE, 12), new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 5));
    addFenceGateRecipe(new ItemStack(McfrBlocks.APPLE_WOOD_FENCE, 12), new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 0));
    addFenceGateRecipe(new ItemStack(McfrBlocks.CHERRY_WOOD_FENCE, 12), new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, 1));

    // Murets
    for (int i = 0; i < BlockPlanks.EnumType.values().length; i++)
      addWallRecipe(new ItemStack(McfrBlocks.WOODEN_WALL, 10, i), new ItemStack(i < 4 ? Blocks.LOG : Blocks.LOG2, 1, i % 4));

    for (EnumExoticWoodType type : EnumExoticWoodType.values())
      addWallRecipe(new ItemStack(McfrBlocks.EXOTIC_WOOD_WALL, 10, type.getMetadata()), new ItemStack(McfrBlocks.EXOTIC_LOG, 1, type.getMetadata()));

    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 0), new ItemStack(Blocks.STONEBRICK, 1, 0));
    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 1), new ItemStack(Blocks.STONEBRICK, 1, 1));
    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 2), new ItemStack(Blocks.STONEBRICK, 1, 2));
    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 3), new ItemStack(Blocks.STONE, 1, 2));
    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 4), new ItemStack(Blocks.STONE, 1, 4));
    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 5), new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 0));
    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 6), new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 1));
    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 7), new ItemStack(McfrBlocks.YELLOW_STONEBRICK, 1, 2));
    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 8), new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 0));
    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 9), new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 1));
    addWallRecipe(new ItemStack(McfrBlocks.STONE_WALL, 10, 10), new ItemStack(McfrBlocks.OCHER_STONEBRICK, 1, 2));

    for (EnumDyeColor color : EnumDyeColor.values())
      addShapelessRecipe(new ItemStack(McfrBlocks.CARPET, 1, color.getMetadata()), new ItemStack(Items.DYE, 1, color.getDyeDamage()), new ItemStack(McfrBlocks.CARPET, 1, 0));

    addShapedRecipe(new ItemStack(McfrBlocks.MOUCHARABIEH, 4, 0), "##", "##", '#', new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 1, 0));
    addShapedRecipe(new ItemStack(McfrBlocks.MOUCHARABIEH, 4, 1), "##", "##", '#', new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 1, 1));
    addLargeRecipe(new ItemStack(McfrBlocks.MOUCHARABIEH, 4, 2), "FVVVF", "VFVFV", "VVFVV", "VFVFV", "FVVVF", 'V', Blocks.GLASS, 'F', Items.IRON_INGOT);

    addLargeRecipe(new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 2, 0), "# # #", " ### ", "## ##", " ### ", "# # #", '#', Blocks.STONE);
    for (Block log : LOGS)
      addLargeRecipe(new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 2, 1), "# # #", " ### ", "## ##", " ### ", "# # #", '#', log);
    addLargeRecipe(new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 4, 2), "FVVVF", "VFVFV", "VVFVV", "VFVFV", "FVVVF", 'V', Blocks.GLASS_PANE, 'F', Items.IRON_INGOT);

    // Échelles
    addShapedRecipe(new ItemStack(McfrBlocks.ROPE_LADDER, 3), "C C", "CSC", "C C", 'C', McfrBlocks.ROPE, 'S', Items.STICK);
    addShapedRecipe(new ItemStack(McfrBlocks.CHAIN_LADDER, 3), "C C", "CCC", "C C", 'C', McfrItems.STITCH);

    // Cordes
    addShapedRecipe(new ItemStack(McfrBlocks.ROPE, 8), " FB", "FBF", "BF ", 'F', Items.STRING, 'B', McfrItems.HEMP_FIBER);
    addShapedRecipe(new ItemStack(McfrBlocks.CHAIN, 4), "B", "B", "B", "B", "B", 'B', McfrItems.STITCH);

    // Éclairage
    addShapedRecipe(new ItemStack(McfrBlocks.CANDLE), "S", "#", "A", 'S', Items.STRING, '#', McfrItems.WAX, 'A', Items.CLAY_BALL);
    addShapedRecipe(new ItemStack(McfrBlocks.CANDLE, 2), "#", '#', McfrBlocks.DOUBLE_CANDLE);
    addShapedRecipe(new ItemStack(McfrBlocks.CANDLE, 3), "#", '#', McfrBlocks.TRIPLE_CANDLE);
    addShapedRecipe(new ItemStack(McfrBlocks.DOUBLE_CANDLE), "##", '#', McfrBlocks.CANDLE);
    addShapedRecipe(new ItemStack(McfrBlocks.TRIPLE_CANDLE), "###", '#', McfrBlocks.CANDLE);

    for (Block planks : PLANKS) {
      for (Block slab : WOODEN_SLABS) {
        addLargeRecipe(new ItemStack(McfrBlocks.CHANDELIER), " C ", "BCB", "DPD", 'C', McfrBlocks.CHAIN, 'B', McfrBlocks.CANDLE, 'D', slab, 'P', planks);
        addLargeRecipe(new ItemStack(McfrBlocks.LARGE_CHANDELIER), "  C  ", "BBCBB", "DDPDD", 'C', McfrBlocks.CHAIN, 'B', McfrBlocks.CANDLE, 'D', slab, 'P', planks);
      }
    }

    addShapedRecipe(new ItemStack(McfrBlocks.LARGE_TORCH, 4), "FCF", " S ", " S ", 'C', Items.COAL, 'F', Items.IRON_INGOT, 'S', Items.STICK);
    addShapedRecipe(new ItemStack(McfrBlocks.BRAZIER), "CCC", "STS", "SSS", 'C', Items.COAL, 'S', Blocks.COBBLESTONE, 'T', Blocks.TORCH);
    addLargeRecipe(new ItemStack(McfrBlocks.WOODEN_LAMP, 2), "PPVPP", "PPTPP", "VTTTV", "PPTPP", "PPVPP", 'P', Blocks.LOG, 'V', Blocks.GLASS, 'T', McfrBlocks.LARGE_TORCH);

    for (EnumLanternColor color : EnumLanternColor.values())
      addShapedRecipe(new ItemStack(McfrBlocks.getLantern(color, false), 1, color.getMetadata()), " B ", "FFF", "GCG", "FTF", 'B', Blocks.IRON_BARS, 'F', Items.IRON_INGOT, 'G', Blocks.STAINED_GLASS_PANE, 'C', new ItemStack(Items.DYE, 4, color.getDyeColor().getDyeDamage()), 'T', Blocks.TORCH);

    // Chaises
    addLargeRecipe(new ItemStack(McfrBlocks.OAK_CHAIR), "B   ", "B   ", "ESSS", "F  F", "F  F", 'F', McfrBlocks.REFINED_OAK_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 0), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0), 'E', McfrBlocks.REFINED_OAK_STAIRS);
    addLargeRecipe(new ItemStack(McfrBlocks.SPRUCE_CHAIR), "B   ", "B   ", "ESSS", "F  F", "F  F", 'F', McfrBlocks.REFINED_SPRUCE_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 1), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1), 'E', McfrBlocks.REFINED_SPRUCE_STAIRS);
    addLargeRecipe(new ItemStack(McfrBlocks.BIRCH_CHAIR), "B   ", "B   ", "ESSS", "F  F", "F  F", 'F', McfrBlocks.REFINED_BIRCH_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 2), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2), 'E', McfrBlocks.REFINED_BIRCH_STAIRS);
    addLargeRecipe(new ItemStack(McfrBlocks.JUNGLE_CHAIR), "B   ", "B   ", "ESSS", "F  F", "F  F", 'F', McfrBlocks.REFINED_JUNGLE_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 3), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3), 'E', McfrBlocks.REFINED_JUNGLE_STAIRS);
    addLargeRecipe(new ItemStack(McfrBlocks.ACACIA_CHAIR), "B   ", "B   ", "ESSS", "F  F", "F  F", 'F', McfrBlocks.REFINED_ACACIA_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 4), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 4), 'E', McfrBlocks.REFINED_ACACIA_STAIRS);
    addLargeRecipe(new ItemStack(McfrBlocks.DARK_OAK_CHAIR), "B   ", "B   ", "ESSS", "F  F", "F  F", 'F', McfrBlocks.REFINED_DARK_OAK_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 5), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 5), 'E', McfrBlocks.REFINED_DARK_OAK_STAIRS);

    // Tabourets
    addLargeRecipe(new ItemStack(McfrBlocks.OAK_STOOL), "LLLL", "BBBB", "FBBF", "F  F", 'L', new ItemStack(McfrBlocks.CARPET, 1, 12), 'F', McfrBlocks.REFINED_OAK_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0));
    addLargeRecipe(new ItemStack(McfrBlocks.SPRUCE_STOOL), "LLLL", "BBBB", "FBBF", "F  F", 'L', new ItemStack(McfrBlocks.CARPET, 1, 12), 'F', McfrBlocks.REFINED_SPRUCE_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1));
    addLargeRecipe(new ItemStack(McfrBlocks.BIRCH_STOOL), "LLLL", "BBBB", "FBBF", "F  F", 'L', new ItemStack(McfrBlocks.CARPET, 1, 12), 'F', McfrBlocks.REFINED_BIRCH_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2));
    addLargeRecipe(new ItemStack(McfrBlocks.JUNGLE_STOOL), "LLLL", "BBBB", "FBBF", "F  F", 'L', new ItemStack(McfrBlocks.CARPET, 1, 12), 'F', McfrBlocks.REFINED_JUNGLE_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3));
    addLargeRecipe(new ItemStack(McfrBlocks.ACACIA_STOOL), "LLLL", "BBBB", "FBBF", "F  F", 'L', new ItemStack(McfrBlocks.CARPET, 1, 12), 'F', McfrBlocks.REFINED_ACACIA_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 4));
    addLargeRecipe(new ItemStack(McfrBlocks.DARK_OAK_STOOL), "LLLL", "BBBB", "FBBF", "F  F", 'L', new ItemStack(McfrBlocks.CARPET, 1, 12), 'F', McfrBlocks.REFINED_DARK_OAK_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 5));

    addLargeRecipe(new ItemStack(McfrBlocks.TALL_OAK_STOOL), "BSSB", "F  F", "FSSF", "F  F", 'F', McfrBlocks.REFINED_OAK_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0));
    addLargeRecipe(new ItemStack(McfrBlocks.TALL_SPRUCE_STOOL), "BSSB", "F  F", "FSSF", "F  F", 'F', McfrBlocks.REFINED_SPRUCE_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1));
    addLargeRecipe(new ItemStack(McfrBlocks.TALL_BIRCH_STOOL), "BSSB", "F  F", "FSSF", "F  F", 'F', McfrBlocks.REFINED_BIRCH_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2));
    addLargeRecipe(new ItemStack(McfrBlocks.TALL_JUNGLE_STOOL), "BSSB", "F  F", "FSSF", "F  F", 'F', McfrBlocks.REFINED_JUNGLE_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3));
    addLargeRecipe(new ItemStack(McfrBlocks.TALL_ACACIA_STOOL), "BSSB", "F  F", "FSSF", "F  F", 'F', McfrBlocks.REFINED_ACACIA_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 4));
    addLargeRecipe(new ItemStack(McfrBlocks.TALL_DARK_OAK_STOOL), "BSSB", "F  F", "FSSF", "F  F", 'F', McfrBlocks.REFINED_DARK_OAK_FENCE, 'S', Items.STICK, 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 5));

    addLargeRecipe(new ItemStack(McfrBlocks.WOODEN_ARMCHAIR), "W    ", "W    ", "WWWW ", "LLLLL", "LWWWL", 'W', new ItemStack(Blocks.LOG, 1, 1), 'L', new ItemStack(Blocks.LOG, 1, 1));
    addLargeRecipe(new ItemStack(McfrBlocks.STONE_ARMCHAIR), "W    ", "W    ", "WTTT ", "LLLLL", "LWWWL", 'W', Blocks.STONE, 'L', new ItemStack(Blocks.STONEBRICK, 1, 3), 'T', new ItemStack(McfrBlocks.CARPET, 1, 12));

    addLargeRecipe(new ItemStack(McfrBlocks.OAK_BENCH, 2), "SSSSS", "F   F", 'F', McfrBlocks.REFINED_OAK_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0));
    addLargeRecipe(new ItemStack(McfrBlocks.SPRUCE_BENCH, 2), "SSSSS", "F   F", 'F', McfrBlocks.REFINED_SPRUCE_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1));
    addLargeRecipe(new ItemStack(McfrBlocks.BIRCH_BENCH, 2), "SSSSS", "F   F", 'F', McfrBlocks.REFINED_BIRCH_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2));
    addLargeRecipe(new ItemStack(McfrBlocks.JUNGLE_BENCH, 2), "SSSSS", "F   F", 'F', McfrBlocks.REFINED_JUNGLE_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3));
    addLargeRecipe(new ItemStack(McfrBlocks.ACACIA_BENCH, 2), "SSSSS", "F   F", 'F', McfrBlocks.REFINED_ACACIA_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 4));
    addLargeRecipe(new ItemStack(McfrBlocks.DARK_OAK_BENCH, 2), "SSSSS", "F   F", 'F', McfrBlocks.REFINED_DARK_OAK_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 5));

    // Tables
    addLargeRecipe(new ItemStack(McfrBlocks.TABLE, 1, 0), "SSSSS", "F   F", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 0), 'F', McfrBlocks.REFINED_OAK_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.TABLE, 1, 1), "SSSSS", "F   F", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 1), 'F', McfrBlocks.REFINED_SPRUCE_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.TABLE, 1, 2), "SSSSS", "F   F", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 2), 'F', McfrBlocks.REFINED_BIRCH_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.TABLE, 1, 3), "SSSSS", "F   F", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 3), 'F', McfrBlocks.REFINED_JUNGLE_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.TABLE, 1, 4), "SSSSS", "F   F", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 4), 'F', McfrBlocks.REFINED_ACACIA_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.TABLE, 1, 5), "SSSSS", "F   F", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 5), 'F', McfrBlocks.REFINED_DARK_OAK_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.END_TABLE, 1, 0), "SSSSS", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 0), 'F', McfrBlocks.REFINED_OAK_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.END_TABLE, 1, 1), "SSSSS", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 1), 'F', McfrBlocks.REFINED_SPRUCE_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.END_TABLE, 1, 2), "SSSSS", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 2), 'F', McfrBlocks.REFINED_BIRCH_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.END_TABLE, 1, 3), "SSSSS", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 3), 'F', McfrBlocks.REFINED_JUNGLE_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.END_TABLE, 1, 4), "SSSSS", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 4), 'F', McfrBlocks.REFINED_ACACIA_FENCE);
    addLargeRecipe(new ItemStack(McfrBlocks.END_TABLE, 1, 5), "SSSSS", "F   F", "F   F", 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 5), 'F', McfrBlocks.REFINED_DARK_OAK_FENCE);
    for (Block fence : WOODEN_FENCES)
      addLargeRecipe(new ItemStack(McfrBlocks.TABLE_WITH_FOOT), "LLLLL", "  F  ", "  F  ", "  F  ", " SSS ", 'L', Blocks.LOG, 'F', fence, 'S', new ItemStack(Blocks.STONE));

    // Étagères
    addLargeRecipe(new ItemStack(McfrBlocks.OAK_SHELF), "BBBBB", "SS SS", "F   F", 'F', McfrBlocks.REFINED_OAK_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 0), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0));
    addLargeRecipe(new ItemStack(McfrBlocks.SPRUCE_SHELF), "BBBBB", "SS SS", "F   F", 'F', McfrBlocks.REFINED_SPRUCE_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 1), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1));
    addLargeRecipe(new ItemStack(McfrBlocks.BIRCH_SHELF), "BBBBB", "SS SS", "F   F", 'F', McfrBlocks.REFINED_BIRCH_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 2), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2));
    addLargeRecipe(new ItemStack(McfrBlocks.JUNGLE_SHELF), "BBBBB", "SS SS", "F   F", 'F', McfrBlocks.REFINED_JUNGLE_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 3), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3));
    addLargeRecipe(new ItemStack(McfrBlocks.JUNGLE_SHELF), "BBBBB", "SS SS", "F   F", 'F', McfrBlocks.REFINED_ACACIA_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 4), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 4));
    addLargeRecipe(new ItemStack(McfrBlocks.JUNGLE_SHELF), "BBBBB", "SS SS", "F   F", 'F', McfrBlocks.REFINED_DARK_OAK_FENCE, 'S', new ItemStack(McfrBlocks.REFINED_PLANKS_SLAB, 1, 5), 'B', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 5));
    for (Block fence : WOODEN_FENCES)
      addLargeRecipe(new ItemStack(McfrBlocks.STONE_SHELF), "BBBBB", "SS SS", "F   F", 'F', fence, 'S', new ItemStack(Blocks.STONE_SLAB), 'B', new ItemStack(Blocks.STONE));

    for (Block log : LOGS) {
      addLargeRecipe(new ItemStack(McfrBlocks.SUPPORT), "BBBBB", "B S  ", "BS   ", "B    ", 'B', log, 'S', Items.STICK);
      addLargeRecipe(new ItemStack(McfrBlocks.LONG_SUPPORT), "B    ", "BBBBB", "B S S", "BS  S", "B    ", 'B', log, 'S', Items.STICK);
    }

    /*
     * Items
     */

    /* Minecraft */

    addShapedRecipe(new ItemStack(Items.SUGAR), "S", "S", "#", 'S', McfrItems.SUGAR_CANES, '#', McfrItems.KITCHEN_MORTAR);
    addShapedRecipe(new ItemStack(Items.COOKIE), "#S#", "PPP", '#', McfrItems.COCOA, 'S', Items.SUGAR, 'P', McfrItems.CAKE_DOUGH);
    addShapedRecipe(new ItemStack(Items.PUMPKIN_PIE), "CFC", "UUU", "PPP", 'F', new ItemStack(McfrItems.FLASK, 1, 0), 'P', McfrItems.BREAD_DOUGH, 'U', Blocks.PUMPKIN, 'C', Items.CARROT);
    addLargeRecipe(new ItemStack(Items.CAKE), "SSASS", "PFEFP", "PPPPP", 'F', new ItemStack(McfrItems.FLASK, 1, 0), 'P', McfrItems.CAKE_DOUGH, 'S', Items.SUGAR, 'E', Items.EGG, 'A', Items.APPLE);

    addShapedRecipe(new ItemStack(Items.FLINT_AND_STEEL), " F", "S ", 'F', Items.FLINT, 'S', Items.IRON_INGOT);

    addShapedRecipe(new ItemStack(Items.WHEAT_SEEDS, 2), "#", '#', Items.WHEAT);

    addShapedRecipe(new ItemStack(Items.BOW), "PSS", "S F", "SF ", 'S', Items.STICK, 'P', new ItemStack(McfrItems.SWORD_HANDLE), 'F', Items.STRING);

    addShapedRecipe(new ItemStack(Items.FISHING_ROD, 1), " S", "SF", 'S', Items.STICK, 'F', Items.STRING);

    addShapedRecipe(new ItemStack(Items.DYE, 2, 0), "#", "#", "M", '#', McfrItems.INK, 'M', McfrItems.KITCHEN_MORTAR);
    addShapedRecipe(new ItemStack(Items.DYE, 2, 1), "#", "#", "M", '#', Blocks.RED_FLOWER, 'M', McfrItems.KITCHEN_MORTAR);
    addShapedRecipe(new ItemStack(Items.DYE, 2, 2), "#", "#", "M", '#', Blocks.CACTUS, 'M', McfrItems.KITCHEN_MORTAR);
    addShapedRecipe(new ItemStack(Items.DYE, 2, 3), "#", "#", "M", '#', McfrItems.COCOA, 'M', McfrItems.KITCHEN_MORTAR);
    addShapedRecipe(new ItemStack(Items.DYE, 2, 4), "#", "#", "M", '#', new ItemStack(McfrItems.ORE, 1, 2), 'M', McfrItems.KITCHEN_MORTAR);
    addShapedRecipe(new ItemStack(Items.DYE, 2, 5), "#SC", '#', new ItemStack(Items.DYE, 1, 1), 'S', Items.STICK, 'C', new ItemStack(Items.DYE, 1, 4));
    addShapedRecipe(new ItemStack(Items.DYE, 2, 6), "#SC", '#', new ItemStack(Items.DYE, 1, 2), 'S', Items.STICK, 'C', new ItemStack(Items.DYE, 1, 4));
    addShapedRecipe(new ItemStack(Items.DYE, 2, 7), "#SC", '#', new ItemStack(Items.DYE, 1, 8), 'S', Items.STICK, 'C', new ItemStack(Items.DYE, 1, 15));
    addShapedRecipe(new ItemStack(Items.DYE, 2, 8), "#SC", '#', new ItemStack(Items.DYE, 1, 0), 'S', Items.STICK, 'C', new ItemStack(Items.DYE, 1, 15));
    addShapedRecipe(new ItemStack(Items.DYE, 2, 9), "#SC", '#', new ItemStack(Items.DYE, 1, 1), 'S', Items.STICK, 'C', new ItemStack(Items.DYE, 1, 15));
    addShapedRecipe(new ItemStack(Items.DYE, 2, 10), "#SC", '#', new ItemStack(Items.DYE, 1, 2), 'S', Items.STICK, 'C', new ItemStack(Items.DYE, 1, 15));
    addShapedRecipe(new ItemStack(Items.DYE, 2, 11), "#", "#", "M", '#', Blocks.YELLOW_FLOWER, 'M', McfrItems.KITCHEN_MORTAR);
    addShapedRecipe(new ItemStack(Items.DYE, 2, 12), "#SC", '#', new ItemStack(Items.DYE, 1, 4), 'S', Items.STICK, 'C', new ItemStack(Items.DYE, 1, 15));
    addShapedRecipe(new ItemStack(Items.DYE, 2, 13), "#SC", '#', new ItemStack(Items.DYE, 1, 5), 'S', Items.STICK, 'C', new ItemStack(Items.DYE, 1, 15));
    addShapedRecipe(new ItemStack(Items.DYE, 2, 14), "#SC", '#', new ItemStack(Items.DYE, 1, 1), 'S', Items.STICK, 'C', new ItemStack(Items.DYE, 1, 11));
    addShapedRecipe(new ItemStack(Items.DYE, 2, 15), "#", "#", "M", '#', Items.BONE, 'M', McfrItems.KITCHEN_MORTAR);

    addLargeRecipe(new ItemStack(Items.BED), "RRRR#", "TTTTT", "DDDDD", "F   F", 'R', McfrItems.CLOTH_ROLL, 'T', McfrBlocks.CARPET, '#', Blocks.WOOL, 'F', Items.IRON_INGOT, 'D', McfrBlocks.REFINED_PLANKS_SLAB);

    addShapedRecipe(new ItemStack(Items.STICK, 4), "#", "#", '#', McfrBlocks.EXOTIC_PLANKS);

    addLargeRecipe(new ItemStack(Items.SADDLE), "  C  ", "MCCCM", "CC CC", "C   C", "M   M", 'C', Items.LEATHER, 'M', McfrItems.STITCH);

    /* Mc-Fr */

    // Divers
    addShapedRecipe(new ItemStack(McfrItems.WRITEABLE_PAPER), "F", "E", "#", 'F', Items.FEATHER, '#', Items.PAPER, 'E', McfrItems.INK);

    for (Block planks : PLANKS)
      for (Block fence : WOODEN_FENCES)
        addLargeRecipe(new ItemStack(McfrItems.SAILBOAT, 1), "SSBSS", "TTBTT", "  B  ", "P B P", " PPP ", 'S', Items.STICK, 'T', McfrItems.CLOTH_ROLL, 'B', fence, 'P', planks);

    // Artisanat
    for (Block planks : PLANKS)
      for (Block fence : WOODEN_FENCES)
        addShapedRecipe(new ItemStack(McfrItems.SAW_SUPPORT, 1), "W W", "BWB", "B B", 'W', planks, 'B', fence);

    addShapedRecipe(new ItemStack(McfrItems.SWORD_HANDLE, 1, 0), " FB", "FBF", "BF ", 'F', Items.STICK, 'B', Items.LEATHER);
    addShapedRecipe(new ItemStack(McfrItems.SWORD_HANDLE, 1, 1), " FL", "FLF", "LF ", 'F', Items.STICK, 'B', Items.LEATHER, 'L', Items.GHAST_TEAR);
    addShapedRecipe(new ItemStack(McfrItems.SWORD_HANDLE, 1, 2), " FL", "FLF", "LF ", 'F', Items.STICK, 'B', Items.LEATHER, 'L', Items.GOLD_NUGGET);
    addShapedRecipe(new ItemStack(McfrItems.SWORD_HANDLE, 1, 3), " FL", "FLF", "LF ", 'F', Items.STICK, 'B', Items.LEATHER, 'L', Items.IRON_INGOT);

    // Agriculture
    addShapedRecipe(new ItemStack(McfrItems.BARLEY_SEEDS, 2), "#", '#', McfrItems.BARLEY);
    addShapedRecipe(new ItemStack(McfrItems.GRAPE_SEEDS, 4), "S", 'S', McfrItems.GRAPES);

    addShapedRecipe(new ItemStack(McfrItems.HEMP_LEAF, 1), "#", '#', McfrItems.HEMP_FLOWER);
    addShapedRecipe(new ItemStack(McfrItems.HEMP_FIBER, 3), "###", '#', McfrItems.HEMP_FLOWER);
    addShapedRecipe(new ItemStack(McfrItems.HEMP_OIL), "C", "C", "M", 'C', McfrItems.HEMP_LEAF, 'M', McfrItems.KITCHEN_MORTAR);

    addShapedRecipe(new ItemStack(McfrItems.FERTILIZER), "BBB", "CCC", "###", 'B', new ItemStack(Items.DYE, 1, 15), 'C', McfrItems.HEMP_OIL, '#', McfrItems.POOP);

    // Tonneaux
    for (Block planks : PLANKS)
      addLargeRecipe(new ItemStack(McfrItems.EMPTY_BARREL), "#####", "#####", "#####", "##H##", "#####", '#', planks, 'H', Blocks.TRIPWIRE_HOOK);
    addShapedRecipe(new ItemStack(McfrItems.BEER_BARREL), "###", "#B#", "#T#", '#', McfrItems.BARLEY, 'B', Items.WATER_BUCKET, 'T', McfrItems.EMPTY_BARREL);
    addShapedRecipe(new ItemStack(McfrItems.CIDER_BARREL), "###", "#B#", "#T#", '#', Items.APPLE, 'B', Items.WATER_BUCKET, 'T', McfrItems.EMPTY_BARREL);
    addShapedRecipe(new ItemStack(McfrItems.WINE_BARREL), "###", "#B#", "#T#", '#', McfrItems.GRAPES, 'B', Items.WATER_BUCKET, 'T', McfrItems.EMPTY_BARREL);
    addShapedRecipe(new ItemStack(McfrItems.RUM_BARREL), "###", "#B#", "#T#", '#', McfrItems.SUGAR_CANES, 'B', Items.WATER_BUCKET, 'T', McfrItems.EMPTY_BARREL);

    // Récipients vides
    for (Block planks : PLANKS) {
      addShapedRecipe(new ItemStack(McfrItems.EMPTY_TANKARD), "# #", "# #", " # ", '#', planks);
      addShapedRecipe(new ItemStack(McfrItems.EMPTY_BOWL, 4), "S S", "# #", " # ", '#', planks, 'S', Items.STICK);
    }
    addShapedRecipe(new ItemStack(McfrItems.EMPTY_GLASS), "# #", "# #", " # ", '#', Blocks.GLASS);
    addShapedRecipe(new ItemStack(McfrItems.EMPTY_BOTTLE), " # ", "# #", "###", '#', Blocks.GLASS);

    // Nourriture
    addShapedRecipe(new ItemStack(McfrItems.CAKE_DOUGH, 8), "FFF", "FOF", "FFF", 'O', Items.EGG, 'F', McfrItems.FLOUR);
    addLargeRecipe(new ItemStack(McfrItems.CHOCOLATE_CAKE, 1), "##S##", "PFEFP", "PPPPP", 'F', new ItemStack(McfrItems.FLASK, 1, 1), 'S', Items.SUGAR, 'P', McfrItems.CAKE_DOUGH, 'E', Items.EGG, '#', McfrItems.COCOA);
    addShapedRecipe(new ItemStack(McfrItems.APPLE_PIE, 1), "ASA", "AAA", "PPP", 'S', Items.SUGAR, 'P', McfrItems.BREAD_DOUGH, 'A', Items.APPLE);
    addShapedRecipe(new ItemStack(McfrItems.PUMPKIN_SOUP), "P", "P", "B", 'P', Blocks.PUMPKIN, 'B', Items.BOWL);
    addShapedRecipe(new ItemStack(McfrItems.FLOUR, 1), "#", "#", "S", '#', Items.WHEAT_SEEDS, 'S', McfrItems.KITCHEN_MORTAR);
    addShapedRecipe(new ItemStack(McfrItems.BREAD_DOUGH, 1), "###", '#', McfrItems.FLOUR);
    for (Block planks : PLANKS)
      addShapedRecipe(new ItemStack(McfrItems.KITCHEN_MORTAR, 16), "  S", "#S#", " # ", '#', planks, 'S', Items.STICK);
    addShapedRecipe(new ItemStack(McfrItems.POIGRUME_COOKIE, 2), "#S#", "PPP", '#', McfrItems.POIGRUME, 'S', Items.SUGAR, 'P', McfrItems.CAKE_DOUGH);

    // Portes solides
    addShapedRecipe(new ItemStack(McfrItems.STRONG_OAK_DOOR), "##", "##", "##", '#', new ItemStack(Blocks.LOG, 1, 0));
    addShapedRecipe(new ItemStack(Items.SPRUCE_DOOR), "##", "##", "##", '#', new ItemStack(Blocks.LOG, 1, 1));
    addShapedRecipe(new ItemStack(Items.BIRCH_DOOR), "##", "##", "##", '#', new ItemStack(Blocks.LOG, 1, 2));
    addShapedRecipe(new ItemStack(Items.JUNGLE_DOOR), "##", "##", "##", '#', new ItemStack(Blocks.LOG, 1, 3));
    addShapedRecipe(new ItemStack(Items.ACACIA_DOOR), "##", "##", "##", '#', new ItemStack(Blocks.LOG, 1, 4));
    addShapedRecipe(new ItemStack(Items.DARK_OAK_DOOR), "##", "##", "##", '#', new ItemStack(Blocks.LOG, 1, 5));

    // Portes artisan
    addLargeRecipe(new ItemStack(McfrItems.CRAFTSMAN_OAK_DOOR), "LFWWL", "LWVWL", "LFLFL", "LWWWL", "LFWWL", 'L', new ItemStack(Blocks.LOG, 1, 0), 'W', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 0), 'F', Items.IRON_INGOT, 'V', new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 1, 2));
    addLargeRecipe(new ItemStack(McfrItems.CRAFTSMAN_SPRUCE_DOOR), "LFWWL", "LWVWL", "LFLFL", "LWWWL", "LFWWL", 'L', new ItemStack(Blocks.LOG, 1, 1), 'W', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 1), 'F', Items.IRON_INGOT, 'V', new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 1, 2));
    addLargeRecipe(new ItemStack(McfrItems.CRAFTSMAN_BIRCH_DOOR), "LFWWL", "LWVWL", "LFLFL", "LWWWL", "LFWWL", 'L', new ItemStack(Blocks.LOG, 1, 2), 'W', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 2), 'F', Items.IRON_INGOT, 'V', new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 1, 2));
    addLargeRecipe(new ItemStack(McfrItems.CRAFTSMAN_JUNGLE_DOOR), "LFWWL", "LWVWL", "LFLFL", "LWWWL", "LFWWL", 'L', new ItemStack(Blocks.LOG, 1, 3), 'W', new ItemStack(McfrBlocks.REFINED_PLANKS, 1, 3), 'F', Items.IRON_INGOT, 'V', new ItemStack(McfrBlocks.MOUCHARABIEH_PANE, 1, 2));

    // Lits
    for (Block slab : WOODEN_SLABS) {
      addShapedRecipe(new ItemStack(McfrItems.HAY_BED), "FFF", "DDD", 'F', Blocks.HAY_BLOCK, 'D', slab);
      addLargeRecipe(new ItemStack(McfrItems.NORMAL_BED), "TTTT#", "FFFFF", "DDDDD", 'T', McfrBlocks.CARPET, '#', Blocks.WOOL, 'F', Blocks.HAY_BLOCK, 'D', slab);
    }
    addShapedRecipe(new ItemStack(McfrItems.STONE_BED), "CCC", "DDD", 'C', Items.LEATHER, 'D', new ItemStack(Blocks.STONE_SLAB));
    addLargeRecipe(new ItemStack(McfrItems.SLEEPING_BAG), "TTTT#", "#####", 'T', McfrBlocks.CARPET, '#', Blocks.WOOL);

    // Éclairage
    for (Block planks : PLANKS)
      addShapedRecipe(new ItemStack(McfrItems.CAMPFIRE, 1), "SS", "BB", 'S', Items.STICK, 'B', planks);

    addShapedRecipe(new ItemStack(McfrItems.LANTERN, 1, 0), "SSS", "PBP", "PSP", 'S', Items.STICK, 'P', Items.PAPER, 'B', McfrBlocks.CANDLE);
    addShapelessRecipe(new ItemStack(McfrItems.LANTERN, 1, 1), new ItemStack(Items.DYE, 1, 14), McfrBlocks.WHITE_LANTERN);
    addShapelessRecipe(new ItemStack(McfrItems.LANTERN, 1, 2), new ItemStack(Items.DYE, 1, 11), McfrBlocks.WHITE_LANTERN);
    addShapelessRecipe(new ItemStack(McfrItems.LANTERN, 1, 3), new ItemStack(Items.DYE, 1, 5), McfrBlocks.WHITE_LANTERN);
    addShapelessRecipe(new ItemStack(McfrItems.LANTERN, 1, 4), new ItemStack(Items.DYE, 1, 4), McfrBlocks.WHITE_LANTERN);
    addShapelessRecipe(new ItemStack(McfrItems.LANTERN, 1, 5), new ItemStack(Items.DYE, 1, 2), McfrBlocks.WHITE_LANTERN);
    addShapelessRecipe(new ItemStack(McfrItems.LANTERN, 1, 6), new ItemStack(Items.DYE, 1, 1), McfrBlocks.WHITE_LANTERN);

    for (Block planks : PLANKS)
      addShapedRecipe(new ItemStack(McfrItems.SIGN), "###", "###", " | ", '#', planks, '|', Items.STICK);

    // Vitrines/stands
    for (Block fence : WOODEN_FENCES)
      addShapedRecipe(new ItemStack(McfrItems.ARMOR_STAND), "#", "#", "D", 'D', new ItemStack(Blocks.STONE_SLAB, 1, 0), '#', fence);
    addShapedRecipe(new ItemStack(McfrItems.WEAPONS_STAND), "###", "# #", "###", '#', Items.STICK);
    for (Block planks : PLANKS)
      addShapedRecipe(new ItemStack(McfrItems.SHOWCASE), "###", "#V#", "###", '#', planks, 'V', Blocks.GLASS_PANE);

    // Panneaux et affiches
    addShapedRecipe(new ItemStack(McfrItems.WALL_NOTE), "###", "#E#", "###", '#', Items.PAPER, 'E', McfrItems.INK);
    addShapedRecipe(new ItemStack(McfrItems.TOMBSTONE, 1), "#", "#", "D", '#', new ItemStack(Blocks.STONE_SLAB, 1, 0), 'D', Blocks.COBBLESTONE);

    // Outils
    for (Block slab : WOODEN_SLABS) {
      addShapedRecipe(new ItemStack(McfrItems.GRAPNEL, 1, 0), " CT", " EC", "P  ", 'T', Blocks.TRIPWIRE_HOOK, 'E', McfrBlocks.ROPE_LADDER, 'C', slab, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
      addLargeRecipe(new ItemStack(McfrItems.GRAPNEL, 1, 1), "  CT", "  EC", " E  ", "P   ", 'T', Blocks.TRIPWIRE_HOOK, 'E', McfrBlocks.ROPE_LADDER, 'C', slab, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
      addLargeRecipe(new ItemStack(McfrItems.GRAPNEL, 1, 2), "   CT", "   EC", "  E  ", " E   ", "P    ", 'T', Blocks.TRIPWIRE_HOOK, 'E', McfrBlocks.ROPE_LADDER, 'C', slab, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
    }
    addShapedRecipe(new ItemStack(McfrItems.GRAPNEL, 1, 3), " CT", " EC", "P  ", 'T', Blocks.TRIPWIRE_HOOK, 'E', McfrBlocks.CHAIN_LADDER, 'C', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
    addLargeRecipe(new ItemStack(McfrItems.GRAPNEL, 1, 4), "  CT", "  EC", " E  ", "P   ", 'T', Blocks.TRIPWIRE_HOOK, 'E', McfrBlocks.CHAIN_LADDER, 'C', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
    addLargeRecipe(new ItemStack(McfrItems.GRAPNEL, 1, 5), "   CT", "   EC", "  E  ", " E   ", "P    ", 'T', Blocks.TRIPWIRE_HOOK, 'E', McfrBlocks.CHAIN_LADDER, 'C', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE));

    addLargeRecipe(new ItemStack(McfrItems.GOOD_FISHING_ROD), "    S", "   SF", "  SFF", " SF H", "P    ", 'S', Items.STICK, 'F', Items.STRING, 'H', Blocks.TRIPWIRE_HOOK, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
    addLargeRecipe(new ItemStack(McfrItems.FISHING_NET), "SSPSS", "CFFFC", "CFFFC", " CFC ", "  C  ", 'S', Items.STICK, 'F', Items.STRING, 'C', McfrBlocks.ROPE, 'P', new ItemStack(McfrItems.SWORD_HANDLE));

    addShapedRecipe(new ItemStack(McfrItems.POINTY_STICK), " S", "S ", 'S', new ItemStack(Items.STICK, 2));
    addShapedRecipe(new ItemStack(McfrItems.STONE_DAGGER), "  S", " S ", "P  ", 'S', Blocks.STONE, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
    addLargeRecipe(new ItemStack(McfrItems.STONE_SPEAR), "    I", "   S ", "  P  ", " P   ", "S    ", 'I', Blocks.COBBLESTONE, 'P', new ItemStack(McfrItems.SWORD_HANDLE), 'S', Items.STICK);

    // Arcs
    addLargeRecipe(new ItemStack(McfrItems.BARBARIAN_BOW), "  ISI", "  S F", "ISPF ", "S F  ", "IF   ", 'I', Items.IRON_INGOT, 'S', Items.STICK, 'P', new ItemStack(McfrItems.SWORD_HANDLE), 'F', Items.STRING);
    addLargeRecipe(new ItemStack(McfrItems.LONG_BOW), "  SSS", " P  F", "S  F ", "S F  ", "SF   ", 'S', Items.STICK, 'P', new ItemStack(McfrItems.SWORD_HANDLE), 'F', Items.STRING);
    addLargeRecipe(new ItemStack(McfrItems.HUNTER_BOW), "  TSS", " PSF ", "TSF  ", "SF   ", "S    ", 'T', McfrItems.CLOTH_ROLL, 'S', Items.STICK, 'P', new ItemStack(McfrItems.SWORD_HANDLE), 'F', Items.STRING);
    addLargeRecipe(new ItemStack(McfrItems.LONG_HUNTER_BOW), "  TSS", " PS F", "TSPF ", "S F  ", "SF   ", 'T', McfrItems.CLOTH_ROLL, 'S', Items.STICK, 'P', new ItemStack(McfrItems.SWORD_HANDLE), 'F', Items.STRING);

    // Arumure d'assassin
    addLargeRecipe(new ItemStack(McfrItems.ASSASSIN_HELMET), " TPT ", "TTFTT", "TF FT", "TFFFT", "TTTTT", 'P', McfrItems.HUNTED_SKIN, 'T', McfrItems.CLOTH_ROLL, 'F', McfrItems.THREAD_COIL);
    addLargeRecipe(new ItemStack(McfrItems.ASSASSIN_CHESTPLATE), " T C ", "TTMTT", "PCCPM", "CPPCM", "PPPPC", 'P', McfrItems.HUNTED_SKIN, 'T', McfrItems.CLOTH_ROLL, 'M', McfrItems.STITCH, 'C', Items.LEATHER);
    addLargeRecipe(new ItemStack(McfrItems.ASSASSIN_LEGGINGS), "CCMCC", "TTTTT", "TTPTT", "TPPPT", "PP PP", 'P', McfrItems.HUNTED_SKIN, 'T', McfrItems.CLOTH_ROLL, 'M', McfrItems.STITCH, 'C', Items.LEATHER);
    addLargeRecipe(new ItemStack(McfrItems.ASSASSIN_BOOTS), "FT TF", "PP PP", 'P', McfrItems.HUNTED_SKIN, 'T', McfrItems.CLOTH_ROLL, 'F', McfrItems.THREAD_COIL);
  }

  /**
   * Enregistre les recettes de l'enclume.
   */
  public static void registerAnvilCrafts() {
    RecipeSorter.register(Constants.MOD_ID + ":anvil", AnvilRecipe.class, Category.SHAPED, "after:minecraft:shaped");

    removeRecipe(new ItemStack(Items.IRON_SWORD));
    removeRecipe(new ItemStack(Items.GOLDEN_SWORD));
    removeRecipe(new ItemStack(Items.DIAMOND_SWORD));
    removeRecipe(new ItemStack(Items.IRON_HELMET));
    removeRecipe(new ItemStack(Items.IRON_CHESTPLATE));
    removeRecipe(new ItemStack(Items.IRON_LEGGINGS));
    removeRecipe(new ItemStack(Items.IRON_BOOTS));
    removeRecipe(new ItemStack(Items.GOLDEN_HELMET));
    removeRecipe(new ItemStack(Items.GOLDEN_CHESTPLATE));
    removeRecipe(new ItemStack(Items.GOLDEN_LEGGINGS));
    removeRecipe(new ItemStack(Items.GOLDEN_BOOTS));
    removeRecipe(new ItemStack(Items.DIAMOND_HELMET));
    removeRecipe(new ItemStack(Items.DIAMOND_CHESTPLATE));
    removeRecipe(new ItemStack(Items.DIAMOND_LEGGINGS));
    removeRecipe(new ItemStack(Items.DIAMOND_BOOTS));
    removeRecipe(new ItemStack(Items.CAULDRON));
    removeRecipe(new ItemStack(Items.BUCKET));
    removeRecipe(new ItemStack(Items.MINECART));
    removeRecipe(new ItemStack(Blocks.RAIL));
    removeRecipe(new ItemStack(Blocks.GOLDEN_RAIL));
    removeRecipe(new ItemStack(Blocks.DETECTOR_RAIL));
    removeRecipe(new ItemStack(Items.CLOCK));
    removeRecipe(new ItemStack(Items.COMPASS));
    removeRecipe(new ItemStack(Items.SHEARS));
    removeRecipe(new ItemStack(Items.IRON_DOOR));
    removeRecipe(new ItemStack(Blocks.IRON_TRAPDOOR));

    addAnvilRecipe(new ItemStack(McfrItems.IRON_BOW), 60, 90, "  #B#", " P  S", "#  S ", "B S  ", "#S   ", '#', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 0), 'B', Items.STICK, 'S', Items.STRING);
    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_BOW), 45, 70, "  ##N", " P  S", "#  S ", "# S  ", "NS   ", '#', Items.GOLD_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 2), 'N', Items.GOLD_NUGGET, 'S', Items.STRING);

    addAnvilRecipe(new ItemStack(Items.IRON_SWORD), 60, 90, "    F", " F F ", " FF  ", " PFF ", "L    ", 'L', new ItemStack(McfrItems.ORE, 1, 2), 'F', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 0));
    addAnvilRecipe(new ItemStack(Items.GOLDEN_SWORD), 45, 70, "    G", " G G ", " GN  ", " PGG ", "G    ", 'N', Items.GOLD_NUGGET, 'G', Items.GOLD_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 2));

    addAnvilRecipe(new ItemStack(Items.IRON_HELMET), 70, 100, " FFF ", "FFFFF", "F   F", "FF FF", " F F ", 'F', Items.IRON_INGOT);
    addAnvilRecipe(new ItemStack(Items.IRON_CHESTPLATE), 70, 100, " FCF ", "FMFFF", "CFFFF", "CCMCC", "FFFFF", 'M', McfrItems.STITCH, 'C', Items.LEATHER, 'F', Items.IRON_INGOT);
    addAnvilRecipe(new ItemStack(Items.IRON_LEGGINGS), 70, 100, "CCMCC", "FFFFF", "FF FF", "FF FF", "FF FF", 'M', McfrItems.STITCH, 'C', Items.LEATHER, 'F', Items.IRON_INGOT);
    addAnvilRecipe(new ItemStack(Items.IRON_BOOTS), 70, 100, "CF FC", "CF FC", "FF FF", 'F', Items.IRON_INGOT, 'C', Items.LEATHER);

    addAnvilRecipe(new ItemStack(Items.GOLDEN_HELMET), 35, 60, " GLG ", "GGGGG", "G G G", "GG GG", "M   M", 'G', Items.GOLD_INGOT, 'L', new ItemStack(Blocks.WOOL, 1, 14), 'M', new ItemStack(McfrItems.STITCH, 1, 1));
    addAnvilRecipe(new ItemStack(Items.GOLDEN_CHESTPLATE), 35, 60, " FFF ", "OFFFO", "OOFOO", "COCOC", "OCOCO", 'O', Items.GOLD_INGOT, 'F', Items.IRON_INGOT, 'C', Items.LEATHER);
    addAnvilRecipe(new ItemStack(Items.GOLDEN_LEGGINGS), 35, 60, "CCMCC", "CCGCC", "CG GC", "GG GG", "GG GG", 'G', Items.GOLD_INGOT, 'C', Items.LEATHER, 'M', new ItemStack(McfrItems.STITCH, 1, 1));
    addAnvilRecipe(new ItemStack(Items.GOLDEN_BOOTS), 35, 60, "CG GC", "CG GC", "GG GG", 'G', Items.GOLD_INGOT, 'C', Items.LEATHER);

    addAnvilRecipe(new ItemStack(Items.CHAINMAIL_HELMET), 70, 100, "FFFFF", "FFFFF", "M F M", "M   M", " M M ", 'F', Items.IRON_INGOT, 'M', McfrItems.STITCH);
    addAnvilRecipe(new ItemStack(Items.CHAINMAIL_CHESTPLATE), 70, 100, " T T ", "TTTMT", "MTCTM", "MMMCM", "MMMMC", 'T', McfrItems.CLOTH_ROLL, 'M', McfrItems.STITCH, 'C', Items.LEATHER);
    addAnvilRecipe(new ItemStack(Items.CHAINMAIL_LEGGINGS), 70, 100, "CCMCC", "TTMTT", "TM MT", "MM MM", "M   M", 'T', McfrItems.CLOTH_ROLL, 'M', McfrItems.STITCH, 'C', Items.LEATHER);
    addAnvilRecipe(new ItemStack(Items.CHAINMAIL_BOOTS), 70, 100, " F F ", "MF FM", "MC CM", 'F', Items.IRON_INGOT, 'M', McfrItems.STITCH, 'C', Items.LEATHER);

    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_CHAINMAIL_HELMET), 35, 60, " CGC ", "CGGGC", "G G G", "MGGGM", "M   M", 'C', Items.LEATHER, 'M', new ItemStack(McfrItems.STITCH, 1, 1), 'G', Items.GOLD_INGOT);
    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_CHAINMAIL_CHESTPLATE), 35, 60, " G C ", "GG#GG", "GCMMG", "CC#CC", "MMMMM", 'C', Items.LEATHER, 'M', new ItemStack(McfrItems.STITCH, 1, 1), '#', McfrItems.STITCH, 'G', Items.GOLD_INGOT);
    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_CHAINMAIL_LEGGINGS), 35, 60, "CCCCC", "MMMMM", "MMTMM", "MTTTM", "TT TT", 'C', Items.LEATHER, 'M', new ItemStack(McfrItems.STITCH, 1, 1), 'T', McfrItems.CLOTH_ROLL, 'G', Items.GOLD_INGOT);
    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_CHAINMAIL_BOOTS), 35, 60, "MG GM", "MG GM", "CC CC", 'C', Items.LEATHER, 'M', new ItemStack(McfrItems.STITCH, 1, 1), 'G', Items.GOLD_INGOT);

    addAnvilRecipe(new ItemStack(Items.CAULDRON), 70, 90, "I   I", "I   I", "II II", "BI IB", "I I I", 'I', Items.IRON_INGOT, 'B', Blocks.PLANKS);
    addAnvilRecipe(new ItemStack(Items.BUCKET), 60, 100, "I I", "I I", " I ", 'I', Items.IRON_INGOT);

    addAnvilRecipe(new ItemStack(Items.MINECART, 1), 60, 100, "I   I", "I   I", "IIII ", 'I', Items.IRON_INGOT);
    addAnvilRecipe(new ItemStack(Blocks.RAIL, 16), 60, 100, "I I I", "ISSSI", "S I S", "ISSSI", "I I I", 'I', Items.IRON_INGOT, 'S', Items.STICK);
    addAnvilRecipe(new ItemStack(Blocks.GOLDEN_RAIL, 6), 40, 70, "GSRSG", "G G G", "GSRSG", "G G G", "GSRSG", 'G', Items.GOLD_INGOT, 'S', Items.STICK, 'R', Items.REDSTONE);
    addAnvilRecipe(new ItemStack(Blocks.DETECTOR_RAIL, 6), 60, 100, "ISSSI", "RIIIR", "ISISI", "RIIIR", "ISSSI", 'I', Items.IRON_INGOT, 'S', Items.STICK, 'R', Items.REDSTONE);

    addAnvilRecipe(new ItemStack(Items.CLOCK, 1), 40, 70, " # ", "#X#", " # ", '#', Items.GOLD_INGOT, 'X', Items.REDSTONE);
    addAnvilRecipe(new ItemStack(Items.COMPASS, 1), 60, 100, " # ", "#X#", " # ", '#', Items.IRON_INGOT, 'X', Items.REDSTONE);
    addAnvilRecipe(new ItemStack(Items.SHEARS), 60, 100, "  #  ", " #   ", " S  #", " SS# ", '#', Items.IRON_INGOT, 'S', Items.STICK);

    addAnvilRecipe(new ItemStack(McfrBlocks.ANVIL), 125, 140, "BBBBB", " BBB ", "  W  ", " IWI ", "IIIII", 'B', Blocks.IRON_BLOCK, 'W', Blocks.PLANKS, 'I', Items.IRON_INGOT);
    addAnvilRecipe(new ItemStack(McfrBlocks.STOVE), 60, 100, "IIIII", "II II", "IIIII", "B   B", "IBBBI", 'B', Blocks.IRON_BLOCK, 'I', Items.IRON_INGOT);

    addAnvilRecipe(new ItemStack(McfrItems.SAW_BLADE, 2), 55, 96, "  #  ", " #B# ", "#BFB#", " #B# ", "  #  ", '#', Items.IRON_INGOT, 'B', Blocks.IRON_BARS, 'F', Blocks.OAK_FENCE);

    addAnvilRecipe(new ItemStack(McfrItems.STITCH, 8), 60, 100, "  #  ", " # # ", " #   ", " # # ", "  #  ", '#', Items.IRON_INGOT);
    addAnvilRecipe(new ItemStack(McfrItems.STITCH, 8, 1), 40, 70, "  #  ", " # # ", " #   ", " # # ", "  #  ", '#', Items.GOLD_INGOT);

    addAnvilRecipe(new ItemStack(McfrItems.IRON_DAGGER), 60, 90, "  I", " I ", "P  ", 'I', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
    addAnvilRecipe(new ItemStack(Items.IRON_DOOR), 60, 100, "FFF", "FBF", "FBF", "FFF", "FFF", 'B', Blocks.IRON_BARS, 'F', Items.IRON_INGOT);
    addAnvilRecipe(new ItemStack(Blocks.IRON_TRAPDOOR), 60, 100, "FFF", "FBF", "FFF", 'B', Blocks.IRON_BARS, 'F', Items.IRON_INGOT);

    addAnvilRecipe(new ItemStack(McfrItems.IRON_RAPIER), 60, 90, "    I", "   I ", "  I  ", "II   ", "PI   ", 'I', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
    addAnvilRecipe(new ItemStack(McfrItems.IRON_BASTARD), 60, 90, "   II", "  II ", " III ", " PI  ", "P    ", 'I', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
    addAnvilRecipe(new ItemStack(McfrItems.IRON_SPEAR), 60, 90, "    I", "   S ", "  P  ", " P   ", "S    ", 'I', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE), 'S', Items.STICK);
    addAnvilRecipe(new ItemStack(McfrItems.IRON_HALBERD), 60, 90, " II I", " III ", "  I  ", " S   ", "P    ", 'I', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE), 'S', Items.STICK);
    addAnvilRecipe(new ItemStack(McfrItems.IRON_BATTLE_AXE), 60, 90, " II  ", " III ", "  III", " S II", "P    ", 'I', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE), 'S', Items.STICK);
    addAnvilRecipe(new ItemStack(McfrItems.IRON_HAMMER), 60, 90, " II  ", " III ", "  III", " I II", "P    ", 'I', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE));
    addAnvilRecipe(new ItemStack(McfrItems.IRON_MACE), 60, 90, "   II", "  III", "  II ", " S   ", "P    ", 'I', Items.IRON_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE), 'S', Items.STICK);

    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_RAPIER), 45, 70, "    I", "   I ", "  I  ", "II   ", "PI   ", 'I', Items.GOLD_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 2));
    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_BASTARD), 45, 70, "   II", "  II ", " III ", " PI  ", "P    ", 'I', Items.GOLD_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 2));
    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_SPEAR), 45, 70, "    I", "   S ", "  P  ", " P   ", "S    ", 'I', Items.GOLD_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 2), 'S', Items.STICK);
    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_HALBERD), 45, 70, " II I", " III ", "  I  ", " S   ", "P    ", 'I', Items.GOLD_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 2), 'S', Items.STICK);
    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_BATTLE_AXE), 45, 70, " II  ", " III ", "  III", " S II", "P    ", 'I', Items.GOLD_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 2), 'S', Items.STICK);
    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_HAMMER), 45, 70, " II  ", " III ", "  III", " I II", "P    ", 'I', Items.GOLD_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 2));
    addAnvilRecipe(new ItemStack(McfrItems.GOLDEN_MACE), 45, 70, "   II", "  III", "  II ", " S   ", "P    ", 'I', Items.GOLD_INGOT, 'P', new ItemStack(McfrItems.SWORD_HANDLE, 1, 2), 'S', Items.STICK);
  }

  /**
   * Enregistre les recettes du four.
   */
  public static void registerFurnaceRecipes() {
    removeFurnaceRecipe(new ItemStack(Blocks.IRON_ORE));
    removeFurnaceRecipe(new ItemStack(Blocks.GOLD_ORE));

    addFurnaceRecipe(new ItemStack(McfrItems.ORE, 1, 0), new ItemStack(Items.IRON_INGOT));
    addFurnaceRecipe(new ItemStack(McfrItems.ORE, 1, 1), new ItemStack(Items.GOLD_INGOT));
    addFurnaceRecipe(new ItemStack(Blocks.STONE, 1, 2), new ItemStack(Blocks.STONE, 1, 1));
    addFurnaceRecipe(new ItemStack(Blocks.STONE, 1, 4), new ItemStack(Blocks.STONE, 1, 3));
    addFurnaceRecipe(new ItemStack(McfrItems.RAW_SWORDFISH), new ItemStack(McfrItems.COOKED_SWORDFISH));
    addFurnaceRecipe(new ItemStack(McfrItems.RAW_SARDINE), new ItemStack(McfrItems.COOKED_SARDINE));
    addFurnaceRecipe(new ItemStack(McfrItems.RAW_HUNTED_LEG), new ItemStack(McfrItems.COOKED_HUNTED_LEG));
    addFurnaceRecipe(new ItemStack(McfrItems.RAW_HUNTED_STEAK), new ItemStack(McfrItems.COOKED_HUNTED_STEAK));
    addFurnaceRecipe(new ItemStack(McfrItems.RAW_HUNTED_POULTRY), new ItemStack(McfrItems.COOKED_HUNTED_POULTRY));
    addFurnaceRecipe(new ItemStack(McfrItems.RAW_NIALE_MEAT), new ItemStack(McfrItems.COOKED_NIALE_MEAT));
    addFurnaceRecipe(new ItemStack(McfrItems.RAW_HOEN_MEAT), new ItemStack(McfrItems.COOKED_HOEN_MEAT));
    addFurnaceRecipe(new ItemStack(McfrItems.RAW_GALT_MEAT), new ItemStack(McfrItems.COOKED_GALT_MEAT));
    addFurnaceRecipe(new ItemStack(McfrItems.BREAD_DOUGH), new ItemStack(Items.BREAD));
    addFurnaceRecipe(new ItemStack(McfrBlocks.EXOTIC_LOG), new ItemStack(Items.COAL, 1, 1));
  }

  /**
   * Ajoute une recette d'escaliers.
   * 
   * @param out le résultat
   * @param in le matériau
   */
  private static void addStairsRecipe(Block out, ItemStack in) {
    addShapedRecipe(new ItemStack(out, 4), "#  ", "## ", "###", '#', in);
  }

  /**
   * Ajoute une recette de pente.
   * 
   * @param out le résultat
   * @param in le matériau
   */
  private static void addSlopeRecipe(Block out, ItemStack in) {
    addLargeRecipe(new ItemStack(out, 6), "#   ", "##  ", "### ", "####", '#', in);
  }

  /**
   * Ajoute une recette de pyramide.
   * 
   * @param out le résultat
   * @param in le matériau
   */
  private static void addPyramidRecipe(Block out, ItemStack in) {
    addLargeRecipe(new ItemStack(out, 6), "  #  ", " ### ", "#####", '#', in);
  }

  /**
   * Ajoute une recette de portillon.
   * 
   * @param out le résultat
   * @param in le matériau
   */
  private static void addFenceGateRecipe(ItemStack out, ItemStack in) {
    addLargeRecipe(out, "#####", "#   #", "#####", "#   #", '#', in);
  }

  /**
   * Ajoute une recette de barrière.
   * 
   * @param out le résultat
   * @param in le matériau
   */
  private static void addFenceRecipe(ItemStack out, ItemStack in) {
    addLargeRecipe(out, "#   #", "#####", "#   #", "#####", "#   #", '#', in);
  }

  /**
   * Ajoute une recette de muret.
   * 
   * @param out le résultat
   * @param in le matériau
   */
  private static void addWallRecipe(ItemStack out, ItemStack in) {
    addLargeRecipe(out, " ## ", "####", "####", "####", '#', in);
  }

  /**
   * Ajoute une recette.
   * 
   * @param output le résultat
   * @param params les composants
   */
  private static void addShapedRecipe(ItemStack output, Object... params) {
    GameRegistry.addShapedRecipe(output, params);
  }

  /**
   * Ajoute une recette sans contrainte de disposition.
   * 
   * @param output le résultat
   * @param params les composants
   */
  private static void addShapelessRecipe(ItemStack output, Object... params) {
    GameRegistry.addShapelessRecipe(output, params);
  }

  /**
   * Ajoute une recette 5x5.
   * 
   * @param result le résultat
   * @param recipeComponents les composants
   */
  private static void addLargeRecipe(ItemStack result, Object... recipeComponents) {
    RawRecipe c = getComponents(recipeComponents);
    CraftingManager.getInstance().getRecipeList().add(new LargeRecipe(c.getWidth(), c.getHeight(), c.getItems(), result));
  }

  /**
   * Ajoute une recette pour l'enclume.
   * 
   * @param result le résultat
   * @param temperatureMin la température minimale requise pour obtenir le résultat
   * @param temperatureMin la température maximale requise pour obtenir le résultat
   * @param recipeComponents les composants
   */
  private static void addAnvilRecipe(ItemStack result, int temperatureMin, int temperatureMax, Object... recipeComponents) {
    RawRecipe c = getComponents(recipeComponents);
    CraftingManager.getInstance().getRecipeList().add(new AnvilRecipe(c.getWidth(), c.getHeight(), c.getItems(), result, temperatureMin, temperatureMax));
  }

  /**
   * Supprime les recettes dont la sortie correspond au stack donné.
   * 
   * @param out le résultat
   */
  private static void removeRecipe(ItemStack out) {
    CraftingManager.getInstance().getRecipeList().removeIf(recipe -> {
      if (recipe == null)
        return false;
      ItemStack stack = recipe.getRecipeOutput();
      return stack != null && stack.getItem() == out.getItem() && stack.getMetadata() == out.getMetadata() && stack.stackSize == out.stackSize;
    });
  }

  /**
   * Ajoute une recette pour le four.
   * 
   * @param in le matériau
   * @param out le résultat
   */
  private static void addFurnaceRecipe(ItemStack in, ItemStack out) {
    GameRegistry.addSmelting(in, out, 0);
  }

  /**
   * Supprime les recettes du four dont la sortie correspond au stack donné.
   * 
   * @param out le résultat
   */
  private static void removeFurnaceRecipe(ItemStack out) {
    Map<ItemStack, ItemStack> list = FurnaceRecipes.instance().getSmeltingList();

    for (ItemStack key : list.keySet()) {
      ItemStack stack = list.get(key);

      if (stack.getItem() == out.getItem() && stack.getMetadata() == out.getMetadata() && stack.stackSize == out.stackSize) {
        list.remove(key);
        break;
      }
    }
  }

  /**
   * Extrait une recette brute à partir d'une liste d'objets.
   * 
   * @param recipeComponents la liste des composants
   * @return la recette brute
   */
  private static RawRecipe getComponents(Object... recipeComponents) {
    String pattern = "";
    int i = 0;
    int width = 0;
    int height = 0;

    while (recipeComponents[i] instanceof String) {
      String str = (String) recipeComponents[i++];

      height++;
      width = str.length();
      pattern += str;
    }

    Map<Character, ItemStack> charToItemStack = new HashMap<>();

    for (; i < recipeComponents.length; i += 2) {
      Character character = (Character) recipeComponents[i];
      ItemStack stack = null;

      if (recipeComponents[i + 1] instanceof Item) {
        stack = new ItemStack((Item) recipeComponents[i + 1]);
      }
      else if (recipeComponents[i + 1] instanceof Block) {
        stack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
      }
      else if (recipeComponents[i + 1] instanceof ItemStack) {
        stack = (ItemStack) recipeComponents[i + 1];
      }

      charToItemStack.put(character, stack);
    }

    ItemStack[] stacks = new ItemStack[width * height];

    for (int j = 0; j < width * height; j++) {
      char c = pattern.charAt(j);

      if (charToItemStack.containsKey(c)) {
        stacks[j] = charToItemStack.get(c).copy();
      }
      else {
        stacks[j] = null;
      }
    }

    return new RawRecipe(width, height, stacks);
  }

  private McfrCrafts() {}

  /**
   * Une recette brute sert à organiser les composants d'une recette afin de faciliter la
   * déclaration.
   * 
   * @author Mc-Fr
   */
  private static class RawRecipe {
    /** Largeur et hauteur de la grille */
    private int width, height;
    /** Les items */
    private ItemStack[] items;

    public RawRecipe(int width, int height, ItemStack[] items) {
      this.width = width;
      this.height = height;
      this.items = items;
    }

    /**
     * @return la largeur de la grille
     */
    public int getWidth() {
      return this.width;
    }

    /**
     * @return la hauteur de la grille
     */
    public int getHeight() {
      return this.height;
    }

    /**
     * @return les composants
     */
    public ItemStack[] getItems() {
      return this.items;
    }
  }
}
