package net.mcfr.decoration.beds;

import java.util.Random;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class McfrBlockBed extends BlockBed {
  private final float height;

  public McfrBlockBed(String name, float height, float hardness) {
    super();
    if (height > 1)
      throw new IllegalArgumentException("" + height);
    name = name + "_block";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    this.height = height;
    setHardness(hardness);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return new AxisAlignedBB(0, 0, 0, 1, this.height, 1);
  }

  @Override
  public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
    return getBoundingBox(blockState, worldIn, pos);
  }

  // TEMP
  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote) {
      return true;
    }
    else {
      if (state.getValue(PART) != BlockBed.EnumPartType.HEAD) {
        pos = pos.offset((EnumFacing) state.getValue(FACING));
        state = worldIn.getBlockState(pos);

        if (state.getBlock() != this) {
          return true;
        }
      }

      if (worldIn.provider.canRespawnHere() && worldIn.getBiomeGenForCoords(pos) != Biomes.HELL) {
        if (((Boolean) state.getValue(OCCUPIED)).booleanValue()) {
          EntityPlayer entityplayer = this.getPlayerInBed(worldIn, pos);

          if (entityplayer != null) {
            playerIn.addChatComponentMessage(new TextComponentTranslation("tile.bed.occupied", new Object[0]));
            return true;
          }

          state = state.withProperty(OCCUPIED, Boolean.valueOf(false));
          worldIn.setBlockState(pos, state, 4);
        }

        EntityPlayer.SleepResult entityplayer$sleepresult = playerIn.trySleep(pos);

        if (entityplayer$sleepresult == EntityPlayer.SleepResult.OK) {
          state = state.withProperty(OCCUPIED, Boolean.valueOf(true));
          worldIn.setBlockState(pos, state, 4);
          return true;
        }
        else {
          if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_POSSIBLE_NOW) {
            playerIn.addChatComponentMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]));
          }
          else if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_SAFE) {
            playerIn.addChatComponentMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]));
          }

          return true;
        }
      }
      else {
        worldIn.setBlockToAir(pos);
        BlockPos blockpos = pos.offset(((EnumFacing) state.getValue(FACING)).getOpposite());

        if (worldIn.getBlockState(blockpos).getBlock() == this) {
          worldIn.setBlockToAir(blockpos);
        }

        worldIn.newExplosion((Entity) null, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 5.0F, true, true);
        return true;
      }
    }
  }

  private EntityPlayer getPlayerInBed(World worldIn, BlockPos pos) {
    for (EntityPlayer entityplayer : worldIn.playerEntities) {
      if (entityplayer.isPlayerSleeping() && entityplayer.playerLocation.equals(pos)) {
        return entityplayer;
      }
    }

    return null;
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return state.getValue(PART) == BlockBed.EnumPartType.HEAD ? null : getItemToDrop();
  }

  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(getItemToDrop());
  }

  protected abstract ItemBed getItemToDrop();

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(getItemToDrop());
  }

  /**
   * @return l'item correspondant à ce lit
   */
  public McfrItemBed getItem() {
    return new McfrItemBed(getRegistryName().getResourcePath().substring(0, getRegistryName().getResourcePath().indexOf("_block")), this);
  }
}
