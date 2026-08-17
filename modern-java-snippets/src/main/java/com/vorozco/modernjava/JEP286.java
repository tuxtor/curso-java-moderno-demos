package com.vorozco.modernjava;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class JEP286 {

    public static void main(String[] args) {
        System.out.println(oldPrimitiveDeclaration());
        System.out.println(oldObjectDeclaration());
        System.out.println(oldForEachLoop());
        System.out.println(oldTryWithResources());
    }

    private static String oldPrimitiveDeclaration() {
        int count = 42;
        return "count = " + count;
    }

    private static String oldObjectDeclaration() {
        String message = "Hello, JEP 286!";
        return message;
    }

    private static String oldForEachLoop() {
        List<String> languages = List.of("Java", "Kotlin", "Scala");
        StringBuilder result = new StringBuilder();
        for (String language : languages) {
            result.append(language).append(" ");
        }
        return result.toString().trim();
    }

    private static String oldTryWithResources() {
        try (StringReader reader = new StringReader("data")) {
            int firstChar = reader.read();
            return "First char: " + (char) firstChar;
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
