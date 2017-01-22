package net.mcfr.forge.guis;

import net.mcfr.Constants;
import net.mcfr.forge.tileEntities.TileEntityStove;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiStove extends GuiContainer {
  private static final ResourceLocation STOVE_GUI_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/gui/container/stove.png");

  private final IInventory stoveInventory;

  public GuiStove(InventoryPlayer playerInv, TileEntityStove stoveInv) {
    super(new ContainerStove(playerInv, stoveInv));
    this.stoveInventory = stoveInv;
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1, 1, 1, 1);
    this.mc.getTextureManager().bindTexture(STOVE_GUI_TEXTURES);
    int x = (this.width - this.xSize) / 2;
    int y = (this.height - this.ySize) / 2;
    drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

    if (TileEntityFurnace.isBurning(this.stoveInventory)) {
      int progress = getBurnLeftScaled(13);
      drawTexturedModalRect(x + 56, y + 36 + 12 - progress, 176, 12 - progress, 14, progress + 1);
    }

    int progress = getTemperatureScaled(24);
    drawTexturedModalRect(x + 79, y + 34, 176, 14, progress + 1, 16);
  }

  private int getTemperatureScaled(int pixels) {
    int temperature = this.stoveInventory.getField(0);
    return temperature != 0 ? temperature * pixels / TileEntityStove.MAX_TEMPERATURE : 0;
  }

  private int getBurnLeftScaled(int pixels) {
    int totalBurnTime = this.stoveInventory.getField(2);

    if (totalBurnTime == 0) totalBurnTime = 200;

    return this.stoveInventory.getField(1) * pixels / totalBurnTime;
  }
}