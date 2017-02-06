package net.mcfr.core;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

/**
 * Classe principale du coremod.
 *
 * @author Mc-Fr
 */
@MCVersion("1.10.2")
@TransformerExclusions("net.mcfr")
public class McfrCore implements IFMLLoadingPlugin {
  @Override
  public String[] getASMTransformerClass() {
    return new String[]{"net.mcfr.core.Transformer"};
  }

  @Override
  public String getModContainerClass() {
    return null;
  }

  @Override
  public String getSetupClass() {
    return null;
  }

  @Override
  public void injectData(Map<String, Object> data) {}

  @Override
  public String getAccessTransformerClass() {
    return null;
  }
}
