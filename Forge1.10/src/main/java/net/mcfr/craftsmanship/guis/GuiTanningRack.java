package net.mcfr.craftsmanship.guis;

import net.mcfr.Constants;
import net.mcfr.craftsmanship.BlockTanningRack;
import net.mcfr.craftsmanship.tile_entities.TileEntityTanningRack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Interface de l'atelier de tannage.
 *
 * @author Mc-Fr
 */
@SideOnly(Side.CLIENT)
public class GuiTanningRack extends GuiRack {
  private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/container/tanning_rack.png");

  public GuiTanningRack(InventoryPlayer playerInventory, TileEntityTanningRack rackInventory, EntityPlayer player) {
    super(playerInventory, rackInventory, player, BlockTanningRack.class);
  }

  @Override
  protected ResourceLocation getTexture() {
    return GUI_TEXTURE;
  }
}
