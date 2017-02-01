package net.mcfr.decoration.lighting;

import java.util.List;

import net.mcfr.McfrBlocks;
import net.mcfr.commons.McfrItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLantern extends McfrItem {
  private final boolean isPaper;

  public ItemLantern(boolean isPaper) {
    super((isPaper ? "paper_" : "") + "lantern", 64, CreativeTabs.DECORATIONS);
    this.isPaper = isPaper;
    setHasSubtypes(true);
  }

  @Override
  public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (playerIn.canPlayerEdit(pos, facing, stack)) {
      IBlockState state = worldIn.getBlockState(pos);
      Block block = state.getBlock();

      if (block == Blocks.SNOW_LAYER && state.getValue(BlockSnow.LAYERS) < 1) {
        facing = EnumFacing.UP;
      }
      else if (!block.isReplaceable(worldIn, pos)) {
        pos = pos.offset(facing);
      }

      BlockLantern lantern = McfrBlocks.getLantern(EnumLanternColor.byMetadata(stack.getMetadata()), isPaper());

      if (stack.stackSize > 0 && lantern.canPlaceBlockOnSide(worldIn, pos, facing.getOpposite())) {
        IBlockState state1 = lantern.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, 0, playerIn);

        if (worldIn.setBlockState(pos, state1)) {
          // state1.getBlock().onBlockPlacedBy(worldIn, pos, state1, playerIn, stack);

          SoundType soundtype = lantern.getSoundType(state1, worldIn, pos, null);
          worldIn.playSound(playerIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ()), soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1) / 2.0f, soundtype.getPitch() * 0.8F);
          stack.stackSize--;

          return EnumActionResult.SUCCESS;
        }
      }
    }

    return EnumActionResult.FAIL;
  }

  public boolean isPaper() {
    return this.isPaper;
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return getUnlocalizedName() + "." + EnumLanternColor.byMetadata(stack.getMetadata()).getName();
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
    for (int i = 0; i < EnumLanternColor.values().length; i++) {
      subItems.add(new ItemStack(itemIn, 1, i));
    }
  }
}
