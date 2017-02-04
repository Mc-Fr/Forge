package net.mcfr.decoration.lighting;

import java.util.Random;

import net.mcfr.utils.NameUtils;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLargeTorch extends BlockTorch {
  public BlockLargeTorch() {
    super();
    String name = "large_torch";
    setRegistryName(name);
    setUnlocalizedName(NameUtils.getUnlocalizedName(name));
    setSoundType(SoundType.WOOD);
    setLightLevel(0.75f);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    EnumFacing facing = (EnumFacing) stateIn.getValue(FACING);
    double x = pos.getX() + 0.5;
    double y = pos.getY() + 0.8;
    double z = pos.getZ() + 0.5;

    if (facing.getAxis().isHorizontal()) {
      EnumFacing opposite = facing.getOpposite();
      worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + 0.2 * opposite.getFrontOffsetX(), y + 0.2, z + 0.2 * opposite.getFrontOffsetZ(), 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.2 * opposite.getFrontOffsetX(), y + 0.2, z + 0.2 * opposite.getFrontOffsetZ(), 0, 0, 0);
    }
    else {
      worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0, 0, 0);
      worldIn.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0);
    }
  }
}
