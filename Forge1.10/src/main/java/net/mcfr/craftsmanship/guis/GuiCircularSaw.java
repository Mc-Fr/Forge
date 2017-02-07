package net.mcfr.craftsmanship.guis;

import net.mcfr.Constants;
import net.mcfr.craftsmanship.BlockCircularSaw;
import net.mcfr.craftsmanship.tile_entities.TileEntityCircularSaw;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * L'interface de la scie circulaire.
 *
 * @author Mc-Fr
 */
public class GuiCircularSaw extends GuiRack {
  private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/container/circular_saw.png");

  public GuiCircularSaw(InventoryPlayer playerInventory, TileEntityCircularSaw sawInventory, EntityPlayer player) {
    super(playerInventory, sawInventory, player, BlockCircularSaw.class);
  }

  @Override
  protected ResourceLocation getTexture() {
    return GUI_TEXTURE;
  }
}
