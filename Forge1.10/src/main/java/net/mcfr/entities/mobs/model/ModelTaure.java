package net.mcfr.entities.mobs.model;

import net.mcfr.entities.mobs.gender.EntityGendered;
import net.mcfr.entities.mobs.gender.Genders;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * Modèle du Taure.
 *
 * @author Mc-Fr
 */
public class ModelTaure extends ModelBase {
  public ModelRenderer abdomen;
  public ModelRenderer head;
  public ModelRenderer leftArm1;
  public ModelRenderer leftArm2;
  public ModelRenderer leftLeg1;
  public ModelRenderer leftLeg2;
  public ModelRenderer leftTooth;
  public ModelRenderer rightArm1;
  public ModelRenderer rightArm2;
  public ModelRenderer rightLeg1;
  public ModelRenderer rightLeg2;
  public ModelRenderer rightTooth;
  public ModelRenderer tail;
  public ModelRenderer torso;
  public boolean isChild;

  private float headYaw;
  private float headPitch;

  public ModelTaure() {
    this.textureHeight = 128;
    this.textureWidth = 128;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;

    this.abdomen = new ModelRenderer(this, 0, 0);
    this.head = new ModelRenderer(this, 0, 0);
    this.leftArm1 = new ModelRenderer(this, 0, 0);
    this.leftArm2 = new ModelRenderer(this, 0, 0);
    this.leftLeg1 = new ModelRenderer(this, 0, 0);
    this.leftLeg2 = new ModelRenderer(this, 0, 0);
    this.leftTooth = new ModelRenderer(this, 0, 0);
    this.rightArm1 = new ModelRenderer(this, 0, 0);
    this.rightArm2 = new ModelRenderer(this, 0, 0);
    this.rightLeg1 = new ModelRenderer(this, 0, 0);
    this.rightLeg2 = new ModelRenderer(this, 0, 0);
    this.rightTooth = new ModelRenderer(this, 0, 0);
    this.tail = new ModelRenderer(this, 0, 0);
    this.torso = new ModelRenderer(this, 0, 0);

    // #f:0
    this.abdomen.addBox(-4.20F, -4.80F, -7.80F, 8, 9, 15);  this.abdomen.setRotationPoint(0.00F, 13.88F, 7.37F);
    this.head.addBox(-5.70F, -4.35F, -9.67F, 11, 8, 11);  this.head.setRotationPoint(0.00F, -0.65F, -11.87F);
    this.leftArm1.addBox(-2.10F, -2.63F, -2.72F, 4, 11, 5); this.leftArm1.setRotationPoint(5.50F, 0.45F, -8.85F);
    this.leftArm2.addBox(-1.50F, -0.86F, -4.46F, 3, 3, 6);  this.leftArm2.setRotationPoint(0.00F, 8.28F, -0.29F);
    this.leftLeg1.addBox(-2.40F, -2.50F, -3.35F, 4, 10, 6); this.leftLeg1.setRotationPoint(4.89F, 0.22F, 1.23F);
    this.leftLeg2.addBox(-1.50F, -0.68F, -4.93F, 3, 3, 7);  this.leftLeg2.setRotationPoint(0.00F, 8.00F, -0.75F);
    this.leftTooth.addBox(-1.40F, -3.59F, -13.48F, 1, 1, 4);  this.leftTooth.setRotationPoint(0.00F, 0.00F, 0.00F);
    this.rightArm1.addBox(-2.10F, -2.63F, -2.72F, 4, 11, 5);  this.rightArm1.setRotationPoint(-5.50F, 0.45F, -8.85F);
    this.rightArm2.addBox(-1.50F, -0.86F, -4.46F, 3, 3, 6); this.rightArm2.setRotationPoint(0.00F, 8.28F, -0.29F);
    this.rightLeg1.addBox(-2.40F, -2.50F, -3.35F, 4, 10, 6);  this.rightLeg1.setRotationPoint(-4.89F, 0.22F, 1.23F);
    this.rightLeg2.addBox(-1.50F, -0.68F, -4.93F, 3, 3, 7); this.rightLeg2.setRotationPoint(0.00F, 8.00F, -0.75F);
    this.rightTooth.addBox(-0.40F, -3.59F, -13.48F, 1, 1, 4); this.rightTooth.setRotationPoint(0.00F, 0.00F, 0.00F);
    this.tail.addBox(-3.30F, -2.69F, -1.16F, 6, 5, 10); this.tail.setRotationPoint(0.00F, 0.32F, 6.53F);
    this.torso.addBox(-5.10F, -6.76F, -13.02F, 10, 12, 15); this.torso.setRotationPoint(0.00F, 0.17F, -5.85F);
    // #f:1

    // Enfantillages
    this.torso.addChild(this.head);
    this.torso.addChild(this.leftArm1);
    this.leftArm1.addChild(this.leftArm2);
    this.abdomen.addChild(this.leftLeg1);
    this.leftLeg1.addChild(this.leftLeg2);
    this.head.addChild(this.leftTooth);
    this.torso.addChild(this.rightArm1);
    this.rightArm1.addChild(this.rightArm2);
    this.abdomen.addChild(this.rightLeg1);
    this.rightLeg1.addChild(this.rightLeg2);
    this.head.addChild(this.rightTooth);
    this.abdomen.addChild(this.tail);
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
    this.abdomen.rotateAngleX = 2.195F * degToRad;
    this.head.rotateAngleX = 7.397F * degToRad;
    this.leftArm1.rotateAngleX = 18.298F * degToRad;
    this.leftArm2.rotateAngleX = -10.908F * degToRad;
    this.leftLeg1.rotateAngleX = 10.897F * degToRad;
    this.leftLeg2.rotateAngleX = -12.382F * degToRad;
    this.leftTooth.rotateAngleX = 29.642F * degToRad;
    this.rightArm1.rotateAngleX = 18.298F * degToRad;
    this.rightArm2.rotateAngleX = -10.908F * degToRad;
    this.rightLeg1.rotateAngleX = 10.897F * degToRad;
    this.rightLeg2.rotateAngleX = -12.382F * degToRad;
    this.rightTooth.rotateAngleX = 29.642F * degToRad;
    this.tail.rotateAngleX = 6.95F * degToRad;
    this.torso.rotateAngleX = -8.428F * degToRad;

    // Calcul de l'animation
    interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);
  }
}
