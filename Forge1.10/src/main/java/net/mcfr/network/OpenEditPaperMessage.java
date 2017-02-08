package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.guis.GuiScreenPaper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OpenEditPaperMessage implements IMessage {
  private EnumHand hand;
  private boolean isUnsigned;

  // Requis par Forge.
  public OpenEditPaperMessage() {
    this(null, true);
  }

  public OpenEditPaperMessage(EnumHand hand, boolean isUnsigned) {
    this.hand = hand;
    this.isUnsigned = isUnsigned;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(getHand().ordinal());
    buf.writeBoolean(isPaperUnsigned());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.hand = EnumHand.values()[buf.readInt()];
    this.isUnsigned = buf.readBoolean();
  }

  public EnumHand getHand() {
    return this.hand;
  }

  public boolean isPaperUnsigned() {
    return this.isUnsigned;
  }

  public static class ClientHandler implements IMessageHandler<OpenEditPaperMessage, IMessage> {
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
}
