package com.vorozco.modernjava;

import java.util.Arrays;

public class JEP338 {

    private static final float[] A = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final float[] B = {8, 7, 6, 5, 4, 3, 2, 1};

    public static void main(String[] args) {
        System.out.println("(old) " + Arrays.toString(oldScalarAdd(A, B)));
    }

    private static float[] oldScalarAdd(float[] a, float[] b) {
        float[] result = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }
}
