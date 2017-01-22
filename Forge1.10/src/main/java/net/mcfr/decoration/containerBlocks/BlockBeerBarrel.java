package net.mcfr.decoration.containerBlocks;

import net.mcfr.McfrItems;
import net.minecraft.item.Item;

public class BlockBeerBarrel extends BlockBarrel {
    public BlockBeerBarrel() {
        super("beer");
    }
    
    @Override
    public Item getItemDropped() {
        return McfrItems.BEER_BARREL;
    }
    
    @Override
    public Item getEmptyContainer() {
        return McfrItems.EMPTY_TANKARD;
    }
    
    @Override
    public Item getFullContainer() {
        return McfrItems.BEER_TANKARD;
    }
}
