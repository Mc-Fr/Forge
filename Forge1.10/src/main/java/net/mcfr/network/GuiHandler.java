package net.mcfr.network;

import net.mcfr.commons.CustomGuiScreens;
import net.mcfr.craftsmanship.BlockCircularSaw;
import net.mcfr.craftsmanship.BlockLoom;
import net.mcfr.craftsmanship.BlockTanningRack;
import net.mcfr.craftsmanship.guis.ContainerLargeWorkbench;
import net.mcfr.craftsmanship.guis.ContainerRack;
import net.mcfr.craftsmanship.guis.GuiCircularSaw;
import net.mcfr.craftsmanship.guis.GuiLargeWorkbench;
import net.mcfr.craftsmanship.guis.GuiLoom;
import net.mcfr.craftsmanship.guis.GuiTanningRack;
import net.mcfr.craftsmanship.tileEntities.TileEntityCircularSaw;
import net.mcfr.craftsmanship.tileEntities.TileEntityLoom;
import net.mcfr.craftsmanship.tileEntities.TileEntityTanningRack;
import net.mcfr.decoration.containerBlocks.BlockBookshelf;
import net.mcfr.decoration.containerBlocks.BlockCrate;
import net.mcfr.decoration.containerBlocks.BlockFoodCrate;
import net.mcfr.decoration.containerBlocks.BlockLittleChest;
import net.mcfr.decoration.containerBlocks.BlockPallet;
import net.mcfr.decoration.containerBlocks.guis.ContainerRestricted;
import net.mcfr.decoration.containerBlocks.guis.GuiRestrictedChest;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityBookshelf;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityCrate;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityFoodCrate;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityLittleChest;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityPallet;
import net.mcfr.forge.guis.ContainerAnvil;
import net.mcfr.forge.guis.ContainerStove;
import net.mcfr.forge.guis.GuiAnvil;
import net.mcfr.forge.guis.GuiStove;
import net.mcfr.forge.tileEntities.TileEntityStove;
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
      case BOOKSHELF:
        if (tileEntity instanceof TileEntityBookshelf)
          return new ContainerRestricted(player.inventory, (TileEntityBookshelf) tileEntity, player, BlockBookshelf.class);
        break;
      case CRATE:
        if (tileEntity instanceof TileEntityCrate)
          return new ContainerRestricted(player.inventory, (TileEntityCrate) tileEntity, player, BlockCrate.class);
        break;
      case FOOD_CRATE:
        if (tileEntity instanceof TileEntityFoodCrate)
          return new ContainerRestricted(player.inventory, (TileEntityFoodCrate) tileEntity, player, BlockFoodCrate.class);
        break;
      case LITTLE_CHEST:
        if (tileEntity instanceof TileEntityLittleChest)
          return new ContainerRestricted(player.inventory, (TileEntityLittleChest) tileEntity, player, BlockLittleChest.class);
        break;
      case PALLET:
        if (tileEntity instanceof TileEntityPallet)
          return new ContainerRestricted(player.inventory, (TileEntityPallet) tileEntity, player, BlockPallet.class);
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
      case BOOKSHELF:
        if (tileEntity instanceof TileEntityBookshelf)
          return new GuiRestrictedChest(player.inventory, (TileEntityBookshelf) tileEntity, player, BlockBookshelf.class);
        break;
      case CRATE:
        if (tileEntity instanceof TileEntityCrate)
          return new GuiRestrictedChest(player.inventory, (TileEntityCrate) tileEntity, player, BlockCrate.class);
        break;
      case FOOD_CRATE:
        if (tileEntity instanceof TileEntityFoodCrate)
          return new GuiRestrictedChest(player.inventory, (TileEntityFoodCrate) tileEntity, player, BlockFoodCrate.class);
        break;
      case LITTLE_CHEST:
        if (tileEntity instanceof TileEntityLittleChest)
          return new GuiRestrictedChest(player.inventory, (TileEntityLittleChest) tileEntity, player, BlockLittleChest.class);
        break;
      case PALLET:
        if (tileEntity instanceof TileEntityPallet)
          return new GuiRestrictedChest(player.inventory, (TileEntityPallet) tileEntity, player, BlockPallet.class);
        break;
    }

    return null;
  }
}