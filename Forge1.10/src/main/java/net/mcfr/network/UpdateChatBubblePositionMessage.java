package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Paquet commandant la mise à jour de la position des bulles de tchat chez les clients.
 *
 * @author Mc-Fr
 */
public class UpdateChatBubblePositionMessage implements IMessage {
  private double x, y, z;
  private int entityId;

  /**
   * Crée un paquet. Constructeur equis par Forge.
   */
  public UpdateChatBubblePositionMessage() {
    this(0, 0, 0, 0);
  }

  /**
   * Crée un paquet.
   * 
   * @param x position x
   * @param y position y
   * @param z position z
   * @param entityId l'ID de l'entité
   */
  public UpdateChatBubblePositionMessage(double x, double y, double z, int entityId) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.entityId = entityId;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readDouble();
    this.y = buf.readDouble();
    this.z = buf.readDouble();
    this.entityId = buf.readInt();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeDouble(getX());
    buf.writeDouble(getY());
    buf.writeDouble(getZ());
    buf.writeInt(getEntityId());
  }

  /**
   * @return la position x
   */
  public double getX() {
    return this.x;
  }

  /**
   * @return la position y
   */
  public double getY() {
    return this.y;
  }

  /**
   * @return la position z
   */
  public double getZ() {
    return this.z;
  }

  /**
   * @return l'ID de l'entité
   */
  public int getEntityId() {
    return this.entityId;
  }

  /**
   * Gestionnaire côté client.
   *
   * @author Mc-Fr
   */
  public static class ClientHandler implements IMessageHandler<UpdateChatBubblePositionMessage, IMessage> {
    @Override
    public IMessage onMessage(UpdateChatBubblePositionMessage message, MessageContext ctx) {
      Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(message.getEntityId());

      if (entity != null) {
        entity.setPosition(message.getX(), message.getY(), message.getZ());
      }

      return null;
    }
  }
}
