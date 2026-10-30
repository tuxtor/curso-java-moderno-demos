# Modern Java Snippets

Self-contained demos of individual JDK Enhancement Proposals (JEPs), each living in its
own `JEPxxx.java` class under `com.vorozco.modernjava`. Every demo pairs an `old*()` method
(pre-JEP approach) with a `new*()` method (JEP-based approach) and prints both so you can
compare the output directly.

## Requirements

JDK 25 (the `maven.compiler.release` and compiler plugin `release` are both set to 25 in
`pom.xml`).

## Build

```bash
mvn compile
```

## Running a demo

Only one class is wired as the Maven `exec:java` entry point at a time, configured via
`<mainClass>` in `pom.xml` (currently `JEP110`):

```bash
mvn compile exec:java
```

To run a **different** demo without editing the pom, run it directly off the compiled
classes:

```bash
mvn compile
java -cp target/classes com.vorozco.modernjava.JEP269
```

(Swap `JEP269` for any class below.)

## Tests

JUnit 6 tests live in `src/test/java` and compare the `old*`/`new*` variants of each demo
(JEPs 110, 269, 286, 305, 323, 325, 355, 359, 360, 406, 408, 413, 440, 456, 467, 485, 513).
Demos that need special flags (338, 403, 437, 444, 454) or have no old/new pair (491, 512) are not covered.

```bash
mvn test
mvn test -DexcludedGroups=network   # skip JEP110 (needs internet access)
```

## Demos

| Class | JEP | Feature |
|---|---|---|
| `JEP110` | 110 | HTTP Client (old `HttpURLConnection` vs new `java.net.http.HttpClient`) |
| `JEP269` | 269 | Convenience Factory Methods for Collections (`List.of`/`Set.of`/`Map.of`) |
| `JEP286` | 286 | Local-Variable Type Inference (`var`) |
| `JEP305` | 305 | Pattern Matching for `instanceof` |
| `JEP323` | 323 | Local-Variable Syntax for Lambda Parameters |
| `JEP325` | 325 | Switch Expressions |
| `JEP338` | 338 | Vector API (incubator) — **see special instructions below** |
| `JEP355` | 355 | Text Blocks |
| `JEP358` | 358 | Helpful NullPointerExceptions |
| `JEP359` | 359 | Records |
| `JEP360` | 360 | Sealed Classes (see `com.vorozco.modernjava.jep360` package, cars/bikes example) |
| `JEP403` | 403 | Strongly Encapsulate JDK Internals (deep reflection on `String.value` vs `String.getBytes()`) — **see special instructions below** |
| `JEP406` | 406 | Pattern Matching for `switch` (finalized as JEP 441 in JDK 21) |
| `JEP408` | 408 | Simple Web Server (`jwebserver`, `com.sun.net.httpserver.SimpleFileServer`) |
| `JEP413` | 413 | Code Snippets in Java API Documentation (`{@snippet}` tag) — Javadoc-only, see class Javadoc |
| `JEP437` | 437 | Structured Concurrency (second incubator, still preview via `java.util.concurrent.StructuredTaskScope` on JDK 25) — **see special instructions below** |
| `JEP440` | 440 | Record Patterns (finalized in JDK 21) |
| `JEP444` | 444 | Virtual Threads (see `com.vorozco.modernjava.jep444` package: 8 platform threads vs 800 virtual threads, each sleeping 30s) — **see special instructions below** |
| `JEP454` | 454 | Foreign Function & Memory API (calls native `getpid()` from the C standard library directly, no old sample since JNI required separate C glue code) — **see special instructions below** |
| `JEP467` | 467 | Markdown Documentation Comments (`///` comments) — Javadoc-only, see class Javadoc — **see special instructions below** |
| `JEP485` | 485 | Stream Gatherers (`Stream.gather`, finalized in JDK 24; batching a stream into fixed-size chunks) |
| `JEP491` | 491 | Synchronize Virtual Threads without Pinning Platform Threads (finalized in JDK 24) — **see special instructions below** |
| `JEP512` | 512 | Compact Source Files and Instance Main Methods (finalized in JDK 25, see `jep512-compact-source/` folder) — **see special instructions below** |
| `JEP513` | 513 | Flexible Constructor Bodies (finalized in JDK 25; statements before `super(...)`) |

## Special instructions: JEP 338 (Vector API)

JEP 338 is an **incubator** feature: its classes live in the `jdk.incubator.vector` module,
which is not on the default module graph. This requires extra flags at both compile and run
time.

- **Compile time**: already handled — `pom.xml`'s `maven-compiler-plugin` passes
  `--add-modules jdk.incubator.vector`, so `mvn compile` works out of the box.
- **Run time**: `mvn compile exec:java` runs in the same JVM as Maven and **cannot** receive
  extra JVM module flags, so `JEP338` can't be run that way (and isn't set as the pom's
  `mainClass`). Run it directly instead:

```bash
mvn compile
java --add-modules jdk.incubator.vector -cp target/classes com.vorozco.modernjava.JEP338
```

You'll see a `WARNING: Using incubator modules: jdk.incubator.vector` — that's expected.

## Special instructions: JEP 403 (Strongly Encapsulate JDK Internals)

`JEP403` tries to read the private `String.value` field via deep reflection (`setAccessible(true)`),
then does the same with the public `String.getBytes()`. Since JDK 17 the old approach is denied by
default with an `InaccessibleObjectException` (`module java.base does not "opens java.lang"`):

```bash
mvn compile
java -cp target/classes com.vorozco.modernjava.JEP403
```

To see the old behavior, re-open the package explicitly with `--add-opens` (migration workaround
only, not recommended):

```bash
java --add-opens java.base/java.lang=ALL-UNNAMED -cp target/classes com.vorozco.modernjava.JEP403
```

## Special instructions: JEP 413 (Code Snippets in Java API Documentation)

JEP 413 only changes how example code renders in generated Javadoc HTML — running the
class just prints `5` twice, since `oldDocumentedAdd`/`newDocumentedAdd` do the same thing
at runtime. To actually see the feature, generate the Javadoc (ad-hoc, not wired into
`pom.xml`) and open the rendered page:

```bash
cd modern-java-snippets
mvn org.apache.maven.plugins:maven-javadoc-plugin:3.10.0:javadoc -Dshow=private "-DadditionalJOption=--enable-preview" -Dsource=25
```

(The extra `--enable-preview`/`-Dsource=25` flags are only needed because `JEP437` uses a
preview API and is compiled as part of the same project — otherwise the whole-project
Javadoc build would fail on that class.)

Then open `target/reports/apidocs/com/vorozco/modernjava/JEP413.html` in a browser and
compare the two methods:
- `oldDocumentedAdd` — a plain `<pre>{@code ...}</pre>` block, no special styling.
- `newDocumentedAdd` — the `{@snippet}` block, rendered as a highlighted
  `<pre class="snippet">` code box.

`target/reports/` is a generated artifact — safe to delete afterward (`rm -rf
target/reports`).

## Special instructions: JEP 437 (Structured Concurrency)

JEP 437's API is still a **preview** feature on JDK 25 (`java.util.concurrent.StructuredTaskScope`),
so it requires `--enable-preview` at both compile and run time.

- **Compile time**: already handled — `pom.xml`'s `maven-compiler-plugin` passes
  `--enable-preview` (only classes that use preview APIs are marked as requiring it at
  runtime; other classes are unaffected).
- **Run time**: `mvn compile exec:java` runs in the same JVM as Maven, which wasn't started
  with `--enable-preview`, so `JEP437` can't be run that way (and isn't set as the pom's
  `mainClass`). Run it directly instead:

```bash
mvn compile
java --enable-preview -cp target/classes com.vorozco.modernjava.JEP437
```

## Special instructions: JEP 444 (Virtual Threads) — JVM monitoring

Running `JEP444` takes about a minute (30s per phase). While it's running, standard thread
tools show a real difference between the 8 platform threads and the 800 virtual threads:

```bash
mvn compile
java -cp target/classes com.vorozco.modernjava.JEP444 &
PID=$!
```

**During the platform-threads phase** (first ~30s), `jstack` shows all 8 by name:
```bash
jstack $PID | grep platform-worker
```

**During the virtual-threads phase** (next ~30s), the same `jstack $PID` shows **none** of
the 800 `virtual-worker-*` threads — traditional thread-dump/JMX tooling (`jstack`,
`jconsole`, VisualVM, `ThreadMXBean`) only sees platform/carrier threads, not unmounted
virtual threads. This was verified directly: `jstack` reported 0 matches for
`virtual-worker`, only the internal `VirtualThread-unblocker` scheduler thread.

To actually see the virtual threads, use `jcmd`'s JSON thread dump instead, which JEP 444
tooling extended to include them:
```bash
jcmd $PID Thread.dump_to_file -format=json /tmp/threads.json
grep -o 'virtual-worker-[0-9]*' /tmp/threads.json | sort -u | wc -l   # -> 800
```

## Special instructions: JEP 454 (Foreign Function & Memory API)

No external native library needed — `JEP454` calls `getpid()` straight from the C standard
library that's already loaded in every process (`Linker.defaultLookup()`). The FFM API itself
is finalized (non-preview) on JDK 25, but calling native code is a **restricted operation**
that prints a warning unless native access is explicitly enabled:

```bash
mvn compile
java --enable-native-access=ALL-UNNAMED -cp target/classes com.vorozco.modernjava.JEP454
```

Without `--enable-native-access=ALL-UNNAMED` the demo still runs (native access is only
blocked, not yet by default, in JDK 25), but prints:
```
WARNING: A restricted method in java.lang.foreign.Linker has been called
...
WARNING: Restricted methods will be blocked in a future release unless native access is enabled
```

`JEP454` prints the PID returned by the native call side-by-side with
`ProcessHandle.current().pid()` so you can confirm they match — proof the native call really
happened, with no C code, no build step, and no memory allocation required.

## Special instructions: JEP 467 (Markdown Documentation Comments)

Like JEP 413, this only changes how a documentation comment is *authored* and rendered in
generated Javadoc HTML — running the class just prints `120` twice, since
`oldDocumentedFactorial`/`newDocumentedFactorial` do the same recursive computation at
runtime. To see the actual difference, generate the Javadoc and open the rendered page:

```bash
cd modern-java-snippets
mvn org.apache.maven.plugins:maven-javadoc-plugin:3.10.0:javadoc -Dshow=private "-DadditionalJOption=--enable-preview" -Dsource=25
```

Then open `target/reports/apidocs/com/vorozco/modernjava/JEP467.html` and compare:
- `oldDocumentedFactorial` — a traditional `/** ... */` comment using HTML tags
  (`<p>`, `<ul>`/`<li>`, `<strong>`, `{@link}`).
- `newDocumentedFactorial` — a `///`-style Markdown comment: `-` lists, `**bold**`, and
  `` `code` `` render exactly the same as their HTML counterparts, and `[java.math.BigInteger]`
  resolves to the same cross-reference link as `{@link java.math.BigInteger}`.

`target/reports/` is a generated artifact — safe to delete afterward (`rm -rf
target/reports`).

## Special instructions: JEP 491 (Synchronize Virtual Threads without Pinning)

`JEP491` runs fine via `mvn compile exec:java` with no special flags — the JEP 491 fix is
just enabled by default on JDK 24+. For the clearest possible demonstration, though, force a
single carrier thread so there's no doubt the 200 virtual threads are truly sharing it
instead of running on separate carriers:

```bash
mvn compile
java -Djdk.virtualThreadScheduler.parallelism=1 -cp target/classes com.vorozco.modernjava.JEP491
```

Either way, the run finishes in well under 200ms. Before JEP 491 (JDK 21-23), the same code
would have taken roughly 10 seconds (`200 tasks * 50ms`), because each virtual thread stayed
pinned to its carrier while sleeping inside the `synchronized` block, forcing them to run one
at a time instead of concurrently.

## Special instructions: JEP 512 (Compact Source Files and Instance Main Methods)

This one isn't a `com.vorozco.modernjava.JEP512` class like the others — compact source
files can't have a `package` declaration, so it lives in its own top-level
`jep512-compact-source/` folder and is run with the single-file source-code launcher instead
of Maven/`-cp`:

```bash
cd modern-java-snippets/jep512-compact-source
java OldStyle.java   # traditional public class + static void main(String[])
java NewStyle.java   # JEP 512: no class wrapper, instance main(), IO.println, auto-imports
```

See `jep512-compact-source/README.md` for details.

