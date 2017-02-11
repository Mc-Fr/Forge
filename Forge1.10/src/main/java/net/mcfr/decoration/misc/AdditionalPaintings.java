package net.mcfr.decoration.misc;

import net.minecraft.entity.item.EntityPainting.EnumArt;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Cette classe ajoute quelques nouveaux types de tableaux.
 * 
 * @author Mc-Fr
 */
public final class AdditionalPaintings {
  public static final EnumArt TEST = EnumHelper.addArt("TEST", "test", 64, 64, 0, 0);

  private AdditionalPaintings() {}
}
