package com.vorozco.modernjava;

import java.util.Objects;

public class JEP359 {

    public static void main(String[] args) {
        System.out.println(oldVerboseClass());
        System.out.println(oldEqualsComparison());
    }

    private static String oldVerboseClass() {
        OldPoint p = new OldPoint(3, 4);
        return "(old) " + p;
    }

    private static String oldEqualsComparison() {
        OldPoint p1 = new OldPoint(1, 2);
        OldPoint p2 = new OldPoint(1, 2);
        return "(old) equals: " + p1.equals(p2) + ", hashCode match: " + (p1.hashCode() == p2.hashCode());
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
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof OldPoint)) {
                return false;
            }
            OldPoint other = (OldPoint) o;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "OldPoint[x=" + x + ", y=" + y + "]";
        }
    }
}
