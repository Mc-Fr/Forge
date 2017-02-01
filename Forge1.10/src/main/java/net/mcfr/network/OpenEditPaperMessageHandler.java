package net.mcfr.network;

import net.mcfr.guis.GuiScreenPaper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OpenEditPaperMessageHandler implements IMessageHandler<OpenEditPaperMessage, IMessage> {
  @Override
  public IMessage onMessage(final OpenEditPaperMessage message, MessageContext ctx) {
    Minecraft.getMinecraft().addScheduledTask(() -> {
      EntityPlayer player = NetworkUtils.getLocalPlayer();
      openGui(player, player.getHeldItem(message.getHand()), message.isPaperUnsigned());
    });

    return null;
  }

  @SideOnly(Side.CLIENT)
  public void openGui(EntityPlayer player, ItemStack stack, boolean isUnsigned) {
    Minecraft.getMinecraft().displayGuiScreen(new GuiScreenPaper(player, stack, isUnsigned));
  }
}
