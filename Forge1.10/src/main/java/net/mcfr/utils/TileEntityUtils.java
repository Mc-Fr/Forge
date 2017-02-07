package net.mcfr.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Cette classe fournit une méthode pour faciliter la synchronisation des tile entities.
 *
 * @author Mc-Fr
 */
public final class TileEntityUtils {
  /**
   * Envoie un paquet de mise à jour à tous les joueurs connectés.
   * 
   * @param world le monde
   * @param te la tile entity
   */
  public static void sendTileEntityUpdate(World world, TileEntity te) {
    if (!world.isRemote) {
      for (EntityPlayer p : world.playerEntities) {
        if (p instanceof EntityPlayerMP)
          ((EntityPlayerMP) p).connection.sendPacket(te.getUpdatePacket());
      }
    }
  }

  private TileEntityUtils() {}
}
