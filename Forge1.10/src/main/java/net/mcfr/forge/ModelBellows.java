package net.mcfr.forge;

import java.lang.reflect.Field;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBellows extends ModelBase {
  private ModelRenderer top;
  private ModelRenderer bottom;
  private ModelRenderer middle;

  public ModelBellows() {
    this.top = new ModelRenderer(this, 0, 0).setTextureSize(96, 64);
    this.middle = new ModelRenderer(this, 0, 32).setTextureSize(96, 64);
    this.bottom = new ModelRenderer(this, 0, 0).setTextureSize(96, 64);
    this.bottom.addBox(0, 0, 0, 16, 3, 16, 0);
  }

  public void renderBellows(int height) {
    Field f = null;

    try {
      f = ModelRenderer.class.getDeclaredField("t");
    }
    catch (NoSuchFieldException | SecurityException ex) {
      try {
        f = ModelRenderer.class.getDeclaredField("compiled");
      }
      catch (NoSuchFieldException | SecurityException ex1) {
        ex1.printStackTrace();
      }
    }
    if (f != null) {
      try {
        f.setAccessible(true);
        f.set(this.top, false);
        f.set(this.middle, false);
      }
      catch (IllegalArgumentException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    this.top.cubeList.clear();
    this.top.addBox(0, height + 2, 0, 16, 3, 16, 0);
    this.middle.cubeList.clear();
    this.middle.addBox(3, 3, 3, 10, height, 10, 0);
    this.top.render(0.0625F);
    this.middle.render(0.0625F);
    this.bottom.render(0.0625F);
  }
}
