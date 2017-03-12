package net.mcfr.equipment;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Cette classe regroupe tous les matériaux d'outils propres au serveur.
 *
 * @author Mc-Fr
 */
public final class AdditionalToolMaterial {
  public static final ToolMaterial BONE = EnumHelper.addToolMaterial("BONE", 1, 131, 4f, 1f, 5);
  public static final ToolMaterial BRONZE = EnumHelper.addToolMaterial("BRONZE", 2, 200, 5.0F, 1.0F, 0);
  public static final ToolMaterial STEEL = EnumHelper.addToolMaterial("STEEL", 3, 1000, 8.0F, 2.0F, 0);
  private AdditionalToolMaterial() {}
}
