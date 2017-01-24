package net.mcfr.entities.mobs.model;

import net.mcfr.entities.mobs.gender.EntityGendered;
import net.mcfr.entities.mobs.gender.Genders;
import net.mcfr.utils.FourierUtils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGalt extends ModelBase {
  public boolean isChild;
  public ModelRenderer abdomen;
  public ModelRenderer torso;
  public ModelRenderer head;
  public ModelRenderer tail;
  public ModelRenderer rightLeg1;
  public ModelRenderer rightLeg2;
  public ModelRenderer rightLeg3;
  public ModelRenderer rightArm1;
  public ModelRenderer rightArm2;
  public ModelRenderer leftLeg1;
  public ModelRenderer leftLeg2;
  public ModelRenderer leftLeg3;
  public ModelRenderer leftArm1;
  public ModelRenderer leftArm2;
  
  private float headYaw;
  private float headPitch;

  public ModelGalt() {
    this.textureHeight = 137;
    this.textureWidth = 56;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;
    
    this.abdomen = new ModelRenderer(this, 0, 53);
    this.head = new ModelRenderer(this, 0, 0);
    this.leftArm1 = new ModelRenderer(this, 0, 103);
    this.leftArm2 = new ModelRenderer(this, 0, 124);
    this.leftLeg1 = new ModelRenderer(this, 36, 103);
    this.leftLeg2 = new ModelRenderer(this, 42, 119);
    this.leftLeg3 = new ModelRenderer(this, 29, 127);
    this.rightArm1 = new ModelRenderer(this, 0, 103);
    this.rightArm2 = new ModelRenderer(this, 0, 124);
    this.rightLeg1 = new ModelRenderer(this, 36, 103);
    this.rightLeg2 = new ModelRenderer(this, 42, 119);
    this.rightLeg3 = new ModelRenderer(this, 29, 127);
    this.tail = new ModelRenderer(this, 0, 82);
    this.torso = new ModelRenderer(this, 0, 24);
    
    // #f:0
    this.abdomen.addBox(-5.40F, -6.00F, -8.70F, 10, 12, 17);  this.abdomen.setRotationPoint(0.00F, 8.13F, 6.66F);
    this.head.addBox(-5.10F, -4.43F, -14.60F, 10, 9, 15); this.head.setRotationPoint(0.00F, -1.89F, -12.17F);
    this.leftArm1.addBox(-2.70F, -2.32F, -3.44F, 5, 15, 6, true); this.leftArm1.setRotationPoint(6.68F, -1.76F, -9.16F);
    this.leftArm2.addBox(-2.10F, -1.00F, -2.25F, 4, 9, 4, true);  this.leftArm2.setRotationPoint(0.00F, 11.25F, -0.44F);
    this.leftLeg1.addBox(-2.40F, -2.66F, -3.29F, 4, 10, 6, true); this.leftLeg1.setRotationPoint(6.08F, 0.11F, 4.13F);
    this.leftLeg2.addBox(-1.50F, -1.87F, -2.11F, 3, 8, 4, true);  this.leftLeg2.setRotationPoint(0.00F, 6.13F, 0.79F);
    this.leftLeg3.addBox(-1.80F, -1.59F, -6.65F, 3, 3, 7, true);  this.leftLeg3.setRotationPoint(0.00F, 5.30F, 0.05F);
    this.rightArm1.addBox(-2.70F, -2.32F, -3.44F, 5, 15, 6);  this.rightArm1.setRotationPoint(-6.52F, -1.76F, -9.16F);
    this.rightArm2.addBox(-2.10F, -1.00F, -2.25F, 4, 9, 4); this.rightArm2.setRotationPoint(0.00F, 11.25F, -0.44F);
    this.rightLeg1.addBox(-2.40F, -2.66F, -3.29F, 4, 10, 6);  this.rightLeg1.setRotationPoint(-6.52F, 0.11F, 4.13F);
    this.rightLeg2.addBox(-1.50F, -1.87F, -2.11F, 3, 8, 4); this.rightLeg2.setRotationPoint(0.00F, 6.13F, 0.79F);
    this.rightLeg3.addBox(-1.80F, -1.59F, -6.65F, 3, 3, 7); this.rightLeg3.setRotationPoint(0.00F, 5.30F, 0.05F);
    this.tail.addBox(-3.60F, -2.92F, -1.50F, 7, 6, 15); this.tail.setRotationPoint(0.00F, 0.80F, 7.45F);
    this.torso.addBox(-6.60F, -7.47F, -13.58F, 13, 14, 15); this.torso.setRotationPoint(0.00F, 0.13F, -5.75F);
    // #f:1
    
    // Enfantillages
    this.abdomen.addChild(this.torso);
    this.torso.addChild(this.head);
    
    this.abdomen.addChild(this.tail);
    
    this.abdomen.addChild(this.rightLeg1);
    this.rightLeg1.addChild(this.rightLeg2);
    this.rightLeg2.addChild(this.rightLeg3);
    
    this.abdomen.addChild(this.leftLeg1);
    this.leftLeg1.addChild(this.leftLeg2);
    this.leftLeg2.addChild(this.leftLeg3);
    
    this.torso.addChild(this.rightArm1);
    this.rightArm1.addChild(this.rightArm2);
    
    this.torso.addChild(this.leftArm1);
    this.leftArm1.addChild(this.leftArm2);
  }

  /**
   * Sets the models various rotation angles then renders the model.
   */
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
      float headPitch, float scale) {
    this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    if (((EntityGendered) entityIn).isChild()) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
      GlStateManager.translate(0.0F, 35.0F * scale, 0.0F);
      this.abdomen.render(scale);
      GlStateManager.popMatrix();
    } else if (((EntityGendered) entityIn).getGender() == Genders.FEMALE){
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.9F, 0.9F, 0.9F);
      GlStateManager.translate(0.0F, 3.0F * scale, 0.0F);
      this.abdomen.render(scale);
      GlStateManager.popMatrix();
    } else {
      this.abdomen.render(scale);
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
    
    if (this.headYaw - headYaw*0.4F > 0.2F) {
      this.headYaw -= speed;
    } else if (this.headYaw - headYaw*0.4F < -0.2F) {
      this.headYaw += speed;
    }
  }
  
  /**
   * Sets the model's various rotation angles. For bipeds, par1 and par2 are
   * used for animating the movement of arms and legs, where par1 represents
   * the time(so that arms and legs swing back and forth) and par2 represents
   * how "far" arms and legs can swing at most.
   */
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
      float headPitch, float scaleFactor, Entity entityIn) {
    float degToRad = ((float) Math.PI / 180F);
    float tickToSec = 0.012F;

    // Pliage du modèle
    this.abdomen.rotateAngleX = -4.752F * degToRad;
    this.head.rotateAngleX = 2.21F * degToRad;
    this.leftArm1.rotateAngleX = 15.664F * degToRad;
    this.leftArm2.rotateAngleX = -34.41F * degToRad;
    this.leftLeg1.rotateAngleX = -21.108F * degToRad;
    this.leftLeg2.rotateAngleX = 64.998F * degToRad;
    this.leftLeg3.rotateAngleX = 13.857F * degToRad;
    this.rightArm1.rotateAngleX = 15.664F * degToRad;
    this.rightArm2.rotateAngleX = -34.41F * degToRad;
    this.rightLeg1.rotateAngleX = -21.108F * degToRad;
    this.rightLeg2.rotateAngleX = 64.998F * degToRad;
    this.rightLeg3.rotateAngleX = 13.857F * degToRad;
    this.tail.rotateAngleX = 0F * degToRad;
    this.torso.rotateAngleX = -1.124F * degToRad;

    // Calcul de l'animation
    float phaseCos = MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI));
    float doubleCos = MathHelper.cos(ageInTicks * tickToSec * 16.0F * ((float) Math.PI));
    
    this.rightLeg1.rotateAngleX += phaseCos * 20.0F * limbSwingAmount * degToRad;
    this.leftLeg1.rotateAngleX -= phaseCos * 20.0F * limbSwingAmount * degToRad;
    
    this.rightArm1.rotateAngleX -= phaseCos * 20.0F * limbSwingAmount * degToRad;
    this.leftArm1.rotateAngleX += phaseCos * 20.0F * limbSwingAmount * degToRad;
    
    this.torso.rotateAngleX += doubleCos * 3.0F * limbSwingAmount * degToRad;
    this.head.rotateAngleX -= doubleCos * 2.5F * limbSwingAmount * degToRad;
    
    float[] animTailY = { 0.0F, 0.0F, 0.0F, 0.0F, 4.0F };
    this.tail.rotateAngleY = FourierUtils.calculateScaledSeries(ageInTicks * tickToSec * 0.1F, new float[0], animTailY, 0.02F);
    this.tail.rotateAngleY += MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI)) * 10.0F * limbSwingAmount * degToRad;
    
    this.interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);
    this.head.rotateAngleY = this.headPitch * degToRad;
    this.head.rotateAngleX += this.headYaw * degToRad;
  }
}
