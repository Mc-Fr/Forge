package net.mcfr.decoration.signs.tile_entities;

import net.mcfr.Constants;

/**
 * Classe de rendu des panneaux HRP.
 *
 * @author Mc-Fr
 */
public class TileEntityOrpSignRenderer extends TileEntitySignRenderer<TileEntityOrpSign> {
  public TileEntityOrpSignRenderer() {
    super(Constants.MOD_ID, "textures/entity/sign_orp.png");
  }
}
