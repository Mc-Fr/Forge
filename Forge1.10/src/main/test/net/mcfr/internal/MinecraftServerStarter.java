package net.mcfr.internal;

import java.lang.reflect.Field;

import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

/**
 * Classe permettant de gérer le serveur de test.
 *
 * @author Michael Vorburger
 * @author Mc-Fr
 */
public class MinecraftServerStarter {
  /** L'instance du gestionnaire. */
  private static MinecraftServerStarter instance = new MinecraftServerStarter();
  
  /**
   * @return l'instance du gestionnaire
   */
  public static MinecraftServerStarter getInstance() {
    return instance;
  }
  
  /**
   * L'instance du serveur. C'est un <i>Object</i> et non un <i>MinecraftServer</i> car cela
   * provoquerait une <i>ClassCastException</i> à ce stade du lancement des tests.
   */
  private Object game;
  /** ClassLoader du serveur. */
  private LaunchClassLoader minecraftServerClassLoader;
  
  private MinecraftServerStarter() {}
  
  /**
   * Démarre le serveur Minecraft en tâche de fonds.
   */
  public void startServer() throws Throwable {
    String[] args = {"nogui", "--noCoreSearch"};
    
    new GradleStartTestServer().launch(args);
  }
  
  /**
   * Attends que le serveur ait démarré.
   */
  public void waitForServerStartupCompletion() throws Throwable {
    /*
     * On ne peut pas utiliser TestsRunnerMod.isServerStarted car la classe TestsRunnerMod est
     * chargée par net.minecraft.launchwrapper.LaunchClassLoader, son champ statique n'aura donc pas
     * la même valeur dans le ClassLoader courant, mais on peut faire de la manière suivante :
     */
    ClassLoader minecraftClassLoader = internalGetMinecraftServerClassLoader();
    Thread.currentThread().setContextClassLoader(minecraftClassLoader);
    Class<?> testsRunnerModClass = minecraftClassLoader.loadClass(TestsRunnerMod.class.getName());
    Field isServerStartedField = testsRunnerModClass.getDeclaredField("isServerStarted");
    
    // Attente du lancement du serveur.
    while (Boolean.FALSE.equals(isServerStartedField.get(null)));
    // Ne pas transtyper.
    this.game = testsRunnerModClass.getDeclaredField("game").get(null);
  }
  
  /**
   * @return l'instance du serveur
   */
  public Object getGame() {
    checkIfStarted();
    return this.game;
  }
  
  /**
   * @return le ClassLoader du serveur
   */
  public ClassLoader getMinecraftServerClassLoader() {
    checkIfStarted();
    return internalGetMinecraftServerClassLoader();
  }
  
  /**
   * @return le ClassLoader du serveur
   */
  private ClassLoader internalGetMinecraftServerClassLoader() {
    if (this.minecraftServerClassLoader == null) {
      this.minecraftServerClassLoader = Launch.classLoader;
      /*
       * Ces deux instructions sont très importantes car sinon JUnit ne sera pas très content qu'on
       * remplace la classe de test depuis MinecraftTestRunner par celle du ClassLoader du serveur.
       */
      this.minecraftServerClassLoader.addClassLoaderExclusion("junit.");
      this.minecraftServerClassLoader.addClassLoaderExclusion("org.junit.");
    }
    
    return this.minecraftServerClassLoader;
  }
  
  /**
   * Vérifie que le serveur ait démarré.
   * 
   * @throws IllegalStateException si le serveur n'a pas démarré
   */
  private void checkIfStarted() throws IllegalStateException {
    if (!isRunning()) throw new IllegalStateException("Minecraft Server has not yet started (or already shut down)");
  }
  
  /**
   * @return vrai si le serveur a démarré
   */
  public boolean isRunning() {
    return this.game != null;
  }
}