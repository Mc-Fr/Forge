package net.mcfr.food;

import net.mcfr.commons.McfrItemBlockSpecial;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class McfrBlockCake extends BlockCake {
  public McfrBlockCake(String type) {
    super();
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
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return super.getPickBlock(state, target, world, pos, player);
  }
}
