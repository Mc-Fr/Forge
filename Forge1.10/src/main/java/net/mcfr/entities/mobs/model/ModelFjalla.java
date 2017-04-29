package net.mcfr.entities.mobs.model;

import net.mcfr.entities.mobs.gender.EntityGendered;
import net.mcfr.entities.mobs.gender.Genders;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * Modèle du Fjalla.
 *
 * @author Mc-Fr
 */
public class ModelFjalla extends ModelBase {
  public ModelRenderer abdomen;
  public ModelRenderer head;
  public ModelRenderer leftArm1;
  public ModelRenderer leftArm2;
  public ModelRenderer leftArm3;
  public ModelRenderer leftLeg1;
  public ModelRenderer leftLeg2;
  public ModelRenderer leftLeg3;
  public ModelRenderer rightArm1;
  public ModelRenderer rightArm2;
  public ModelRenderer rightArm3;
  public ModelRenderer rightLeg1;
  public ModelRenderer rightLeg2;
  public ModelRenderer rightLeg3;
  public ModelRenderer tail1;
  public ModelRenderer tail2;
  public ModelRenderer tail3;
  public ModelRenderer torso;
  public boolean isChild;

  private float headYaw;
  private float headPitch;

  public ModelFjalla() {
    this.textureHeight = 128;
    this.textureWidth = 128;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;

    this.abdomen = new ModelRenderer(this, 0, 0);
    this.head = new ModelRenderer(this, 0, 0);
    this.leftArm1 = new ModelRenderer(this, 0, 0);
    this.leftArm2 = new ModelRenderer(this, 0, 0);
    this.leftArm3 = new ModelRenderer(this, 0, 0);
    this.leftLeg1 = new ModelRenderer(this, 0, 0);
    this.leftLeg2 = new ModelRenderer(this, 0, 0);
    this.leftLeg3 = new ModelRenderer(this, 0, 0);
    this.rightArm1 = new ModelRenderer(this, 0, 0);
    this.rightArm2 = new ModelRenderer(this, 0, 0);
    this.rightArm3 = new ModelRenderer(this, 0, 0);
    this.rightLeg1 = new ModelRenderer(this, 0, 0);
    this.rightLeg2 = new ModelRenderer(this, 0, 0);
    this.rightLeg3 = new ModelRenderer(this, 0, 0);
    this.tail1 = new ModelRenderer(this, 0, 0);
    this.tail2 = new ModelRenderer(this, 0, 0);
    this.tail3 = new ModelRenderer(this, 0, 0);
    this.torso = new ModelRenderer(this, 0, 0);

    // #f:0
    this.abdomen.addBox(-5.10F, -6.60F, -8.10F, 10, 13, 16);  this.abdomen.setRotationPoint(0.00F, 6.82F, 6.64F);
    this.head.addBox(-3.60F, -4.55F, -10.27F, 7, 8, 11);  this.head.setRotationPoint(0.00F, -3.03F, -11.84F);
    this.leftArm1.addBox(-2.10F, -3.58F, -3.59F, 4, 15, 7); this.leftArm1.setRotationPoint(6.98F, -0.01F, -6.67F);
    this.leftArm2.addBox(-1.80F, -1.93F, -2.37F, 3, 7, 4);  this.leftArm2.setRotationPoint(0.00F, 11.17F, -0.17F);
    this.leftArm3.addBox(-1.50F, -1.34F, -3.78F, 3, 3, 6);  this.leftArm3.setRotationPoint(0.00F, 5.00F, 0.05F);
    this.leftLeg1.addBox(-2.10F, -2.60F, -3.67F, 4, 12, 7); this.leftLeg1.setRotationPoint(6.58F, 1.41F, 2.26F);
    this.leftLeg2.addBox(-1.80F, -1.16F, -2.60F, 3, 7, 4);  this.leftLeg2.setRotationPoint(0.00F, 8.21F, 0.23F);
    this.leftLeg3.addBox(-1.50F, -0.57F, -3.95F, 3, 3, 6);  this.leftLeg3.setRotationPoint(0.00F, 5.26F, -0.18F);
    this.rightArm1.addBox(-2.10F, -3.58F, -3.59F, 4, 15, 7);  this.rightArm1.setRotationPoint(-6.98F, -0.01F, -6.67F);
    this.rightArm2.addBox(-1.80F, -1.93F, -2.37F, 3, 7, 4); this.rightArm2.setRotationPoint(0.00F, 11.17F, -0.17F);
    this.rightArm3.addBox(-1.50F, -1.34F, -3.78F, 3, 3, 6); this.rightArm3.setRotationPoint(0.00F, 5.00F, 0.05F);
    this.rightLeg1.addBox(-2.10F, -2.60F, -3.67F, 4, 12, 7);  this.rightLeg1.setRotationPoint(-6.58F, 1.41F, 2.26F);
    this.rightLeg2.addBox(-1.80F, -1.16F, -2.60F, 3, 7, 4); this.rightLeg2.setRotationPoint(0.00F, 8.21F, 0.23F);
    this.rightLeg3.addBox(-1.50F, -0.57F, -3.95F, 3, 3, 6); this.rightLeg3.setRotationPoint(0.00F, 5.26F, -0.18F);
    this.tail1.addBox(-1.80F, -1.35F, -0.90F, 3, 3, 8); this.tail1.setRotationPoint(0.00F, -3.40F, 7.37F);
    this.tail2.addBox(-1.80F, -1.06F, -0.35F, 3, 3, 8); this.tail2.setRotationPoint(0.00F, -0.38F, 6.82F);
    this.tail3.addBox(-1.80F, -1.10F, -0.14F, 3, 3, 8); this.tail3.setRotationPoint(0.00F, -0.02F, 7.33F);
    this.torso.addBox(-5.70F, -7.79F, -12.61F, 11, 15, 15); this.torso.setRotationPoint(0.00F, -0.35F, -5.95F);
    // #f:1

    // Enfantillages
    this.torso.addChild(this.head);
    this.torso.addChild(this.leftArm1);
    this.leftArm1.addChild(this.leftArm2);
    this.leftArm2.addChild(this.leftArm3);
    this.abdomen.addChild(this.leftLeg1);
    this.leftLeg1.addChild(this.leftLeg2);
    this.leftLeg2.addChild(this.leftLeg3);
    this.torso.addChild(this.rightArm1);
    this.rightArm1.addChild(this.rightArm2);
    this.rightArm2.addChild(this.rightArm3);
    this.abdomen.addChild(this.rightLeg1);
    this.rightLeg1.addChild(this.rightLeg2);
    this.rightLeg2.addChild(this.rightLeg3);
    this.abdomen.addChild(this.tail1);
    this.tail1.addChild(this.tail2);
    this.tail2.addChild(this.tail3);
    this.abdomen.addChild(this.torso);

  }

  @Override
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    if (((EntityGendered) entityIn).isChild()) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
      GlStateManager.translate(0.0F, 35.0F * scale, 0.0F);
      this.abdomen.render(scale);
      GlStateManager.popMatrix();
    }
    else if (((EntityGendered) entityIn).getGender() == Genders.FEMALE) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.9F, 0.9F, 0.9F);
      GlStateManager.translate(0.0F, 3.0F * scale, 0.0F);
      this.abdomen.render(scale);
      GlStateManager.popMatrix();
    }
    else {
      this.abdomen.render(scale);
    }
  }

  /**
   * Interpole les angles de rotation de la tête pour rendre le mouvement plus doux.
   */
  private void interpolateHeadAngles(float headPitch, float headYaw, float speed) {
    if (this.headPitch - headPitch > 0.2F) {
      this.headPitch -= speed;
    }
    else if (this.headPitch - headPitch < -0.2F) {
      this.headPitch += speed;
    }

    if (this.headYaw - headYaw * 0.4F > 0.2F) {
      this.headYaw -= speed;
    }
    else if (this.headYaw - headYaw * 0.4F < -0.2F) {
      this.headYaw += speed;
    }
  }

  @Override
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor,
      Entity entityIn) {
    float degToRad = (float) Math.PI / 180F;

    // Pliage du modèle
    this.abdomen.rotateAngleX = 3.339F * degToRad;
    this.head.rotateAngleX = 7.227F * degToRad;
    this.leftArm1.rotateAngleX = 1.825F * degToRad;
    this.leftArm2.rotateAngleX = 4.448F * degToRad;
    this.leftArm3.rotateAngleX = -0.552F * degToRad;
    this.leftLeg1.rotateAngleX = -10.273F * degToRad;
    this.leftLeg2.rotateAngleX = 13.62F * degToRad;
    this.leftLeg3.rotateAngleX = -5.648F * degToRad;
    this.rightArm1.rotateAngleX = 1.825F * degToRad;
    this.rightArm2.rotateAngleX = 4.448F * degToRad;
    this.rightArm3.rotateAngleX = -0.552F * degToRad;
    this.rightLeg1.rotateAngleX = -10.273F * degToRad;
    this.rightLeg2.rotateAngleX = 13.62F * degToRad;
    this.rightLeg3.rotateAngleX = -5.648F * degToRad;
    this.tail1.rotateAngleX = -51.51F * degToRad;
    this.tail2.rotateAngleX = 8.33F * degToRad;
    this.tail3.rotateAngleX = 24.291F * degToRad;
    this.torso.rotateAngleX = -8.915F * degToRad;

    // Calcul de l'animation
    interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);
  }
}
