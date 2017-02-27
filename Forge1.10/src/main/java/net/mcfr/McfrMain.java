package net.mcfr;

import net.mcfr.craftsmanship.tile_entities.TileEntityCircularSaw;
import net.mcfr.craftsmanship.tile_entities.TileEntityLoom;
import net.mcfr.craftsmanship.tile_entities.TileEntityTanningRack;
import net.mcfr.decoration.container_blocks.BlockBarrel;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityBarrel;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityBookshelf;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityCrate;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityFoodCrate;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityLittleChest;
import net.mcfr.decoration.container_blocks.tile_entities.TileEntityPallet;
import net.mcfr.decoration.furniture.tile_entities.TileEntityArmChair;
import net.mcfr.decoration.furniture.tile_entities.TileEntityShowcase;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWeaponsStand;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWoodenBench;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWoodenChair;
import net.mcfr.decoration.furniture.tile_entities.TileEntityWoodenStool;
import net.mcfr.decoration.lighting.tile_entities.TileEntityCampfire;
import net.mcfr.decoration.lighting.tile_entities.TileEntityChandelier;
import net.mcfr.decoration.misc.tile_entities.TileEntityCarpet;
import net.mcfr.decoration.misc.tile_entities.TileEntitySupport;
import net.mcfr.decoration.signs.tile_entities.TileEntityNormalSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityOrpSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityPaperSign;
import net.mcfr.decoration.signs.tile_entities.TileEntityTombstone;
import net.mcfr.decoration.signs.tile_entities.TileEntityWallNote;
import net.mcfr.entities.EntityChatBubble;
import net.mcfr.entities.EntityGrapnel;
import net.mcfr.entities.EntitySailBoat;
import net.mcfr.entities.mobs.entity.EntityBormoth;
import net.mcfr.entities.mobs.entity.EntityFjalla;
import net.mcfr.entities.mobs.entity.EntityGalt;
import net.mcfr.entities.mobs.entity.EntityGronle;
import net.mcfr.entities.mobs.entity.EntityHoen;
import net.mcfr.entities.mobs.entity.EntityNiale;
import net.mcfr.entities.mobs.entity.EntityPryf;
import net.mcfr.entities.mobs.entity.EntitySiker;
import net.mcfr.entities.mobs.entity.EntityTaure;
import net.mcfr.event.BlockEventsHandler;
import net.mcfr.event.PlayerEventsHandler;
import net.mcfr.forge.tile_entities.TileEntityBellows;
import net.mcfr.forge.tile_entities.TileEntityStove;
import net.mcfr.network.CreateChatBubbleMessage;
import net.mcfr.network.DestroyChatBubbleMessage;
import net.mcfr.network.GuiHandler;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.ModVersionCheckMessage;
import net.mcfr.network.OpenEditMcfrSignMessage;
import net.mcfr.network.OpenEditPaperMessage;
import net.mcfr.network.OpenEditWallNoteMessage;
import net.mcfr.network.SyncChatBubbleTypeMessage;
import net.mcfr.network.SyncEntityMessage;
import net.mcfr.network.UpdateChatBubblePositionMessage;
import net.mcfr.network.UpdateWallNoteMessage;
import net.mcfr.proxy.CommonProxy;
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
 * Classe pricipale du mod.
 * 
 * @author Mc-Fr
 */
@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION)
public class McfrMain {
  /** L'instance du mod. */
  @Instance
  public static McfrMain instance;

  /** Proxys client et serveur. */
  @SidedProxy(clientSide = "net.mcfr.proxy.ClientProxy", serverSide = "net.mcfr.proxy.CommonProxy")
  public static CommonProxy proxy;

  /**
   * Pré-initialisation du mod.
   * 
   * @param e l'évènement de pré-initialisation
   */
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

  /**
   * Initialisation du mod.
   * 
   * @param e l'évènement d'initialisation
   */
  @EventHandler
  public void init(FMLInitializationEvent e) {
    proxy.register();
    registerEventHandlers();
    NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    registerPackets();
  }

  /**
   * Enregistre les écouteurs d'évènements.
   */
  private void registerEventHandlers() {
    MinecraftForge.EVENT_BUS.register(BlockBarrel.TILE_ENTITY_SAVER);
    MinecraftForge.EVENT_BUS.register(new PlayerEventsHandler());
    MinecraftForge.EVENT_BUS.register(new BlockEventsHandler());
  }

  /**
   * Enregistre les paquets réseau.
   */
  private void registerPackets() {
    McfrNetworkWrapper.registerPacket(OpenEditPaperMessage.ClientHandler.class, OpenEditPaperMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(OpenEditMcfrSignMessage.ClientHandler.class, OpenEditMcfrSignMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(OpenEditWallNoteMessage.ClientHandler.class, OpenEditWallNoteMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(UpdateWallNoteMessage.ServerHandler.class, UpdateWallNoteMessage.class, Side.SERVER);
    McfrNetworkWrapper.registerPacket(CreateChatBubbleMessage.ServerHandler.class, CreateChatBubbleMessage.class, Side.SERVER);
    McfrNetworkWrapper.registerPacket(SyncChatBubbleTypeMessage.ClientHandler.class, SyncChatBubbleTypeMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(SyncChatBubbleTypeMessage.ServerHandler.class, SyncChatBubbleTypeMessage.class, Side.SERVER);
    McfrNetworkWrapper.registerPacket(UpdateChatBubblePositionMessage.ClientHandler.class, UpdateChatBubblePositionMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(DestroyChatBubbleMessage.ServerHandler.class, DestroyChatBubbleMessage.class, Side.SERVER);
    McfrNetworkWrapper.registerPacket(SyncEntityMessage.ClientHandler.class, SyncEntityMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(SyncEntityMessage.ServerHandler.class, SyncEntityMessage.class, Side.SERVER);
    McfrNetworkWrapper.registerPacket(ModVersionCheckMessage.ServerHandler.class, ModVersionCheckMessage.class, Side.SERVER);
  }

  /**
   * Enregistre les tile entities.
   */
  private void registerTileEntities() {
    GameRegistry.registerTileEntity(TileEntityBarrel.class, "barrel");

    GameRegistry.registerTileEntity(TileEntityBellows.class, "bellows");
    GameRegistry.registerTileEntity(TileEntityStove.class, "stove");

    GameRegistry.registerTileEntity(TileEntityCampfire.class, "campfire");
    GameRegistry.registerTileEntity(TileEntityChandelier.class, "chandelier");

    GameRegistry.registerTileEntity(TileEntityCarpet.class, "carpet");

    GameRegistry.registerTileEntity(TileEntityBookshelf.class, "bookshelf");
    GameRegistry.registerTileEntity(TileEntityCrate.class, "crate");
    GameRegistry.registerTileEntity(TileEntityFoodCrate.class, "food_crate");
    GameRegistry.registerTileEntity(TileEntityLittleChest.class, "little_chest");
    GameRegistry.registerTileEntity(TileEntityPallet.class, "pallet");

    GameRegistry.registerTileEntity(TileEntityCircularSaw.class, "circular_saw");
    GameRegistry.registerTileEntity(TileEntityLoom.class, "loom");
    GameRegistry.registerTileEntity(TileEntityTanningRack.class, "tanning_rack");

    GameRegistry.registerTileEntity(TileEntityNormalSign.class, "sign");
    GameRegistry.registerTileEntity(TileEntityPaperSign.class, "paper_sign");
    GameRegistry.registerTileEntity(TileEntityOrpSign.class, "orp_sign");
    GameRegistry.registerTileEntity(TileEntityTombstone.class, "tombstone");
    GameRegistry.registerTileEntity(TileEntityWallNote.class, "wall_note");

    GameRegistry.registerTileEntity(TileEntityShowcase.class, "showcase");
    GameRegistry.registerTileEntity(TileEntityWeaponsStand.class, "weapons_stand");

    GameRegistry.registerTileEntity(TileEntitySupport.class, "support");

    GameRegistry.registerTileEntity(TileEntityArmChair.class, "arm_chair");
    GameRegistry.registerTileEntity(TileEntityWoodenBench.class, "wooden_bench");
    GameRegistry.registerTileEntity(TileEntityWoodenChair.class, "wooden_chair");
    GameRegistry.registerTileEntity(TileEntityWoodenStool.class, "wooden_stool");
  }

  /**
   * Enregistre les entités.
   */
  private void registerEntities() {
    EntityRegistry.registerModEntity(EntityGrapnel.class, "grapnel", 1000, this, 10, 3, true);
    EntityRegistry.registerModEntity(EntitySailBoat.class, "sailboat", 1001, this, 80, 3, true);
    EntityRegistry.registerModEntity(EntityChatBubble.class, "chat_bubble", 1002, this, 20, 3, true);

    EntityRegistry.registerModEntity(EntitySiker.class, "siker", 1, this, 80, 3, true, 0xBA9B54, 0x5E4B23);
    EntityRegistry.registerModEntity(EntityBormoth.class, "bormoth", 2, this, 80, 3, true, 1000, 1000);
    EntityRegistry.registerModEntity(EntityHoen.class, "hoen", 3, this, 80, 3, true, 0x7E1620, 0x514445);
    EntityRegistry.registerModEntity(EntityGalt.class, "galt", 4, this, 80, 3, true, 0xc7c7c7, 0x81616F);
    EntityRegistry.registerModEntity(EntityNiale.class, "niale", 5, this, 80, 3, true, 0xd0c0c0, 0x180808);
    EntityRegistry.registerModEntity(EntityFjalla.class, "fjalla", 6, this, 80, 3, true, 0xd0c0c0, 0x180808);
    EntityRegistry.registerModEntity(EntityGronle.class, "gronle", 7, this, 80, 3, true, 0xd0c0c0, 0x180808);
    EntityRegistry.registerModEntity(EntityPryf.class, "pryf", 8, this, 80, 3, true, 0xd0c0c0, 0x180808);
    EntityRegistry.registerModEntity(EntityTaure.class, "taure", 9, this, 80, 3, true, 0xd0c0c0, 0x180808);
  }
}