package com.vorozco.modernjava;

import java.lang.reflect.Field;

public class JEP403 {

  public static void main(String[] args) {
    System.out.println("OLD:");
    oldStringBytes("Hola");
  }

  static void oldStringBytes(String s) {
    try {
      Field value = String.class.getDeclaredField("value");
      value.setAccessible(true); // InaccessibleObjectException: java.base does not "opens java.lang"
      byte[] bytes = (byte[]) value.get(s);
      System.out.println("  bytes length: " + bytes.length);
    } catch (Exception e) {
      System.out.println("  " + e.getClass().getSimpleName() + ": " + e.getMessage());
    }
  }

}
