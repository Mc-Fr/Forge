package net.mcfr.proxy;

import net.mcfr.capabilities.IPrevFood;
import net.mcfr.capabilities.PrevFood;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Proxy utilisé par le serveur.
 * 
 * @author Mc-Fr
 */
public class CommonProxy {
  public void register() {
    CapabilityManager.INSTANCE.register(IPrevFood.class, new PrevFood.Storage(), PrevFood.class);
  }
}