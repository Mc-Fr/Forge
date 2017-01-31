package net.mcfr.misc;

import net.mcfr.commons.McfrItem;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.OpenEditPaperMessage;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemWriteablePaper extends McfrItem {
  public static final int MAX_PAGES_NB = 3;

  public ItemWriteablePaper() {
    super("writeable_paper", 1, CreativeTabs.MISC);
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
    if (!worldIn.isRemote)
      McfrNetworkWrapper.getInstance().sendTo(new OpenEditPaperMessage(hand, true), (EntityPlayerMP) playerIn);
    return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
  }
}
