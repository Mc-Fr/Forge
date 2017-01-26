package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class OpenEditWallNoteMessage implements IMessage {
  private BlockPos notePos;

  // Requis par Forge.
  public OpenEditWallNoteMessage() {
    this(null);
  }

  public OpenEditWallNoteMessage(BlockPos signPos) {
    this.notePos = signPos;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.notePos.getX());
    buf.writeInt(this.notePos.getY());
    buf.writeInt(this.notePos.getZ());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    int x = buf.readInt();
    int y = buf.readInt();
    int z = buf.readInt();
    this.notePos = new BlockPos(x, y, z);
  }

  public BlockPos getSignPos() {
    return this.notePos;
  }
}
