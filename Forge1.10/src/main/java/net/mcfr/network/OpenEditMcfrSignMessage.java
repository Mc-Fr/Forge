package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class OpenEditMcfrSignMessage implements IMessage {
  // Requis par Forge.
  public OpenEditMcfrSignMessage() {
    this(null);
  }

  private BlockPos signPos;

  public OpenEditMcfrSignMessage(BlockPos signPos) {
    this.signPos = signPos;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.signPos.getX());
    buf.writeInt(this.signPos.getY());
    buf.writeInt(this.signPos.getZ());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    int x = buf.readInt();
    int y = buf.readInt();
    int z = buf.readInt();
    this.signPos = new BlockPos(x, y, z);
  }

  public BlockPos getSignPos() {
    return this.signPos;
  }
}