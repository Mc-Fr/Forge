package net.mcfr.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public final class FacingUtils {
  /**
   * Indique l'orientation horizontale de l'entité en paramétre.
   *
   * @param entity l'entité
   * @return un des quatre points cardinaux
   */
  public static EnumFacing getHorizontalFacing(EntityLivingBase entity) {
    return EnumFacing.getHorizontal(MathHelper.floor_double((entity.rotationYaw * 4 / 360) + 0.5) & 3).getOpposite();
  }

  private FacingUtils() {}
}
