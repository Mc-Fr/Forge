package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.entities.EntityChatBubble;
import net.mcfr.guis.chat_bubble.ChatBubble;
import net.mcfr.guis.chat_bubble.DummyEntity;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NotifyChatBubbleMessage implements IMessage {
  private DummyEntity entity;
  private boolean addBubble;

  // Requis par Forge.
  public NotifyChatBubbleMessage() {
    this(false, null);
  }

  public NotifyChatBubbleMessage(boolean addBubble, DummyEntity entity) {
    this.addBubble = addBubble;
    this.entity = entity;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(isAddBubble());
    buf.writeDouble(getEntity().getX());
    buf.writeDouble(getEntity().getY());
    buf.writeDouble(getEntity().getZ());
    buf.writeInt(getEntity().getId());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.addBubble = buf.readBoolean();
    this.entity = new DummyEntity(buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readInt());
  }

  public boolean isAddBubble() {
    return this.addBubble;
  }

  public DummyEntity getEntity() {
    return this.entity;
  }

  public static class ClientHandler implements IMessageHandler<NotifyChatBubbleMessage, IMessage> {
    @Override
    public IMessage onMessage(final NotifyChatBubbleMessage message, MessageContext ctx) {
      ChatBubble.currentEntityId = message.getEntity().getId();
      return null;
    }
  }

  public static class ServerHandler implements IMessageHandler<NotifyChatBubbleMessage, IMessage> {
    @Override
    public IMessage onMessage(final NotifyChatBubbleMessage message, MessageContext ctx) {
      World world = ctx.getServerHandler().playerEntity.worldObj;
      DummyEntity dummyEntity = message.getEntity();
      double x = dummyEntity.getX();
      double y = dummyEntity.getY();
      double z = dummyEntity.getZ();

      if (message.isAddBubble()) {
        EntityChatBubble chatBubble = new EntityChatBubble(world, x, y, z);
        int id = chatBubble.getEntityId();

        world.spawnEntityInWorld(chatBubble);
        McfrNetworkWrapper.getInstance().sendTo(new NotifyChatBubbleMessage(true, new DummyEntity(x, y, z, id)), ctx.getServerHandler().playerEntity);
      }
      else {
        Entity e = world.getEntityByID(dummyEntity.getId());
        if (e != null)
          e.setDead();
      }

      return null;
    }
  }
}
