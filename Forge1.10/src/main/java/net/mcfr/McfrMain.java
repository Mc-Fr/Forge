package net.mcfr;

import net.mcfr.coma.PotionComa;
import net.mcfr.decoration.containerBlocks.BlockBarrel;
import net.mcfr.entities.EntityGrapnel;
import net.mcfr.entities.EntitySailBoat;
import net.mcfr.entities.mobs.entity.EntityBormoth;
import net.mcfr.entities.mobs.entity.EntityGalt;
import net.mcfr.entities.mobs.entity.EntityHoen;
import net.mcfr.entities.mobs.entity.EntitySiker;
import net.mcfr.event.PlayerEventHandler;
import net.mcfr.network.GuiHandler;
import net.mcfr.network.McfrNetworkWrapper;
import net.mcfr.network.OpenEditMcfrSignMessage;
import net.mcfr.network.OpenEditMcfrSignMessageHandler;
import net.mcfr.network.OpenEditWallNoteMessage;
import net.mcfr.network.OpenEditWallNoteMessageHandler;
import net.mcfr.network.SyncEntityMessage;
import net.mcfr.network.UpdateWallNoteMessage;
import net.mcfr.network.UpdateWallNoteMessageHandler;
import net.mcfr.proxy.CommonProxy;
import net.mcfr.utils.LargeRecipesUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameData;
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
  @SuppressWarnings("deprecation")
  public void preInit(FMLPreInitializationEvent e) {
    McfrBlocks.init();
    McfrItems.init();
    registerEntities();
    // DEBUG à réactiver une fois le débug terminé.
    // CraftingManager.getInstance().getRecipeList().clear();
    LargeRecipesUtils.addAnvilRecipe(new ItemStack(Items.STICK), 0, "|", "|", "|", "|", "|", '|', new ItemStack(Blocks.PLANKS)); // DEBUG
    FurnaceRecipes.instance().getSmeltingList().clear();

    // GameData.getPotionRegistry().register(PotionComa.COMA);
    GameData.getPotionRegistry().register(28, new ResourceLocation("coma"), new PotionComa());
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
    MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
  }

  private void registerPackets() {
    McfrNetworkWrapper.registerPacket(OpenEditMcfrSignMessageHandler.class, OpenEditMcfrSignMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(OpenEditWallNoteMessageHandler.class, OpenEditWallNoteMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(UpdateWallNoteMessageHandler.class, UpdateWallNoteMessage.class, Side.SERVER);
    McfrNetworkWrapper.registerPacket(SyncEntityMessage.ClientHandler.class, SyncEntityMessage.class, Side.CLIENT);
    McfrNetworkWrapper.registerPacket(SyncEntityMessage.ServerHandler.class, SyncEntityMessage.class, Side.SERVER);
  }

  private void registerEntities() {
    EntityRegistry.registerModEntity(EntityGrapnel.class, "grapnel", 1000, this, 10, 3, true);
    EntityRegistry.registerModEntity(EntitySailBoat.class, "sailboat", 1001, this, 80, 3, true);
    EntityRegistry.registerModEntity(EntitySiker.class, "siker", 1, this, 80, 3, true, 0xBA9B54, 0x5E4B23);
    EntityRegistry.registerModEntity(EntityBormoth.class, "bormoth", 2, this, 80, 3, true, 1000, 1000);
    EntityRegistry.registerModEntity(EntityHoen.class, "hoen", 3, this, 80, 3, true, 0x7E1620, 0x514445);
    EntityRegistry.registerModEntity(EntityGalt.class, "galt", 4, this, 80, 3, true, 0xC7C7C7, 0x81616F);
  }
}