package net.mcfr;

import java.lang.reflect.InvocationTargetException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import net.mcfr.decoration.lighting.BlockCampfireTestsSuite;
import net.mcfr.internal.MinecraftServerStarter;

/**
 * Tests d'intégration du mod <i>Mc-Fr Blocs et Items</i>.
 *
 * @author Mc-Fr
 */
@RunWith(Suite.class)
@SuiteClasses({ McfrTests.class, BlockCampfireTestsSuite.class})
public class McfrTestsSuite {
  /**
   * Stoppe le serveur après l'exécution de tous les tests.
   */
  @AfterClass
  public static void tearDownAfterClass() {
    try {
      MinecraftServerStarter.getInstance().getGame().getClass().getMethod("stopServer").invoke(MinecraftServerStarter.getInstance().getGame());
    }
    catch (IllegalAccessException e) {}
    catch (IllegalArgumentException e) {}
    catch (InvocationTargetException e) {}
    catch (NoSuchMethodException e) {}
    catch (SecurityException e) {}
  }
}