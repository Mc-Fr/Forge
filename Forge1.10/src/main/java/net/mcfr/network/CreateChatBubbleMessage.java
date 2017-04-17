package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.entities.ChatBubbleType;
import net.mcfr.entities.EntityChatBubble;
import net.mcfr.guis.chat_bubble.ChatBubble;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CreateChatBubbleMessage implements IMessage {
  private ChatBubbleType type;

  public CreateChatBubbleMessage() {
    this(ChatBubbleType.TALKING);
  }

  public CreateChatBubbleMessage(ChatBubbleType type) {
    this.type = type;
  }

  public ChatBubbleType getType() {
    return this.type;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.type.ordinal());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.type = ChatBubbleType.byOrdinal(buf.readInt());
  }

  public static class ServerHandler implements IMessageHandler<CreateChatBubbleMessage, IMessage> {
    @Override
    public IMessage onMessage(final CreateChatBubbleMessage message, MessageContext ctx) {
      EntityPlayerMP player = ctx.getServerHandler().playerEntity;

      player.getServerWorld().addScheduledTask(() -> {
        World world = player.worldObj;
        Vec3d pos = player.getPositionVector();
        EntityChatBubble chatBubble = new EntityChatBubble(message.getType(), world, pos.xCoord, pos.yCoord + player.eyeHeight + 0.5, pos.zCoord);
        int id = chatBubble.getEntityId();

        if (world.spawnEntityInWorld(chatBubble)) {
          /*
           * Lorsque le bulle chevauche le joueur, un plugin la repositionne dans la tête du joueur.
           * C'est ce qui provoque le tressautement lors de la mise à jour de la position.
           */
          // FIXME
          // chatBubble.startRiding(player, true);
          ChatBubble.PLAYER_BUBBLE.put(player.getUniqueID(), id);
        }
      });

      return null;
    }
  }
}
