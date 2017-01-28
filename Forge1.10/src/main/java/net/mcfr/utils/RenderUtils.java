package net.mcfr.utils;

import org.lwjgl.opengl.GL11;

import net.mcfr.utils.math.Point2d;
import net.mcfr.utils.math.Point3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public final class RenderUtils {
  public static final int TOP_OFFSET = 17;
  public static final int INV_SEPARATOR = 14;
  public static final int HOTBAR_SEPARATOR = 4;
  public static final int BOTTOM_OFFSET = 7;
  public static final int SIDE_OFFSET = 8;
  public static final int SLOT_SIZE = 18;

  /**
   * @return le WorldRenderer
   */
  public static VertexBuffer getRenderer() {
    return Tessellator.getInstance().getBuffer();
  }

  /**
   * Démarre le mode GL_QUADS.
   */
  public static void beginQuads() {
    getRenderer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
  }

  /**
   * Associe une texture au WorldRenderer.
   *
   * @param texturePath le chemin de la texture
   */
  public static void bindTex(String texturePath) {
    beginQuads();
    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(texturePath));
  }

  /**
   * Déclare un sommet.
   *
   * @param x la coordonnée x
   * @param y la coordonnée y
   * @param z la coordonnée z
   * @param texX le coordonnée x de la texture
   * @param texY le coordonnée y de la texture
   */
  public static void vertex(double x, double y, double z, double texX, double texY) {
    getRenderer().pos(x, y, z).tex(texX, texY).endVertex();
  }

  /**
   * Déclare un sommet.
   *
   * @param x la coordonnée x
   * @param y la coordonnée y
   * @param z la coordonnée z
   * @param tex la coordonnée de la texture
   */
  public static void vertex(double x, double y, double z, Point2d tex) {
    vertex(x, y, z, tex.getX(), tex.getY());
  }

  /**
   * Déclare un sommet.
   *
   * @param ver le sommet
   * @param tex la coordonnée de la texture
   */
  public static void vertex(Point3d ver, Point2d tex) {
    vertex(ver.getX(), ver.getY(), ver.getZ(), tex);
  }

  /**
   * Dessine un quadrilatère.
   *
   * @param p1 le premier point
   * @param p2 le deuxième point
   * @param p3 le troisième point
   * @param p4 le quatrième point
   * @param tex1 le premier point de la texture
   * @param tex2 le deuxième point de la texture
   * @param tex3 le troisième point de la texture
   * @param tex4 le quatrième point de la texture
   */
  public static void drawQuad(Point3d p1, Point3d p2, Point3d p3, Point3d p4, Point2d tex1, Point2d tex2, Point2d tex3, Point2d tex4) {
    vertex(p1, tex1);
    vertex(p2, tex2);
    vertex(p3, tex3);
    vertex(p4, tex4);
  }

  /**
   * Affiche un item.
   *
   * @param stack le stack contenant l'item
   */
  public static void renderItem(ItemStack stack) {
    Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
  }

  /**
   * Dessine le modèle.
   */
  public static void draw() {
    Tessellator.getInstance().draw();
  }

  private RenderUtils() {}
}
