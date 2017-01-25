package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.decoration.signs.tileEntities.TileEntityMcfrSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityNormalSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityOrpSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityPaperSign;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class OpenEditMcfrSignMessage implements IMessage {
  private BlockPos signPos;
  private SignType signType;

  // Requis par Forge.
  public OpenEditMcfrSignMessage() {
    this(null, null);
  }

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

  public BlockPos getSignPos() {
    return this.signPos;
  }

  public SignType getSignType() {
    return this.signType;
  }

  public static enum SignType {
    NORMAL,
    PAPER,
    ORP;

    public static SignType fromOrdinal(int ordinal) {
      return values()[ordinal % values().length];
    }

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
}