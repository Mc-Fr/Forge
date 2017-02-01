package net.mcfr.utils;

import java.lang.reflect.Field;

public final class ReflectionUtils {
  @SuppressWarnings("unchecked")
  public static <T, U> U getValueForField(Class<T> clazz, String fieldName, String obfName, T obj) {
    Field field;

    try {
      // Le nom obfusqué est testé en premier pour optimiser une fois le mod exporté.
      field = clazz.getDeclaredField(obfName);
    }
    catch (NoSuchFieldException | SecurityException ex1) {
      try {
        field = clazz.getDeclaredField(fieldName);
      }
      catch (NoSuchFieldException | SecurityException ex2) {
        throw new RuntimeException(ex2);
      }
    }

    try {
      field.setAccessible(true);
      return (U) field.get(obj);
    }
    catch (IllegalArgumentException | IllegalAccessException ex) {
      throw new RuntimeException(ex);
    }
  }

  private ReflectionUtils() {}
}
