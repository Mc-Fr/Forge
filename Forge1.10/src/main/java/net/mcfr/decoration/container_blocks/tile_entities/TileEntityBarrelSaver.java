package net.mcfr.decoration.container_blocks.tile_entities;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TileEntityBarrelSaver extends TileEntitySaver<TileEntityBarrel> {
  /**
   * Récupère l'évènement de destruction d'un bloc par le joueur et sauvegarde la tile entity
   * associée si le bloc est un tonneau.
   */
  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    TileEntity tileEntity = event.getWorld().getTileEntity(event.getPos());
    if (tileEntity instanceof TileEntityBarrel)
      put(event.getWorld(), event.getPos(), (TileEntityBarrel) tileEntity);
  }
}
