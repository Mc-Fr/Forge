package net.mcfr.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class NetworkUtils {
  @SideOnly(Side.CLIENT)
  public static EntityPlayer getLocalPlayer() {
    return Minecraft.getMinecraft().thePlayer;
  }

  @SideOnly(Side.CLIENT)
  public static World getLocalWorld() {
    return Minecraft.getMinecraft().thePlayer.worldObj;
  }

  @SideOnly(Side.CLIENT)
  public static boolean isPlayerAdmin() {
    try {
      List<String> admins = new ArrayList<>();
      URLConnection conn = new URL("http://www.minecraft-fr.net/launcher/adminList.txt").openConnection();

      try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8.name()))) {
        admins = rd.lines().collect(Collectors.toList());
      }

      return admins.contains(Minecraft.getMinecraft().getSession().getUsername().toLowerCase());
    }
    catch (IOException e) {}

    return false;
  }

  private NetworkUtils() {}
}
