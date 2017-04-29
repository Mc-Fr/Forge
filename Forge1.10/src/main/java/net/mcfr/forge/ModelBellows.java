package net.mcfr.forge;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Modèle du soufflet.
 *
 * @author Mc-Fr
 */
public class ModelBellows extends ModelBase {
  private ModelRenderer top;
  private ModelRenderer middle1;
  private ModelRenderer middle2;
  private ModelRenderer middle3;
  private ModelRenderer bottom;

  public ModelBellows() {
    this.top = new ModelRenderer(this, 0, 0).setTextureSize(96, 64);
    this.middle1 = new ModelRenderer(this, 0, 32).setTextureSize(96, 64);
    this.middle2 = new ModelRenderer(this, 0, 32).setTextureSize(96, 64);
    this.middle3 = new ModelRenderer(this, 0, 32).setTextureSize(96, 64);
    this.bottom = new ModelRenderer(this, 0, 0).setTextureSize(96, 64);
    this.top.addBox(0, 2, 0, 16, 3, 16);
    this.middle1.addBox(3, 3, 3, 10, 10, 10);
    this.middle2.addBox(3, 3, 3, 10, 7, 10);
    this.middle3.addBox(3, 3, 3, 10, 4, 10);
    this.bottom.addBox(0, 0, 0, 16, 3, 16);
  }

  /**
   * Affiche le modèle.
   * 
   * @param height la hauteur en pixels
   */
  public void renderModel(int height) {
    this.top.offsetY = height / 16f;
    this.top.render(0.0625f);
    if (height > 7)
      this.middle1.render(0.0625f);
    else if (height > 4)
      this.middle2.render(0.0625f);
    else if (height > 1)
      this.middle3.render(0.0625f);
    this.bottom.render(0.0625f);
  }
}
