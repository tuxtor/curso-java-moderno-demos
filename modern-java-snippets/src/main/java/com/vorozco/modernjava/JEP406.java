package com.vorozco.modernjava;

public class JEP406 {

    public static void main(String[] args) {
        Object[] inputs = {42, "Hi", "Hello, JEP 406!", null};

        for (Object input : inputs) {
            System.out.println("(old) " + oldDescribe(input));
            System.out.println("(new) " + newDescribe(input));
        }
    }

    /**
     * Before JEP 406 (finalized as JEP 441 in JDK 21), matching an Object
     * against several types meant a chain of instanceof checks with
     * explicit casts, plus a separate, easy-to-forget null check up front.
     */
    private static String oldDescribe(Object obj) {
        if (obj == null) {
            return "it's null";
        } else if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            return "an Integer: " + i;
        } else if (obj instanceof String) {
            String s = (String) obj;
            if (s.length() > 5) {
                return "a long String: " + s;
            }
            return "a short String: " + s;
        } else {
            return "something else";
        }
    }

    /**
     * JEP 406 lets switch use type patterns directly, combine them with a
     * boolean guard via 'when', and even handle 'case null' inline instead
     * of a separate check before the switch.
     */
    private static String newDescribe(Object obj) {
        return switch (obj) {
            case null -> "it's null";
            case Integer i -> "an Integer: " + i;
            case String s when s.length() > 5 -> "a long String: " + s;
            case String s -> "a short String: " + s;
            default -> "something else";
        };
    }
}
