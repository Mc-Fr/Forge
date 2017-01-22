package net.mcfr.coma;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;

public class PotionComa extends Potion {

  public PotionComa() {
    super(true, 0);
    this.setPotionName("effect.coma");
    this.setIconIndex(0, 0);
  }

  @Override
  public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {    
    EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
    player.motionX = 0.0F;
    player.motionY = 0.0F;
    player.motionZ = 0.0F;
    player.posX = player.prevPosX;
    player.posY = player.prevPosY;
    player.posZ = player.prevPosZ;

    float currentHealth = entityLivingBaseIn.getHealth();
    if (currentHealth > 1.0F) {
      entityLivingBaseIn.attackEntityFrom(DamageSource.magic, currentHealth - 1.0F);
    }
  }
  
  @Override
  public boolean isReady(int duration, int amplifier) {
    return true;
  }
}
