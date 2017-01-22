package net.mcfr.forge.tileEntities;

import static net.mcfr.utils.RenderUtils.*;

import net.mcfr.utils.math.Point2d;
import net.mcfr.utils.math.Point3d;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TileEntityBellowsRenderer extends TileEntitySpecialRenderer<TileEntityBellows> {
  private static final float H = 0.25f;
  private static final float W = 0.1f;
  
  @Override
  public void renderTileEntityAt(TileEntityBellows te, double x, double y, double z, float partialTicks, int destroyStage) {
    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);
    
    fixLight(te);
    
    float h = -te.getStep() / 16f;
    
    drawTop(h);
    drawMiddle(h);
    drawBottom();
    
    GlStateManager.popMatrix();
  }
  
  private void drawTop(float offset) {
    offset += 1 - H;
    GlStateManager.translate(0, offset, 0);
    drawBottom();
    GlStateManager.translate(0, -offset, 0);
  }
  
  private void drawMiddle(float offset) {
    offset += 1 - H;
    bindTex("minecraft:textures/blocks/wool_colored_brown.png");
    
    Point3d[] modPoints = { //
      new Point3d(W, H, W), //
      new Point3d(W, offset, W), //
      new Point3d(1 - W, offset, W), //
      new Point3d(1 - W, H, W), //
      new Point3d(1 - W, offset, 1 - W), //
      new Point3d(1 - W, H, 1 - W), //
      new Point3d(W, offset, 1 - W), //
      new Point3d(W, H, 1 - W), //
    };
    Point2d[] texPoints = {new Point2d(1 - W, 1 - H), new Point2d(1 - W, -offset + 1), new Point2d(W, -offset + 1), new Point2d(W, 1 - H)};
    
    // Nord
    vertex(modPoints[0], texPoints[0]);
    vertex(modPoints[1], texPoints[1]);
    vertex(modPoints[2], texPoints[2]);
    vertex(modPoints[3], texPoints[3]);
    
    // Est
    vertex(modPoints[3], texPoints[0]);
    vertex(modPoints[2], texPoints[1]);
    vertex(modPoints[4], texPoints[2]);
    vertex(modPoints[5], texPoints[3]);
    
    // Sud
    vertex(modPoints[5], texPoints[0]);
    vertex(modPoints[4], texPoints[1]);
    vertex(modPoints[6], texPoints[2]);
    vertex(modPoints[7], texPoints[3]);
    
    // Ouest
    vertex(modPoints[7], texPoints[0]);
    vertex(modPoints[6], texPoints[1]);
    vertex(modPoints[1], texPoints[2]);
    vertex(modPoints[0], texPoints[3]);
    
    draw();
  }
  
  private void drawBottom() {
    bindTex("minecraft:textures/blocks/planks_spruce.png");
    
    // Haut
    vertex(1, H, 0, 1, 0);
    vertex(0, H, 0, 0, 0);
    vertex(0, H, 1, 0, 1);
    vertex(1, H, 1, 1, 1);
    
    // Bas
    vertex(0, 0, 0, 0, 1);
    vertex(1, 0, 0, 1, 1);
    vertex(1, 0, 1, 1, 0);
    vertex(0, 0, 1, 0, 0);
    
    draw();
    
    bindTex("minecraft:textures/blocks/planks_oak.png");
    
    // Nord
    vertex(0, 0, 0, 1, 1);
    vertex(0, H, 0, 1, 1 - H);
    vertex(1, H, 0, 0, 1 - H);
    vertex(1, 0, 0, 0, 1);
    
    // Est
    vertex(1, 0, 0, 1, 1);
    vertex(1, H, 0, 1, 1 - H);
    vertex(1, H, 1, 0, 1 - H);
    vertex(1, 0, 1, 0, 1);
    
    // Sud
    vertex(1, 0, 1, 1, 1);
    vertex(1, H, 1, 1, 1 - H);
    vertex(0, H, 1, 0, 1 - H);
    vertex(0, 0, 1, 0, 1);
    
    // Ouest
    vertex(0, 0, 1, 1, 1);
    vertex(0, H, 1, 1, 1 - H);
    vertex(0, H, 0, 0, 1 - H);
    vertex(0, 0, 0, 0, 1);
    
    draw();
  }
}
