package net.mcfr.event;

import net.mcfr.guis.GuiMcfrChat;
import net.mcfr.guis.GuiMcfrIngameMenu;
import net.mcfr.guis.GuiMcfrMainMenu;
import net.mcfr.guis.chat_bubble.ChatBubble;
import net.minecraft.block.BlockSign;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Cette classe regroupe tous les écouteurs d'évènements associés au joueur.
 *
 * @author Mc-Fr
 */
public class PlayerEventsHandler {
  @SideOnly(Side.SERVER)
  // @SubscribeEvent
  public void onWorldTickEvent(TickEvent.WorldTickEvent e) {
    ChatBubble.PLAYER_BUBBLE.entrySet().forEach(data -> {
      System.out.println(e.world);
      System.out.println(e.world.getPlayerEntityByUUID(data.getKey()));
      Vec3d pos = e.world.getPlayerEntityByUUID(data.getKey()).getPositionVector();
      e.world.getEntityByID(data.getValue()).setPosition(pos.xCoord, pos.yCoord + 2.3, pos.zCoord);
    });
  }

  /**
   * Cet écouteur remplace l'écran-titre et le menu en jeu par ceux du mod.
   * 
   * @param e l'évènement
   */
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onOpenGui(GuiOpenEvent e) {
    GuiScreen gui = e.getGui();

    if (gui instanceof GuiMainMenu)
      e.setGui(new GuiMcfrMainMenu());
    if (gui instanceof GuiIngameMenu)
      e.setGui(new GuiMcfrIngameMenu());
    if (gui instanceof GuiChat && !(gui instanceof GuiSleepMP))
      e.setGui(new GuiMcfrChat());
  }

  /**
   * Cet écouteur empêche le joueur de boire les seaux de lait et de poser les panneaux de base.
   *
   * @param e l'évènement
   */
  @SubscribeEvent
  public void onPlayerInteract(PlayerInteractEvent.RightClickItem e) {
    ItemStack stack = e.getItemStack();

    if (stack != null) {
      if (stack.getItem() == Items.MILK_BUCKET) {
        e.setCanceled(true);
      }
      if (stack.getItem() == Items.SIGN) {
        e.setCanceled(true);
      }
      if (stack.getItem() == Items.GLASS_BOTTLE && e.getWorld().getBlockState(e.getPos()).getBlock() == Blocks.CAULDRON) {
        // TODO : donner une fiole en fonction du contenu du chaudron
      }
    }
  }

  /**
   * Cet écouteur empêche le joueur de poser le bloc des panneaux de base.
   *
   * @param e l'évènement
   */
  @SubscribeEvent
  public void onBlockPlaced(PlaceEvent e) {
    if (e.getPlacedBlock().getBlock() instanceof BlockSign) {
      e.setCanceled(true);
    }
  }
}
