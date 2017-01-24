package net.mcfr.forge.tileEntities;

import net.mcfr.utils.TileEntityUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityBellows extends TileEntity implements ITickable {
  /**
   * Le nombre de pas correspond au nombre de pixels (seixièmes de bloc) à parcourir dans
   * l'animation.
   */
  public static final int STEPS = 9;

  private boolean powered;
  private int step;
  private long lastTick;
  private boolean rising;

  public TileEntityBellows() {
    super();
    this.powered = false;
    this.step = 0;
    this.lastTick = 0;
    this.rising = false;
  }

  public boolean isPowered() {
    return this.powered;
  }

  public void setPowered(boolean isPowered) {
    this.powered = isPowered;
    if (getWorld() != null) {
      TileEntityUtils.sendTileEntityUpdate(getWorld(), this);
      markDirty();
    }
  }

  public int getStep() {
    return this.step;
  }

  @Override
  public void update() {
    if (isPowered()) {
      long tick = System.currentTimeMillis();

      if (tick - this.lastTick >= 100) {
        if (this.rising)
          this.step++;
        else
          this.step--;
        this.lastTick = tick;

        if (getStep() == STEPS - 1 || getStep() <= 0) {
          if (getStep() < 0)
            this.step = 0;
          this.rising = !this.rising;
        }
      }
      markDirty();
    }
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setBoolean("Powered", isPowered());
    compound.setInteger("Step", getStep());
    compound.setLong("LastTick", this.lastTick);
    compound.setBoolean("Rising", this.rising);

    return compound;
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    setPowered(compound.getBoolean("Powered"));
    this.step = compound.getInteger("Step");
    this.lastTick = compound.getLong("LastTick");
    this.rising = compound.getBoolean("Rising");
  }

  @Override
  public SPacketUpdateTileEntity getUpdatePacket() {
    return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
  }

  @Override
  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
    readFromNBT(pkt.getNbtCompound());
  }

  @Override
  public NBTTagCompound getUpdateTag() {
    return writeToNBT(new NBTTagCompound());
  }
}
