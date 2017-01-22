package net.mcfr.equipment;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Cette classe regroupe tous les matériaux d'outils propres au serveur.
 *
 * @author Mc-Fr
 */
// TODO : équilibrages
public final class AdditionalToolMaterial {
  public static final ToolMaterial STEEL = EnumHelper.addToolMaterial("STEEL", 3, 0, 0, 0, 0);
  public static final ToolMaterial GEROUN = EnumHelper.addToolMaterial("GEROUN", 0, 0, 0, 0, 0);
  public static final ToolMaterial BARBARIAN = EnumHelper.addToolMaterial("BARBARIAN", 0, 0, 0, 0, 0);

  private AdditionalToolMaterial() {}
}
