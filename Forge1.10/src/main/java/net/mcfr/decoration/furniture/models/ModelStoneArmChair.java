package net.mcfr.decoration.furniture.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelStoneArmChair extends ModelBase implements ArmChairModel {
  ModelRenderer cote_1;
  ModelRenderer cote_2;
  ModelRenderer dossier_milieu;
  ModelRenderer dossier_bas;
  ModelRenderer dossier_droit;
  ModelRenderer dossier_haut;
  ModelRenderer dossier_bas2;
  ModelRenderer base;
  ModelRenderer peau_bas;
  ModelRenderer peau_haut;

  public ModelStoneArmChair() {
    this.textureWidth = 128;
    this.textureHeight = 64;

    this.cote_1 = new ModelRenderer(this, 80, 30);
    this.cote_1.addBox(0F, 0F, 0F, 3, 12, 16);
    this.cote_1.setRotationPoint(-11F, 12F, -8F);
    this.cote_1.setTextureSize(128, 64);
    this.cote_1.mirror = true;
    this.setRotation(this.cote_1, 0F, 0F, 0F);
    this.cote_2 = new ModelRenderer(this, 80, 30);
    this.cote_2.addBox(0F, 0F, 0F, 3, 12, 16);
    this.cote_2.setRotationPoint(8F, 12F, -8F);
    this.cote_2.setTextureSize(128, 64);
    this.cote_2.mirror = true;
    this.setRotation(this.cote_2, 0F, 0F, 0F);
    this.dossier_milieu = new ModelRenderer(this, 60, 9);
    this.dossier_milieu.addBox(0F, 0F, 0F, 6, 6, 0);
    this.dossier_milieu.setRotationPoint(-3F, 6F, 7F);
    this.dossier_milieu.setTextureSize(128, 64);
    this.dossier_milieu.mirror = true;
    this.setRotation(this.dossier_milieu, 0F, 0F, 0F);
    this.dossier_bas = new ModelRenderer(this, 0, 0);
    this.dossier_bas.addBox(0F, 0F, 0F, 16, 12, 2);
    this.dossier_bas.setRotationPoint(-8F, 12F, 6F);
    this.dossier_bas.setTextureSize(128, 64);
    this.dossier_bas.mirror = true;
    this.setRotation(this.dossier_bas, 0F, 0F, 0F);
    this.dossier_droit = new ModelRenderer(this, 80, 10);
    this.dossier_droit.addBox(0F, 0F, 0F, 5, 6, 2);
    this.dossier_droit.setRotationPoint(-8F, 6F, 6F);
    this.dossier_droit.setTextureSize(128, 64);
    this.dossier_droit.mirror = true;
    this.setRotation(this.dossier_droit, 0F, 0F, 0F);
    this.dossier_haut = new ModelRenderer(this, 50, 0);
    this.dossier_haut.addBox(0F, 0F, 0F, 16, 5, 2);
    this.dossier_haut.setRotationPoint(-8F, 1F, 6F);
    this.dossier_haut.setTextureSize(128, 64);
    this.dossier_haut.mirror = true;
    this.setRotation(this.dossier_haut, 0F, 0F, 0F);
    this.dossier_bas2 = new ModelRenderer(this, 40, 10);
    this.dossier_bas2.addBox(0F, 0F, 0F, 5, 6, 2);
    this.dossier_bas2.setRotationPoint(3F, 6F, 6F);
    this.dossier_bas2.setTextureSize(128, 64);
    this.dossier_bas2.mirror = true;
    this.setRotation(this.dossier_bas2, 0F, 0F, 0F);
    this.base = new ModelRenderer(this, 0, 24);
    this.base.addBox(0F, 0F, 0F, 16, 8, 12);
    this.base.setRotationPoint(-8F, 16F, -6F);
    this.base.setTextureSize(128, 64);
    this.base.mirror = true;
    this.setRotation(this.base, 0F, 0F, 0F);
    this.peau_bas = new ModelRenderer(this, 35, 49);
    this.peau_bas.addBox(0F, 0F, 0F, 14, 8, 0);
    this.peau_bas.setRotationPoint(-7F, 15.5F, -6.5F);
    this.peau_bas.setTextureSize(128, 64);
    this.peau_bas.mirror = true;
    this.setRotation(this.peau_bas, 0F, 0F, 0F);
    this.peau_haut = new ModelRenderer(this, -10, 47);
    this.peau_haut.addBox(0F, 0F, 0F, 14, 1, 12);
    this.peau_haut.setRotationPoint(-7F, 15.4F, -6.5F);
    this.peau_haut.setTextureSize(128, 64);
    this.peau_haut.mirror = false;
    this.setRotation(this.peau_haut, 0F, 0F, 0F);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  @Override
  public void renderModel(float f) {
    this.cote_1.render(f);
    this.cote_2.render(f);
    this.dossier_milieu.render(f);
    this.dossier_bas.render(f);
    this.dossier_droit.render(f);
    this.dossier_haut.render(f);
    this.dossier_bas2.render(f);
    this.base.render(f);
    this.peau_bas.render(f);
    this.peau_haut.render(f);

  }
}
