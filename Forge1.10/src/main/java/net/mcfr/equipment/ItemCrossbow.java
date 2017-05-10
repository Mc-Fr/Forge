package net.mcfr.equipment;

import java.util.List;

import net.mcfr.McfrItems;
import net.mcfr.utils.NameUtils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * Arbalète.
 *
 * @author Mc-Fr
 */
public class ItemCrossbow extends ItemBow {
  /**
   * Crée une arbalète.
   * 
   * @param name le nom
   */
  public ItemCrossbow(String name) {
    super();
    name = name + "_crossbow";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }

  @Override
  public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
    if (entityLiving instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer) entityLiving;
      boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
      ItemStack itemstack = findAmmo(entityplayer);

      if (itemstack != null || flag) {
        if (itemstack == null)
          itemstack = new ItemStack(McfrItems.CROSSBOW_BOLT);

        float velocity = getArrowVelocity(3);

        if (velocity >= 0.1) {
          boolean flag1 = entityplayer.capabilities.isCreativeMode || itemstack.getItem() instanceof ItemCrossbowBolt
              && ((ItemCrossbowBolt) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer);

          if (!worldIn.isRemote) {
            ItemCrossbowBolt bolt = itemstack.getItem() instanceof ItemCrossbowBolt ? (ItemCrossbowBolt) itemstack.getItem()
                : McfrItems.CROSSBOW_BOLT;
            EntityArrow entityArrow = bolt.createArrow(worldIn, itemstack, entityplayer);
            entityArrow.setAim(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0, velocity * 3, 1);

            if (velocity == 1) {
              entityArrow.setIsCritical(true);
            }

            int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

            if (j > 0) {
              entityArrow.setDamage(entityArrow.getDamage() + j * 0.5 + 0.5);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

            if (k > 0) {
              entityArrow.setKnockbackStrength(k);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
              entityArrow.setFire(100);
            }

            stack.damageItem(1, entityplayer);

            if (flag1) {
              entityArrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
            }

            worldIn.spawnEntityInWorld(entityArrow);
          }

          worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT,
              SoundCategory.NEUTRAL, 1f, 1f / (itemRand.nextFloat() * 0.4f + 1.2f) + velocity * 0.5f);

          if (!flag1) {
            --itemstack.stackSize;

            if (itemstack.stackSize == 0) {
              entityplayer.inventory.deleteStack(itemstack);
            }
          }

          entityplayer.addStat(StatList.getObjectUseStats(this));
        }
      }
    }
  }

  /**
   * Cherche des munitions dans l'inventaire du joueur.
   * 
   * @param player le joueur
   * @return des munitions
   */
  private ItemStack findAmmo(EntityPlayer player) {
    if (isBolt(player.getHeldItem(EnumHand.OFF_HAND))) {
      return player.getHeldItem(EnumHand.OFF_HAND);
    }
    else if (isBolt(player.getHeldItem(EnumHand.MAIN_HAND))) {
      return player.getHeldItem(EnumHand.MAIN_HAND);
    }
    else {
      for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
        ItemStack itemstack = player.inventory.getStackInSlot(i);

        if (isBolt(itemstack))
          return itemstack;
      }

      return null;
    }
  }

  /**
   * Indique si l'item est un carreau.
   * 
   * @param stack l'item
   * @return true si l'item est un carreau
   */
  protected boolean isBolt(ItemStack stack) {
    return stack != null && stack.getItem() instanceof ItemCrossbowBolt;
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip);
  }
}
