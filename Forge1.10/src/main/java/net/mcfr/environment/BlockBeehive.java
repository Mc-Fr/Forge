package net.mcfr.environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBeehive extends BlockCocoa {
  public BlockBeehive() {
    super();
    String name = "beehive";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setHardness(0.2f);
    setResistance(5);
    setSoundType(SoundType.WOOD);
  }
  
  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (playerIn.getHeldItemMainhand() != null && playerIn.getHeldItemMainhand().getItem() == Items.BOWL) {
      if (!playerIn.inventory.addItemStackToInventory(new ItemStack(McfrItems.HONEY)) && !worldIn.isRemote) {
        EntityItem loot = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(McfrItems.HONEY));
        
        loot.setPickupDelay(10);
        worldIn.spawnEntityInWorld(loot);
      }
      playerIn.getHeldItemMainhand().stackSize--;
      
      return true;
    }
    
    return false;
  }
  
  @Override
  public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
    IBlockState s = worldIn.getBlockState(pos.offset(state.getValue(FACING)));
    return s.getBlock() == Blocks.LOG && s.getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.OAK;
  }
  
  @Override
  public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    List<ItemStack> list = new ArrayList<ItemStack>();
    
    list.add(getItem((World) world, pos, state));
    
    return list;
  }
  
  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    return new ItemStack(McfrItems.WAX);
  }
  
  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return McfrItems.WAX;
  }
  
  @Override
  public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
    return false;
  }
  
  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return getItem(world, pos, state);
  }
}
