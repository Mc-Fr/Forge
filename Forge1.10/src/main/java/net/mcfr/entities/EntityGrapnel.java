package net.mcfr.entities;

import java.util.List;

import net.mcfr.equipment.ItemGrapnel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGrapnel extends Entity implements IProjectile {
  private static final DataParameter<Byte> LENGTH = EntityDataManager.createKey(EntityGrapnel.class, DataSerializers.BYTE);
  private static final DataParameter<Boolean> IS_ROPE = EntityDataManager.createKey(EntityGrapnel.class, DataSerializers.BOOLEAN);
  
  private static final float VELOCITY = 20;
  
  private int xTile = -1;
  private int yTile = -1;
  private int zTile = -1;
  private Block inTile;
  private int inData;
  private boolean inGround;
  private int ticksInGround;
  private int ticksInAir;
  
  private ItemGrapnel.EnumType type;
  private Entity shootingEntity;
  
  public EntityGrapnel(World worldIn, Entity shooter, ItemGrapnel.EnumType type) {
    super(worldIn);
    this.type = type;
    setRenderDistanceWeight(10);
    this.shootingEntity = shooter;
    setSize(0.5f, 0.5f);
    setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
    this.posX -= MathHelper.cos(this.rotationYaw / 180 * (float) Math.PI) * 0.16f;
    this.posY -= 0.10000000149011612;
    this.posZ -= MathHelper.sin(this.rotationYaw / 180 * (float) Math.PI) * 0.16f;
    setPosition(this.posX, this.posY, this.posZ);
    this.motionX = -MathHelper.sin(this.rotationYaw / 180 * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180 * (float) Math.PI);
    this.motionZ = MathHelper.cos(this.rotationYaw / 180 * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180 * (float) Math.PI);
    this.motionY = (-MathHelper.sin(this.rotationPitch / 180 * (float) Math.PI));
    setThrowableHeading(this.motionX, this.motionY, this.motionZ, VELOCITY * 1.5f, 1);
    this.dataManager.set(LENGTH, (byte) type.getLength());
    this.dataManager.set(IS_ROPE, type.isRope());
  }
  
  @Override
  protected void entityInit() {
    this.dataManager.register(LENGTH, (byte) 0);
    this.dataManager.register(IS_ROPE, null);
  }
  
  public int getLength() {
    return this.dataManager.get(LENGTH);
  }
  
  public boolean isRope() {
    return this.dataManager.get(IS_ROPE);
  }
  
  @Override
  protected void readEntityFromNBT(NBTTagCompound tagCompound) {
    this.type = ItemGrapnel.EnumType.byMetadata(tagCompound.getInteger("Type"));
  }
  
  @Override
  protected void writeEntityToNBT(NBTTagCompound tagCompound) {
    tagCompound.setInteger("Type", this.type.getMetadata());
  }
  
  @Override
  public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
    float f = MathHelper.sqrt_double(x * x + y * y + z * z);
    x = x / f;
    y = y / f;
    z = z / f;
    x = x + this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
    y = y + this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
    z = z + this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * inaccuracy;
    x = x * velocity;
    y = y * velocity;
    z = z * velocity;
    this.motionX = x;
    this.motionY = y;
    this.motionZ = z;
    float f1 = MathHelper.sqrt_double(x * x + z * z);
    this.prevRotationYaw = this.rotationYaw = (float) (MathHelper.atan2(x, z) * 180.0D / Math.PI);
    this.prevRotationPitch = this.rotationPitch = (float) (MathHelper.atan2(y, f1) * 180.0D / Math.PI);
  }
  
  @Override
  @SideOnly(Side.CLIENT)
  public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean b) {
    setPosition(x, y, z);
    setRotation(yaw, pitch);
  }
  
  /**
   * Sets the velocity to the args. Args: x, y, z
   */
  @Override
  @SideOnly(Side.CLIENT)
  public void setVelocity(double x, double y, double z) {
    this.motionX = x;
    this.motionY = y;
    this.motionZ = z;
    
    if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
      float f = MathHelper.sqrt_double(x * x + z * z);
      this.prevRotationYaw = this.rotationYaw = (float) (MathHelper.atan2(x, z) * 180.0D / Math.PI);
      this.prevRotationPitch = this.rotationPitch = (float) (MathHelper.atan2(y, f) * 180.0D / Math.PI);
      this.prevRotationPitch = this.rotationPitch;
      this.prevRotationYaw = this.rotationYaw;
      setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
    }
  }
  
  @Override
  public void onUpdate() {
    super.onUpdate();
    
    if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
      float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.prevRotationYaw = this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
      this.prevRotationPitch = this.rotationPitch = (float) (MathHelper.atan2(this.motionY, f) * 180.0D / Math.PI);
    }
    
    BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
    IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
    Block block = iblockstate.getBlock();
    
    if (iblockstate.getMaterial() != Material.AIR) {
      AxisAlignedBB axisalignedbb = iblockstate.getBoundingBox(this.worldObj, blockpos);
      
      if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3d(this.posX, this.posY, this.posZ))) {
        this.inGround = true;
      }
    }
    
    if (this.inGround) {
      int j = block.getMetaFromState(iblockstate);
      
      if (block == this.inTile && j == this.inData) {
        ++this.ticksInGround;
        
        if (this.ticksInGround >= 20) {
          setDead();
        }
      }
      else {
        this.inGround = false;
        this.motionX *= this.rand.nextFloat() * 0.2F;
        this.motionY *= this.rand.nextFloat() * 0.2F;
        this.motionZ *= this.rand.nextFloat() * 0.2F;
        this.ticksInGround = 0;
        this.ticksInAir = 0;
      }
    }
    else {
      ++this.ticksInAir;
      Vec3d vec31 = new Vec3d(this.posX, this.posY, this.posZ);
      Vec3d vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
      RayTraceResult movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3, false, true, false);
      vec31 = new Vec3d(this.posX, this.posY, this.posZ);
      vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
      
      if (movingobjectposition != null) {
        vec3 = new Vec3d(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
      }
      
      Entity entity = null;
      List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
      double d0 = 0.0D;
      
      for (int i = 0; i < list.size(); ++i) {
        Entity entity1 = list.get(i);
        
        if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
          float f1 = 0.3F;
          AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand(f1, f1, f1);
          RayTraceResult movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);
          
          if (movingobjectposition1 != null) {
            double d1 = vec31.squareDistanceTo(movingobjectposition1.hitVec);
            
            if (d1 < d0 || d0 == 0.0D) {
              entity = entity1;
              d0 = d1;
            }
          }
        }
      }
      
      if (entity != null) {
        movingobjectposition = new RayTraceResult(entity);
      }
      
      if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer) {
        EntityPlayer entityplayer = (EntityPlayer) movingobjectposition.entityHit;
        
        if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer) this.shootingEntity).canAttackPlayer(entityplayer)) {
          movingobjectposition = null;
        }
      }
      
      if (movingobjectposition != null) {
        BlockPos blockpos1 = movingobjectposition.getBlockPos();
        this.xTile = blockpos1.getX();
        this.yTile = blockpos1.getY();
        this.zTile = blockpos1.getZ();
        IBlockState iblockstate1 = this.worldObj.getBlockState(blockpos1);
        this.inTile = iblockstate1.getBlock();
        this.inData = this.inTile.getMetaFromState(iblockstate1);
        this.motionX = ((float) (movingobjectposition.hitVec.xCoord - this.posX));
        this.motionY = ((float) (movingobjectposition.hitVec.yCoord - this.posY));
        this.motionZ = ((float) (movingobjectposition.hitVec.zCoord - this.posZ));
        float f5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        this.posX -= this.motionX / f5 * 0.05000000074505806D;
        this.posY -= this.motionY / f5 * 0.05000000074505806D;
        this.posZ -= this.motionZ / f5 * 0.05000000074505806D;
        playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
        this.inGround = true;
        
        if (iblockstate1.getMaterial() != Material.AIR) {
          this.inTile.onEntityCollidedWithBlock(this.worldObj, blockpos1, iblockstate1, this);
        }
      }
      
      this.posX += this.motionX;
      this.posY += this.motionY;
      this.posZ += this.motionZ;
      float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
      this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
      
      for (this.rotationPitch = (float) (MathHelper.atan2(this.motionY, f3) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
        ;
      }
      
      while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
        this.prevRotationPitch += 360.0F;
      }
      
      while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
        this.prevRotationYaw -= 360.0F;
      }
      
      while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
        this.prevRotationYaw += 360.0F;
      }
      
      this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
      this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
      float f4 = 0.99F;
      float f6 = 0.05F;
      
      if (isInWater()) {
        for (int i1 = 0; i1 < 4; ++i1) {
          float f8 = 0.25F;
          this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * f8, this.posY - this.motionY * f8, this.posZ - this.motionZ * f8, this.motionX, this.motionY, this.motionZ, new int[0]);
        }
        
        f4 = 0.6F;
      }
      
      if (isWet()) {
        extinguish();
      }
      
      this.motionX *= f4;
      this.motionY *= f4;
      this.motionZ *= f4;
      this.motionY -= f6;
      setPosition(this.posX, this.posY, this.posZ);
      doBlockCollisions();
    }
  }
  
  @Override
  protected boolean canTriggerWalking() {
    return false;
  }
  
  @Override
  public float getEyeHeight() {
    return 0;
  }
}
