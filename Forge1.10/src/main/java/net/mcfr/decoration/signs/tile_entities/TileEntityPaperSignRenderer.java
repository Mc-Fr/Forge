package net.mcfr.decoration.signs.tile_entities;

import net.mcfr.Constants;

/**
 * Classe de rendu des panneaux en papier.
 *
 * @author Mc-Fr
 */
public class TileEntityPaperSignRenderer extends TileEntitySignRenderer<TileEntityPaperSign> {
  public TileEntityPaperSignRenderer() {
    super(Constants.MOD_ID, "textures/entity/sign_paper.png");
  }
}
