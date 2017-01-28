package net.mcfr.decoration.tileEntities;

import static net.mcfr.utils.RenderUtils.*;

import net.mcfr.McfrBlocks;
import net.mcfr.utils.math.Point2d;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Cette classe s'occupe du rendu des tapis.
 *
 * @author Mc-Fr
 */
public class TileEntityCarpetRenderer extends TileEntitySpecialRenderer<TileEntityCarpet> {
  /** Hauteur des tapis */
  private static final double H = 1 / 16d;
  /** Largeur des tapis sur les côtés égale à la hauteur (alias pour la clarté). */
  private static final double W = H;

  @Override
  public void renderTileEntityAt(TileEntityCarpet te, double x, double y, double z, float partialTicks, int destroyStage) {
    World world = te.getWorld();
    BlockPos down = te.getPos().down();

    GlStateManager.pushMatrix();
    GlStateManager.translate(x, y, z);

    bindTex("minecraft:textures/blocks/wool_colored_" + EnumDyeColor.byMetadata(te.getMetadata()).getName() + ".png");

    if (isBottomHalfSlab(world, down)) {
      renderNormal(-0.5);
    }
    else if (isBottomStairs(world, down)) {
      IBlockState state = world.getBlockState(down);
      state = ((BlockStairs) state.getBlock()).getActualState(state, world, down);
      renderStairs(state.getValue(BlockStairs.SHAPE), state.getValue(BlockStairs.FACING));
    }
    else {
      renderNormal(0);
      renderFullSide(world, te.getPos());
    }

    draw();

    GlStateManager.popMatrix();
  }

  private void renderFullSide(World world, BlockPos pos) {
    if (canRenderFullSide(world, pos.north())) {
      // Dessus.
      vertex(0, 1 + H, 0, 0, 0);
      vertex(0, 1 + H, W, 0, W);
      vertex(1, 1 + H, W, 1, W);
      vertex(1, 1 + H, 0, 1, 0);
      // Face nord.
      vertex(0, H, 0, 1, 1 - H);
      vertex(0, 1, 0, 1, 0);
      vertex(1, 1, 0, 0, 0);
      vertex(1, H, 0, 0, 1 - H);
      // Face est.
      vertex(1, H, W, 1 - W, 1 - H);
      vertex(1, H, 0, 1, 1 - H);
      vertex(1, 1 + H, 0, 1, -H);
      vertex(1, 1 + H, W, 1 - W, -H);
      // Face sud.
      vertex(0, H, W, 0, 1 - H);
      vertex(1, H, W, 1, 1 - H);
      vertex(1, 1 + H, W, 1, -H);
      vertex(0, 1 + H, W, 0, -H);
      // Face ouest.
      vertex(0, H, 0, 0, 1 - H);
      vertex(0, H, W, W, 1 - H);
      vertex(0, 1 + H, W, W, -H);
      vertex(0, 1 + H, 0, 0, -H);
    }
    if (canRenderFullSide(world, pos.east())) {
      // Dessus.
      vertex(1 - W, 1 + H, 0, 1 - W, 0);
      vertex(1 - W, 1 + H, 1, 1 - W, 1);
      vertex(1, 1 + H, 1, 1, 1);
      vertex(1, 1 + H, 0, 1, 0);
      // Face nord.
      vertex(1 - W, H, 0, W, 1 - H);
      vertex(1 - W, 1 + H, 0, W, -H);
      vertex(1, 1 + H, 0, 0, -H);
      vertex(1, H, 0, 0, 1 - H);
      // Face est.
      vertex(1, H, 1, 0, 1 - H);
      vertex(1, H, 0, 1, 1 - H);
      vertex(1, 1, 0, 1, 0);
      vertex(1, 1, 1, 0, 0);
      // Face sud.
      vertex(1 - W, H, 1, 1 - W, 1 - H);
      vertex(1, H, 1, 1, 1 - H);
      vertex(1, 1 + H, 1, 1, -H);
      vertex(1 - W, 1 + H, 1, 1 - W, -H);
      // Face ouest.
      vertex(1 - W, H, 0, 0, 1 - H);
      vertex(1 - W, H, 1, 1, 1 - H);
      vertex(1 - W, 1 + H, 1, 1, -H);
      vertex(1 - W, 1 + H, 0, 0, -H);
    }
    if (canRenderFullSide(world, pos.south())) {
      // Dessus.
      vertex(0, 1 + H, 1 - W, 0, 1 - W);
      vertex(0, 1 + H, 1, 0, 1);
      vertex(1, 1 + H, 1, 1, 1);
      vertex(1, 1 + H, 1 - W, 1, 1 - W);
      // Face nord.
      vertex(0, H, 1 - W, 1, 1 - H);
      vertex(0, 1 + H, 1 - W, 1, -H);
      vertex(1, 1 + H, 1 - W, 0, -H);
      vertex(1, H, 1 - W, 0, 1 - H);
      // Face est.
      vertex(1, H, 1, 0, 1 - H);
      vertex(1, H, 1 - W, W, 1 - H);
      vertex(1, 1 + H, 1 - W, W, -H);
      vertex(1, 1 + H, 1, 0, -H);
      // Face sud.
      vertex(0, H, 1, 0, 1 - H);
      vertex(1, H, 1, 1, 1 - H);
      vertex(1, 1, 1, 1, 0);
      vertex(0, 1, 1, 0, 0);
      // Face ouest.
      vertex(0, H, 1 - W, 1 - W, 1 - H);
      vertex(0, H, 1, 1, 1 - H);
      vertex(0, 1 + H, 1, 1, -H);
      vertex(0, 1 + H, 1 - W, 1 - W, -H);
    }
    if (canRenderFullSide(world, pos.west())) {
      // Dessus.
      vertex(0, 1 + H, 0, 0, 0);
      vertex(0, 1 + H, 1, 0, 1);
      vertex(W, 1 + H, 1, W, 1);
      vertex(W, 1 + H, 0, W, 0);
      // Face nord.
      vertex(0, H, 0, 1, 1 - H);
      vertex(0, 1 + H, 0, 1, -H);
      vertex(W, 1 + H, 0, 1 - W, -H);
      vertex(W, H, 0, 1 - W, 1 - H);
      // Face est.
      vertex(W, H, 1, 0, 1 - H);
      vertex(W, H, 0, 1, 1 - H);
      vertex(W, 1 + H, 0, 1, -H);
      vertex(W, 1 + H, 1, 0, -H);
      // Face sud.
      vertex(0, H, 1, 0, 1 - H);
      vertex(W, H, 1, W, 1 - H);
      vertex(W, 1 + H, 1, W, -H);
      vertex(0, 1 + H, 1, 0, -H);
      // Face ouest.
      vertex(0, H, 0, 0, 1 - H);
      vertex(0, H, 1, 1, 1 - H);
      vertex(0, 1, 1, 1, 0);
      vertex(0, 1, 0, 0, 0);
    }
  }

  private void renderNormal(double offset) {
    double b = offset;
    double h = H + offset;

    // Dessus.
    vertex(0, h, 0, 0, 0);
    vertex(0, h, 1, 0, 1);
    vertex(1, h, 1, 1, 1);
    vertex(1, h, 0, 1, 0);
    // Dessous.
    vertex(0, b, 0, 0, 1);
    vertex(1, b, 0, 1, 1);
    vertex(1, b, 1, 1, 0);
    vertex(0, b, 1, 0, 0);
    // Face nord.
    vertex(0, b, 0, 1, 1 - h);
    vertex(0, h, 0, 1, 1 - b);
    vertex(1, h, 0, 0, 1 - b);
    vertex(1, b, 0, 0, 1 - h);
    // Face est.
    vertex(1, b, 0, 1, 1 - h);
    vertex(1, h, 0, 1, 1 - b);
    vertex(1, h, 1, 0, 1 - b);
    vertex(1, b, 1, 0, 1 - h);
    // Face sud.
    vertex(1, b, 1, 1, 1 - h);
    vertex(1, h, 1, 1, 1 - b);
    vertex(0, h, 1, 0, 1 - b);
    vertex(0, b, 1, 0, 1 - h);
    // Face ouest.
    vertex(0, b, 1, 1, 1 - h);
    vertex(0, h, 1, 1, 1 - b);
    vertex(0, h, 0, 0, 1 - b);
    vertex(0, b, 0, 0, 1 - h);
  }

  @SuppressWarnings("incomplete-switch")
  private void renderStairs(BlockStairs.EnumShape shape, EnumFacing facing) {
    switch (shape) {
      case INNER_LEFT:
        switch (facing) {
          case NORTH:
            renderInnerStairs(0);
            break;
          case EAST:
            renderInnerStairs(-90);
            break;
          case SOUTH:
            renderInnerStairs(180);
            break;
          case WEST:
            renderInnerStairs(90);
            break;
        }
        break;
      case INNER_RIGHT:
        switch (facing) {
          case NORTH:
            renderInnerStairs(-90);
            break;
          case EAST:
            renderInnerStairs(180);
            break;
          case SOUTH:
            renderInnerStairs(90);
            break;
          case WEST:
            renderInnerStairs(0);
            break;
        }
        break;
      case OUTER_LEFT:
        switch (facing) {
          case NORTH:
            renderOuterStairs(0);
            break;
          case EAST:
            renderOuterStairs(-90);
            break;
          case SOUTH:
            renderOuterStairs(180);
            break;
          case WEST:
            renderOuterStairs(90);
            break;
        }
        break;
      case OUTER_RIGHT:
        switch (facing) {
          case NORTH:
            renderOuterStairs(-90);
            break;
          case EAST:
            renderOuterStairs(180);
            break;
          case SOUTH:
            renderOuterStairs(90);
            break;
          case WEST:
            renderOuterStairs(0);
            break;
        }
        break;
      case STRAIGHT:
        switch (facing) {
          case NORTH:
            renderStraightStairs(0);
            break;
          case EAST:
            renderStraightStairs(-90);
            break;
          case SOUTH:
            renderStraightStairs(180);
            break;
          case WEST:
            renderStraightStairs(-270);
            break;
        }
        break;
    }
  }

  private void renderStraightStairs(int rotation) {
    double b = -0.5;
    double h = H + b;
    double l = 0.5;
    Point2d[][] points = { //
      {new Point2d(0, 0), new Point2d(0, l + H), new Point2d(1, l + H), new Point2d(1, 0), new Point2d(0, l + H), new Point2d(0, 1), new Point2d(1, 1), new Point2d(1, l + H)}, //
      {new Point2d(1, 0), new Point2d(l - W, 0), new Point2d(l - W, 1), new Point2d(1, 1), new Point2d(l - W, 0), new Point2d(0, 0), new Point2d(0, 1), new Point2d(l - W, 1)}, //
      {new Point2d(1, 1), new Point2d(1, l - H), new Point2d(0, l - H), new Point2d(0, 1), new Point2d(1, l - H), new Point2d(1, 0), new Point2d(0, 0), new Point2d(0, l - H)}, //
      {new Point2d(0, 1), new Point2d(l + W, 1), new Point2d(l + W, 0), new Point2d(0, 0), new Point2d(l + W, 1), new Point2d(1, 1), new Point2d(1, 0), new Point2d(l + W, 0)} //
    };
    Point2d[] p = points[0];

    GlStateManager.rotate(rotation, 0, 1, 0);
    if (rotation == -90 || rotation == 270) {
      GlStateManager.translate(0, 0, -1);
      p = points[1];
    }
    else if (Math.abs(rotation) == 180) {
      GlStateManager.translate(-1, 0, -1);
      p = points[2];
    }
    else if (rotation == 90 || rotation == -270) {
      GlStateManager.translate(-1, 0, 0);
      p = points[3];
    }

    // Dessus (haut).
    vertex(0, H, 0, p[0]);
    vertex(0, H, l + W, p[1]);
    vertex(1, H, l + W, p[2]);
    vertex(1, H, 0, p[3]);
    // Dessus (bas).
    vertex(0, h, l + W, p[4]);
    vertex(0, h, 1, p[5]);
    vertex(1, h, 1, p[6]);
    vertex(1, h, l + W, p[7]);
    // Face nord.
    vertex(0, 0, 0, 1, 1);
    vertex(0, H, 0, 1, 1 - H);
    vertex(1, H, 0, 0, 1 - H);
    vertex(1, 0, 0, 0, 1);
    // Face est (bas).
    vertex(1, b, 1, 0, l);
    vertex(1, b, l, l, l);
    vertex(1, h, l, l, l - H);
    vertex(1, h, 1, 0, l - H);
    // Face est (verticale).
    vertex(1, h, l + W, l - W, l - H);
    vertex(1, h, l, l, l - H);
    vertex(1, 0, l, l, 0);
    vertex(1, 0, l + W, l - W, 0);
    // Face est (haut).
    vertex(1, 0, l + W, l - W, 1);
    vertex(1, 0, 0, 1, 1);
    vertex(1, H, 0, 1, 1 - H);
    vertex(1, H, l + W, l - W, 1 - H);
    // Face sud (millieu).
    vertex(0, h, l + W, 0, l - H);
    vertex(1, h, l + W, 1, l - H);
    vertex(1, H, l + W, 1, -H);
    vertex(0, H, l + W, 0, -H);
    // Face sud (bas).
    vertex(0, b, 1, 0, l);
    vertex(1, b, 1, 1, l);
    vertex(1, h, 1, 1, l - H);
    vertex(0, h, 1, 0, l - H);
    // Face ouest (bas).
    vertex(0, b, l, l, l);
    vertex(0, b, 1, 1, l);
    vertex(0, h, 1, 1, l - H);
    vertex(0, h, l, l, l - H);
    // Face ouest (verticale).
    vertex(0, h, l, l, l - H);
    vertex(0, h, l + W, l + W, l - H);
    vertex(0, 0, l + W, l + W, 0);
    vertex(0, 0, l, l, 0);
    // Face ouest (haut).
    vertex(0, 0, 0, 0, 1);
    vertex(0, 0, l + W, l + W, 1);
    vertex(0, H, l + W, l + W, 1 - H);
    vertex(0, H, 0, 0, 1 - H);
  }

  private void renderInnerStairs(int rotation) {
    double b = -0.5;
    double h = H + b;
    double l = 0.5;
    Point2d[][] points = { //
      {new Point2d(0, 0), new Point2d(0, l + H), new Point2d(1, l + H), new Point2d(1, 0), new Point2d(0, l + H), new Point2d(0, 1), new Point2d(l + W, 1), new Point2d(l + W, l + H), new Point2d(l + W, l + H), new Point2d(l + W, 1), new Point2d(1, 1), new Point2d(1, l + H)}, //
      {new Point2d(1, 0), new Point2d(l - W, 0), new Point2d(l - W, 1), new Point2d(1, 1), new Point2d(l - W, 0), new Point2d(0, 0), new Point2d(0, l + H), new Point2d(l - W, l + H), new Point2d(l - W, l + H), new Point2d(0, l + H), new Point2d(0, 1), new Point2d(l - W, 1)}, //
      {new Point2d(1, 1), new Point2d(1, l - H), new Point2d(0, l - H), new Point2d(0, 1), new Point2d(1, l - H), new Point2d(1, 0), new Point2d(l - W, 0), new Point2d(l - W, l - H), new Point2d(l - W, l - H), new Point2d(l - W, 0), new Point2d(0, 0), new Point2d(0, l - H)}, //
      {new Point2d(0, 1), new Point2d(l + W, 1), new Point2d(l + W, 0), new Point2d(0, 0), new Point2d(l + W, 1), new Point2d(1, 1), new Point2d(1, l - H), new Point2d(l + W, l - H), new Point2d(l + W, l - H), new Point2d(1, l - H), new Point2d(1, 0), new Point2d(l + W, 0)} //
    };
    Point2d[] p = points[0];

    GlStateManager.rotate(rotation, 0, 1, 0);
    if (rotation == -90 || rotation == 270) {
      GlStateManager.translate(0, 0, -1);
      p = points[1];
    }
    else if (Math.abs(rotation) == 180) {
      GlStateManager.translate(-1, 0, -1);
      p = points[2];
    }
    else if (rotation == 90 || rotation == -270) {
      GlStateManager.translate(-1, 0, 0);
      p = points[3];
    }

    // Dessus (haut).
    vertex(0, H, 0, p[0]);
    vertex(0, H, l + W, p[1]);
    vertex(1, H, l + W, p[2]);
    vertex(1, H, 0, p[3]);
    // Dessus (haut 2).
    vertex(0, H, l + W, p[4]);
    vertex(0, H, 1, p[5]);
    vertex(l + W, H, 1, p[6]);
    vertex(l + W, H, l + W, p[7]);
    // Dessus (bas).
    vertex(l + W, h, l + W, p[8]);
    vertex(l + W, h, 1, p[9]);
    vertex(1, h, 1, p[10]);
    vertex(1, h, l + W, p[11]);
    // Face nord.
    vertex(0, 0, 0, 1, 1);
    vertex(0, H, 0, 1, 1 - H);
    vertex(1, H, 0, 0, 1 - H);
    vertex(1, 0, 0, 0, 1);
    // Face est (bas).
    vertex(1, b, 1, 0, l);
    vertex(1, b, l, l, l);
    vertex(1, h, l, l, l - H);
    vertex(1, h, 1, 0, l - H);
    // Face est (verticale).
    vertex(1, h, l + W, l - W, l - H);
    vertex(1, h, l, l, l - H);
    vertex(1, 0, l, l, 0);
    vertex(1, 0, l + W, l - W, 0);
    // Face est (haut).
    vertex(1, 0, l + W, l - W, 1);
    vertex(1, 0, 0, 1, 1);
    vertex(1, H, 0, 1, 1 - H);
    vertex(1, H, l + W, l - W, 1 - H);
    // Face est (milieu).
    vertex(l + W, h, 1, 0, l - H);
    vertex(l + W, h, l + W, l - W, l - H);
    vertex(l + W, H, l + W, l - W, -H);
    vertex(l + W, H, 1, 0, -H);
    // Face sud (bas).
    vertex(l, b, 1, l, l);
    vertex(1, b, 1, 1, l);
    vertex(1, h, 1, 1, l - H);
    vertex(l, h, 1, l, l - H);
    // Face sud (verticale).
    vertex(l + W, h, 1, l, l);
    vertex(l + W, 0, 1, l, W);
    vertex(l, 0, 1, l + W, W);
    vertex(l, h, 1, l + W, l);
    // Face sud (haut).
    vertex(0, 0, 1, 0, 0);
    vertex(l + W, 0, 1, l + W, 0);
    vertex(l + W, H, 1, l + W, -H);
    vertex(0, H, 1, 0, -H);
    // Face sud (millieu).
    vertex(l + W, h, l + W, l + W, l - H);
    vertex(1, h, l + W, 1, l - H);
    vertex(1, H, l + W, 1, -H);
    vertex(l + W, H, l + W, l + W, -H);
    // Face ouest.
    vertex(0, 0, 1, 1, 1 - H);
    vertex(0, H, 1, 1, 1);
    vertex(0, H, 0, 0, 1);
    vertex(0, 0, 0, 0, 1 - H);
  }

  private void renderOuterStairs(int rotation) {
    double b = -0.5;
    double h = H + b;
    double l = 0.5;
    Point2d[][] points = { //
      {new Point2d(0, 0), new Point2d(0, l + H), new Point2d(l + W, l + H), new Point2d(l + W, 0), new Point2d(0, l + H), new Point2d(0, 1), new Point2d(1, 1), new Point2d(1, l + H), new Point2d(l + W, 0), new Point2d(l + W, l + H), new Point2d(1, l + H), new Point2d(1, 0)}, //
      {new Point2d(1, 0), new Point2d(l - W, 0), new Point2d(l - W, l + H), new Point2d(1, l + H), new Point2d(l - W, 0), new Point2d(0, 0), new Point2d(0, 1), new Point2d(l - W, 1), new Point2d(1, l + H), new Point2d(l - W, l + H), new Point2d(l - W, 1), new Point2d(1, 1)}, //
      {new Point2d(1, 1), new Point2d(1, l - H), new Point2d(l - W, l - H), new Point2d(l - W, 1), new Point2d(1, l - H), new Point2d(1, 0), new Point2d(0, 0), new Point2d(0, l - H), new Point2d(l - W, 1), new Point2d(l - W, l - H), new Point2d(0, l - H), new Point2d(0, 1)}, //
      {new Point2d(0, 1), new Point2d(l + W, 1), new Point2d(l + W, l - H), new Point2d(0, l - H), new Point2d(l + W, 1), new Point2d(1, 1), new Point2d(1, 0), new Point2d(l + W, 0), new Point2d(0, l - H), new Point2d(l + W, l - H), new Point2d(l + W, 0), new Point2d(0, 0)} //
    };
    Point2d[] p = points[0];

    GlStateManager.rotate(rotation, 0, 1, 0);
    if (rotation == -90 || rotation == 270) {
      GlStateManager.translate(0, 0, -1);
      p = points[1];
    }
    else if (Math.abs(rotation) == 180) {
      GlStateManager.translate(-1, 0, -1);
      p = points[2];
    }
    else if (rotation == 90 || rotation == -270) {
      GlStateManager.translate(-1, 0, 0);
      p = points[3];
    }

    // Dessus (haut).
    vertex(0, H, 0, p[0]);
    vertex(0, H, l + W, p[1]);
    vertex(l + W, H, l + W, p[2]);
    vertex(l + W, H, 0, p[3]);
    // Dessus (bas).
    vertex(0, h, l + W, p[4]);
    vertex(0, h, 1, p[5]);
    vertex(1, h, 1, p[6]);
    vertex(1, h, l + W, p[7]);
    // Dessus (bas 2).
    vertex(l + W, h, 0, p[8]);
    vertex(l + W, h, l + W, p[9]);
    vertex(1, h, l + W, p[10]);
    vertex(1, h, 0, p[11]);
    // Face nord (bas).
    vertex(l, b, 0, l, l);
    vertex(l, h, 0, l, l - H);
    vertex(1, h, 0, 0, l - H);
    vertex(1, b, 0, 0, l);
    // Face nord (verticale).
    vertex(l, h, 0, l, l - H);
    vertex(l, 0, 0, l, 0);
    vertex(l + W, 0, 0, l - W, 0);
    vertex(l + W, h, 0, l - W, l - H);
    // Face nord (haut).
    vertex(0, 0, 0, 1, 0);
    vertex(0, H, 0, 1, -H);
    vertex(l + W, H, 0, l - W, -H);
    vertex(l + W, 0, 0, l - W, 0);
    // Face est (bas).
    vertex(1, b, 0, 1, l);
    vertex(1, h, 0, 1, l - H);
    vertex(1, h, 1, 0, l - H);
    vertex(1, b, 1, 0, l);
    // Face est (milieu).
    vertex(l + W, h, l + W, l - W, l - H);
    vertex(l + W, h, 0, 1, l - H);
    vertex(l + W, H, 0, 1, -H);
    vertex(l + W, H, l + W, l - W, -H);
    // Face sud (bas).
    vertex(0, b, 1, 0, l);
    vertex(1, b, 1, 1, l);
    vertex(1, h, 1, 1, l - H);
    vertex(0, h, 1, 0, l - H);
    // Face sud (millieu).
    vertex(0, h, l + W, 0, l - H);
    vertex(l + W, h, l + W, l + W, l - H);
    vertex(l + W, H, l + W, l + W, -H);
    vertex(0, H, l + W, 0, -H);
    // Face ouest (bas).
    vertex(0, b, l, l, l);
    vertex(0, b, 1, 1, l);
    vertex(0, h, 1, 1, l - H);
    vertex(0, h, l, l, l - H);
    // Face ouest (verticale).
    vertex(0, h, l, l, l - H);
    vertex(0, h, l + W, l + W, l - H);
    vertex(0, 0, l + W, l + W, 0);
    vertex(0, 0, l, l, 0);
    // Face ouest (haut).
    vertex(0, 0, 0, 0, 1);
    vertex(0, 0, l + W, l + W, 1);
    vertex(0, H, l + W, l + W, 1 - H);
    vertex(0, H, 0, 0, 1 - H);
  }

  /**
   * Indique si le bloc à la position donnée est un bloc plein (air compris).
   */
  private boolean canRenderFullSide(World world, BlockPos pos) {
    return world.getBlockState(pos.up()).getBlock() == McfrBlocks.CARPET && world.getBlockState(pos).isFullCube();
  }

  /**
   * Indique si le bloc à la position donnée est la moitié inférieure d'une dalle.
   */
  private boolean isBottomHalfSlab(IBlockAccess worldIn, BlockPos pos) {
    IBlockState state = worldIn.getBlockState(pos);
    return state.getBlock() instanceof BlockSlab && state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM;
  }

  /**
   * Indique si le bloc à la position donnée est un escalier en position basse (normale).
   */
  private boolean isBottomStairs(IBlockAccess worldIn, BlockPos pos) {
    IBlockState state = worldIn.getBlockState(pos);
    return state.getBlock() instanceof BlockStairs && state.getValue(BlockStairs.HALF) == BlockStairs.EnumHalf.BOTTOM;
  }
}
