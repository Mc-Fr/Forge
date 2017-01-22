package net.mcfr.decoration.lighting.tileEntities;

import static net.mcfr.utils.RenderUtils.*;

import net.mcfr.utils.math.Point2d;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TileEntityCampfireRenderer extends TileEntitySpecialRenderer<TileEntityCampfire> {
  public static final float W = 0.2f;
  
  @Override
  public void renderTileEntityAt(TileEntityCampfire te, double x, double y, double z, float partialTicks, int destroyStage) {
    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);
    
    fixLight(te);
    
    drawLog(0.2f, 0, 0, 0, -60, 17);
    drawLog(0, 0, 1 - W, 0, 20, 0);
    drawLog(1, 0, W, 0, -130, 35);
    
    GlStateManager.popMatrix();
  }
  
  private void drawLog(float x, float y, float z, float rx, float ry, float rz) {
    GlStateManager.translate(x, y, z);
    GlStateManager.rotate(rx, 1, 0, 0);
    GlStateManager.rotate(ry, 0, 1, 0);
    GlStateManager.rotate(rz, 0, 0, 1);
    
    Point2d p1 = new Point2d(0, 1 / W);
    Point2d p2 = new Point2d(1, 1 / W);
    Point2d p3 = new Point2d(1, 0);
    Point2d p4 = new Point2d(0, 0);
    
    // Côtés
    bindTex("minecraft:textures/blocks/log_oak.png");
    // Gauche
    vertex(0, 0, 0, p1);
    vertex(0, W, 0, p2);
    vertex(1, W, 0, p3);
    vertex(1, 0, 0, p4);
    // Haut
    vertex(0, W, 0, p1);
    vertex(0, W, W, p2);
    vertex(1, W, W, p3);
    vertex(1, W, 0, p4);
    // Droite
    vertex(0, W, W, p1);
    vertex(0, 0, W, p2);
    vertex(1, 0, W, p3);
    vertex(1, W, W, p4);
    // Bas
    vertex(0, 0, W, p1);
    vertex(0, 0, 0, p2);
    vertex(1, 0, 0, p3);
    vertex(1, 0, W, p4);
    
    draw();
    
    // Extrémités
    bindTex("minecraft:textures/blocks/log_oak_top.png");
    // Arrière
    vertex(0, 0, 0, 0, 1);
    vertex(0, 0, W, 1, 1);
    vertex(0, W, W, 1, 0);
    vertex(0, W, 0, 0, 0);
    // Avant
    vertex(1, 0, W, 0, 1);
    vertex(1, 0, 0, 1, 1);
    vertex(1, W, 0, 1, 0);
    vertex(1, W, W, 0, 0);
    
    draw();
    
    GlStateManager.rotate(-rz, 0, 0, 1);
    GlStateManager.rotate(-ry, 0, 1, 0);
    GlStateManager.rotate(-rx, 1, 0, 0);
    GlStateManager.translate(-x, -y, -z);
  }
}
