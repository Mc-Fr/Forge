package net.mcfr;

import static net.minecraft.inventory.EntityEquipmentSlot.*;

import net.mcfr.commons.McfrItem;
import net.mcfr.commons.McfrItemBlockSpecial;
import net.mcfr.craftsmanship.ItemStitch;
import net.mcfr.craftsmanship.ItemSwordHandle;
import net.mcfr.decoration.beds.McfrItemBed;
import net.mcfr.decoration.container_blocks.ItemBarrel;
import net.mcfr.decoration.lighting.ItemLantern;
import net.mcfr.decoration.signs.ItemTombstone;
import net.mcfr.decoration.signs.ItemWallNote;
import net.mcfr.decoration.signs.McfrItemSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityNormalSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityOrpSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityPaperSign;
import net.mcfr.economy.ItemClawMoney;
import net.mcfr.economy.ItemCoin;
import net.mcfr.economy.ItemToken;
import net.mcfr.environment.ItemOre;
import net.mcfr.equipment.AdditionalArmorMaterial;
import net.mcfr.equipment.AdditionalToolMaterial;
import net.mcfr.equipment.ItemGrapnel;
import net.mcfr.equipment.ItemHammer;
import net.mcfr.equipment.McfrItemArmor;
import net.mcfr.equipment.McfrItemBow;
import net.mcfr.equipment.McfrItemSword;
import net.mcfr.equipment.fishing.McfrItemFishingRod;
import net.mcfr.farming.ItemFodder;
import net.mcfr.farming.McfrItemSeeds;
import net.mcfr.food.ItemAlcoholDrink;
import net.mcfr.food.ItemFlask;
import net.mcfr.food.McfrItemFood;
import net.mcfr.mecanisms.doors.McfrItemDoor;
import net.mcfr.misc.ItemDecoratedRing;
import net.mcfr.misc.ItemRing;
import net.mcfr.misc.ItemSailBoat;
import net.mcfr.misc.ItemSignedPaper;
import net.mcfr.misc.ItemWriteablePaper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Cette classe regroupe tous les items du mod.
 * 
 * @author Mc-Fr
 */
public final class McfrItems {
  // Monnaie
  public static final ItemCoin COIN = new ItemCoin();
  public static final ItemToken TOKEN = new ItemToken();
  public static final ItemClawMoney CLAW_MONEY = new ItemClawMoney();
  // Divers
  public static final ItemOre ORE = new ItemOre();
  public static final McfrItem INK = new McfrItem("ink", CreativeTabs.MATERIALS);
  /** Fourrage */
  public static final ItemFodder FODDER = new ItemFodder();
  public static final McfrItem PIPE = new McfrItem("pipe", 1, CreativeTabs.MISC);
  public static final ItemWriteablePaper WRITEABLE_PAPER = new ItemWriteablePaper();
  public static final ItemSignedPaper SIGNED_PAPER = new ItemSignedPaper();
  public static final ItemRing SILVER_RING = new ItemRing("silver");
  public static final ItemRing GOLDEN_RING = new ItemRing("golden");
  public static final ItemDecoratedRing DECORATED_RING = new ItemDecoratedRing();
  /** Bateau à voiles */
  public static final ItemSailBoat SAILBOAT = new ItemSailBoat();
  // Artisanat
  public static final McfrItem SAW_SUPPORT = new McfrItem("saw_support", 16, CreativeTabs.MATERIALS);
  public static final McfrItem SAW_BLADE = new McfrItem("saw_blade", 16, CreativeTabs.MATERIALS);
  /** Bobine de fil */
  public static final McfrItem THREAD_COIL = new McfrItem("thread_coil", CreativeTabs.MATERIALS);
  /** Rouleau de tissu */
  public static final McfrItem CLOTH_ROLL = new McfrItem("cloth_roll", CreativeTabs.MATERIALS);
  public static final McfrItem STEEL_PLATE = new McfrItem("steel_plate", CreativeTabs.MATERIALS);
  /** Maille */
  public static final ItemStitch STITCH = new ItemStitch();
  public static final ItemSwordHandle SWORD_HANDLE = new ItemSwordHandle();
  // Clés
  public static final McfrItem KEYRING = new McfrItem("keyring", CreativeTabs.MISC);
  public static final McfrItem KEY = new McfrItem("key", CreativeTabs.MISC);
  // Agriculture
  public static final McfrItemSeeds BARLEY_SEEDS = new McfrItemSeeds("barley_seeds", McfrBlocks.BARLEY, Blocks.FARMLAND);
  public static final McfrItem BARLEY = new McfrItem("barley", CreativeTabs.MATERIALS);
  public static final McfrItemSeeds GRAPE_SEEDS = new McfrItemSeeds("grape_seeds", McfrBlocks.VINE_BASE, Blocks.FARMLAND);
  public static final McfrItem GRAPES = new McfrItem("grapes", CreativeTabs.FOOD);
  public static final McfrItemBlockSpecial SUGAR_CANES = new McfrItemBlockSpecial("sugar_canes", McfrBlocks.SUGAR_CANES, CreativeTabs.MATERIALS);
  public static final McfrItemBlockSpecial HEMP = new McfrItemBlockSpecial("hemp", McfrBlocks.HEMP_BASE, CreativeTabs.MATERIALS);
  public static final McfrItem HEMP_FLOWER = new McfrItem("hemp_flower", CreativeTabs.MATERIALS);
  public static final McfrItem HEMP_LEAF = new McfrItem("hemp_leaf", CreativeTabs.MATERIALS);
  public static final McfrItem HEMP_FIBER = new McfrItem("hemp_fiber", CreativeTabs.MATERIALS);
  public static final McfrItem HEMP_OIL = new McfrItem("hemp_oil", CreativeTabs.MATERIALS);
  public static final McfrItem POOP = new McfrItem("poop", CreativeTabs.MATERIALS);
  public static final McfrItem FERTILIZER = new McfrItem("fertilizer", CreativeTabs.MISC);
  // Organique
  public static final McfrItem WAX = new McfrItem("wax", 16, CreativeTabs.MATERIALS);
  public static final McfrItemBlockSpecial REEDS = new McfrItemBlockSpecial("reed", McfrBlocks.REEDS, CreativeTabs.MATERIALS);
  // Tonneaux
  public static final McfrItemBlockSpecial EMPTY_BARREL = new McfrItemBlockSpecial("empty_barrel", McfrBlocks.EMPTY_BARREL, CreativeTabs.DECORATIONS);
  public static final ItemBarrel BEER_BARREL = new ItemBarrel(McfrBlocks.BEER_BARREL);
  public static final ItemBarrel CIDER_BARREL = new ItemBarrel(McfrBlocks.CIDER_BARREL);
  public static final ItemBarrel WINE_BARREL = new ItemBarrel(McfrBlocks.WINE_BARREL);
  public static final ItemBarrel RUM_BARREL = new ItemBarrel(McfrBlocks.RUM_BARREL);
  // Boissons et récipients vides
  public static final McfrItem EMPTY_TANKARD = new McfrItem("empty_tankard", 8, CreativeTabs.MATERIALS);
  public static final ItemAlcoholDrink BEER_TANKARD = new ItemAlcoholDrink("beer_tankard", EMPTY_TANKARD, 2, 0.2f);
  public static final McfrItem EMPTY_BOWL = new McfrItem("empty_bowl", 8, CreativeTabs.MATERIALS);
  public static final ItemAlcoholDrink CIDER_BOWL = new ItemAlcoholDrink("cider_bowl", EMPTY_BOWL, 2, 0.2f);
  public static final McfrItem EMPTY_GLASS = new McfrItem("empty_glass", 8, CreativeTabs.MATERIALS);
  public static final ItemAlcoholDrink WINE_GLASS = new ItemAlcoholDrink("wine_glass", EMPTY_GLASS, 2, 0.2f);
  public static final McfrItem EMPTY_BOTTLE = new McfrItem("empty_bottle", 8, CreativeTabs.MATERIALS);
  public static final ItemAlcoholDrink RUM_BOTTLE = new ItemAlcoholDrink("rum_bottle", EMPTY_BOTTLE, 2, 0.2f);
  public static final ItemFlask FLASK = new ItemFlask(Items.GLASS_BOTTLE, 0, 0.2f);
  // Nourriture, ingrédients et ustensiles de cuisine
  public static final McfrItem CAKE_DOUGH = new McfrItem("cake_dough", CreativeTabs.MATERIALS);
  public static final McfrItemBlockSpecial CHOCOLATE_CAKE = McfrBlocks.CHOCOLATE_CAKE.getItem();
  public static final McfrItemFood APPLE_PIE = (McfrItemFood) new McfrItemFood("apple_pie", 6, 9).setMaxStackSize(1);
  public static final McfrItemFood PUMPKIN_SOUP = (McfrItemFood) new McfrItemFood("pumpkin_soup", 3, 5.8f).setMaxStackSize(1);
  public static final McfrItem FLOUR = new McfrItem("flour", CreativeTabs.MATERIALS);
  public static final McfrItem BREAD_DOUGH = new McfrItem("bread_dough", CreativeTabs.MATERIALS);
  public static final McfrItem KITCHEN_MORTAR = new McfrItem("kitchen_mortar", 16, CreativeTabs.MATERIALS);
  public static final McfrItemFood RAW_SWORDFISH = new McfrItemFood("raw_swordfish", 3, 0.5f);
  public static final McfrItemFood COOKED_SWORDFISH = new McfrItemFood("cooked_swordfish", 5, 6);
  public static final McfrItemFood RAW_SARDINE = new McfrItemFood("raw_sardine", 1, 0.4f);
  public static final McfrItemFood COOKED_SARDINE = new McfrItemFood("cooked_sardine", 3, 1.4f);
  public static final McfrItemFood HONEY = (McfrItemFood) new McfrItemFood("honey", 2, 0.4f).setMaxStackSize(1);
  public static final McfrItemFood POIGRUME = new McfrItemFood("poigrume", 1, 0.8f);
  public static final McfrItemFood POIGRUME_COOKIE = new McfrItemFood("poigrume_cookie", 2, 3.2f);
  public static final McfrItem COCOA = new McfrItem("cocoa", CreativeTabs.MATERIALS);
  // Loots de chasse
  public static final McfrItemFood RAW_HUNTED_LEG = new McfrItemFood("raw_hunted_leg", 3, 0.6f);
  public static final McfrItemFood COOKED_HUNTED_LEG = new McfrItemFood("cooked_hunted_leg", 4, 8.8f);
  public static final McfrItemFood RAW_HUNTED_STEAK = new McfrItemFood("raw_hunted_steak", 3, 0.6f);
  public static final McfrItemFood COOKED_HUNTED_STEAK = new McfrItemFood("cooked_hunted_steak", 6, 10.6f);
  public static final McfrItemFood RAW_HUNTED_POULTRY = new McfrItemFood("raw_hunted_poultry", 2, 0.5f);
  public static final McfrItemFood COOKED_HUNTED_POULTRY = new McfrItemFood("cooked_hunted_poultry", 4, 6.8f);
  public static final McfrItem HUNTED_SKIN = new McfrItem("hunted_skin", CreativeTabs.MATERIALS);
  // Loots d'élevage
  public static final McfrItemFood RAW_NIALE_MEAT = new McfrItemFood("raw_niale_meat", 3, 0.6f);
  public static final McfrItemFood COOKED_NIALE_MEAT = new McfrItemFood("cooked_niale_meat", 6, 12.8f);
  public static final McfrItemFood RAW_HOEN_MEAT = new McfrItemFood("raw_hoen_meat", 2, 0.5f);
  public static final McfrItemFood COOKED_HOEN_MEAT = new McfrItemFood("cooked_hoen_meat", 6, 9.2f);
  public static final McfrItemFood RAW_GALT_MEAT = new McfrItemFood("raw_galt_meat", 3, 0.6f);
  public static final McfrItemFood COOKED_GALT_MEAT = new McfrItemFood("cooked_galt_meat", 6, 12.8f);
  // Portes
  public static final McfrItemDoor STRONG_OAK_DOOR = McfrBlocks.STRONG_OAK_DOOR.getItem();
  public static final McfrItemDoor CRAFTSMAN_OAK_DOOR = McfrBlocks.CRAFTSMAN_OAK_DOOR.getItem();
  public static final McfrItemDoor CRAFTSMAN_SPRUCE_DOOR = McfrBlocks.CRAFTSMAN_SPRUCE_DOOR.getItem();
  public static final McfrItemDoor CRAFTSMAN_BIRCH_DOOR = McfrBlocks.CRAFTSMAN_BIRCH_DOOR.getItem();
  public static final McfrItemDoor CRAFTSMAN_JUNGLE_DOOR = McfrBlocks.CRAFTSMAN_JUNGLE_DOOR.getItem();
  public static final McfrItemDoor CRAFTSMAN_ACACIA_DOOR = McfrBlocks.CRAFTSMAN_ACACIA_DOOR.getItem();
  public static final McfrItemDoor CRAFTSMAN_DARK_OAK_DOOR = McfrBlocks.CRAFTSMAN_DARK_OAK_DOOR.getItem();
  // Lits
  public static final McfrItemBed HAY_BED = McfrBlocks.HAY_BED.getItem();
  public static final McfrItemBed NORMAL_BED = McfrBlocks.NORMAL_BED.getItem();
  public static final McfrItemBed STONE_BED = McfrBlocks.STONE_BED.getItem();
  public static final McfrItemBed SLEEPING_BAG = McfrBlocks.SLEEPING_BAG.getItem();
  // Éclairage
  public static final McfrItemBlockSpecial CAMPFIRE = new McfrItemBlockSpecial("campfire", McfrBlocks.CAMPFIRE, CreativeTabs.DECORATIONS);
  public static final McfrItemBlockSpecial LIT_CAMPFIRE = new McfrItemBlockSpecial("lit_campfire", McfrBlocks.LIT_CAMPFIRE, CreativeTabs.DECORATIONS);
  public static final ItemLantern LANTERN = new ItemLantern(false);
  public static final ItemLantern PAPER_LANTERN = new ItemLantern(true);
  // Vitrines/stands
  public static final McfrItemBlockSpecial ARMOR_STAND = new McfrItemBlockSpecial("armor_stand", McfrBlocks.ARMOR_STAND,
      null /* CreativeTabs.DECORATIONS */); // TEMP
  public static final McfrItemBlockSpecial WEAPONS_STAND = new McfrItemBlockSpecial("weapons_stand", McfrBlocks.WEAPONS_STAND,
      null /* CreativeTabs.DECORATIONS */); // TEMP
  public static final McfrItemBlockSpecial SHOWCASE = new McfrItemBlockSpecial("showcase", McfrBlocks.SHOWCASE, CreativeTabs.DECORATIONS);
  // Panneaux et affiches
  public static final ItemWallNote WALL_NOTE = new ItemWallNote();
  public static final ItemTombstone TOMBSTONE = new ItemTombstone();
  public static final McfrItemSign SIGN = new McfrItemSign("sign", McfrBlocks.STANDING_SIGN, McfrBlocks.WALL_SIGN, McfrBlocks.SUSPENDED_SIGN, TileEntityNormalSign.class);
  public static final McfrItemSign PAPER_SIGN = new McfrItemSign("paper_sign", McfrBlocks.STANDING_PAPER_SIGN, McfrBlocks.WALL_PAPER_SIGN, McfrBlocks.SUSPENDED_PAPER_SIGN, TileEntityPaperSign.class);
  public static final McfrItemSign ORP_SIGN = new McfrItemSign("orp_sign", McfrBlocks.STANDING_ORP_SIGN, McfrBlocks.WALL_ORP_SIGN, null, TileEntityOrpSign.class);
  // Redstone
  public static final McfrItemBlockSpecial LONG_REPEATER = new McfrItemBlockSpecial("long_repeater", McfrBlocks.LONG_REPEATER_OFF, CreativeTabs.REDSTONE);
  // Outils
  public static final McfrItem HAMMER = new ItemHammer();
  /** Grappin */
  public static final ItemGrapnel GRAPNEL = new ItemGrapnel();
  // Pêche
  public static final McfrItemFishingRod GOOD_FISHING_ROD = new McfrItemFishingRod("good_fishing_rod");
  public static final McfrItemFishingRod FISHING_NET = new McfrItemFishingRod("fishing_net");
  // Épées
  public static final McfrItemSword POINTY_STICK = new McfrItemSword("pointy", "stick", ToolMaterial.WOOD);
  public static final McfrItemSword BARBARIAN_SWORD = new McfrItemSword("barbarian", AdditionalToolMaterial.BARBARIAN);
  // Dagues
  public static final McfrItemSword STONE_DAGGER = new McfrItemSword("stone", "dagger", ToolMaterial.STONE);
  public static final McfrItemSword IRON_DAGGER = new McfrItemSword("iron", "dagger", ToolMaterial.IRON);
  public static final McfrItemSword GOLDEN_DAGGER = new McfrItemSword("golden", "dagger", ToolMaterial.GOLD);
  public static final McfrItemSword STEEL_DAGGER = new McfrItemSword("steel", "dagger", AdditionalToolMaterial.STEEL);
  public static final McfrItemSword GEROUN_DAGGER = new McfrItemSword("geroun", "dagger", AdditionalToolMaterial.GEROUN);
  // Cimeterres
  public static final McfrItemSword STONE_SCIMITAR = new McfrItemSword("stone", "scimitar", ToolMaterial.STONE);
  public static final McfrItemSword IRON_SCIMITAR = new McfrItemSword("iron", "scimitar", ToolMaterial.IRON);
  public static final McfrItemSword GOLDEN_SCIMITAR = new McfrItemSword("golden", "scimitar", ToolMaterial.GOLD);
  public static final McfrItemSword STEEL_SCIMITAR = new McfrItemSword("steel", "scimitar", AdditionalToolMaterial.STEEL);
  // Rapières
  public static final McfrItemSword IRON_RAPIER = new McfrItemSword("iron", "rapier", ToolMaterial.IRON);
  public static final McfrItemSword GOLDEN_RAPIER = new McfrItemSword("golden", "rapier", ToolMaterial.GOLD);
  public static final McfrItemSword STEEL_RAPIER = new McfrItemSword("steel", "rapier", AdditionalToolMaterial.STEEL);
  // Épées batardes
  public static final McfrItemSword IRON_BASTARD = new McfrItemSword("iron_bastard", ToolMaterial.IRON);
  public static final McfrItemSword GOLDEN_BASTARD = new McfrItemSword("golden_bastard", ToolMaterial.GOLD);
  public static final McfrItemSword STEEL_BASTARD = new McfrItemSword("steel_bastard", AdditionalToolMaterial.STEEL);
  // Lances
  public static final McfrItemSword STONE_SPEAR = new McfrItemSword("stone", "spear", ToolMaterial.STONE);
  public static final McfrItemSword IRON_SPEAR = new McfrItemSword("iron", "spear", ToolMaterial.IRON);
  public static final McfrItemSword GOLDEN_SPEAR = new McfrItemSword("golden", "spear", ToolMaterial.GOLD);
  public static final McfrItemSword STEEL_SPEAR = new McfrItemSword("steel", "spear", AdditionalToolMaterial.STEEL);
  // Hallebardes
  public static final McfrItemSword IRON_HALBERD = new McfrItemSword("iron", "halberd", ToolMaterial.IRON);
  public static final McfrItemSword GOLDEN_HALBERD = new McfrItemSword("golden", "halberd", ToolMaterial.GOLD);
  public static final McfrItemSword STEEL_HALBERD = new McfrItemSword("steel", "halberd", AdditionalToolMaterial.STEEL);
  // Haches de guerre
  public static final McfrItemSword IRON_BATTLE_AXE = new McfrItemSword("iron", "battle_axe", ToolMaterial.IRON);
  public static final McfrItemSword GOLDEN_BATTLE_AXE = new McfrItemSword("golden", "battle_axe", ToolMaterial.GOLD);
  public static final McfrItemSword STEEL_BATTLE_AXE = new McfrItemSword("steel", "battle_axe", AdditionalToolMaterial.STEEL);
  public static final McfrItemSword BARBARIAN_BATTLE_AXE = new McfrItemSword("barbarian", "battle_axe", AdditionalToolMaterial.BARBARIAN);
  // Marteaux
  public static final McfrItemSword IRON_HAMMER = new McfrItemSword("iron", "war_hammer", ToolMaterial.IRON);
  public static final McfrItemSword GOLDEN_HAMMER = new McfrItemSword("golden", "war_hammer", ToolMaterial.GOLD);
  public static final McfrItemSword STEEL_HAMMER = new McfrItemSword("steel", "war_hammer", AdditionalToolMaterial.STEEL);
  // Masses d'armes
  public static final McfrItemSword IRON_MACE = new McfrItemSword("iron", "mace", ToolMaterial.IRON);
  public static final McfrItemSword GOLDEN_MACE = new McfrItemSword("golden", "mace", ToolMaterial.GOLD);
  public static final McfrItemSword STEEL_MACE = new McfrItemSword("steel", "mace", AdditionalToolMaterial.STEEL);
  // Arcs
  public static final McfrItemBow IRON_BOW = new McfrItemBow("iron", 0);
  public static final McfrItemBow GOLDEN_BOW = new McfrItemBow("golden", 0);
  public static final McfrItemBow STEEL_BOW = new McfrItemBow("steel", 0);
  public static final McfrItemBow BARBARIAN_BOW = new McfrItemBow("barbarian", 0);
  public static final McfrItemBow LONG_BOW = new McfrItemBow("long", 0);
  public static final McfrItemBow HUNTER_BOW = new McfrItemBow("hunter", 0);
  public static final McfrItemBow LONG_HUNTER_BOW = new McfrItemBow("long_hunter", 0);
  public static final McfrItemBow ANCIENT_BOW = new McfrItemBow("ancient", 0);
  // Armure d'assassin
  public static final McfrItemArmor ASSASSIN_HELMET = new McfrItemArmor("assassin", AdditionalArmorMaterial.ASSASSIN, HEAD);
  public static final McfrItemArmor ASSASSIN_CHESTPLATE = new McfrItemArmor("assassin", AdditionalArmorMaterial.ASSASSIN, CHEST);
  public static final McfrItemArmor ASSASSIN_LEGGINGS = new McfrItemArmor("assassin", AdditionalArmorMaterial.ASSASSIN, LEGS);
  public static final McfrItemArmor ASSASSIN_BOOTS = new McfrItemArmor("assassin", AdditionalArmorMaterial.ASSASSIN, FEET);
  // Armure barbare
  public static final McfrItemArmor BARBARIAN_HELMET = new McfrItemArmor("barbarian", AdditionalArmorMaterial.BARBARIAN, HEAD);
  public static final McfrItemArmor BARBARIAN_CHESTPLATE = new McfrItemArmor("barbarian", AdditionalArmorMaterial.BARBARIAN, CHEST);
  public static final McfrItemArmor BARBARIAN_LEGGINGS = new McfrItemArmor("barbarian", AdditionalArmorMaterial.BARBARIAN, LEGS);
  public static final McfrItemArmor BARBARIAN_BOOTS = new McfrItemArmor("barbarian", AdditionalArmorMaterial.BARBARIAN, FEET);
  // Cotte de mailles en or
  public static final McfrItemArmor GOLDEN_CHAINMAIL_HELMET = new McfrItemArmor("golden_chainmail", AdditionalArmorMaterial.GOLDEN_CHAIN, HEAD);
  public static final McfrItemArmor GOLDEN_CHAINMAIL_CHESTPLATE = new McfrItemArmor("golden_chainmail", AdditionalArmorMaterial.GOLDEN_CHAIN, CHEST);
  public static final McfrItemArmor GOLDEN_CHAINMAIL_LEGGINGS = new McfrItemArmor("golden_chainmail", AdditionalArmorMaterial.GOLDEN_CHAIN, LEGS);
  public static final McfrItemArmor GOLDEN_CHAINMAIL_BOOTS = new McfrItemArmor("golden_chainmail", AdditionalArmorMaterial.GOLDEN_CHAIN, FEET);
  // Couronne
  public static final McfrItemArmor CROWN = new McfrItemArmor("crown", ArmorMaterial.GOLD, HEAD, false);
  // Items admin
  public static final McfrItem MESUREING_TAPE = new McfrItem("mesureing_tape", 1, CreativeTabs.TOOLS);
  /** Tromblon */
  public static final McfrItem BLUNDERBUSS = new McfrItem("blunderbuss", 1, CreativeTabs.TOOLS);
  public static final McfrItem MAGIC_WAND = new McfrItem("magic_wand", 1, CreativeTabs.TOOLS);
  public static final McfrItem LIGHTNING = new McfrItem("lightning", 1, CreativeTabs.TOOLS);

  /**
   * Initialise tous les items.
   */
  public static void init() {
    Items.PUMPKIN_PIE.setMaxStackSize(1);
    Items.CAULDRON.setMaxStackSize(1);
    Items.SIGN.setCreativeTab(null);
    Items.ENCHANTED_BOOK.setCreativeTab(null);
    setFoodAlwaysEdible();
    // TEMP
    CRAFTSMAN_OAK_DOOR.setCreativeTab(null);
    CRAFTSMAN_SPRUCE_DOOR.setCreativeTab(null);
    CRAFTSMAN_BIRCH_DOOR.setCreativeTab(null);
    CRAFTSMAN_JUNGLE_DOOR.setCreativeTab(null);
    CRAFTSMAN_ACACIA_DOOR.setCreativeTab(null);
    CRAFTSMAN_DARK_OAK_DOOR.setCreativeTab(null);

    register(COIN);
    register(TOKEN);
    register(CLAW_MONEY);

    register(ORE);
    register(INK);
    register(FODDER);
    register(PIPE);
    register(WRITEABLE_PAPER);
    register(SIGNED_PAPER);
    register(SILVER_RING);
    register(GOLDEN_RING);
    register(DECORATED_RING);
    register(SAILBOAT);

    register(SAW_SUPPORT);
    register(SAW_BLADE);
    register(THREAD_COIL);
    register(CLOTH_ROLL);
    register(STEEL_PLATE);
    register(STITCH);
    register(SWORD_HANDLE);

    register(KEYRING);
    register(KEY);

    register(BARLEY_SEEDS);
    register(BARLEY);
    register(GRAPE_SEEDS);
    register(GRAPES);
    register(SUGAR_CANES);
    register(HEMP);
    register(HEMP_FLOWER);
    register(HEMP_LEAF);
    register(HEMP_FIBER);
    register(HEMP_OIL);
    register(POOP);
    register(FERTILIZER);

    register(WAX);
    register(REEDS);

    register(EMPTY_BARREL);
    register(BEER_BARREL);
    register(CIDER_BARREL);
    register(WINE_BARREL);
    register(RUM_BARREL);

    register(EMPTY_TANKARD);
    register(BEER_TANKARD);
    register(EMPTY_BOWL);
    register(CIDER_BOWL);
    register(EMPTY_GLASS);
    register(WINE_GLASS);
    register(EMPTY_BOTTLE);
    register(RUM_BOTTLE);
    register(FLASK);

    register(CAKE_DOUGH);
    register(CHOCOLATE_CAKE);
    register(APPLE_PIE);
    register(PUMPKIN_SOUP);
    register(FLOUR);
    register(BREAD_DOUGH);
    register(KITCHEN_MORTAR);
    register(RAW_SWORDFISH);
    register(COOKED_SWORDFISH);
    register(RAW_SARDINE);
    register(COOKED_SARDINE);
    register(HONEY);
    register(POIGRUME);
    register(POIGRUME_COOKIE);
    register(COCOA);

    register(RAW_HUNTED_LEG);
    register(COOKED_HUNTED_LEG);
    register(RAW_HUNTED_STEAK);
    register(COOKED_HUNTED_STEAK);
    register(RAW_HUNTED_POULTRY);
    register(COOKED_HUNTED_POULTRY);
    register(HUNTED_SKIN);

    register(RAW_NIALE_MEAT);
    register(COOKED_NIALE_MEAT);
    register(RAW_HOEN_MEAT);
    register(COOKED_HOEN_MEAT);
    register(RAW_GALT_MEAT);
    register(COOKED_GALT_MEAT);

    register(STRONG_OAK_DOOR);
    register(CRAFTSMAN_OAK_DOOR);
    register(CRAFTSMAN_SPRUCE_DOOR);
    register(CRAFTSMAN_BIRCH_DOOR);
    register(CRAFTSMAN_JUNGLE_DOOR);
    register(CRAFTSMAN_ACACIA_DOOR);
    register(CRAFTSMAN_DARK_OAK_DOOR);

    register(HAY_BED);
    register(NORMAL_BED);
    register(STONE_BED);
    register(SLEEPING_BAG);

    register(CAMPFIRE);
    register(LIT_CAMPFIRE);
    register(LANTERN);
    register(PAPER_LANTERN);

    register(ARMOR_STAND);
    register(WEAPONS_STAND);
    register(SHOWCASE);

    register(WALL_NOTE);
    register(TOMBSTONE);
    register(SIGN);
    register(PAPER_SIGN);
    register(ORP_SIGN);

    register(LONG_REPEATER);

    register(HAMMER);
    register(GRAPNEL);

    register(GOOD_FISHING_ROD);
    register(FISHING_NET);

    register(POINTY_STICK);
    register(BARBARIAN_SWORD);

    register(STONE_DAGGER);
    register(IRON_DAGGER);
    register(GOLDEN_DAGGER);
    register(STEEL_DAGGER);
    register(GEROUN_DAGGER);

    register(STONE_SCIMITAR);
    register(IRON_SCIMITAR);
    register(GOLDEN_SCIMITAR);
    register(STEEL_SCIMITAR);

    register(IRON_RAPIER);
    register(GOLDEN_RAPIER);
    register(STEEL_RAPIER);

    register(IRON_BASTARD);
    register(GOLDEN_BASTARD);
    register(STEEL_BASTARD);

    register(STONE_SPEAR);
    register(IRON_SPEAR);
    register(GOLDEN_SPEAR);
    register(STEEL_SPEAR);

    register(IRON_HALBERD);
    register(GOLDEN_HALBERD);
    register(STEEL_HALBERD);

    register(IRON_BATTLE_AXE);
    register(GOLDEN_BATTLE_AXE);
    register(STEEL_BATTLE_AXE);
    register(BARBARIAN_BATTLE_AXE);

    register(IRON_HAMMER);
    register(GOLDEN_HAMMER);
    register(STEEL_HAMMER);

    register(IRON_MACE);
    register(GOLDEN_MACE);
    register(STEEL_MACE);

    register(IRON_BOW);
    register(GOLDEN_BOW);
    register(STEEL_BOW);
    register(BARBARIAN_BOW);
    register(LONG_BOW);
    register(HUNTER_BOW);
    register(LONG_HUNTER_BOW);
    register(ANCIENT_BOW);

    register(ASSASSIN_HELMET);
    register(ASSASSIN_CHESTPLATE);
    register(ASSASSIN_LEGGINGS);
    register(ASSASSIN_BOOTS);

    register(BARBARIAN_HELMET);
    register(BARBARIAN_CHESTPLATE);
    register(BARBARIAN_LEGGINGS);
    register(BARBARIAN_BOOTS);

    register(GOLDEN_CHAINMAIL_HELMET);
    register(GOLDEN_CHAINMAIL_CHESTPLATE);
    register(GOLDEN_CHAINMAIL_LEGGINGS);
    register(GOLDEN_CHAINMAIL_BOOTS);

    register(CROWN);

    register(MESUREING_TAPE);
    register(BLUNDERBUSS);
    register(MAGIC_WAND);
    register(LIGHTNING);

    McfrBlocks.LIT_CAMPFIRE.addRecipe(McfrItems.RAW_HUNTED_LEG, McfrItems.COOKED_HUNTED_LEG);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(McfrItems.RAW_HUNTED_STEAK, McfrItems.COOKED_HUNTED_STEAK);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(McfrItems.RAW_HUNTED_POULTRY, McfrItems.COOKED_HUNTED_POULTRY);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(McfrItems.RAW_HOEN_MEAT, McfrItems.COOKED_HOEN_MEAT);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(McfrItems.RAW_NIALE_MEAT, McfrItems.COOKED_NIALE_MEAT);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(McfrItems.RAW_GALT_MEAT, McfrItems.COOKED_GALT_MEAT);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(Items.PORKCHOP, Items.COOKED_PORKCHOP);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(Items.BEEF, Items.COOKED_BEEF);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(Items.CHICKEN, Items.COOKED_CHICKEN);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(Items.FISH, Items.COOKED_FISH);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(RAW_SARDINE, COOKED_SARDINE);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(RAW_SWORDFISH, COOKED_SWORDFISH);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(Items.POTATO, Items.BAKED_POTATO);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(Items.RABBIT, Items.COOKED_RABBIT);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(Items.MUTTON, Items.COOKED_MUTTON);
    McfrBlocks.LIT_CAMPFIRE.addRecipe(Items.STICK, Item.getItemFromBlock(Blocks.TORCH));
  }

  /**
   * Enregistre un item.
   * 
   * @param item l'item
   */
  private static void register(Item item) {
    GameRegistry.register(item);
  }

  /**
   * Permet aux joueurs de manger peu importe le niveau de satiété.
   */
  private static void setFoodAlwaysEdible() {
    for (ResourceLocation rl : Item.REGISTRY.getKeys()) {
      Item item = Item.REGISTRY.getObject(rl);

      if (item instanceof ItemFood) {
        ((ItemFood) item).setAlwaysEdible();
      }
    }
  }

  private McfrItems() {}
}
