package net.mcfr.entities.mobs.model;

import net.mcfr.entities.mobs.gender.EntityGendered;
import net.mcfr.entities.mobs.gender.Genders;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * Modèle du Pryf.
 *
 * @author Mc-Fr
 */
public class ModelPryf extends ModelBase {
  public ModelRenderer body;
  public ModelRenderer chin;
  public ModelRenderer head;
  public ModelRenderer hump;
  public ModelRenderer leftArm;
  public ModelRenderer leftLeg1;
  public ModelRenderer leftLeg2;
  public ModelRenderer neck;
  public ModelRenderer rightArm;
  public ModelRenderer rightLeg1;
  public ModelRenderer rightLeg2;
  public boolean isChild;

  private float headYaw;
  private float headPitch;

  public ModelPryf() {
    this.textureHeight = 128;
    this.textureWidth = 128;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;

    this.body = new ModelRenderer(this, 0, 0);
    this.chin = new ModelRenderer(this, 0, 0);
    this.head = new ModelRenderer(this, 0, 0);
    this.hump = new ModelRenderer(this, 0, 0);
    this.leftArm = new ModelRenderer(this, 0, 0);
    this.leftLeg1 = new ModelRenderer(this, 0, 0);
    this.leftLeg2 = new ModelRenderer(this, 0, 0);
    this.neck = new ModelRenderer(this, 0, 0);
    this.rightArm = new ModelRenderer(this, 0, 0);
    this.rightLeg1 = new ModelRenderer(this, 0, 0);
    this.rightLeg2 = new ModelRenderer(this, 0, 0);

    // #f:0
    this.body.addBox(-6.60F, -8.40F, -18.30F, 13, 16, 36);  this.body.setRotationPoint(0.00F, -12.27F, 2.06F);
    this.chin.addBox(-1.50F, 0.35F, -15.51F, 3, 8, 6);  this.chin.setRotationPoint(0.00F, 0.00F, 0.00F);
    this.head.addBox(-4.50F, -4.67F, -15.30F, 9, 9, 20);  this.head.setRotationPoint(0.00F, -20.91F, 0.41F);
    this.hump.addBox(-4.50F, -6.30F, -7.50F, 9, 12, 15);  this.hump.setRotationPoint(0.00F, -7.21F, -3.62F);
    this.leftArm.addBox(-2.10F, -2.31F, -2.60F, 4, 40, 4);  this.leftArm.setRotationPoint(8.23F, 0.48F, -13.28F);
    this.leftLeg1.addBox(-2.40F, -3.30F, -4.77F, 4, 17, 9); this.leftLeg1.setRotationPoint(8.69F, -0.17F, 8.63F);
    this.leftLeg2.addBox(-1.80F, -3.21F, -2.39F, 3, 27, 4); this.leftLeg2.setRotationPoint(0.00F, 11.82F, -0.55F);
    this.neck.addBox(-3.30F, -21.04F, -4.27F, 6, 25, 9);  this.neck.setRotationPoint(0.00F, 0.52F, -16.22F);
    this.rightArm.addBox(-2.10F, -2.31F, -2.60F, 4, 40, 4); this.rightArm.setRotationPoint(-8.23F, 0.48F, -13.28F);
    this.rightLeg1.addBox(-2.40F, -3.30F, -4.77F, 4, 17, 9);  this.rightLeg1.setRotationPoint(-8.69F, -0.17F, 8.63F);
    this.rightLeg2.addBox(-1.80F, -3.21F, -2.39F, 3, 27, 4);  this.rightLeg2.setRotationPoint(0.00F, 11.82F, -0.55F);
    // #f:1

    // Enfantillages
    this.head.addChild(this.chin);
    this.neck.addChild(this.head);
    this.body.addChild(this.hump);
    this.body.addChild(this.leftArm);
    this.body.addChild(this.leftLeg1);
    this.leftLeg1.addChild(this.leftLeg2);
    this.body.addChild(this.neck);
    this.body.addChild(this.rightArm);
    this.body.addChild(this.rightLeg1);
    this.rightLeg1.addChild(this.rightLeg2);

  }

  @Override
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    if (((EntityGendered) entityIn).isChild()) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
      GlStateManager.translate(0.0F, 35.0F * scale, 0.0F);
      this.body.render(scale);
      GlStateManager.popMatrix();
    }
    else if (((EntityGendered) entityIn).getGender() == Genders.FEMALE) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.9F, 0.9F, 0.9F);
      GlStateManager.translate(0.0F, 3.0F * scale, 0.0F);
      this.body.render(scale);
      GlStateManager.popMatrix();
    }
    else {
      this.body.render(scale);
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
    this.body.rotateAngleX = -6.551F * degToRad;
    this.chin.rotateAngleX = 13.82F * degToRad;
    this.head.rotateAngleX = -12.235F * degToRad;
    this.hump.rotateAngleX = 33.769F * degToRad;
    this.leftArm.rotateAngleX = 6.925F * degToRad;
    this.leftLeg1.rotateAngleX = 9.351F * degToRad;
    this.leftLeg2.rotateAngleX = -5.744F * degToRad;
    this.neck.rotateAngleX = 29.273F * degToRad;
    this.rightArm.rotateAngleX = 6.925F * degToRad;
    this.rightLeg1.rotateAngleX = 9.351F * degToRad;
    this.rightLeg2.rotateAngleX = -5.744F * degToRad;

    // Calcul de l'animation
    interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);
  }
}
