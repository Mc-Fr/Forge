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

public class McfrBlockCake extends BlockCake {
  private final int amount;
  private final float saturation;

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
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    eatCake(worldIn, pos, state, playerIn);
    return true;
  }

  private void eatCake(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
    if (player.canEat(false)) {
      player.addStat(StatList.CAKE_SLICES_EATEN);
      player.getFoodStats().addStats(this.amount, this.saturation);
      int i = state.getValue(BITES);

      if (i < 6) {
        worldIn.setBlockState(pos, state.withProperty(BITES, i + 1), 3);
      }
      else {
        worldIn.setBlockToAir(pos);
      }
    }
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return super.getPickBlock(state, target, world, pos, player);
  }
}
