package net.mcfr.internal;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Mod permettant d'accéder à l'instance du serveur pour les tests d'intégration.
 * 
 * @author Mc-Fr
 */
// @Mod(modid = "tests_runner", name = "mcfr_b_i Tests Runner", version = "1.0")
public class TestsRunnerMod {
  /** Instance du serveur. */
  public static MinecraftServer game;
  /** Indique si le serveur a démarré. */
  public static boolean isServerStarted = false;

  @EventHandler
  public void serverStarted(FMLServerStartingEvent e) {
    game = e.getServer();
    isServerStarted = true;
  }
}