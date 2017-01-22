package net.mcfr.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public final class TileEntityUtils {
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
