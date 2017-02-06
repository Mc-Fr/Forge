package net.mcfr.guis.chat_bubble;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.lwjgl.opengl.GL11;

import net.mcfr.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ChatBubble {
  /** Distance de rendu maximale de la bulle de tchat. */
  public static final int MAX_DISTANCE_SQ = 64;
  /** Liste des joueurs au-dessus desquels il faut afficher une bulle. */
  private static final Set<DummyPlayer> PLAYERS = new HashSet<>();

  public static void addPlayer(DummyPlayer player) {
    PLAYERS.add(player);
  }

  public static void removePlayer(UUID uuid) {
    PLAYERS.removeIf(player -> player.getUniqueId().equals(uuid));
  }

  public static void render() {
    // @f0
    PLAYERS.stream()
      .filter(player -> Minecraft.getMinecraft().thePlayer.getDistanceSq(player.getX(), player.getY(), player.getZ()) <= MAX_DISTANCE_SQ)
      .forEach(player -> renderAt(player.getX() + 0.5, player.getY() + 2.3, player.getZ() + 0.5));
    // @f1
  }

  private static void renderAt(double x, double y, double z) {
    RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);
    GlStateManager.rotate(-renderManager.playerViewY, 0, 1, 0);
    GlStateManager.rotate(renderManager.playerViewX, 1, 0, 0);
    GlStateManager.color(1, 1, 1, 1);
    GL11.glDisable(GL11.GL_LIGHTING);

    RenderUtils.renderItem(new ItemStack(Items.PAPER));
    GlStateManager.translate(0.1, 0.1, -0.05);
    RenderUtils.renderItem(new ItemStack(Items.FEATHER));

    GL11.glEnable(GL11.GL_LIGHTING);
    GlStateManager.popMatrix();
  }
}
