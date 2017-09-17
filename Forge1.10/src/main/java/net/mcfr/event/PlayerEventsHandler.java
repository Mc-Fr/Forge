package net.mcfr.event;

import java.lang.reflect.Field;

import net.mcfr.Constants;
import net.mcfr.capabilities.IPrevFood;
import net.mcfr.capabilities.PrevFoodProvider;
import net.mcfr.guis.GuiMcfrChat;
import net.mcfr.guis.GuiMcfrIngameMenu;
import net.mcfr.guis.GuiMcfrMainMenu;
import net.minecraft.block.BlockSign;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Cette classe regroupe tous les écouteurs d'évènements associés au joueur.
 *
 * @author Mc-Fr
 */
public class PlayerEventsHandler {
  public static final ResourceLocation PREV_FOOD_CAP = new ResourceLocation(Constants.MOD_ID, "prevFood");
  
  private final float SATURATION_SPEED_MULTIPLIER = 0.1f;
  private final float SATURATION_REGENERATION_PROPORTION = 0.5f;
  
  @SubscribeEvent
  public void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
    if (event.getObject() instanceof EntityPlayer)
      event.addCapability(PREV_FOOD_CAP, new PrevFoodProvider());
  }
  
  @SubscribeEvent
  public void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntity() instanceof EntityPlayer && !event.getEntity().getEntityWorld().isRemote) {
      EntityPlayer player = (EntityPlayer) event.getEntity();
      IPrevFood cap = player.getCapability(PrevFoodProvider.PREV_FOOD_CAP, null);
      FoodStats stats = player.getFoodStats();
      
      int food = stats.getFoodLevel();
      int prevFood = cap.getFood();
      float saturation = stats.getSaturationLevel();
      float prevSaturation = cap.getSaturation();
      
      if (food < prevFood) {
        stats.addStats(food, 0.5f * SATURATION_REGENERATION_PROPORTION);
        stats.setFoodLevel(food);
      } else if (saturation < prevSaturation && saturation > 3f) {
        stats.addStats(1, 0.5f - 0.5f * SATURATION_SPEED_MULTIPLIER);
        stats.setFoodLevel(food);
      }

      cap.set(stats.getFoodLevel(), stats.getSaturationLevel());
    }
  }
  
  @SubscribeEvent
  public void onClientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent e) {
    System.out.println("connection");
    // McfrNetworkWrapper.getInstance().sendToServer(new ModVersionCheckMessage(Constants.MOD_ID));
  }

  /**
   * Cet écouteur remplace l'écran-titre, le menu en jeu et le tchat par ceux du mod.
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
    if (gui instanceof GuiChat) {
      GuiChat g = (GuiChat) gui;
      String s = "";

      try {
        Class<?> clazz = g.getClass();
        Field f = null;

        try {
          f = clazz.getDeclaredField("field_146409_v");
        }
        catch (NoSuchFieldException | SecurityException ex1) {
          try {
            f = clazz.getDeclaredField("defaultInputFieldText");
          }
          catch (NoSuchFieldException | SecurityException ex) {}
        }
        if (f != null) {
          f.setAccessible(true);
          s = (String) f.get(gui);
        }
      }
      catch (IllegalArgumentException | IllegalAccessException ex) {}

      e.setGui(new GuiMcfrChat(s));
    }
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
