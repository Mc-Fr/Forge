package net.mcfr.entities.mobs.model;

import net.mcfr.entities.mobs.entity.EntityBormoth;
import net.mcfr.entities.mobs.gender.EntityGendered;
import net.mcfr.entities.mobs.gender.Genders;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Modèle du Bormoth.
 *
 * @author Mc-Fr
 */
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
  public ModelRenderer preTrunk;
  public ModelRenderer trunk1;
  public ModelRenderer trunk2;
  public ModelRenderer trunk3;
  public boolean isChild;

  private float headYaw;
  private float headPitch;

  public ModelBormoth() {
    this.textureHeight = 200;
    this.textureWidth = 100;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;

    this.abdomen = new ModelRenderer(this, 0, 78);
    this.head = new ModelRenderer(this, 0, 0);
    this.leftArm1 = new ModelRenderer(this, 0, 108);
    this.leftArm2 = new ModelRenderer(this, 32, 108);
    this.leftLeg = new ModelRenderer(this, 64, 78);
    this.rightArm1 = new ModelRenderer(this, 0, 108);
    this.rightArm2 = new ModelRenderer(this, 32, 108);
    this.rightLeg = new ModelRenderer(this, 64, 78);
    this.torso = new ModelRenderer(this, 0, 33);
    this.preTrunk = new ModelRenderer(this, 0, 0);
    this.trunk1 = new ModelRenderer(this, 0, 146);
    this.trunk2 = new ModelRenderer(this, 32, 146);
    this.trunk3 = new ModelRenderer(this, 64, 146);

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
		
		this.preTrunk.setRotationPoint(-30.00F, -18.66F, 0.67F);
    this.preTrunk.offsetX = 2.0F;
    this.preTrunk.offsetY = 2.8F;
    this.preTrunk.offsetZ = 0.3F;
    
		this.trunk1.addBox(-4.01F, -4.64F, -7.09F, 8, 46, 8);	this.trunk1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.trunk2.addBox(-4.01F, -4.64F, -7.09F, 8, 46, 8); this.trunk2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.trunk3.addBox(-4.01F, -4.64F, -7.09F, 8, 46, 8); this.trunk3.setRotationPoint(0.0F, 0.0F, 0.0F);
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

    this.rightArm2.addChild(this.preTrunk);
    this.preTrunk.addChild(this.trunk1);
    this.preTrunk.addChild(this.trunk2);
    this.preTrunk.addChild(this.trunk3);
  }

  @Override
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    this.trunk1.isHidden = true;
    this.trunk2.isHidden = true;
    this.trunk3.isHidden = true;
    switch (((EntityBormoth) entityIn).getTrunkType()) {
      case 1:
        this.trunk1.isHidden = false;
        break;
      case 2:
        this.trunk2.isHidden = false;
        break;
      case 3:
        this.trunk3.isHidden = false;
        break;
    }

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
    float degToRad = ((float) Math.PI / 180F);
    float tickToSec = 0.012F;

    // Pliage du modèle
    this.abdomen.rotateAngleX = 89.09F * degToRad;
    this.abdomen.rotateAngleY = 180.0F * degToRad;
    this.head.rotateAngleX = -74.27F * degToRad;
    this.preTrunk.rotateAngleX = 0.0F * degToRad;

    this.rightLeg.rotateAngleX = -88.77F * degToRad;
    this.leftLeg.rotateAngleX = -88.77F * degToRad;

    if (((EntityBormoth) entityIn).getTrunkType() != 0) {
      this.torso.rotateAngleX = -36.79F * degToRad;
      this.rightArm1.rotateAngleX = -106.97F * degToRad;
      this.rightArm2.rotateAngleX = -7.26F * degToRad;
      this.leftArm1.rotateAngleX = -38.29F * degToRad;
      this.leftArm2.rotateAngleX = 11.17F * degToRad;
      this.head.rotateAngleX = -67.71F * degToRad;
    }
    else {
      this.torso.rotateAngleX = -31.41F * degToRad;
      this.rightArm1.rotateAngleX = -44.06F * degToRad;
      this.rightArm2.rotateAngleX = 6.04F * degToRad;
      this.leftArm1.rotateAngleX = -44.06F * degToRad;
      this.leftArm2.rotateAngleX = 6.04F * degToRad;
    }

    // Calcul de l'animation
    float phaseCos = MathHelper.cos(ageInTicks * tickToSec * 8.0F * ((float) Math.PI));
    float doubleCos = MathHelper.cos(ageInTicks * tickToSec * 16.0F * ((float) Math.PI));

    this.rightLeg.rotateAngleX += phaseCos * 40.0F * limbSwingAmount * degToRad;
    this.leftLeg.rotateAngleX -= phaseCos * 40.0F * limbSwingAmount * degToRad;

    if (((EntityBormoth) entityIn).getTrunkType() != 0) {
      this.rightArm1.rotateAngleY = phaseCos * 10.0F * limbSwingAmount * degToRad;
    }
    else {
      this.rightArm1.rotateAngleX -= phaseCos * 20.0F * limbSwingAmount * degToRad;
      this.rightArm2.rotateAngleX += phaseCos * 10.0F * limbSwingAmount * degToRad;
    }

    this.leftArm1.rotateAngleX += phaseCos * 20.0F * limbSwingAmount * degToRad;
    this.leftArm2.rotateAngleX -= phaseCos * 10.0F * limbSwingAmount * degToRad;

    this.torso.rotateAngleX += doubleCos * 3.0F * limbSwingAmount * degToRad;
    this.head.rotateAngleX -= doubleCos * 2.8F * limbSwingAmount * degToRad;

    this.interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);

    this.head.rotateAngleY = this.headPitch * degToRad;
    this.head.rotateAngleX += this.headYaw * degToRad;
  }
}
