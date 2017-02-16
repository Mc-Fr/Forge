package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.entities.EntityChatBubble;
import net.mcfr.guis.chat_bubble.ChatBubble;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CreateChatBubbleMessage implements IMessage {
  @Override
  public void toBytes(ByteBuf buf) {}

  @Override
  public void fromBytes(ByteBuf buf) {}

  public static class ServerHandler implements IMessageHandler<CreateChatBubbleMessage, IMessage> {
    @Override
    public IMessage onMessage(final CreateChatBubbleMessage message, MessageContext ctx) {
      EntityPlayerMP player = ctx.getServerHandler().playerEntity;

      player.getServerWorld().addScheduledTask(() -> {
        World world = player.worldObj;
        System.out.println(world);
        Vec3d pos = player.getPositionVector();
        EntityChatBubble chatBubble = new EntityChatBubble(world, pos.xCoord, pos.yCoord + 2.3, pos.zCoord);
        int id = chatBubble.getEntityId();

        if (world.spawnEntityInWorld(chatBubble))
          ChatBubble.PLAYER_BUBBLE.put(ctx.getServerHandler().playerEntity.getUniqueID(), id);
      });

      return null;
    }
  }
}
