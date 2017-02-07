package net.mcfr.decoration.container_blocks.tile_entities;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Cette classe permet de sauvegarder des tile entities avec leur position dans le monde.
 *
 * @author Mc-Fr
 * 
 * @param <T> le type des tile entities
 */
public abstract class TileEntitySaver<T extends TileEntity> {
  private final Map<String, T> tileEntities;

  public TileEntitySaver() {
    this.tileEntities = new HashMap<>();
  }

  /**
   * Ajoute une tile entity.
   * 
   * @param world le monde
   * @param pos la position
   * @param tileEntity la tile entity
   */
  public void put(World world, BlockPos pos, T tileEntity) {
    this.tileEntities.put(getKey(world, pos), tileEntity);
  }

  /**
   * Retourne la tile entity correspondant à la position dans le monde donné.
   * 
   * @param world le monde
   * @param pos la position
   * @return la tile entity ou null si aucune n'a été trouvée
   */
  public T get(World world, BlockPos pos) {
    return this.tileEntities.get(getKey(world, pos));
  }

  /**
   * Supprime la tile entity correspondant à la position dans le monde donné.
   * 
   * @param world le monde
   * @param pos la position
   */
  public void remove(World world, BlockPos pos) {
    this.tileEntities.remove(getKey(world, pos));
  }

  /**
   * Retourne la clé correspondant au monde et à la position donnés.
   * 
   * @param world le monde
   * @param pos la position
   * @return la clé
   */
  private String getKey(World world, BlockPos pos) {
    return world.getWorldInfo().getWorldName() + "@" + pos.toString();
  }
}
