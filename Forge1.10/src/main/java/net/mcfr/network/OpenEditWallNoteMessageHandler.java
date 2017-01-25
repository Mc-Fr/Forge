package net.mcfr.network;

import net.mcfr.decoration.signs.gui.GuiEditWallNote;
import net.mcfr.decoration.signs.tileEntities.TileEntityWallNote;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OpenEditWallNoteMessageHandler implements IMessageHandler<OpenEditWallNoteMessage, IMessage> {
  @Override
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(final OpenEditWallNoteMessage message, MessageContext ctx) {
    Minecraft.getMinecraft().addScheduledTask(() -> {
      BlockPos pos = message.getSignPos();
      World world = Minecraft.getMinecraft().theWorld;
      TileEntity te = world.getTileEntity(pos);

      if (!(te instanceof TileEntityWallNote)) {
        te = new TileEntityWallNote();
        te.setWorldObj(world);
        te.setPos(message.getSignPos());
      }

      Minecraft.getMinecraft().displayGuiScreen(new GuiEditWallNote((TileEntityWallNote) te));
    });

    return null;
  }
}
