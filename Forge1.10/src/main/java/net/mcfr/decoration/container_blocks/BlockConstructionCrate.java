package net.mcfr.decoration.container_blocks;

import net.mcfr.decoration.container_blocks.tile_entities.TileEntityConstructionCrate;
import net.mcfr.guis.CustomGuiScreens;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Caisse de construction (incassable).
 *
 * @author Mc-Fr
 */
public class BlockConstructionCrate extends McfrBlockContainer<TileEntityConstructionCrate> {
  public BlockConstructionCrate() {
    super("construction_crate", Material.BARRIER, SoundType.WOOD, 100, 5, null, TileEntityConstructionCrate.class);
  }

  @Override
  public TileEntityConstructionCrate createNewTileEntity(World world, int meta) {
    return new TileEntityConstructionCrate();
  }

  @Override
  public CustomGuiScreens getGui() {
    return CustomGuiScreens.CONSTRUCTION_CRATE;
  }
}
