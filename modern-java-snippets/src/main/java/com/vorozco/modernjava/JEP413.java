package com.vorozco.modernjava;

public class JEP413 {

  public static void main(String[] args) {
    System.out.println("(old) " + oldDocumentedAdd(2, 3));
    System.out.println("(new) " + newDocumentedAdd(2, 3));
  }

  /**
   * Adds two numbers.
   *
   * <p>
   * Before JEP 413, example code in Javadoc had to be wrapped in
   * {@code 
   * 
   * 
   * 
   * <pre>} and {@code {@code}}, with manual HTML escaping and no
   * syntax highlighting or external file support:
   *
   * <pre>{@code
   * int result = oldDocumentedAdd(2, 3);
   * System.out.println(result); // 5
   * }</pre>
   */
  private static int oldDocumentedAdd(int a, int b) {
    return a + b;
  }

  /**
   * Adds two numbers.
   *
   * <p>
   * JEP 413 introduces the {@code @snippet} tag, giving cleaner syntax,
   * automatic indentation handling, and the option to pull the example
   * from an external source file:
   * {@snippet :
   * int result = newDocumentedAdd(2, 3);
   * System.out.println(result); // 5
   * }
   */
  private static int newDocumentedAdd(int a, int b) {
    return a + b;
  }
}
