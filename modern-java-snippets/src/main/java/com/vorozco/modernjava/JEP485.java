package com.vorozco.modernjava;

import java.util.ArrayList;
import java.util.List;

public class JEP485 {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        System.out.println("(old) " + oldBatchesOfThree(numbers));
    }

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
}
