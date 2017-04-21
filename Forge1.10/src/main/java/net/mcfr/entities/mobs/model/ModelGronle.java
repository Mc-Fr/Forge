package net.mcfr.entities.mobs.model;

import net.mcfr.entities.mobs.gender.EntityGendered;
import net.mcfr.entities.mobs.gender.Genders;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelGronle extends ModelBase {
  public ModelRenderer body;
  public ModelRenderer head;
  public ModelRenderer horn;
  public ModelRenderer leftArm1;
  public ModelRenderer leftArm2;
  public ModelRenderer leftHorn;
  public ModelRenderer leftLeg1;
  public ModelRenderer leftLeg2;
  public ModelRenderer leftLeg3;
  public ModelRenderer neck;
  public ModelRenderer rightArm1;
  public ModelRenderer rightArm2;
  public ModelRenderer rightHorn;
  public ModelRenderer rightLeg1;
  public ModelRenderer rightLeg2;
  public ModelRenderer rightLeg3;
  public boolean isChild;

  private float headYaw;
  private float headPitch;

  public ModelGronle() {
    this.textureHeight = 128;
    this.textureWidth = 128;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;

    this.body = new ModelRenderer(this, 0, 0);
    this.head = new ModelRenderer(this, 0, 0);
    this.horn = new ModelRenderer(this, 0, 0);
    this.leftArm1 = new ModelRenderer(this, 0, 0);
    this.leftArm2 = new ModelRenderer(this, 0, 0);
    this.leftHorn = new ModelRenderer(this, 0, 0);
    this.leftLeg1 = new ModelRenderer(this, 0, 0);
    this.leftLeg2 = new ModelRenderer(this, 0, 0);
    this.leftLeg3 = new ModelRenderer(this, 0, 0);
    this.neck = new ModelRenderer(this, 0, 0);
    this.rightArm1 = new ModelRenderer(this, 0, 0);
    this.rightArm2 = new ModelRenderer(this, 0, 0);
    this.rightHorn = new ModelRenderer(this, 0, 0);
    this.rightLeg1 = new ModelRenderer(this, 0, 0);
    this.rightLeg2 = new ModelRenderer(this, 0, 0);
    this.rightLeg3 = new ModelRenderer(this, 0, 0);

    // #f:0
    this.body.addBox(-5.40F, -6.90F, -14.69F, 10, 13, 29);  this.body.setRotationPoint(0.00F, -3.41F, 3.24F);
    this.head.addBox(-2.70F, -5.74F, -8.33F, 5, 6, 11); this.head.setRotationPoint(0.00F, -17.70F, 0.02F);
    this.horn.addBox(-7.50F, -4.75F, -0.03F, 15, 2, 3); this.horn.setRotationPoint(0.00F, 0.00F, 0.00F);
    this.leftArm1.addBox(-1.50F, -2.07F, -1.64F, 3, 25, 3); this.leftArm1.setRotationPoint(6.64F, -2.30F, -9.71F);
    this.leftArm2.addBox(-1.20F, -0.41F, -1.54F, 2, 7, 3);  this.leftArm2.setRotationPoint(0.00F, 23.10F, 0.09F);
    this.leftHorn.addBox(5.10F, -4.75F, -3.03F, 2, 2, 3); this.leftHorn.setRotationPoint(0.00F, 0.00F, 0.00F);
    this.leftLeg1.addBox(-1.80F, -2.87F, -3.48F, 3, 13, 6); this.leftLeg1.setRotationPoint(6.98F, -0.01F, 7.58F);
    this.leftLeg2.addBox(-1.50F, -1.60F, -1.63F, 3, 15, 3); this.leftLeg2.setRotationPoint(0.00F, 9.56F, -0.71F);
    this.leftLeg3.addBox(-1.20F, -0.83F, -1.57F, 2, 7, 3);  this.leftLeg3.setRotationPoint(0.00F, 12.73F, -0.04F);
    this.neck.addBox(-2.10F, -18.18F, -2.56F, 4, 19, 4);  this.neck.setRotationPoint(0.00F, -6.07F, -11.88F);
    this.rightArm1.addBox(-1.50F, -2.07F, -1.64F, 3, 25, 3);  this.rightArm1.setRotationPoint(-6.64F, -2.30F, -9.71F);
    this.rightArm2.addBox(-1.20F, -0.41F, -1.54F, 2, 7, 3); this.rightArm2.setRotationPoint(0.00F, 23.10F, 0.09F);
    this.rightHorn.addBox(-7.50F, -4.75F, -3.03F, 2, 2, 3); this.rightHorn.setRotationPoint(0.00F, 0.00F, 0.00F);
    this.rightLeg1.addBox(-1.80F, -2.87F, -3.48F, 3, 13, 6);  this.rightLeg1.setRotationPoint(-6.98F, -0.01F, 7.58F);
    this.rightLeg2.addBox(-1.50F, -1.60F, -1.63F, 3, 15, 3);  this.rightLeg2.setRotationPoint(0.00F, 9.56F, -0.71F);
    this.rightLeg3.addBox(-1.20F, -0.83F, -1.57F, 2, 7, 3); this.rightLeg3.setRotationPoint(0.00F, 12.73F, -0.04F);
    // #f:1

    // Enfantillages
    this.neck.addChild(this.head);
    this.head.addChild(this.horn);
    this.body.addChild(this.leftArm1);
    this.leftArm1.addChild(this.leftArm2);
    this.head.addChild(this.leftHorn);
    this.body.addChild(this.leftLeg1);
    this.leftLeg1.addChild(this.leftLeg2);
    this.leftLeg2.addChild(this.leftLeg3);
    this.body.addChild(this.neck);
    this.body.addChild(this.rightArm1);
    this.rightArm1.addChild(this.rightArm2);
    this.head.addChild(this.rightHorn);
    this.body.addChild(this.rightLeg1);
    this.rightLeg1.addChild(this.rightLeg2);
    this.rightLeg2.addChild(this.rightLeg3);
  }

  /**
   * Sets the models various rotation angles then renders the model.
   */
  @Override
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    if (((EntityGendered) entityIn).isChild()) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
      GlStateManager.translate(0.0F, 35.0F * scale, 0.0F);
      this.body.render(scale);
      GlStateManager.popMatrix();
    } else if (((EntityGendered) entityIn).getGender() == Genders.FEMALE) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.9F, 0.9F, 0.9F);
      GlStateManager.translate(0.0F, 3.0F * scale, 0.0F);
      this.body.render(scale);
      GlStateManager.popMatrix();
    } else {
      this.body.render(scale);
    }
  }

  /**
   * Interpolate the head rotation angles to make the head movement softer
   */
  private void interpolateHeadAngles(float headPitch, float headYaw, float speed) {
    if (this.headPitch - headPitch > 0.2F) {
      this.headPitch -= speed;
    } else if (this.headPitch - headPitch < -0.2F) {
      this.headPitch += speed;
    }

    if (this.headYaw - headYaw * 0.4F > 0.2F) {
      this.headYaw -= speed;
    } else if (this.headYaw - headYaw * 0.4F < -0.2F) {
      this.headYaw += speed;
    }
  }

  /**
   * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms and
   * legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how "far" arms
   * and legs can swing at most.
   */
  @Override
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor,
      Entity entityIn) {
    float degToRad = (float) Math.PI / 180F;

    // Pliage du modèle
    this.body.rotateAngleX = -1.779F * degToRad;
    this.head.rotateAngleX = -1.488F * degToRad;
    this.horn.rotateAngleX = 8.414F * degToRad;
    this.leftArm1.rotateAngleX = -3.63F * degToRad;
    this.leftArm2.rotateAngleX = 7.195F * degToRad;
    this.leftHorn.rotateAngleX = 8.414F * degToRad;
    this.leftLeg1.rotateAngleX = 26.671F * degToRad;
    this.leftLeg2.rotateAngleX = -43.216F * degToRad;
    this.leftLeg3.rotateAngleX = 20.081F * degToRad;
    this.neck.rotateAngleX = 4.087F * degToRad;
    this.rightArm1.rotateAngleX = -3.63F * degToRad;
    this.rightArm2.rotateAngleX = 7.195F * degToRad;
    this.rightHorn.rotateAngleX = 8.414F * degToRad;
    this.rightLeg1.rotateAngleX = 26.671F * degToRad;
    this.rightLeg2.rotateAngleX = -43.216F * degToRad;
    this.rightLeg3.rotateAngleX = 20.081F * degToRad;

    // Calcul de l'animation
    interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);
  }
}
