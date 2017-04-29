package net.mcfr.equipment;

import net.mcfr.entities.EntityCrossbowBolt;
import net.mcfr.utils.NameUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCrossbowBolt extends ItemArrow {
  public ItemCrossbowBolt() {
    String name = "crossbow_bolt";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public EntityArrow createArrow(World world, ItemStack stack, EntityLivingBase shooter) {
    return new EntityCrossbowBolt(world, shooter);
  }
}
