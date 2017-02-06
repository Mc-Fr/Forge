package net.mcfr.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.mcfr.guis.chat_bubble.DummyPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class AddChatBubbleMessage implements IMessage {
  private DummyPlayer player;

  // Requis par Forge.
  public AddChatBubbleMessage() {
    this(null);
  }

  public AddChatBubbleMessage(DummyPlayer player) {
    this.player = player;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeDouble(this.player.getX());
    buf.writeDouble(this.player.getY());
    buf.writeDouble(this.player.getZ());
    buf.writeLong(this.player.getUniqueId().getMostSignificantBits());
    buf.writeLong(this.player.getUniqueId().getLeastSignificantBits());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    double x = buf.readDouble();
    double y = buf.readDouble();
    double z = buf.readDouble();
    UUID uuid = new UUID(buf.readLong(), buf.readLong());

    this.player = new DummyPlayer(x, y, z, uuid);
  }

  public DummyPlayer getPlayer() {
    return this.player;
  }
}
