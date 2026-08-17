package com.vorozco.modernjava;

public class JEP467 {

    public static void main(String[] args) {
        System.out.println("(old) " + oldDocumentedFactorial(5));
        System.out.println("(new) " + newDocumentedFactorial(5));
    }

    // JEP 467 only changes how a documentation comment is *authored* and
    // rendered in generated Javadoc HTML - it has no effect on runtime
    // behavior. Both methods below do the same recursive factorial; look at
    // their source comments (or generated HTML via `mvn javadoc:javadoc`) to
    // see the actual difference.

    /**
     * Computes the factorial of {@code n}.
     * <p>
     * Before JEP 467, richer formatting required HTML tags mixed into the
     * comment, which is verbose and easy to get wrong:
     * <ul>
     *   <li>{@code <p>} for paragraphs</li>
     *   <li>{@code <ul>}/{@code <li>} for lists</li>
     *   <li>{@code <strong>} for bold text</li>
     * </ul>
     * <strong>Warning</strong>: for large {@code n}, prefer
     * {@link java.math.BigInteger}.
     *
     * @param n the number to compute the factorial of
     * @return {@code n!}
     */
    private static long oldDocumentedFactorial(int n) {
        return n <= 1 ? 1 : n * oldDocumentedFactorial(n - 1);
    }

    /// Computes the factorial of `n`.
    ///
    /// JEP 467 lets Javadoc comments be written in plain Markdown instead,
    /// using `///` on every line:
    /// - `#`/`##` for headings
    /// - `-` for lists (like this one)
    /// - `**bold**` for bold text
    ///
    /// **Warning**: for large `n`, prefer [java.math.BigInteger].
    ///
    /// @param n the number to compute the factorial of
    /// @return `n!`
    private static long newDocumentedFactorial(int n) {
        return n <= 1 ? 1 : n * newDocumentedFactorial(n - 1);
    }
}
