package net.mcfr.event;

import net.mcfr.decoration.container_blocks.tile_entities.TileEntityBarrel;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntitySaver;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Classe permettant de sauvegarder temporairement les tile entities de tonneaux se faisant détruire
 * afin de générer les items associés.
 *
 * @author Mc-Fr
 */
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
