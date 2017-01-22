package net.mcfr.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.minecraft.block.Block;
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
  private static final Map<String, List<Item>> TYPES = new HashMap<String, List<Item>>();
  private static final Map<Class<? extends Block>, List<Item>> LISTS = new HashMap<Class<? extends Block>, List<Item>>();

  /**
   * @return une liste non modifiable de tous les outils
   */
  public static List<Item> getTools() {
    String key = "tools";

    if (!TYPES.containsKey(key)) {
      List<Item> l = new ArrayList<Item>();

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
      List<Item> l = new ArrayList<Item>();

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
      List<Item> l = new ArrayList<Item>();

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
      List<Item> l = new ArrayList<Item>();

      for (ResourceLocation r : Item.REGISTRY.getKeys()) {
        Item i = Item.REGISTRY.getObject(r);

        if (i instanceof ItemFood && i != Items.FERMENTED_SPIDER_EYE || i instanceof ItemBow) {
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
      List<Item> l = new ArrayList<Item>();

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
      List<Item> l = new ArrayList<Item>();

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
      List<Item> l = new ArrayList<Item>();

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

  public static List<Item> getLittleChestItems() {
    Class<BlockLittleChest> key = BlockLittleChest.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<Item>();

      auth.add(McfrItems.COIN);

      LISTS.put(key, Collections.unmodifiableList(auth));
    }

    return LISTS.get(key);
  }

  public static List<Item> getCrateItems() {
    Class<BlockCrate> key = BlockCrate.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<Item>();

      auth.add(McfrItems.COIN);
      auth.addAll(getTools());
      auth.addAll(getArmorPieces());
      auth.addAll(getWeapons());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }

    return LISTS.get(key);
  }

  public static List<Item> getFoodCrateItems() {
    Class<BlockFoodCrate> key = BlockFoodCrate.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<Item>();

      auth.addAll(getFood());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }

    return LISTS.get(key);
  }

  public static List<Item> getPalletItems() {
    Class<BlockPallet> key = BlockPallet.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<Item>();

      auth.addAll(getLargeObjects());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }

    return LISTS.get(key);
  }

  public static List<Item> getBookshelfItems() {
    Class<BlockBookshelf> key = BlockBookshelf.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<Item>();

      auth.addAll(getPaperBasedItems());

      LISTS.put(key, Collections.unmodifiableList(auth));
    }

    return LISTS.get(key);
  }

  public static List<Item> getLoomItems() {
    Class<BlockLoom> key = BlockLoom.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<Item>();

      auth.add(Items.STRING);

      LISTS.put(key, Collections.unmodifiableList(auth));
    }

    return LISTS.get(key);
  }

  public static List<Item> getTanningRackItems() {
    Class<BlockTanningRack> key = BlockTanningRack.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<Item>();

      // TODO

      LISTS.put(key, Collections.unmodifiableList(auth));
    }

    return LISTS.get(key);
  }

  public static List<Item> getCircularSawItems() {
    Class<BlockCircularSaw> key = BlockCircularSaw.class;

    if (!LISTS.containsKey(key)) {
      List<Item> auth = new ArrayList<Item>();

      auth.add(Item.getItemFromBlock(Blocks.PLANKS));

      LISTS.put(key, Collections.unmodifiableList(auth));
    }

    return LISTS.get(key);
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

  private ItemsLists() {}
}
