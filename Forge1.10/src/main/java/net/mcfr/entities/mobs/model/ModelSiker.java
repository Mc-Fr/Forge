package net.mcfr.entities.mobs.model;

import net.mcfr.entities.mobs.gender.EntityGendered;
import net.mcfr.entities.mobs.gender.Genders;
import net.mcfr.utils.FourierUtils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSiker extends ModelBase {
  public ModelRenderer abdomen1;
  public ModelRenderer abdomen2;
  public ModelRenderer tail;
  public ModelRenderer torso1;
  public ModelRenderer torso2;
  public ModelRenderer neck1;
  public ModelRenderer neck2;
  public ModelRenderer neck3;
  public ModelRenderer head;
  public ModelRenderer trunk1;
  public ModelRenderer trunk2;
  public ModelRenderer rightLeg1;
  public ModelRenderer rightLeg2;
  public ModelRenderer rightLeg3;
  public ModelRenderer rightLeg4;
  public ModelRenderer rightArm1;
  public ModelRenderer rightArm2;
  public ModelRenderer rightArm3;
  public ModelRenderer leftLeg1;
  public ModelRenderer leftLeg2;
  public ModelRenderer leftLeg3;
  public ModelRenderer leftLeg4;
  public ModelRenderer leftArm1;
  public ModelRenderer leftArm2;
  public ModelRenderer leftArm3;
  public boolean isChild;

  private float headYaw;
  private float headPitch;

  public ModelSiker() {
    // Généré via Excel

    this.textureHeight = 128;
    this.textureWidth = 128;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;

    this.abdomen1 = new ModelRenderer(this, 0, 70);
    this.abdomen2 = new ModelRenderer(this, 0, 96);
    this.head = new ModelRenderer(this, 56, 0);
    this.leftArm1 = new ModelRenderer(this, 54, 94);
    this.leftArm2 = new ModelRenderer(this, 52, 102);
    this.leftArm3 = new ModelRenderer(this, 81, 112);
    this.leftLeg1 = new ModelRenderer(this, 79, 76);
    this.leftLeg2 = new ModelRenderer(this, 105, 74);
    this.leftLeg3 = new ModelRenderer(this, 99, 86);
    this.leftLeg4 = new ModelRenderer(this, 101, 85);
    this.neck1 = new ModelRenderer(this, 75, 2);
    this.neck2 = new ModelRenderer(this, 79, 28);
    this.neck3 = new ModelRenderer(this, 87, 52);
    this.rightArm1 = new ModelRenderer(this, 54, 94);
    this.rightArm2 = new ModelRenderer(this, 52, 102);
    this.rightArm3 = new ModelRenderer(this, 81, 112);
    this.rightLeg1 = new ModelRenderer(this, 79, 76);
    this.rightLeg2 = new ModelRenderer(this, 105, 74);
    this.rightLeg3 = new ModelRenderer(this, 99, 86);
    this.rightLeg4 = new ModelRenderer(this, 101, 85);
    this.tail = new ModelRenderer(this, 51, 37);
    this.torso1 = new ModelRenderer(this, 0, 36);
    this.torso2 = new ModelRenderer(this, 0, 0);
    this.trunk1 = new ModelRenderer(this, 44, 71);
    this.trunk2 = new ModelRenderer(this, 58, 75);

    // #f:0
		this.abdomen1.addBox(-6.90F, -7.60F, -5.99F, 13, 14, 12);	this.abdomen1.setRotationPoint(0.00F, 0.98F, 24.00F);
		this.abdomen2.addBox(-6.30F, -6.37F, -11.17F, 12, 13, 12);	this.abdomen2.setRotationPoint(0.00F, -0.48F, -4.69F);
		this.head.addBox(-4.20F, -4.39F, -7.82F, 8, 8, 8);	this.head.setRotationPoint(0.00F, -0.11F, -11.09F);
		this.leftArm1.addBox(-1.63F, -1.58F, -2.35F, 4, 23, 4);	this.leftArm1.setRotationPoint(9.11F, 1.87F, -10.93F);
		this.leftArm2.addBox(-2.11F, -1.38F, -19.32F, 4, 4, 20);	this.leftArm2.setRotationPoint(0.64F, 20.23F, -0.58F);
		this.leftArm3.addBox(-1.82F, -1.10F, -1.85F, 3, 6, 3);	this.leftArm3.setRotationPoint(0.00F, 1.09F, -18.17F);
		this.leftLeg1.addBox(11.28F, -3.18F, -3.11F, 4, 16, 6);	this.leftLeg1.setRotationPoint(-6.69F, 2.69F, 0.34F);
		this.leftLeg2.addBox(11.58F, -1.74F, -2.15F, 3, 3, 8);	this.leftLeg2.setRotationPoint(0.00F, 12.50F, -0.05F);
		this.leftLeg3.addBox(11.88F, -1.31F, -1.60F, 3, 6, 3);	this.leftLeg3.setRotationPoint(0.00F, 0.28F, 4.67F);
		this.leftLeg4.addBox(9.78F, -0.39F, -8.76F, 3, 3, 10);	this.leftLeg4.setRotationPoint(1.80F, 4.78F, -0.14F);
		this.neck1.addBox(-5.70F, -5.95F, -12.96F, 11, 11, 15);	this.neck1.setRotationPoint(0.00F, -1.81F, -13.42F);
		this.neck2.addBox(-4.80F, -5.03F, -13.12F, 9, 9, 15);	this.neck2.setRotationPoint(0.00F, -0.01F, -11.15F);
		this.neck3.addBox(-3.60F, -3.58F, -12.00F, 7, 7, 13);	this.neck3.setRotationPoint(0.00F, 0.28F, -12.06F);
		this.rightArm1.addBox(-3.17F, -1.58F, -2.35F, 4, 23, 4);	this.rightArm1.setRotationPoint(-9.11F, 1.87F, -10.93F);
		this.rightArm2.addBox(-2.09F, -1.38F, -19.32F, 4, 4, 20);	this.rightArm2.setRotationPoint(-0.64F, 20.23F, -0.58F);
		this.rightArm3.addBox(-1.78F, -1.10F, -1.85F, 3, 6, 3);	this.rightArm3.setRotationPoint(0.00F, 1.09F, -18.17F);
		this.rightLeg1.addBox(-15.48F, -3.18F, -3.11F, 4, 16, 6);	this.rightLeg1.setRotationPoint(6.69F, 2.69F, 0.34F);
		this.rightLeg2.addBox(-15.18F, -1.74F, -2.15F, 3, 3, 8);	this.rightLeg2.setRotationPoint(0.00F, 12.50F, -0.05F);
		this.rightLeg3.addBox(-14.88F, -1.31F, -1.60F, 3, 6, 3);	this.rightLeg3.setRotationPoint(0.00F, 0.28F, 4.67F);
		this.rightLeg4.addBox(-13.38F, -0.39F, -8.76F, 3, 3, 10);	this.rightLeg4.setRotationPoint(-1.80F, 4.78F, -0.14F);
		this.tail.addBox(-2.70F, -4.04F, -2.62F, 5, 8, 6);	this.tail.setRotationPoint(0.00F, 1.29F, 5.85F);
		this.torso1.addBox(-8.40F, -9.96F, -15.02F, 16, 18, 16);	this.torso1.setRotationPoint(0.00F, -0.64F, -8.25F);
		this.torso2.addBox(-9.60F, -9.79F, -15.45F, 19, 19, 17);	this.torso2.setRotationPoint(0.00F, -3.51F, -10.24F);
		this.trunk1.addBox(-1.80F, -1.76F, -7.32F, 3, 3, 7);	this.trunk1.setRotationPoint(0.00F, 0.26F, -7.42F);
		this.trunk2.addBox(-1.20F, -1.12F, -6.77F, 2, 2, 7);	this.trunk2.setRotationPoint(0.00F, 0.03F, -6.85F);
		// #f:1

    // Enfantillages

    this.abdomen1.addChild(this.tail);
    this.abdomen1.addChild(this.abdomen2);
    this.abdomen1.addChild(this.rightLeg1);
    this.abdomen1.addChild(this.leftLeg1);

    this.abdomen2.addChild(this.torso1);
    this.torso1.addChild(this.torso2);

    this.torso2.addChild(this.neck1);
    this.torso2.addChild(this.rightArm1);
    this.torso2.addChild(this.leftArm1);

    this.neck1.addChild(this.neck2);
    this.neck2.addChild(this.neck3);
    this.neck3.addChild(this.head);
    this.head.addChild(this.trunk1);
    this.trunk1.addChild(this.trunk2);

    this.rightLeg1.addChild(this.rightLeg2);
    this.rightLeg2.addChild(this.rightLeg3);
    this.rightLeg3.addChild(this.rightLeg4);

    this.rightArm1.addChild(this.rightArm2);
    this.rightArm2.addChild(this.rightArm3);

    this.leftLeg1.addChild(this.leftLeg2);
    this.leftLeg2.addChild(this.leftLeg3);
    this.leftLeg3.addChild(this.leftLeg4);

    this.leftArm1.addChild(this.leftArm2);
    this.leftArm2.addChild(this.leftArm3);
  }

  /**
   * Sets the models various rotation angles then renders the model.
   */
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    if (((EntityGendered) entityIn).isChild()) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.4F, 0.4F, 0.4F);
      GlStateManager.translate(0.0F, 35.0F * scale, 0.0F);
      this.abdomen1.render(scale);
      GlStateManager.popMatrix();
    } else if (((EntityGendered) entityIn).getGender() == Genders.FEMALE) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.9F, 0.9F, 0.9F);
      GlStateManager.translate(0.0F, 3.0F * scale, 0.0F);
      this.abdomen1.render(scale);
      GlStateManager.popMatrix();
    } else {
      this.abdomen1.render(scale);
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
    this.abdomen1.rotateAngleX = -18.41F * degToRad;
    this.abdomen2.rotateAngleX = -7.08F * degToRad;
    this.tail.rotateAngleX = 33.98F * degToRad;
    this.torso1.rotateAngleX = -10.50F * degToRad;
    this.torso2.rotateAngleX = 35.50F * degToRad;
    this.neck1.rotateAngleX = -1.10F * degToRad;
    this.neck2.rotateAngleX = 15.37F * degToRad;
    this.neck3.rotateAngleX = 7.40F * degToRad;
    this.head.rotateAngleX = 17.67F * degToRad;
    this.trunk1.rotateAngleX = 19.25F * degToRad;
    this.trunk2.rotateAngleX = 17.89F * degToRad;

    this.rightLeg1.rotateAngleX = -2.34F * degToRad;
    this.rightLeg2.rotateAngleX = 2.87F * degToRad;
    this.rightLeg3.rotateAngleX = -4.51F * degToRad;
    this.rightLeg4.rotateAngleX = 22.28F * degToRad;

    this.rightArm1.rotateAngleX = 10.0F * degToRad;
    this.rightArm1.rotateAngleZ = 20.0F * degToRad;
    this.rightArm2.rotateAngleX = 33.0F * degToRad;
    this.rightArm2.rotateAngleZ = -20.0F * degToRad;
    this.rightArm3.rotateAngleX = -44.61F * degToRad;

    this.leftLeg1.rotateAngleX = -2.34F * degToRad;
    this.leftLeg2.rotateAngleX = 2.87F * degToRad;
    this.leftLeg3.rotateAngleX = -4.51F * degToRad;
    this.leftLeg4.rotateAngleX = 22.28F * degToRad;

    this.leftArm1.rotateAngleX = 10.0F * degToRad;
    this.leftArm1.rotateAngleZ = -20.0F * degToRad;
    this.leftArm2.rotateAngleX = 33.0F * degToRad;
    this.leftArm2.rotateAngleZ = 20.0F * degToRad;
    this.leftArm3.rotateAngleX = -44.61F * degToRad;

    // Tables d'animation
    float[] animNeckYb = { 0.0F, 0.0F, 0.0F, 0.0F, 4.0F };
    float phaseCos = MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI));
    float doubleCos = MathHelper.cos(ageInTicks * tickToSec * 16.0F * ((float) Math.PI));

    // Calcul de l'animation
    this.rightLeg1.rotateAngleX += phaseCos * 30.0F * limbSwingAmount * degToRad;
    this.rightLeg4.rotateAngleX -= this.rightLeg1.rotateAngleX;

    this.leftLeg1.rotateAngleX -= phaseCos * 30.0F * limbSwingAmount * degToRad;
    this.leftLeg4.rotateAngleX -= this.leftLeg1.rotateAngleX;

    this.rightArm1.rotateAngleX -= this.leftLeg1.rotateAngleX * 0.8F;
    this.rightArm3.rotateAngleX -= this.leftLeg1.rotateAngleX * 0.3F;
    this.rightArm2.rotateAngleX += this.rightArm1.rotateAngleX * 0.5F;

    this.leftArm1.rotateAngleX += this.leftLeg1.rotateAngleX * 0.8F;
    this.leftArm3.rotateAngleX += this.leftLeg1.rotateAngleX * 0.3F;
    this.leftArm2.rotateAngleX += this.leftArm1.rotateAngleX * 0.5F;
    
    this.torso1.rotateAngleX += doubleCos * 1.0F * limbSwingAmount * degToRad;
    this.torso2.rotateAngleX += doubleCos * 1.0F * limbSwingAmount * degToRad;
    this.neck1.rotateAngleX -= doubleCos * 1.5F * limbSwingAmount * degToRad;

    this.neck1.rotateAngleY = FourierUtils.calculateScaledSeries(ageInTicks * tickToSec * 0.05F, new float[0], animNeckYb, 0.02F);
    this.neck2.rotateAngleY = FourierUtils.calculateScaledSeries(ageInTicks * tickToSec * 0.05F, new float[0], animNeckYb, 0.02F);
    this.neck3.rotateAngleY = FourierUtils.calculateScaledSeries(ageInTicks * tickToSec * 0.05F, new float[0], animNeckYb, 0.02F);

    this.interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);

    this.head.rotateAngleY = this.headPitch * degToRad;
    this.head.rotateAngleX += this.headYaw * degToRad;
    this.neck1.rotateAngleX += this.headYaw * degToRad * 0.1F;
    this.neck2.rotateAngleX += this.headYaw * degToRad * 0.1F;
    this.neck3.rotateAngleX += this.headYaw * degToRad * 0.1F;
  }
}
