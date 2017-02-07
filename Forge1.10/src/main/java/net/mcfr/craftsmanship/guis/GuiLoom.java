package net.mcfr.craftsmanship.guis;

import net.mcfr.Constants;
import net.mcfr.craftsmanship.BlockLoom;
import net.mcfr.craftsmanship.tile_entities.TileEntityLoom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Interface du pétier à tisser.
 *
 * @author Mc-Fr
 */
@SideOnly(Side.CLIENT)
public class GuiLoom extends GuiRack {
  private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/gui/container/loom.png");

  public GuiLoom(InventoryPlayer playerInventory, TileEntityLoom loomInventory, EntityPlayer player) {
    super(playerInventory, loomInventory, player, BlockLoom.class);
  }

  @Override
  protected ResourceLocation getTexture() {
    return GUI_TEXTURE;
  }
}
