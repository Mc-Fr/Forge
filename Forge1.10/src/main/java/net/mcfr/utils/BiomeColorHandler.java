package net.mcfr.utils;

import static net.mcfr.McfrBlocks.*;

import net.mcfr.McfrBlocks;
import net.mcfr.environment.plants.BlockExoticLeaves;
import net.mcfr.environment.plants.EnumExoticWoodType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Cette classe s'occupe de la gestion des couleurs des biomes sur les blocs et items.
 *
 * @author Mc-Fr
 */
@SideOnly(Side.CLIENT)
public final class BiomeColorHandler {
  /**
   * Enregistre les gestionnaires.
   */
  public static void init() {
    Minecraft minecraft = Minecraft.getMinecraft();
    BlockColors blockColors = minecraft.getBlockColors();
    ItemColors itemColors = minecraft.getItemColors();

    registerBlocksColorHandlers(blockColors);
    registerItemsColorHandlers(blockColors, itemColors);
  }

  /**
   * Enregistre le rendu des blocs.
   * 
   * @param blockColors le gestionnaire de couleurs des blocs
   */
  private static void registerBlocksColorHandlers(BlockColors blockColors) {
    // Utilise la couleur du biome ou celle par défaut.
    IBlockColor foliageColorHandler = new IBlockColor() {
      @Override
      public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        if (worldIn != null && pos != null) {
          if (state.getBlock() == McfrBlocks.EXOTIC_LEAVES && state.getValue(BlockExoticLeaves.VARIANT) == EnumExoticWoodType.CHERRY_TREE)
            return 0xffffff;
          return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
        }

        return ColorizerFoliage.getFoliageColor(0.5, 1);
      }
    };

    // Utilise la couleur du biome ou celle par défaut.
    IBlockColor grassColorHandler = new IBlockColor() {
      @Override
      public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        if (worldIn != null && pos != null) {
          return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);
        }

        return ColorizerGrass.getGrassColor(0.5, 1);
      }
    };

    blockColors.registerBlockColorHandler(foliageColorHandler, OAK_LEAVES_SLOPE);
    blockColors.registerBlockColorHandler(foliageColorHandler, SPRUCE_LEAVES_SLOPE);
    blockColors.registerBlockColorHandler(foliageColorHandler, BIRCH_LEAVES_SLOPE);
    blockColors.registerBlockColorHandler(foliageColorHandler, JUNGLE_LEAVES_SLOPE);
    blockColors.registerBlockColorHandler(foliageColorHandler, ACACIA_LEAVES_SLOPE);
    blockColors.registerBlockColorHandler(foliageColorHandler, DARK_OAK_LEAVES_SLOPE);
    blockColors.registerBlockColorHandler(foliageColorHandler, OAK_LEAVES_PYRAMID);
    blockColors.registerBlockColorHandler(foliageColorHandler, SPRUCE_LEAVES_PYRAMID);
    blockColors.registerBlockColorHandler(foliageColorHandler, BIRCH_LEAVES_PYRAMID);
    blockColors.registerBlockColorHandler(foliageColorHandler, JUNGLE_LEAVES_PYRAMID);
    blockColors.registerBlockColorHandler(foliageColorHandler, ACACIA_LEAVES_PYRAMID);
    blockColors.registerBlockColorHandler(foliageColorHandler, DARK_OAK_LEAVES_PYRAMID);
    blockColors.registerBlockColorHandler(foliageColorHandler, EXOTIC_LEAVES);
    blockColors.registerBlockColorHandler(foliageColorHandler, APPLE_LEAVES_SLOPE);
    blockColors.registerBlockColorHandler(foliageColorHandler, PALM_LEAVES_SLOPE);
    blockColors.registerBlockColorHandler(foliageColorHandler, BELUXIER_LEAVES_SLOPE);
    blockColors.registerBlockColorHandler(foliageColorHandler, APPLE_LEAVES_PYRAMID);
    blockColors.registerBlockColorHandler(foliageColorHandler, PALM_LEAVES_PYRAMID);
    blockColors.registerBlockColorHandler(foliageColorHandler, BELUXIER_LEAVES_PYRAMID);

    blockColors.registerBlockColorHandler(grassColorHandler, WILD_GRASS);
  }

  /**
   * Enregistre le rendu des items.
   * 
   * @param blockColors le gestionnaire de couleurs des blocs
   * @param itemColors le gestionnaire de couleurs des items
   */
  private static void registerItemsColorHandlers(BlockColors blockColors, ItemColors itemColors) {
    // Utilise le gestionnaire du bloc pour l'item.
    IItemColor itemBlockColorHandler = new IItemColor() {
      @SuppressWarnings("deprecation")
      @Override
      public int getColorFromItemstack(ItemStack stack, int tintIndex) {
        if (stack.getItem() == Item.getItemFromBlock(McfrBlocks.EXOTIC_LEAVES) && stack.getMetadata() == EnumExoticWoodType.CHERRY_TREE.getMetadata())
          return 0xffffff;
        IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
        return blockColors.colorMultiplier(iblockstate, null, null, tintIndex);
      }
    };

    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(OAK_LEAVES_SLOPE));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(SPRUCE_LEAVES_SLOPE));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(BIRCH_LEAVES_SLOPE));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(JUNGLE_LEAVES_SLOPE));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(ACACIA_LEAVES_SLOPE));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(DARK_OAK_LEAVES_SLOPE));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(OAK_LEAVES_PYRAMID));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(SPRUCE_LEAVES_PYRAMID));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(BIRCH_LEAVES_PYRAMID));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(JUNGLE_LEAVES_PYRAMID));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(ACACIA_LEAVES_PYRAMID));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(DARK_OAK_LEAVES_PYRAMID));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(EXOTIC_LEAVES));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(APPLE_LEAVES_SLOPE));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(PALM_LEAVES_SLOPE));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(BELUXIER_LEAVES_SLOPE));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(APPLE_LEAVES_PYRAMID));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(PALM_LEAVES_PYRAMID));
    itemColors.registerItemColorHandler(itemBlockColorHandler, Item.getItemFromBlock(BELUXIER_LEAVES_PYRAMID));

    itemColors.registerItemColorHandler(itemBlockColorHandler, WILD_GRASS);
  }

  private BiomeColorHandler() {}
}