package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class OpenEditPaperMessage implements IMessage {
  private EnumHand hand;
  private boolean isUnsigned;

  // Requis par Forge.
  public OpenEditPaperMessage() {
    this(null, true);
  }

  public OpenEditPaperMessage(EnumHand hand, boolean isUnsigned) {
    this.hand = hand;
    this.isUnsigned = isUnsigned;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(getHand().ordinal());
    buf.writeBoolean(isPaperUnsigned());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.hand = EnumHand.values()[buf.readInt()];
    this.isUnsigned = buf.readBoolean();
  }

  public EnumHand getHand() {
    return this.hand;
  }

  public boolean isPaperUnsigned() {
    return this.isUnsigned;
  }
}
