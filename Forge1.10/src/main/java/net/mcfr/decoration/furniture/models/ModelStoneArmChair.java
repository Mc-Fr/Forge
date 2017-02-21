package net.mcfr.decoration.furniture.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Modèle du fauteuil en pierre.
 *
 * @author Mc-Fr
 */
public class ModelStoneArmChair extends ModelBase implements ArmChairModel {
  private ModelRenderer side1;
  private ModelRenderer side2;
  private ModelRenderer backMiddle;
  private ModelRenderer backBottom;
  private ModelRenderer backRight;
  private ModelRenderer backLeft;
  private ModelRenderer backBottom2;
  private ModelRenderer base;
  private ModelRenderer skinBottom;
  private ModelRenderer skinTop;

  public ModelStoneArmChair() {
    this.textureWidth = 128;
    this.textureHeight = 64;

    this.side1 = new ModelRenderer(this, 80, 30);
    this.side1.addBox(0F, 0F, 0F, 3, 12, 16);
    this.side1.setRotationPoint(-11F, 12F, -8F);
    this.side1.setTextureSize(128, 64);
    this.side1.mirror = true;
    this.setRotation(this.side1, 0F, 0F, 0F);
    this.side2 = new ModelRenderer(this, 80, 30);
    this.side2.addBox(0F, 0F, 0F, 3, 12, 16);
    this.side2.setRotationPoint(8F, 12F, -8F);
    this.side2.setTextureSize(128, 64);
    this.side2.mirror = true;
    this.setRotation(this.side2, 0F, 0F, 0F);
    this.backMiddle = new ModelRenderer(this, 60, 9);
    this.backMiddle.addBox(0F, 0F, 0F, 6, 6, 0);
    this.backMiddle.setRotationPoint(-3F, 6F, 7F);
    this.backMiddle.setTextureSize(128, 64);
    this.backMiddle.mirror = true;
    this.setRotation(this.backMiddle, 0F, 0F, 0F);
    this.backBottom = new ModelRenderer(this, 0, 0);
    this.backBottom.addBox(0F, 0F, 0F, 16, 12, 2);
    this.backBottom.setRotationPoint(-8F, 12F, 6F);
    this.backBottom.setTextureSize(128, 64);
    this.backBottom.mirror = true;
    this.setRotation(this.backBottom, 0F, 0F, 0F);
    this.backRight = new ModelRenderer(this, 80, 10);
    this.backRight.addBox(0F, 0F, 0F, 5, 6, 2);
    this.backRight.setRotationPoint(-8F, 6F, 6F);
    this.backRight.setTextureSize(128, 64);
    this.backRight.mirror = true;
    this.setRotation(this.backRight, 0F, 0F, 0F);
    this.backLeft = new ModelRenderer(this, 50, 0);
    this.backLeft.addBox(0F, 0F, 0F, 16, 5, 2);
    this.backLeft.setRotationPoint(-8F, 1F, 6F);
    this.backLeft.setTextureSize(128, 64);
    this.backLeft.mirror = true;
    this.setRotation(this.backLeft, 0F, 0F, 0F);
    this.backBottom2 = new ModelRenderer(this, 40, 10);
    this.backBottom2.addBox(0F, 0F, 0F, 5, 6, 2);
    this.backBottom2.setRotationPoint(3F, 6F, 6F);
    this.backBottom2.setTextureSize(128, 64);
    this.backBottom2.mirror = true;
    this.setRotation(this.backBottom2, 0F, 0F, 0F);
    this.base = new ModelRenderer(this, 0, 24);
    this.base.addBox(0F, 0F, 0F, 16, 8, 12);
    this.base.setRotationPoint(-8F, 16F, -6F);
    this.base.setTextureSize(128, 64);
    this.base.mirror = true;
    this.setRotation(this.base, 0F, 0F, 0F);
    this.skinBottom = new ModelRenderer(this, 35, 49);
    this.skinBottom.addBox(0F, 0F, 0F, 14, 8, 0);
    this.skinBottom.setRotationPoint(-7F, 15.5F, -6.5F);
    this.skinBottom.setTextureSize(128, 64);
    this.skinBottom.mirror = true;
    this.setRotation(this.skinBottom, 0F, 0F, 0F);
    this.skinTop = new ModelRenderer(this, -10, 47);
    this.skinTop.addBox(0F, 0F, 0F, 14, 1, 12);
    this.skinTop.setRotationPoint(-7F, 15.4F, -6.5F);
    this.skinTop.setTextureSize(128, 64);
    this.skinTop.mirror = false;
    this.setRotation(this.skinTop, 0F, 0F, 0F);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  @Override
  public void renderModel(float f) {
    this.side1.render(f);
    this.side2.render(f);
    this.backMiddle.render(f);
    this.backBottom.render(f);
    this.backRight.render(f);
    this.backLeft.render(f);
    this.backBottom2.render(f);
    this.base.render(f);
    this.skinBottom.render(f);
    this.skinTop.render(f);
  }
}
