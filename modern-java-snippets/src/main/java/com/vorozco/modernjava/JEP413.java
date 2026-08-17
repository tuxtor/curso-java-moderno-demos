package com.vorozco.modernjava;

public class JEP413 {

    public static void main(String[] args) {
        System.out.println("(old) " + oldDocumentedAdd(2, 3));
    }

    private static int oldDocumentedAdd(int a, int b) {
        return a + b;
    }
}
