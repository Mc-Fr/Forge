package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.Constants;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ModVersionCheckMessage implements IMessage {
  private String modVersion;

  public ModVersionCheckMessage() {
    this("");
  }

  public ModVersionCheckMessage(String modVersion) {
    this.modVersion = modVersion;
    System.out.println("Création paquet : " + modVersion);
  }

  public String getModVersion() {
    return this.modVersion;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    byte[] b = new byte[buf.readInt()];
    buf.readBytes(b);
    this.modVersion = new String(b);
    System.out.println("from bytes");
  }

  @Override
  public void toBytes(ByteBuf buf) {
    byte[] b = this.modVersion.getBytes();
    buf.writeInt(b.length);
    buf.writeBytes(b);
  }

  // FIXME jamais appelé par le serveur.
  public static class ServerHandler implements IMessageHandler<ModVersionCheckMessage, IMessage> {
    @Override
    public IMessage onMessage(final ModVersionCheckMessage message, MessageContext ctx) {
      System.out.println("ServerHandler.onMessage()");
      System.out.println(message.getModVersion());
      if (!Constants.MOD_ID.equals(message.getModVersion())) {
        ctx.getServerHandler().kickPlayerFromServer("La version du mod ne correspond pas : " + message.getModVersion());
      }
      return null;
    }
  }
}
