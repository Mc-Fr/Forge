package net.mcfr.decoration.beds;

import java.util.List;

import net.mcfr.utils.FacingUtils;
import net.mcfr.utils.NameUtils;
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
import net.minecraft.world.World;

/**
 * Item de lit.
 *
 * @author Mc-Fr
 */
public class McfrItemBed extends ItemBed {
  /** Le lit à poser */
  private final BlockBed block;

  /**
   * Crée un nouvel item.
   * 
   * @param name le nom
   * @param block le bloc à poser
   */
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
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (world.isRemote) {
      return EnumActionResult.SUCCESS;
    }
    else if (facing == EnumFacing.UP) {
      boolean replaceable = world.getBlockState(pos).getBlock().isReplaceable(world, pos);

      if (!replaceable)
        pos = pos.up();

      EnumFacing side = FacingUtils.getHorizontalFacing(player).getOpposite();
      BlockPos sidePos = pos.offset(side);

      if (player.canPlayerEdit(pos, facing, stack) && player.canPlayerEdit(sidePos, facing, stack)) {
        boolean sideReplaceable = world.getBlockState(sidePos).getBlock().isReplaceable(world, sidePos);
        boolean replaceableOrAir = replaceable || world.isAirBlock(pos);
        boolean sideReplaceableOrAir = sideReplaceable || world.isAirBlock(sidePos);

        if (replaceableOrAir && sideReplaceableOrAir && world.getBlockState(pos.down()).isFullyOpaque() && world.getBlockState(sidePos.down()).isFullyOpaque()) {
          IBlockState state = this.block.getDefaultState().withProperty(BlockBed.OCCUPIED, false).withProperty(BlockBed.FACING, side).withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT);

          if (world.setBlockState(pos, state, 11)) {
            world.setBlockState(sidePos, state.withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 11);
          }

          SoundType soundType = state.getBlock().getSoundType(state, world, pos, player);
          world.playSound(null, pos, soundType.getPlaceSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1) / 2, soundType.getPitch() * 0.8f);
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
