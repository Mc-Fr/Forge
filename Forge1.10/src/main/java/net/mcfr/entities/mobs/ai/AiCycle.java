package net.mcfr.entities.mobs.ai;

/**
 * Cycles de l'IA :
 * <ul>
 * <li>à l'attaque</li>
 * <li>doit fuire</li>
 * <li>en fuite</li>
 * <li>immobile</li>
 * </ul>
 *
 * @author Mc-Fr
 */
public enum AiCycle {
  ATTACKING,
  MUST_FLEE,
  FLEEING,
  IDLE;
}
