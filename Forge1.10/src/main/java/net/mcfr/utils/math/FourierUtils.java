package net.mcfr.utils.math;

import net.minecraft.util.math.MathHelper;

/**
 * Cette classe fournit des méthodes pour calculer des séries de Fourier.
 *
 * @author Mc-Fr
 */
public final class FourierUtils {
  private static float[] resizeTable(float[] table, int size) {
    float[] result = new float[size];
    for (int i = 0; i < table.length; i++) {
      result[i] = table[i];
    }
    return result;
  }

  public static float calculateSeries(float t, float[] a, float[] b, float offSet) {
    float result = 0.0F;
    float pi = (float) Math.PI;

    if (a.length < b.length) {
      a = resizeTable(a, b.length);
    }
    if (a.length > b.length) {
      b = resizeTable(b, a.length);
    }

    for (int i = 0; i < a.length; i++) {
      if (a[i] != 0.0F) {
        result += a[i] * MathHelper.cos(i * t * 2.0F * pi + offSet * 2.0F * pi);
      }
      if (b[i] != 0.0F) {
        result += b[i] * MathHelper.sin(i * t * 2.0F * pi + offSet * 2.0F * pi);
      }
    }

    return result;
  }

  public static float calculateSeries(float t, float[] a, float[] b) {
    return calculateSeries(t, a, b, 0.0F);
  }

  public static float calculateScaledSeries(float t, float[] a, float[] b, float scale, float offSet) {
    return calculateSeries(t, a, b, offSet) * scale;
  }

  public static float calculateScaledSeries(float t, float[] a, float[] b, float scale) {
    return calculateScaledSeries(t, a, b, scale, 0.0F);
  }
}
