package net.mcfr.entities;

import net.mcfr.McfrItems;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * Cette classe représente un bateau à voiles.
 * 
 * @author Mc-Fr
 */
public class EntitySailBoat extends EntityBoat {
  private boolean sailOpen;

  public EntitySailBoat(World worldIn) {
    this(worldIn, 0, 0, 0);
  }

  public EntitySailBoat(World worldIn, double x, double y, double z) {
    super(worldIn, x, y, z);
    this.sailOpen = false;
  }

  public boolean isSailOpen() {
    return this.sailOpen;
  }

  public void setSailOpen(boolean sailOpen) {
    this.sailOpen = sailOpen;
  }

  @Override
  public Item getItemBoat() {
    return McfrItems.SAILBOAT;
  }

  @Override
  public void onUpdate() {
    super.onUpdate();
    setSailOpen(!getPassengers().isEmpty());
  }
}
