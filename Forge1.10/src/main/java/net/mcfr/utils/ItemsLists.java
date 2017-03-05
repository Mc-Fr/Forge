package net.mcfr.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mcfr.McfrBlocks;
import net.mcfr.McfrItems;
import net.mcfr.craftsmanship.BlockCircularSaw;
import net.mcfr.craftsmanship.BlockLoom;
import net.mcfr.craftsmanship.BlockMortar;
import net.mcfr.craftsmanship.BlockTanningRack;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.mcfr.decoration.container_blocks.BlockBookshelf;
import net.mcfr.decoration.container_blocks.BlockCrate;
import net.mcfr.decoration.container_blocks.BlockFoodCrate;
import net.mcfr.decoration.container_blocks.BlockLittleChest;
import net.mcfr.decoration.container_blocks.BlockPallet;
import net.mcfr.decoration.container_blocks.ItemBarrel;
import net.mcfr.decoration.container_blocks.guis.ContainerRestricted;
import net.mcfr.decoration.signs.ItemTombstone;
import net.mcfr.environment.plants.EnumExoticWoodType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;

/**
 * Cette classe fournit des méthodes pour classer les items.
 *
 * @author Mc-Fr
 */
public final class ItemsLists {
  /** Les items triés par type */
  private static final Map<String, List<Item>> TYPES = new HashMap<>();
  /** Les items acceptés par chaque bloc */
  private static final Map<Class<? extends Block>, List<Item>> LISTS = new HashMap<>();
  /** Les crafts associés à chaque bloc artisan */
  private static final Map<Class<? extends Block>, Map<HashedItemStack, HashedItemStack>> MAPS = new HashMap<>();

  static {
    initLittleChestItems();
    initCrateItems();
    initFoodCrateItems();
    initPalletItems();
    initBookshelfItems();
    initLoomItems();
    initTanningRackItems();
    initCircularSawItems();
    initMortarItems();
  }

  /**
   * @return une liste non modifiable de tous les outils
   */
  public static List<Item> getTools() {
    String key = "tools";

    if (!TYPES.containsKey(key)) {
      List<Item> l = new ArrayList<>();

      for (ResourceLocation r : Item.REGISTRY.getKeys()) {
        Item i = Item.REGISTRY.getObject(r);

        if (i instanceof ItemTool || i instanceof ItemHoe || i instanceof ItemShears || i instanceof ItemFishingRod
            || i instanceof ItemFlintAndSteel) {
          l.add(i);
        }
      }

      TYPES.put(key, Collections.unmodifiableList(l));
    }

    return TYPES.get(key);
  }

  /**
   * @return une liste non modifiable de toutes les armes (épées et arcs)
   */
  public static List<Item> getWeapons() {
    String key = "weapons";

    if (!TYPES.containsKey(key)) {
      List<Item> l = new ArrayList<>();

      for (ResourceLocation r : Item.REGISTRY.getKeys()) {
        Item i = Item.REGISTRY.getObject(r);

        if (i instanceof ItemSword || i instanceof ItemBow) {
          l.add(i);
        }
      }

      TYPES.put(key, Collections.unmodifiableList(l));
    }

    return TYPES.get(key);
  }

  /**
   * @return une liste non modifiable de toutes les pièces d'armure
   */
  public static List<Item> getArmorPieces() {
    String key = "armor";

    if (!TYPES.containsKey(key)) {
      List<Item> l = new ArrayList<>();

      for (ResourceLocation r : Item.REGISTRY.getKeys()) {
        Item i = Item.REGISTRY.getObject(r);

        if (i instanceof ItemArmor) {
          l.add(i);
        }
      }

      TYPES.put(key, Collections.unmodifiableList(l));
    }

    return TYPES.get(key);
  }

  /**
   * @return une liste non modifiable des items de nourriture
   */
  public static List<Item> getFood() {
    String key = "food";

    if (!TYPES.containsKey(key)) {
      List<Item> l = new ArrayList<>();

      for (ResourceLocation r : Item.REGISTRY.getKeys()) {
        Item i = Item.REGISTRY.getObject(r);

        if (i instanceof ItemFood && i != Items.FERMENTED_SPIDER_EYE) {
          l.add(i);
        }
      }
      l.add(Item.getItemFromBlock(Blocks.PUMPKIN));
      l.add(Item.getItemFromBlock(Blocks.MELON_BLOCK));

      TYPES.put(key, Collections.unmodifiableList(l));
    }

    return TYPES.get(key);
  }

  /**
   * @return une liste non modifiable des objets larges
   */
  public static List<Item> getLargeObjects() {
    String key = "large";

    if (!TYPES.containsKey(key)) {
      List<Item> l = new ArrayList<>();

      for (ResourceLocation r : Block.REGISTRY.getKeys()) {
        Block b = Block.REGISTRY.getObject(r);

        if (b != Blocks.MELON_BLOCK && b != Blocks.PUMPKIN && !(b instanceof BlockSapling) && !(b instanceof BlockFlower)
            && !(b instanceof BlockLeaves) && b != Blocks.WEB && b != Blocks.GRASS && b != Blocks.CACTUS && b != Blocks.VINE
            && !(b instanceof BlockButton) && b != Blocks.LEVER && b != Blocks.REDSTONE_WIRE && b != Blocks.TRIPWIRE_HOOK && b != Blocks.TRIPWIRE)
          l.add(Item.getItemFromBlock(b));
      }

      for (ResourceLocation r : Item.REGISTRY.getKeys()) {
        Item i = Item.REGISTRY.getObject(r);

        if (i instanceof ItemMinecart || i instanceof ItemBarrel || i instanceof ItemTombstone || i == Items.CAULDRON || i == McfrItems.SAW_SUPPORT)
          l.add(i);
      }

      TYPES.put(key, Collections.unmodifiableList(l));
    }

    return TYPES.get(key);
  }

  /**
   * @return une liste non modifiable des petits objets
   */
  public static List<Item> getSmallItems() {
    String key = "small";

    if (!TYPES.containsKey(key)) {
      List<Item> l = new ArrayList<>();

      for (ResourceLocation r : Item.REGISTRY.getKeys()) {
        Item i = Item.REGISTRY.getObject(r);

        if (i != McfrItems.COIN && i != McfrItems.TOKEN && i != McfrItems.CLAW_MONEY)
          l.add(i);
      }

      l.removeAll(getPaperBasedItems());
      l.removeAll(getLargeObjects());
      l.removeAll(getFood());
      l.removeAll(getArmorPieces());
      l.removeAll(getWeapons());
      l.removeAll(getTools());

      TYPES.put(key, Collections.unmodifiableList(l));
    }

    return TYPES.get(key);
  }

  /**
   * @return une liste non modifiable des objets en papier
   */
  public static List<Item> getPaperBasedItems() {
    String key = "paper";

    if (!TYPES.containsKey(key)) {
      List<Item> l = new ArrayList<>();

      l.add(Items.PAPER);
      l.add(Items.BOOK);
      l.add(Items.WRITABLE_BOOK);
      l.add(Items.WRITTEN_BOOK);
      l.add(Items.ENCHANTED_BOOK);
      l.add(Items.MAP);
      l.add(Items.FILLED_MAP);
      l.add(McfrItems.WRITEABLE_PAPER);
      l.add(McfrItems.SIGNED_PAPER);

      TYPES.put(key, Collections.unmodifiableList(l));
    }

    return TYPES.get(key);
  }

  /**
   * Initialise les items acceptés par la caissette.
   */
  public static void initLittleChestItems() {
    Class<BlockLittleChest> key = BlockLittleChest.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<>();

      auth.add(McfrItems.COIN);

      LISTS.put(key, Collections.unmodifiableList(auth));
    }
  }

  /**
   * Initialise les items acceptés par la caisse.
   */
  public static void initCrateItems() {
    Class<BlockCrate> key = BlockCrate.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<>();

      auth.add(McfrItems.COIN);
      auth.addAll(getTools());
      auth.addAll(getArmorPieces());
      auth.addAll(getWeapons());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }
  }

  /**
   * Initialise les items acceptés par le fût de nourriture.
   */
  public static void initFoodCrateItems() {
    Class<BlockFoodCrate> key = BlockFoodCrate.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<>();

      auth.addAll(getFood());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }
  }

  /**
   * Initialise les items acceptés par la palette.
   */
  public static void initPalletItems() {
    Class<BlockPallet> key = BlockPallet.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<>();

      auth.addAll(getLargeObjects());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }
  }

  /**
   * Initialise les items acceptés par la bibliothèque.
   */
  public static void initBookshelfItems() {
    Class<BlockBookshelf> key = BlockBookshelf.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<>();

      auth.addAll(getPaperBasedItems());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }
  }

  /**
   * Initialise les items acceptés par le métier à tisser.
   */
  public static void initLoomItems() {
    Class<BlockLoom> key = BlockLoom.class;

    if (!MAPS.containsKey(key)) {
      Map<HashedItemStack, HashedItemStack> auth = new HashMap<>();

      auth.put(HashedItemStack.fromStack(new ItemStack(McfrItems.HEMP_FIBER, 3)), HashedItemStack.fromStack(new ItemStack(McfrItems.CLOTH_ROLL)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Items.STRING, 4)), HashedItemStack.fromStack(new ItemStack(McfrItems.THREAD_COIL)));
      auth.put(HashedItemStack.fromStack(new ItemStack(McfrItems.THREAD_COIL, 2)), HashedItemStack.fromStack(new ItemStack(Blocks.WOOL)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.WOOL)), HashedItemStack.fromStack(new ItemStack(McfrBlocks.CARPET, 3)));

      MAPS.put(key, Collections.unmodifiableMap(auth));
      createList(key);
    }
  }

  /**
   * Initialise les items acceptés par l'atelier de tannage.
   */
  public static void initTanningRackItems() {
    Class<BlockTanningRack> key = BlockTanningRack.class;

    if (!MAPS.containsKey(key)) {
      Map<HashedItemStack, HashedItemStack> auth = new HashMap<>();

      auth.put(HashedItemStack.fromStack(new ItemStack(McfrItems.HUNTED_SKIN)), HashedItemStack.fromStack(new ItemStack(Items.LEATHER)));

      MAPS.put(key, Collections.unmodifiableMap(auth));
      createList(key);
    }
  }

  /**
   * Initialise les items acceptés par la scie circulaire.
   */
  public static void initCircularSawItems() {
    Class<BlockCircularSaw> key = BlockCircularSaw.class;

    if (!MAPS.containsKey(key)) {
      Map<HashedItemStack, HashedItemStack> auth = new HashMap<>();

      for (int i = 0; i < BlockPlanks.EnumType.values().length; i++) {
        auth.put(HashedItemStack.fromStack(new ItemStack(i < 4 ? Blocks.LOG : Blocks.LOG2, 1, i % 4)),
            HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 4, i)));
        auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 1, i)),
            HashedItemStack.fromStack(new ItemStack(McfrBlocks.REFINED_PLANKS, 1, i)));
        auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.WOODEN_SLAB, 2, i)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 1, i)));
      }
      for (int i = 0; i < EnumExoticWoodType.values().length; i++)
        auth.put(HashedItemStack.fromStack(new ItemStack(McfrBlocks.EXOTIC_LOG, 1, i)),
            HashedItemStack.fromStack(new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, i)));
      auth.put(HashedItemStack.fromStack(new ItemStack(McfrBlocks.STRONG_OAK_DOOR)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 2)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.OAK_STAIRS)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 1, 0)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.SPRUCE_STAIRS)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 1, 1)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.BIRCH_STAIRS)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 1, 2)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.JUNGLE_STAIRS)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 1, 3)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.ACACIA_STAIRS)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 1, 4)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.DARK_OAK_STAIRS)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 1, 5)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.TRAPDOOR)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.WOODEN_BUTTON)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.CHEST)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.CRAFTING_TABLE)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS)));

      MAPS.put(key, Collections.unmodifiableMap(auth));
      createList(key);
    }
  }

  /**
   * Initialise les items acceptés par le mortier.
   */
  public static void initMortarItems() {
    Class<BlockMortar> key = BlockMortar.class;

    if (!MAPS.containsKey(key)) {
      Map<HashedItemStack, HashedItemStack> auth = new HashMap<>();

      auth.put(HashedItemStack.fromStack(new ItemStack(Items.WHEAT_SEEDS, 2)), HashedItemStack.fromStack(new ItemStack(McfrItems.FLOUR)));
      auth.put(HashedItemStack.fromStack(new ItemStack(McfrItems.HEMP_LEAF, 2)), HashedItemStack.fromStack(new ItemStack(McfrItems.HEMP_OIL)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Items.COAL, 2, 1)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 0)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Items.BONE, 2)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 15)));
      auth.put(HashedItemStack.fromStack(new ItemStack(McfrItems.COCOA, 2)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 1, 3)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.YELLOW_FLOWER, 2)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 11)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.RED_FLOWER, 2, 0)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 1)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.RED_FLOWER, 2, 4)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 1)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.RED_FLOWER, 2, 2)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 5)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.RED_FLOWER, 2, 6)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 7)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.RED_FLOWER, 2, 5)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 14)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.RED_FLOWER, 2, 7)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 9)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.TALLGRASS, 2, 1)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 2)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.TALLGRASS, 2, 2)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 2)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.DOUBLE_PLANT, 2, 2)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 2)));
      auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.DOUBLE_PLANT, 2, 3)), HashedItemStack.fromStack(new ItemStack(Items.DYE, 8, 2)));

      MAPS.put(key, Collections.unmodifiableMap(auth));
      createList(key);
    }
  }

  /**
   * Crée une liste pour une classe donnée à partir de MAPS.
   * 
   * @param clazz la classe du bloc
   */
  private static void createList(Class<? extends Block> clazz) {
    List<Item> items = new ArrayList<>();
    MAPS.get(clazz).keySet().stream().forEach(stack -> items.add(stack.getStack().getItem()));
    LISTS.put(clazz, items);
  }

  /**
   * Indique si le bloc spécifié accepte le stack donné.
   *
   * @param blockClass la classe du bloc
   * @param stack le stack
   * @return vrai si le stack est accepté
   * @note Cette méthode est prévue pour être utilisée par les classes {@link ContainerRestricted}
   *       et {@link ContainerRack}.
   */
  public static boolean isItemValid(Class<? extends Block> blockClass, ItemStack stack) {
    List<Item> list = LISTS.get(blockClass);

    return list != null && (stack == null || list.contains(stack.getItem()));
  }

  /**
   * Retourne la table associative pour une classe donnée.
   * 
   * @param clazz la classe du bloc
   * @return la table associative
   */
  public static Map<HashedItemStack, HashedItemStack> getMapForClass(Class<? extends Block> clazz) {
    return MAPS.get(clazz);
  }

  private ItemsLists() {}
}
