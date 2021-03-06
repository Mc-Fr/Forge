package net.mcfr.craftsmanship.guis;

import net.mcfr.craftsmanship.BlockRack;
import net.mcfr.craftsmanship.tile_entities.TileEntityRack;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Interface de base des blocs artisan.
 * 
 * @author Mc-Fr
 */
@SideOnly(Side.CLIENT)
public abstract class GuiRack extends GuiContainer {
  /** La tile entity correspondante */
  private TileEntityRack tileEntityRack;

  /**
   * Crée une nouvelle interface.
   * 
   * @param playerInventory l'invetaire du joueur
   * @param rackInventory l'invetaire du bloc
   * @param player le joueur
   * @param blockClass la classe du bloc correspondant
   */
  public GuiRack(InventoryPlayer playerInventory, TileEntityRack rackInventory, EntityPlayer player, final Class<? extends BlockRack<?>> blockClass) {
    super(new ContainerRack(playerInventory, rackInventory, player, blockClass));
    this.tileEntityRack = rackInventory;
  }

  /**
   * @return la texture de l'interface
   */
  protected abstract ResourceLocation getTexture();

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1, 1, 1, 1);
    this.mc.getTextureManager().bindTexture(getTexture());
    int x = (this.width - this.xSize) / 2;
    int y = (this.height - this.ySize) / 2;
    drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

    int l = getTranformProgressScaled(22);
    drawTexturedModalRect(x + 77, y + 67, 212, 67, l, 5);
  }

  /**
   * Retourne la progression en nombre de pixels rapportée à la taille donnée.
   * 
   * @param pixels la taille en pixels de la barre de progression
   * @return le nombre de pixels à afficher
   */
  private int getTranformProgressScaled(int pixels) {
    return (int) (this.tileEntityRack.getProgress() * pixels);
  }
}