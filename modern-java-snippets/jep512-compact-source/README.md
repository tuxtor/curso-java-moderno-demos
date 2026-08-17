# JEP 512: Compact Source Files and Instance Main Methods

This demo lives outside `src/main/java` (unlike every other `JEPxxx` demo in this
repo) because compact source files **cannot** have a `package` declaration, so
they don't fit into the `com.vorozco.modernjava` package used everywhere else.
They're also meant to be run directly with the single-file source-code
launcher (`java SomeFile.java`), not compiled into `target/classes` and run via
`-cp`.

- `OldStyle.java` — the traditional way: a public class, a
  `public static void main(String[] args)`, and an explicit `import`.
- `NewStyle.java` — the JEP 512 way: no class wrapper, an instance `main()`
  with no parameters, no `import` (`List` is auto-imported from `java.base`),
  and `IO.println` instead of `System.out.println`.

Both print the same shopping list.

## How to run

```bash
cd modern-java-snippets/jep512-compact-source
java OldStyle.java
java NewStyle.java
```

No `javac`/Maven step required for either — the `java` launcher compiles
single source files in-memory before running them.
