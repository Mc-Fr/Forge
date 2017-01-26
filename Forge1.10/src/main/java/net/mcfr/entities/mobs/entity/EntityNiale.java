package net.mcfr.entities.mobs.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.mcfr.entities.mobs.EntityBurrowed;
import net.mcfr.entities.mobs.ai.EntityAIGoToBurrow;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityNiale extends EntityBurrowed implements net.minecraftforge.common.IShearable {
  private static final Set<Item> TEMPTATION_ITEMS = Sets
      .newHashSet(new Item[] { Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS });
  private EntityAIEatGrass entityAIEatGrass;
  private int nialeTimer;

  public EntityNiale(World worldIn) {
    super(worldIn);
    this.setSize(1.1F, 1.3F);
    this.setPathPriority(PathNodeType.WATER, 0.0F);
    this.setWhoolColor(this.rand.nextInt(3));
    this.setSheared(this.rand.nextBoolean());
  }

  public void setWhoolColor(int color) {
    this.setSyncedInteger("WhoolColor", color);
  }

  public int getWhoolColor() {
    return this.getSyncedInteger("WhoolColor");
  }

  public void setSheared(boolean sheared) {
    this.setSyncedBoolean("Sheared", sheared);
  }

  public boolean getSheared() {
    return this.getSyncedBoolean("Sheared");
  }

  protected void initEntityAI() {
    this.entityAIEatGrass = new EntityAIEatGrass(this);
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
    this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
    this.tasks.addTask(3, new EntityAITempt(this, 1.0D, true, TEMPTATION_ITEMS));
    this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
    this.tasks.addTask(5, new EntityAIGoToBurrow(this, 1.0D, 5));
    this.tasks.addTask(6, this.entityAIEatGrass);
    this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
    this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    this.tasks.addTask(9, new EntityAILookIdle(this));
  }

  protected void updateAITasks() {
    this.nialeTimer = this.entityAIEatGrass.getEatingGrassTimer();
    super.updateAITasks();
  }
  
  @SideOnly(Side.CLIENT)
  public void handleStatusUpdate(byte id)
  {
      if (id == 10)
      {
          this.nialeTimer = 40;
      }
      else
      {
          super.handleStatusUpdate(id);
      }
  }

  public float getEyeHeight() {
    return this.height;
  }

  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
    this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
  }

  /**
   * Called frequently so the entity can update its state every tick as
   * required. For example, zombies and skeletons use this to react to sunlight
   * and start to burn.
   */
  public void onLivingUpdate() {
    if (this.worldObj.isRemote) {
      this.nialeTimer = Math.max(0, this.nialeTimer - 1);
    }

    super.onLivingUpdate();
  }

  protected SoundEvent getAmbientSound() {
    return SoundEvents.ENTITY_CHICKEN_AMBIENT;
  }

  protected SoundEvent getHurtSound() {
    return SoundEvents.ENTITY_CHICKEN_HURT;
  }

  protected SoundEvent getDeathSound() {
    return SoundEvents.ENTITY_CHICKEN_DEATH;
  }

  protected void playStepSound(BlockPos pos, Block blockIn) {
    this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
  }

  @Nullable
  protected ResourceLocation getLootTable() {
    return LootTableList.ENTITIES_CHICKEN;
  }

  public EntityNiale createChild(EntityAgeable ageable) {
    return new EntityNiale(this.worldObj);
  }

  /**
   * Checks if the parameter is an item which this animal can be fed to breed it
   * (wheat, carrots or seeds depending on the animal type)
   */
  public boolean isBreedingItem(@Nullable ItemStack stack) {
    return stack != null && TEMPTATION_ITEMS.contains(stack.getItem());
  }

  public void eatGrassBonus() {
    this.setSheared(false);

    if (this.isChild()) {
      this.addGrowth(60);
    }
  }
  
  @SideOnly(Side.CLIENT)
  public float getHeadRotationPointY(float p_70894_1_)
  {
      return this.nialeTimer <= 0 ? 0.0F : (this.nialeTimer >= 4 && this.nialeTimer <= 36 ? 1.0F : (this.nialeTimer < 4 ? ((float)this.nialeTimer - p_70894_1_) / 4.0F : -((float)(this.nialeTimer - 40) - p_70894_1_) / 4.0F));
  }
  
  @SideOnly(Side.CLIENT)
  public float getHeadRotationAngleX(float p_70890_1_)
  {
      if (this.nialeTimer > 4 && this.nialeTimer <= 36)
      {
          float f = ((float)(this.nialeTimer - 4) - p_70890_1_) / 32.0F;
          return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
      }
      else
      {
          return this.nialeTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * 0.017453292F;
      }
  }
  
  @Override
  public boolean isShearable(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos) {
    return !this.getSheared() && !this.isChild();
  }

  @Override
  public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
    this.setSheared(true);
    int i = 1 + this.rand.nextInt(3);

    List<ItemStack> ret = new ArrayList<ItemStack>();
    for (int j = 0; j < i; ++j)
      ret.add(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1));

    this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
    return ret;
  }

  /**
   * Get the experience points the entity currently has.
   */
  protected int getExperiencePoints(EntityPlayer player) {
    return super.getExperiencePoints(player);
  }

  public static void func_189789_b(DataFixer p_189789_0_) {
    EntityLiving.func_189752_a(p_189789_0_, "Chicken");
  }

  /**
   * (abstract) Protected helper method to read subclass entity data from NBT.
   */
  public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
  }

  /**
   * (abstract) Protected helper method to write subclass entity data to NBT.
   */
  public void writeEntityToNBT(NBTTagCompound compound) {
    super.writeEntityToNBT(compound);
  }

  public void updatePassenger(Entity passenger) {
    super.updatePassenger(passenger);
    float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
    float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
    passenger.setPosition(this.posX + (double) (0.1F * f), this.posY + (double) (this.height * 0.5F) + passenger.getYOffset() + 0.0D,
        this.posZ - (double) (0.1F * f1));

    if (passenger instanceof EntityLivingBase) {
      ((EntityLivingBase) passenger).renderYawOffset = this.renderYawOffset;
    }
  }
}
