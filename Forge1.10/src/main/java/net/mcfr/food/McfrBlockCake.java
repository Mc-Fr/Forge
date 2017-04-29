package net.mcfr.food;

import javax.annotation.Nullable;

import net.mcfr.commons.McfrItemBlockSpecial;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Gâteau.
 *
 * @author Mc-Fr
 */
public class McfrBlockCake extends BlockCake {
  private final int amount;
  private final float saturation;

  /**
   * Crée un gâteau.
   * 
   * @param type le type
   * @param amount la quantité de faim restituée
   * @param saturation le taux de saturation
   */
  public McfrBlockCake(String type, int amount, float saturation) {
    this.amount = amount;
    this.saturation = saturation;
    String name = type + "_cake_block";
    setRegistryName(name);
    setHardness(0.5f);
    setSoundType(SoundType.CLOTH);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    disableStats();
  }

  /**
   * @return l'item correspondant à ce gâteau
   */
  public McfrItemBlockSpecial getItem() {
    String name = getRegistryName().getResourcePath();
    return (McfrItemBlockSpecial) new McfrItemBlockSpecial(name.substring(0, name.indexOf("_block")), this, CreativeTabs.FOOD).setMaxStackSize(1);
  }

  @Override
  public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    eatCake(world, pos, state, playerIn);
    return true;
  }

  /**
   * Effectue l'action de consommation d'une part du gâteau.
   * 
   * @param world le monde
   * @param pos la position
   * @param state l'état
   * @param player le mangeur
   */
  private void eatCake(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
    if (player.canEat(false)) {
      player.addStat(StatList.CAKE_SLICES_EATEN);
      player.getFoodStats().addStats(this.amount, this.saturation);
      int i = state.getValue(BITES);

      if (i < 6) {
        world.setBlockState(pos, state.withProperty(BITES, i + 1), 3);
      }
      else {
        world.setBlockToAir(pos);
      }
    }
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return super.getPickBlock(state, target, world, pos, player);
  }
}
