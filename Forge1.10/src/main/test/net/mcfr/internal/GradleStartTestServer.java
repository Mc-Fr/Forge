package net.mcfr.internal;

import java.util.List;
import java.util.Map;

import net.minecraftforge.gradle.GradleStartCommon;

/**
 * Cette classe démarre une instance de serveur Minecraft.
 * 
 * @author Mc-Fr
 */
public class GradleStartTestServer extends GradleStartCommon {
  /**
   * Démarre le serveur.
   * 
   * @param args les paramètres de la ligne de commande
   */
  @Override
  public void launch(String[] args) throws Throwable {
    super.launch(args);
  }
  
  /**
   * @return la classe FMLServerTweaker
   */
  @Override
  protected String getTweakClass() {
    return "net.minecraftforge.fml.common.launcher.FMLServerTweaker";
  }
  
  /**
   * @return la classe Launch
   */
  @Override
  protected String getBounceClass() {
    return "net.minecraft.launchwrapper.Launch";
  }
  
  @Override
  protected void preLaunch(Map<String, String> argMap, List<String> extras) {}
  
  @Override
  protected void setDefaultArguments(Map<String, String> argMap) {}
}