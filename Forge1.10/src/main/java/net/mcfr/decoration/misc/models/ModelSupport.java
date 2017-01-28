package net.mcfr.decoration.misc.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelSupport extends ModelBase {
  private ModelRenderer topPlank;
  private ModelRenderer verticalPlank;
  private ModelRenderer diagonalPlank;

  public ModelSupport() {
    this.textureWidth = 64;
    this.textureHeight = 32;

    this.topPlank = new ModelRenderer(this, 0, 0);
    this.topPlank.addBox(-2, 0, 0, 4, 2, 16);
    this.topPlank.setRotationPoint(0, 8, -8);
    this.topPlank.setTextureSize(64, 32);
    setRotation(this.topPlank, 0, 0, 0);

    this.verticalPlank = new ModelRenderer(this, 0, 0);
    this.verticalPlank.addBox(-2, 0, 0, 4, 14, 2);
    this.verticalPlank.setRotationPoint(0, 10, 6);
    this.verticalPlank.setTextureSize(64, 32);
    this.verticalPlank.mirror = true;
    setRotation(this.verticalPlank, 0, 0, 0);

    this.diagonalPlank = new ModelRenderer(this, 0, 0);
    this.diagonalPlank.addBox(-1, -6, -0.5f, 2, 12, 2);
    this.diagonalPlank.setRotationPoint(0, 14, 2);
    this.diagonalPlank.setTextureSize(64, 32);
    this.diagonalPlank.mirror = true;
    setRotation(this.diagonalPlank, 0.7853982f, 0, 0);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  public void renderModel(float scale) {
    this.topPlank.render(scale);
    this.verticalPlank.render(scale);
    this.diagonalPlank.render(scale);
  }
}
