package net.mcfr.forge.guis;

import net.mcfr.craftsmanship.guis.ContainerLargeWorkbench;
import net.mcfr.forge.recipes.LargeRecipesHandler;
import net.mcfr.forge.tile_entities.TileEntityStove;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Conteneur de l'interface de l'enclume.
 *
 * @author Mc-Fr
 */
public class ContainerAnvil extends ContainerLargeWorkbench {
  /** La longueur des côtes de la zone de détection des haut-fourneaux. */
  private static final int DETECTION_SIZE = 3;

  /**
   * Crée un conteneur pour l'enclume.
   * 
   * @param playerInventory l'invetaire du joueur
   * @param world le monde
   * @param pos la position
   */
  public ContainerAnvil(InventoryPlayer playerInventory, World world, BlockPos pos) {
    super(playerInventory, world, pos);
    setCraftMatrixAnvil();
  }

  @Override
  public void onCraftMatrixChanged(IInventory inventoryIn) {
    getCraftResult().setInventorySlotContents(0, LargeRecipesHandler.findMatchingAnvilRecipe(getCraftMatrix(), getStovesTemperature(), getWorld()));
  }

  /**
   * @return la plus haute température des haut-fourneaux dans la zone d'effet
   */
  private int getStovesTemperature() {
    int maxTemperature = 0;

    for (int x = -DETECTION_SIZE; x <= DETECTION_SIZE; x++) {
      for (int z = -DETECTION_SIZE; z <= DETECTION_SIZE; z++) {
        TileEntity te = getWorld().getTileEntity(getPos().east(x).south(z));

        if (te instanceof TileEntityStove) {
          maxTemperature = Math.max(maxTemperature, ((TileEntityStove) te).getTemperature());
        }
      }
    }

    return maxTemperature;
  }
}
