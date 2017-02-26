package net.mcfr.guis.chat_bubble;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.mcfr.McfrItems;
import net.mcfr.entities.ChatBubbleType;
import net.mcfr.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChatBubble {
  /** Associe un joueur à une bulle de tchat. */
  public static final Map<UUID, Integer> PLAYER_BUBBLE = new HashMap<>();

  /**
   * Affiche une bulle de tchat à l'endroit donné.
   * 
   * @param type le type (sauf {@link ChatBubbleType#NONE})
   */
  @SideOnly(Side.CLIENT)
  public static void render(ChatBubbleType type, double x, double y, double z) {
    RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);
    GlStateManager.rotate(-renderManager.playerViewY, 0, 1, 0);
    GlStateManager.rotate(renderManager.playerViewX, 1, 0, 0);
    GlStateManager.color(1, 1, 1, 1);

    switch (type) {
      case TALKING:
        RenderUtils.renderItem(new ItemStack(Items.PAPER));
        GlStateManager.translate(0.1, 0.1, -0.05);
        RenderUtils.renderItem(new ItemStack(Items.FEATHER));
        break;
      case COMMAND:
        RenderUtils.renderItem(new ItemStack(Items.COMPASS));
        break;
      case ACTION:
        RenderUtils.renderItem(new ItemStack(McfrItems.HAMMER));
        break;
      case ORP:
        // Remise à l'endroit
        GlStateManager.rotate(180, 0, 1, 0);
        RenderUtils.renderItem(new ItemStack(McfrItems.ORP_SIGN));
        break;
      case NONE:
        // No-op
        break;
    }

    GlStateManager.popMatrix();
  }
}
