package net.mcfr.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public final class FacingUtils {
  /**
   * Indique l'orientation horizontale de l'entité en paramètre.
   *
   * @param entity l'entité
   * @return un des quatre points cardinaux
   */
  public static EnumFacing getHorizontalFacing(EntityLivingBase entity) {
    return EnumFacing.getHorizontal(MathHelper.floor_double((entity.rotationYaw * 4 / 360) + 0.5) & 3).getOpposite();
  }

  public static int getSignRotation(EntityLivingBase entity) {
    return MathHelper.floor_double((entity.rotationYaw + 180) * 16 / 360 + 0.5) & 15;
  }

  private FacingUtils() {}
}
