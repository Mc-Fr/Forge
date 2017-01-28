package net.mcfr.decoration.furniture.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelWoodenArmChair extends ModelBase implements ArmChairModel {
  private ModelRenderer base;
  private ModelRenderer side1;
  private ModelRenderer side2;
  private ModelRenderer back;

  public ModelWoodenArmChair() {
    this.textureWidth = 128;
    this.textureHeight = 64;

    this.base = new ModelRenderer(this, 0, 24);
    this.base.addBox(0, 0, 0, 16, 8, 12);
    this.base.setRotationPoint(-8, 16, -6);
    this.base.setTextureSize(64, 32);
    this.base.mirror = true;
    setRotation(this.base, 0, 0, 0);

    this.side1 = new ModelRenderer(this, 80, 30);
    this.side1.addBox(0, 0, 0, 3, 12, 16);
    this.side1.setRotationPoint(-11, 12, -8);
    this.side1.setTextureSize(64, 32);
    this.side1.mirror = true;
    setRotation(this.side1, 0, 0, 0);

    this.side2 = new ModelRenderer(this, 80, 30);
    this.side2.addBox(0, 0, 0, 3, 12, 16);
    this.side2.setRotationPoint(8, 12, -8);
    this.side2.setTextureSize(64, 32);
    this.side2.mirror = true;
    setRotation(this.side2, 0, 0, 0);

    this.back = new ModelRenderer(this, 0, 0);
    this.back.addBox(0, 0, 0, 16, 20, 2);
    this.back.setRotationPoint(-8, 4, 6);
    this.back.setTextureSize(64, 32);
    this.back.mirror = true;
    setRotation(this.back, 0, 0, 0);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  @Override
  public void renderModel(float scale) {
    this.base.render(scale);
    this.side1.render(scale);
    this.side2.render(scale);
    this.back.render(scale);
  }
}
