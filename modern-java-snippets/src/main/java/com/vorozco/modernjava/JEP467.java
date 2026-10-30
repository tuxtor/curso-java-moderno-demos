package com.vorozco.modernjava;

public class JEP467 {

  public static void main(String[] args) {
    System.out.println("(old) " + oldDocumentedFactorial(5));
    System.out.println("(new) " + newDocumentedFactorial(5));
  }

  /**
   * Computes the factorial of {@code n}.
   * <p>
   * 
   * @param n the number to compute the factorial of
   * @return {@code n!}
   */
  static long oldDocumentedFactorial(int n) {
    return n <= 1 ? 1 : n * oldDocumentedFactorial(n - 1);
  }

  /// Computes the factorial of {@code n}.
  ///
  /// * Element 1
  ///
  /// * Element 2
  ///
  /// * Element 3
  ///
  /// > Facta non verba
  ///
  /// @param n the number to compute the factorial of
  /// @return {@code n!} ///
  static long newDocumentedFactorial(int n) {
    return n <= 1 ? 1 : n * oldDocumentedFactorial(n - 1);
  }
}
