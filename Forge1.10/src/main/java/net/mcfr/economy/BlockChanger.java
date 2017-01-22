package net.mcfr.economy;

import javax.annotation.Nullable;

import net.mcfr.McfrItems;
import net.mcfr.commons.McfrBlockOrientable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Bloc changeur de monnaie.
 *
 * @author Mc-Fr
 */
public class BlockChanger extends McfrBlockOrientable {
  public BlockChanger() {
    super("changer", Material.ROCK, SoundType.STONE, 1.5f, 10f, "pickaxe", 0, CreativeTabs.MISC);
  }

  /**
   * Permet de faire le change de la monnaie.<br/>
   * Taux de conversion :
   * <ul>
   * <li>1 platine <-> 16 élinvar</li>
   * <li>1 élinvar <-> 64 laiton</li>
   * </ul>
   */
  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem,
      EnumFacing side, float hitX, float hitY, float hitZ) {
    InventoryPlayer i = playerIn.inventory;
    ItemStack stack = i.getStackInSlot(i.currentItem);

    if (stack != null && stack.getItem() == McfrItems.COIN) {
      switch (stack.getMetadata()) {
        case 0:
          if (stack.stackSize == 64)
            i.setInventorySlotContents(i.currentItem, new ItemStack(McfrItems.COIN, 1, 1));
          else
            return false;
          break;
        case 1:
          if (stack.stackSize == 16)
            i.setInventorySlotContents(i.currentItem, new ItemStack(McfrItems.COIN, 1, 2));
          else if (stack.stackSize == 1)
            i.setInventorySlotContents(i.currentItem, new ItemStack(McfrItems.COIN, 64, 0));
          else
            return false;
          break;
        case 2:
          if (stack.stackSize == 1)
            i.setInventorySlotContents(i.currentItem, new ItemStack(McfrItems.COIN, 16, 1));
          else
            return false;
          break;
        default:
          return false;
      }

      return true;
    }

    return false;
  }
}
