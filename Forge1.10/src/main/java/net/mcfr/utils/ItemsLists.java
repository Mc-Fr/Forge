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
import net.mcfr.craftsmanship.BlockTanningRack;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.mcfr.decoration.containerBlocks.BlockBookshelf;
import net.mcfr.decoration.containerBlocks.BlockCrate;
import net.mcfr.decoration.containerBlocks.BlockFoodCrate;
import net.mcfr.decoration.containerBlocks.BlockLittleChest;
import net.mcfr.decoration.containerBlocks.BlockPallet;
import net.mcfr.decoration.containerBlocks.guis.ContainerRestricted;
import net.mcfr.environment.plants.EnumExoticWoodType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;

public final class ItemsLists {
  private static final Map<String, List<Item>> TYPES = new HashMap<>();
  private static final Map<Class<? extends Block>, List<Item>> LISTS = new HashMap<>();
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

        if (i instanceof ItemTool || i instanceof ItemHoe || i instanceof ItemShears || i instanceof ItemFishingRod || i instanceof ItemFlintAndSteel) {
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

  public static List<Item> getLargeObjects() {
    String key = "large";

    if (!TYPES.containsKey(key)) {
      List<Item> l = new ArrayList<>();

      for (ResourceLocation r : Item.REGISTRY.getKeys()) {
        Item i = Item.REGISTRY.getObject(r);

        l.add(i); // TEMP
        // TODO large objects
        // if (i instanceof ItemFood && i != Items.fermented_spider_eye || i instanceof ItemBow) {
        // l.add(i);
        // }
      }

      TYPES.put(key, Collections.unmodifiableList(l));
    }

    return TYPES.get(key);
  }

  public static List<Item> getSmallItems() {
    String key = "small";

    if (!TYPES.containsKey(key)) {
      List<Item> l = new ArrayList<>();

      for (ResourceLocation r : Item.REGISTRY.getKeys()) {
        @SuppressWarnings("unused")
        Item i = Item.REGISTRY.getObject(r);

        // TODO small items
        // if (i instanceof ItemFood && i != Items.fermented_spider_eye || i instanceof ItemBow) {
        // l.add(i);
        // }
      }

      TYPES.put(key, Collections.unmodifiableList(l));
    }

    return TYPES.get(key);
  }

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

      TYPES.put(key, Collections.unmodifiableList(l));
    }

    return TYPES.get(key);
  }

  public static void initLittleChestItems() {
    Class<BlockLittleChest> key = BlockLittleChest.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<>();

      auth.add(McfrItems.COIN);

      LISTS.put(key, Collections.unmodifiableList(auth));
    }
  }

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

  public static void initFoodCrateItems() {
    Class<BlockFoodCrate> key = BlockFoodCrate.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<>();

      auth.addAll(getFood());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }
  }

  public static void initPalletItems() {
    Class<BlockPallet> key = BlockPallet.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<>();

      auth.addAll(getLargeObjects());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }
  }

  public static void initBookshelfItems() {
    Class<BlockBookshelf> key = BlockBookshelf.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<>();

      auth.addAll(getPaperBasedItems());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }
  }

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

  public static void initTanningRackItems() {
    Class<BlockTanningRack> key = BlockTanningRack.class;

    if (!MAPS.containsKey(key)) {
      Map<HashedItemStack, HashedItemStack> auth = new HashMap<>();

      auth.put(HashedItemStack.fromStack(new ItemStack(McfrItems.HUNTED_SKIN)), HashedItemStack.fromStack(new ItemStack(Items.LEATHER)));

      MAPS.put(key, Collections.unmodifiableMap(auth));
      createList(key);
    }
  }

  public static void initCircularSawItems() {
    Class<BlockCircularSaw> key = BlockCircularSaw.class;

    if (!MAPS.containsKey(key)) {
      Map<HashedItemStack, HashedItemStack> auth = new HashMap<>();

      for (int i = 0; i < BlockPlanks.EnumType.values().length; i++) {
        auth.put(HashedItemStack.fromStack(new ItemStack(i < 4 ? Blocks.LOG : Blocks.LOG2, 1, i % 4)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 4, i)));
        auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 1, i)), HashedItemStack.fromStack(new ItemStack(McfrBlocks.REFINED_PLANKS, 1, i)));
        auth.put(HashedItemStack.fromStack(new ItemStack(Blocks.WOODEN_SLAB, 2, i)), HashedItemStack.fromStack(new ItemStack(Blocks.PLANKS, 1, i)));
      }
      for (int i = 0; i < EnumExoticWoodType.values().length; i++)
        auth.put(HashedItemStack.fromStack(new ItemStack(McfrBlocks.EXOTIC_LOG, 1, i)), HashedItemStack.fromStack(new ItemStack(McfrBlocks.EXOTIC_PLANKS, 1, i)));
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

  public static Map<HashedItemStack, HashedItemStack> getMapForClass(Class<? extends Block> clazz) {
    return MAPS.get(clazz);
  }

  private ItemsLists() {}
}
