package net.mcfr;

import net.mcfr.craftsmanship.tileEntities.TileEntityCircularSaw;
import net.mcfr.craftsmanship.tileEntities.TileEntityLoom;
import net.mcfr.craftsmanship.tileEntities.TileEntityTanningRack;
import net.mcfr.decoration.containerBlocks.BlockBarrel;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityBarrel;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityBookshelf;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityCrate;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityFoodCrate;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityLittleChest;
import net.mcfr.decoration.containerBlocks.tileEntities.TileEntityPallet;
import net.mcfr.decoration.furniture.tileEntities.TileEntityArmChair;
import net.mcfr.decoration.furniture.tileEntities.TileEntityShowcase;
import net.mcfr.decoration.furniture.tileEntities.TileEntityWeaponsStand;
import net.mcfr.decoration.furniture.tileEntities.TileEntityWoodenBench;
import net.mcfr.decoration.furniture.tileEntities.TileEntityWoodenChair;
import net.mcfr.decoration.furniture.tileEntities.TileEntityWoodenStool;
import net.mcfr.decoration.lighting.tileEntities.TileEntityCampfire;
import net.mcfr.decoration.lighting.tileEntities.TileEntityChandelier;
import net.mcfr.decoration.misc.tileEntities.TileEntityCarpet;
import net.mcfr.decoration.misc.tileEntities.TileEntitySupport;
import net.mcfr.decoration.signs.tileEntities.TileEntityNormalSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityOrpSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityPaperSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityTombstone;
import net.mcfr.decoration.signs.tileEntities.TileEntityWallNote;
import net.mcfr.entities.EntityGrapnel;
import net.mcfr.entities.EntitySailBoat;
import net.mcfr.entities.mobs.entity.EntityBormoth;
import net.mcfr.entities.mobs.entity.EntityGalt;
import net.mcfr.entities.mobs.entity.EntityHoen;
import net.mcfr.entities.mobs.entity.EntityNiale;
import net.mcfr.entities.mobs.entity.EntitySiker;
import net.mcfr.event.EventsHandler;
import net.mcfr.forge.tileEntities.TileEntityBellows;
import net.mcfr.forge.tileEntities.TileEntityStove;
import net.mcfr.network.GuiHandler;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.OpenEditMcfrSignMessage;
import net.mcfr.network.OpenEditMcfrSignMessageHandler;
import net.mcfr.network.OpenEditPaperMessage;
import net.mcfr.network.OpenEditPaperMessageHandler;
import net.mcfr.network.OpenEditWallNoteMessage;
import net.mcfr.network.OpenEditWallNoteMessageHandler;
import net.mcfr.network.SyncEntityMessage;
import net.mcfr.network.UpdateWallNoteMessage;
import net.mcfr.network.UpdateWallNoteMessageHandler;
import net.mcfr.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Classe pricipale.
 * 
 * @author Mc-Fr
 */
@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION)
public class McfrMain {
  @Instance
  public static McfrMain instance;

  /**
   * Proxys client et serveur.
   */
  @SidedProxy(clientSide = "net.mcfr.proxy.ClientProxy", serverSide = "net.mcfr.proxy.CommonProxy")
  public static CommonProxy proxy;

  @EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    McfrBlocks.init();
    McfrItems.init();
    McfrCrafts.registerCrafts();
    McfrCrafts.registerAnvilCrafts();
    McfrCrafts.registerFurnaceRecipes();

    registerTileEntities();
    registerEntities();
  }

  @EventHandler
  public void init(FMLInitializationEvent e) {
    proxy.register();
    registerEventHandlers();
    NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    registerPackets();
  }

  private void registerEventHandlers() {
    MinecraftForge.EVENT_BUS.register(BlockBarrel.TILE_ENTITY_SAVER);
    MinecraftForge.EVENT_BUS.register(new EventsHandler());
  }

  private void registerPackets() {
    McfrNetworkWrapper.registerPacket(OpenEditPaperMessageHandler.class, OpenEditPaperMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(OpenEditMcfrSignMessageHandler.class, OpenEditMcfrSignMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(OpenEditWallNoteMessageHandler.class, OpenEditWallNoteMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(UpdateWallNoteMessageHandler.class, UpdateWallNoteMessage.class, Side.SERVER);
    McfrNetworkWrapper.registerPacket(SyncEntityMessage.ClientHandler.class, SyncEntityMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(SyncEntityMessage.ServerHandler.class, SyncEntityMessage.class, Side.SERVER);
  }

  private void registerTileEntities() {
    registerTileEntity(TileEntityBarrel.class, "barrel");

    registerTileEntity(TileEntityBellows.class, "bellows");
    registerTileEntity(TileEntityStove.class, "stove");

    registerTileEntity(TileEntityCampfire.class, "campfire");
    registerTileEntity(TileEntityChandelier.class, "chandelier");

    registerTileEntity(TileEntityCarpet.class, "carpet");

    registerTileEntity(TileEntityBookshelf.class, "bookshelf");
    registerTileEntity(TileEntityCrate.class, "crate");
    registerTileEntity(TileEntityFoodCrate.class, "food_crate");
    registerTileEntity(TileEntityLittleChest.class, "little_chest");
    registerTileEntity(TileEntityPallet.class, "pallet");

    registerTileEntity(TileEntityCircularSaw.class, "circular_saw");
    registerTileEntity(TileEntityLoom.class, "loom");
    registerTileEntity(TileEntityTanningRack.class, "tanning_rack");

    registerTileEntity(TileEntityNormalSign.class, "sign");
    registerTileEntity(TileEntityPaperSign.class, "paper_sign");
    registerTileEntity(TileEntityOrpSign.class, "orp_sign");
    registerTileEntity(TileEntityTombstone.class, "tombstone");
    registerTileEntity(TileEntityWallNote.class, "wall_note");

    registerTileEntity(TileEntityShowcase.class, "showcase");
    registerTileEntity(TileEntityWeaponsStand.class, "weapons_stand");

    registerTileEntity(TileEntitySupport.class, "support");

    registerTileEntity(TileEntityArmChair.class, "arm_chair");
    registerTileEntity(TileEntityWoodenBench.class, "wooden_bench");
    registerTileEntity(TileEntityWoodenChair.class, "wooden_chair");
    registerTileEntity(TileEntityWoodenStool.class, "wooden_stool");
  }

  private void registerEntities() {
    EntityRegistry.registerModEntity(EntityGrapnel.class, "grapnel", 1000, this, 10, 3, true);
    EntityRegistry.registerModEntity(EntitySailBoat.class, "sailboat", 1001, this, 80, 3, true);

    EntityRegistry.registerModEntity(EntitySiker.class, "siker", 1, this, 80, 3, true, 0xBA9B54, 0x5E4B23);
    EntityRegistry.registerModEntity(EntityBormoth.class, "bormoth", 2, this, 80, 3, true, 1000, 1000);
    EntityRegistry.registerModEntity(EntityHoen.class, "hoen", 3, this, 80, 3, true, 0x7E1620, 0x514445);
    EntityRegistry.registerModEntity(EntityGalt.class, "galt", 4, this, 80, 3, true, 0xc7c7c7, 0x81616F);
    EntityRegistry.registerModEntity(EntityNiale.class, "niale", 5, this, 80, 3, true, 0xd0c0c0, 0x180808);
  }

  /**
   * Enregistre une tile entity.
   *
   * @param tileEntityClass la tile entity
   * @param id son ID
   */
  private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
    GameRegistry.registerTileEntity(tileEntityClass, id);
  }
}