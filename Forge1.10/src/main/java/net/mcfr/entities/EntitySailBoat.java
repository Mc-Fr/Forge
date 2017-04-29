package net.mcfr.entities;

import net.mcfr.McfrItems;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * Entité du bateau à voiles.
 * 
 * @author Mc-Fr
 */
public class EntitySailBoat extends EntityBoat {
  /** État d'ouverture des voiles */
  private boolean sailOpen;

  /**
   * Crée un bateau à voiles. Constructeur requis par Forge.
   * 
   * @param world le monde
   */
  public EntitySailBoat(World world) {
    this(world, 0, 0, 0);
  }

  /**
   * Crée un bateau à voiles.
   * 
   * @param world le monde
   * @param x position x
   * @param y position y
   * @param z postiton z
   */
  public EntitySailBoat(World world, double x, double y, double z) {
    super(world, x, y, z);
    this.sailOpen = false;
  }

  /**
   * @return true si les voiles sont ouvertes
   */
  public boolean isSailOpen() {
    return this.sailOpen;
  }

  /**
   * Change l'état des voiles.
   * 
   * @param sailOpen si true, les voiles seront ouvertes
   */
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
