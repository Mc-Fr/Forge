package net.mcfr.decoration.containerBlocks.guis;

import static net.mcfr.utils.RenderUtils.*;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiRestrictedChest extends GuiContainer {
  private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");

  private final IInventory chestInventory;
  private final IInventory playerInventory;

  public GuiRestrictedChest(IInventory playerInv, IInventory chestInv, EntityPlayer player, final Class<? extends Block> blockClass) {
    super(new ContainerRestricted(playerInv, chestInv, player, blockClass));
    this.chestInventory = chestInv;
    this.playerInventory = playerInv;
    this.ySize = TOP_OFFSET + SLOT_SIZE + INV_SEPARATOR + 3 * SLOT_SIZE + HOTBAR_SEPARATOR + SLOT_SIZE + BOTTOM_OFFSET;
  }

  @Override
  protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    this.fontRendererObj.drawString(this.chestInventory.getDisplayName().getUnformattedText(), SIDE_OFFSET, 6, 4210752);
    this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), SIDE_OFFSET, this.ySize - 96 + 2, 4210752);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1, 1, 1, 1);
    this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);

    int x = (this.width - this.xSize) / 2;
    int y = (this.height - this.ySize) / 2;
    int separator = TOP_OFFSET + SLOT_SIZE;

    drawTexturedModalRect(x, y, 0, 0, this.xSize, separator);
    drawTexturedModalRect(x, y + separator, 0, 126, this.xSize, this.ySize - separator);
  }
}