package com.vorozco.modernjava;

import java.util.*;

public class JEP269 {

    public static void main(String[] args) {
        System.out.println(oldListCreation());
        System.out.println(newListCreation());
        
        System.out.println(oldSetCreation());
        System.out.println(newSetCreation());
        
        System.out.println(oldMapCreation());
        System.out.println(newMapCreation());
    }

    private static String oldListCreation() {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("JavaScript");
        return Collections.unmodifiableList(list).toString();
    }

    private static String newListCreation() {
        List<String> list = List.of("Java", "Python", "JavaScript");
        return list.toString();
    }

    private static String oldSetCreation() {
        Set<String> set = new HashSet<>();
        set.add("Red");
        set.add("Green");
        set.add("Blue");
        return Collections.unmodifiableSet(set).toString();
    }

    private static String newSetCreation() {
        Set<String> set = Set.of("Red", "Green", "Blue");
        return set.toString();
    }

    private static String oldMapCreation() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        return Collections.unmodifiableMap(map).toString();
    }

    private static String newMapCreation() {
        Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
        return map.toString();
    }
}
