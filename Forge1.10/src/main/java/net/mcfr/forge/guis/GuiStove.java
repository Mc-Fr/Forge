package net.mcfr.forge.guis;

import net.mcfr.Constants;
import net.mcfr.forge.tile_entities.TileEntityStove;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiStove extends GuiContainer {
  private static final ResourceLocation STOVE_GUI_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/gui/container/stove.png");

  private final TileEntityStove tileStove;

  public GuiStove(InventoryPlayer playerInv, TileEntityStove stoveInv) {
    super(new ContainerStove(playerInv, stoveInv));
    this.tileStove = stoveInv;
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1, 1, 1, 1);
    this.mc.getTextureManager().bindTexture(STOVE_GUI_TEXTURES);
    int x = (this.width - this.xSize) / 2;
    int y = (this.height - this.ySize) / 2;
    drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

    int max = -1, progress = -1, offset = -1;

    if (this.tileStove.isBurning()) {
      max = 13;
      progress = getFuelProgressScaled(max);
      offset = max - progress;
      drawTexturedModalRect(x + 57, y + 32 + offset, 176, 1 + offset, 14, progress);

      progress = getNextTemperatureProgressScaled(22);
      drawTexturedModalRect(x + 80, y + 38, 176, 20, progress, 5);
    }

    max = 66;
    progress = getTemperatureProgressScaled(max);
    offset = max - progress;
    drawTexturedModalRect(x + 110, y + 4 + offset, 207, 4 + offset, 19, progress);
  }

  private int getFuelProgressScaled(int pixels) {
    return (int) ((1 - this.tileStove.getFuelProgress()) * pixels) + 1;
  }

  private int getNextTemperatureProgressScaled(int pixels) {
    return (int) (this.tileStove.getNextTemperatureProgress() * pixels);
  }

  private int getTemperatureProgressScaled(int pixels) {
    return (int) (this.tileStove.getTemperatureProgress() * pixels);
  }
}