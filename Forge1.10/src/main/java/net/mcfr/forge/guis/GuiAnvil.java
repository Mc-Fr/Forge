package net.mcfr.forge.guis;

import net.mcfr.Constants;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAnvil extends GuiContainer {
  private static final ResourceLocation ANVIL_GUI_TEXTURES = new ResourceLocation(Constants.MOD_ID, "textures/gui/container/anvil.png");

  public GuiAnvil(InventoryPlayer playerInv, World worldIn, BlockPos blockPosition) {
    super(new ContainerAnvil(playerInv, worldIn, blockPosition));
    this.ySize = 203;
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1, 1, 1, 1);
    this.mc.getTextureManager().bindTexture(ANVIL_GUI_TEXTURES);
    int x = (this.width - this.xSize) / 2;
    int y = (this.height - this.ySize) / 2;
    drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
  }
}