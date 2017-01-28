package net.mcfr.decoration.lighting.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelChandelier extends ModelBase {
  private ModelRenderer plank1;
  private ModelRenderer plank2;
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

  public ModelChandelier() {
    this.textureWidth = 128;
    this.textureHeight = 64;

    this.plank1 = new ModelRenderer(this, 5, 10);
    this.plank1.addBox(-2, 0, -16, 4, 2, 32);
    this.plank1.setRotationPoint(0, 16, 0);
    this.plank1.setTextureSize(128, 64);
    this.plank1.mirror = true;
    setRotation(this.plank1, 0, 1.570796f, 0);

    this.plank2 = new ModelRenderer(this, 5, 10);
    this.plank2.addBox(-2, 0, -16, 4, 2, 32);
    this.plank2.setRotationPoint(0, 16, 0);
    this.plank2.setTextureSize(128, 64);
    this.plank2.mirror = true;
    setRotation(this.plank2, 0, 0, 0);

    this.middle = new ModelRenderer(this, 0, 50);
    this.middle.addBox(-2.5f, 0, -2.5f, 5, 3, 5);
    this.middle.setRotationPoint(0, 15.5f, 0);
    this.middle.setTextureSize(128, 64);
    this.middle.mirror = true;
    setRotation(this.middle, 0, 0.7853982f, 0);

    this.chain1 = new ModelRenderer(this, 0, 0);
    this.chain1.addBox(-8, 0, 0, 16, 16, 0);
    this.chain1.setRotationPoint(0, 1, 0);
    this.chain1.setTextureSize(128, 64);
    this.chain1.mirror = true;
    setRotation(this.chain1, 0, 2.3572325f, 0);

    this.chain2 = new ModelRenderer(this, 0, 0);
    this.chain2.addBox(0, 0, -8, 0, 16, 16);
    this.chain2.setRotationPoint(0, 1, 0);
    this.chain2.setTextureSize(128, 64);
    this.chain2.mirror = true;
    setRotation(this.chain2, 0, 2.3572325f, 0);

    this.candleA1 = new ModelRenderer(this, 50, 0);
    this.candleA1.addBox(-2, -10, 0, 4, 10, 0);
    this.candleA1.setRotationPoint(13, 16, 0);
    this.candleA1.setTextureSize(128, 64);
    this.candleA1.mirror = true;
    setRotation(this.candleA1, 0, 0, 0);

    this.candleA2 = new ModelRenderer(this, 50, 0);
    this.candleA2.addBox(-2, -10, 0, 4, 10, 0);
    this.candleA2.setRotationPoint(13, 16, 0);
    this.candleA2.setTextureSize(128, 64);
    this.candleA2.mirror = true;
    setRotation(this.candleA2, 0, 1.570796f, 0);

    this.candleB1 = new ModelRenderer(this, 50, 0);
    this.candleB1.addBox(-2, -10, 0, 4, 10, 0);
    this.candleB1.setRotationPoint(-13, 16, 0);
    this.candleB1.setTextureSize(128, 64);
    this.candleB1.mirror = true;
    setRotation(this.candleB1, 0, 0, 0);

    this.candleB2 = new ModelRenderer(this, 50, 0);
    this.candleB2.addBox(-2, -10, 0, 4, 10, 0);
    this.candleB2.setRotationPoint(-13, 16, 0);
    this.candleB2.setTextureSize(128, 64);
    this.candleB2.mirror = true;
    setRotation(this.candleB2, 0, 1.570796f, 0);

    this.candleC1 = new ModelRenderer(this, 50, 0);
    this.candleC1.addBox(-2, -10, 0, 4, 10, 0);
    this.candleC1.setRotationPoint(0, 16, 13);
    this.candleC1.setTextureSize(128, 64);
    this.candleC1.mirror = true;
    setRotation(this.candleC1, 0, 0, 0);

    this.candleC2 = new ModelRenderer(this, 50, 0);
    this.candleC2.addBox(-2, -10, 0, 4, 10, 0);
    this.candleC2.setRotationPoint(0, 16, 13);
    this.candleC2.setTextureSize(128, 64);
    this.candleC2.mirror = true;
    setRotation(this.candleC2, 0, 1.570796f, 0);

    this.candleD1 = new ModelRenderer(this, 50, 0);
    this.candleD1.addBox(-2, -10, 0, 4, 10, 0);
    this.candleD1.setRotationPoint(0, 16, -13);
    this.candleD1.setTextureSize(128, 64);
    this.candleD1.mirror = true;
    setRotation(this.candleD1, 0, 1.570796f, 0);

    this.candleD2 = new ModelRenderer(this, 50, 0);
    this.candleD2.addBox(-2, -10, 0, 4, 10, 0);
    this.candleD2.setRotationPoint(0, 16, -13);
    this.candleD2.setTextureSize(128, 64);
    this.candleD2.mirror = true;
    setRotation(this.candleD2, 0, 0, 0);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

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
  }
}
