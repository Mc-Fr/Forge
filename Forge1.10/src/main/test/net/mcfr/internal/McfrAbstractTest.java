package net.mcfr.internal;

import org.junit.Before;
import org.junit.runner.RunWith;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

/**
 * Toutes les classes de test doivent hériter de cette classe pour bénéficier de l'instance du
 * serveur.
 *
 * @author Mc-Fr
 */
@RunWith(McFrTestsRunner.class)
public abstract class McfrAbstractTest {
  /** L'instance du serveur. Doit être statique pour pouvoir être instanciée. */
  public static MinecraftServer game;
  
  protected World world;

  @Before
  public void setUp() {
    this.world = game.getEntityWorld();
  }
}
