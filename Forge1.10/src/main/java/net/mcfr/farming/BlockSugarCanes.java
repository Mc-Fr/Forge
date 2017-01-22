package net.mcfr.farming;

import java.util.Random;

import net.mcfr.McfrItems;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockReed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockSugarCanes extends BlockReed {
  public BlockSugarCanes() {
    super();
    String name = "sugar_canes_block";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setHardness(0);
    setSoundType(SoundType.PLANT);
  }
  
  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.SUGAR_CANES;
  }
  
  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(McfrItems.SUGAR_CANES);
  }
  
  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (worldIn.getBlockState(pos.down()).getBlock() == this || checkForDrop(worldIn, pos, state)) {
      if (worldIn.isAirBlock(pos.up())) {
        int i;
        
        for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i);
        
        if (i < 3) {
          int j = state.getValue(AGE).intValue();
          
          if (j == 15) {
            worldIn.setBlockState(pos.up(), getDefaultState());
            worldIn.setBlockState(pos, state.withProperty(AGE, 0), 4);
          }
          else {
            worldIn.setBlockState(pos, state.withProperty(AGE, j + 1), 4);
          }
        }
      }
    }
  }
}
