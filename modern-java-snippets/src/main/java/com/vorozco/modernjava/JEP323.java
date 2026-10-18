package com.vorozco.modernjava;

import java.util.function.BiFunction;
import java.util.function.Function;

public class JEP323 {

  public static void main(String[] args) {
    System.out.println(oldSingleParamLambda());
    System.out.println(newSingleParamLambda());

    System.out.println(oldMultiParamLambda());
    System.out.println(newMultiParamLambda());

    System.out.println(oldAnnotatedParamLambda());
    System.out.println(newAnnotatedParamLambda());
  }

  private static String oldSingleParamLambda() {
    Function<String, String> shout = (String s) -> s.toUpperCase();
    return shout.apply("hello jep 323");
  }

  private static String newSingleParamLambda() {
    Function<String, String> shout = (var s) -> s.toUpperCase();
    return shout.apply("hello jep 323");
  }

  private static String oldMultiParamLambda() {
    BiFunction<Integer, Integer, Integer> sum = (Integer a, Integer b) -> a + b;
    return "sum = " + sum.apply(3, 4);
  }

  private static String newMultiParamLambda() {
    BiFunction<Integer, Integer, Integer> sum = (var a, var b) -> a + b;
    return "sum = " + sum.apply(3, 4);
  }

  private static String oldAnnotatedParamLambda() {
    Function<String, String> trim = (final String s) -> s.trim();
    return "[" + trim.apply("  padded  ") + "]";
  }

  private static String newAnnotatedParamLambda() {
    Function<String, String> trim = (final var s) -> s.trim();
    return "[" + trim.apply("  padded  ") + "]";
  }
}
