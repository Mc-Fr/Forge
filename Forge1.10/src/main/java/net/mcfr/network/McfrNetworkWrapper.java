package net.mcfr.network;

import net.mcfr.Constants;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class McfrNetworkWrapper {
  private static SimpleNetworkWrapper instance;
  private static int id = 0;

  public static SimpleNetworkWrapper getInstance() {
    if (instance == null)
      instance = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MOD_ID);
    return instance;
  }

  public static <T extends IMessage, U extends IMessage> void registerPacket(Class<? extends IMessageHandler<T, U>> handlerClass, Class<T> packetClass, Side side) {
    getInstance().registerMessage(handlerClass, packetClass, id++, side);
  }

  private McfrNetworkWrapper() {}
}
