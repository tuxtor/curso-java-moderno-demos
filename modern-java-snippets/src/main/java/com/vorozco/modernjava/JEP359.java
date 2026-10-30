package com.vorozco.modernjava;

import java.util.Objects;

public class JEP359 {

  public static void main(String[] args) {
    System.out.println(oldVerboseClass());
    System.out.println(newVerboseClass());

    System.out.println(oldEqualsComparison());
    System.out.println(newEqualsComparison());
  }

  static String oldVerboseClass() {
    OldPoint p = new OldPoint(3, 4);
    return "(old) " + p;
  }

  static String newVerboseClass() {
    NewPoint p = new NewPoint(3, 4);
    return "(new) " + p;
  }

  static String oldEqualsComparison() {
    OldPoint p1 = new OldPoint(1, 2);
    OldPoint p2 = new OldPoint(1, 2);
    return "(old) equals: " + p1.equals(p2) + ", hashCode match: " + (p1.hashCode() == p2.hashCode());
  }

  static String newEqualsComparison() {
    NewPoint p1 = new NewPoint(1, 2);
    NewPoint p2 = new NewPoint(1, 2);
    return "(new) equals: " + p1.equals(p2) + ", hashCode match: " + (p1.hashCode() == p2.hashCode());
  }

  private record NewPoint(int x, int y) {
  }

  private static final class OldPoint {
    private final int x;
    private final int y;

    OldPoint(int x, int y) {
      this.x = x;
      this.y = y;
    }

    int getX() {
      return x;
    }

    int getY() {
      return y;
    }

    @Override
    public String toString() {
      return "OldPoint[x=" + x + ", y=" + y + "]";
    }
  }
}
