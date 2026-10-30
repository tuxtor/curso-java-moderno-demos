# Curso Java Moderno — Demos

Companion demos for the *Java Moderno* course. Each demo illustrates a specific JEP
(JDK Enhancement Proposal) or modern Java feature, usually contrasting the "old" way with
the "new" way.

There is no root `pom.xml`: every subproject is built and run on its own.

## Requirements

- JDK 25
- Maven 3.9+

## Projects

| Folder | What it covers |
|---|---|
| [`modern-java-snippets/`](modern-java-snippets/README.md) | One `JEPxxx.java` class per JEP (110, 269, 286, 305, 323, 325, 338, 355, 358, 359, 360, 403, 406, 408, 413, 437, 440, 444, 454, 456, 467, 485, 491, 512, 513), each pairing an `old*()` and `new*()` method. Includes JUnit 6 tests. |
| [`jep201-modules/`](jep201-modules/README.md) | JPMS (JEP 201): `util-module` exports `com.vorozco.util` and hides `com.vorozco.internal`; `app-module` consumes it. |
| [`jep310-appcds/`](jep310-appcds/README.md) | Application Class-Data Sharing (JEP 310) walkthrough, reusing the `modern-java-snippets` jar. |

## Quick start

```bash
# Snippets
cd modern-java-snippets
mvn compile exec:java      # runs the class set as <mainClass> in pom.xml (currently JEP110)
mvn test                   # JUnit 6 tests (add -DexcludedGroups=network to skip internet-dependent ones)

# Modules (util-module must be installed first)
cd jep201-modules/util-module && mvn clean install
cd ../app-module && mvn clean package && mvn exec:exec
```

Some demos use preview/incubator features or restricted operations and need extra JVM flags
(JEP 338, 403, 437, 444, 454, 512). Their "Special instructions" sections in the
[`modern-java-snippets` README](modern-java-snippets/README.md) explain how to run them.
