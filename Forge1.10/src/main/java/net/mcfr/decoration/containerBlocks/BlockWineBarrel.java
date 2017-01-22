package net.mcfr.decoration.containerBlocks;

import net.mcfr.McfrItems;
import net.minecraft.item.Item;

public class BlockWineBarrel extends BlockBarrel {
    public BlockWineBarrel() {
        super("wine");
    }
    
    @Override
    public Item getItemDropped() {
        return McfrItems.WINE_BARREL;
    }
    
    @Override
    public Item getEmptyContainer() {
        return McfrItems.EMPTY_GLASS;
    }
    
    @Override
    public Item getFullContainer() {
        return McfrItems.WINE_GLASS;
    }
}
