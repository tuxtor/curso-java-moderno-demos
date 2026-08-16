# JEP 201 Module System Demo

Two standalone Maven projects demonstrating Java modules.

## Structure
```
jep201-modules/           (container)
├── util-module/          (library module)
│   └── src/main/java/
│       ├── module-info.java              (exports com.vorozco.util)
│       ├── com/vorozco/util/StringUtil.java
│       └── com/vorozco/internal/InternalUtil.java
└── app-module/           (application module)
    └── src/main/java/
        ├── module-info.java              (requires util.module)
        └── com/vorozco/app/JEP201.java
```

## Execution

```bash
cd jep201-modules/util-module
mvn clean install

cd ../app-module
mvn clean package
mvn exec:exec
```

Output:
```
HELLO WORLD
dlrow olleh
```

## Module Protection

util-module exports only `com.vorozco.util`, hiding `com.vorozco.internal`.

Test:
1. Edit `app-module/src/main/java/com/vorozco/app/JEP201.java`
2. Add: `import com.vorozco.internal.InternalUtil;`
3. Run: `mvn compile`
4. Error: **"package com.vorozco.internal is not visible"**
