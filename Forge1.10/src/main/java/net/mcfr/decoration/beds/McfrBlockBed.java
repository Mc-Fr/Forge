package net.mcfr.decoration.beds;

import java.util.Random;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
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

/**
 * Classe de base des lits du mod.
 *
 * @author Mc-Fr
 */
public abstract class McfrBlockBed extends BlockBed {
  /** La hauteur de la hitbox */
  private final float height;

  /**
   * Crée un lit.
   * 
   * @param name le nom (sans le suffixe '_block')
   * @param height la hauteur de la hitbox
   * @param hardness la dureté
   */
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

  @Override
  public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    if (world.isRemote)
      return true;
    else {
      if (state.getValue(PART) != BlockBed.EnumPartType.HEAD) {
        pos = pos.offset(state.getValue(FACING));
        state = world.getBlockState(pos);

        if (state.getBlock() != this)
          return true;
      }

      if (world.provider.canRespawnHere() && world.getBiomeForCoordsBody(pos) != Biomes.HELL) {
        if (state.getValue(OCCUPIED)) {
          EntityPlayer sleepingPlayer = getPlayerInBed(world, pos);

          if (sleepingPlayer != null) {
            player.addChatComponentMessage(new TextComponentTranslation("tile.bed.occupied"));
            return true;
          }

          state = state.withProperty(OCCUPIED, false);
          world.setBlockState(pos, state, 4);
        }

        EntityPlayer.SleepResult sleepResult = player.trySleep(pos);

        if (sleepResult == EntityPlayer.SleepResult.OK) {
          state = state.withProperty(OCCUPIED, true);
          world.setBlockState(pos, state, 4);
          return true;
        }
        else {
          if (sleepResult == EntityPlayer.SleepResult.NOT_POSSIBLE_NOW) {
            player.addChatComponentMessage(new TextComponentTranslation("tile.bed.noSleep"));
          }
          else if (sleepResult == EntityPlayer.SleepResult.NOT_SAFE) {
            player.addChatComponentMessage(new TextComponentTranslation("tile.bed.notSafe"));
          }

          return true;
        }
      }
      else {
        world.setBlockToAir(pos);
        BlockPos blockpos = pos.offset(state.getValue(FACING).getOpposite());

        if (world.getBlockState(blockpos).getBlock() == this) {
          world.setBlockToAir(blockpos);
        }

        world.newExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5f, true, true);

        return true;
      }
    }
  }

  /**
   * Retourne le joueur présent dans le lit ou null s'il n'y en a pas.
   * 
   * @param world le monde
   * @param pos la position
   * @return le joueur ou null
   */
  private EntityPlayer getPlayerInBed(World world, BlockPos pos) {
    for (EntityPlayer entityplayer : world.playerEntities) {
      if (entityplayer.isPlayerSleeping() && entityplayer.getBedLocation().equals(pos))
        return entityplayer;
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

  /**
   * @return l'item correspondant
   */
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
