package net.mcfr.entities.mobs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.entities.mobs.EntityBurrowed;
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
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityBormoth extends EntityBurrowed {

  public EntityBormoth(World worldIn) {
    super(worldIn);
    setSize(1.7F, 4.0F);
    setPathPriority(PathNodeType.WATER, 0.0F);
    setTrunkType(this.rand.nextInt(3));
  }

  public void setTrunkType(int type) {
    setSyncedInteger("TrunkType", type);
  }

  public int getTrunkType() {
    return getSyncedInteger("TrunkType");
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
    this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
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
  public EntityBormoth createChild(EntityAgeable ageable) {
    return new EntityBormoth(this.worldObj);
  }

  public static void func_189789_b(DataFixer p_189789_0_) {
    EntityLiving.registerFixesMob(p_189789_0_, "Chicken");
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

  @Override
  public List<ItemStack> getLoots() {
    List<ItemStack> itemList = new ArrayList<>();

    itemList.add(new ItemStack(Items.ROTTEN_FLESH, getRandomQuantity(12.5F)));
    itemList.add(new ItemStack(McfrItems.HUNTED_SKIN, getRandomQuantity(12.5F)));

    return itemList;
  }
}