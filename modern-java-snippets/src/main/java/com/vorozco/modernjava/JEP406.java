package com.vorozco.modernjava;

public class JEP406 {

    public static void main(String[] args) {
        Object[] inputs = {42, "Hi", "Hello, JEP 406!", null};

        for (Object input : inputs) {
            System.out.println("(old) " + oldDescribe(input));
        }
    }

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
}
