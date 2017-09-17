package net.mcfr.event;

import net.mcfr.Constants;
import net.mcfr.mecanisms.keys.KeyCode;
import net.mcfr.mecanisms.keys.McfrCodedItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemEventsHandler {
  public static final ResourceLocation KEY_CODE_CAP = new ResourceLocation(Constants.MOD_ID, "keyCode");
  
  @SubscribeEvent
  public void onAttachEntityCapabilities(AttachCapabilitiesEvent<Item> event) {
    if (event.getObject() instanceof McfrCodedItem) {
      event.addCapability(KEY_CODE_CAP, new KeyCode.Provider());
    }
  }
}
