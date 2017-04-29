package net.mcfr.network;

import net.mcfr.Constants;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Cette classe joue le rôle de gestionnaire de paquets pour le mod.
 *
 * @author Mc-Fr
 */
public final class McfrNetworkWrapper {
  private static SimpleNetworkWrapper instance;
  /** ID du prochain paquet */
  private static int id = 0;

  /**
   * @return l'instance
   */
  public static SimpleNetworkWrapper getInstance() {
    if (instance == null)
      instance = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MOD_ID);
    return instance;
  }

  /**
   * Enregistre un type de paquet.
   * 
   * @param handlerClass le gestionnaire
   * @param packetClass le paquet
   * @param side le côté concerné
   */
  public static <T extends IMessage, U extends IMessage> void registerPacket(Class<? extends IMessageHandler<T, U>> handlerClass,
      Class<T> packetClass, Side side) {
    getInstance().registerMessage(handlerClass, packetClass, id++, side);
  }

  private McfrNetworkWrapper() {}
}
