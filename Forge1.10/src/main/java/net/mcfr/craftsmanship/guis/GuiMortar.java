package net.mcfr.craftsmanship.guis;

import net.mcfr.Constants;
import net.mcfr.craftsmanship.BlockMortar;
import net.mcfr.craftsmanship.tile_entities.TileEntityMortar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Interface du mortier.
 *
 * @author Mc-Fr
 */
@SideOnly(Side.CLIENT)
public class GuiMortar extends GuiRack {
  private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/container/mortar.png");

  public GuiMortar(InventoryPlayer playerInventory, TileEntityMortar mortarInventory, EntityPlayer player) {
    super(playerInventory, mortarInventory, player, BlockMortar.class);
  }

  @Override
  protected ResourceLocation getTexture() {
    return GUI_TEXTURE;
  }
}
