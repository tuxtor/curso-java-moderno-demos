package com.vorozco.modernjava;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

import java.util.Arrays;

public class JEP338 {

    private static final float[] A = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final float[] B = {8, 7, 6, 5, 4, 3, 2, 1};

    public static void main(String[] args) {
        System.out.println("(old) " + Arrays.toString(oldScalarAdd(A, B)));
        System.out.println("(new) " + Arrays.toString(newVectorAdd(A, B)));
    }

    /**
     * Before JEP 338, element-wise array math had to be written as a plain
     * scalar loop - the JIT may auto-vectorize it, but there's no language
     * or API-level guarantee of SIMD execution.
     */
    private static float[] oldScalarAdd(float[] a, float[] b) {
        float[] result = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }

    /**
     * JEP 338's incubating Vector API lets code explicitly express SIMD
     * computations: SPECIES_PREFERRED picks the widest vector lane size the
     * current CPU supports, and add() performs the whole lane in one op.
     * Requires --add-modules jdk.incubator.vector at compile and run time.
     */
    private static float[] newVectorAdd(float[] a, float[] b) {
        VectorSpecies<Float> species = FloatVector.SPECIES_PREFERRED;
        float[] result = new float[a.length];
        int i = 0;
        int upperBound = species.loopBound(a.length);
        for (; i < upperBound; i += species.length()) {
            FloatVector va = FloatVector.fromArray(species, a, i);
            FloatVector vb = FloatVector.fromArray(species, b, i);
            va.add(vb).intoArray(result, i);
        }
        for (; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }
}
