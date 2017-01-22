package net.mcfr.event;

import java.util.List;

import net.mcfr.McfrItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

/**
 * Cette classe permet regroupe les écouteurs d'évènement liés au joueur.
 *
 * @author Mc-Fr
 */
public class PlayerEventHandler {
  /**
   * Cet écouteur est notifié à chaque interaction d'un joueur avec l'environnement.
   *
   * @param e l'évènement
   */
  @SubscribeEvent
  public void onPlayerInteract(PlayerInteractEvent.RightClickItem e) {
    ItemStack stack = e.getItemStack();

    if (stack != null) {
      // Empêche le joueur de boire un seau de lait.
      if (stack.getItem() == Items.MILK_BUCKET) {
        e.setCanceled(true);
      }
      // Rend inactif l'item du panneau de base.
      // TEMP réactivation des panneaux de base.
      // if (stack.getItem() == Items.SIGN) {
      // e.setCanceled(true);
      // }
      if (stack.getItem() == Items.GLASS_BOTTLE && e.getWorld().getBlockState(e.getPos()).getBlock() == Blocks.CAULDRON) {
        // TODO : donner une fiole en fonction du contenu du chaudron
      }
    }
  }

  /**
   * Récupère l'évènement lorsqu'un bloc est placé dans le monde.
   *
   * @param e l'évènement
   */
  @SubscribeEvent
  public void onBlockPlaced(PlaceEvent e) {
    // Désactive complètement l'utilisation des panneaux de base.
    // TEMP réactivation des panneaux de base.
    // if (e.getPlacedBlock().getBlock() instanceof BlockSign) {
    // e.setCanceled(true);
    // }
  }

  /**
   * Cet écouteur sert à modifier les items donnés par certains blocs.<br/>
   * Modifications :
   * <ul>
   * <li>stone/1 -> stone/2 au lieu de stone/1</li>
   * <li>stone/3 -> stone/4 au lieu de stone/3</li>
   * <li>iron_ore -> mcfr_b_i:ore/0 au lieu de iron_ore</li>
   * <li>gold_ore -> mcfr_b_i:ore/1 au lieu de gold_ore</li>
   * <li>lapis_ore -> mcfr_b_i:ore/2 au lieu de dye/4</li>
   * <li>standing_sign -> mcfr_b_i:sign au lieu de minecraft:sign</li>
   * <li>wall_sign -> mcfr_b_i:sign au lieu de minecraft:sign</li>
   * </ul>
   *
   * @param e l'évènement
   */
  @SubscribeEvent
  public void onHarvestDrops(HarvestDropsEvent e) {
    Block block = e.getState().getBlock();

    // TODO supprimer les drops des cannes à sucre, bambou et roseaux.
    if (block == Blocks.STONE) {
      replaceStackIfPresent(e.getDrops(), ROUGH_GRANITE, SMOOTH_GRANITE);
      replaceStackIfPresent(e.getDrops(), ROUGH_DIORITE, SMOOTH_DIORITE);
    }
    else if (block == Blocks.IRON_ORE) {
      replaceStackIfPresent(e.getDrops(), IRON_ORE, new ItemStack(McfrItems.ORE));
    }
    else if (block == Blocks.GOLD_ORE) {
      replaceStackIfPresent(e.getDrops(), GOLD_ORE, new ItemStack(McfrItems.ORE, 1, 1));
    }
    else if (block == Blocks.LAPIS_ORE) {
      replaceStackIfPresent(e.getDrops(), BLUE_DYE, new ItemStack(McfrItems.ORE, 1, 2));
    }
    else if (block == Blocks.STANDING_SIGN || block == Blocks.WALL_SIGN) {
      replaceStackIfPresent(e.getDrops(), SIGN, new ItemStack(McfrItems.SIGN));
    }
  }
  
  @SubscribeEvent
  public void onPlayerLogsIn(PlayerLoggedInEvent event) {
    /*System.out.println("Un joueur s'est connecté !");
    if (!event.player.worldObj.isRemote) {
      System.out.println("Message envoyé au joueur récemment connecté.");
      List<EntitySyncedAnimal> list = event.player.worldObj.getEntities(EntitySyncedAnimal.class, e -> true);
      list.forEach(e -> e.syncToPlayer((EntityPlayerMP) event.player));
    }*/
  }
  
  /**
   * Remplace toutes les occurences du stack donné par un autre dans la liste spécifiée.
   *
   * @param list la liste
   * @param oldStack l'ancien stack
   * @param newStack le nouveau stack
   */
  @SuppressWarnings("deprecation")
  private static void replaceStackIfPresent(List<ItemStack> list, ItemStack oldStack, ItemStack newStack) {
    for (int i = 0; i < list.size(); i++) {
      ItemStack stack = list.get(i);

      if (stack.getItem() == oldStack.getItem() && stack.stackSize == oldStack.stackSize && stack.getMetadata() == oldStack.getMetadata()) {
        stack.setItem(newStack.getItem());
        stack.stackSize = newStack.stackSize;
        stack.setItemDamage(newStack.getMetadata());
      }
    }
  }

  private static final ItemStack ROUGH_GRANITE = new ItemStack(Blocks.STONE, 1, 1);
  private static final ItemStack SMOOTH_GRANITE = new ItemStack(Blocks.STONE, 1, 2);
  private static final ItemStack ROUGH_DIORITE = new ItemStack(Blocks.STONE, 1, 3);
  private static final ItemStack SMOOTH_DIORITE = new ItemStack(Blocks.STONE, 1, 4);
  private static final ItemStack GOLD_ORE = new ItemStack(Blocks.GOLD_ORE);
  private static final ItemStack IRON_ORE = new ItemStack(Blocks.IRON_ORE);
  private static final ItemStack BLUE_DYE = new ItemStack(Items.DYE, 1, 4);
  private static final ItemStack SIGN = new ItemStack(Items.SIGN);
}
