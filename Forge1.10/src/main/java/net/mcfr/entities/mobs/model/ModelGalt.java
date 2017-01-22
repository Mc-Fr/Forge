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
  public ModelRenderer neck;
  public ModelRenderer head;
  public ModelRenderer tail1;
  public ModelRenderer tail2;
  public ModelRenderer rightLeg1;
  public ModelRenderer rightLeg2;
  public ModelRenderer rightLeg3;
  public ModelRenderer rightArm1;
  public ModelRenderer rightArm2;
  public ModelRenderer rightArm3;
  public ModelRenderer leftLeg1;
  public ModelRenderer leftLeg2;
  public ModelRenderer leftLeg3;
  public ModelRenderer leftArm1;
  public ModelRenderer leftArm2;
  public ModelRenderer leftArm3;
  
  private float headYaw;
  private float headPitch;

  public ModelGalt() {
    this.textureHeight = 123;
    this.textureWidth = 55;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;
    
    this.abdomen = new ModelRenderer(this, 0, 64);
    this.head = new ModelRenderer(this, 0, 0);
    this.leftArm1 = new ModelRenderer(this, 33, 104);
    this.leftArm2 = new ModelRenderer(this, 39, 92);
    this.leftArm3 = new ModelRenderer(this, 35, 71);
    this.leftLeg1 = new ModelRenderer(this, 35, 0);
    this.leftLeg2 = new ModelRenderer(this, 41, 16);
    this.leftLeg3 = new ModelRenderer(this, 39, 29);
    this.neck = new ModelRenderer(this, 0, 14);
    this.rightArm1 = new ModelRenderer(this, 33, 104);
    this.rightArm2 = new ModelRenderer(this, 39, 92);
    this.rightArm3 = new ModelRenderer(this, 35, 71);
    this.rightLeg1 = new ModelRenderer(this, 35, 0);
    this.rightLeg2 = new ModelRenderer(this, 41, 16);
    this.rightLeg3 = new ModelRenderer(this, 39, 29);
    this.tail1 = new ModelRenderer(this, 0, 91);
    this.tail2 = new ModelRenderer(this, 0, 109);
    this.torso = new ModelRenderer(this, 0, 35);
    
    // #f:0
    this.abdomen.addBox(-4.80F, -5.40F, -8.70F, 9, 10, 17); this.abdomen.setRotationPoint(0.00F, 9.83F, 4.41F);
    this.head.addBox(-3.00F, -3.17F, -7.97F, 6, 6, 8);  this.head.setRotationPoint(0.00F, 0.40F, -10.79F);
    this.leftArm1.addBox(-2.70F, -4.13F, -3.22F, 5, 13, 6, true); this.leftArm1.setRotationPoint(6.52F, 0.52F, -7.34F);
    this.leftArm2.addBox(-2.10F, -1.62F, -2.05F, 4, 8, 4, true);  this.leftArm2.setRotationPoint(0.00F, 8.30F, -0.37F);
    this.leftArm3.addBox(-1.80F, -0.55F, -5.11F, 3, 3, 7, true);  this.leftArm3.setRotationPoint(0.00F, 6.37F, 0.04F);
    this.leftLeg1.addBox(-2.40F, -2.90F, -3.28F, 4, 10, 6, true); this.leftLeg1.setRotationPoint(6.52F, 0.61F, 4.84F);
    this.leftLeg2.addBox(-1.50F, -1.63F, -2.27F, 3, 6, 4, true);  this.leftLeg2.setRotationPoint(0.00F, 6.27F, -0.25F);
    this.leftLeg3.addBox(-1.20F, -0.72F, -4.46F, 2, 2, 6, true);  this.leftLeg3.setRotationPoint(0.00F, 4.64F, -0.14F);
    this.neck.addBox(-4.50F, -4.51F, -11.02F, 9, 9, 12);  this.neck.setRotationPoint(0.00F, -2.66F, -11.81F);
    this.rightArm1.addBox(-2.70F, -4.13F, -3.22F, 5, 13, 6);  this.rightArm1.setRotationPoint(-6.52F, 0.52F, -7.34F);
    this.rightArm2.addBox(-2.10F, -1.62F, -2.05F, 4, 8, 4); this.rightArm2.setRotationPoint(0.00F, 8.30F, -0.37F);
    this.rightArm3.addBox(-1.80F, -0.55F, -5.11F, 3, 3, 7); this.rightArm3.setRotationPoint(0.00F, 6.37F, 0.04F);
    this.rightLeg1.addBox(-2.40F, -2.90F, -3.28F, 4, 10, 6);  this.rightLeg1.setRotationPoint(-6.52F, 0.61F, 4.84F);
    this.rightLeg2.addBox(-1.50F, -1.63F, -2.27F, 3, 6, 4); this.rightLeg2.setRotationPoint(0.00F, 6.27F, -0.25F);
    this.rightLeg3.addBox(-1.20F, -0.72F, -4.46F, 2, 2, 6); this.rightLeg3.setRotationPoint(0.00F, 4.64F, -0.14F);
    this.tail1.addBox(-3.90F, -3.21F, -1.27F, 7, 6, 12);  this.tail1.setRotationPoint(0.00F, -0.10F, 7.79F);
    this.tail2.addBox(-2.10F, -2.15F, -0.53F, 4, 4, 10);  this.tail2.setRotationPoint(0.00F, 0.57F, 10.87F);
    this.torso.addBox(-5.70F, -8.26F, -12.88F, 11, 14, 15); this.torso.setRotationPoint(0.00F, 0.13F, -5.93F);
    // #f:1
    
    // Enfantillages
    this.abdomen.addChild(this.torso);
    this.torso.addChild(this.neck);
    this.neck.addChild(this.head);
    
    this.abdomen.addChild(this.tail1);
    this.tail1.addChild(this.tail2);
    
    this.abdomen.addChild(this.rightLeg1);
    this.rightLeg1.addChild(this.rightLeg2);
    this.rightLeg2.addChild(this.rightLeg3);
    
    this.abdomen.addChild(this.leftLeg1);
    this.leftLeg1.addChild(this.leftLeg2);
    this.leftLeg2.addChild(this.leftLeg3);
    
    this.torso.addChild(this.rightArm1);
    this.rightArm1.addChild(this.rightArm2);
    this.rightArm2.addChild(this.rightArm3);
    
    this.torso.addChild(this.leftArm1);
    this.leftArm1.addChild(this.leftArm2);
    this.leftArm2.addChild(this.leftArm3);
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
    this.abdomen.rotateAngleX = -19.683F * degToRad;
    this.head.rotateAngleX = 2.32F * degToRad;
    this.leftArm1.rotateAngleX = 2.375F * degToRad;
    this.leftArm2.rotateAngleX = 15.849F * degToRad;
    this.leftArm3.rotateAngleX = -2.386F * degToRad;
    this.leftLeg1.rotateAngleX = 40.244F * degToRad;
    this.leftLeg2.rotateAngleX = -42.26F * degToRad;
    this.leftLeg3.rotateAngleX = 21.653F * degToRad;
    this.neck.rotateAngleX = 14.886F * degToRad;
    this.rightArm1.rotateAngleX = 2.375F * degToRad;
    this.rightArm2.rotateAngleX = 15.849F * degToRad;
    this.rightArm3.rotateAngleX = -2.386F * degToRad;
    this.rightLeg1.rotateAngleX = 40.244F * degToRad;
    this.rightLeg2.rotateAngleX = -42.26F * degToRad;
    this.rightLeg3.rotateAngleX = 21.653F * degToRad;
    this.tail1.rotateAngleX = 9.354F * degToRad;
    this.tail2.rotateAngleX = 8.445F * degToRad;
    this.torso.rotateAngleX = 4.507F * degToRad;

    // Calcul de l'animation
    float phaseCos = MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI));
    
    this.rightLeg1.rotateAngleX += phaseCos * 40.0F * limbSwingAmount * degToRad;
    this.rightLeg2.rotateAngleX -= phaseCos * 40.0F * limbSwingAmount * degToRad;
    
    this.leftLeg1.rotateAngleX -= phaseCos * 40.0F * limbSwingAmount * degToRad;
    this.leftLeg2.rotateAngleX += phaseCos * 40.0F * limbSwingAmount * degToRad;
    
    this.rightArm1.rotateAngleX -= phaseCos * 20.0F * limbSwingAmount * degToRad;
    this.rightArm2.rotateAngleX += phaseCos * 10.0F * limbSwingAmount * degToRad;
    this.rightArm3.rotateAngleX += (phaseCos - 0.5F) * 10.0F * limbSwingAmount * degToRad;
    
    this.leftArm1.rotateAngleX += phaseCos * 20.0F * limbSwingAmount * degToRad;
    this.leftArm2.rotateAngleX -= phaseCos * 10.0F * limbSwingAmount * degToRad;
    this.leftArm3.rotateAngleX -= (phaseCos + 0.5F) * 10.0F * limbSwingAmount * degToRad;
    
    float[] animTailY = { 0.0F, 0.0F, 0.0F, 0.0F, 4.0F };
    this.tail1.rotateAngleY = FourierUtils.calculateScaledSeries(ageInTicks * tickToSec * 0.1F, new float[0], animTailY, 0.02F);
    this.tail2.rotateAngleY = FourierUtils.calculateScaledSeries(ageInTicks * tickToSec * 0.1F, new float[0], animTailY, 0.02F);
    this.tail1.rotateAngleY += MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI)) * 10.0F * limbSwingAmount * degToRad;
    
    this.interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);
    this.head.rotateAngleY = this.headPitch * degToRad;
    this.head.rotateAngleX += this.headYaw * degToRad;
    this.neck.rotateAngleX += this.headYaw * degToRad * 0.1F;
  }
}
