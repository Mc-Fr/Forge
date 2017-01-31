package net.mcfr.misc;

import java.util.List;

import net.mcfr.commons.McfrItem;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.OpenEditPaperMessage;
import net.mcfr.utils.NBTUtils;
import net.minecraft.command.CommandException;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("deprecation")
public class ItemSignedPaper extends McfrItem {
  public ItemSignedPaper() {
    super("signed_paper", 1, CreativeTabs.MISC);
  }

  @Override
  public String getItemStackDisplayName(ItemStack stack) {
    if (stack.hasTagCompound()) {
      NBTTagCompound compound = stack.getTagCompound();
      String title = compound.getString("Title");

      if (!StringUtils.isNullOrEmpty(title))
        return title;
    }

    return super.getItemStackDisplayName(stack);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    if (stack.hasTagCompound()) {
      NBTTagCompound compound = stack.getTagCompound();
      String author = compound.getString("Author");

      if (!StringUtils.isNullOrEmpty(author)) {
        tooltip.add(TextFormatting.GRAY + I18n.translateToLocalFormatted("book.byAuthor", author));
      }

      tooltip.add(TextFormatting.GRAY + I18n.translateToLocal("book.generation." + compound.getInteger("generation")));
    }
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
    if (!worldIn.isRemote) {
      resolveContents(itemStackIn, playerIn);
      McfrNetworkWrapper.getInstance().sendTo(new OpenEditPaperMessage(hand, false), (EntityPlayerMP) playerIn);
    }

    return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
  }

  private void resolveContents(ItemStack stack, EntityPlayer player) {
    if (stack != null && stack.getTagCompound() != null) {
      NBTTagCompound compound = stack.getTagCompound();

      if (!compound.getBoolean("Resolved")) {
        compound.setBoolean("Resolved", true);

        if (validPaperTagContents(compound)) {
          NBTTagList pages = compound.getTagList("Pages", NBTUtils.TAG_STRING);

          for (int i = 0; i < pages.tagCount(); ++i) {
            String page = pages.getStringTagAt(i);
            ITextComponent text;

            try {
              text = ITextComponent.Serializer.fromJsonLenient(page);
              text = TextComponentUtils.processComponent(player, text, player);
            }
            catch (CommandException ex) {
              text = new TextComponentString(page);
            }

            pages.set(i, new NBTTagString(ITextComponent.Serializer.componentToJson(text)));
          }

          compound.setTag("Pages", pages);

          if (player instanceof EntityPlayerMP && player.getHeldItemMainhand() == stack) {
            Slot slot = player.openContainer.getSlotFromInventory(player.inventory, player.inventory.currentItem);
            ((EntityPlayerMP) player).connection.sendPacket(new SPacketSetSlot(0, slot.slotNumber, stack));
          }
        }
      }
    }
  }

  @Override
  @SideOnly(Side.CLIENT)
  public boolean hasEffect(ItemStack stack) {
    return true;
  }

  /**
   * Cette méthode retourne vrai si le tag NBT "Pages" est valide.
   */
  public static boolean isNBTValid(NBTTagCompound nbt) {
    if (nbt != null && nbt.hasKey("Pages", NBTUtils.TAG_LIST)) {
      NBTTagList list = nbt.getTagList("Pages", NBTUtils.TAG_STRING);
      boolean valid = true;

      for (int i = 0; i < list.tagCount(); i++) {
        String s = list.getStringTagAt(i);
        if (s == null || s.length() > 32767) {
          valid = false;
          break;
        }
      }

      return valid;
    }

    return false;
  }

  public static boolean validPaperTagContents(NBTTagCompound nbt) {
    if (!ItemSignedPaper.isNBTValid(nbt) || !nbt.hasKey("Title", NBTUtils.TAG_STRING)) {
      return false;
    }
    else {
      String title = nbt.getString("Title");
      return title != null && title.length() <= 32 ? nbt.hasKey("Author", NBTUtils.TAG_STRING) : false;
    }
  }
}
