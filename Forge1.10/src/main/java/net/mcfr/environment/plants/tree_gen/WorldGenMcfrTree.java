package net.mcfr.environment.plants.tree_gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

/**
 * Classes de base de génération d'arbres.
 * 
 * @author Mc-Fr
 */
public abstract class WorldGenMcfrTree extends WorldGenAbstractTree {
  /** The minimum height of a generated tree. */
  protected final int minTreeHeight;
  /** The metadata value of the wood to use in tree generation. */
  protected final IBlockState wood;
  /** The metadata value of the leaves to use in tree generation. */
  protected final IBlockState leaves;

  /**
   * Crée un générateur.
   * 
   * @param notify doit-on avertir de la mise à jour
   * @param minTreeHeight la hauteur minimale de l'arbre
   * @param wood le bois à utiliser
   * @param leaves les feuilles à utiliser
   */
  public WorldGenMcfrTree(boolean notify, int minTreeHeight, IBlockState wood, IBlockState leaves) {
    super(notify);
    this.minTreeHeight = minTreeHeight;
    this.wood = wood;
    this.leaves = leaves;
  }

  /**
   * Vérifie qu'il y ait une hauteur suffisante pour que l'arbre puisse pouser.
   * 
   * @param world le monde
   * @param position la position
   * @param height la hauteur
   * @return true si l'arbre peut pousser
   */
  protected boolean checkHeight(World world, BlockPos position, int height) {
    boolean canGrow = true;

    if (position.getY() >= 1 && position.getY() + height + 1 <= world.getHeight()) {
      for (int y = position.getY(); y <= position.getY() + 1 + height; y++) {
        int i = 1;

        if (y == position.getY())
          i = 0;
        if (y >= position.getY() + 1 + height - 2)
          i = 2;

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = position.getX() - i; x <= position.getX() + i && canGrow; x++) {
          for (int z = position.getZ() - i; z <= position.getZ() + i && canGrow; z++) {
            if (y < 0 && y >= world.getHeight() || !isReplaceable(world, pos.setPos(x, y, z))) {
              canGrow = false;
              break;
            }
          }
        }
      }
    }
    else
      canGrow = false;

    return canGrow;
  }
}
