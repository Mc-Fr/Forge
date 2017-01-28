package net.mcfr.entities.mobs.model;

import net.mcfr.entities.mobs.entity.EntityNiale;
import net.mcfr.entities.mobs.gender.EntityGendered;
import net.mcfr.entities.mobs.gender.Genders;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelNiale extends ModelBase {
  public boolean isChild;

  private float headYaw;
  private float headPitch;
  private float headRotationAngleX;

  public ModelRenderer body;
  public ModelRenderer head;
  public ModelRenderer headWhool;
  public ModelRenderer leftArm;
  public ModelRenderer leftLeg;
  public ModelRenderer rightArm;
  public ModelRenderer rightLeg;
  public ModelRenderer snout;
  public ModelRenderer tail;
  public ModelRenderer tailWhool;
  public ModelRenderer whool;

  public ModelNiale() {
    this.textureHeight = 157;
    this.textureWidth = 126;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;

    this.body = new ModelRenderer(this, 0, 47);
    this.head = new ModelRenderer(this, 0, 0);
    this.headWhool = new ModelRenderer(this, 0, 21);
    this.leftArm = new ModelRenderer(this, 42, 0);
    this.leftLeg = new ModelRenderer(this, 42, 0);
    this.rightArm = new ModelRenderer(this, 42, 0);
    this.rightLeg = new ModelRenderer(this, 42, 0);
    this.snout = new ModelRenderer(this, 0, 0);
    this.tail = new ModelRenderer(this, 64, 47);
    this.tailWhool = new ModelRenderer(this, 50, 17);
    this.whool = new ModelRenderer(this, 0, 94);

    // #f:0
    this.body.addBox(-8.00F, -7.73F, -16.20F, 16, 15, 32);  this.body.setRotationPoint(0.00F, 9.60F, 0.00F);
    this.head.addBox(-5.10F, -5.31F, -10.18F, 10, 10, 11);  this.head.setRotationPoint(0.00F, -0.74F, -15.84F);
    this.headWhool.addBox(-7.50F, -9.28F, -8.05F, 15, 16, 10);  this.headWhool.setRotationPoint(0.00F, -0.74F, -15.84F);
    this.leftArm.addBox(-2.70F, -2.87F, -2.71F, 5, 16, 5);  this.leftArm.setRotationPoint(4.00F, 10.03F, -11.75F);
    this.leftLeg.addBox(-2.70F, -2.87F, -2.81F, 5, 16, 5);  this.leftLeg.setRotationPoint(4.00F, 10.03F, 11.75F);
    this.rightArm.addBox(-2.70F, -2.87F, -2.71F, 5, 16, 5); this.rightArm.setRotationPoint(-4.00F, 10.03F, -11.75F);
    this.rightLeg.addBox(-2.70F, -2.87F, -2.81F, 5, 16, 5); this.rightLeg.setRotationPoint(-4.00F, 10.03F, 11.75F);
    this.snout.addBox(-1.92F, -0.40F, -10.98F, 4, 3, 1);  this.snout.setRotationPoint(0.00F, 0.00F, 0.00F);
    this.tail.addBox(-4.20F, -2.14F, -1.93F, 8, 4, 15); this.tail.setRotationPoint(0.00F, -3.22F, 14.77F);
    this.tailWhool.addBox(-7.80F, -4.39F, -3.60F, 15, 7, 23); this.tailWhool.setRotationPoint(0.00F, 0.00F, 0.00F);
    this.whool.addBox(-12.00F, -12.23F, -17.15F, 24, 29, 34); this.whool.setRotationPoint(0.00F, 0.00F, 0.00F);
    // #f:1

    // Enfantillages
    this.body.addChild(this.head);
    this.body.addChild(this.headWhool);
    this.body.addChild(this.leftArm);
    this.body.addChild(this.rightArm);
    this.body.addChild(this.leftLeg);
    this.body.addChild(this.rightLeg);
    this.body.addChild(this.tail);
    this.body.addChild(this.whool);

    this.head.addChild(this.snout);
    this.tail.addChild(this.tailWhool);
  }

  /**
   * Sets the models various rotation angles then renders the model.
   */
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    
    if (!((EntityNiale) entityIn).getSheared()) {
      this.whool.isHidden = false;
      this.tailWhool.isHidden = false;
      this.headWhool.isHidden = false;
    } else {
      this.whool.isHidden = true;
      this.tailWhool.isHidden = true;
      this.headWhool.isHidden = true;
    }
    
    if (((EntityGendered) entityIn).isChild()) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
      GlStateManager.translate(0.0F, 35.0F * scale, 0.0F);
      this.body.render(scale);
      GlStateManager.popMatrix();
    } else if (((EntityGendered) entityIn).getGender() == Genders.FEMALE){
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.6F, 0.6F, 0.6F);
      GlStateManager.translate(0.0F, 7.0F * scale, 0.0F);
      this.body.render(scale);
      GlStateManager.popMatrix();
    } else {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.6F, 0.6F, 0.6F);
      GlStateManager.translate(0.0F, 7.0F * scale, 0.0F);
      this.body.render(scale);
      GlStateManager.popMatrix();
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
  
  public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
  {
      super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);
      this.head.rotationPointY = -0.74F + ((EntityNiale)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
      this.headRotationAngleX = ((EntityNiale)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
      this.headWhool.rotationPointY = this.head.rotationPointY;
  }
  
  /**
   * Sets the model's various rotation angles. For bipeds, par1 and par2 are
   * used for animating the movement of arms and legs, where par1 represents the
   * time(so that arms and legs swing back and forth) and par2 represents how
   * "far" arms and legs can swing at most.
   */
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor,
      Entity entityIn) {
    float degToRad = ((float) Math.PI / 180F);
    float tickToSec = 0.012F;

    // Pliage du modèle
    this.body.rotateAngleX = 0F;
    this.snout.rotateAngleX = 0F * degToRad;
    this.tail.rotateAngleX = -25.106F * degToRad;
    this.tail.rotateAngleY = 0F;
    this.tailWhool.rotateAngleX = 0F * degToRad;
    this.whool.rotateAngleX = 0F * degToRad;
    
    // Calcul de l'animation    
    float phaseCos = MathHelper.cos(ageInTicks * tickToSec * 12.0F * ((float) Math.PI));
    float phaseSin = MathHelper.sin(ageInTicks * tickToSec * 16.0F * ((float) Math.PI));
    
    this.leftArm.rotateAngleX = phaseCos * 20.0F * limbSwingAmount * degToRad;
    this.rightArm.rotateAngleX = -phaseCos * 20.0F * limbSwingAmount * degToRad;
    this.leftLeg.rotateAngleX = -phaseCos * 20.0F * limbSwingAmount * degToRad;
    this.rightLeg.rotateAngleX = phaseCos * 20.0F * limbSwingAmount * degToRad;
    this.whool.rotateAngleZ = phaseSin * 6.0F * limbSwingAmount * degToRad;
    this.body.rotateAngleZ = phaseCos * 2.0F * limbSwingAmount * degToRad;
    this.tail.rotateAngleY += phaseCos * 10.0F * limbSwingAmount * degToRad;
    
    this.interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);
    
    this.head.rotateAngleY = this.headPitch * degToRad;
    this.head.rotateAngleX = this.headRotationAngleX;
    this.headWhool.rotateAngleX = this.head.rotateAngleX / 2;
    this.headWhool.rotateAngleY = this.head.rotateAngleY / 2;
  }
}
