package net.mcfr.farming;

import java.util.ArrayList;
import java.util.List;

import net.mcfr.McfrBlocks;
import net.mcfr.McfrItems;
import net.minecraft.item.ItemStack;

/**
 * Bloc du haut du cep de vigne.
 *
 * @author Mc-Fr
 */
public class BlockVineTop extends BlockBushTop {
  public BlockVineTop() {
    super("vine");
  }

  @Override
  public BlockBushBase getBaseBlock() {
    return McfrBlocks.VINE_BASE;
  }

  @Override
  public List<ItemStack> getItems() {
    List<ItemStack> list = new ArrayList<>();

    list.add(new ItemStack(McfrItems.GRAPES));

    return list;
  }
}
