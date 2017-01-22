package net.mcfr.decoration.beds;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class McfrItemBed extends ItemBed {
  private final BlockBed block;

  public McfrItemBed(String name, BlockBed block) {
    super();
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    this.block = block;
    setMaxStackSize(1);
  }

  // Adapté de la classe ItemBed.
  @Override
  @SuppressWarnings("deprecation")
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote) {
      return EnumActionResult.SUCCESS;
    }
    else if (facing == EnumFacing.UP) {
      IBlockState state = worldIn.getBlockState(pos);
      Block block = state.getBlock();
      boolean replaceable = block.isReplaceable(worldIn, pos);

      if (!replaceable) {
        pos = pos.up();
      }

      EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double((playerIn.rotationYaw * 4 / 360) + 0.5) & 3);
      BlockPos blockpos = pos.offset(enumfacing);

      if (playerIn.canPlayerEdit(pos, facing, stack) && playerIn.canPlayerEdit(blockpos, facing, stack)) {
        boolean replaceable2 = worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
        boolean replaceableOrAir = replaceable || worldIn.isAirBlock(pos);
        boolean replaceableOrAir2 = replaceable2 || worldIn.isAirBlock(blockpos);

        if (replaceableOrAir && replaceableOrAir2 && worldIn.getBlockState(pos.down()).isFullyOpaque() && worldIn.getBlockState(blockpos.down()).isFullyOpaque()) {
          IBlockState state1 = this.block.getDefaultState().withProperty(BlockBed.OCCUPIED, false).withProperty(BlockBed.FACING, enumfacing).withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);

          if (worldIn.setBlockState(pos, state1, 11)) {
            IBlockState state2 = state1.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD);
            worldIn.setBlockState(blockpos, state2, 11);
          }

          SoundType soundType = state1.getBlock().getSoundType(state1, worldIn, pos, playerIn);
          worldIn.playSound(null, pos, soundType.getPlaceSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1) / 2, soundType.getPitch() * 0.8F);
          stack.stackSize--;

          return EnumActionResult.SUCCESS;
        }
      }
    }

    return EnumActionResult.FAIL;
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip, advanced);
  }
}
