package net.mcfr.decoration.containerBlocks;

import net.mcfr.McfrItems;
import net.minecraft.item.Item;

public class BlockRumBarrel extends BlockBarrel {
    public BlockRumBarrel() {
        super("rum");
    }
    
    @Override
    public Item getItemDropped() {
        return McfrItems.RUM_BARREL;
    }
    
    @Override
    public Item getEmptyContainer() {
        return McfrItems.EMPTY_BOTTLE;
    }
    
    @Override
    public Item getFullContainer() {
        return McfrItems.RUM_BOTTLE;
    }
}
