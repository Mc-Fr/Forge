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

/**
 * Paquet commandant l'ouverture de l'interface d'édition du papier chez le client.
 * 
 * @author Mc-Fr
 */
public class OpenEditPaperMessage implements IMessage {
  private EnumHand hand;
  private boolean isUnsigned;

  /**
   * Crée un paquet. Constructeur equis par Forge.
   */
  public OpenEditPaperMessage() {
    this(null, true);
  }

  /**
   * Crée un paquet.
   * 
   * @param hand la main dans laquelle se trouve l'item
   * @param isUnsigned le papier est-il non signé ?
   */
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

  /**
   * @return la main dans laquelle se trouve l'item
   */
  public EnumHand getHand() {
    return this.hand;
  }

  /**
   * @return true si la papier n'est pas signé
   */
  public boolean isPaperUnsigned() {
    return this.isUnsigned;
  }

  /**
   * Gestionnaire côté client.
   *
   * @author Mc-Fr
   */
  public static class ClientHandler implements IMessageHandler<OpenEditPaperMessage, IMessage> {
    @Override
    public IMessage onMessage(final OpenEditPaperMessage message, MessageContext ctx) {
      Minecraft.getMinecraft().addScheduledTask(() -> {
        EntityPlayer player = NetworkUtils.getLocalPlayer();
        openGui(player, player.getHeldItem(message.getHand()), message.isPaperUnsigned());
      });

      return null;
    }

    /**
     * Ouvre l'interface d'édition. L'appel doit se faire dans une fonction pour éviter que
     * l'instruction se retrouve sur le serveur et provoque une erreur.
     * 
     * @param player le joueur
     * @param stack l'item
     * @param isUnsigned l'état de la signature
     */
    @SideOnly(Side.CLIENT)
    public void openGui(EntityPlayer player, ItemStack stack, boolean isUnsigned) {
      Minecraft.getMinecraft().displayGuiScreen(new GuiScreenPaper(player, stack, isUnsigned));
    }
  }
}
