package net.mcfr.entities.mobs.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.mcfr.McfrItems;
import net.mcfr.entities.mobs.EntityBurrowed;
import net.mcfr.entities.mobs.ai.EntityAIGoToBurrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityGalt extends EntityBurrowed {
  private static final Set<Item> TEMPTATION_ITEMS = Sets
      .newHashSet();

  public EntityGalt(World worldIn) {
    super(worldIn);
    setSize(1.5F, 1.7F);
    setPathPriority(PathNodeType.WATER, 0.0F);
    setFurType(2);
  }

  public void setFurType(int type) {
    setSyncedInteger("FurType", type);
  }

  public int getFurType() {
    return getSyncedInteger("FurType");
  }

  @Override
  protected void initEntityAI() {
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
    this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.5F, true));
    this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 25.0F, 0.9F));
    this.tasks.addTask(4, new EntityAIGoToBurrow(this, 1.0D, 5));
    this.tasks.addTask(5, new EntityAIWander(this, 1.0D, 10));
    this.tasks.addTask(6, new EntityAILookIdle(this));

    this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true, new Class[0]));
    this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityNiale.class, true));
    this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityHoen.class, true));
  }

  @Override
  public float getEyeHeight() {
    return this.height;
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
    getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0F);

    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
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
  public EntityGalt createChild(EntityAgeable ageable) {
    return new EntityGalt(this.worldObj);
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
  
  public List<ItemStack> getLoots() {
    List<ItemStack> itemList = new ArrayList<>();
    
    itemList.add(new ItemStack(McfrItems.RAW_HUNTED_STEAK, getRandomQuantity(8.6F)));
    itemList.add(new ItemStack(McfrItems.HUNTED_SKIN, getRandomQuantity(9.6F)));
    itemList.add(new ItemStack(Items.BONE, getRandomQuantity(6.4f)));
    
    return itemList;
  }
}
