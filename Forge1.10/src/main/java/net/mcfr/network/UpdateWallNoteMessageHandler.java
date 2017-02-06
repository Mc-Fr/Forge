package net.mcfr.network;

import net.mcfr.decoration.signs.tile_entities.TileLargeSign;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UpdateWallNoteMessageHandler implements IMessageHandler<UpdateWallNoteMessage, IMessage> {
  @Override
  @SideOnly(Side.SERVER)
  public IMessage onMessage(final UpdateWallNoteMessage message, MessageContext ctx) {
    BlockPos pos = message.getNotePos();
    World world = ctx.getServerHandler().playerEntity.worldObj;
    IBlockState state = world.getBlockState(pos);
    TileEntity te = world.getTileEntity(pos);

    if (te instanceof TileLargeSign && world.isBlockLoaded(pos)) {
      for (int i = 0; i < message.getLines().length; i++) {
        ((TileLargeSign) te).getText()[i] = new TextComponentString(message.getLines()[i]);
      }
    }
    te.markDirty();
    world.notifyBlockUpdate(pos, state, state, 3);

    return null;
  }
}
