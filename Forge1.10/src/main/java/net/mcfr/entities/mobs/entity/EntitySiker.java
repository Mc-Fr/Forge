package net.mcfr.entities.mobs.entity;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.mcfr.entities.mobs.EntityBurrowed;
import net.mcfr.entities.mobs.gender.Genders;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntitySiker extends EntityBurrowed {
  private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS);

  public EntitySiker(World worldIn) {
    super(worldIn);
    setSize(2.0F, 3.5F);
    setPathPriority(PathNodeType.WATER, 0.0F);
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
    this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
    this.tasks.addTask(3, new EntityAITempt(this, 1.0D, false, TEMPTATION_ITEMS));
    this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
    this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    this.tasks.addTask(7, new EntityAILookIdle(this));
  }

  @Override
  public float getEyeHeight() {
    return this.height;
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
  }

  /**
   * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons use
   * this to react to sunlight and start to burn.
   */
  @Override
  public void onLivingUpdate() {
    super.onLivingUpdate();
  }

  @Override
  protected SoundEvent getAmbientSound() {
    if (this.getGender() == Genders.FEMALE)
      return SoundEvents.ENTITY_COW_AMBIENT;
    else
      return SoundEvents.ENTITY_CHICKEN_AMBIENT;
  }

  @Override
  protected SoundEvent getHurtSound() {
    return SoundEvents.ENTITY_CHICKEN_HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundEvents.ENTITY_CHICKEN_DEATH;
  }

  @Override
  protected void playStepSound(BlockPos pos, Block blockIn) {
    playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
  }

  @Override
  @Nullable
  protected ResourceLocation getLootTable() {
    return LootTableList.ENTITIES_CHICKEN;
  }

  @Override
  public EntitySiker createChild(EntityAgeable ageable) {
    return new EntitySiker(this.worldObj);
  }

  /**
   * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
   * the animal type)
   */
  @Override
  public boolean isBreedingItem(@Nullable ItemStack stack) {
    return stack != null && TEMPTATION_ITEMS.contains(stack.getItem());
  }

  /**
   * Get the experience points the entity currently has.
   */
  @Override
  protected int getExperiencePoints(EntityPlayer player) {
    return super.getExperiencePoints(player);
  }

  public static void func_189789_b(DataFixer p_189789_0_) {
    EntityLiving.func_189752_a(p_189789_0_, "Chicken");
  }

  /**
   * (abstract) Protected helper method to read subclass entity data from NBT.
   */
  @Override
  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
  }

  /**
   * (abstract) Protected helper method to write subclass entity data to NBT.
   */
  @Override
  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
  }

  @Override
  public void updatePassenger(Entity passenger) {
    super.updatePassenger(passenger);
    float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
    float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
    passenger.setPosition(this.posX + 0.1F * f, this.posY + this.height * 0.5F + passenger.getYOffset() + 0.0D, this.posZ - 0.1F * f1);

    if (passenger instanceof EntityLivingBase) {
      ((EntityLivingBase) passenger).renderYawOffset = this.renderYawOffset;
    }
  }
}