package com.vorozco.modernjava;

public class JEP467 {

    public static void main(String[] args) {
        System.out.println("(old) " + oldDocumentedFactorial(5));
    }

    private static long oldDocumentedFactorial(int n) {
        return n <= 1 ? 1 : n * oldDocumentedFactorial(n - 1);
    }
}
