package net.mcfr.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

final class NetworkUtils {
  @SideOnly(Side.CLIENT)
  public static EntityPlayer getLocalPlayer() {
    return Minecraft.getMinecraft().thePlayer;
  }

  @SideOnly(Side.CLIENT)
  public static World getLocalWorld() {
    return Minecraft.getMinecraft().thePlayer.worldObj;
  }

  private NetworkUtils() {}
}
