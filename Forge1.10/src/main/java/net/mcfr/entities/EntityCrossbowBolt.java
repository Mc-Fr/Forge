package net.mcfr.entities;

import net.mcfr.McfrItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Entité du carreau d'arbalète.
 *
 * @author Mc-Fr
 */
public class EntityCrossbowBolt extends EntityArrow {
  /**
   * Crée un carreau.
   * 
   * @param world le monde
   * @param shooter le tireur
   */
  public EntityCrossbowBolt(World world, EntityLivingBase shooter) {
    super(world, shooter);
  }

  @Override
  protected ItemStack getArrowStack() {
    return new ItemStack(McfrItems.CROSSBOW_BOLT);
  }
}
