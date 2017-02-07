package net.mcfr.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

/**
 * Cette classe fournit des méthodes pour simplifier la manipulation des orientations.
 *
 * @author Mc-Fr
 */
public final class FacingUtils {
  /**
   * Retourne l'orientation horizontale de l'entité en paramètre.
   *
   * @param entity l'entité
   * @return NORTH, SOUTH, WEST ou EAST
   */
  public static EnumFacing getHorizontalFacing(EntityLivingBase entity) {
    return EnumFacing.getHorizontal(MathHelper.floor_double((entity.rotationYaw * 4 / 360) + 0.5) & 3).getOpposite();
  }

  /**
   * Retourne la rotation de l'entité donnée.
   * 
   * @param entity l'entité
   * @return la rotation entre 0 et 15 inclus
   */
  public static int getSignRotation(EntityLivingBase entity) {
    return MathHelper.floor_double((entity.rotationYaw + 180) * 16 / 360 + 0.5) & 15;
  }

  private FacingUtils() {}
}
