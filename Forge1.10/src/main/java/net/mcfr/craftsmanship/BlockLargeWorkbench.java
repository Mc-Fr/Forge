package net.mcfr.craftsmanship;

import net.mcfr.McfrMain;
import net.mcfr.commons.McfrBlock;
import net.mcfr.guis.CustomGuiScreens;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Altelier large (grille 5x5).
 *
 * @author Mc-Fr
 */
public class BlockLargeWorkbench extends McfrBlock {
  public BlockLargeWorkbench() {
    super("large_workbench", Material.WOOD, SoundType.WOOD, 2, 5, "axe", 0, CreativeTabs.DECORATIONS);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    if (!worldIn.isRemote) {
      playerIn.openGui(McfrMain.instance, CustomGuiScreens.LARGE_WORKBENCH.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
    }

    return true;
  }
}
