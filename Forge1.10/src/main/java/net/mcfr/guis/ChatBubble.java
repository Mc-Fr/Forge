package net.mcfr.guis;

import org.lwjgl.opengl.GL11;

import net.mcfr.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ChatBubble {
  public static final int MAX_DISTANCE_SQ = 64;

  public static void renderAt(double x, double y, double z) {
    RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
    double distance = Minecraft.getMinecraft().thePlayer.getDistanceSq(x, y, z);

    if (distance <= MAX_DISTANCE_SQ) {
      GlStateManager.pushMatrix();
      GlStateManager.translate(x, y + 2.3, z);
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
}
