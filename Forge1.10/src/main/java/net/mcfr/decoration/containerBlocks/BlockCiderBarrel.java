package net.mcfr.decoration.containerBlocks;

import net.mcfr.McfrItems;
import net.minecraft.item.Item;

public class BlockCiderBarrel extends BlockBarrel {
    public BlockCiderBarrel() {
        super("cider");
    }
    
    @Override
    public Item getItemDropped() {
        return McfrItems.CIDER_BARREL;
    }
    
    @Override
    public Item getEmptyContainer() {
        return McfrItems.EMPTY_BOWL;
    }
    
    @Override
    public Item getFullContainer() {
        return McfrItems.CIDER_BOWL;
    }
}
