package net.mcfr.equipment;

import net.mcfr.McfrItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCrossbowBolt extends EntityArrow {
  public EntityCrossbowBolt(World world, EntityLivingBase shooter) {
    super(world, shooter);
  }

  @Override
  protected ItemStack getArrowStack() {
    return new ItemStack(McfrItems.CROSSBOW_BOLT);
  }
}
