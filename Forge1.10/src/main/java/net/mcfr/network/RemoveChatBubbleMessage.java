package net.mcfr.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class RemoveChatBubbleMessage implements IMessage {
  private UUID uuid;

  // Requis par Forge.
  public RemoveChatBubbleMessage() {
    this(null);
  }

  public RemoveChatBubbleMessage(UUID uuid) {
    this.uuid = uuid;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeLong(this.uuid.getMostSignificantBits());
    buf.writeLong(this.uuid.getLeastSignificantBits());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.uuid = new UUID(buf.readLong(), buf.readLong());
  }

  public UUID getUniqueId() {
    return this.uuid;
  }
}
