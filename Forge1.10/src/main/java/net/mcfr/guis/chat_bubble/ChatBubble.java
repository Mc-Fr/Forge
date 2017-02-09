package net.mcfr.guis.chat_bubble;

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
  /** L'identifiant de l'entité associée au joueur local */
  public static int currentEntityId = -1;

  public static void renderAt(double x, double y, double z) {
    RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);
    GlStateManager.rotate(-renderManager.playerViewY, 0, 1, 0);
    GlStateManager.rotate(renderManager.playerViewX, 1, 0, 0);
    GlStateManager.color(1, 1, 1, 1);

    RenderUtils.renderItem(new ItemStack(Items.PAPER));
    GlStateManager.translate(0.1, 0.1, -0.05);
    RenderUtils.renderItem(new ItemStack(Items.FEATHER));

    GlStateManager.popMatrix();
  }
}
