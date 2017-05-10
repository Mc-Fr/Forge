package net.mcfr.decoration.lighting.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Modèle du grand lustre.
 *
 * @author Mc-Fr
 */
public class ModelLargeChandelier extends ModelBase {
  private ModelRenderer middle;
  private ModelRenderer chain1;
  private ModelRenderer chain2;
  private ModelRenderer candleA1;
  private ModelRenderer candleA2;
  private ModelRenderer candleB1;
  private ModelRenderer candleB2;
  private ModelRenderer candleC1;
  private ModelRenderer candleC2;
  private ModelRenderer candleD1;
  private ModelRenderer candleD2;
  private ModelRenderer plank1;
  private ModelRenderer plank2;
  private ModelRenderer plank3;
  private ModelRenderer plank4;
  private ModelRenderer candleE1;
  private ModelRenderer candleE2;
  private ModelRenderer candleF1;
  private ModelRenderer candleF2;
  private ModelRenderer candleG1;
  private ModelRenderer candleG2;
  private ModelRenderer candleH1;
  private ModelRenderer candleH2;
  private ModelRenderer side1;
  private ModelRenderer side2;
  private ModelRenderer side3;
  private ModelRenderer side4;
  private ModelRenderer side5;
  private ModelRenderer side6;
  private ModelRenderer side7;
  private ModelRenderer side8;

  public ModelLargeChandelier() {
    this.textureWidth = 128;
    this.textureHeight = 64;

    this.middle = new ModelRenderer(this, 0, 50);
    this.middle.addBox(-2.5f, 0f, -2.5f, 5, 2, 5);
    this.middle.setRotationPoint(0f, 15.5f, 0f);
    this.middle.setTextureSize(128, 64);
    this.middle.mirror = true;
    setRotation(this.middle, 0f, 0.7853982f, 0f);

    this.chain1 = new ModelRenderer(this, 0, 0);
    this.chain1.addBox(-8f, 0f, 0f, 16, 16, 0);
    this.chain1.setRotationPoint(0f, 1f, 0f);
    this.chain1.setTextureSize(128, 64);
    this.chain1.mirror = true;
    setRotation(this.chain1, 0f, 2.3572325f, 0f);

    this.chain2 = new ModelRenderer(this, 0, 0);
    this.chain2.addBox(0f, 0f, -8f, 0, 16, 16);
    this.chain2.setRotationPoint(0f, 1f, 0f);
    this.chain2.setTextureSize(128, 64);
    this.chain2.mirror = true;
    setRotation(this.chain2, 0f, 2.3572325f, 0f);

    this.candleA1 = new ModelRenderer(this, 50, 0);
    this.candleA1.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleA1.setRotationPoint(17f, 16f, 0f);
    this.candleA1.setTextureSize(128, 64);
    this.candleA1.mirror = true;
    setRotation(this.candleA1, 0f, 0f, 0f);

    this.candleA2 = new ModelRenderer(this, 50, 0);
    this.candleA2.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleA2.setRotationPoint(17f, 16f, 0f);
    this.candleA2.setTextureSize(128, 64);
    this.candleA2.mirror = true;
    setRotation(this.candleA2, 0f, 1.570796f, 0f);

    this.candleB1 = new ModelRenderer(this, 50, 0);
    this.candleB1.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleB1.setRotationPoint(-17f, 16f, 0f);
    this.candleB1.setTextureSize(128, 64);
    this.candleB1.mirror = true;
    setRotation(this.candleB1, 0f, 0f, 0f);

    this.candleB2 = new ModelRenderer(this, 50, 0);
    this.candleB2.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleB2.setRotationPoint(-17f, 16f, 0f);
    this.candleB2.setTextureSize(128, 64);
    this.candleB2.mirror = true;
    setRotation(this.candleB2, 0f, 1.570796f, 0f);

    this.candleC1 = new ModelRenderer(this, 50, 0);
    this.candleC1.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleC1.setRotationPoint(0f, 16f, 17f);
    this.candleC1.setTextureSize(128, 64);
    this.candleC1.mirror = true;
    setRotation(this.candleC1, 0f, 0f, 0f);

    this.candleC2 = new ModelRenderer(this, 50, 0);
    this.candleC2.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleC2.setRotationPoint(0f, 16f, 17f);
    this.candleC2.setTextureSize(128, 64);
    this.candleC2.mirror = true;
    setRotation(this.candleC2, 0f, 1.570796f, 0f);

    this.candleD1 = new ModelRenderer(this, 50, 0);
    this.candleD1.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleD1.setRotationPoint(0f, 16f, -17f);
    this.candleD1.setTextureSize(128, 64);
    this.candleD1.mirror = true;
    setRotation(this.candleD1, 0f, 1.570796f, 0f);

    this.candleD2 = new ModelRenderer(this, 50, 0);
    this.candleD2.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleD2.setRotationPoint(0f, 16f, -17f);
    this.candleD2.setTextureSize(128, 64);
    this.candleD2.mirror = true;
    setRotation(this.candleD2, 0f, 0f, 0f);

    this.plank1 = new ModelRenderer(this, 42, 23);
    this.plank1.addBox(-1.5f, 0f, -20f, 3, 1, 40);
    this.plank1.setRotationPoint(0f, 15.8f, 0f);
    this.plank1.setTextureSize(128, 64);
    this.plank1.mirror = true;
    setRotation(this.plank1, 0f, 0.7853982f, 0f);

    this.plank2 = new ModelRenderer(this, 42, 23);
    this.plank2.addBox(-1.5f, 0f, -20f, 3, 1, 40);
    this.plank2.setRotationPoint(0f, 15.8f, 0f);
    this.plank2.setTextureSize(128, 64);
    this.plank2.mirror = true;
    setRotation(this.plank2, 0f, -0.7853982f, 0f);

    this.plank3 = new ModelRenderer(this, 42, 23);
    this.plank3.addBox(-1.5f, 0f, -20f, 3, 1, 40);
    this.plank3.setRotationPoint(0f, 16f, 0f);
    this.plank3.setTextureSize(128, 64);
    this.plank3.mirror = true;
    setRotation(this.plank3, 0f, 0f, 0f);

    this.plank4 = new ModelRenderer(this, 42, 23);
    this.plank4.addBox(-1.5f, 0f, -20f, 3, 1, 40);
    this.plank4.setRotationPoint(0f, 16f, 0f);
    this.plank4.setTextureSize(128, 64);
    this.plank4.mirror = true;
    setRotation(this.plank4, 0f, 1.570796f, 0f);

    this.candleH1 = new ModelRenderer(this, 50, 0);
    this.candleH1.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleH1.setRotationPoint(-12f, 16f, 12f);
    this.candleH1.setTextureSize(128, 64);
    this.candleH1.mirror = true;
    setRotation(this.candleH1, 0f, -0.7853982f, 0f);

    this.candleH2 = new ModelRenderer(this, 50, 0);
    this.candleH2.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleH2.setRotationPoint(-12f, 16f, 12f);
    this.candleH2.setTextureSize(128, 64);
    this.candleH2.mirror = true;
    setRotation(this.candleH2, 0f, 0.7853982f, 0f);

    this.candleE1 = new ModelRenderer(this, 50, 0);
    this.candleE1.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleE1.setRotationPoint(12f, 16f, 12f);
    this.candleE1.setTextureSize(128, 64);
    this.candleE1.mirror = true;
    setRotation(this.candleE1, 0f, -0.7853982f, 0f);

    this.candleE2 = new ModelRenderer(this, 50, 0);
    this.candleE2.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleE2.setRotationPoint(12f, 16f, 12f);
    this.candleE2.setTextureSize(128, 64);
    this.candleE2.mirror = true;
    setRotation(this.candleE2, 0f, 0.7853982f, 0f);

    this.candleF2 = new ModelRenderer(this, 50, 0);
    this.candleF2.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleF2.setRotationPoint(12f, 16f, -12f);
    this.candleF2.setTextureSize(128, 64);
    this.candleF2.mirror = true;
    setRotation(this.candleF2, 0f, 0.7853982f, 0f);

    this.candleF1 = new ModelRenderer(this, 50, 0);
    this.candleF1.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleF1.setRotationPoint(12f, 16f, -12f);
    this.candleF1.setTextureSize(128, 64);
    this.candleF1.mirror = true;
    setRotation(this.candleF1, 0f, -0.7853982f, 0f);

    this.candleG2 = new ModelRenderer(this, 50, 0);
    this.candleG2.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleG2.setRotationPoint(-12f, 16f, -12f);
    this.candleG2.setTextureSize(128, 64);
    this.candleG2.mirror = true;
    setRotation(this.candleG2, 0f, 0.7853982f, 0f);

    this.candleG1 = new ModelRenderer(this, 50, 0);
    this.candleG1.addBox(-2f, -10f, 0f, 4, 10, 0);
    this.candleG1.setRotationPoint(-12f, 16f, -12f);
    this.candleG1.setTextureSize(128, 64);
    this.candleG1.mirror = true;
    setRotation(this.candleG1, 0f, -0.7853982f, 0f);

    this.side1 = new ModelRenderer(this, 24, 28);
    this.side1.addBox(-0.1f, -3f, -8f, 1, 3, 16);
    this.side1.setRotationPoint(-7.5f, 18f, -17.5f);
    this.side1.setTextureSize(128, 64);
    this.side1.mirror = true;
    setRotation(this.side1, 0f, -1.186824f, 0f);

    this.side2 = new ModelRenderer(this, 24, 28);
    this.side2.addBox(-0.1f, -3f, -8f, 1, 3, 16);
    this.side2.setRotationPoint(7.5f, 18f, 17.5f);
    this.side2.setTextureSize(128, 64);
    this.side2.mirror = true;
    setRotation(this.side2, 0f, 1.972222f, 0f);

    this.side3 = new ModelRenderer(this, 24, 28);
    this.side3.addBox(-1f, -3f, -8f, 1, 3, 16);
    this.side3.setRotationPoint(-17f, 18f, 7f);
    this.side3.setTextureSize(128, 64);
    this.side3.mirror = true;
    setRotation(this.side3, 0f, 0.4014257f, 0f);

    this.side4 = new ModelRenderer(this, 24, 28);
    this.side4.addBox(-0.1f, -3f, -8f, 1, 3, 16);
    this.side4.setRotationPoint(-7.5f, 18f, 17.5f);
    this.side4.setTextureSize(128, 64);
    this.side4.mirror = true;
    setRotation(this.side4, 0f, 1.186824f, 0f);

    this.side5 = new ModelRenderer(this, 24, 28);
    this.side5.addBox(-0.1f, -3f, -8f, 1, 3, 16);
    this.side5.setRotationPoint(7.5f, 18f, -17.5f);
    this.side5.setTextureSize(128, 64);
    this.side5.mirror = true;
    setRotation(this.side5, 0f, -1.972222f, 0f);

    this.side6 = new ModelRenderer(this, 24, 28);
    this.side6.addBox(0.1f, -3f, -8f, 1, 3, 16);
    this.side6.setRotationPoint(17f, 18f, 7f);
    this.side6.setTextureSize(128, 64);
    this.side6.mirror = true;
    setRotation(this.side6, 0f, -0.418879f, 0f);

    this.side7 = new ModelRenderer(this, 24, 28);
    this.side7.addBox(-0.1f, -3f, -7.7f, 1, 3, 16);
    this.side7.setRotationPoint(17f, 18f, -7f);
    this.side7.setTextureSize(128, 64);
    this.side7.mirror = true;
    setRotation(this.side7, 0f, 0.4014257f, 0f);

    this.side8 = new ModelRenderer(this, 24, 28);
    this.side8.addBox(-1f, -3f, -8f, 1, 3, 16);
    this.side8.setRotationPoint(-17f, 18f, -7f);
    this.side8.setTextureSize(128, 64);
    this.side8.mirror = true;
    setRotation(this.side8, 0f, -0.4014257f, 0f);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  /**
   * Affiche le modèle.
   * 
   * @param scale l'échelle d'affichage
   */
  public void renderModel(float scale) {
    GL11.glPushMatrix();
    GL11.glScalef(1.5f, 1, 1.5f);
    this.middle.render(scale);
    this.chain1.render(scale);
    this.chain2.render(scale);
    GL11.glPopMatrix();
    this.candleA1.render(scale);
    this.candleA2.render(scale);
    this.candleB1.render(scale);
    this.candleB2.render(scale);
    this.candleC1.render(scale);
    this.candleC2.render(scale);
    this.candleD1.render(scale);
    this.candleD2.render(scale);
    this.plank1.render(scale);
    this.plank2.render(scale);
    this.plank3.render(scale);
    this.plank4.render(scale);
    this.candleH1.render(scale);
    this.candleH2.render(scale);
    this.candleE1.render(scale);
    this.candleE2.render(scale);
    this.candleF2.render(scale);
    this.candleF1.render(scale);
    this.candleG2.render(scale);
    this.candleG1.render(scale);
    this.side1.render(scale);
    this.side2.render(scale);
    this.side3.render(scale);
    this.side4.render(scale);
    this.side5.render(scale);
    this.side6.render(scale);
    this.side7.render(scale);
    this.side8.render(scale);
  }
}
