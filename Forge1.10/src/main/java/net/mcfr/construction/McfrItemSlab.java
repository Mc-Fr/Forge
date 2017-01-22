package net.mcfr.construction;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class McfrItemSlab<T extends McfrBlockSlab<?>> extends ItemSlab {
  public McfrItemSlab(Block block, T slab, T doubleSlab) {
    super(block, slab, doubleSlab);
    setRegistryName(slab.getRegistryName());
  }
  
  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip, advanced);
  }
}
