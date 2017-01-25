package net.mcfr.network;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import net.mcfr.decoration.signs.tileEntities.TileEntityWallNote;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class UpdateWallNoteMessage implements IMessage {
  private BlockPos notePos;
  private String[] lines;

  // Requis par Forge.
  public UpdateWallNoteMessage() {
    this(null, new String[TileEntityWallNote.LINES_NB]);
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
}
