package net.mcfr.forge;

import net.mcfr.McfrMain;
import net.mcfr.commons.McfrBlockOrientable;
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
 * Cette enclume est un atelier possédant une matrice 5x5.
 *
 * @author Mc-Fr
 */
public class McfrBlockAnvil extends McfrBlockOrientable {
  public McfrBlockAnvil() {
    super("anvil", Material.ANVIL, SoundType.ANVIL, 5, 2000, "pickaxe", 1, CreativeTabs.DECORATIONS);
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    if (!worldIn.isRemote) {
      playerIn.openGui(McfrMain.instance, CustomGuiScreens.ANVIL.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
    }

    return true;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }
}
