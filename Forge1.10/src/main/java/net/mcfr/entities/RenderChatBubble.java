package net.mcfr.entities;

import net.mcfr.guis.chat_bubble.ChatBubble;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderChatBubble extends Render<EntityChatBubble> {
  public RenderChatBubble(RenderManager renderManager) {
    super(renderManager);
  }

  @Override
  public void doRender(EntityChatBubble entity, double x, double y, double z, float entityYaw, float partialTicks) {
    if (!Minecraft.getMinecraft().gameSettings.hideGUI)
      ChatBubble.render(entity.getType(), x, y, z);
  }

  @Override
  protected ResourceLocation getEntityTexture(EntityChatBubble entity) {
    return null;
  }
}
