package net.mcfr.core;

import static org.objectweb.asm.Opcodes.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.block.BlockCrops;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Transformer implements IClassTransformer {
  /** Associe une fonction de transformation au nom d'une classe. */
  private static final Map<String, BiConsumer<ClassNode, Boolean>> CLASSES;

  static {
    Map<String, BiConsumer<ClassNode, Boolean>> map = new HashMap<>();
    // map.put("net.minecraft.block.BlockCrops", Transformer::transformBlockCrops);
    // map.put("net.minecraft.block.Block", Transformer::transformBlock);
    map.put("net.minecraft.entity.projectile.EntityFishHook", Transformer::transformEntityFishHook);

    CLASSES = Collections.unmodifiableMap(map);
  }

  @Override
  public byte[] transform(String name, String transformedName, byte[] classCode) {
    return CLASSES.containsKey(transformedName) ? transform(!name.equals(transformedName), transformedName, classCode) : classCode;
  }

  private byte[] transform(boolean obfuscated, String name, byte[] classCode) {
    System.out.println("Transformation de " + name);

    ClassReader classReader = new ClassReader(classCode);
    ClassNode classNode = new ClassNode();
    classReader.accept(classNode, 0);

    BiConsumer<ClassNode, Boolean> f = CLASSES.get(name);
    if (f != null) {
      f.accept(classNode, obfuscated);
    }

    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
    classNode.accept(classWriter);

    return classWriter.toByteArray();
  }

  /**
   * Remplace le code de {@link BlockCrops#canBlockStay BlockCrops.canBlockStay} par le code
   * suivant&nbsp;:
   * 
   * <pre>
   * return !worldIn.isAirBlock(pos.down());
   * </pre>
   * 
   * Bytecode&nbsp;:
   * 
   * <pre>
   * aload_1 // worldIn
   * aload_2 // pos
   * invokevirtual BlockPos.down ()BlockPos
   * invokevirtual World.isAirBlock (BlockPos)Z
   * // not
   * ifne L1
   * iconst_1
   * goto L2
   * L1
   * iconst_0
   * L2
   * return
   * </pre>
   * 
   * @param classNode
   * @param obfuscated
   */
  @SuppressWarnings("unused")
  @Deprecated
  private static void transformBlockCrops(ClassNode classNode, boolean obfuscated) {
    final String methodName = obfuscated ? "f" : "canBlockStay";
    final String methodDesc = obfuscated ? "(Laid;Lcm;Lars;)Z" : "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;)Z";

    for (MethodNode method : classNode.methods) {
      if (method.name.equals(methodName) && method.desc.equals(methodDesc)) {
        AbstractInsnNode node = null;

        // On ajoute les instructions au début
        method.instructions.insert(node = new VarInsnNode(ALOAD, 1));
        method.instructions.insert(node, node = new VarInsnNode(ALOAD, 2));
        method.instructions.insert(node, node = new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(BlockPos.class), obfuscated ? "b" : "down", obfuscated ? "()Lcm;" : "()Lnet/minecraft/util/math/BlockPos;", false));
        method.instructions.insert(node, node = new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(World.class), obfuscated ? "d" : "isAirBlock", obfuscated ? "(Lcm;)Z" : "(Lnet/minecraft/util/math/BlockPos;)Z", false));
        final LabelNode label1 = new LabelNode(new Label());
        method.instructions.insert(node, node = new JumpInsnNode(IFNE, label1));
        method.instructions.insert(node, node = new InsnNode(ICONST_1));
        final LabelNode label2 = new LabelNode(new Label());
        method.instructions.insert(node, node = new JumpInsnNode(GOTO, label2));
        method.instructions.insert(node, node = label1);
        method.instructions.insert(node, node = new InsnNode(ICONST_0));
        method.instructions.insert(node, node = label2);
        method.instructions.insert(node, new InsnNode(IRETURN));
        break;
      }
    }
  }

  /**
   * Remplace le code de {@link net.minecraft.block.Block#isBed Block.isBed} par le code suivant (en
   * italique)&nbsp;:
   * 
   * <pre>
   * return this <i>instanceof BlockBed</i>;
   * </pre>
   * 
   * Bytecode&nbsp;:
   * 
   * <pre>
   * aload_0                // this
   * <i>instanceof BlockBed
   * ireturn</i>
   * </pre>
   * 
   * @param classNode
   * @param obfuscated
   */
  @SuppressWarnings("unused")
  @Deprecated
  private static void transformBlock(ClassNode classNode, boolean obfuscated) {
    final String methodName = "isBed";
    final String methodDesc = obfuscated ? "(Lars;Laih;Lcm;Lrw;)Z" : "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)Z";

    for (MethodNode method : classNode.methods) {
      if (method.name.equals(methodName) && method.desc.equals(methodDesc)) {
        AbstractInsnNode targetNode = null;

        for (AbstractInsnNode node : method.instructions.toArray()) {
          if (node.getOpcode() == ALOAD && ((VarInsnNode) node).var == 0) {
            targetNode = node;
            break;
          }
        }

        if (targetNode != null) {
          AbstractInsnNode node = targetNode;

          // On insère les nouvelles instructions
          method.instructions.insert(node, node = new TypeInsnNode(INSTANCEOF, "net/minecraft/block/BlockBed"));
          method.instructions.insert(node, new InsnNode(IRETURN));
        }
        break;
      }
    }
  }

  /**
   * Modifie le code suivant (en italique) dans {@link EntityFishHook#onUpdate
   * EntityFishHook.onUpdate}&nbsp;:
   * 
   * <pre>
   * if (this.angler.isDead ||
   *     !this.angler.isEntityAlive() ||
   *     itemstack == null ||
   *     itemstack.getItem() <i>instanceof ItemFishHook</i> <span style=
   * "text-decoration:line-through">!= Items.FISHING_ROD</span> ||
   *     this.getDistanceSqToEntity(this.angler) > 1024.0D) {
   *   ...
   * </pre>
   * 
   * Bytecode&nbsp;:
   * 
   * <pre>
   * aload_1                         // itemstack
   * invokevirtual ItemStack.getItem
   * <i>instanceof ItemFishingRod
   * goto L</i>
   * getstatic Items.FISHING_ROD     // On ne supprime pas les instructions existantes
   * if_acmpne L9
   * <i>L
   * ifeq L9</i>
   * </pre>
   * 
   * @param classNode
   * @param obfuscated
   */
  private static void transformEntityFishHook(ClassNode classNode, boolean obfuscated) {
    final String methodName = obfuscated ? "m" : "onUpdate";
    final String methodDesc = "()V";

    for (MethodNode method : classNode.methods) {
      if (method.name.equals(methodName) && method.desc.equals(methodDesc)) {
        AbstractInsnNode targetNode = null;

        for (AbstractInsnNode node : method.instructions.toArray()) {
          AbstractInsnNode prev = node.getPrevious();

          if (node.getOpcode() == INVOKEVIRTUAL && prev.getOpcode() == ALOAD && ((VarInsnNode) prev).var == 1 && node.getNext().getOpcode() == GETSTATIC) {
            targetNode = node;
            break;
          }
        }

        if (targetNode != null) {
          AbstractInsnNode node = targetNode;

          // On insère les nouvelles instructions
          method.instructions.insert(node, node = new TypeInsnNode(INSTANCEOF, Type.getInternalName(ItemFishingRod.class)));
          LabelNode label = new LabelNode(new Label());
          method.instructions.insert(node, node = new JumpInsnNode(GOTO, label));
          node = node.getNext().getNext(); // On saute les 2 suivantes.
          LabelNode label1 = ((JumpInsnNode) node).label;
          method.instructions.insert(node, node = label);
          method.instructions.insert(node, new JumpInsnNode(IFEQ, label1));
        }
        break;
      }
    }
  }
}
