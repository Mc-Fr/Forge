package net.mcfr.decoration.signs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelSign extends ModelBase {
  public ModelRenderer signBoard = new ModelRenderer(this, 0, 0);
  public ModelRenderer signStick = new ModelRenderer(this, 0, 14);
  /** La première corde du panneau. */
  public ModelRenderer signRope1 = new ModelRenderer(this, 8, 14);
  /** La seconde corde du panneau. */
  public ModelRenderer signRope2 = new ModelRenderer(this, 8, 14);

  public ModelSign() {
    this.signBoard.addBox(-12, -14, -1, 24, 12, 2, 0);
    this.signStick.addBox(-1, -2, -1, 2, 14, 2, 0);
    this.signRope1.addBox(8, -22, -1, 2, 8, 2, 0);
    this.signRope2.addBox(-9, -22, -1, 2, 8, 2, 0);
  }

  public void renderSign() {
    this.signBoard.render(0.0625F);
    this.signStick.render(0.0625F);
    this.signRope1.render(0.0625F);
    this.signRope2.render(0.0625F);
  }
}
