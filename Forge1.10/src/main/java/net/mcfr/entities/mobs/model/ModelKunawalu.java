package net.mcfr.entities.mobs.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

/**
 * Modèle du Kunawalu.
 *
 * @author Mc-Fr
 */
public class ModelKunawalu extends ModelBase {
  public boolean isChild;

  private float headYaw;
  private float headPitch;

  public ModelKunawalu() {
    this.textureHeight = 128;
    this.textureWidth = 128;
    this.headPitch = 0.0F;
    this.headYaw = 0.0F;

    // Enfantillages

  }

  @Override
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

    if (this.isChild) {
      // TODO
    }
    else {

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

    // Pliage du modèle

    // Calcul de l'animation
    interpolateHeadAngles(headPitch, netHeadYaw, 0.2F);
  }
}
