package net.mcfr.entities.mobs.model;

import java.util.Random;

import net.mcfr.entities.mobs.entity.EntityHoen;
import net.mcfr.entities.mobs.gender.EntityGendered;
import net.mcfr.entities.mobs.gender.Genders;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHoen extends ModelBase {
  private ModelRenderer body;
  private ModelRenderer neck1;
  private ModelRenderer neck2;
  private ModelRenderer preHead;
  private ModelRenderer head;
  private ModelRenderer leftLeg;
  private ModelRenderer rightLeg;
  private ModelRenderer beak;
  private ModelRenderer crete;

  private float previousAge;

  public ModelHoen() {
    this.textureHeight = 34;
    this.textureWidth = 42;

    this.beak = new ModelRenderer(this, 0, 7);
    this.body = new ModelRenderer(this, 0, 0);
    this.crete = new ModelRenderer(this, 2, 1);
    this.preHead = new ModelRenderer(this, 0, 0);
    this.head = new ModelRenderer(this, 26, 23);
    this.leftLeg = new ModelRenderer(this, 30, 0);
    this.neck1 = new ModelRenderer(this, 0, 20);
    this.neck2 = new ModelRenderer(this, 12, 23);
    this.rightLeg = new ModelRenderer(this, 30, 0);

    this.preHead.setRotationPoint(0.00F, -0.23F, -6.17F);

    // #f:0
    this.beak.addBox(-1.00F, -0.38F, -5.22F, 2, 1, 4);  this.beak.setRotationPoint(0.00F, 0.00F, 0.00F);
    this.body.addBox(-4.00F, -4.00F, -6.00F, 8, 8, 12); this.body.setRotationPoint(0.00F, 22.37F, 0.00F);
    this.crete.addBox(-1.50F, -4.41F, -0.52F, 3, 5, 1); this.crete.setRotationPoint(0.00F, -1.96F, 0.19F);
    this.head.addBox(-2.00F, -3.13F, -3.02F, 4, 4, 4);  this.head.setRotationPoint(0.00F, 0.0F, 0.0F);
    this.leftLeg.addBox(-1.00F, -1.00F, -0.96F, 2, 10, 2);  this.leftLeg.setRotationPoint(2.66F, 2.89F, 0.38F);
    this.neck1.addBox(-2.00F, -2.42F, -5.02F, 4, 5, 6); this.neck1.setRotationPoint(0.00F, -1.95F, -4.98F);
    this.neck2.addBox(-1.50F, -1.53F, -7.22F, 3, 3, 8); this.neck2.setRotationPoint(0.00F, -0.30F, -4.47F);
    this.rightLeg.addBox(-1.00F, -1.00F, -0.96F, 2, 10, 2); this.rightLeg.setRotationPoint(-2.66F, 2.89F, 0.38F);
		// #f:1

    // Enfantillages

    this.body.addChild(this.neck1);
    this.body.addChild(this.leftLeg);
    this.body.addChild(this.rightLeg);

    this.neck1.addChild(this.neck2);
    this.neck2.addChild(this.preHead);
    this.preHead.addChild(this.head);

    this.head.addChild(this.beak);
    this.head.addChild(this.crete);
  }

  /**
   * Sets the models various rotation angles then renders the model.
   */
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    
    if (((EntityGendered) entityIn).isChild()) {
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.7F, 0.7F, 0.7F);
      GlStateManager.translate(0.0F, 12F * scale, 0.0F);
      this.crete.isHidden = true;
      this.body.render(scale * 0.7F);
      GlStateManager.popMatrix();

    } else if (((EntityGendered) entityIn).getGender() == Genders.FEMALE) {
      this.crete.isHidden = true;
      this.body.render(scale * 0.7F);
      
    } else {
      this.crete.isHidden = false;
      this.body.render(scale * 0.7F);
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
    this.beak.rotateAngleX = 0F * degToRad;
    this.body.rotateAngleX = -7.906F * degToRad;
    this.crete.rotateAngleX = -32.506F * degToRad;
    this.preHead.rotateAngleX = 52.79F * degToRad;
    this.leftLeg.rotateAngleX = 7.973F * degToRad;
    this.neck1.rotateAngleX = -24.375F * degToRad;
    this.neck2.rotateAngleX = -19.692F * degToRad;
    this.rightLeg.rotateAngleX = 7.973F * degToRad;

    // Calcul de l'animation
    this.rightLeg.rotateAngleX += MathHelper.cos(ageInTicks * tickToSec * 12.0F * ((float) Math.PI)) * 40.0F * limbSwingAmount * degToRad;
    this.leftLeg.rotateAngleX += MathHelper.cos((ageInTicks * tickToSec * 12.0F + 1.0F) * ((float) Math.PI)) * 40.0F * limbSwingAmount * degToRad;
    this.neck1.rotateAngleX += MathHelper.cos(ageInTicks * tickToSec * 24.0F * ((float) Math.PI)) * 10.0F * limbSwingAmount * degToRad;
    this.neck2.rotateAngleX += MathHelper.cos(ageInTicks * tickToSec * 24.0F * ((float) Math.PI)) * 30.0F * limbSwingAmount * degToRad;
    this.preHead.rotateAngleX -= MathHelper.cos(ageInTicks * tickToSec * 24.0F * ((float) Math.PI)) * 40.0F * limbSwingAmount * degToRad;

    this.head.rotateAngleX = headPitch * 0.017453292F;
    this.head.rotateAngleY = netHeadYaw * 0.017453292F;

    if (((EntityHoen) entityIn).getTicks() > this.previousAge + 200.0F) {
      this.head.rotateAngleZ = ((new Random()).nextFloat() - 0.5F) * 0.5F;
      this.previousAge = ((EntityHoen) entityIn).getTicks();
    }
  }
}
