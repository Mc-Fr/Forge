package net.mcfr.entities.mobs.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelNiale extends ModelBase {
  public boolean isChild;
  
  private float headYaw;
  private float headPitch;

  public ModelNiale() {
    this.textureHeight = 128;
    this.textureWidth = 128;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;
    
    // Enfantillages
    
  }

  /**
   * Sets the models various rotation angles then renders the model.
   */
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
      float headPitch, float scale) {
    this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    if (this.isChild) {
      //TODO
    } else {
      
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

    // Calcul de l'animation
    this.interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);
  }
}
