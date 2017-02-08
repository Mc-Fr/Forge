package net.mcfr.network;

import io.netty.buffer.ByteBuf;
import net.mcfr.decoration.signs.guis.GuiEditWallNote;
import net.mcfr.decoration.signs.tile_entities.TileEntityWallNote;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OpenEditWallNoteMessage implements IMessage {
  private BlockPos notePos;

  // Requis par Forge.
  public OpenEditWallNoteMessage() {
    this(null);
  }

  public OpenEditWallNoteMessage(BlockPos signPos) {
    this.notePos = signPos;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.notePos.getX());
    buf.writeInt(this.notePos.getY());
    buf.writeInt(this.notePos.getZ());
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    int x = buf.readInt();
    int y = buf.readInt();
    int z = buf.readInt();
    this.notePos = new BlockPos(x, y, z);
  }

  public BlockPos getSignPos() {
    return this.notePos;
  }

  public static class ClientHandler implements IMessageHandler<OpenEditWallNoteMessage, IMessage> {
    @Override
    public IMessage onMessage(final OpenEditWallNoteMessage message, MessageContext ctx) {
      Minecraft.getMinecraft().addScheduledTask(() -> {
        BlockPos pos = message.getSignPos();
        World world = NetworkUtils.getLocalWorld();
        TileEntity te = world.getTileEntity(pos);

        if (!(te instanceof TileEntityWallNote)) {
          te = new TileEntityWallNote();
          te.setWorldObj(world);
          te.setPos(message.getSignPos());
        }

        openGui((TileEntityWallNote) te);
      });

      return null;
    }

    @SideOnly(Side.CLIENT)
    public void openGui(TileEntityWallNote te) {
      Minecraft.getMinecraft().displayGuiScreen(new GuiEditWallNote(te));
    }
  }
}
