package net.mcfr.network;

import net.mcfr.craftsmanship.BlockCircularSaw;
import net.mcfr.craftsmanship.BlockLoom;
import net.mcfr.craftsmanship.BlockMortar;
import net.mcfr.craftsmanship.BlockTanningRack;
import net.mcfr.craftsmanship.guis.ContainerLargeWorkbench;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.mcfr.craftsmanship.guis.GuiCircularSaw;
import net.mcfr.craftsmanship.guis.GuiLargeWorkbench;
import net.mcfr.craftsmanship.guis.GuiLoom;
import net.mcfr.craftsmanship.guis.GuiMortar;
import net.mcfr.craftsmanship.guis.GuiTanningRack;
import net.mcfr.craftsmanship.tile_entities.TileEntityCircularSaw;
import net.mcfr.craftsmanship.tile_entities.TileEntityLoom;
import net.mcfr.craftsmanship.tile_entities.TileEntityMortar;
import net.mcfr.craftsmanship.tile_entities.TileEntityTanningRack;
import net.mcfr.decoration.container_blocks.BlockBookshelf;
import net.mcfr.decoration.container_blocks.BlockPallet;
import net.mcfr.decoration.container_blocks.guis.ContainerRestrictable;
import net.mcfr.decoration.container_blocks.guis.GuiRestrictedChest;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityBookshelf;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityConstructionCrate;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityCrate;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityFoodCrate;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityLittleChest;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityPallet;
import net.mcfr.decoration.furniture.tile_entities.TileEntityEndTable;
import net.mcfr.decoration.furniture.tile_entities.TileEntityTable;
import net.mcfr.decoration.furniture.tile_entities.TileEntityFootTable;
import net.mcfr.forge.guis.ContainerAnvil;
import net.mcfr.forge.guis.ContainerStove;
import net.mcfr.forge.guis.GuiAnvil;
import net.mcfr.forge.guis.GuiStove;
import net.mcfr.forge.tile_entities.TileEntityStove;
import net.mcfr.guis.CustomGuiScreens;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Classe permettant la gestion des interfaces personnalisées.
 * 
 * @author Mc-Fr
 */
public class GuiHandler implements IGuiHandler {
  @Override
  public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    BlockPos pos = new BlockPos(x, y, z);
    TileEntity tileEntity = world.getTileEntity(pos);

    switch (CustomGuiScreens.values()[id]) {
      case ANVIL:
        return new ContainerAnvil(player.inventory, world, pos);
      case STOVE:
        if (tileEntity instanceof TileEntityStove)
          return new ContainerStove(player.inventory, (TileEntityStove) tileEntity);
        break;
      case LARGE_WORKBENCH:
        return new ContainerLargeWorkbench(player.inventory, world, pos);
      case LOOM:
        if (tileEntity instanceof TileEntityLoom)
          return new ContainerRack(player.inventory, (TileEntityLoom) tileEntity, player, BlockLoom.class);
        break;
      case TANNING_RACK:
        if (tileEntity instanceof TileEntityTanningRack)
          return new ContainerRack(player.inventory, (TileEntityTanningRack) tileEntity, player, BlockTanningRack.class);
        break;
      case SAW:
        if (tileEntity instanceof TileEntityCircularSaw)
          return new ContainerRack(player.inventory, (TileEntityCircularSaw) tileEntity, player, BlockCircularSaw.class);
        break;
      case MORTAR:
        if (tileEntity instanceof TileEntityMortar)
          return new ContainerRack(player.inventory, (TileEntityMortar) tileEntity, player, BlockMortar.class);
        break;
      case BOOKSHELF:
        if (tileEntity instanceof TileEntityBookshelf)
          return new ContainerRestrictable(player.inventory, (TileEntityBookshelf) tileEntity, player, BlockBookshelf.class);
        break;
      case CONSTRUCTION_CRATE:
        if (tileEntity instanceof TileEntityConstructionCrate)
          return new ContainerRestrictable(player.inventory, (TileEntityConstructionCrate) tileEntity, player, null);
        break;
      case CRATE:
        if (tileEntity instanceof TileEntityCrate)
          return new ContainerRestrictable(player.inventory, (TileEntityCrate) tileEntity, player, null);
        break;
      case FOOD_CRATE:
        if (tileEntity instanceof TileEntityFoodCrate)
          return new ContainerRestrictable(player.inventory, (TileEntityFoodCrate) tileEntity, player, null);
        break;
      case LITTLE_CHEST:
        if (tileEntity instanceof TileEntityLittleChest)
          return new ContainerRestrictable(player.inventory, (TileEntityLittleChest) tileEntity, player, null);
        break;
      case PALLET:
        if (tileEntity instanceof TileEntityPallet)
          return new ContainerRestrictable(player.inventory, (TileEntityPallet) tileEntity, player, BlockPallet.class);
        break;
      case TABLE:
        if (tileEntity instanceof TileEntityTable)
          return new ContainerRestrictable(player.inventory, (TileEntityTable) tileEntity, player, null);
        break;
      case END_TABLE:
        if (tileEntity instanceof TileEntityEndTable)
          return new ContainerRestrictable(player.inventory, (TileEntityEndTable) tileEntity, player, null);
        break;
      case FOOT_TABLE:
        if (tileEntity instanceof TileEntityFootTable)
          return new ContainerRestrictable(player.inventory, (TileEntityFootTable) tileEntity, player, null);
        break;
    }

    return null;
  }

  @Override
  public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    BlockPos pos = new BlockPos(x, y, z);
    TileEntity tileEntity = world.getTileEntity(pos);

    switch (CustomGuiScreens.values()[id]) {
      case ANVIL:
        return new GuiAnvil(player.inventory, world, pos);
      case STOVE:
        if (tileEntity instanceof TileEntityStove)
          return new GuiStove(player.inventory, (TileEntityStove) tileEntity);
        break;
      case LARGE_WORKBENCH:
        return new GuiLargeWorkbench(player.inventory, world, pos);
      case LOOM:
        if (tileEntity instanceof TileEntityLoom)
          return new GuiLoom(player.inventory, (TileEntityLoom) tileEntity, player);
        break;
      case TANNING_RACK:
        if (tileEntity instanceof TileEntityTanningRack)
          return new GuiTanningRack(player.inventory, (TileEntityTanningRack) tileEntity, player);
        break;
      case SAW:
        if (tileEntity instanceof TileEntityCircularSaw)
          return new GuiCircularSaw(player.inventory, (TileEntityCircularSaw) tileEntity, player);
        break;
      case MORTAR:
        if (tileEntity instanceof TileEntityMortar)
          return new GuiMortar(player.inventory, (TileEntityMortar) tileEntity, player);
        break;
      case BOOKSHELF:
        if (tileEntity instanceof TileEntityBookshelf)
          return new GuiRestrictedChest(player.inventory, (TileEntityBookshelf) tileEntity, player, BlockBookshelf.class);
        break;
      case CRATE:
        if (tileEntity instanceof TileEntityCrate)
          return new GuiRestrictedChest(player.inventory, (TileEntityCrate) tileEntity, player, null);
        break;
      case CONSTRUCTION_CRATE:
        if (tileEntity instanceof TileEntityConstructionCrate)
          return new GuiRestrictedChest(player.inventory, (TileEntityConstructionCrate) tileEntity, player, null);
        break;
      case FOOD_CRATE:
        if (tileEntity instanceof TileEntityFoodCrate)
          return new GuiRestrictedChest(player.inventory, (TileEntityFoodCrate) tileEntity, player, null);
        break;
      case LITTLE_CHEST:
        if (tileEntity instanceof TileEntityLittleChest)
          return new GuiRestrictedChest(player.inventory, (TileEntityLittleChest) tileEntity, player, null);
        break;
      case PALLET:
        if (tileEntity instanceof TileEntityPallet)
          return new GuiRestrictedChest(player.inventory, (TileEntityPallet) tileEntity, player, BlockPallet.class);
        break;
      case TABLE:
        if (tileEntity instanceof TileEntityTable)
          return new GuiRestrictedChest(player.inventory, (TileEntityTable) tileEntity, player, null);
        break;
      case END_TABLE:
        if (tileEntity instanceof TileEntityEndTable)
          return new GuiRestrictedChest(player.inventory, (TileEntityEndTable) tileEntity, player, null);
        break;
      case FOOT_TABLE:
        if (tileEntity instanceof TileEntityFootTable)
          return new GuiRestrictedChest(player.inventory, (TileEntityFootTable) tileEntity, player, null);
        break;
    }

    return null;
  }
}