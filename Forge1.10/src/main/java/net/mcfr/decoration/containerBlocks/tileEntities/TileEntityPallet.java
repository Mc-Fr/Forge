package net.mcfr.decoration.containerBlocks.tileEntities;

import net.mcfr.decoration.containerBlocks.BlockPallet;
import net.mcfr.decoration.containerBlocks.guis.ContainerRestricted;
import net.mcfr.utils.TileEntityUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;

public class TileEntityPallet extends TileEntityRestricted {
  public TileEntityPallet() {
    super("pallet", ContainerRestricted.SIZE, 64, false, BlockPallet.class, ContainerRestricted.class);
  }

  @Override
  public void closeInventory(EntityPlayer player) {
    super.closeInventory(player);
    TileEntityUtils.sendTileEntityUpdate(getWorld(), this);
  }

  @Override
  public SPacketUpdateTileEntity getUpdatePacket() {
    NBTTagCompound c = new NBTTagCompound();
    writeToNBT(c);
    SPacketUpdateTileEntity packet = new SPacketUpdateTileEntity(getPos(), 0, c);

    return packet;
  }

  @Override
  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
    readFromNBT(pkt.getNbtCompound());
  }
}
