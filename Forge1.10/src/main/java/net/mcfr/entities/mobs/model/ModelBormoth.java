package net.mcfr.entities.mobs.model;

import net.mcfr.entities.mobs.entity.EntityBormoth;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBormoth extends ModelBase {
  public ModelRenderer abdomen;
  public ModelRenderer torso;
  public ModelRenderer head;
  public ModelRenderer rightLeg;
  public ModelRenderer rightArm1;
  public ModelRenderer rightArm2;
  public ModelRenderer leftLeg;
  public ModelRenderer leftArm1;
  public ModelRenderer leftArm2;
  public ModelRenderer trunk;
  public boolean isChild;

  private float headYaw;
  private float headPitch;

  public ModelBormoth() {
    this.textureHeight = 128;
    this.textureWidth = 128;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;

    this.abdomen = new ModelRenderer(this, 0, 0);
    this.head = new ModelRenderer(this, 0, 0);
    this.leftArm1 = new ModelRenderer(this, 0, 0);
    this.leftArm2 = new ModelRenderer(this, 0, 0);
    this.leftLeg = new ModelRenderer(this, 0, 0);
    this.rightArm1 = new ModelRenderer(this, 0, 0);
    this.rightArm2 = new ModelRenderer(this, 0, 0);
    this.rightLeg = new ModelRenderer(this, 0, 0);
    this.torso = new ModelRenderer(this, 0, 0);
    this.trunk = new ModelRenderer(this, 0, 0);

    // #f:0
		this.abdomen.addBox(-7.01F, -6.00F, -9.00F, 14, 12, 18);	this.abdomen.setRotationPoint(0.00F, -4.25F, 0.00F);
		this.head.addBox(-6.01F, -6.97F, -2.98F, 12, 11, 22);	this.head.setRotationPoint(0.00F, -2.19F, 22.92F);
		this.leftArm1.addBox(-4.01F, -3.93F, -4.11F, 8, 30, 8);	this.leftArm1.setRotationPoint(-14.00F, 0.28F, 16.64F);
		this.leftArm2.addBox(-4.51F, -1.53F, -4.55F, 9, 28, 9);	this.leftArm2.setRotationPoint(0.00F, 24.32F, -0.07F);
		this.leftLeg.addBox(-4.51F, -4.33F, -4.31F, 9, 27, 9);	this.leftLeg.setRotationPoint(-11.50F, -0.08F, -5.63F);
		this.rightArm1.addBox(-4.01F, -3.93F, -4.11F, 8, 30, 8);	this.rightArm1.setRotationPoint(14.00F, 0.28F, 16.64F);
		this.rightArm2.addBox(-4.51F, -1.53F, -4.55F, 9, 28, 9);	this.rightArm2.setRotationPoint(0.00F, 24.32F, -0.07F);
		this.rightLeg.addBox(-4.51F, -4.33F, -4.31F, 9, 27, 9);	this.rightLeg.setRotationPoint(11.50F, -0.08F, -5.63F);
		this.torso.addBox(-10.02F, -9.18F, -2.57F, 20, 18, 27);	this.torso.setRotationPoint(0.00F, 0.23F, 6.96F);
		this.trunk.addBox(-4.01F, -4.64F, -7.09F, 8, 46, 8);	this.trunk.setRotationPoint(-16.00F, 1.92F, 24.20F);
		// #f:1

    // Enfantillages

    this.abdomen.addChild(this.rightLeg);
    this.abdomen.addChild(this.leftLeg);

    this.abdomen.addChild(this.torso);
    this.torso.addChild(this.head);

    this.torso.addChild(this.leftArm1);
    this.torso.addChild(this.rightArm1);

    this.rightArm1.addChild(this.rightArm2);
    this.leftArm1.addChild(this.leftArm2);
  }

  /**
   * Sets the models various rotation angles then renders the model.
   */
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    if (this.isChild) {
      // TODO
    } else {
      this.abdomen.render(scale);
    }

    if (((EntityBormoth) entityIn).getTrunkType() != 0) {
      this.trunk.render(scale);
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
    this.abdomen.rotateAngleX = 89.09F * degToRad;
    this.abdomen.rotateAngleY = 180.0F * degToRad;
    this.head.rotateAngleX = -74.27F * degToRad;
    this.trunk.rotateAngleX = 68.19F * degToRad;

    this.rightLeg.rotateAngleX = -88.77F * degToRad;
    this.leftLeg.rotateAngleX = -88.77F * degToRad;

    if (((EntityBormoth) entityIn).getTrunkType() != 0) {
      this.torso.rotateAngleX = -36.79F * degToRad;
      this.rightArm1.rotateAngleX = -106.97F * degToRad;
      this.rightArm2.rotateAngleX = -7.26F * degToRad;
      this.leftArm1.rotateAngleX = -38.29F * degToRad;
      this.leftArm2.rotateAngleX = 11.17F * degToRad;
      this.head.rotateAngleX = -67.71F * degToRad;
    } else {
      this.torso.rotateAngleX = -31.41F * degToRad;
      this.rightArm1.rotateAngleX = -44.06F * degToRad;
      this.rightArm2.rotateAngleX = 6.04F * degToRad;
      this.leftArm1.rotateAngleX = -44.06F * degToRad;
      this.leftArm2.rotateAngleX = 6.04F * degToRad;
    }

    // Calcul de l'animation
    this.rightLeg.rotateAngleX += MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI)) * 40.0F * limbSwingAmount * degToRad;

    this.leftLeg.rotateAngleX += MathHelper.cos((ageInTicks * tickToSec * 4.0F + 0.5F) * 2.0F * ((float) Math.PI)) * 40.0F * limbSwingAmount
        * degToRad;

    if (((EntityBormoth) entityIn).getTrunkType() != 0) {
      this.rightArm1.rotateAngleY = MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI)) * 10.0F * limbSwingAmount * degToRad;
      this.trunk.rotateAngleY = MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI)) * 12.0F * limbSwingAmount * degToRad;
      this.trunk.offsetX = MathHelper.sin(this.rightArm1.rotateAngleY) * 2.0F;
      this.trunk.offsetZ = MathHelper.cos(this.rightArm1.rotateAngleY) - 1.0F;
    } else {
      this.rightArm1.rotateAngleX += MathHelper.cos((ageInTicks * tickToSec * 4.0F + 0.5F) * 2.0F * ((float) Math.PI)) * 20.0F * limbSwingAmount
          * degToRad;
      this.rightArm2.rotateAngleX += MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI)) * 10.0F * limbSwingAmount * degToRad;
    }

    this.leftArm1.rotateAngleX += MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI)) * 20.0F * limbSwingAmount * degToRad;
    this.leftArm2.rotateAngleX += MathHelper.cos((ageInTicks * tickToSec * 4.0F + 0.5F) * 2.0F * ((float) Math.PI)) * 10.0F * limbSwingAmount
        * degToRad;

    this.interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);

    this.head.rotateAngleY = this.headPitch * degToRad;
    this.head.rotateAngleX += this.headYaw * degToRad;
  }
}
