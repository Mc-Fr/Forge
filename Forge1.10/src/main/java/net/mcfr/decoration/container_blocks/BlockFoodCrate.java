package net.mcfr.decoration.container_blocks;

import net.mcfr.decoration.container_blocks.tile_entities.TileEntityFoodCrate;
import net.mcfr.guis.CustomGuiScreens;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Fût de nourriture.
 *
 * @author Mc-Fr
 */
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
