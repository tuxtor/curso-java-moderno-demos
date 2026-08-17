package com.vorozco.modernjava;

public class JEP305 {

    public static void main(String[] args) {
        System.out.println(oldInstanceofWithCast("Hello, JEP 305!"));
        System.out.println(oldInstanceofWithCast(42));
        System.out.println(oldCombinedCondition("Hi"));
        System.out.println(oldCombinedCondition("Hello, JEP 305!"));
    }

    private static String oldInstanceofWithCast(Object obj) {
        if (obj instanceof String) {
            String s = (String) obj;
            return "(old) String of length " + s.length();
        }
        return "(old) not a String";
    }

    private static String oldCombinedCondition(Object obj) {
        if (obj instanceof String) {
            String s = (String) obj;
            if (s.length() > 5) {
                return "(old) long string: " + s;
            }
        }
        return "(old) short or not a string";
    }
}
