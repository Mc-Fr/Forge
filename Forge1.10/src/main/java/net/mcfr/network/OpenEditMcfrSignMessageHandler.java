package net.mcfr.network;

import net.mcfr.decoration.signs.GuiEditSign;
import net.mcfr.decoration.signs.tileEntities.TileEntityMcfrSign;
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
    Minecraft.getMinecraft().addScheduledTask(new Runnable() {
      @Override
      public void run() {
        BlockPos pos = message.getSignPos();
        World world = Minecraft.getMinecraft().theWorld;
        TileEntity te = world.getTileEntity(pos); // Null p*tain !

        System.out.println(" " + te + " " + System.currentTimeMillis());
        if (te instanceof TileEntityMcfrSign)
          Minecraft.getMinecraft().displayGuiScreen(new GuiEditSign((TileEntityMcfrSign) te));
      }
    });

    return null;
  }
}