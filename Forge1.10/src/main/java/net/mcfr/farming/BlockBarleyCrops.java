package net.mcfr.farming;

import net.mcfr.McfrItems;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Orge.
 *
 * @author Mc-Fr
 */
public class BlockBarleyCrops extends BlockCrops {
  public BlockBarleyCrops() {
    String name = "barley_crops";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
  }
  
  @Override
  protected Item getSeed() {
    return McfrItems.BARLEY_SEEDS;
  }
  
  @Override
  protected Item getCrop() {
    return McfrItems.BARLEY;
  }
  
  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(getSeed());
  }
}
