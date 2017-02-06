package net.mcfr.decoration.containerBlocks;

import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityFoodCrate;
import net.mcfr.guis.CustomGuiScreens;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockFoodCrate extends McfrBlockContainer<TileEntityFoodCrate> {
  public BlockFoodCrate() {
    super("food_crate", Material.WOOD, SoundType.WOOD, 2, 5, "axe", TileEntityFoodCrate.class);
  }

  @Override
  public TileEntityFoodCrate createNewTileEntity(World worldIn, int meta) {
    return new TileEntityFoodCrate();
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.FOOD_CRATE;
  }
}
