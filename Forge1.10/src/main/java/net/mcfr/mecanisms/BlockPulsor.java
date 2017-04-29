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

/**
 * Le pulseur attire vers lui tous les mobs aggressifs dans un rayon donné.
 *
 * @author Mc-Fr
 */
public class BlockPulsor extends McfrBlock {
  /** Rayon d'action */
  private static final int RADIUS = 65;

  public BlockPulsor() {
    super("pulsor", Material.ROCK, SoundType.STONE, 20, 0, null, -1, null);
  }

  @Override
  public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    attractMobs(world, pos);
    return true;
  }

  /**
   * Attire certains mobs vers le bloc.
   * 
   * @param world le monde
   * @param pos la position
   */
  private void attractMobs(World world, BlockPos pos) {
    lockAttraction(world, pos, EntityBlaze.class);
    lockAttraction(world, pos, EntityCaveSpider.class);
    lockAttraction(world, pos, EntityCreeper.class);
    lockAttraction(world, pos, EntityEnderman.class);
    lockAttraction(world, pos, EntityEndermite.class);
    lockAttraction(world, pos, EntityGhast.class);
    lockAttraction(world, pos, EntityGiantZombie.class);
    lockAttraction(world, pos, EntityGuardian.class);
    lockAttraction(world, pos, EntityMagmaCube.class);
    lockAttraction(world, pos, EntityPigZombie.class);
    lockAttraction(world, pos, EntitySilverfish.class);
    lockAttraction(world, pos, EntitySkeleton.class);
    lockAttraction(world, pos, EntitySlime.class);
    lockAttraction(world, pos, EntitySpider.class);
    lockAttraction(world, pos, EntityWitch.class);
    lockAttraction(world, pos, EntityWither.class);
    lockAttraction(world, pos, EntityZombie.class);
  }

  /**
   * Attire les mobs de la classe donnée vers le bloc.
   * 
   * @param world le monde
   * @param pos la position
   * @param type le type d'entité
   */
  private void lockAttraction(World world, BlockPos pos, Class<? extends EntityLiving> type) {
    List<EntityLiving> list = world.getEntitiesWithinAABB(type, new AxisAlignedBB(pos.getX() - RADIUS, pos.getY() - RADIUS, pos.getZ() - RADIUS,
        pos.getX() + RADIUS, pos.getY() + RADIUS, pos.getZ() + RADIUS));
    for (EntityLiving e : list) {
      e.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1);
    }
  }
}
