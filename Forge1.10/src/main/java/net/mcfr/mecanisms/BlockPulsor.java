package net.mcfr.mecanisms;

import java.util.List;

import javax.annotation.Nullable;

import net.mcfr.commons.McfrBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPulsor extends McfrBlock {
  private static final int RADIUS = 65;

  public BlockPulsor() {
    super("pulsor", Material.ROCK, SoundType.STONE, 20, 0, null, -1, null);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    attractMobs(worldIn, pos, state);
    return true;
  }

  private void attractMobs(World worldIn, BlockPos pos, IBlockState state) {
    lockAttraction(worldIn, pos, EntityBlaze.class);
    lockAttraction(worldIn, pos, EntityCaveSpider.class);
    lockAttraction(worldIn, pos, EntityCreeper.class);
    lockAttraction(worldIn, pos, EntityEnderman.class);
    lockAttraction(worldIn, pos, EntityEndermite.class);
    lockAttraction(worldIn, pos, EntityGhast.class);
    lockAttraction(worldIn, pos, EntityGiantZombie.class);
    lockAttraction(worldIn, pos, EntityGuardian.class);
    lockAttraction(worldIn, pos, EntityMagmaCube.class);
    lockAttraction(worldIn, pos, EntityPigZombie.class);
    lockAttraction(worldIn, pos, EntitySilverfish.class);
    lockAttraction(worldIn, pos, EntitySkeleton.class);
    lockAttraction(worldIn, pos, EntitySlime.class);
    lockAttraction(worldIn, pos, EntitySpider.class);
    lockAttraction(worldIn, pos, EntityWitch.class);
    lockAttraction(worldIn, pos, EntityWither.class);
    lockAttraction(worldIn, pos, EntityZombie.class);
  }

  private void lockAttraction(World world, BlockPos pos, Class<? extends EntityLiving> type) {
    List<EntityLiving> list = world.getEntitiesWithinAABB(type,
        new AxisAlignedBB(pos.getX() - RADIUS, pos.getY() - RADIUS, pos.getZ() - RADIUS, pos.getX() + RADIUS, pos.getY() + RADIUS, pos.getZ() + RADIUS));
    for (EntityLiving e : list) {
      e.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1);
    }
  }
}
