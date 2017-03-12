package net.mcfr.equipment;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Cette classe regroupe tous les matériaux d'outils propres au serveur.
 *
 * @author Mc-Fr
 */
public final class AdditionalToolMaterial {
  public static final ToolMaterial STEEL = EnumHelper.addToolMaterial("STEEL", 3, 1561, 8f, 3f, 10);
  public static final ToolMaterial BRONZE = EnumHelper.addToolMaterial("BRONZE", 2, 200, 6f, 2f, 14);
  public static final ToolMaterial BONE = EnumHelper.addToolMaterial("BONE", 1, 131, 4f, 1f, 5);

  private AdditionalToolMaterial() {}
}
