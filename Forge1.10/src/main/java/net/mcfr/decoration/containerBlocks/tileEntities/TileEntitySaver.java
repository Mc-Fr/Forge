package net.mcfr.decoration.containerBlocks.tileEntities;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Cette classe permet de sauvegarder des tile entities avec leur position dans le monde.
 *
 * @author Mc-Fr
 */
public abstract class TileEntitySaver<T extends TileEntity> {
  private final Map<String, T> tileEntities;

  public TileEntitySaver() {
    this.tileEntities = new HashMap<>();
  }

  public void put(World world, BlockPos pos, T tileEntity) {
    this.tileEntities.put(getKey(world, pos), tileEntity);
  }

  public T get(World world, BlockPos pos) {
    return this.tileEntities.get(getKey(world, pos));
  }

  public void remove(World world, BlockPos pos) {
    this.tileEntities.remove(getKey(world, pos));
  }

  private String getKey(World world, BlockPos pos) {
    return world.getWorldInfo().getWorldName() + "@" + pos.toString();
  }
}
