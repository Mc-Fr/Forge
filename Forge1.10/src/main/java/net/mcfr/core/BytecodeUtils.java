package net.mcfr.core;

import java.lang.reflect.Field;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * Classe utilitaire pour l'affichage de bytecode.
 *
 * @author Mc-Fr
 */
class BytecodeUtils {
  /**
   * Affiche le bytecode à partir de l'instruction donnée.
   * 
   * @param start l'instruction de départ.
   * @param length le nombre d'instructions à afficher
   */
  static void displayCode(AbstractInsnNode start, int length) {
    AbstractInsnNode node = start;

    for (int i = 0; i < length && node != null; i++, node = node.getNext()) {
      if (node instanceof MethodInsnNode) {
        MethodInsnNode m = (MethodInsnNode) node;
        System.out.println(getOpcodeName(node.getOpcode()) + " " + m.owner + "." + m.name + " " + m.desc);
      }
      if (node instanceof TypeInsnNode)
        System.out.println(getOpcodeName(node.getOpcode()) + " " + ((TypeInsnNode) node).desc);
      if (node instanceof JumpInsnNode)
        System.out.println(getOpcodeName(node.getOpcode()) + " " + ((JumpInsnNode) node).label.getLabel());
      if (node instanceof FieldInsnNode) {
        FieldInsnNode f = (FieldInsnNode) node;
        System.out.println(getOpcodeName(node.getOpcode()) + " " + f.owner + "." + f.name + ": " + f.desc);
      }
      if (node instanceof VarInsnNode)
        System.out.println(getOpcodeName(node.getOpcode()) + " " + ((VarInsnNode) node).var);
      if (node instanceof LabelNode)
        System.out.println(((LabelNode) node).getLabel());
    }
  }

  /**
   * Retourne le nom de l'opcode correspondant au code donné.
   * 
   * @param opcode le code
   * @return le nom de l'opcode
   */
  private static String getOpcodeName(int opcode) {
    Field[] fields = Opcodes.class.getDeclaredFields();
    String name = null;

    for (Field field : fields) {
      try {
        if (field.getType() == int.class && field.getInt(null) == opcode) {
          name = field.getName().toLowerCase();
          break;
        }
      }
      catch (IllegalArgumentException | IllegalAccessException e) {}
    }

    return name;
  }

  private BytecodeUtils() {}
}
