package net.mcfr.network;

import net.mcfr.decoration.signs.gui.GuiEditMcfrSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityMcfrSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityNormalSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityOrpSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityPaperSign;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OpenEditMcfrSignMessageHandler implements IMessageHandler<OpenEditMcfrSignMessage, IMessage> {
  @Override
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(final OpenEditMcfrSignMessage message, MessageContext ctx) {
    Minecraft.getMinecraft().addScheduledTask(() -> {
      BlockPos pos = message.getSignPos();
      World world = Minecraft.getMinecraft().theWorld;
      TileEntity te = world.getTileEntity(pos);

      if (!(te instanceof TileEntityMcfrSign)) {
        switch (message.getSignType()) {
          case NORMAL:
            te = new TileEntityNormalSign();
            break;
          case PAPER:
            te = new TileEntityPaperSign();
            break;
          case ORP:
            te = new TileEntityOrpSign();
            break;
        }
        te.setWorldObj(world);
        te.setPos(message.getSignPos());
      }

      Minecraft.getMinecraft().displayGuiScreen(new GuiEditMcfrSign((TileEntityMcfrSign) te));
    });

    return null;
  }
}