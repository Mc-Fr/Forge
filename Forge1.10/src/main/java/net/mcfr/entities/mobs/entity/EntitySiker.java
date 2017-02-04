package net.mcfr.entities.mobs.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.mcfr.McfrItems;
import net.mcfr.entities.mobs.EntityBurrowed;
import net.mcfr.entities.mobs.ai.AICycle;
import net.mcfr.entities.mobs.ai.EntityAIAttackCycle;
import net.mcfr.entities.mobs.ai.EntityAIAvoidEntityCycle;
import net.mcfr.entities.mobs.ai.EntityAIGoToBurrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EntitySiker extends EntityBurrowed {
  private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet();
  private AICycle cycle;

  public EntitySiker(World worldIn) {
    super(worldIn);
    setSize(1.9F, 3.0F);
    setPathPriority(PathNodeType.WATER, 0.0F);
  }

  @Override
  protected void initEntityAI() {
    this.cycle = AICycle.IDLE;

    this.tasks.addTask(0, new EntityAIAvoidEntityCycle<>(this, EntityPlayer.class, 30.0F, 1.0F, 1.4F, 150));
    this.tasks.addTask(1, new EntityAIAttackCycle(this, 1.3F, true, 50));
    this.tasks.addTask(2, new EntityAIPanic(this, 1.3F));
    this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 25.0F, 0.9F));
    this.tasks.addTask(4, new EntityAIGoToBurrow(this, 1.0D, 5));
    this.tasks.addTask(5, new EntityAIWander(this, 1.0D, 10));
    this.tasks.addTask(6, new EntityAILookIdle(this));

    this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, new Class[0]));
    this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
  }

  public AICycle getCycle() {
    return this.cycle;
  }

  public void setCycle(AICycle cycle) {
    this.cycle = cycle;
  }

  @Override
  public float getEyeHeight() {
    return this.height;
  }

  @Override
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0F);

    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
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
  
  @Override
  public void setDropItems(LivingDropsEvent event) {
    List<EntityItem> drops = event.getDrops();
    
    drops.clear();
    
    List<ItemStack> itemList = new ArrayList<>();
    itemList.add(new ItemStack(McfrItems.RAW_HUNTED_STEAK, getRandomQuantity(10.7F)));
    itemList.add(new ItemStack(McfrItems.HUNTED_SKIN, getRandomQuantity(8.4F)));
    
    for (ItemStack i : itemList) {
      drops.add(new EntityItem(event.getEntity().worldObj, event.getEntity().posX, 
          event.getEntity().posY, event.getEntity().posZ, i));
    }
  }
}