# Copilot Instructions

This repo is a collection of standalone Maven projects, each demonstrating a specific Java JEP (JDK Enhancement Proposal) or modern Java feature. There is no root `pom.xml` or multi-module aggregator — each subproject is built and run independently.

## Repository layout

- `modern-java-snippets/` — single Maven module with one `.java` file per JEP demo (e.g. `JEP110.java` for the new `HttpClient` API, `JEP269.java` for `List.of`/`Set.of`/`Map.of` collection factories), all in package `com.vorozco.modernjava`. Each class is a self-contained `main()` demo, typically pairing an `old*()` method (pre-JEP approach) with a `new*()` method (JEP-based approach) and printing both for comparison.
- `jep201-modules/` — two separate Maven projects (`util-module`, `app-module`) demonstrating the JPMS module system (JEP 201/JPMS). `util-module` exports `com.vorozco.util` but hides `com.vorozco.internal`; `app-module` requires `util.module` and cannot access the internal package. See `jep201-modules/README.md` for the walkthrough of this encapsulation.

When adding a new JEP demo to `modern-java-snippets`, follow the existing naming convention: create `JEPxxx.java` in `com.vorozco.modernjava`, and update the `exec-maven-plugin` `mainClass` in `modern-java-snippets/pom.xml` if it should become the runnable entry point.

## Build & run

Requires JDK 25 (Maven compiler `release` is set to 25 in every `pom.xml`).

**modern-java-snippets:**
```bash
cd modern-java-snippets
mvn compile              # compile only
mvn compile exec:java    # run the class configured as <mainClass> in pom.xml (currently JEP110)
```
There is only one `mainClass` configured at a time — to run a different JEP demo, temporarily change `<mainClass>` in `modern-java-snippets/pom.xml` to the desired class (e.g. `com.vorozco.modernjava.JEP269`).

**jep201-modules** (util-module must be installed to the local repo before app-module can resolve it):
```bash
cd jep201-modules/util-module
mvn clean install

cd ../app-module
mvn clean package
mvn exec:exec             # runs java with --module-path, launching app.module/com.vorozco.app.JEP201
```

`modern-java-snippets` has JUnit 6 tests (`mvn test`, add `-DexcludedGroups=network` to skip the internet-dependent JEP110 test). There are no linters configured in this repository.
