package com.vorozco.modernjava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class JEP485 {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        System.out.println("(old) " + oldBatchesOfThree(numbers));
        System.out.println("(new) " + newBatchesOfThree(numbers));
    }

    /**
     * Before JEP 485, there was no intermediate stream operation for custom,
     * stateful transformations like batching. The stream had to be collected
     * to a list first, then chunked with an imperative loop - losing
     * laziness and requiring manual index bookkeeping.
     */
    private static List<List<Integer>> oldBatchesOfThree(List<Integer> numbers) {
        List<List<Integer>> batches = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        for (int number : numbers) {
            current.add(number);
            if (current.size() == 3) {
                batches.add(current);
                current = new ArrayList<>();
            }
        }
        if (!current.isEmpty()) {
            batches.add(current);
        }
        return batches;
    }

    /**
     * JEP 485 adds {@code Stream.gather(Gatherer)}, a general-purpose
     * intermediate operation for custom stateful transformations. The JDK
     * ships ready-made gatherers for common cases like fixed-size windows,
     * so batching becomes a single, lazy, composable stream step.
     */
    private static List<List<Integer>> newBatchesOfThree(List<Integer> numbers) {
        return numbers.stream()
                .gather(Gatherers.windowFixed(3))
                .toList();
    }
}
