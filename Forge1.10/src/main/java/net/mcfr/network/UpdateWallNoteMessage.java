package net.mcfr.network;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import net.mcfr.decoration.signs.tile_entities.TileEntityLargeSign;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateWallNoteMessage implements IMessage {
  private BlockPos notePos;
  private String[] lines;

  // Requis par Forge.
  public UpdateWallNoteMessage() {
    this(null, new String[TileEntityLargeSign.LINES_NB]);
  }

  public UpdateWallNoteMessage(BlockPos signPos, String[] lines) {
    this.notePos = signPos;
    this.lines = lines;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.notePos.getX());
    buf.writeInt(this.notePos.getY());
    buf.writeInt(this.notePos.getZ());

    // Nombre de lignes
    buf.writeInt(this.lines.length);
    for (int i = 0; i < this.lines.length; i++) {
      String line = this.lines[i];

      if (line != null) {
        byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
        // Indice de la ligne
        buf.writeInt(i);
        // Longueur de le ligne
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
      }
    }
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    int x = buf.readInt();
    int y = buf.readInt();
    int z = buf.readInt();
    this.notePos = new BlockPos(x, y, z);

    int length = buf.readInt();
    for (int i = 0; i < length; i++) {
      int index = buf.readInt();
      int l = buf.readInt();
      byte[] bytes = buf.readBytes(l).array();

      try {
        this.lines[index] = new String(bytes, StandardCharsets.UTF_8.name());
      }
      catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public BlockPos getNotePos() {
    return this.notePos;
  }

  public String[] getLines() {
    return this.lines;
  }

  public static class ServerHandler implements IMessageHandler<UpdateWallNoteMessage, IMessage> {
    @Override
    public IMessage onMessage(final UpdateWallNoteMessage message, MessageContext ctx) {
      BlockPos pos = message.getNotePos();
      World world = ctx.getServerHandler().playerEntity.worldObj;
      IBlockState state = world.getBlockState(pos);
      TileEntity te = world.getTileEntity(pos);

      if (te instanceof TileEntityLargeSign && world.isBlockLoaded(pos)) {
        for (int i = 0; i < message.getLines().length; i++) {
          ((TileEntityLargeSign) te).getText()[i] = new TextComponentString(message.getLines()[i]);
        }
      }
      te.markDirty();
      world.notifyBlockUpdate(pos, state, state, 3);

      return null;
    }
  }
}
