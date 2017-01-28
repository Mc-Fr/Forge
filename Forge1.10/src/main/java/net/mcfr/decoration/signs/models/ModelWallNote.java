package net.mcfr.decoration.signs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelWallNote extends ModelBase {
  public ModelRenderer signBoard = new ModelRenderer(this, 0, 0);

  public ModelWallNote() {
    this.signBoard.addBox(-32, -26, 0, 32 * 2, 32, 0, 0);
  }

  public void renderModel() {
    this.signBoard.render(0.0625F);
  }
}
