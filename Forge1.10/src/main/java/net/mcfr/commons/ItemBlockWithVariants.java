package net.mcfr.commons;

import java.util.List;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Classe utilisée pour les blocs ayant plusieurs metadata.
 * 
 * @author Mc-Fr
 */
public class ItemBlockWithVariants extends ItemBlock {
  public ItemBlockWithVariants(IBlockWithVariants block) {
    super(getBlock(block));

    setRegistryName(getBlock(block).getRegistryName());
    setHasSubtypes(true);
  }

  private static Block getBlock(IBlockWithVariants block) {
    if (block instanceof Block)
      return (Block) block;
    throw new IllegalArgumentException(String.format("The given Block '%s' is not an instance of IBlockWithVariants!", block));
  }

  @Override
  public int getMetadata(int damage) {
    return damage;
  }

  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return super.getUnlocalizedName(stack) + "." + ((IBlockWithVariants) this.block).getVariantName(stack.getMetadata());
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    super.addInformation(stack, playerIn, tooltip, advanced);
    NameUtils.addItemInformation(this, stack, playerIn, tooltip, advanced);
  }
}