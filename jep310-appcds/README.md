# JEP 310 Application Class-Data Sharing (AppCDS) Demo

JEP 310 is a JVM startup/runtime optimization, not a language feature — there is no
`JEPxxx.java` source file to write. Instead, it's demonstrated by running an already-built
jar with CDS flags and comparing how classes are loaded.

This demo reuses the existing `modern-java-snippets` jar as the subject application.

## Prerequisite

Build the jar first:
```bash
cd ../modern-java-snippets
mvn package -q
```

## Steps

Run all commands from `modern-java-snippets/` (so the paths below resolve):

```bash
cd modern-java-snippets
JAR=target/modern-java-snippets-1.0.0-SNAPSHOT.jar

# 1. Record which classes get loaded when the app runs
java -XX:DumpLoadedClassList=classes.lst -cp $JAR com.vorozco.modernjava.JEP269

# 2. Build the AppCDS archive from that class list
java -Xshare:dump -XX:SharedClassListFile=classes.lst \
     -XX:SharedArchiveFile=app-cds.jsa -cp $JAR

# 3a. Run WITHOUT the archive, logging class-load sources
java -Xlog:class+load:file=without-cds.log -cp $JAR com.vorozco.modernjava.JEP269

# 3b. Run WITH the archive, logging class-load sources
java -XX:SharedArchiveFile=app-cds.jsa -Xlog:class+load:file=with-cds.log -cp $JAR com.vorozco.modernjava.JEP269
```

## Verifying it worked

The JDK's own bootstrap classes (`java.lang.*`, etc.) are shared by default even without
`-XX:SharedArchiveFile`, so grepping for `"shared objects file"` alone isn't proof of
AppCDS. The real signal is the **application's own class**:

```bash
grep "JEP269" without-cds.log
# [...] com.vorozco.modernjava.JEP269 source: file:/.../modern-java-snippets-1.0.0-SNAPSHOT.jar

grep "JEP269" with-cds.log
# [...] com.vorozco.modernjava.JEP269 source: shared objects file
```

Without the archive, `JEP269` is loaded straight from the jar (`source: file:...`). With
`-XX:SharedArchiveFile=app-cds.jsa`, the same class is loaded from the shared archive
(`source: shared objects file`) — confirming AppCDS is preloading and reusing metadata for
your own application class, not just JDK internals.

## Cleanup

The generated `classes.lst`, `app-cds.jsa`, and `*.log` files are build artifacts — delete
them or leave them out of version control:
```bash
rm -f classes.lst app-cds.jsa with-cds.log without-cds.log
```
