package net.mcfr.craftsmanship.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Modèle de la scie circulaire.
 *
 * @author Mc-Fr
 */
public class ModelCircularSaw extends ModelBase {
  private ModelRenderer foot1;
  private ModelRenderer foot2;
  private ModelRenderer foot3;
  private ModelRenderer foot4;
  private ModelRenderer side1;
  private ModelRenderer side2;
  private ModelRenderer side3;
  private ModelRenderer side4;
  private ModelRenderer sideLow1;
  private ModelRenderer sideLow2;
  private ModelRenderer axle;
  private ModelRenderer saw;

  public ModelCircularSaw() {
    this.textureWidth = 64;
    this.textureHeight = 32;

    this.foot1 = new ModelRenderer(this, 0, 0);
    this.foot1.addBox(0, 0, 0, 2, 10, 2);
    this.foot1.setRotationPoint(-7, 14, -5);
    this.foot1.setTextureSize(64, 32);
    this.foot1.mirror = true;
    setRotation(this.foot1, 0, 0, 0);
    this.foot2 = new ModelRenderer(this, 0, 0);
    this.foot2.addBox(0, 0, 0, 2, 10, 2);
    this.foot2.setRotationPoint(-7, 14, 3);
    this.foot2.setTextureSize(64, 32);
    this.foot2.mirror = true;
    setRotation(this.foot2, 0, 0, 0);
    this.foot3 = new ModelRenderer(this, 8, 0);
    this.foot3.addBox(0, 0, 0, 2, 7, 2);
    this.foot3.setRotationPoint(5, 17, -5);
    this.foot3.setTextureSize(64, 32);
    this.foot3.mirror = true;
    setRotation(this.foot3, 0, 0, 0);
    this.foot4 = new ModelRenderer(this, 8, 0);
    this.foot4.addBox(0, 0, 0, 2, 7, 2);
    this.foot4.setRotationPoint(5, 17, 3);
    this.foot4.setTextureSize(64, 32);
    this.foot4.mirror = true;
    setRotation(this.foot4, 0, 0, 0);
    this.side1 = new ModelRenderer(this, 0, 28);
    this.side1.addBox(0, 0, 0, 16, 1, 3);
    this.side1.setRotationPoint(-8, 13, -6);
    this.side1.setTextureSize(64, 32);
    this.side1.mirror = true;
    setRotation(this.side1, 0, 0, 0.2617994f);
    this.side2 = new ModelRenderer(this, 0, 28);
    this.side2.addBox(0, 0, 0, 16, 1, 3);
    this.side2.setRotationPoint(-8, 13, 3);
    this.side2.setTextureSize(64, 32);
    this.side2.mirror = true;
    setRotation(this.side2, 0, 0, 0.2617994f);
    this.side3 = new ModelRenderer(this, 46, 0);
    this.side3.addBox(0, 0, 0, 3, 1, 6);
    this.side3.setRotationPoint(4, 17, -3);
    this.side3.setTextureSize(64, 32);
    this.side3.mirror = true;
    setRotation(this.side3, 0, 0, 0);
    this.side4 = new ModelRenderer(this, 46, 0);
    this.side4.addBox(0, 0, 0, 3, 1, 6);
    this.side4.setRotationPoint(-8, 14, -3);
    this.side4.setTextureSize(64, 32);
    this.side4.mirror = true;
    setRotation(this.side4, 0, 0, 0);
    this.sideLow1 = new ModelRenderer(this, 0, 20);
    this.sideLow1.addBox(0, 0, 0, 1, 2, 6);
    this.sideLow1.setRotationPoint(-6, 18, -3);
    this.sideLow1.setTextureSize(64, 32);
    this.sideLow1.mirror = true;
    setRotation(this.sideLow1, 0, 0, 0);
    this.sideLow2 = new ModelRenderer(this, 0, 20);
    this.sideLow2.addBox(0, 0, 0, 1, 2, 6);
    this.sideLow2.setRotationPoint(5, 20, -3);
    this.sideLow2.setTextureSize(64, 32);
    this.sideLow2.mirror = true;
    setRotation(this.sideLow2, 0, 0, 0);
    this.axle = new ModelRenderer(this, 46, 11);
    this.axle.addBox(0, 0, 0, 1, 1, 8);
    this.axle.setRotationPoint(-0.5f, 16, -4);
    this.axle.setTextureSize(64, 32);
    this.axle.mirror = true;
    setRotation(this.axle, 0, 0, 0);
    this.saw = new ModelRenderer(this, 21, 12);
    this.saw.addBox(-4, -4, 0, 8, 8, 0);
    this.saw.setRotationPoint(0, 16.5f, 0);
    this.saw.setTextureSize(64, 32);
    this.saw.mirror = true;
    setRotation(this.saw, 0, 0, 0);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  /**
   * Affiche le modèle.
   * 
   * @param scale l'échelle
   */
  public void renderModel(float scale) {
    this.foot1.render(scale);
    this.foot2.render(scale);
    this.foot3.render(scale);
    this.foot4.render(scale);
    this.side1.render(scale);
    this.side2.render(scale);
    this.side3.render(scale);
    this.side4.render(scale);
    this.sideLow1.render(scale);
    this.sideLow2.render(scale);
    this.axle.render(scale);
    this.saw.render(scale);
  }
}
