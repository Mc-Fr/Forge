package net.mcfr.craftsmanship.guis;

import net.mcfr.Constants;
import net.mcfr.craftsmanship.BlockTanningRack;
import net.mcfr.craftsmanship.tileEntities.TileEntityTanningRack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

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
