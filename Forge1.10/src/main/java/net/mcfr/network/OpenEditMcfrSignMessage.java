package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.decoration.signs.guis.GuiEditMcfrSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityMcfrSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityNormalSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityOrpSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityPaperSign;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Paquet commandant l'ouverture de l'interface d'édition des panneaux chez le client.
 *
 * @author Mc-Fr
 */
public class OpenEditMcfrSignMessage implements IMessage {
  private BlockPos signPos;
  private SignType signType;

  /**
   * Crée un paquet. Constructeur equis par Forge.
   */
  public OpenEditMcfrSignMessage() {
    this(null, null);
  }

  /**
   * Crée un paquet.
   * 
   * @param signPos la position du panneau
   * @param signType le type de panneau
   */
  public OpenEditMcfrSignMessage(BlockPos signPos, SignType signType) {
    this.signPos = signPos;
    this.signType = signType;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.signPos.getX());
    buf.writeInt(this.signPos.getY());
    buf.writeInt(this.signPos.getZ());
    buf.writeInt(this.signType.ordinal());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    int x = buf.readInt();
    int y = buf.readInt();
    int z = buf.readInt();
    this.signPos = new BlockPos(x, y, z);
    this.signType = SignType.fromOrdinal(buf.readInt());
  }

  /**
   * @return la position du panneau
   */
  public BlockPos getSignPos() {
    return this.signPos;
  }

  /**
   * @return le type de panneau
   */
  public SignType getSignType() {
    return this.signType;
  }

  /**
   * Types de panneaux :
   * <ul>
   * <li>normal</li>
   * <li>en papier</li>
   * <li>HRP</li>
   * </ul>
   *
   * @author Mc-Fr
   */
  public static enum SignType {
    NORMAL,
    PAPER,
    ORP;

    public static SignType fromOrdinal(int ordinal) {
      return values()[ordinal % values().length];
    }

    /**
     * Retourne le type de panneau en fonction de la classe passée.
     * 
     * @param clazz la classe
     * @return le type de panneau
     */
    public static SignType fromClass(Class<? extends TileEntityMcfrSign> clazz) {
      if (clazz == TileEntityNormalSign.class)
        return NORMAL;
      if (clazz == TileEntityPaperSign.class)
        return PAPER;
      if (clazz == TileEntityOrpSign.class)
        return ORP;
      return null;
    }
  }

  /**
   * Gestionnaire côté client.
   *
   * @author Mc-Fr
   */
  public static class ClientHandler implements IMessageHandler<OpenEditMcfrSignMessage, IMessage> {
    @Override
    public IMessage onMessage(final OpenEditMcfrSignMessage message, MessageContext ctx) {
      Minecraft.getMinecraft().addScheduledTask(() -> {
        BlockPos pos = message.getSignPos();
        World world = NetworkUtils.getLocalWorld();
        TileEntity te = world.getTileEntity(pos);

        if (!(te instanceof TileEntityMcfrSign)) {
          switch (message.getSignType()) {
            case NORMAL:
              te = new TileEntityNormalSign();
              break;
            case PAPER:
              te = new TileEntityPaperSign();
              break;
            case ORP:
              te = new TileEntityOrpSign();
              break;
          }
          te.setWorldObj(world);
          te.setPos(message.getSignPos());
        }

        openGui((TileEntityMcfrSign) te);
      });

      return null;
    }

    /**
     * Ouvre l'interface d'édition. L'appel doit se faire dans une fonction pour éviter que
     * l'instruction se retrouve sur le serveur et provoque une erreur.
     * 
     * @param te le panneau
     */
    @SideOnly(Side.CLIENT)
    public void openGui(TileEntityMcfrSign te) {
      Minecraft.getMinecraft().displayGuiScreen(new GuiEditMcfrSign(te));
    }
  }
}